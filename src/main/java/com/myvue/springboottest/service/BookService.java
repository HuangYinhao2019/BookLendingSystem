package com.myvue.springboottest.service;

import com.myvue.springboottest.entity.Book;
import com.myvue.springboottest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liusandao
 * @description BookService
 * @date 2020-4-12 20:26
 */

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public int save(Book book){
        return bookRepository.save(book);
    }
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    public Book findById(Integer id){
        return bookRepository.findById(id);
    }
    public int deleteById(Integer id){
        return bookRepository.deleteById(id);
    }
    public int update(Book book){
        return bookRepository.update(book);
    }


}
