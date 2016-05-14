package com.example.rest.controller;

import com.example.common.validation.LocalizedMessageInterpolator;
import com.example.persistence.entity.Employee;
import com.example.rest.form.EmployeeIdForm;
import com.example.rest.thymeleaf.ThymeleafViewable;
import com.example.service.EmployeeService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tada
 */
@Path("employee")
@RequestScoped
@Produces(MediaType.TEXT_HTML)
public class EmployeeController {
    
    @Inject
    private EmployeeService employeeService;

    @Context
    private HttpHeaders httpHeaders;

    private Validator validator;

    @PostConstruct
    public void init() {
        Locale locale = httpHeaders.getAcceptableLanguages().get(0);
        System.out.println(locale);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.usingContext()
                .messageInterpolator(new LocalizedMessageInterpolator(locale, factory.getMessageInterpolator()))
                .getValidator();
    }

    @GET
    @Path("index")
    public ThymeleafViewable index() throws Exception {
        return new ThymeleafViewable("employee/index.html");
    }
    
    @GET
    @Path("result")
    public ThymeleafViewable result(@BeanParam EmployeeIdForm form) throws Exception {
        // バリデーション実行
        Set<ConstraintViolation<EmployeeIdForm>> violations = validator.validate(form);
        // エラーがあれば入力画面に戻る
        if (!violations.isEmpty()) {
            HashMap<String, Object> model = new HashMap<>();
            model.put("violations", violations);
            return new ThymeleafViewable("employee/index.html", model);
        }
        Integer id = Integer.valueOf(form.getId());
        throwException(id);
        Employee employee = employeeService.findByEmpId(id).orElse(null);
        HashMap<String, Object> models = new HashMap<>();
        models.put("employee", employee);
        return new ThymeleafViewable("employee/result.html", models);
    }

    private void throwException(int value) throws Exception {
        switch (value) {
            case 77:
                throw new IOException("入出力例外が発生しました。");
            case 88:
                throw new NullPointerException("null例外が発生しました。");
            case 99:
                throw new Exception("例外が発生しました。");
        }
    }
}
