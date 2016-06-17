package org.test.business.boundary.service;

import javax.inject.Inject;

import org.test.business.api.boundary.service.EmployeeService;
import org.test.business.api.entity.EmployeeEntity;
import org.test.business.control.dao.EmployeeDAO;

public class EmployeeServiceBean implements EmployeeService {
    
    @Inject
    private EmployeeDAO employeeDAO;

    @Override
    public String test(String a) {
	
	return employeeDAO.test(a);
    }

    @Override
    public EmployeeEntity findById(Object id) {

	return employeeDAO.findById(id);
    }

    @Override
    public void save(EmployeeEntity employee) {
	
	employeeDAO.save(employee);
    }

}
