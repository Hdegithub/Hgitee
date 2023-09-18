package com.geekaca.news.service.impl;

import com.geekaca.news.domain.NewsCategory;
import com.geekaca.news.mapper.NewsCategoryMapper;
import com.geekaca.news.service.CategoryService;
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
}
