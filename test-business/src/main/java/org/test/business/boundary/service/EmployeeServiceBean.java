package org.test.business.boundary.service;

import javax.inject.Inject;

import org.test.business.api.boundary.service.EmployeeService;
import org.test.business.control.dao.EmployeeDAO;

public class EmployeeServiceBean implements EmployeeService {

    @SuppressWarnings("unused")
    @Inject
    private EmployeeDAO employeeDAO;


}
