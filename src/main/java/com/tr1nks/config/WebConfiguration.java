package com.tr1nks.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private static final String ENCODING = "UTF-8";
    private ApplicationContext applicationContext;

    public WebConfiguration() {
        super();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/theme/**").addResourceLocations("classpath:/static/theme/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/pages/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(true);
        templateResolver.setCharacterEncoding(ENCODING);
        templateResolver.addTemplateAlias("includes", "./../includes");
//        templateResolver.addTemplateAlias("navigation","./../includes/navigation");
//        templateResolver.addTemplateAlias("cssIncludes","./../includes/cssIncludes");
//        templateResolver.addTemplateAlias("jsIncludes","./../includes/jsIncludes");
        return templateResolver;
    }

    @Bean
    public org.thymeleaf.spring5.SpringTemplateEngine templateEngine() {
        org.thymeleaf.spring5.SpringTemplateEngine templateEngine = new org.thymeleaf.spring5.SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ViewResolver thymeleafViewResolver() {
        org.thymeleaf.spring5.view.ThymeleafViewResolver viewResolver = new org.thymeleaf.spring5.view.ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        viewResolver.setCharacterEncoding(ENCODING);
        return viewResolver;
    }


}
