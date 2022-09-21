package com.jonefywang.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jonefywang.blog.common.dto.BlogDto;
import com.jonefywang.blog.common.dto.TagDto;
import com.jonefywang.blog.entity.Blog;
import com.jonefywang.blog.entity.Tag;
import com.jonefywang.blog.entity.User;
import com.jonefywang.blog.mapper.TagMapper;
import com.jonefywang.blog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jonefywang.blog.service.UserService;
import com.jonefywang.blog.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private UserService userService;

    @Override
    public boolean saveOrUpdateTag(TagDto tagDto) {
        Tag temp = null;
        SnowFlake snowFlake = null;
        if (StringUtils.isEmpty(tagDto.getTagId())){
            //如果id为空
            temp =  new Tag();

            temp.setId(String.valueOf(snowFlake.nextId()));
            temp.setTagName(tagDto.getTagName());
            temp.setUserId(tagDto.getUserId());
            temp.setCreateUser(this.getUserName(tagDto.getUserId()));
            temp.setCreateDate(LocalDateTime.now());

        } else {
            Tag tag = this.getById(tagDto.getTagId());
            String tagName = tagDto.getTagName();
            if (tag.getTagName().equals(tagName)){

            } else {
                    temp.setTagName(tagName);
                    temp.setUpdateUser(this.getUserName(tagDto.getUserId()));
                    temp.setUpdateDate(LocalDateTime.now());
            }
        }
        BeanUtil.copyProperties(tagDto,temp,"id","userId");
        boolean flag = save(temp);
        return flag;
    }



    /**
     * 根据UserId获取userName
     * @param userId
     * @return userName
     */
    private String getUserName(String userId){
        User user = userService.getById(userId);
        String userName = user.getUsername();
        return userName;
    }
}
