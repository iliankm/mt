package org.test.business.api.entity;

import java.util.Date;

import org.bson.types.ObjectId;

public interface BaseEntity {

    public ObjectId getId();

    public Date getCreationDate();

    public Date getLastModifiedDate();

}