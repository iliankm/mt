package org.mt.web.resource.employees;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.mt.business.api.boundary.service.employee.EmployeeService;
import org.mt.business.api.boundary.service.employee.argument.AddressArgument;
import org.mt.business.api.boundary.service.employee.argument.CreateUpdateEmployeeArgument;
import org.mt.business.api.boundary.service.employee.argument.PhoneArgument;
import org.mt.business.api.domain.employee.Employee;
import org.mt.web.resource.RestEndpoints;

@Path(RestEndpoints.EMPLOYEES)
public class EmployeesResource {

    @Inject
    EmployeeService employeeService;

    @POST
    public Response create(CreateUpdateEmployeeArgument createUpdateEmployeeArgument) {

        final Employee employee = employeeService.create(createUpdateEmployeeArgument);

        return Response.status(Status.OK).type(MediaType.APPLICATION_JSON_TYPE).entity(employee.getId()).build();
    }

    @PUT
    @Path(RestEndpoints.EMPLOYEE)
    public Response update(@PathParam("id") String id, CreateUpdateEmployeeArgument createUpdateEmployeeArgument) {

        employeeService.update(id, createUpdateEmployeeArgument);

        return Response.status(Status.OK).type(MediaType.APPLICATION_JSON_TYPE).entity(id).build();
    }

    @PUT
    @Path(RestEndpoints.EMPLOYEE_ADDRESS)
    public Response update(@PathParam("id") String id, AddressArgument updateAddressArgument) {

        employeeService.update(id, updateAddressArgument);

        return Response.status(Status.OK).type(MediaType.APPLICATION_JSON_TYPE).entity(id).build();
    }

    @PUT
    @Path(RestEndpoints.EMPLOYEE_PHONES)
    public Response update(@PathParam("id") String id, Set<PhoneArgument> phones) {

        employeeService.update(id, phones);

        return Response.status(Status.OK).type(MediaType.APPLICATION_JSON_TYPE).entity(id).build();
    }

}
