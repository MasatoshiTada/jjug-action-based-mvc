package com.example.rest.controller;

import com.example.persistence.entity.Employee;
import com.example.rest.form.EmployeeIdForm;
import com.example.rest.thymeleaf.ThymeleafViewable;
import com.example.rest.validation.LocalizedValidator;
import com.example.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

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

    @Inject
    private LocalizedValidator validator;

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
            HashMap<String, Object> models = new HashMap<>();
            models.put("violations", violations);
            return new ThymeleafViewable("employee/index.html", models);
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
