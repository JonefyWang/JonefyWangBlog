package com.jonefywang.blog.shrio;

import com.jonefywang.blog.entity.User;
import com.jonefywang.blog.service.UserService;
import com.jonefywang.blog.utils.JwtUtils;
import lombok.val;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;

/**
 * @ClassName AccountRealm
 * @Description TODO
 * @Author 19285
 * @Date 2022/8/24 23:07
 * @Version 1.0
 */
@Component
public class  AccountRealm extends AuthorizingRealm {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof JwtToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken) token;
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        User user= userService.getById(Long.valueOf(userId));
        /**
         * 用户账户判断
         */
        if (user == null){
            throw new UnknownAccountException("账户不存在！！");
        }

        if (user.getStatus() == -1){
            throw new LockedAccountException("账户被锁定！");
        }

        AccountProfile accountProfile = new AccountProfile();
        BeanUtils.copyProperties(user,accountProfile);
        return new SimpleAuthenticationInfo(accountProfile,jwtToken.getCredentials(),getName());
    }
}
