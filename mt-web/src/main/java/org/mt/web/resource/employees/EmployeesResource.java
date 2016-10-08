package org.mt.web.resource.employees;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.mt.business.api.boundary.service.employee.EmployeeService;
import org.mt.business.api.boundary.service.employee.argument.CreateEmployeeArgument;
import org.mt.business.api.domain.employee.Employee;
import org.mt.web.resource.RestEndpoints;

@Path(RestEndpoints.EMPLOYEES)
public class EmployeesResource {

    @Inject
    EmployeeService employeeService;

    @POST
    public Response create(CreateEmployeeArgument createEmployeeArgument) {

	final Employee employee = employeeService.create(createEmployeeArgument);

	return Response.status(Status.OK).type(MediaType.APPLICATION_JSON_TYPE).entity(employee.getId()).build();
    }

}
