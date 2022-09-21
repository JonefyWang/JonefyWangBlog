package com.jonefywang.blog.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.jonefywang.blog.common.dto.BlogDto;
import com.jonefywang.blog.common.dto.TagDto;
import com.jonefywang.blog.common.lang.Result;
import com.jonefywang.blog.entity.Blog;
import com.jonefywang.blog.entity.Tag;
import com.jonefywang.blog.service.BlogService;
import com.jonefywang.blog.service.BlogTagService;
import com.jonefywang.blog.service.TagService;
import com.jonefywang.blog.utils.ShrioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JonefyWang
 * @since 2022-09-16
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogTagService blogTagService;

    /**
     * 获取所有的标签
     * @return
     */
    @GetMapping("/listTags")
    public Result listTags(){
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("DEL_FLAG","1");
        wrapper.orderByDesc("CREATE_DATE");
        List<Tag> list = tagService.list(wrapper);
        return Result.success(list);
    }

    @PostMapping("/edit")
    public Result editTags(@Validated @RequestBody TagDto tagDto){
        Tag currentTag = null;
        if (StringUtils.isNotEmpty(tagDto.getTagId())){
            currentTag = tagService.getById(tagDto.getTagId());
            //判断当前修改用户
            Assert.isTrue(currentTag.getUserId().equals(ShrioUtils.getProfile().getId()),"没有权限修改该文章！");
        }else {
           tagService.saveOrUpdateTag(tagDto);
        }
        return Result.success(currentTag);
    }

    @PostMapping("/delete")
    public Result deleteTags(@PathVariable(name = "id") String tagId){
        if (StringUtils.isNotEmpty(tagId)){
            Tag currentTag = tagService.getById(tagId);
            //判断当前删除操作的用户
            Assert.isTrue(currentTag.getUserId().equals(ShrioUtils.getProfile().getId()),"没有权限删除该标签！");
        } else {
            tagService.removeById(tagId);
            blogTagService.removeById(tagId);
        }
        return Result.success(null);
    }



}
