package com.geekaca.news.service.impl;

import com.geekaca.news.domain.Link;
import com.geekaca.news.mapper.LinkMapper;
import com.geekaca.news.service.LinkService;
import com.geekaca.news.utils.PageQueryUtil;
import com.geekaca.news.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkMapper linkMapper;

    @Override
    public int getTotalLinks() {
        return linkMapper.getTotalLinks(null);
    }

    @Override
    public PageResult getBlogLinkPage(PageQueryUtil pageUtil) {
        List<Link> links = linkMapper.findLinkList(pageUtil);
        int total = linkMapper.getTotalLinks(pageUtil);
        PageResult pageResult = new PageResult(links, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean saveLink(Link link) {
        return linkMapper.insertSelective(link) > 0;
    }

    @Override
    public Link selectById(Integer id) {
        return linkMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean updateLink(Link tempLink) {
        return linkMapper.updateByPrimaryKeySelective(tempLink) > 0;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return linkMapper.deleteBatch(ids) > 0;
    }
}
