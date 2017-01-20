package org.mt.business.boundary.service.employee;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

import org.mt.business.api.boundary.service.employee.ImagesService;
import org.mt.business.api.exception.ResourceNotFoundException;

public class ImagesServiceBean implements ImagesService {

    private static final String ENV_HOME = "HOME";

    private static final String APP_UPLOAD_DIR = "mt-upload";

    @Override
    public Path getImage(String employeeId, String fileName) {

	Objects.requireNonNull(employeeId);

	Objects.requireNonNull(fileName);

	final Path employeePath = getEmployeeImagesDirectory(employeeId);

	final Path imagePath = Paths.get(employeePath.toString(), fileName);

	if (imagePath.toFile().exists()) {
	    return imagePath;
	} else {
	    throw new ResourceNotFoundException();
	}
    }

    @Override
    public Path save(String employeeId, InputStream in, String fileName) {

	Objects.requireNonNull(employeeId);

	Objects.requireNonNull(in);

	Objects.requireNonNull(fileName);

	// get employee images dir - if there are some missing folders, they are
	// created
	final Path employeeImagesDirectory = getEmployeeImagesDirectory(employeeId);

	// target file Path
	final Path targetFilePath = Paths.get(employeeImagesDirectory.toString(),
		UUID.randomUUID().toString().concat(fileName));

	// copy InputStream to the target file
	try {
	    Files.copy(in, targetFilePath);
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}

	return targetFilePath;
    }

    /**
     *
     * @param employeeId
     *            - Employee id
     * @return - employee images directory Path. All nonexistent parent
     *         directories are created.
     */
    private Path getEmployeeImagesDirectory(String employeeId) {

	final String homePathString = System.getenv(ENV_HOME);

	if (homePathString == null || homePathString.isEmpty()) {
	    throw new RuntimeException("Missing environment variable HOME");
	}

	try {

	    final Path path = Paths.get(homePathString, APP_UPLOAD_DIR, employeeId);

	    if (!path.toFile().exists()) {
		return Files.createDirectories(path);
	    } else {
		return path;
	    }

	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

}
