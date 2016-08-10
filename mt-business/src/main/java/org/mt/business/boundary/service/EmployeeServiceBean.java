package org.mt.business.boundary.service;

import javax.inject.Inject;

import org.mt.business.api.boundary.service.EmployeeService;
import org.mt.business.api.entity.EmployeeEntity;
import org.mt.business.control.dao.EmployeeDAO;

public class EmployeeServiceBean implements EmployeeService {

    @Inject
    private EmployeeDAO employeeDAO;

    @Override
    public EmployeeEntity findById(Object id) {

	return employeeDAO.findById(id);
    }

    @Override
    public void save(EmployeeEntity employee) {

	employeeDAO.save(employee);
    }

}
