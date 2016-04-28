package com.example.rest.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.example.rest.exception.dto.ExceptionDto;
import org.glassfish.jersey.server.mvc.Viewable;

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
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new Viewable("/error/exception", exceptionDto))
                .build();
    }
    
}
