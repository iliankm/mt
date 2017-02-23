package org.mt.business.util.logging;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerFactory {

    @Produces
    public static Logger createLogger(InjectionPoint injectionPoint) {

        String name = injectionPoint.getMember().getDeclaringClass().getName();
        Logger logger = Logger.getLogger(name);

        logger.setLevel(Level.ALL);

        return logger;
    }
}
