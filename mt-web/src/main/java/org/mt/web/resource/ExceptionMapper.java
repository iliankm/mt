package org.mt.web.resource;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.mt.business.api.exception.ResourceNotFoundException;

public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {

	if (ResourceNotFoundException.class.isAssignableFrom(exception.getClass())) {
	    return Response.status(Status.NOT_FOUND).build();
	} else {
	    return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}
    }

}
