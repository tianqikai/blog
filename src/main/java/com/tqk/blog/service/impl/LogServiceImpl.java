package com.tqk.blog.service.impl;

import com.tqk.blog.excel.entity.ExportParams;
import com.tqk.blog.excel.handler.ExcelExportHandler;
import com.tqk.blog.mapper.BlLogMapper;
import com.tqk.blog.pojo.BlLog;
import com.tqk.blog.service.LogService;
import com.tqk.blog.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 接口访问日志表服务实现类
 * </p>
 *
 * @author 田起凯
 * @date 2020年10月11日13:02:23
 * @Version 1.0
 *
 */
@Component
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    private BlLogMapper logMapper;

    /**
     * 保存
     * @param logger
     */
    @Override
    public void save(BlLog logger) {
        logMapper.save(logger);
//        logMapper.insert(logger);
    }

    @Override
    public Page<BlLog> getByPage(Page<BlLog> page) {
        // 查询数据
        List<BlLog> logList = logMapper.getByPage(page);
        page.setList(logList);
        // 查询总数
        int totalCount = logMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public void deleteById(Integer id) {
        logMapper.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        logMapper.deleteByIds(ids);
    }

    @Override
    public Workbook export() {
        List<BlLog> logList = logMapper.getAll();
        return new ExcelExportHandler().createSheet(new ExportParams("最新日志", "sheet1"), BlLog.class, logList);
    }
}
