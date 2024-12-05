package com.example.controller;

import com.example.pojo.Article;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.ArticleService;
import com.example.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

/*    @GetMapping("/list")
    public Result<String> list(){
        return Result.success("文章列表");
    }*/

    @PostMapping("")
    public  Result addArticle(@RequestBody Article article){
        articleService.addArticle(article);
        return Result.success("添加文章成功");
    }

    @DeleteMapping("")
    public  Result deleteArticle(@RequestParam Integer id){
        articleService.deleteArticle(id);
        return Result.success("删除文章成功");
    }
    @GetMapping("/detail")
    public  Result<Article> detail(@RequestParam Integer id){
        Article article = articleService.getDetailById(id);
        return Result.success(article);
    }
    @PutMapping("")
    public  Result updateArticle(@RequestBody Article article){
        articleService.updateArticle(article);
        return Result.success("更新文章成功");
    }

    @GetMapping("")
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam (required = false)Integer categoryId,
            @RequestParam (required = false)String state
    ){
        PageBean<Article>  pageBean= articleService.findList(pageNum, pageSize, categoryId, state);
            return Result.success(pageBean);
    }

    @GetMapping("/deleteall")
    public Result deleteAll(){
        articleService.deleteAll();
        return Result.success("删除所有文章成功");
    }



}
