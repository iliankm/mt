package org.mt.business.api.domain.employee;

import org.mt.business.api.domain.DomainObject;

public interface Phone extends DomainObject {

    public PhoneType getType();

    public String getPhone();
}
