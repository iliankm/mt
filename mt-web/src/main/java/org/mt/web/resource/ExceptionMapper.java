package org.mt.web.resource;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {

	return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }

}
