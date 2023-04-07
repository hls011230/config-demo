package com.hls.userservice.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hls.userservice.Service.UserService;
import com.hls.userservice.mapper.UserMapper;
import com.hls.userservice.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
