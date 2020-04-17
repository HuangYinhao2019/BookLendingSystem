package com.myvue.springboottest.repository;

import com.myvue.springboottest.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liusandao
 * @description AccountRepository
 * @date 2020-4-12 22:55
 */
@Repository
@Mapper
public interface AccountRepository {

    int save(Account account);
    List<Account> findAll();
    Account findById(Integer id);
    Account findByName(String name);
    int update(Account account);
    Account findByIdForUpdate(Integer id);
    int borrowByAccountId(Integer id);
    int returnByAccountId(Integer id);

}
