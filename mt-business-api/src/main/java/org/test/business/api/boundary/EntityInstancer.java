package org.test.business.api.boundary;

import org.test.business.api.entity.Entity;

public interface EntityInstancer {

    public <EI extends Entity> EI create(Class<EI> entityInterfaceClazz);
}
