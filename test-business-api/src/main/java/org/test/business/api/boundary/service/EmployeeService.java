package org.test.business.api.boundary.service;

import org.test.business.api.entity.EmployeeEntity;

public interface EmployeeService {
    
    public String test(String a);
    
    public EmployeeEntity findById(Object id);
    
    public void save(EmployeeEntity employee);

}
