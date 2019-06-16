package com.centralapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfigurationStaticResource extends WebMvcConfigurationSupport 
{


public void addResourceHandlers(ResourceHandlerRegistry registry){ 
    registry.addResourceHandler("/images/**")
         .addResourceLocations("classpath:/static/");}
	
	

}
