package com.example.rest.thymeleaf;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Provider
@ThymeleafController
public class ThymeleafWriterInterceptor implements WriterInterceptor {

    private TemplateEngine templateEngine;

    @PostConstruct
    public void init() {
        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/");
        // setSuffix()は指定しない
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        Object entity = context.getEntity();
        if (ThymeleafView.class.isAssignableFrom(entity.getClass())) {
            ThymeleafView thymeleafView = (ThymeleafView) context.getEntity();
            thymeleafView.setTemplateEngine(templateEngine);
        }
        context.proceed();
    }
}
