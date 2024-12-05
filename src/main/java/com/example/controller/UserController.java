package com.example.controller;

import com.example.pojo.Result;
import com.example.pojo.User;
import com.example.service.UserService;
import com.example.utils.JwtUtils;
import com.example.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//Valid控制输入参数 类上@Validated 参数前@Pattern(regexp = "^[a-zA-Z0-9_-]{4,16}$"，message = "用户名长度为4-16位")
@RequestMapping("/user")
@RestController
@Validated
@CrossOrigin
public class UserController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    //注册
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^[a-zA-Z0-9_-]{4,16}$", message = "用户名长度为4-16位") String username,@Pattern(regexp = "^[a-zA-Z0-9_-]{6,16}$", message = "密码长度为6-16位") String password) {
        //用户名是否被占用
        User user = userService.findByUsername(username);
        if (user != null){
            return Result.failure("用户名已被占用");
        }
        //注册
        userService.register(username, password);
        return Result.success("注册成功");
    }
    @GetMapping("/deleteall")
    public Result deleteAll() {
        userService.deleteAll();
        return Result.success("删除成功");
    }
    @PostMapping("/login")
    public Result login( @Pattern(regexp = "^[a-zA-Z0-9_-]{4,16}$", message = "用户名长度为4-16位") String username, @Pattern(regexp = "^[a-zA-Z0-9_-]{6,16}$", message = "密码长度为6-16位")String password){
        User user = userService.findByUsername(username);
        if (user.getUsername() == null){
            return Result.failure("用户不存在或者密码错误");
        }else if (!user.getPassword().equals(password)){
            return Result.failure("用户不存在或者密码错误");
        }else{
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            String token = JwtUtils.createToken(claims);
            //把token存到redis中
            System.out.println("token:"+token);
            redisTemplate.opsForValue().set("token",token,20000, java.util.concurrent.TimeUnit.MINUTES);
//           ->拦截器
            return Result.success(token);
        }
    }

    @GetMapping("/userinfo")
//    public Result userinfo(@RequestHeader("token") String token){
    public Result userinfo(){
        //解析token
//        Map<String, Object> map = JwtUtils.parseToken(token);
//        String username =(String) map.get("username");
        //利用全局的ThreadLocal来减少操作量
        //从ThreadLocal获取数据
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        //findByUsername直接返回的时一个User对象
        User user = userService.findByUsername(username);
        System.out.println("user:"+user);
        if (user == null){
            return Result.failure();
        }
        return Result.success(user);
    }

    //设置nickname和email
    @PutMapping("/update")
    //数据是在请求体中以JSON格式传递的
    public Result update(@RequestBody @Validated User user){
        userService.updateUser(user);
        return Result.success();
    }

    //从QueryString中获取数据 @RequestParam
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> map ){
        //删除redis中对应的token
        redisTemplate.delete("token");
        Result result = userService.updatePwd(map);
        return result;

    }

    @PostMapping("/updata")
    public Result updata(@RequestBody Map<String,String> map){
        Result updata = userService.updata(map);
        return updata;
    }


}
