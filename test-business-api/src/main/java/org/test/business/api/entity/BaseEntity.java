package org.test.business.api.entity;

import java.util.Date;

import org.bson.types.ObjectId;

public interface BaseEntity extends Entity {

    public ObjectId getId();

    public Date getCreationDate();

    public Date getLastModifiedDate();

}