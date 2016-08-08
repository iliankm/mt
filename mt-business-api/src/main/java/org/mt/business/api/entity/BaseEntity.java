package org.mt.business.api.entity;

import java.util.Date;

public interface BaseEntity extends Entity {

    public String getId();

    public Date getCreationDate();

    public Date getLastModifiedDate();

}