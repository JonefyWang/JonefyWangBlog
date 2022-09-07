package com.jonefywang.blog.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * @ClassName LoginDto
 * @Description TODO
 * @Author 19285
 * @Date 2022/8/26 0:19
 * @Version 1.0
 */
@Data
public class LoginDto {
    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
