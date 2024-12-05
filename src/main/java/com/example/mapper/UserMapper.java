package com.example.mapper;

import com.example.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 11965
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2024-11-30 18:14:01
* @Entity com.example.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findByUsername(String username);

    void register(String username, String password);

    void updateUser(User user);

    void updateAvatar(String avatarUrl,Integer id);

    void updatePwd(String newPwd, Integer id);

    void deleteAll();

    void updata(String username, String newPass);
}




