package org.test.business.control.dao.morphia.util;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import org.bson.types.ObjectId;

public class MongoDBUtils {

    public static ObjectId objectIdFromString(String id) {

        return new ObjectId(id);
    }

    public static Collection<ObjectId> objectIdsFromStrings(Collection<String> ids) {

        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }

        Collection<ObjectId> objectIds = new LinkedList<>();

        for (String s : ids) {
            objectIds.add(objectIdFromString(s));
        }

        return objectIds;
    }
}
