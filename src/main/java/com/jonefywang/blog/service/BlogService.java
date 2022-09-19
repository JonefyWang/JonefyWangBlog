package com.jonefywang.blog.service;

import com.jonefywang.blog.common.dto.BlogDto;
import com.jonefywang.blog.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JonefyWAng
 * @since 2022-08-23
 */
public interface BlogService extends IService<Blog> {


    /**
     * 保存或更新博客
     *
     * @return
     */
    boolean saveOrUpdateBlog(BlogDto blogDto);
}
