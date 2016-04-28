package com.example.rest.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.example.rest.exception.dto.ExceptionDto;
import org.glassfish.jersey.server.mvc.Viewable;

import java.util.HashMap;

/**
 *
 * @author tada
 */
@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {
    
    @Override
    public Response toResponse(Exception exception) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(exception.getMessage());
        HashMap<String, Object> model = new HashMap<>();
        model.put("exceptionDto", exceptionDto);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new Viewable("/error/exception", model))
                .build();
    }
    
}
