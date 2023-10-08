package com.geekaca.news.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName tb_news_tag_relation
 */
@Data
public class NewsTagRelation implements Serializable {
    /**
     * 关系表id
     */
    private Long relationId;

    /**
     * 新闻id
     */
    private Long newsId;

    /**
     * 标签id
     */
    private Integer tagId;

    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}