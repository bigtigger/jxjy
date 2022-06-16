package com.jxjy.exam.web.controller;

import com.jxjy.exam.service.QuestionService;
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
 * @date 2022/6/16 18:02
 */
@RestController
@RequestMapping("/question")
@Api(tags = "考题管理")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/scapy")
    @ApiOperation("根据类目id爬取类目及其子类目信息")
    public Result scrapy(@RequestParam("catId") Long catId, @RequestParam("page") Integer pageNo) {
        return Result.success(questionService.scrapyQuestionsRetry(catId, pageNo));
    }
}
