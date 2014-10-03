package org.test.business.boundary.service;

import javax.inject.Inject;

import org.test.business.api.boundary.service.EmployeeService;
import org.test.business.api.domain.Address;
import org.test.business.api.domain.Employee;
import org.test.business.api.domain.Phone;
import org.test.business.api.domain.util.Gender;
import org.test.business.control.dao.EmployeeDAO;

public class EmployeeServiceBean implements EmployeeService {

    @Inject
    private EmployeeDAO employeeDAO;

    @Override
    public Employee create(String name, Gender gender) {

        Employee employee = new Employee();

        employee.setName(name);
        employee.setGender(gender);

        return employeeDAO.save(employee);
    }

    @Override
    public Employee update(String employeeId, String name, Gender gender, String email, long salary) {

        Employee employee = employeeDAO.findById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee with id: " + employeeId + " not found.");
        }

        employee.setName(name);
        employee.setGender(gender);
        employee.setEmail(email);
        employee.setSalary(salary);

        return employeeDAO.save(employee);
    }

    @Override
    public Employee findById(String employeeId) {

        return employeeDAO.findById(employeeId);
    }

    @Override
    public boolean deleteById(String employeeId) {

        return employeeDAO.deleteById(employeeId);
    }

    @Override
    public void updateAddress(String employeeId, Address address) {

        Employee employee = employeeDAO.findById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee with id: " + employeeId + " not found.");
        }

        employee.setAddress(address);

        employeeDAO.save(employee);
    }

    @Override
    public void addPhone(String employeeId, Phone phone) {

        Employee employee = employeeDAO.findById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee with id: " + employeeId + " not found.");
        }

        employee.getPhones().add(phone);

        employeeDAO.save(employee);
    }

    @Override
    public void deletePhone(String employeeId, Phone phone) {

        Employee employee = employeeDAO.findById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee with id: " + employeeId + " not found.");
        }

        employee.getPhones().remove(phone);

        employeeDAO.save(employee);
    }

    @Override
    public void addSubstitute(String employeeId, String substituteEmployeeId) {

        employeeDAO.addSubstitute(employeeId, substituteEmployeeId);
    }

    @Override
    public void deleteSubstitute(String employeeId, String substituteEmployeeId) {

        employeeDAO.deleteSubstitute(employeeId, substituteEmployeeId);
    }

}
