package org.test.business.control.dao.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.mongodb.WriteResult;

/**
 * Abstract DAO class for base CRUD operations on entities. <br>
 *
 * @param <EI> Entity interface
 * @param <EC> Entity concrete class (annotated with Morphia annotations) and implements EI.
 */
public abstract class AbstractDAO <EI, EC extends EI> {

    @Inject
    protected Datastore ds;

    public abstract Class<EC> getEntityClazz();

    public void save(EI entity) {

	if (entity == null) {
	    throw new IllegalArgumentException("entity is null");
	}

	ds.save(entity);
    }

    public void save(Collection<EI> entities) {

	if (entities == null) {
	    throw new IllegalArgumentException("entities cannot be null");
	}

	if (!entities.isEmpty()) {
	    ds.save(entities);
	}
    }

    public EI findById(Object id) {

	if (id == null) {
	    throw new IllegalArgumentException("id is null");
	}

	return ds.get(getEntityClazz(), id);
    }

    @SuppressWarnings("unchecked")
    public List<EI> findByIds(Collection<?> ids) {

	if (ids == null) {
	    throw new IllegalArgumentException("ids cannot be null");
	}

	if (ids.isEmpty()) {
	    return Collections.emptyList();
	}

	Query<EC> query = ds.get(getEntityClazz(), ids);

	List<EI> results = (List<EI>) query.asList();

	if (results == null) {
	    results = Collections.emptyList();
	}

	return results;
    }

    public long getCount() {

	return ds.getCount(getEntityClazz());
    }

    public EI refresh(EI entity) {

	if (entity == null) {
	    throw new IllegalArgumentException("entity is null");
	}

	return ds.get(entity);
    }

    public boolean deleteById(Object id) {

	if (id == null) {
	    throw new IllegalArgumentException("id is null");
	}

	WriteResult wr = ds.delete(getEntityClazz(), id);

	if (wr.getN() > 0) {
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

	return wr.getN();
    }


}
