package com.myvue.springboottest.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myvue.springboottest.entity.Account;
import com.myvue.springboottest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liusandao
 * @description AccountController
 * @date 2020-4-12 23:05
 */

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/findAll/{page}/{size}")
    public PageInfo findAll(@PathVariable("page") Integer currentpage, @PathVariable("size") Integer size){
        PageInfo pageInfo = PageHelper.startPage(currentpage, size).doSelectPageInfo(() -> accountService.findAll());
        return pageInfo;
    }

    @PostMapping("/register")
    public String register(@RequestBody Account account){
        return accountService.register(account);
    }

    @PostMapping("/save")
    public String save(@RequestBody Account account){
        return accountService.register(account);
    }

    @GetMapping("/findById/{account_id}")
    public Account findById(@PathVariable Integer account_id){
        return accountService.findById(account_id);
    }

    @PutMapping("/update")
    public String update(@RequestBody Account account){
        return accountService.update(account);
    }

    @PostMapping("/submit")
    public String submit(@RequestBody Account account){
        return accountService.submit(account);
    }

    @GetMapping("/findByName/{account_name}")
    public Account findByName(@PathVariable String account_name){
//        System.out.println(accountService.findByName(account_name));
        return accountService.findByName(account_name);
    }

}
