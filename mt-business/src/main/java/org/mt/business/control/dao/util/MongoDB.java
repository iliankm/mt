package org.mt.business.control.dao.util;

import java.net.UnknownHostException;
import java.util.logging.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

/**
 * Singleton for obtaining mongodb connection and Datastore
 */
public class MongoDB {

    private static final Logger LOG = Logger.getLogger(MongoDB.class.getName());

    private static final MongoDB INSTANCE = new MongoDB();

    private static final String DB_HOST = "127.0.0.1";

    private static final int DB_PORT = 27017;

    private static final int SOCKET_TIMEOUT = 60000;

    private static final int CONNECT_TIMEOUT = 1200000;

    private static final String DB_NAME = "mtdb";

    private final Datastore datastore;

    private MongoDB() {

	MongoClientOptions mongoOptions = MongoClientOptions.builder()
		.socketTimeout(SOCKET_TIMEOUT).connectTimeout(CONNECT_TIMEOUT).build();
	MongoClient mongoClient;
	try {
	    mongoClient = new MongoClient(
		    new ServerAddress(DB_HOST, DB_PORT), mongoOptions);
	} catch (UnknownHostException e) {
	    throw new RuntimeException("Error initializing MongoDB", e);
	}

	mongoClient.setWriteConcern(WriteConcern.SAFE);

	datastore = new Morphia().mapPackage("org.mt.business.entity")
		.createDatastore(mongoClient, DB_NAME);

	datastore.ensureIndexes();

	LOG.info("Connection to database '" + DB_NAME + "' initialized");
    }

    public static MongoDB instance() {

	return INSTANCE;
    }

    /**
     * Creating the mongo connection is expensive - (re)use a singleton for <br>
     * performance reasons <br>
     * Both the underlying Java driver and Datastore are thread safe
     * @return
     */
    public Datastore getDatastore() {

	return datastore;
    }

}
