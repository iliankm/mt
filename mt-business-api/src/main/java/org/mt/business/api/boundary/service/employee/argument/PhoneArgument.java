package org.mt.business.api.boundary.service.employee.argument;

import org.mt.business.api.domain.employee.Phone;
import org.mt.business.api.domain.employee.PhoneType;

public class PhoneArgument implements Phone {

    private static final long serialVersionUID = 7395043944392104460L;

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

    @Override
    public PhoneType getType() {

	return type;
    }

    @Override
    public String getPhone() {

	return phone;
    }
}
