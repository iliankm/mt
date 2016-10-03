package org.mt.business.domain;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Version;
import org.mt.business.api.domain.IdentifiableObject;

/**
 * BaseEntityBean with auto-generated (by persistence framework) fields
 *
 */
public abstract class BaseEntityBean implements IdentifiableObject {

    private static final long serialVersionUID = 2628771360357245371L;

    @Id
    private ObjectId id;

    @Version
    private long version;

    private Date creationDate;

    private Date lastModifiedDate;

    @Override
    public String getId() {

	return id.toHexString();
    }

    @Override
    public Date getCreationDate() {

	return new Date(creationDate.getTime());
    }

    @Override
    public Date getLastModifiedDate() {

	return new Date(lastModifiedDate.getTime());
    }

    @PrePersist
    private void prePersist() {

	this.creationDate = (creationDate == null) ? new Date() : creationDate;

	this.lastModifiedDate = (lastModifiedDate == null) ? creationDate : new Date();
    }

}
