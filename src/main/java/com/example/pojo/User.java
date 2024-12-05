package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
//实体参数校验！！
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * ID
     */
    @NotNull
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    //让SpringMVC把当前对象转换成JSON字符串时忽略该属性 防止泄露
    @JsonIgnore
    private String password;

    /**
     * 昵称
     */
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")//1-10非空字符串
    private String nickname;

    /**
     * 邮箱
     */
    //如果想自定义@Pattern(regexp="^\\S{1,10}$")
    //不能为空
    @NotEmpty
    //邮箱格式
    @Email
    //想让这些注解生效需要@Validated
    private String email;

    /**
     * 头像
     */
    private String userPic;

    /**
     * 创建时间
     */
//    private Date createTime;
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
//    private Date updateTime;
    private LocalDateTime updateTime;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}