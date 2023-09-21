package com.geekaca.news.mapper;

import com.geekaca.news.utils.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LinkMapper {
    int getTotalLinks(PageQueryUtil pageUtil);
}
