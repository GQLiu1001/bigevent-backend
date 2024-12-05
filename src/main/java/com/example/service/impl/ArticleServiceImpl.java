package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pojo.Article;
import com.example.pojo.PageBean;
import com.example.service.ArticleService;
import com.example.mapper.ArticleMapper;
import com.example.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 11965
 * @description 针对表【article】的数据库操作Service实现
 * @createDate 2024-11-30 18:14:11
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setId(id);
        articleMapper.addArticle(article);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleMapper.deleteById(id);
    }

    @Override
    public Article getDetailById(Integer id) {
        Article article = articleMapper.getDetailById(id);
        return article;
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateArticle(article);
    }

    /*@Override
    public PageBean<Article> findList(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pageBean = new PageBean<>();
        //开启分页查询 PageHelper pageNum和pageSize会自动拼接到sql后面
        PageHelper.startPage(pageNum, pageSize);
        //调用ThreadLocal获得当前登录用户的id
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        //调用mapper 这个mapper返回的是最里层的Article数据 外层该有个items 和 total
        List<Article> list = articleMapper.findList(userId,categoryId, state);
        //Page中提供了方法，可以获取PageHelper分页查询后的
        // 总记录条数 对应返回结果的total getTotal
        // 和当前页数据 对应返回结果的items getResult
        //进行强转！！！
        Page<Article> page = (Page<Article>) list;
        System.out.println(page);
        //把数据填充到PageBean对象中
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());
        return  pageBean;
    }*/
    @Override
    public PageBean<Article> findList(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pageBean = new PageBean<>();
        // 开启分页查询
        PageHelper.startPage(pageNum, pageSize);
        // 调用 ThreadLocal 获得当前登录用户的 id
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        // 调用 mapper 查询数据
        List<Article> list = articleMapper.findList(userId, categoryId, state);

        // 获取分页信息 PageInfo<>:pageNum pageSize size startRow endRow total pages list(mapper传来的泛型接收)
        PageInfo<Article> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo);
        // 将数据填充到 PageBean 对象中
        pageBean.setTotal(pageInfo.getTotal());
        pageBean.setItems(pageInfo.getList());

        return pageBean;
    }

    @Override
    public void deleteAll() {
        articleMapper.deleteAll();

    }
}




