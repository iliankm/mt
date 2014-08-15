package org.test.business.control.dao;

import java.util.Collection;

public interface DAO<DO> {

    DO save(DO domainObject);

    DO findById(Object id);

    Collection<DO> findByIds(Collection<?> ids);

    boolean deleteById(Object id);

    int deleteByIds(Collection<?> ids);

}
