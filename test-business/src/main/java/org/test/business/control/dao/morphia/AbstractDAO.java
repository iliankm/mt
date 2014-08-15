package org.test.business.control.dao.morphia;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.cache.annotation.CacheResult;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.test.business.api.domain.Employee;
import org.test.business.api.domain.util.Identifiable;
import org.test.business.control.dao.DAO;
import org.test.business.entity.morphia.EmployeeEntityBean;

import com.mongodb.WriteResult;

public abstract class AbstractDAO<DO extends Identifiable, EO extends Identifiable> implements DAO<DO> {

    @Inject
    protected Datastore ds;

    @Inject
    private Validator validator;

    protected abstract Class<EO> getEntitytClass();

    protected abstract EO newEntityObject();

    protected abstract DO newDomainObject();

    protected abstract void domainObjectToEntity(DO domainObject, EO entityObject);

    protected abstract void entityToDomainObject(EO entityObject, DO domainObject);

    protected abstract void invalidateCache();

    @Override
    public DO save(DO domainObject) {

        if (domainObject == null) {
            throw new IllegalArgumentException("domainObject is null");
        }

        /**
         * Find by id or create new entity
         */
        EO entity;
        if (domainObject.getId() != null) {
            entity = ds.get(getEntitytClass(), domainObject.getId());
        }
        else {
            entity = newEntityObject();
        }

        /**
         * Domain to entity
         */
        domainObjectToEntity(domainObject, entity);

        /**
         * Validation
         */
        Set<ConstraintViolation<EO>> constraintViolations = validator.validate(entity);
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
        DO result = newDomainObject();
        entityToDomainObject(entity, result);

        return result;
    }

    @Override
    public DO findById(Object id) {

        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        EO entity = ds.get(getEntitytClass(), id);

        DO domainObject = newDomainObject();

        entityToDomainObject(entity, domainObject);

        return domainObject;
    }

    @Override
    public Collection<DO> findByIds(Collection<?> ids) {

        if (ids == null) {
            throw new IllegalArgumentException("ids cannot be null");
        }

        if (ids.isEmpty()) {
            return Collections.emptyList();
        }

        Query<EO> query = ds.get(getEntitytClass(), ids);

        List<EO> queryResults = query.asList();

        if (queryResults == null) {
            queryResults = Collections.emptyList();
        }

        Collection<DO> results = entitesToDomainObjects(queryResults);

        return results;
    }

    @Override
    public boolean deleteById(Object id) {

        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        WriteResult wr = ds.delete(getEntitytClass(), id);

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

        WriteResult wr = ds.delete(getEntitytClass(), ids);

        invalidateCache();

        return wr.getN();
    }


    protected Collection<DO> entitesToDomainObjects(Collection<EO> entites) {

        Collection<DO> domainObjects = new LinkedList<>();

        for (EO e : entites) {

            DO domainObject = newDomainObject();
            entityToDomainObject(e, domainObject);
            domainObjects.add(domainObject);
        }

        return domainObjects;
    }





}
