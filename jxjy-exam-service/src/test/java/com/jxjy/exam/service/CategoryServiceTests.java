package com.jxjy.exam.service;

import com.jxjy.exam.service.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 21:34
 */
@SpringBootTest(classes = TestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(value = {"classpath:application-test.properties"})
public class CategoryServiceTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testScrapyCategories() {
        categoryService.scrapyCategories(71697L);
    }
}
