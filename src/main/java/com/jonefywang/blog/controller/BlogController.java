package com.jonefywang.blog.controller;



import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jonefywang.blog.common.dto.BlogDto;
import com.jonefywang.blog.common.lang.Result;
import com.jonefywang.blog.entity.Blog;
import com.jonefywang.blog.service.BlogTagService;
import com.jonefywang.blog.service.TagService;
import com.jonefywang.blog.service.UserService;
import com.jonefywang.blog.service.BlogService;
import com.jonefywang.blog.utils.ShrioUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;




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

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("/listBlogs")
    public Result listBlogs(@RequestParam Integer currentPage){
        IPage pageDate = blogService.listBlogs(currentPage);

        return Result.success(pageDate);
    }
    @GetMapping("/detilBlog/{id}")
    public Result detilBlog(@PathVariable(name = "id") String blogId){
        Blog blog = blogService.getById(blogId);
        Assert.isTrue(blog.getDelFlag().equals("1"), "该博客已删除");
        return Result.success(blog);
    }

    @RequiresAuthentication
    @PostMapping("/editBlogs")
    public Result editBlogs(@Validated @RequestBody BlogDto blogDto){
        Blog currentblog = null;
        if (StringUtils.isNotEmpty(blogDto.getBlogId())){
            currentblog = blogService.getById(blogDto.getBlogId());
            //判断当前修改用户
            Assert.isTrue(currentblog.getUserId().equals(ShrioUtils.getProfile().getId()),"没有权限修改该文章！");
        }else {
            blogService.saveOrUpdateBlog(blogDto);
            blogTagService.saveTagBlog(blogDto);
        }
        return Result.success(null);
    }

    @RequiresAuthentication
    @PostMapping("/deleteBlogs")
    public Result deleteBlogs(@PathVariable(name = "id") String blogId){
        if (StringUtils.isNotEmpty(blogId)){
            Blog currentblog = blogService.getById(blogId);
            //判断当前删除操作的用户
            Assert.isTrue(currentblog.getUserId().equals(ShrioUtils.getProfile().getId()),"没有权限删除该文章！");
        }else{
            blogService.removeById(blogId);
            blogTagService.removeById(blogId);
        }
        return Result.success(null);
    }
}
