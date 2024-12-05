package com.example.service;

import com.example.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author 11965
* @description 针对表【category】的数据库操作Service
* @createDate 2024-11-30 18:14:08
*/
public interface CategoryService extends IService<Category> {

    void addCategory(Category category);

    List<Category> getCategory();

    void updateCategory(Category category);

    Category getCategoryDetail(Integer id);

    void deleteCategory(Integer id);

    void deleteAll();
}
