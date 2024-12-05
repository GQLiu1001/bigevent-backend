package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pojo.Result;
import com.example.pojo.User;
import com.example.service.UserService;
import com.example.mapper.UserMapper;
import com.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author 11965
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-11-30 18:14:01
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        //加密

        //添加
        userMapper.register(username, password);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id =(Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public Result updatePwd(Map<String, String> map) {
        String oldPwd = map.get("old_pwd");
        System.out.println(oldPwd);
        String newPwd = map.get("new_pwd");
        System.out.println(newPwd);
        String rePwd = map.get("re_pwd");
        System.out.println(rePwd);
        Map<String, Object> map1 = ThreadLocalUtil.get();
//        map1.forEach((k,v)->System.out.println(k+"-->"+v));
        Integer id =(Integer) map1.get("id");
        System.out.println(id);
        String username = (String) map1.get("username");
        System.out.println(username);
        User user = userMapper.findByUsername(username);
        System.out.println("user:"+user);
        String pwd = user.getPassword();
        System.out.println("pwd:"+pwd);
        System.out.println("oldPwd:"+oldPwd);
        if (!pwd.equals(oldPwd)){
            return Result.failure("密码错误");
        }else if (!newPwd.equals(rePwd)){
            return Result.failure("两次密码不一致");
        }
        userMapper.updatePwd(newPwd,id);
        return Result.success();

    }

    @Override
    public void deleteAll() {
        userMapper.deleteAll();
    }

    @Override
    public Result updata(Map<String, String> map) {
        String username = map.get("username");
        String newPass = map.get("new_pwd");
        userMapper.updata(username,newPass);
        String s =username+newPass;
        return Result.success(s);
    }


}




