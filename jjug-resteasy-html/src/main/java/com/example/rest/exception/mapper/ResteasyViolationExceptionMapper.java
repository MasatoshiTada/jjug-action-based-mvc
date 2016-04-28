package com.example.rest.exception.mapper;

import org.jboss.resteasy.api.validation.ResteasyViolationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResteasyViolationExceptionMapper implements ExceptionMapper<ResteasyViolationException> {
    @Override
    public Response toResponse(ResteasyViolationException exception) {
        System.out.println(this.getClass().getSimpleName() + ".toResponse()");
        String[] errors = exception.getViolations()
                .stream()
                .map(violation -> violation.getMessage())
                .toArray(String[]::new);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errors).build();
    }
}
