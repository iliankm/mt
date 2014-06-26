package org.test.business.boundary;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.test.business.api.boundary.EntityInstancer;
import org.test.business.api.entity.BaseEntity;

public class EntityInstancerBean<EI extends BaseEntity> implements EntityInstancer<EI> {

    @Inject
    private Instance<EI> entityProvider;

    @Override
    public EI create() {
	return entityProvider.get();
    }

}
