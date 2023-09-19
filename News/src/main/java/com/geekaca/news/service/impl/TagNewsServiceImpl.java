package com.geekaca.news.service.impl;

import com.geekaca.news.domain.TagNewsCount;
import com.geekaca.news.mapper.NewsTagMapper;
import com.geekaca.news.service.TagService;
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
}
