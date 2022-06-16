package com.jxjy.exam.service.impl;

import com.alibaba.fastjson.JSON;
import com.jxjy.exam.common.CommonConst;
import com.jxjy.exam.common.httpclient.HttpClientUtils;
import com.jxjy.exam.domain.dao.Category;
import com.jxjy.exam.domain.vo.cat.CategoryResultVo;
import com.jxjy.exam.domain.vo.cat.CategoryVo;
import com.jxjy.exam.domain.vo.cat.ParentCategoryVo;
import com.jxjy.exam.mapper.CategoryMapper;
import com.jxjy.exam.service.AbstractTokenService;
import com.jxjy.exam.service.CategoryService;
import com.jxjy.exam.service.convert.CategoryConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author yyc
 * @version 1.0
 * @date 2022/6/15 20:21
 */
@Service
@Slf4j
public class CategoryServiceImpl extends AbstractTokenService implements CategoryService {

    @Autowired
    private CategoryConverter categoryConverter;

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 类目url
     */
    private static final String cat_url = "https://www.juhaisoft.net/cloud/exam/practise/getdirs2?pid=%s";

    private static final String category_referer_url = "https://www.juhaisoft.net/cloud/exam/practise";

    @Override
    public void scrapyCategories(Long catId) {
        try {
            String url = String.format(cat_url, catId);
            String result = HttpClientUtils.doPost(url, getHeaders(category_referer_url), null, CommonConst.TIME_OUT);
            if(StringUtils.isEmpty(result)){
                log.warn("HttpClientUtils ");
                return;
            }
            CategoryResultVo categoryResultVo = JSON.parseObject(result, CategoryResultVo.class);
            if(Objects.isNull(categoryResultVo) || CollectionUtils.isEmpty(categoryResultVo.getResult())){
                log.error("CategoryServiceImpl.scrapyCategories result convert to CategoryResultVo error,url->{},result->{}", url, result);
                return;
            }
            for(ParentCategoryVo parentCategoryVo : categoryResultVo.getResult()){
                Category category = categoryConverter.convert(parentCategoryVo);
                if(Objects.isNull(category)){
                    log.error("CategoryServiceImpl.scrapyCategories parentCategoryVo is null,parentCategoryVo->{}", JSON.toJSONString(parentCategoryVo));
                    continue;
                }
                categoryMapper.insertSelective(category);
                if(CollectionUtils.isEmpty(parentCategoryVo.getChilds())){
                    continue;
                }
                for(CategoryVo categoryVo : parentCategoryVo.getChilds()){
                    Category childCategory = categoryConverter.convert(categoryVo);
                    if(Objects.isNull(childCategory)){
                        log.error("CategoryServiceImpl.scrapyCategories childCategory is null,childCategory->{}", JSON.toJSONString(categoryVo));
                        continue;
                    }
                    categoryMapper.insertSelective(childCategory);
                    if(Objects.equals(categoryVo.getIs_leaf(), Boolean.FALSE)){
                        /**先睡一小会儿*/
                        Thread.sleep(1000 + (int) (Math.random() * 800));
                        scrapyCategories(categoryVo.getId());
                    }
                }
            }
        }catch (Exception e){
            log.error("scrapy category error,catId->{}", catId, e);
            throw new RuntimeException(e);
        }
    }
}
