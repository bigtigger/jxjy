package com.jxjy.exam.web.controller;

import com.jxjy.exam.service.CategoryService;
import com.jxjy.exam.web.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 21:28
 */
@RestController
@RequestMapping("/category")
@Api(tags = "类目管理")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/scapy")
    @ApiOperation("根据类目id爬取类目及其子类目信息")
    public Result scrapyCategories(@RequestParam("catId") Long catId) {
        categoryService.scrapyCategories(catId);
        return Result.success(Boolean.TRUE);
    }
}
