package com.example.rest.thymeleaf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import org.jboss.resteasy.plugins.providers.html.Renderable;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

public class ThymeleafView implements Renderable {

    private String templateName;
    private Map<String, Object> models;
    private TemplateEngine templateEngine;

    public ThymeleafView(String templateName) {
        this(templateName, new HashMap<>());
    }

    public ThymeleafView(String templateName, Map<String, Object> models) {
        this.templateName = templateName;
        this.models = models;
    }

    void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, WebApplicationException {
        response.setCharacterEncoding("UTF-8");
        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale());
        webContext.setVariables(models);
        templateEngine.process(templateName, webContext, response.getWriter());
    }
    
}
