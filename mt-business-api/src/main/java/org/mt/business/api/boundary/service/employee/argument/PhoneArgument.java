package org.mt.business.api.boundary.service.employee.argument;

import org.mt.business.api.domain.employee.PhoneType;

public class PhoneArgument {

    private PhoneType type;

    private String phone;

    /**
     * Default no-args constructor
     */
    private PhoneArgument() {
    }

    public PhoneArgument(PhoneType type, String phone) {

	this();
	this.type = type;
	this.phone = phone;
    }

    public PhoneType getType() {

	return type;
    }

    public String getPhone() {

	return phone;
    }
}
