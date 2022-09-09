package com.jonefywang.blog.controller;



import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jonefywang.blog.common.lang.Result;
import com.jonefywang.blog.entity.Blog;
import com.jonefywang.blog.utils.SnowFlake;
import com.jonefywang.blog.service.BlogService;
import com.jonefywang.blog.utils.ShrioUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JonefyWAng
 * @since 2022-08-23
 */
@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/listBlogs")
    public Result listBlogs(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage,5);
        IPage pageDate = blogService.page(page,new QueryWrapper<Blog>().orderByDesc("created"));

        return Result.success(pageDate);
    }
    @GetMapping("/detilBlog/{id}")
    public Result detilBlog(@PathVariable(name = "id") String id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"博客已删除");
        return Result.success(blog);
    }

    @RequiresAuthentication
    @PostMapping("/editBlogs")
    public Result editBlogs(@Validated @RequestBody Blog blog){
        Blog currentblog = null;
        if (StringUtils.isNotEmpty(blog.getId())){
            currentblog = blogService.getById(blog.getId());
            //判断当前修改用户
            Assert.isTrue(currentblog.getUserId() == ShrioUtils.getProfile().getId(),"没有权限修改该文章！");
        }else{

            currentblog = new Blog();
            SnowFlake snowFlake = null;
            currentblog.setId(String.valueOf(snowFlake.nextId()));
            currentblog.setUserId(ShrioUtils.getProfile().getId());
            currentblog.setCreated(LocalDateTime.now());
            currentblog.setStatus(0);
        }
        BeanUtils.copyProperties(blog,currentblog,"id","userId","created","status");
        blogService.saveOrUpdate(currentblog);

        return Result.success(null);
    }
}
