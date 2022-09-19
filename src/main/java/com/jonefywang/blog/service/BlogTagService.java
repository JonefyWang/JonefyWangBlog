package com.jonefywang.blog.service;

import com.jonefywang.blog.common.dto.BlogDto;
import com.jonefywang.blog.entity.BlogTag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JonefyWang
 * @since 2022-09-16
 */
public interface BlogTagService extends IService<BlogTag> {

    boolean saveTagBlog(BlogDto blogDto);
}
