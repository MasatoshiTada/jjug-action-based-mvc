package com.example.rest.thymeleaf;

import java.io.IOException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.thymeleaf.TemplateEngine;

class ThymeleafWriterInterceptor implements WriterInterceptor {

    private TemplateEngine templateEngine;

    ThymeleafWriterInterceptor(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        ThymeleafView thymeleafView = (ThymeleafView) context.getEntity();
        thymeleafView.setTemplateEngine(templateEngine);
        context.proceed();
    }
}
