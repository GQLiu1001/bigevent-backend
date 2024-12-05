package com.example.controller;

import com.example.pojo.Category;
import com.example.pojo.Result;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public Result getCategory() {
        List<Category> data = categoryService.getCategory();
        System.out.println("data = " + data);
        return Result.success(data);
    }
    @PostMapping("")
    public Result addCategory( @RequestBody Category category) {
        categoryService.addCategory(category);
        return Result.success();
    }
    @PutMapping("")
    public  Result updateCategory(@RequestBody Category category){
        categoryService.updateCategory(category);
        return Result.success();
    }
    @GetMapping("/detail")
    public Result getCategoryDetail(@RequestParam Integer id) {
        Category detail = categoryService.getCategoryDetail(id);
        return Result.success(detail);
    }
    @DeleteMapping("")
    public Result deleteCategory( @RequestParam Integer id) {
        categoryService.deleteCategory(id);
        return Result.success();
    }
    @GetMapping("/deleteall")
    public Result deleteAll() {
        categoryService.deleteAll();
        return Result.success();
    }

}
