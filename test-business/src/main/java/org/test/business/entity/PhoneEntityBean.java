package org.test.business.entity;

import javax.validation.constraints.NotNull;
import org.mongodb.morphia.annotations.Embedded;
import org.test.business.api.entity.PhoneEntity;
import org.test.business.api.entity.util.PhoneType;

@Embedded
public class PhoneEntityBean implements PhoneEntity {

    @NotNull(message = "validation_phone_type_is_mandatory")
    private PhoneType type;

    @NotNull(message = "validation_phone_is_mandatory")
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
