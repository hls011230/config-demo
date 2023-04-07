package com.hls.userservice.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hls.common.Result;
import com.hls.userservice.Service.UserService;
import com.hls.userservice.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public Result<String> Add(String username) {
        boolean save = userService.save(new User(null,username,"123456","123","student"));
        if (save){
            return Result.ok("success");
        }
        return Result.Fail("fail");
    }

    @DeleteMapping("/delete/{username}")
    public Result<String> Delete(@PathVariable String username) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        boolean remove = userService.remove(queryWrapper);

        if (remove){
            return Result.ok("success");
        }
        return Result.Fail("fail");
    }

    @PutMapping("/update")
    public Result<String> Update(String username,String password) {
        User user = new User();
        user.setPassword(password);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        boolean update = userService.update(user,queryWrapper);

        if (update){
            return Result.ok("success");
        }
        return Result.Fail("fail");
    }

    @GetMapping("/getAll")
    public Result<List<com.hls.User>> GetAll() {

        List<com.hls.User> users = new ArrayList<>();
        for (User user : userService.list()){
            com.hls.User u = new com.hls.User(user.getId(),user.getUsername(),user.getPassword(),user.getEmail(),user.getRole());
            users.add(u);
        }
        return Result.ok(users);
    }

    @GetMapping("/findByUserName/{username}")
    public Result<com.hls.User> FindByUserName(@PathVariable String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userService.getOne(queryWrapper);
        com.hls.User u = new com.hls.User(user.getId(),user.getUsername(),user.getPassword(),user.getEmail(),user.getRole());
        return Result.ok(u);
    }

    @GetMapping("/findByUserId/{id}")
    public com.hls.User FindByUserId(@PathVariable String id) {
        System.out.println(id);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        User user = userService.getOne(queryWrapper);
        System.out.println("查询到user："+user);
        com.hls.User u = new com.hls.User(user.getId(),user.getUsername(),user.getPassword(),user.getEmail(),user.getRole());
        return u;
    }

    @PostMapping("/login")
    public boolean login(String username,String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userService.getOne(queryWrapper);

        if(user.getPassword().equals(password)){
            return true;
        }

        return false;
    }

}
