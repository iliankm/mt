package org.test.business.control.dao.util.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.test.business.control.dao.util.MongoDB;

public class AbstractDAOTest {

    TestDAO dao;

    @BeforeClass
    @AfterClass
    public static void cleanUp() {
	Datastore ds = MongoDB.instance().getDatastore();
	Query<TestEntityImpl> query = ds.find(TestEntityImpl.class);
	for (TestEntityImpl e : query.asList()) {
	    ds.delete(TestEntityImpl.class, e.getId());
	}
    }

    @Before
    public void setUp() throws Exception {
	dao = new TestDAO();
    }

    @Test
    public void testSaveEI() {
	TestEntityImpl entity = null;

	boolean thrown = false;
	try {
	    dao.save(entity);
	} catch (IllegalArgumentException e) {
	    thrown = true;
	}
	assertTrue(thrown);

	entity = generateTestEntity();
	dao.save(entity);

	assertNotNull(entity.getId());
    }

    @Test
    public void testSaveCollectionOfEI() {
	Collection<TestEntityImpl> entities = null;

	boolean thrown = false;
	try {
	    dao.save(entities);
	} catch (IllegalArgumentException e) {
	    thrown = true;
	}
	assertTrue(thrown);

	entities = new LinkedList<>();
	entities.add(generateTestEntity());
	entities.add(generateTestEntity());

	dao.save(entities);

	for (TestEntityImpl entity : entities) {
	    assertNotNull(entity.getId());
	}
    }

    @Test
    public void testFindById() {
	boolean thrown = false;
	try {
	    dao.findById(null);
	} catch (IllegalArgumentException e) {
	    thrown = true;
	}
	assertTrue(thrown);

	ObjectId id = ObjectId.get();
	TestEntityImpl notFoundEntity = dao.findById(id);
	assertNull(notFoundEntity);

	TestEntityImpl entity = generateTestEntity();
	dao.save(entity);
	assertNotNull(entity.getId());

	TestEntityImpl foundEntity = dao.findById(entity.getId());
	assertNotNull(foundEntity);
	assertNotNull(foundEntity.getId());
	assertEquals(entity.getId(), foundEntity.getId());
    }

    @Test
    public void testFindByIds() {
	boolean thrown = false;
	try {
	    dao.findByIds(null);
	} catch (IllegalArgumentException e) {
	    thrown = true;
	}
	assertTrue(thrown);

	List<TestEntityImpl> foundEntites;

	Collection<Object> ids = new ArrayList<>();
	foundEntites = dao.findByIds(ids);
	assertTrue(foundEntites.isEmpty());

	ids.add(ObjectId.get());
	ids.add(ObjectId.get());
	foundEntites = dao.findByIds(ids);
	assertTrue(foundEntites.isEmpty());

	Collection<TestEntityImpl> entities = new LinkedList<>();
	entities.add(generateTestEntity());
	entities.add(generateTestEntity());
	dao.save(entities);

	foundEntites.clear();

	for (TestEntityImpl e : entities) {
	    ids.add(e.getId());
	}

	foundEntites = dao.findByIds(ids);

	assertNotNull(foundEntites);
	assertEquals(entities.size(), foundEntites.size());

    }

    @Test
    public void testGetCount() {
	AbstractDAOTest.cleanUp();

	long count;

	count = dao.getCount();
	assertEquals(0, count);

	Collection<TestEntityImpl> entities = new LinkedList<>();
	entities.add(generateTestEntity());
	entities.add(generateTestEntity());
	dao.save(entities);

	count = dao.getCount();
	assertEquals(2, count);
    }

    @Test
    public void testDeleteById() {
	boolean thrown = false;
	try {
	    dao.deleteById(null);
	} catch (IllegalArgumentException e) {
	    thrown = true;
	}
	assertTrue(thrown);

	boolean deleteResult;

	deleteResult = dao.deleteById(ObjectId.get());
	assertFalse(deleteResult);

	TestEntityImpl entity = generateTestEntity();
	dao.save(entity);

	TestEntityImpl foundEntity;

	foundEntity = dao.findById(entity.getId());
	assertNotNull(foundEntity);

	deleteResult = dao.deleteById(entity.getId());
	assertTrue(deleteResult);

	foundEntity = dao.findById(entity.getId());
	assertNull(foundEntity);
    }

    @Test
    public void testDeleteByIds() {
	Collection<TestEntityImpl> entities = null;
	Collection<Object> ids;

	boolean thrown = false;
	try {
	    dao.deleteByIds(entities);
	} catch (IllegalArgumentException e) {
	    thrown = true;
	}
	assertTrue(thrown);

	int deleteResult;

	ids = new ArrayList<>();
	deleteResult = dao.deleteByIds(ids);
	assertEquals(0, deleteResult);

	ids.add(ObjectId.get().toHexString());
	ids.add(ObjectId.get().toHexString());
	deleteResult = dao.deleteByIds(ids);
	assertEquals(0, deleteResult);

	entities = new ArrayList<>();
	entities.add(generateTestEntity());
	entities.add(generateTestEntity());
	dao.save(entities);

	ids.clear();
	for (TestEntityImpl e : entities) {
	    ids.add(e.getId());
	}

	List<TestEntityImpl> foundEntities;

	foundEntities = dao.findByIds(ids);
	assertEquals(2, foundEntities.size());

	deleteResult = dao.deleteByIds(ids);
	assertEquals(2, deleteResult);

	foundEntities = dao.findByIds(ids);
	assertEquals(0, foundEntities.size());
    }

    private TestEntityImpl generateTestEntity() {
        TestEntityImpl testEntity = new TestEntityImpl();

	testEntity.setField1(UUID.randomUUID().toString());

	Random generator = new Random();
	int i = generator.nextInt(100) + 1;
	testEntity.setField2(i);

	return testEntity;
    }


}
