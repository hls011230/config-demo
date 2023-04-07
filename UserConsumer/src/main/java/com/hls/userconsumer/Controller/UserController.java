package com.hls.userconsumer.Controller;

import com.hls.User;
import com.hls.Feign.UserFeignClient;
import com.hls.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserFeignClient userFeignClient;

    @RequestMapping("/getAll")
    public Result<List<User>> getAll(){
        return userFeignClient.getAll();
    }

    @GetMapping("/user/findByUserName/{username}")
    public Result<User> findByUserName(@PathVariable String username){
        return userFeignClient.findByUserName(username);
    }

    @GetMapping("/user/findByUserId/{id}")
    public Result<User> findByUserId(@PathVariable String id){
        return userFeignClient.findByUserId(id);
    }

    @DeleteMapping("/user/delete/{username}")
    public Result<String> delete(@PathVariable String username){
        return userFeignClient.delete(username);
    }

    @PutMapping("/user/update")
    public Result<String> update(String username, String password){
        User user = new User(null,username,password,"","");
        return userFeignClient.update(user);
    }

    @PostMapping("/user/add")
    public Result<String> add(String username){
        return userFeignClient.add(username);
    }
}
