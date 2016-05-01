package com.example.rest.exception.mapper;

import com.example.rest.exception.dto.ExceptionDto;
import com.example.rest.thymeleaf.ThymeleafView;
import org.jboss.resteasy.plugins.providers.html.View;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;

@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace();
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(exception.getMessage());
        HashMap<String, Object> models = new HashMap<>();
        models.put("exceptionDto", exceptionDto);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ThymeleafView("error/exception.html", models))
                .build();
    }
}
