package com.geekaca.news.service.impl;

import com.geekaca.news.mapper.LinkMapper;
import com.geekaca.news.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkMapper linkMapper;

    @Override
    public int getTotalLinks() {
        return linkMapper.getTotalLinks(null);
    }
}
