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
	TestEntity entity = null;

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
	Collection<TestEntity> entities = null;

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

	for (TestEntity entity : entities) {
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
	TestEntity notFoundEntity = dao.findById(id);
	assertNull(notFoundEntity);

	TestEntity entity = generateTestEntity();
	dao.save(entity);
	assertNotNull(entity.getId());

	TestEntity foundEntity = dao.findById(entity.getId());
	assertNotNull(foundEntity);
	assertNotNull(foundEntity.getId());
	assertEquals(entity.getId(), foundEntity.getId());
    }

    @Test
    public void testFindByIds() {
	AbstractDAOTest.cleanUp();

	boolean thrown = false;
	try {
	    dao.findByIds(null);
	} catch (IllegalArgumentException e) {
	    thrown = true;
	}
	assertTrue(thrown);

	List<TestEntity> foundEntites;

	Collection<Object> ids = new ArrayList<>();
	foundEntites = dao.findByIds(ids);
	assertTrue(foundEntites.isEmpty());

	ids.add(ObjectId.get());
	ids.add(ObjectId.get());
	foundEntites = dao.findByIds(ids);
	assertTrue(foundEntites.isEmpty());

	Collection<TestEntity> entities = new LinkedList<>();
	entities.add(generateTestEntity());
	entities.add(generateTestEntity());
	dao.save(entities);

	foundEntites.clear();

	for (TestEntity e : entities) {
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

	Collection<TestEntity> entities = new LinkedList<>();
	entities.add(generateTestEntity());
	entities.add(generateTestEntity());
	dao.save(entities);

	count = dao.getCount();
	assertEquals(2, count);
    }

    @Test
    public void testRefresh() {
	TestEntity entity = null;

	boolean thrown = false;
	try {
	    dao.refresh(entity);
	} catch (IllegalArgumentException e) {
	    thrown = true;
	}
	assertTrue(thrown);

	entity = generateTestEntity();
	dao.save(entity);

	ObjectId originalId = entity.getId();
	String originalFiled1 = entity.getField1();
	Integer originalField2 = entity.getField2();

	entity.setField1(null);
	entity.setField2(null);

	entity = dao.refresh(entity);

	assertNotNull(entity);
	assertEquals(originalId, entity.getId());
	assertEquals(originalFiled1, entity.getField1());
	assertEquals(originalField2, entity.getField2());
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

	TestEntity entity = generateTestEntity();
	dao.save(entity);

	TestEntity foundEntity;

	foundEntity = dao.findById(entity.getId());
	assertNotNull(foundEntity);

	deleteResult = dao.deleteById(entity.getId());
	assertTrue(deleteResult);

	foundEntity = dao.findById(entity.getId());
	assertNull(foundEntity);
    }

    @Test
    public void testDeleteByIds() {
	Collection<TestEntity> entities = null;
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

	ids.add(ObjectId.get());
	ids.add(ObjectId.get());
	deleteResult = dao.deleteByIds(ids);
	assertEquals(0, deleteResult);

	entities = new ArrayList<>();
	entities.add(generateTestEntity());
	entities.add(generateTestEntity());
	dao.save(entities);

	ids.clear();
	for (TestEntity e : entities) {
	    ids.add(e.getId());
	}

	List<TestEntity> foundEntities;

	foundEntities = dao.findByIds(ids);
	assertEquals(2, foundEntities.size());

	deleteResult = dao.deleteByIds(ids);
	assertEquals(2, deleteResult);

	foundEntities = dao.findByIds(ids);
	assertEquals(0, foundEntities.size());
    }

    private TestEntity generateTestEntity() {
	TestEntity testEntity = new TestEntityImpl();

	testEntity.setField1(UUID.randomUUID().toString());

	Random generator = new Random();
	int i = generator.nextInt(100) + 1;
	testEntity.setField2(i);

	return testEntity;
    }


}
