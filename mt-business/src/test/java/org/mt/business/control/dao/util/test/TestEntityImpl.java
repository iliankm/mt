package org.mt.business.control.dao.util.test;

import org.mongodb.morphia.annotations.Entity;
import org.mt.business.entity.BaseEntityBean;

@Entity
public class TestEntityImpl extends BaseEntityBean implements TestEntity {

    private static final long serialVersionUID = 431749443507951476L;

    String field1;

    Integer field2;

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
