package com.example.mapper;

import com.example.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 11965
* @description 针对表【article】的数据库操作Mapper
* @createDate 2024-11-30 18:14:11
* @Entity com.example.pojo.Article
*/
public interface ArticleMapper extends BaseMapper<Article> {

    void addArticle(Article article);

    Article getDetailById(Integer id);

    void updateArticle(Article article);

    List<Article> findList(Integer userId, Integer categoryId, String state);

    void deleteAll();
}




