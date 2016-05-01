package com.example.rest.feature;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import org.jboss.resteasy.plugins.providers.html.View;
import org.thymeleaf.TemplateEngine;

public class ThymeleafResponseFilter implements ContainerResponseFilter {
        
    private TemplateEngine templateEngine;

    ThymeleafResponseFilter(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        View view = (View) responseContext.getEntity();
        ThymeleafView thymeleafView = new ThymeleafView(templateEngine, view.getPath(), 
                view.getModel(), view.getModelName());
        responseContext.setEntity(thymeleafView);
    }
        
}
