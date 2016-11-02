package org.mt.business.api.boundary.service.employee;

import javax.validation.Valid;

import org.mt.business.api.boundary.service.employee.argument.CreateUpdateEmployeeArgument;
import org.mt.business.api.domain.employee.Employee;

public interface EmployeeService {

    Employee findById(String id);

    Employee create(@Valid CreateUpdateEmployeeArgument createUpdateEmployeeArgument);

    void update(String employeeId, @Valid CreateUpdateEmployeeArgument createUpdateEmployeeArgument);

}
