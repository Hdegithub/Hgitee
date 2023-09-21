package com.geekaca.news.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BlogListVO implements Serializable {

    private Long newsId;

    private String newsTitle;

    private String newsSubUrl;

    private String newsCoverImage;

    private Integer newsCategoryId;

    private String newsCategoryIcon;

    private String newsCategoryName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;


}
