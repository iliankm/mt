package org.mt.business.control.repository.employee;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;
import javax.inject.Inject;
import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.mt.business.api.boundary.service.employee.argument.CreateUpdateEmployeeArgument;
import org.mt.business.api.domain.SortCriteria;
import org.mt.business.api.domain.employee.EmployeeSearchCriteria;
import org.mt.business.control.repository.AbstractRepository;
import org.mt.business.domain.employee.AddressEntityBean;
import org.mt.business.domain.employee.EmployeeEntityBean;
import org.mt.business.domain.employee.PhoneEntityBean;

@CacheDefaults(cacheName = "EMPLOYEE_CACHE")
public class EmployeeRepository extends AbstractRepository<EmployeeEntityBean> {

    @SuppressWarnings("unused")
    @Inject
    private Logger log;

    @Override
    public Class<EmployeeEntityBean> getEntityClazz() {

	return EmployeeEntityBean.class;
    }

    @Override
    @CacheRemoveAll
    public void save(@Valid EmployeeEntityBean entity) {

	super.save(entity);
    }

    @Override
    @CacheResult
    public EmployeeEntityBean findById(String id) {

	return super.findById(id);
    }

    @Override
    @CacheRemoveAll
    public boolean deleteById(String id) {

	return super.deleteById(id);
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

    @CacheRemoveAll
    public int update(String id, @Valid CreateUpdateEmployeeArgument createUpdateEmployeeArgument) {

	Objects.requireNonNull(id);

	Objects.requireNonNull(createUpdateEmployeeArgument);

	final UpdateOperations<EmployeeEntityBean> ops = ds.createUpdateOperations(EmployeeEntityBean.class);

	ops.set("identificationNumber", createUpdateEmployeeArgument.getIdentificationNumber());

	ops.set("name", createUpdateEmployeeArgument.getName());

	if (createUpdateEmployeeArgument.getEmail() != null) {
	    ops.set("email", createUpdateEmployeeArgument.getEmail());
	} else {
	    ops.unset("email");
	}

	ops.set("gender", createUpdateEmployeeArgument.getGender());

	ops.set("lastModifiedDate", new Date());

	final Query<EmployeeEntityBean> query = ds.createQuery(EmployeeEntityBean.class).field(Mapper.ID_KEY).equal(new ObjectId(id));

	final UpdateResults<EmployeeEntityBean> updateResults = ds.update(query, ops);

	return updateResults.getUpdatedCount();
    }

    @CacheRemoveAll
    public int update(String id, @Valid AddressEntityBean address) {

	Objects.requireNonNull(id);

	Objects.requireNonNull(address);

	final UpdateOperations<EmployeeEntityBean> ops = ds.createUpdateOperations(EmployeeEntityBean.class);

	ops.set("address", address);

	ops.set("lastModifiedDate", new Date());

	final Query<EmployeeEntityBean> query = ds.createQuery(EmployeeEntityBean.class).field(Mapper.ID_KEY).equal(new ObjectId(id));

	final UpdateResults<EmployeeEntityBean> updateResults = ds.update(query, ops);

	return updateResults.getUpdatedCount();
    }

    @CacheRemoveAll
    public int update(String id, @Valid Set<PhoneEntityBean> phones) {

	Objects.requireNonNull(id);

	final UpdateOperations<EmployeeEntityBean> ops = ds.createUpdateOperations(EmployeeEntityBean.class);

	ops.set("phones", phones != null ? phones : Collections.emptySet());

	ops.set("lastModifiedDate", new Date());

	final Query<EmployeeEntityBean> query = ds.createQuery(EmployeeEntityBean.class).field(Mapper.ID_KEY).equal(new ObjectId(id));

	final UpdateResults<EmployeeEntityBean> updateResults = ds.update(query, ops);

	return updateResults.getUpdatedCount();
    }
}
