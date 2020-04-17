package com.myvue.springboottest.repository;

import com.myvue.springboottest.entity.LendList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author liusandao
 * @description LendListRepository
 * @date 2020-4-14 23:39
 */

@Repository
@Mapper
public interface LendListRepository {

    List<LendList> findAll();
    List<LendList> findByAccountId(Integer account_id);
    List<LendList> findByAccountIdRended(Integer account_id);
    List<LendList> findByAccountIdHistory(Integer account_id);
    List<LendList> findByBookId(Integer book_id);
    List<LendList> findByUserName(String user_name);
    int save(LendList lendList);
    LendList findByLendIdForUpdate(Integer lend_id);
    int returnBook(Integer lend_id, Date returnDate);

    List<LendList> retrieval(@Param("where") String where);


}
