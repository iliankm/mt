package org.test.business.entity.morphia;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Version;

public abstract class BaseEntityBean implements Serializable {

    private static final long serialVersionUID = 2628771360357245371L;

    @Id
    private ObjectId id;

    @Version
    private long version;

    private Date creationDate;

    private Date lastModifiedDate;

    public ObjectId getId() {
	return id;
    }
    
    public Date getCreationDate() {
	return creationDate;
    }

    public Date getLastModifiedDate() {
	return lastModifiedDate;
    }

    @PrePersist
    public void prePersist() {
	this.creationDate = (creationDate == null) ? new Date() : creationDate;

	this.lastModifiedDate = (lastModifiedDate == null) ? creationDate : new Date();
    }

}
