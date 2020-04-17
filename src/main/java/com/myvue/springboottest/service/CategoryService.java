package com.myvue.springboottest.service;

import com.myvue.springboottest.entity.Category;
import com.myvue.springboottest.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liusandao
 * @description CategoryService
 * @date 2020-4-14 13:25
 */

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

}
