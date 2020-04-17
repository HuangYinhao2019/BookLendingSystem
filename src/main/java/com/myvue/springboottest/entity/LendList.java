package com.myvue.springboottest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Date;

/**
 * @author liusandao
 * @description LendList
 * @date 2020-4-13 10:58
 */

@Entity
@Data
public class LendList {

    @Id
    private Integer lend_id;
    private Integer book_id;
    @OneToOne
    private Book book;
    private Integer account_id;
    @OneToOne
    private Account account;
    private Date lend_date;
    private Date back_date;

}
