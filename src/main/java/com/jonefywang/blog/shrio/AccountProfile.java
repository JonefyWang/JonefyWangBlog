package com.jonefywang.blog.shrio;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName AccountProfilr
 * @Description TODO
 * @Author 19285
 * @Date 2022/8/25 23:34
 * @Version 1.0
 */
@Data
public class AccountProfile implements Serializable {
    private String id;

    private String username;

    private String avatar;

    private String email;
}
