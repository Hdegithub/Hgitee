package com.geekaca.news.utils;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class PageInfo extends LinkedHashMap<String, Object> {
    private int page;
    private int limit;
    public PageInfo(Map<String, Object> params) {
        this.putAll(params);

        //分页参数
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put("start", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
    }
}
