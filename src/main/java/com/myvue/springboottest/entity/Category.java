package com.myvue.springboottest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author liusandao
 * @description Category
 * @date 2020-4-13 10:45
 */

@Entity
@Data
public class Category {

    @Id
    private Integer class_id;
    private String class_name;

}
