package com.myvue.springboottest.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myvue.springboottest.entity.Book;
import com.myvue.springboottest.pojo.BookRetrievalPOJO;
import com.myvue.springboottest.service.BookService;
import com.myvue.springboottest.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author liusandao
 * @description BookController
 * @date 2020-4-10 21:05
 */

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/findAll/{page}/{size}")
    public PageInfo findAll(@PathVariable("page") Integer currentpage, @PathVariable("size") Integer size){
        String ps = "page" + String.valueOf(currentpage) + "size" + String.valueOf(size);
        PageInfo pageInfo;
        if (redisUtil.hasKey(ps)){
            pageInfo = (PageInfo)redisUtil.get(ps);
        }
        else {
            pageInfo = PageHelper.startPage(currentpage, size).doSelectPageInfo(() -> bookService.findAll());
            redisUtil.set(ps,pageInfo,3600);
        }
        return pageInfo;
    }

    @GetMapping("/findById/{book_id}")
    public Book findById(@PathVariable("book_id") Integer id){
        return bookService.findById(id);
    }

    @PostMapping("/save")
    public String save(@RequestBody Book book){
        int result = bookService.save(book);
        if(result != 0){
            return "success";
        }else{
            return "error";
        }
    }

    @PutMapping("/update")
    public String update(@RequestBody Book book){
        int result = bookService.update(book);
        if(result != 0){
            return "success";
        }else{
            return "error";
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        bookService.deleteById(id);
    }

    @PostMapping("/retrieval")
    public List<Book> retrieval(@RequestBody BookRetrievalPOJO bookRetrievalPOJO){
        System.out.println(bookRetrievalPOJO);
        String book_name = bookRetrievalPOJO.getBook_name();
        String author = bookRetrievalPOJO.getAuthor();
        Integer class_id = bookRetrievalPOJO.getClass_id();
        return bookService.retrieval(book_name,author,class_id);
    }

}
