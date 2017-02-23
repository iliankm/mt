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
        PhoneArgument other = (PhoneArgument) obj;
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
