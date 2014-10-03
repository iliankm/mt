package org.test.business.control.dao.morphia;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;
import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.test.business.api.domain.Address;
import org.test.business.api.domain.Employee;
import org.test.business.api.domain.Phone;
import org.test.business.api.domain.util.EmployeeSearchCriteria;
import org.test.business.api.domain.util.SortCriteria;
import org.test.business.control.dao.EmployeeDAO;
import org.test.business.control.dao.morphia.util.MongoDBUtils;
import org.test.business.control.dao.morphia.util.OrderGenerator;
import org.test.business.entity.morphia.AddressEntityBean;
import org.test.business.entity.morphia.EmployeeEntityBean;
import org.test.business.entity.morphia.PhoneEntityBean;

public class EmployeeDAOBean extends AbstractDAO<Employee, EmployeeEntityBean> implements EmployeeDAO {

    private static final String CACHE_BY_ID = "emp-cache-by-id";

    private static final String CACHE_BY_IDS = "emp-cache-by-ids";

    private static final String CACHE_BY_CRITERIA = "emp-cache-by-criteria";

    @Inject
    private OrderGenerator orderGenerator;

    @SuppressWarnings("unused")
    @Inject
    private Logger log;

    @Override
    @CacheResult(cacheName = CACHE_BY_ID)
    public Employee findById(String id) {

	return super.findById(id);
    }

    @Override
    @CacheResult(cacheName = CACHE_BY_IDS)
    public Collection<Employee> findByIds(Collection<String> ids) {

	return super.findByIds(ids);
    }

    @Override
    @CacheResult(cacheName = CACHE_BY_CRITERIA)
    public Collection<Employee> findByCriteria(EmployeeSearchCriteria criteria, SortCriteria sort, int offset, int limit) {

	Query<EmployeeEntityBean> query = ds.createQuery(EmployeeEntityBean.class);

	if (criteria != null) {
	    List<Criteria> _criteria = new LinkedList<>();

	    if (criteria.getName() != null && !criteria.getName().isEmpty()) {
		_criteria.add(query.criteria("name").containsIgnoreCase(criteria.getName()));
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

	Collection<EmployeeEntityBean> queryResults = query.asList();

	Collection<Employee> results = entitesToDomainObjects(queryResults);

	return results;
    }

    @Override
    public void addSubstitute(String employeeId, String substituteEmployeeId) {

        if (employeeId == null || substituteEmployeeId == null) {
            throw new IllegalArgumentException("employeeId and substituteEmployeeId cannot be null.");
        }

        ObjectId employeeOId = MongoDBUtils.objectIdFromString(employeeId);

        ObjectId substituteEmployeeOId = MongoDBUtils.objectIdFromString(substituteEmployeeId);

        EmployeeEntityBean employeeEntity = ds.get(EmployeeEntityBean.class, employeeOId);

        if (employeeEntity == null) {
            throw new RuntimeException("Employee " + employeeId + " not found.");
        }

        EmployeeEntityBean substituteEmployeeEntity = ds.get(EmployeeEntityBean.class, substituteEmployeeOId);

        if (substituteEmployeeEntity == null) {
            throw new RuntimeException("Employee " + substituteEmployeeId + " not found.");
        }

        employeeEntity.getSubstituteIds().add(substituteEmployeeOId);

        ds.save(employeeEntity);
    }

    @Override
    public void deleteSubstitute(String employeeId, String substituteEmployeeId) {

        if (employeeId == null || substituteEmployeeId == null) {
            throw new IllegalArgumentException("employeeId and substituteEmployeeId cannot be null");
        }

        ObjectId employeeOId = MongoDBUtils.objectIdFromString(employeeId);

        ObjectId substituteEmployeeOId = MongoDBUtils.objectIdFromString(substituteEmployeeId);

        EmployeeEntityBean employeeEntity = ds.get(EmployeeEntityBean.class, employeeOId);

        if (employeeEntity == null) {
            throw new RuntimeException("Employee " + employeeId + " not found.");
        }

        EmployeeEntityBean substituteEmployeeEntity = ds.get(EmployeeEntityBean.class, substituteEmployeeOId);

        if (substituteEmployeeEntity == null) {
            throw new RuntimeException("Employee " + substituteEmployeeId + " not found.");
        }

        employeeEntity.getSubstituteIds().remove(substituteEmployeeOId);

        ds.save(employeeEntity);
    }

    @Override
    protected Class<EmployeeEntityBean> getEntitytClass()
    {
        return EmployeeEntityBean.class;
    }

    @Override
    protected EmployeeEntityBean newEntityObject() {

        return new EmployeeEntityBean();
    }

    @Override
    protected Employee newDomainObject() {

        return new Employee();
    }

    @Override
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

    @Override
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

    @Override
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
