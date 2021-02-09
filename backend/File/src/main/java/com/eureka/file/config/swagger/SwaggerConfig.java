package com.eureka.file.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
class SwaggerConfiguration {
    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.swaggerInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eureka.file.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(true); // 기본으로 세팅되는 200,401,403,404 메시지를 표시
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("Eureka API Documentation")
                .description("Eureka 서비스 개발시 사용되는 서버 API에 대한 연동 문서입니다")
                .license("이수민")
                .licenseUrl("sumin416@naver.com")
                .version("1.0.0")
                .build();
    }
}