package com.example.rest.feature;

import org.jboss.resteasy.plugins.providers.html.View;

import javax.ws.rs.container.*;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;

@Provider
public class ValidationTemplateDynamicFeature implements DynamicFeature {

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        System.out.println(this.getClass().getSimpleName() + ".configure()");
        Method resourceMethod = resourceInfo.getResourceMethod();
        ValidationTemplate validationTemplate = resourceMethod.getDeclaredAnnotation(ValidationTemplate.class);
        if (validationTemplate != null) {
            String template = validationTemplate.value();
            System.out.println("register ValidationTemplateResponseFilter : " + template);
            context.register(new ValidationTemplateResponseFilter(template));
        }
    }

    private static class ValidationTemplateResponseFilter implements ContainerResponseFilter {

        private String template;

        ValidationTemplateResponseFilter(String template) {
            this.template = template;
        }

        @Override
        public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
            Response.StatusType statusInfo = responseContext.getStatusInfo();
            if (statusInfo.equals(Response.Status.BAD_REQUEST)) {
                String[] errors = (String[]) responseContext.getEntity();
                View view = new View(template, errors, "errors");
                responseContext.setEntity(view);
                responseContext.setStatusInfo(statusInfo);
            }
        }
    }
}
