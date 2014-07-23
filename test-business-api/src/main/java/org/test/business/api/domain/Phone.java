package org.test.business.api.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.test.business.api.domain.util.PhoneType;

public class Phone implements Serializable {

    private static final long serialVersionUID = 2436097481823432962L;

    @NotNull(message = "validation_phone_type_is_mandatory")
    private PhoneType type;

    @NotNull(message = "validation_phone_is_mandatory")
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((phone == null) ? 0 : phone.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Phone other = (Phone) obj;
	if (phone == null) {
	    if (other.phone != null)
		return false;
	} else if (!phone.equals(other.phone))
	    return false;
	if (type != other.type)
	    return false;
	return true;
    }

}
