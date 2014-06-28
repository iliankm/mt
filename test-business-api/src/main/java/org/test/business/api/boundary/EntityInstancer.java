package org.test.business.api.boundary;

import org.test.business.api.entity.Entity;

public interface EntityInstancer<EI extends Entity> {

    public EI create(Class<EI> entityInterfaceClazz);
}
