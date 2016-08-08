package org.mt.business.api.boundary;

import org.mt.business.api.entity.Entity;

public interface EntityInstancer {

    public <EI extends Entity> EI create(Class<EI> entityInterfaceClazz);
}
