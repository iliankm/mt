package org.test.business.control.dao;

import java.util.Collection;

public interface DAO<DO> {

    DO save(DO domainObject);

    DO findById(String id);

    Collection<DO> findByIds(Collection<String> ids);

    boolean deleteById(String id);

    int deleteByIds(Collection<String> ids);

}
