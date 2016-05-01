package com.example.rest.exception.mapper;

import com.example.rest.exception.dto.ExceptionDto;
import org.glassfish.ozark.core.ModelsImpl;

import javax.mvc.Models;
import javax.mvc.Viewable;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace();
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(exception.getMessage());
        Models models = new ModelsImpl();
        models.put("exceptionDto", exceptionDto);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new Viewable("error/exception.html", models)).build();
    }
}
