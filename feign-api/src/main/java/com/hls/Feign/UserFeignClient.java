package com.hls.Feign;

import com.hls.User;
import com.hls.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "UserService" ,fallback = UserFeignClientFallBack.class)
public interface UserFeignClient {
    @GetMapping("/user/getAll")
    public Result<List<User>> getAll();

    @GetMapping("/user/findByUserName/{username}")
    public Result<User> findByUserName(@PathVariable String username);

    @GetMapping("/user/findByUserId/{id}")
    public Result<User> findByUserId(@PathVariable String id);

    @DeleteMapping("/user/delete/{username}")
    public Result<String> delete(@PathVariable String username);

    @PutMapping("/user/update")
    public Result<String> update(User user);

    @PostMapping("/user/add")
    public Result<String> add(String username);


}
