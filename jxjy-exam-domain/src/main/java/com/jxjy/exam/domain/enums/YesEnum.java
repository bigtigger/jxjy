package com.jxjy.exam.domain.enums;

import lombok.Getter;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 20:58
 */
@Getter
public enum YesEnum {
    NO(0, "否"),
    YES(1, "是");

    private Integer value;
    private String label;

    YesEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

}
