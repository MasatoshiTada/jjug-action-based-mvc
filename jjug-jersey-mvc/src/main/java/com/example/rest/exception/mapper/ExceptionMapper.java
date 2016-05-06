package com.example.rest.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.example.rest.exception.dto.ExceptionDto;
import com.example.rest.thymeleaf.ThymeleafViewable;

import java.util.HashMap;

/**
 *
 * @author tada
 */
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
                .entity(new ThymeleafViewable("error/exception.html", models))
                .build();
    }
    
}
