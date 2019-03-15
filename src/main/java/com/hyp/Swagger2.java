package com.hyp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 

* @Title: Swagger2  

* @Description: Swagger 是一款RESTFUL接口的文档在线自动生成+功能测试功能软件。  

* @author HanYupeng  

* @date 2018-06-13 08:22
 */
@Configuration /*通过此注解，让spring加载此配置类*/
@EnableSwagger2 /*启用Swagger2*/
public class Swagger2 {
	
	@Value("${swagger.show}")
	private boolean swaggerShow;
	
	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hyp.controller"))
                .paths(PathSelectors.any())
                .build();
    }
   /* private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger Restful API")
                .description("更多Spring Boot相关文章请关注：https://luischen.com/")
                .termsOfServiceUrl("https://luischen.com/")
                .contact("Luis chen")
                .version("1.0")
                .build();
    } */
	 private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Spring cloud 中使用Swagger2构建Restful APIs")
	                .description("swagger项目文档测试说明")
	                .version("1.0").build();
	    }
}
