package com.example.rest.resource;

import com.example.persistence.entity.Employee;
import com.example.rest.form.EmployeeIdForm;
import com.example.service.EmployeeService;
import org.jboss.resteasy.plugins.providers.html.Renderable;
import org.jboss.resteasy.plugins.providers.html.View;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;
import java.util.Set;

@Path("employee")
public class EmployeeResource {

    @Inject
    private EmployeeService employeeService;

    @Inject
    private Validator validator;

    @GET
    @Path("index")
    public Renderable index() {
        return new View("/WEB-INF/views/employee/index.jsp");
    }

    @GET
    @Path("result")
    public Renderable result(@BeanParam EmployeeIdForm form) throws Exception {
        // バリデーション実行
        Set<ConstraintViolation<EmployeeIdForm>> violations = validator.validate(form);
        // エラーがあれば入力画面に戻る
        if (!violations.isEmpty()) {
            return new View("/WEB-INF/views/employee/index.jsp", violations, "violations");
        }
        Integer id = Integer.valueOf(form.getId());
        throwException(id);
        Employee employee = employeeService.findByEmpId(id);
        return new View("/WEB-INF/views/employee/result.jsp", employee, "employee");
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
