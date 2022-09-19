package com.jonefywang.blog.service;

import com.jonefywang.blog.common.dto.BlogDto;
import com.jonefywang.blog.common.dto.TagDto;
import com.jonefywang.blog.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JonefyWang
 * @since 2022-09-16
 */
public interface TagService extends IService<Tag> {



    boolean saveOrUpdateTag(TagDto tagDto);

}
