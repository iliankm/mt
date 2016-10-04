package org.mt.business.boundary.service.employee;

import javax.inject.Inject;

import org.mt.business.api.boundary.service.employee.EmployeeService;
import org.mt.business.api.boundary.service.employee.argument.CreateEmployeeArgument;
import org.mt.business.api.domain.employee.Employee;
import org.mt.business.control.repository.employee.EmployeeRepository;

public class EmployeeServiceBean implements EmployeeService {

    @Inject
    private EmployeeRepository employeeRepository;

    @Override
    public Employee findById(Object id) {

	return employeeRepository.findById(id);
    }

    @Override
    public Employee create(CreateEmployeeArgument createEmployeeArgument) {
	// TODO Auto-generated method stub
	return null;
    }

}
