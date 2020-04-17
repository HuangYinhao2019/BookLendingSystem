package com.myvue.springboottest.controller;

import com.github.pagehelper.PageInfo;
import com.myvue.springboottest.entity.Category;
import com.myvue.springboottest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liusandao
 * @description CategoryController
 * @date 2020-4-14 13:27
 */

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/findAll")
    public List<Category> findAll(){
        return categoryService.findAll();
    }

}
