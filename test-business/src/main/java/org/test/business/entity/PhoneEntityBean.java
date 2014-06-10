package org.test.business.entity;

import org.mongodb.morphia.annotations.Embedded;
import org.test.business.api.entity.PhoneEntity;
import org.test.business.api.entity.util.PhoneType;

@Embedded
public class PhoneEntityBean implements PhoneEntity {

    private PhoneType type;

    private String phone;

    @Override
    public PhoneType getType() {
	return type;
    }

    @Override
    public void setType(PhoneType type) {
	this.type = type;
    }

    @Override
    public String getPhone() {
	return phone;
    }

    @Override
    public void setPhone(String phone) {
	this.phone = phone;
    }

}
