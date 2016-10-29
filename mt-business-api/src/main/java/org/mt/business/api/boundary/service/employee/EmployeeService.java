package org.mt.business.api.boundary.service.employee;

import org.mt.business.api.boundary.service.employee.argument.CreateUpdateEmployeeArgument;
import org.mt.business.api.domain.employee.Employee;

public interface EmployeeService {

    Employee findById(String id);

    Employee create(CreateUpdateEmployeeArgument createUpdateEmployeeArgument);

    void update(String employeeId, CreateUpdateEmployeeArgument createUpdateEmployeeArgument);

}
