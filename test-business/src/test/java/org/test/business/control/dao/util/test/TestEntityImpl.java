package org.test.business.control.dao.util.test;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class TestEntityImpl implements TestEntity {

    @Id
    ObjectId id;

    String field1;

    Integer field2;

    @Override
    public ObjectId getId() {
        return id;
    }

    @Override
    public void setId(ObjectId id) {
        this.id = id;
    }

    @Override
    public String getField1() {
        return field1;
    }

    @Override
    public void setField1(String field1) {
        this.field1 = field1;
    }

    @Override
    public Integer getField2() {
        return field2;
    }

    @Override
    public void setField2(Integer field2) {
        this.field2 = field2;
    }

}
