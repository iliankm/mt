package org.mt.business.control.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;
import javax.inject.Inject;

import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.mt.business.api.entity.EmployeeEntity;
import org.mt.business.api.entity.util.EmployeeSearchCriteria;
import org.mt.business.api.entity.util.SortCriteria;
import org.mt.business.control.dao.util.AbstractDAO;
import org.mt.business.control.dao.util.OrderGenerator;
import org.mt.business.entity.EmployeeEntityBean;

@CacheDefaults(cacheName = "EMPLOYEE_CACHE")
public class EmployeeDAO extends AbstractDAO<EmployeeEntity, EmployeeEntityBean> {

    @Inject
    private OrderGenerator orderGenerator;

    @SuppressWarnings("unused")
    @Inject
    private Logger log;

    @Override
    public Class<EmployeeEntityBean> getEntityClazz() {

	return EmployeeEntityBean.class;
    }

    @CacheResult
    public List<? extends EmployeeEntity> findByCriteria(EmployeeSearchCriteria criteria, SortCriteria sort, int offset, int limit) {

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

	    query.and((Criteria[])_criteria.toArray());
	}

	if (sort != null) {
	    final String orderCondition = orderGenerator.generate(sort);
	    if (orderCondition != null && !orderCondition.isEmpty()) {
		query.order(orderCondition);
	    }
	}

	query.offset(offset);

	query.limit(limit);

	final List<EmployeeEntityBean> results = query.asList();

	return results;

    }
}
