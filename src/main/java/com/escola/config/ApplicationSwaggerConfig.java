package com.escola.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationSwaggerConfig implements WebMvcConfigurer {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
       .apiInfo(getApiInfo()).select()
       .apis(RequestHandlerSelectors
       .basePackage("com.escola.controller"))
       .paths(PathSelectors.any()).build();
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
      .addMapping("/**")
      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
      .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
      .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  private ApiInfo getApiInfo() {
    return new ApiInfoBuilder().title("Documentação")
        .description("Escola")
        .version("0.2").build();
  }
}
