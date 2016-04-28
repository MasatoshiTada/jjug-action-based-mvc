package com.example.rest.resource;

import com.example.persistence.entity.Employee;
import com.example.rest.feature.ValidationTemplate;
import com.example.service.EmployeeService;
import org.hibernate.validator.constraints.NotBlank;
import org.jboss.resteasy.plugins.providers.html.Renderable;
import org.jboss.resteasy.plugins.providers.html.View;

import javax.inject.Inject;
import javax.validation.constraints.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.util.Optional;

@Path("employee")
public class EmployeeResource {

    @Inject
    private EmployeeService employeeService;

    @GET
    @Path("index")
    public Renderable index() {
        return new View("/WEB-INF/views/employee/index.jsp");
    }

    @GET
    @Path("result")
    @ValidationTemplate("/WEB-INF/views/employee/index.jsp")
    public Renderable result(@QueryParam("id")
            @NotBlank(message = "{employee.id.notblank}")
            @Pattern(regexp = "[1-9][0-9]*", message = "{employee.id.pattern}") String idStr) throws Exception {
        Integer id = Integer.valueOf(idStr);
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
