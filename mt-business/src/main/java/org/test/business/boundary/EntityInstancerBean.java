package org.test.business.boundary;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.test.business.api.boundary.EntityInstancer;
import org.test.business.api.entity.Entity;

public class EntityInstancerBean implements EntityInstancer {

    @Inject
    private Instance<Entity> entityProvider;

    @Override
    public <EI extends Entity> EI create(Class<EI> entityInterfaceClazz) {

	return entityProvider.select(entityInterfaceClazz).get();
    }
}
