package org.mt.business.api.boundary.service;

import org.mt.business.api.domain.employee.Employee;

public interface EmployeeService {

    public Employee findById(Object id);

}
