package com.ang.mybatisplus.base.service.impl;

import com.ang.mybatisplus.base.entity.User;
import com.ang.mybatisplus.base.mapper.UserMapper;
import com.ang.mybatisplus.base.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ssang
 * @since 2021-05-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
