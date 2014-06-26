package org.test.business.api.boundary;

import org.test.business.api.entity.BaseEntity;

public interface EntityInstancer<EI extends BaseEntity> {

    public EI create();
}
