package com.example.service;

import com.example.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.PageBean;

/**
* @author 11965
* @description 针对表【article】的数据库操作Service
* @createDate 2024-11-30 18:14:11
*/
public interface ArticleService extends IService<Article> {

    void addArticle(Article article);

    void deleteArticle(Integer id);

    Article getDetailById(Integer id);

    void updateArticle(Article article);

    PageBean<Article> findList(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    void deleteAll();
}
