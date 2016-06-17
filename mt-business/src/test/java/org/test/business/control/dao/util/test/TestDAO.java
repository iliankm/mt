package org.test.business.control.dao.util.test;

import org.test.business.control.dao.util.AbstractDAO;
import org.test.business.control.dao.util.MongoDB;

public class TestDAO extends AbstractDAO<TestEntity, TestEntityImpl> {

    public TestDAO() {
	this.ds = MongoDB.instance().getDatastore();
    }

    @Override
    public Class<TestEntityImpl> getEntityClazz() {
	return TestEntityImpl.class;
    }

}
