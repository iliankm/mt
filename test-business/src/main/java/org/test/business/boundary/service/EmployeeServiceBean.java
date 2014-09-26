package org.test.business.boundary.service;

import javax.inject.Inject;

import org.test.business.api.boundary.service.EmployeeService;
import org.test.business.api.domain.Address;
import org.test.business.api.domain.Employee;
import org.test.business.api.domain.Phone;
import org.test.business.api.domain.util.Gender;
import org.test.business.control.dao.EmployeeDAO;

public class EmployeeServiceBean implements EmployeeService {

    @SuppressWarnings("unused")
    @Inject
    private EmployeeDAO employeeDAO;

    @Override
    public Employee create(String name, Gender gender)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Employee update(String name, Gender gender, String email, long salary)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Employee findById(String employeeId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Employee deleteById(String employeeId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateAddress(String employeeId, Address address)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void addPhone(String employeeId, Phone phone)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void deletePhone(String employeeId, Phone phone)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void addSubstitute(String employeeId, String substituteEmployeeId)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteSubstitute(String employeeId, String substituteEmployeeId)
    {
        // TODO Auto-generated method stub

    }


}
