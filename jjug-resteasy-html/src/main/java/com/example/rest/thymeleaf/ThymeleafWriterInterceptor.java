package com.example.rest.thymeleaf;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Provider
@ThymeleafController
public class ThymeleafWriterInterceptor implements WriterInterceptor {

    private TemplateEngine templateEngine;

    @Inject
    private ServletContext servletContext;

    @PostConstruct
    public void init() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
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
