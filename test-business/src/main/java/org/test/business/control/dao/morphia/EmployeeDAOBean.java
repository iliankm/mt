package org.test.business.control.dao.morphia;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.test.business.api.domain.Address;
import org.test.business.api.domain.Employee;
import org.test.business.api.domain.Phone;
import org.test.business.api.domain.util.EmployeeSearchCriteria;
import org.test.business.api.domain.util.SortCriteria;
import org.test.business.control.dao.EmployeeDAO;
import org.test.business.control.dao.morphia.util.OrderGenerator;
import org.test.business.entity.morphia.AddressEntityBean;
import org.test.business.entity.morphia.EmployeeEntityBean;
import org.test.business.entity.morphia.PhoneEntityBean;

import com.mongodb.WriteResult;

public class EmployeeDAOBean implements EmployeeDAO {

    private static final String CACHE_BY_ID = "emp-cache-by-id";

    private static final String CACHE_BY_IDS = "emp-cache-by-ids";

    private static final String CACHE_BY_CRITERIA = "emp-cache-by-criteria";

    @Inject
    protected Datastore ds;

    @Inject
    private OrderGenerator orderGenerator;

    @SuppressWarnings("unused")
    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Override
    public Employee save(Employee employee) {

	if (employee == null) {
	    throw new IllegalArgumentException("Employee is null");
	}

        /**
         * Find by id or create new entity
         */
	EmployeeEntityBean entity;
        if (employee.getId() != null) {
            entity = ds.get(EmployeeEntityBean.class, employee.getId());
        }
        else {
            entity = newEntityObject();
        }

	/**
	 * Domain to entity
	 */
	domainObjectToEntity(employee, entity);

	/**
	 * Validation
	 */
	Set<ConstraintViolation<EmployeeEntityBean>> constraintViolations = validator.validate(entity);
	if (!constraintViolations.isEmpty()) {
	    throw new RuntimeException(constraintViolations.toString());
	}

	/**
	 * Persist
	 */
	ds.save(entity);

	/**
	 * Invalidate cache
	 */
	invalidateCache();

	/**
	 * Result
	 */
	Employee result = newDomainObject();
	entityToDomainObject(entity, result);

	return result;
    }

    @Override
    @CacheResult(cacheName = CACHE_BY_ID)
    public Employee findById(Object id) {

	if (id == null) {
	    throw new IllegalArgumentException("id is null");
	}

	EmployeeEntityBean employeeEntity = ds.get(EmployeeEntityBean.class, id);

	Employee employee = newDomainObject();

	entityToDomainObject(employeeEntity, employee);

	return employee;
    }

    @Override
    @CacheResult(cacheName = CACHE_BY_IDS)
    public Collection<Employee> findByIds(Collection<?> ids) {

	if (ids == null) {
	    throw new IllegalArgumentException("ids cannot be null");
	}

	if (ids.isEmpty()) {
	    return Collections.emptyList();
	}

	Query<EmployeeEntityBean> query = ds.get(EmployeeEntityBean.class, ids);

	List<EmployeeEntityBean> queryResults = query.asList();

	if (queryResults == null) {
	    queryResults = Collections.emptyList();
	}

	List<Employee> results = new LinkedList<>();

	entitesToDomainObjects(queryResults, results);

	return results;
    }

    @Override
    @CacheResult(cacheName = CACHE_BY_CRITERIA)
    public Collection<Employee> findByCriteria(EmployeeSearchCriteria criteria, SortCriteria sort, int offset, int limit) {

	Query<EmployeeEntityBean> query = ds.createQuery(EmployeeEntityBean.class);

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

	List<EmployeeEntityBean> queryResults = query.asList();

	List<Employee> results = new LinkedList<>();

	entitesToDomainObjects(queryResults, results);

	return results;
    }

    @Override
    public boolean deleteById(Object id) {

	if (id == null) {
	    throw new IllegalArgumentException("id is null");
	}

	WriteResult wr = ds.delete(EmployeeEntityBean.class, id);

	if (wr.getN() > 0) {

	    invalidateCache();

	    return true;
	}

	return false;
    }

    @Override
    public int deleteByIds(Collection<?> ids) {

	if (ids == null) {
	    throw new IllegalArgumentException("ids cannot be null");
	}

	if (ids.isEmpty()) {
	    return 0;
	}

	WriteResult wr = ds.delete(EmployeeEntityBean.class, ids);

	invalidateCache();

	return wr.getN();
    }

    protected EmployeeEntityBean newEntityObject() {

        return new EmployeeEntityBean();
    }

    protected Employee newDomainObject() {

        return new Employee();
    }

    protected void entityToDomainObject(EmployeeEntityBean employeeEntity, Employee employee) {

	employee.setId(employeeEntity.getId());
	employee.setName(employeeEntity.getName());
	employee.setGender(employeeEntity.getGender());
	employee.setEmail(employeeEntity.getEmail());
	employee.setSalary(employeeEntity.getSalary());

	if (employee.getAddress() != null) {

	    Address address = new Address();
	    address.setCountry(employee.getAddress().getCountry());
	    address.setCity(employee.getAddress().getCity());
	    address.setStreet(employee.getAddress().getStreet());
	    address.setZip(employee.getAddress().getZip());
	}

	if (!employeeEntity.getPhones().isEmpty()) {

	    for (PhoneEntityBean p : employeeEntity.getPhones()) {

		Phone phone = new Phone();
		phone.setPhone(p.getPhone());
		phone.setType(p.getType());

		employee.getPhones().add(phone);
	    }
	}

	return;
    }

    protected void entitesToDomainObjects(Collection<EmployeeEntityBean> employeeEntites, Collection<Employee> employees) {

	for (EmployeeEntityBean e : employeeEntites) {

	    Employee employee = newDomainObject();
	    entityToDomainObject(e, employee);
	    employees.add(employee);
	}

	return;
    }

    protected void domainObjectToEntity(Employee employee, EmployeeEntityBean employeeEntity) {

	employeeEntity.setName(employee.getName());
	employeeEntity.setGender(employee.getGender());
	employeeEntity.setEmail(employee.getEmail());
	employeeEntity.setSalary(employee.getSalary());

	if (employee.getAddress() != null) {

	    AddressEntityBean addressEntityBean = new AddressEntityBean();
	    employeeEntity.setAddress(addressEntityBean);

	    addressEntityBean.setCountry(employee.getAddress().getCountry());
	    addressEntityBean.setCity(employee.getAddress().getCity());
	    addressEntityBean.setStreet(employee.getAddress().getStreet());
	    addressEntityBean.setZip(employee.getAddress().getZip());
	}

	if (!employee.getPhones().isEmpty()) {

	    for (Phone p : employee.getPhones()) {

		PhoneEntityBean phoneEntityBean = new PhoneEntityBean();
		employeeEntity.getPhones().add(phoneEntityBean);

		phoneEntityBean.setPhone(p.getPhone());
		phoneEntityBean.setType(p.getType());
	    }
	}
    }

    protected void invalidateCache() {

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
