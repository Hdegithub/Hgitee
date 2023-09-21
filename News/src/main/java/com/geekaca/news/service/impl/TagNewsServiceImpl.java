package com.geekaca.news.service.impl;

import com.geekaca.news.domain.NewsTag;
import com.geekaca.news.domain.TagNewsCount;
import com.geekaca.news.mapper.NewsTagMapper;
import com.geekaca.news.mapper.NewsTagRelationMapper;
import com.geekaca.news.service.TagService;
import com.geekaca.news.utils.PageQueryUtil;
import com.geekaca.news.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class TagNewsServiceImpl implements TagService {
    @Autowired
    private NewsTagMapper tagMapper;
    @Autowired
    private NewsTagRelationMapper relationMapper;

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

    @Override
    public int getTotalTags() {
        return tagMapper.getTotalTags(null);
    }

    @Override
    public Boolean saveTag(String tagName) {
        NewsTag temp = tagMapper.selectByTagName(tagName);
        if (temp == null) {
            NewsTag blogTag = new NewsTag();
            blogTag.setTagName(tagName);
            return tagMapper.insertSelective(blogTag) > 0;
        }
        return false;
    }

    @Override
    public boolean deleteBatch(Integer[] ids) {
        //已存在关联关系不删除
        List<Long> relations = relationMapper.selectDistinctTagIds(ids);
        if (!CollectionUtils.isEmpty(relations)) {
            return false;
        }
        //删除tag
        return tagMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<TagNewsCount> getBlogTagCountForIndex() {
        return tagMapper.getTagCount();
    }
}
