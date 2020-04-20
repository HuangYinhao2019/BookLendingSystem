package com.myvue.springboottest.service;

import com.myvue.springboottest.entity.Book;
import com.myvue.springboottest.repository.BookRepository;
import com.myvue.springboottest.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
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

    @Resource
    private RedisUtil redisUtil;

    public int save(Book book){
        return bookRepository.save(book);
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(Integer id){
        String key = "book_id " + String.valueOf(id);
        if (redisUtil.hasKey(key)){
            System.out.println("从缓存中获取");
            return (Book)redisUtil.get(key);
        }
        else {
            System.out.println("没有缓存");
            Book book = bookRepository.findById(id);
            if (book != null && book.getBook_id() != null) {
                redisUtil.set(key, book, 3600);
                System.out.println("记录进缓存");
            }
            return book;
        }
    }

    @Transactional
    public int deleteById(Integer id){
        bookRepository.findByIdForUpdate(id);
        return bookRepository.deleteById(id);
    }

    @Transactional
    public int update(Book book){
        bookRepository.findByIdForUpdate(book.getBook_id());
        return bookRepository.update(book);
    }

}
