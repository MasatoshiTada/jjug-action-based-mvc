package com.example.rest.thymeleaf;

import java.util.HashMap;
import java.util.Map;

public class ThymeleafViewable {

    private String templatePath;
    private Map<String, Object> models;

    public ThymeleafViewable(String templatePath, Map<String, Object> models) {
        this.templatePath = templatePath;
        this.models = models;
    }

    public ThymeleafViewable(String templatePath) {
        this.templatePath = templatePath;
        this.models = new HashMap<>();
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public Map<String, Object> getModels() {
        return models;
    }
}
