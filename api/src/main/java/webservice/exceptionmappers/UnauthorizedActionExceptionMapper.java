package webservice.exceptionmappers;

import webservice.exceptions.UnauthorizedActionException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnauthorizedActionExceptionMapper implements ExceptionMapper<UnauthorizedActionException> {
    @Override
    public Response toResponse(UnauthorizedActionException e) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
