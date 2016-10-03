package org.mt.business.api.domain.employee;

import org.mt.business.api.domain.DomainObject;

public interface Address extends DomainObject {

    public String getStreet();

    public String getZip();

    public String getCity();

    public String getCountry();

}
