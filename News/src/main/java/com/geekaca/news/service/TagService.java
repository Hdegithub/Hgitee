package com.geekaca.news.service;

import com.geekaca.news.domain.TagNewsCount;
import com.geekaca.news.utils.PageQueryUtil;
import com.geekaca.news.utils.PageResult;

import java.util.List;

public interface TagService {
    List<TagNewsCount> getAll();
    PageResult getBlogTagPage(PageQueryUtil pageUtil);
    int getTotalTags();
}
