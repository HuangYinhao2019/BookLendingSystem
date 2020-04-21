package com.myvue.springboottest.service;

import com.myvue.springboottest.entity.Book;
import com.myvue.springboottest.repository.BookRepository;
import com.myvue.springboottest.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        Book book = bookRepository.findById(id);
        return book;
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

    public List<Book> retrieval(String book_name, String author, Integer class_id){
        String where = "";
        List<String> target = new ArrayList<>(3);
        if (book_name != null && book_name.length() > 0){
            target.add("b.name like '%" + book_name + "%'");
        }
        if (author != null && author.length() > 0){
            target.add("b.author = '" + author + "'");
        }
        if (class_id != null){
            target.add("c.class_id = " + String.valueOf(class_id));
        }
        if (target.size() == 0){
            return bookRepository.findAll();
        }
        else {
            for (int i = 0; i < target.size(); i++) {
                where = where.concat(target.get(i));
                if (i != target.size() - 1) {
                    where = where.concat(" and ");
                }
            }
            if (redisUtil.hasKey(where)){
                List<Object> objects = redisUtil.rangeList(where, 0, -1);
                System.out.println("从缓存中取");
                return (List<Book>)objects.get(0);
            }
            else {
                System.out.println(where);
                List<Book> bookList = bookRepository.retrieval(where);
                System.out.println("存入缓存");
                redisUtil.lSet(where,bookList,3600);
                return bookList;
            }
        }

    }


}
