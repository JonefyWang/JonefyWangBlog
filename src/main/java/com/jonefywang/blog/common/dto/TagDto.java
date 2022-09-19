package com.jonefywang.blog.common.dto;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangyuan
 * @Date: 2022/09/19/10:00
 * @Description:
 */
@Data
public class TagDto {

    private String id;

    private String userId;

    private List<String> tagName;

    private String createUser;
}
