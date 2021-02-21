package com.tqk.blog.aspect;

import com.alibaba.fastjson.JSON;
import com.tqk.blog.enums.StateEnums;
import com.tqk.blog.pojo.BlLog;
import com.tqk.blog.service.LogService;
import com.tqk.blog.utils.StringUtils;
import com.tqk.blog.utils.ThreadLocalContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

/**
 * 切面输出基本信息
 * 以及记录日志
 *
 * @author: 杨德石
 * @date: 2019/8/5 13:22
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class RequestAspect {

    @Autowired
    private LogService logService;

    /**
     * 两个..代表所有子目录，最后括号里的两个..代表所有参数
     */
    @Pointcut("execution( * com.tqk.*.controller..*(..))")
    public void logPointCut() {
    }

    /**
     * 方法执行之前调用
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        // 接收到请求，记录请求内容
        //RequestContextHolder：持有上下文的Request容器
        //通过RequestContextHolder的静态方法可以随时随地取到当前请求的request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        // 记录下请求内容
        printRequestLog(joinPoint, request, uri);
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();
        long time = System.currentTimeMillis() - startTime;
        log.info("耗时 : {}", time);
        BlLog logger = ThreadLocalContext.get().getLogger();
        logger.setLogTime(time);
        return ob;
    }

    /**
     * 后置通知
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) {
        String result = JSON.toJSONString(ret);
        log.info("返回值：{}", JSON.toJSONString(ret));
        BlLog logger = ThreadLocalContext.get().getLogger();
        logger.setLogResult(result);
        logService.save(logger);
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        BlLog logger = ThreadLocalContext.get().getLogger();
        logger.setLogStatus(StateEnums.REQUEST_ERROR.getCode());
        String exception = StringUtils.getPackageException(e, "com.tqk");
        logger.setLogMessage(exception);
        Date date = new Date();
        logger.setCreatedTime(new Timestamp(date.getTime()));
        logger.setLogTime(0L);
        System.out.println(logger.toString());
        logService.save(logger);
    }

    /**
     * 打印请求日志
     *
     * @param joinPoint
     * @param request
     * @param uri
     */
    private void printRequestLog(JoinPoint joinPoint, HttpServletRequest request, String uri) {
        // 拿到切面方法
        log.info("请求地址 : {}", uri);
        log.info("请求方式 : {}", request.getMethod());
        // 获取真实的ip地址
        String ip = StringUtils.getRemoteIp(request);
        log.info("IP : {}", ip);
        String controllerName = joinPoint.getSignature().getDeclaringTypeName();
        log.info("方法 : {}.{}", controllerName, joinPoint.getSignature().getName());
        String params = Arrays.toString(joinPoint.getArgs());
        log.info("请求参数：{}", params);
        // 获取日志实体
        BlLog logger = ThreadLocalContext.get().getLogger();
        logger.setLogUrl(uri);
        logger.setLogParams(params);
        logger.setLogStatus(StateEnums.REQUEST_SUCCESS.getCode());
        logger.setLogMethod(request.getMethod());
        System.out.println("创建时间："+logger.getCreatedTime());
        logger.setLogIp(ip);
    }

}
