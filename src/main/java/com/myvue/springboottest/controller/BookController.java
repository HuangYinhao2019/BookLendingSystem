package com.myvue.springboottest.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myvue.springboottest.entity.Book;
import com.myvue.springboottest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/findAll/{page}/{size}")
    public PageInfo findAll(@PathVariable("page") Integer currentpage, @PathVariable("size") Integer size){
        PageInfo pageInfo = PageHelper.startPage(currentpage, size).doSelectPageInfo(() -> bookService.findAll());
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



}
