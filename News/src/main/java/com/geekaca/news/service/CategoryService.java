package com.geekaca.news.service;

import com.geekaca.news.domain.NewsCategory;
import com.geekaca.news.utils.PageQueryUtil;
import com.geekaca.news.utils.PageResult;

import java.util.List;

public interface CategoryService {
    List<NewsCategory>getAllCategories();

    int getTotalCategories();


    /**
     * 查询分类的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getNewsCategoryPage(PageQueryUtil pageUtil);
}
