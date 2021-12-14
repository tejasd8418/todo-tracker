package com.niit.springcloudapigateway.config;

import com.niit.springcloudapigateway.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/v1/**")
                        .uri("lb://ToDo"))
                .route(p->p
                .path("/api/v2/**")
                        .uri("lb://user-authentication"))
                .route(p->p
                        .path("/api/v3/**")
                        .uri("lb://NotificationService"))
                .route(p->p
                        .path("/api/v4/**")
                        .uri("lb://ArchivesService"))
                .route(p->p
                        .path("/api/v5/**")
                        .uri("lb://Guest"))
                .build();
    }
    @Bean
    public FilterRegistrationBean jwtFilterBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/api/v1/**","/api/v3/**","/api/v4/**","/api/v5/**");
        return filterRegistrationBean;
    }
}




