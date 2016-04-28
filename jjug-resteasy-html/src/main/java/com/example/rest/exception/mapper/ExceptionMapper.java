package com.example.rest.exception.mapper;

import com.example.rest.exception.dto.ExceptionDto;
import org.jboss.resteasy.plugins.providers.html.View;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(exception.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new View("/WEB-INF/views/error/exception.jsp", exceptionDto, "exceptionDto"))
                .build();
    }
}
