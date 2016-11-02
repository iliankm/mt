package org.mt.business.api.boundary.service.employee.argument;

import javax.validation.constraints.NotNull;

import org.mt.business.api.domain.employee.PhoneType;

public class PhoneArgument {

    @NotNull(message = "validation_phone_type_is_mandatory")
    private PhoneType type;

    @NotNull(message = "validation_phone_is_mandatory")
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
