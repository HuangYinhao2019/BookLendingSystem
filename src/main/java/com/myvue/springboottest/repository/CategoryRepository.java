package com.myvue.springboottest.repository;

import com.myvue.springboottest.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liusandao
 * @description CategoryRepository
 * @date 2020-4-14 13:23
 */

@Repository
@Mapper
public interface CategoryRepository {

    List<Category> findAll();

}
