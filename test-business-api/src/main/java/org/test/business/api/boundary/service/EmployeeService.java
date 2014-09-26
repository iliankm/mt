package org.test.business.api.boundary.service;

import org.test.business.api.domain.Address;
import org.test.business.api.domain.Employee;
import org.test.business.api.domain.Phone;
import org.test.business.api.domain.util.Gender;

public interface EmployeeService {

    Employee create(String name, Gender gender);

    Employee update(String name, Gender gender, String email, long salary);

    Employee findById(String employeeId);

    Employee deleteById(String employeeId);

    void updateAddress(String employeeId, Address address);

    void addPhone(String employeeId, Phone phone);

    void deletePhone(String employeeId, Phone phone);

    void addSubstitute(String employeeId, String substituteEmployeeId);

    void deleteSubstitute(String employeeId, String substituteEmployeeId);
}
