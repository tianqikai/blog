package com.tqk.blog.controller;


import com.tqk.blog.enums.ResultEnum;
import com.tqk.blog.pojo.BlLog;
import com.tqk.blog.service.LogService;
import com.tqk.blog.utils.Page;
import com.tqk.blog.utils.Result;
import com.tqk.blog.utils.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: tianqikai
 * @Date: 2020年10月26日00:10:43
 * @Version 1.0
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<BlLog>> getByPage(@RequestBody Page<BlLog> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"log_url", "log_status", "log_method", "log_time", "created_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = logService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id) {
        logService.deleteById(id);
        return new Result<>("删除成功！");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.GET)
    public Result<Object> deleteByIds(@RequestBody List<Integer> ids) {
        logService.deleteByIds(ids);
        return new Result<>("删除成功！");
    }

    /**
     * 全部导出
     *
     * @throws Exception
     */
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(HttpServletResponse response) throws Exception {
        Workbook workbook = logService.export();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + "日志");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}
