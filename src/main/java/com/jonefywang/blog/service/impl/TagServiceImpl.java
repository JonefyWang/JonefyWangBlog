package com.jonefywang.blog.service.impl;

import com.jonefywang.blog.entity.Tag;
import com.jonefywang.blog.mapper.TagMapper;
import com.jonefywang.blog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
