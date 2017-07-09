package com.tr1nks.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * web конфигурация приложения
 * описание view resolver,
 * описание template resolver,
 * расположение ресурсов,
 * алиасы
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private static final String ENCODING = "UTF-8";
    private ApplicationContext applicationContext;

    /**
     * {@inheritDoc}
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/theme/**").addResourceLocations("classpath:/static/theme/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }

    /**
     * template resolver для thymeleaf, с расширением view - .html
     *
     * @return template resolver
     */
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

    /**
     * template engine для thymeleaf
     *
     * @return template engine
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    /**
     * template view resolver для thymeleaf
     *
     * @return template view resolver
     */
    @Bean
    public ViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        viewResolver.setCharacterEncoding(ENCODING);
        return viewResolver;
    }


}
