package com.hls.Feign;

import com.hls.Feign.UserFeignClient;
import com.hls.User;
import com.hls.common.Result;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFeignClientFallBack implements UserFeignClient {
    @Override
    public Result<List<User>> getAll() {
        System.out.println("null");
        return Result.Fail(null);
    }

    @Override
    public Result<User> findByUserName(String username) {
        return Result.Fail(null);
    }

    @Override
    public Result<User> findByUserId(String id) {
        return Result.Fail(null);
    }

    @Override
    public Result<String> delete(String username) {
        return Result.Fail(null);
    }

    @Override
    public Result<String> update(User user) {
        return Result.Fail(null);
    }

    @Override
    public Result<String> add(String username) {
        return Result.Fail(null);
    }
}
