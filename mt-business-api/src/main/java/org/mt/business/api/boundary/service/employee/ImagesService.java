package org.mt.business.api.boundary.service.employee;

import java.io.InputStream;
import java.nio.file.Path;

public interface ImagesService {

    /**
     *
     * @param employeeId
     *            - Employee id
     * @param fileName
     *            - image file name
     * @return - Path of the saved file
     */
    Path getImage(String employeeId, String fileName);

    /**
     * Save given input stream to file in the application upload folder.
     *
     * Directory structure is ensured and missing folders are created if not
     * exist.
     *
     * @param employeeId
     *            - Employee id
     * @param inputStream
     *            - image input stream
     * @param fileName
     *            - original filename
     * @return - Path of the saved file
     */
    Path save(String employeeId, InputStream in, String fileName);

}
