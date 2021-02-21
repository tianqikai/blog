package com.tqk.blog.service;

import com.tqk.blog.pojo.BlLog;
import com.tqk.blog.utils.Page;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * <p>
 * 接口访问日志表服务层接口
 * </p>
 *
 * @author tianqikai
 * @date 2020年10月25日23:42:02
 * @Version 1.0
 *
 */
public interface LogService {

    /**
     * 保存
     * @param logger
     */
    void save(BlLog logger);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<BlLog> getByPage(Page<BlLog> page);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id集合删除
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 查询数据，构建成workbook用于导出
     * @return
     */
    Workbook export();
}
