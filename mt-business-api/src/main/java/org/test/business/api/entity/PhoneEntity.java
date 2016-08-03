package org.test.business.api.entity;

import org.test.business.api.entity.util.PhoneType;

public interface PhoneEntity extends Entity {

    public PhoneType getType();

    public String getPhone();
}
