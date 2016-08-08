package org.mt.business.api.entity;

import org.mt.business.api.entity.util.PhoneType;

public interface PhoneEntity extends Entity {

    public PhoneType getType();

    public String getPhone();
}
