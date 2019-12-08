package webservice.exceptionmappers;

import webservice.dto.ExceptionMessage;
import webservice.exceptions.DatabaseOperationFailedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DatabaseOperationFailedExceptionMapper implements ExceptionMapper<DatabaseOperationFailedException> {
    @Override
    public Response toResponse(DatabaseOperationFailedException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ExceptionMessage(e.getMessage())).build();
    }
}
