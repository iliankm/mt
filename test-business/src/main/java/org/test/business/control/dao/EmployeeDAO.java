package org.test.business.control.dao;

import java.util.Collection;

import org.test.business.api.domain.Employee;
import org.test.business.api.domain.util.EmployeeSearchCriteria;
import org.test.business.api.domain.util.SortCriteria;

public interface EmployeeDAO extends DAO<Employee> {

    Collection<Employee> findByCriteria(EmployeeSearchCriteria criteria,
	    SortCriteria sort, int offset, int limit);

    void addSubstitute(String employeeId, String substituteEmployeeId);

    void deleteSubstitute(String employeeId, String substituteEmployeeId);

}
