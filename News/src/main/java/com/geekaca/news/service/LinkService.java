package com.geekaca.news.service;

import com.geekaca.news.domain.Link;
import com.geekaca.news.utils.PageQueryUtil;
import com.geekaca.news.utils.PageResult;

import java.util.List;
import java.util.Map;


public interface LinkService {
    int getTotalLinks();

    PageResult getBlogLinkPage(PageQueryUtil pageUtil);

    Boolean saveLink(Link link);

    Link selectById(Integer id);

    Boolean updateLink(Link tempLink);

    Boolean deleteBatch(Integer[] ids);

    Map<Byte, List<Link>> getLinksForLinkPage();

}
