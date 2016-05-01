package com.example.rest.resource;

import com.example.persistence.entity.Employee;
import com.example.rest.form.EmployeeIdForm;
import com.example.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.binding.BindingResult;
import javax.validation.Valid;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Optional;

@Path("employee")
@RequestScoped
@Produces(MediaType.TEXT_HTML)
public class EmployeeResource {

    @Inject
    private EmployeeService employeeService;

    @Inject
    private Models models;

    @Inject
    private BindingResult bindingResult;

    @GET
    @Path("index")
    @Controller
    public String index() {
        return "employee/index.html";
    }

    @GET
    @Path("result")
    @Controller
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String result(@Valid @BeanParam EmployeeIdForm form) throws Exception {
        if (bindingResult.isFailed()) {
            models.put("bindingResult", bindingResult);
            return "employee/index.html";
        }
        Integer id = Integer.valueOf(form.getId());
        throwException(id);
        Optional<Employee> employeeOptional = employeeService.findByEmpId(Integer.valueOf(form.getId()));
        models.put("employee", employeeOptional.orElse(null));
        return "employee/result.html";
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
