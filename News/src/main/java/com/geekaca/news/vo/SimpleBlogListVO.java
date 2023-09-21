package com.geekaca.news.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SimpleBlogListVO implements Serializable {

    private Long newsId;

    private String newsTitle;


}
