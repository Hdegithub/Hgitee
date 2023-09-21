package com.geekaca.news.service;

import com.geekaca.news.domain.Link;
import com.geekaca.news.utils.PageQueryUtil;
import com.geekaca.news.utils.PageResult;

public interface LinkService {
    int getTotalLinks();

    PageResult getBlogLinkPage(PageQueryUtil pageUtil);

    Boolean saveLink(Link link);

    Link selectById(Integer id);

    Boolean updateLink(Link tempLink);

    Boolean deleteBatch(Integer[] ids);
}
