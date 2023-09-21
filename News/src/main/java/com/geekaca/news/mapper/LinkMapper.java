package com.geekaca.news.mapper;

import com.geekaca.news.domain.Link;
import com.geekaca.news.utils.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LinkMapper {
    int getTotalLinks(PageQueryUtil pageUtil);

    List<Link> findLinkList(PageQueryUtil pageUtil);

    int insertSelective(Link record);

    Link selectByPrimaryKey(Integer linkId);

    int updateByPrimaryKeySelective(Link record);

    int deleteBatch(Integer[] ids);
}
