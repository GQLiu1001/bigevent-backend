package com.example.mapper;

import com.example.pojo.Article;
import com.example.pojo.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
* @author 11965
* @description 针对表【category】的数据库操作Mapper
* @createDate 2024-11-30 18:14:08
* @Entity com.example.pojo.Category
*/
public interface CategoryMapper extends BaseMapper<Category> {

    void addCategory(String categoryName, String categoryAlias , Integer id);


    List<Category> getCategory();

    void updateCategory(Category category);

    Category getCategoryDetail(Integer id);

    void deleteCategory(Integer id);

    void deleteAll();
}




