package com.jonefywang.blog.service.impl;

import com.jonefywang.blog.common.dto.BlogDto;
import com.jonefywang.blog.common.dto.TagDto;
import com.jonefywang.blog.entity.BlogTag;
import com.jonefywang.blog.entity.Tag;
import com.jonefywang.blog.entity.User;
import com.jonefywang.blog.mapper.BlogTagMapper;
import com.jonefywang.blog.service.BlogTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jonefywang.blog.service.UserService;
import com.jonefywang.blog.utils.SnowFlake;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JonefyWang
 * @since 2022-09-16
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    @Autowired
    private UserService userService;

    @Override
    public boolean saveTagBlog(BlogDto blogDto) {
        BlogTag blogTag = null;
        SnowFlake snowFlake = null;
        List<TagDto> tagDtos = blogDto.getTags();
        List<String> collect = this.list(null).stream().map(BlogTag::getTagId).collect(Collectors.toList());
        List<String> tagIds = new ArrayList<>();
        for (TagDto tagDto : tagDtos){
            tagIds.add(tagDto.getTagId());
        }
        List<String> filters = collect.stream().filter(tagIds::contains).collect(Collectors.toList());
        for (String filter : filters){
            blogTag.setId(String.valueOf(snowFlake.nextId()));
            blogTag.setBlogId(blogDto.getBlogId());
            blogTag.setTagId(filter);
            blogTag.setCreateUser(this.getUserName(blogDto.getUserId()));
            blogTag.setCreateDate(LocalDateTime.now());
        }
        boolean flag = save(blogTag);
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
