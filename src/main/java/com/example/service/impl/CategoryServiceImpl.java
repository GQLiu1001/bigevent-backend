package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pojo.Category;
import com.example.service.CategoryService;
import com.example.mapper.CategoryMapper;
import com.example.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author 11965
* @description 针对表【category】的数据库操作Service实现
* @createDate 2024-11-30 18:14:08
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public void addCategory(Category category) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id =(Integer) map.get("id");
        String categoryName = category.getCategoryName();
        String categoryAlias = category.getCategoryAlias();
        categoryMapper.addCategory(categoryName,categoryAlias,id);
    }

    @Override
    public List<Category> getCategory() {
        List<Category> list = categoryMapper.getCategory();
        return list;
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    @Override
    public Category getCategoryDetail(Integer id) {
        Category detail = categoryMapper.getCategoryDetail(id);

        return detail;
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryMapper.deleteCategory(id);
    }

    @Override
    public void deleteAll() {
        categoryMapper.deleteAll();
    }
}




