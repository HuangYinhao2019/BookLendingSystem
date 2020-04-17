package com.myvue.springboottest.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myvue.springboottest.entity.LendList;
import com.myvue.springboottest.pojo.BorrowRequestPOJO;
import com.myvue.springboottest.pojo.LendInfoPOJO;
import com.myvue.springboottest.service.LendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author liusandao
 * @description LendListController
 * @date 2020-4-15 0:15
 */

@RestController
@RequestMapping("/lendlist")
public class LendListController {

    @Autowired
    private LendListService lendListService;

    @GetMapping("findAll")
    public List<LendList> findAll(){
        return lendListService.findAll();
    }

    @GetMapping("findByAccountId/{accountid}")
    public List<LendList> findByAccountId(@PathVariable("accountid") Integer account_id){
        return lendListService.findByAccountId(account_id);
    }

    @GetMapping("findByBookId/{bookid}")
    public List<LendList> findByBookId(@PathVariable("bookid") Integer bookid){
        return lendListService.findByBookId(bookid);
    }

    @GetMapping("findByAccountIdRended/{accountid}")
    public List<LendList> findByAccountIdRended(@PathVariable("accountid") Integer account_id){
        return lendListService.findByAccountIdRended(account_id);
    }

    @GetMapping("findByAccountIdHistory/{accountid}")
    public List<LendList> findByAccountIdHistory(@PathVariable("accountid") Integer account_id){
        return lendListService.findByAccountIdHistory(account_id);
    }

    @PostMapping("retrieval")
    public List<LendList> retrieval(@RequestBody LendInfoPOJO lendInfoPOJO){
        List<LendList> retrieval = lendListService.retrieval(lendInfoPOJO.getUser_name(), lendInfoPOJO.getBook_name(), lendInfoPOJO.getAccount_id());
        return retrieval;
    }

    @PostMapping("borrow")
    public String borrow(@RequestBody BorrowRequestPOJO borrowRequestPOJO){
        String result = lendListService.borrow(borrowRequestPOJO.getBook_id(), borrowRequestPOJO.getAccount_id());
        System.out.println(result);
        return result;
    }

    @GetMapping("returnBook/{lend_id}")
    public String returnBook(@PathVariable("lend_id") Integer lend_id){
        return lendListService.returnbook(lend_id);
    }


}
