package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/static/bootstrap/**")
        .addResourceLocations("classpath:/static/bootstrap/");
    registry.addResourceHandler("/resources/static/css/**")
        .addResourceLocations("classpath:/static/css/");
    registry.addResourceHandler("/resources/static/images/**")
    	.addResourceLocations("classpath:/static/images/");
    registry.addResourceHandler("/resources/static/js/**")
		.addResourceLocations("classpath:/static/js/");
  }
}