package com.myvue.springboottest.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author liusandao
 * @description Book
 * @date 2020-4-10 20:52
 */

@Entity
@Data
@Table(name = "book")
public class Book {

    @Id
    private Integer book_id;
    private String name;
    private String author;
    private String isbn;
    private String introduction;
    private String language;
    private Date pubdate;
    private Integer left_number;
    private Integer class_id;
    private Integer is_on;
    @OneToOne
    private Category category;

}
