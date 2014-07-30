package org.test.business.control.dao;

import java.util.Collection;

import org.test.business.api.domain.Employee;
import org.test.business.api.domain.util.EmployeeSearchCriteria;
import org.test.business.api.domain.util.SortCriteria;

public interface EmployeeDAO {

    Employee save(Employee employee);

    Employee findById(Object id);

    Collection<Employee> findByIds(Collection<?> ids);

    Collection<Employee> findByCriteria(EmployeeSearchCriteria criteria,
	    SortCriteria sort, int offset, int limit);

    boolean deleteById(Object id);

    int deleteByIds(Collection<?> ids);

}
