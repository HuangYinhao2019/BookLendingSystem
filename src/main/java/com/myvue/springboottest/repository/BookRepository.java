package com.myvue.springboottest.repository;

import com.myvue.springboottest.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liusandao
 * @description BookRepository
 * @date 2020-4-10 20:54
 */

@Mapper
@Repository
public interface BookRepository{

    int save(Book book);
    List<Book> findAll();
    Book findById(Integer id);
    int deleteById(Integer id);
    int update(Book book);
    Book findByIdForUpdate(Integer id);
    int bookBorrowedByBookId(Integer id);
    int bookReturnedByBookId(Integer id);

}
