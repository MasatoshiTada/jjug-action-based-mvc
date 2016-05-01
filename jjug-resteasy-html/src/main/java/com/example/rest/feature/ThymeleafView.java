package com.example.rest.feature;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import org.jboss.resteasy.plugins.providers.html.Renderable;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

public class ThymeleafView implements Renderable {

    private TemplateEngine templateEngine;
    private String templateName;
    private Object model;
    private String modelName;

    public ThymeleafView(TemplateEngine templateEngine, String templateName, 
            Object model, String modelName) {
        this.templateEngine = templateEngine;
        this.templateName = templateName;
        this.model = model;
        this.modelName = modelName;
    }
    
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, WebApplicationException {
        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale());
        response.setCharacterEncoding("UTF-8");
        if (model instanceof Map) {
            Map<String, Object> variables = (Map<String, Object>) model;
            webContext.setVariables(variables);
        } else if (modelName != null) {
            webContext.setVariable(modelName, model);
        }
        templateEngine.process(templateName, webContext, response.getWriter());
    }
    
}
