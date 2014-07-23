package org.test.business.control.dao.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.mongodb.WriteResult;

/**
 * Abstract DAO class for base CRUD operations on entities. <br>
 *
 * @param <E> Entity concrete class (annotated with Morphia annotations) and implements EI.
 */
public abstract class AbstractDAO <E> {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Inject
    protected Datastore ds;

    public abstract Class<E> getEntityClazz();

    public void save(E entity) {

	if (entity == null) {
	    throw new IllegalArgumentException("entity is null");
	}

	ds.save(entity);

	invalidateCache();
    }

    public void save(Collection<E> entities) {

	if (entities == null) {
	    throw new IllegalArgumentException("entities cannot be null");
	}

	if (!entities.isEmpty()) {

	    ds.save(entities);

	    invalidateCache();
	}
    }

    public E findById(Object id) {

	if (id == null) {
	    throw new IllegalArgumentException("id is null");
	}

	logger.info(this.getEntityClazz().getName() + "with id: " + id + " is to be loaded from db!!!");

	return ds.get(getEntityClazz(), id);
    }

    public List<E> findByIds(Collection<?> ids) {

	if (ids == null) {
	    throw new IllegalArgumentException("ids cannot be null");
	}

	if (ids.isEmpty()) {
	    return Collections.emptyList();
	}

	Query<E> query = ds.get(getEntityClazz(), ids);

	List<E> results = query.asList();

	if (results == null) {
	    results = Collections.emptyList();
	}

	return results;
    }

    public long getCount() {

	return ds.getCount(getEntityClazz());
    }

    public boolean deleteById(Object id) {

	if (id == null) {
	    throw new IllegalArgumentException("id is null");
	}

	WriteResult wr = ds.delete(getEntityClazz(), id);

	if (wr.getN() > 0) {

	    invalidateCache();

	    return true;
	}

	return false;
    }

    public int deleteByIds(Collection<?> ids) {

	if (ids == null) {
	    throw new IllegalArgumentException("ids cannot be null");
	}

	if (ids.isEmpty()) {
	    return 0;
	}

	WriteResult wr = ds.delete(getEntityClazz(), ids);

	invalidateCache();

	return wr.getN();
    }

    protected void invalidateCache() {

	logger.info("Cache is being invalidated.");
    };

}
