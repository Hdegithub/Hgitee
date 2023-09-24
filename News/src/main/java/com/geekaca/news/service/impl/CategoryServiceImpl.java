package com.geekaca.news.service.impl;

import com.geekaca.news.domain.NewsCategory;
import com.geekaca.news.mapper.NewsCategoryMapper;
import com.geekaca.news.mapper.NewsMapper;
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
    @Autowired
    private NewsMapper newsMapper;
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

    @Override
    public Boolean saveCategory(String categoryName, String categoryIcon) {
        NewsCategory temp = categoryMapper.selectByCategoryName(categoryName);
        if (temp == null) {
            NewsCategory NewsCategory = new NewsCategory();
            NewsCategory.setCategoryName(categoryName);
            NewsCategory.setCategoryIcon(categoryIcon);
            return categoryMapper.insertSelective(NewsCategory) > 0;
        }
        return false;
    }

    @Override
    public Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
        NewsCategory NewsCategory = categoryMapper.selectByPrimaryKey(categoryId);
        if (NewsCategory != null) {
            NewsCategory.setCategoryIcon(categoryIcon);
            NewsCategory.setCategoryName(categoryName);
            //修改分类实体
            newsMapper.updateNewsCategorys(categoryName, NewsCategory.getCategoryId(), new Integer[]{categoryId});
            return categoryMapper.updateByPrimaryKeySelective(NewsCategory) > 0;
        }
        return false;
    }


    @Override
    public Boolean deleteCategory(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //修改tb_blog表
        newsMapper.updateNewsCategorys("默认分类", 0, ids);
        //删除分类数据
        return categoryMapper.deleteByIds(ids) > 0;
    }


}
