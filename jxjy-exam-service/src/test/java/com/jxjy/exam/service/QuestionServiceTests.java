package com.jxjy.exam.service;

import com.jxjy.exam.service.config.TestConfig;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/16 15:45
 */
@SpringBootTest(classes = TestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(value = {"classpath:application-test.properties"})
public class QuestionServiceTests {

    @Autowired
    private QuestionService questionService;

    @Test
    public void testScrapyQuestions() {
        questionService.scrapyQuestions(Lists.newArrayList(38870L));
    }



    @Test
    public void testscrapByScanCategory() {
        questionService.scrapByScanCategory(19058L, 37712L);
    }
}
