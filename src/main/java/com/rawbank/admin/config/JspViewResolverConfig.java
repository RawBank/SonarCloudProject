package com.rawbank.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class JspViewResolverConfig {

    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("text/html");
        
        // Make sure > Thymeleaf order & FreeMarker order.
        viewResolver.setOrder(1000);
        
        return viewResolver;
    }
    
}

 /*
  * https://o7planning.org/11257/use-multiple-viewresolvers-in-spring-boot
  * 
   this tutorial  is helping  to know how to use multiple  web  java Template Engine   in the same project
   Such as Thymeleaf, Jsp, Freemaker and others 
   in this project i  was  be forced to use two engines because of handling an  ongoing project build with Jsp while some spec is going fun with 
   Thymeleaf  in this project  the email 
  * 
  * 
  
  */