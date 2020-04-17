package com.myvue.springboottest.service;

import com.myvue.springboottest.entity.Account;
import com.myvue.springboottest.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String update(Account account){
        int result =  accountRepository.update(account);
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
