package com.jonefywang.blog.service.impl;

import com.jonefywang.blog.entity.User;
import com.jonefywang.blog.mapper.UserMapper;
import com.jonefywang.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JonefyWAng
 * @since 2022-08-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
