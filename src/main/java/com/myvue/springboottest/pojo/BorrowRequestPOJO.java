package com.myvue.springboottest.pojo;

import lombok.Data;

/**
 * @author liusandao
 * @description BorrowRequestPOJO
 * @date 2020-4-15 23:27
 */

@Data
public class BorrowRequestPOJO {

    private Integer book_id;
    private Integer account_id;

}
