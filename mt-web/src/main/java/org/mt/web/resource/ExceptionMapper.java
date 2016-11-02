package org.mt.web.resource;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.mt.business.api.exception.ResourceNotFoundException;

public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    @Inject
    private Logger log;

    @Override
    public Response toResponse(Exception exception) {

	log.log(Level.SEVERE, "Exception caught by JAX-RS exception mapper:", exception);

	//ResourceNotFoundException
	if (ResourceNotFoundException.class.isAssignableFrom(exception.getClass())) {
	    return Response.status(Status.NOT_FOUND).build();
	}

	return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
}
