package com.example.rest.controller;

import com.example.persistence.entity.Employee;
import com.example.rest.thymeleaf.ThymeleafController;
import com.example.rest.thymeleaf.ThymeleafView;
import com.example.rest.form.EmployeeIdForm;
import com.example.rest.validation.LocalizedValidator;
import com.example.service.EmployeeService;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

@Path("employee")
public class EmployeeController {

    @Inject
    private EmployeeService employeeService;

    @Inject
    private LocalizedValidator validator;

    @GET
    @Path("index")
    @ThymeleafController
    public ThymeleafView index() {
        return new ThymeleafView("employee/index.html");
    }

    @GET
    @Path("result")
    @ThymeleafController
    public ThymeleafView result(@BeanParam EmployeeIdForm form) throws Exception {
        // バリデーション実行
        Set<ConstraintViolation<EmployeeIdForm>> violations = validator.validate(form);
        // エラーがあれば入力画面に戻る
        if (!violations.isEmpty()) {
            HashMap<String, Object> models = new HashMap<>();
            models.put("violations", violations);
            return new ThymeleafView("employee/index.html", models);
        }
        Integer id = Integer.valueOf(form.getId());
        throwException(id);
        Employee employee = employeeService.findByEmpId(id);
        HashMap<String, Object> models = new HashMap<>();
        models.put("employee", employee);
        return new ThymeleafView("employee/result.html", models);
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
