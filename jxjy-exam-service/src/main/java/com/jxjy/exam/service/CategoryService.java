package com.jxjy.exam.service;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 18:43
 */
public interface CategoryService {
    /**
     * 爬取指定类目及其子类目
     * @param catId 类目id
     */
    void scrapyCategories(Long catId);

    /**
     * 爬取指定类目及其子类目
     * @param catId 类目id
     */
    void scrapyCategoryByRetry(Long catId);
}
