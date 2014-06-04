package org.test.business.control.dao.util.test;

import org.bson.types.ObjectId;

public interface TestEntity {

    public abstract ObjectId getId();

    public abstract void setId(ObjectId id);

    public abstract String getField1();

    public abstract void setField1(String field1);

    public abstract Integer getField2();

    public abstract void setField2(Integer field2);

}