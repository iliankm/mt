package org.mt.business.control.dao.util.test;

import org.mt.business.control.dao.util.AbstractDAO;
import org.mt.business.control.dao.util.MongoDB;

public class TestDAO extends AbstractDAO<TestEntity, TestEntityImpl> {

    public TestDAO() {
	this.ds = MongoDB.instance().getDatastore();
    }

    @Override
    public Class<TestEntityImpl> getEntityClazz() {
	return TestEntityImpl.class;
    }

}
