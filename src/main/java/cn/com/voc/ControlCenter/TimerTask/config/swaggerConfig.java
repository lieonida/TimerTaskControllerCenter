package cn.com.voc.ControlCenter.TimerTask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @description: Swagger-Api配置
 * @author: hn
 * @date: 2019/4/4
 */
@Configuration
public class swaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.com.voc.ControlCenter.TimerTask.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("定时任务调度中心")
                .description("接口")
                .termsOfServiceUrl("http://127.0.0.1:8080/")
                .version("1.0")
                .build();
    }
}
