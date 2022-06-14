package com.jxjy.exam.web.config;

import com.jxjy.exam.common.CommonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * webMvc 配置类
 *
 * @author yusheng
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(1024 * 1024 * 10);
        return commonsMultipartResolver;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jxjy.exam.web.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("JXJY系统平台API文档")
                        .description("JXJY系统平台API详细文档")
                        .version("1.0")
                        .contact(new Contact("职业白给团队","https://github.com/bigtigger/jxjy.git","530808281@qq.com"))
                        .license("The Apache License")
                        .build());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String imgDirPath = CommonUtils.getImgDirPath();
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:" + imgDirPath + "/static/img/");
    }
}