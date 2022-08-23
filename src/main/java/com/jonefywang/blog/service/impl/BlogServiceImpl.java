package com.jonefywang.blog.service.impl;

import com.jonefywang.blog.entity.Blog;
import com.jonefywang.blog.mapper.BlogMapper;
import com.jonefywang.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JonefyWAng
 * @since 2022-08-23
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
