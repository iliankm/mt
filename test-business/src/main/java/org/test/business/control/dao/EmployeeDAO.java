package org.test.business.control.dao;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;
import javax.inject.Inject;

import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.test.business.api.domain.util.EmployeeSearchCriteria;
import org.test.business.api.domain.util.SortCriteria;
import org.test.business.control.dao.util.AbstractDAO;
import org.test.business.control.dao.util.OrderGenerator;
import org.test.business.entity.EmployeeEntityBean;

public class EmployeeDAO extends AbstractDAO<EmployeeEntityBean> {

    private static final String CACHE_BY_ID = "emp-cache-by-id";

    private static final String CACHE_BY_IDS = "emp-cache-by-ids";

    private static final String CACHE_BY_CRITERIA = "emp-cache-by-criteria";

    @Inject
    private OrderGenerator orderGenerator;

    @Inject
    private Logger log;

    @Override
    public Class<EmployeeEntityBean> getEntityClazz() {
	return EmployeeEntityBean.class;
    }

    @CacheResult(cacheName = CACHE_BY_CRITERIA)
    public List<EmployeeEntityBean> findByCriteria(EmployeeSearchCriteria criteria, SortCriteria sort, int offset, int limit) {

	Query<EmployeeEntityBean> query = ds.createQuery(getEntityClazz());

	if (criteria != null) {
	    List<Criteria> _criteria = new LinkedList<>();

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
	    String orderCondition = orderGenerator.generate(sort);
	    if (orderCondition != null && !orderCondition.isEmpty()) {
		query.order(orderCondition);
	    }
	}

	query.offset(offset);

	query.limit(limit);

	List<EmployeeEntityBean> results = query.asList();

	return results;

    }

    @CacheResult(cacheName = "employee-test")
    public String test(String a) {

	log.info("Method test(" + a + ") just invoked." );

	return a + "1";
    }

    @Override
    @CacheResult(cacheName = CACHE_BY_ID)
    public EmployeeEntityBean findById(Object id) {

	return super.findById(id);
    }

    @Override
    @CacheResult(cacheName = CACHE_BY_IDS)
    public List<EmployeeEntityBean> findByIds(Collection<?> ids) {

	return super.findByIds(ids);
    }

    @Override
    protected void invalidateCache() {

	super.invalidateCache();

	invalidateCacheById();

	invalidateCacheByIds();

	invalidateCacheByCriteria();
    }

    @CacheRemoveAll(cacheName = CACHE_BY_ID)
    private void invalidateCacheById() {}

    @CacheRemoveAll(cacheName = CACHE_BY_IDS)
    private void invalidateCacheByIds() {}

    @CacheRemoveAll(cacheName = CACHE_BY_CRITERIA)
    private void invalidateCacheByCriteria() {}

}
