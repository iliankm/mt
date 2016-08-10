package org.mt.business.boundary.service;

import javax.inject.Inject;

import org.mt.business.api.boundary.service.EmployeeService;
import org.mt.business.api.entity.EmployeeEntity;
import org.mt.business.control.repository.EmployeeRepository;

public class EmployeeServiceBean implements EmployeeService {

    @Inject
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeEntity findById(Object id) {

	return employeeRepository.findById(id);
    }

    @Override
    public void save(EmployeeEntity employee) {

	employeeRepository.save(employee);
    }

}
