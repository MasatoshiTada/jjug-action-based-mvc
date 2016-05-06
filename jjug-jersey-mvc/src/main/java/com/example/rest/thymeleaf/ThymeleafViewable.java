package com.example.rest.thymeleaf;

import org.glassfish.jersey.server.mvc.Viewable;

import java.util.Map;

public class ThymeleafViewable extends Viewable {

    public ThymeleafViewable(String templateName) throws IllegalArgumentException {
        super(templateName);
    }

    public ThymeleafViewable(String templateName, Map<String, Object> models) throws IllegalArgumentException {
        super(templateName, models);
    }

    @Override
    public Map<String, Object> getModel() {
        return (Map<String, Object>) super.getModel();
    }

    @Override
    public String getTemplateName() {
        String templateName = super.getTemplateName();
        if (templateName.startsWith("/") == false) {
            return "/" + templateName;
        } else {
            return templateName;
        }
    }

    @Override
    public boolean isTemplateNameAbsolute() {
        return true;
    }
}
