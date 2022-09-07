package com.jonefywang.blog.utils;

import com.jonefywang.blog.shrio.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangyuan
 * @Date: 2022/09/07/17:14
 * @Description: ShrioUtils 用户权限控制
 */
public class ShrioUtils {

    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
