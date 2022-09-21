package com.jonefywang.blog.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jonefywang.blog.common.dto.BlogDto;
import com.jonefywang.blog.entity.Blog;
import com.jonefywang.blog.entity.User;
import com.jonefywang.blog.mapper.BlogMapper;
import com.jonefywang.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jonefywang.blog.service.UserService;
import com.jonefywang.blog.utils.SnowFlake;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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


    @Autowired
    private UserService userService;

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public IPage listBlogs(Integer currentPage) {
        IPage<BlogDto> page = blogMapper.listBlogs(new Page(currentPage,5));

        return page;
    }

    @Override
    public boolean saveOrUpdateBlog(BlogDto blogDto) {
        Blog temp = null;
        SnowFlake snowFlake = null;
        //根据blogId判断该博客是新建还是修改
        if (StringUtils.isEmpty(blogDto.getBlogId())){
            temp = new Blog();
            temp.setId(String.valueOf(snowFlake.nextId()));
            temp.setTitle(blogDto.getTitle());
            temp.setUserId(blogDto.getUserId());
            temp.setDescription(blogDto.getDescription());
            temp.setContent(blogDto.getContent());
            temp.setCreateUser(this.getUserName(blogDto.getUserId()));
            temp.setStatus(0);
            temp.setDelFlag("0");
            temp.setCreateDate(LocalDateTime.now());
        }else {
            temp.setTitle(blogDto.getTitle());
            temp.setUserId(blogDto.getUserId());
            temp.setDescription(blogDto.getDescription());
            temp.setContent(blogDto.getContent());
            temp.setUpdateUser(this.getUserName(blogDto.getUserId()));
            temp.setStatus(1);
            temp.setUpdateDate(LocalDateTime.now());
        }
        //将blog的值赋给temp 忽略 id userid created status 引用于hutool
        BeanUtil.copyProperties(blogDto,temp,"id","userId","createDate","status");
        boolean flag = save(temp);
        return flag;
    }



    /**
     * 根据UserId获取userName
     * @param userId
     * @return userName
     */
    private String  getUserName(String userId){
        User user = userService.getById(userId);
        String userName = user.getUsername();
        return userName;
    }
}
