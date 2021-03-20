package com.tqk.blog.service;

import com.tqk.blog.pojo.BlVideo;
import com.tqk.blog.utils.Page;

/**
 * @program: blog
 * @description:
 * @author: tianqikai
 * @create: 2021-03-19 23:57
 **/
public interface VideoService {
    /**
     * 保存
     * @param vedio
     */
    void save(BlVideo vedio);

    /**
     * 更新
     * @param vedio
     */
    void update(BlVideo vedio);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    BlVideo getById(Integer id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 启用
     * @param id
     */
    void enableById(Integer id);

    /**
     * 弃用
     * @param id
     */
    void disableById(Integer id);
    /**
     * @Method：获取视频资源
     * @Date： 2021/3/20 18:06
     * @Author： Administrator
     * @Version  1.0
     */
    Page<BlVideo> getAll(Page<BlVideo> page);

}
