package com.jxjy.exam.service;

import java.util.List;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 18:55
 */
public interface QuestionService {
    /**
     * 爬取指定类目考题
     * @param catIds 类目id列表
     */
    void scrapyQuestions(List<Long> catIds);

    /**
     * 爬取指定类目考题
     * @param catId 类目id
     * @param pageNo 页码
     * @return
     */
    Integer scrapyQuestionsRetry(Long catId, Integer pageNo);


    /**
     * 轮询类目主键id抓取考题信息
     * @param beginId 类目主键id
     */
    void scrapByScanCategory(Long beginId, Long endId);
}
