package com.example.rest.thymeleaf;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Provider
public class ThymeleafDynamicFeature implements DynamicFeature {

    private TemplateEngine templateEngine;
    
    public ThymeleafDynamicFeature() {
        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/");
        // setSuffix()は指定しない
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }
    
    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        Class<?> returnType = resourceInfo.getResourceMethod().getReturnType();
        if (returnType.equals(ThymeleafView.class)) {
            context.register(new ThymeleafWriterInterceptor(templateEngine));
        }
    }
    
}
