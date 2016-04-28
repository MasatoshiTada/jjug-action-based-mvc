package com.example.rest.exception.mapper;

import org.glassfish.ozark.core.ModelsImpl;

import javax.mvc.Models;
import javax.mvc.Viewable;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        Models models = new ModelsImpl();
        models.put("message", exception.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new Viewable("error/exception.jsp", models)).build();
    }
}
