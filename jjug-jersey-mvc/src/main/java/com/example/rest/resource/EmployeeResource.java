package com.example.rest.resource;

import com.example.persistence.entity.Employee;
import com.example.rest.form.EmployeeIdForm;
import com.example.service.EmployeeService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;

import org.glassfish.jersey.server.mvc.Viewable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author tada
 */
@Path("employee")
@RequestScoped
public class EmployeeResource {
    
    @Inject
    private EmployeeService employeeService;

    @Inject
    private Validator validator;

    @GET
    @Path("index")
    public Viewable index() throws Exception {
        return new Viewable("/employee/index");
    }
    
    @GET
    @Path("result")
    public Viewable result(@BeanParam EmployeeIdForm form) throws Exception {
        // バリデーション実行
        Set<ConstraintViolation<EmployeeIdForm>> violations = validator.validate(form);
        // エラーがあれば入力画面に戻る
        if (!violations.isEmpty()) {
            HashMap<String, Object> model = new HashMap<>();
            model.put("violations", violations);
            return new Viewable("/employee/index", model);
        }
        Integer id = Integer.valueOf(form.getId());
        throwException(id);
        Employee employee = employeeService.findByEmpId(id).orElse(null);
        HashMap<String, Object> model = new HashMap<>();
        model.put("employee", employee);
        return new Viewable("/employee/result", model);
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
