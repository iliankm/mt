package org.mt.business.api.boundary.service.employee;

import org.mt.business.api.boundary.service.employee.argument.CreateEmployeeArgument;
import org.mt.business.api.domain.employee.Employee;

public interface EmployeeService {

    Employee findById(Object id);

    Employee create(CreateEmployeeArgument createEmployeeArgument);

}
