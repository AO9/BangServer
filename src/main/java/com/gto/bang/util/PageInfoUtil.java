package com.gto.bang.util;

import com.github.pagehelper.PageInfo;

/**
 * Created by shenjialong on 20/1/27.
 */
public class PageInfoUtil {

    public static final int PAGE_SIZE = 40; // 默认分页行数
    public static final int PAGE_SIZE_CEILING = 100; // 分页行数最大值
    public static final int PAGE_FIRST = 1; // 第一页

    public static <T> void setDefaultValue(PageInfo<T> page) {
        if (page.getPageNum() == 0) {
            page.setPageNum(PAGE_FIRST);
        }
        if (page.getPageSize() == 0) {
            page.setPageSize(PAGE_SIZE);
        }
        if (page.getPageSize() > PAGE_SIZE_CEILING) {
            page.setPageSize(PAGE_SIZE_CEILING);
        }
    }

}
