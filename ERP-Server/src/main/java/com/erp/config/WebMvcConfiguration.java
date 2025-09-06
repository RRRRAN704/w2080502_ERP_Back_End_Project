package com.erp.config;

import com.erp.interceptor.JwtTokenAdminInterceptor;
import com.erp.json.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * Configuration class, register web layer related components
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    /**
     * Register custom interceptors
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("Register custom interceptor...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/employee/**")
                .excludePathPatterns("/employee/info/login")
                .excludePathPatterns("/employee/info/send-email-code")
                .excludePathPatterns("/employee/info/reset-password")
                .excludePathPatterns("/employee/order");

    }

    /**
     * Generate interface documentation using knife4j
     * @return
     */
    @Bean
    public Docket docket1() {
        log.info("ERP Project API Documentation");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("ERP Project API Documentation")
                .version("1.0")
                .description("ERP Project API Documentation")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("Mangement Interface")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.erp.controller.employee"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * Set static resource mapping
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    /*Extending the Spring MVC framework's message converter*/
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("Extending the message converter...");
        //Create a message converter object
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //Set up an object converter for the message converter Serialize Java objects to JSON data
        converter.setObjectMapper(new JacksonObjectMapper());
        //Add message converter to the container.
        converters.add(0,converter);
    }
}
