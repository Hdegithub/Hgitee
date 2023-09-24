package com.geekaca.news.service.impl;

import com.geekaca.news.domain.NewsCategory;
import com.geekaca.news.mapper.NewsCategoryMapper;
import com.geekaca.news.service.CategoryService;
import com.geekaca.news.utils.PageQueryUtil;
import com.geekaca.news.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private NewsCategoryMapper categoryMapper;
    @Override
    public List<NewsCategory> getAllCategories() {
        return categoryMapper.findAll();
    }

    @Override
    public int getTotalCategories() {
        return categoryMapper.getTotalCategories(null);
    }

    @Override
    public PageResult getNewsCategoryPage(PageQueryUtil pageUtil) {
        List<NewsCategory> categoryList = categoryMapper.findCategoryList(pageUtil);
        int total = categoryMapper.getTotalCategories(pageUtil);
        PageResult pageResult = new PageResult(categoryList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
