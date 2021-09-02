package com.lc.bo.response;


import lombok.Data;

import java.util.List;

/**
 * 分页响应对象
 */
@Data
public class PageTableResponse<T> {

    /**
     * 当前页数据
     */
    private List<T> list;

    /**
     * 总数
     */
    private long total;

    /**
     * 总页数
     */
    private long totalPage;

    /**
     * 当前页数
     */
    private long page;

    /**
     * 当前页数据长度
     */
    private long size;

}
