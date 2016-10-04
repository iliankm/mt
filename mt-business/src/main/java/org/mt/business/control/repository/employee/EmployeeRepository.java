package org.mt.business.control.repository.employee;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;
import javax.inject.Inject;

import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.mt.business.api.domain.SortCriteria;
import org.mt.business.api.domain.employee.EmployeeSearchCriteria;
import org.mt.business.control.repository.AbstractRepository;
import org.mt.business.domain.employee.EmployeeEntityBean;

@CacheDefaults(cacheName = "EMPLOYEE_CACHE")
public class EmployeeRepository extends AbstractRepository<EmployeeEntityBean> {

    @SuppressWarnings("unused")
    @Inject
    private Logger log;

    @Override
    public Class<EmployeeEntityBean> getEntityClazz() {

	return EmployeeEntityBean.class;
    }

    @CacheResult
    public List<EmployeeEntityBean> findByCriteria(EmployeeSearchCriteria criteria, SortCriteria sort, int offset,
	    int limit) {

	final Query<EmployeeEntityBean> query = ds.createQuery(getEntityClazz());

	if (criteria != null) {
	    final List<Criteria> _criteria = new LinkedList<>();

	    if (criteria.getName() != null && !criteria.getName().isEmpty()) {
		_criteria.add(query.criteria("name").contains(criteria.getName()));
	    }

	    if (criteria.getEmail() != null && !criteria.getEmail().isEmpty()) {
		_criteria.add(query.criteria("email").equal(criteria.getEmail()));
	    }

	    if (criteria.getGender() != null) {
		_criteria.add(query.criteria("gender").equal(criteria.getGender().name()));
	    }

	    query.and((Criteria[]) _criteria.toArray());
	}

	if (sort != null) {
	    query.order(ORDER_TRANSFORMER.apply(sort));
	}

	query.offset(offset);

	query.limit(limit);

	final List<EmployeeEntityBean> results = query.asList();

	return results;

    }
}
