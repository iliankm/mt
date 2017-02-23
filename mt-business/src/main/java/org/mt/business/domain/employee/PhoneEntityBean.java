package org.mt.business.domain.employee;

import javax.validation.constraints.NotNull;

import org.mongodb.morphia.annotations.Embedded;
import org.mt.business.api.domain.employee.Phone;
import org.mt.business.api.domain.employee.PhoneType;

@Embedded
public class PhoneEntityBean implements Phone {

    private static final long serialVersionUID = 4329488264002771374L;

    @NotNull(message = "validation_phone_type_is_mandatory")
    private PhoneType type;

    @NotNull(message = "validation_phone_is_mandatory")
    private String phone;

    /**
     * Default no-args constructor needed by persistence framework
     */
    private PhoneEntityBean() {
    }

    public PhoneEntityBean(PhoneType type, String phone) {

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
        PhoneEntityBean other = (PhoneEntityBean) obj;
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
