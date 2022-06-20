package com.jxjy.exam.service;

import com.alibaba.fastjson.JSON;
import com.jxjy.exam.domain.dao.Category;
import com.jxjy.exam.service.config.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 21:34
 */
@SpringBootTest(classes = TestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(value = {"classpath:application-test.properties"})
@Slf4j
public class CategoryServiceTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testScrapyCategories() {
//        categoryService.scrapyCategories(71697L);
        categoryService.scrapyCategories(-1L);
    }

    @Test
    public void testScanCategories() {
        List<Category> categoryList = categoryService.scanById(261L);
        log.info("类目数量->{},类目数据->{}", categoryList.size(), JSON.toJSONString(categoryList));
    }
}
