package com.geekaca.news.service;

import com.geekaca.news.domain.TagNewsCount;

import java.util.List;

public interface TagService {
    List<TagNewsCount> getAll();
}
