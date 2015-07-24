package org.gradle;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.togglz.console.TogglzConsoleServlet;
import org.togglz.servlet.TogglzFilter;

@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    
    @Bean
    public ServletRegistrationBean togglzServletBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                new TogglzConsoleServlet());
        registration.addUrlMappings("/togglz/*");
        return registration;
    }
    
    @Bean
    public FilterRegistrationBean togglzFilter() {
    	FilterRegistrationBean registration = new FilterRegistrationBean(
                new TogglzFilter());
        registration.setUrlPatterns(Arrays.asList("/*"));
        return registration;
    }
}