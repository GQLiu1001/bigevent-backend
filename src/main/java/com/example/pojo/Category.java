package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * @TableName category
 */
//搞个DTO呢？
//分组校验！！！！  更新组 添加组
    //1.定义分组
    //2.定义校验时指定归属的分组
    //3.校验时指定要交校验的分组
    //如果说没指定分组  默认为Default分组
    //A extends B 则A中 拥有B所有的校验项
    //也就是 不涉及id的搞成default分组 同时 Add分组和Update分组都extends Default
@TableName(value ="category")
@Data
public class Category implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类别名
     */
    private String categoryAlias;

    /**
     * 创建人ID
     */
    private Integer createUser;

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