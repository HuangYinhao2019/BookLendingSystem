package com.myvue.springboottest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author liusandao
 * @description Account
 * @date 2020-4-12 22:44
 */

@Entity
@Data
public class Account {

    @Id
    private Integer account_id;
    private String account_name;
    private String password;
    private Integer is_admin;
    private String user_name;
    private Integer max_lend_number;
    private Integer left_lend_number;

}
