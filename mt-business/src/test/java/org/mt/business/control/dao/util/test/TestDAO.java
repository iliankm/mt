package org.mt.business.control.dao.util.test;

import org.mt.business.control.repository.util.AbstractRepository;
import org.mt.business.control.repository.util.MongoDB;

public class TestDAO extends AbstractRepository<TestEntity, TestEntityImpl> {

    public TestDAO() {
	this.ds = MongoDB.instance().getDatastore();
    }

    @Override
    public Class<TestEntityImpl> getEntityClazz() {
	return TestEntityImpl.class;
    }

}
