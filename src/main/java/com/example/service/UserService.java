package com.example.service;

import com.example.pojo.Result;
import com.example.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author 11965
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-11-30 18:14:01
*/
public interface UserService extends IService<User> {


        User findByUsername(String username);

        void register(String username, String password);

        void updateUser(User user);

        void updateAvatar(String avatarUrl);

        Result updatePwd(Map<String, String> map);

        void deleteAll();

        Result updata(Map<String, String> map);
}
