package org.mt.business.control.repository.employee;

import javax.enterprise.inject.Produces;

import org.mongodb.morphia.Datastore;

public class MongoDBDatastoreFactory {

    @Produces
    public static Datastore createDatastore() {

	return MongoDB.instance().getDatastore();

    }

}
