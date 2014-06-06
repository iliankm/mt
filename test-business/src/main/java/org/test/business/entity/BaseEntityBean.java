package org.test.business.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Version;
import org.test.business.api.entity.BaseEntity;

public abstract class BaseEntityBean implements BaseEntity {

    @Id
    private ObjectId id;

    @Version
    private long version;

    private Date creationDate;

    private Date lastModifiedDate;

    @Override
    public ObjectId getId() {
	return id;
    }

    @Override
    public Date getCreationDate() {
	return creationDate;
    }

    @Override
    public Date getLastModifiedDate() {
	return lastModifiedDate;
    }

    @PrePersist
    public void prePersist() {
	this.creationDate = (creationDate == null) ? new Date() : creationDate;

	this.lastModifiedDate = (lastModifiedDate == null) ? creationDate : new Date();
    }

}
