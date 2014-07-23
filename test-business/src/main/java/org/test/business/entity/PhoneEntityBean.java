package org.test.business.entity;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Embedded;
import org.test.business.api.domain.util.PhoneType;

@Embedded
public class PhoneEntityBean implements Serializable {

    private static final long serialVersionUID = 4329488264002771374L;

    private PhoneType type;

    private String phone;

    public PhoneType getType() {
	return type;
    }

    public void setType(PhoneType type) {
	this.type = type;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

}
