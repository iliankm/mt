package org.mt.web.resource;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(RestEndpoints.APP_BASE_PATH)
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {

	final Set<Class<?>> classes = new HashSet<Class<?>>();

        classes.add(org.mt.web.resource.ExceptionMapper.class);
        classes.add(org.mt.web.resource.messages.MessagesResource.class);

        return classes;
    }

}
