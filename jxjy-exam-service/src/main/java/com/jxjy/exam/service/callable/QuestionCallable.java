package com.jxjy.exam.service.callable;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/16 21:52
 */
@Component
public class QuestionCallable implements Callable {


    @Override
    public Integer call() throws Exception {
        return null;
    }
}
