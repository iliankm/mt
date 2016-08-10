package org.mt.business.control.repository.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;
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
public abstract class AbstractRepository <EI, EC extends EI> {

    @Inject
    protected Datastore ds;

    public abstract Class<EC> getEntityClazz();

    @CacheRemoveAll
    public void save(EI entity) {

	Objects.requireNonNull(entity);

	ds.save(entity);
    }

    @CacheRemoveAll
    public void save(Collection<EI> entities) {

        Objects.requireNonNull(entities);

        if (entities.isEmpty()) {
            throw new IllegalArgumentException("Empty entities collection passed for save.");
        }

        ds.save(entities);
    }

    @CacheResult
    public EI findById(Object id) {

        Objects.requireNonNull(id);

	return ds.get(getEntityClazz(), id);
    }

    @SuppressWarnings("unchecked")
    @CacheResult
    public List<EI> findByIds(Collection<?> ids) {

        Objects.requireNonNull(ids);

	if (ids.isEmpty()) {
	    throw new IllegalArgumentException("Empty ids collection passed.");
	}

	final Query<EC> query = ds.get(getEntityClazz(), ids);

	final List<EI> results = (List<EI>) query.asList();

	if (results == null) {
	    return Collections.emptyList();
	}

	return results;
    }

    public long getCount() {

	return ds.getCount(getEntityClazz());
    }

    @CacheRemoveAll
    public boolean deleteById(Object id) {

        Objects.requireNonNull(id);

	final WriteResult wr = ds.delete(getEntityClazz(), id);

	if (wr.getN() > 0) {

	    return true;
	}

	return false;
    }

    @CacheRemoveAll
    public int deleteByIds(Collection<?> ids) {

        Objects.requireNonNull(ids);

        if (ids.isEmpty()) {
            throw new IllegalArgumentException("Empty ids collection passed.");
        }

	final WriteResult wr = ds.delete(getEntityClazz(), ids);

	return wr.getN();
    }

}
