package com.jonefywang.blog.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jonefywang.blog.entity.Tag;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangyuan
 * @Date: 2022/09/16/11:27
 * @Description:
 */
@Data
public class BlogDto {

    private String blogId;

    private String userId;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "描述不能为空")
    private String description;

    @NotBlank(message = "内容不能为空")
    private String content;

    private Integer status;

    private String createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    private String updateUser;

    private LocalDateTime updateDate;

    private boolean delFlag;

    private List<TagDto> tags;
}
