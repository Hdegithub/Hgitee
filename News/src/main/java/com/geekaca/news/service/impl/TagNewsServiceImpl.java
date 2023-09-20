package com.geekaca.news.service.impl;

import com.geekaca.news.domain.NewsTag;
import com.geekaca.news.domain.TagNewsCount;
import com.geekaca.news.mapper.NewsTagMapper;
import com.geekaca.news.service.TagService;
import com.geekaca.news.utils.PageQueryUtil;
import com.geekaca.news.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagNewsServiceImpl implements TagService {
    @Autowired
    private NewsTagMapper tagMapper;

    @Override
    public List<TagNewsCount> getAll() {
        return tagMapper.selectTagNewsCounts();
    }

    @Override
    public PageResult getBlogTagPage(PageQueryUtil pageUtil) {
        List<NewsTag> tags = tagMapper.findTagList(pageUtil);
        int total = tagMapper.getTotalTags(pageUtil);
        PageResult pageResult = new PageResult(tags, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
