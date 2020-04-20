package com.myvue.springboottest.service;

import com.myvue.springboottest.entity.Account;
import com.myvue.springboottest.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liusandao
 * @description AccountService
 * @date 2020-4-12 23:04
 */

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public String register(Account account){
        if (accountRepository.findByName(account.getAccount_name()) != null){
            return "error";
        }
        else {
            accountRepository.save(account);
            return "success";
        }
    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Account findById(Integer id){
        return accountRepository.findById(id);
    }

    public Account findByName(String name){
        return accountRepository.findByName(name);
    }

    @Transactional
    public String update(Account account){
        Account account1 = accountRepository.findByIdForUpdate(account.getAccount_id());
        int max_lend_number_before = account1.getMax_lend_number();
        int left_lend_number_before = account1.getLeft_lend_number();
        int left_lend_number_now = account.getMax_lend_number() - max_lend_number_before + left_lend_number_before;
        System.out.println(left_lend_number_now);
        int result =  accountRepository.update(account);
        accountRepository.updateForLend(account.getAccount_id(),left_lend_number_now);
        if (result != 0){
            return "success";
        }else {
            return "error";
        }
    }

    public String submit(Account account){
        if (accountRepository.findByName(account.getAccount_name()) == null){
            return "noexist";
        }
        else {
            if (accountRepository.findByName(account.getAccount_name()).getPassword().equals(account.getPassword())){
                return "success";
            }
            else {
                return "wrongpassword";
            }
        }
    }

}
