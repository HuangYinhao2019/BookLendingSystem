package com.myvue.springboottest.service;

import com.github.pagehelper.PageInfo;
import com.myvue.springboottest.entity.Account;
import com.myvue.springboottest.entity.Book;
import com.myvue.springboottest.entity.LendList;
import com.myvue.springboottest.repository.AccountRepository;
import com.myvue.springboottest.repository.BookRepository;
import com.myvue.springboottest.repository.LendListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liusandao
 * @description LendListService
 * @date 2020-4-15 0:12
 */

@Service
public class LendListService {

    @Autowired
    private LendListRepository lendListRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<LendList> findAll(){
        return lendListRepository.findAll();
    }

    public List<LendList> findByAccountId(Integer id){
        return lendListRepository.findByAccountId(id);
    }

    public List<LendList> findByAccountIdRended(Integer id){
        return lendListRepository.findByAccountIdRended(id);
    }

    public List<LendList> findByAccountIdHistory(Integer id){
        return lendListRepository.findByAccountIdHistory(id);
    }

    public List<LendList> findByBookId(Integer id){
        return lendListRepository.findByBookId(id);
    }

    public List<LendList> findByUserName(String user_name){
        return lendListRepository.findByUserName(user_name);
    }

    public List<LendList> retrieval(String user_name, String book_name, Integer account_id){
        String where = "";
        List<String> target = new ArrayList<>(3);
        if (user_name != null && user_name.length() > 0){
            target.add("a.user_name = '" + user_name + "'");
        }
        if (book_name != null && book_name.length() > 0){
            target.add("b.name like '%" + book_name + "%'");
        }
        if (account_id != null){
            target.add("a.account_id = " + String.valueOf(account_id));
        }
        if (target.size() == 0){
            return lendListRepository.findAll();
        }
        else {
            for (int i = 0; i < target.size(); i++) {
                where = where.concat(target.get(i));
                if (i != target.size() - 1) {
                    where = where.concat(" and ");
                }
            }

            System.out.println(where);
            return lendListRepository.retrieval(where);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public String borrow(Integer book_id, Integer account_id){

        System.out.println("查看用户状态");
        Account account = accountRepository.findByIdForUpdate(account_id);
        if (account.getLeft_lend_number() <= 0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "OutOfMaxRend";
        }

        List<LendList> byAccountIdRended = lendListRepository.findByAccountIdRended(account_id);
        for (int i = 0; i < byAccountIdRended.size(); i++) {
            if (byAccountIdRended.get(i).getBook_id().equals(book_id)){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return "AlreadyRendedThisOne";
            }
        }

        System.out.println("开始借书");
        Book book = bookRepository.findByIdForUpdate(book_id);
        if (book.getLeft_number() <= 0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "OutOfBookNumber";
        }

        System.out.println("用户可借书");
        accountRepository.borrowByAccountId(account_id);

        System.out.println("书本数量可借");
        bookRepository.bookBorrowedByBookId(book_id);

        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

        LendList lendList = new LendList();
        lendList.setAccount_id(account_id);
        lendList.setBook_id(book_id);
        lendList.setLend_date(currentDate);
        System.out.println(lendList);

        lendListRepository.save(lendList);
        System.out.println("借书记录已保存");

        return "success";
    }

    @Transactional
    public String returnbook(Integer lend_id){

        LendList lendList = lendListRepository.findByLendIdForUpdate(lend_id);

        Integer book_id = lendList.getBook_id();
        Integer account_id = lendList.getAccount_id();

        Account account = accountRepository.findByIdForUpdate(account_id);
        Book book = bookRepository.findByIdForUpdate(book_id);

        accountRepository.returnByAccountId(account_id);
        bookRepository.bookReturnedByBookId(book_id);

        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

        lendList.setBack_date(currentDate);
        lendListRepository.returnBook(lend_id,currentDate);

        return "success";

    }

}
