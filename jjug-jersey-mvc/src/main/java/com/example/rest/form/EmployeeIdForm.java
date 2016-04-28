package com.example.rest.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.ws.rs.QueryParam;

public class EmployeeIdForm {

    @QueryParam("id")
    @NotBlank(message = "{employee.id.notblank}")
    @Pattern(regexp = "[1-9][0-9]*", message = "{employee.id.pattern}")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
