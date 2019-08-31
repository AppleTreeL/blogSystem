package com.bit.blog.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Classname Article
 * @Description TODO
 * @Date 2019/8/23 15:08
 * @Created by AppleTree
 */

@Getter
@Setter
@ToString
public class Article {

    private Integer id;
    private String title;
    private String content;
    private Integer userId;

    private String userAccout;

    private Date createTime;
}
