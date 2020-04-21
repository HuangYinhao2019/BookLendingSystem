package com.myvue.springboottest.pojo;/**
 * @description BookRetrievalPOJO
 * @author liusandao
 * @date 2020-4-21 19:00
 */

import lombok.Data;

/**
 * @program: springboottest
 * @description:
 * @author: liusandao
 * @date 2020-04-21 19:00
 */

@Data
public class BookRetrievalPOJO {

    private String book_name;
    private String author;
    private Integer class_id;

}
