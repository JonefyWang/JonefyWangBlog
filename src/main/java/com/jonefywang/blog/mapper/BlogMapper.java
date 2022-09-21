package com.jonefywang.blog.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jonefywang.blog.common.dto.BlogDto;
import com.jonefywang.blog.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JonefyWAng
 * @since 2022-08-23
 */
@Component
public interface BlogMapper extends BaseMapper<Blog> {

    IPage<BlogDto> listBlogs(Page currentPage);
}
