package org.mt.business.boundary.service;

import javax.inject.Inject;

import org.mt.business.api.boundary.service.EmployeeService;
import org.mt.business.api.domain.employee.Employee;
import org.mt.business.control.repository.employee.EmployeeRepository;

public class EmployeeServiceBean implements EmployeeService {

    @Inject
    private EmployeeRepository employeeRepository;

    @Override
    public Employee findById(Object id) {

	return employeeRepository.findById(id);
    }

}
