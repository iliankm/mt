package org.mt.business.api.domain;

import java.util.Date;

public interface IdentifiableObject extends DomainObject {

    public String getId();

    public Date getCreationDate();

    public Date getLastModifiedDate();

}