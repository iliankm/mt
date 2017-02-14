package org.mt.web.resource.employees;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.mt.business.api.boundary.service.employee.ImagesService;
import org.mt.business.api.exception.ResourceNotFoundException;
import org.mt.web.resource.RestEndpoints;

@Path(RestEndpoints.EMPLOYEES)
public class ImagesResource {

    private static final int MAX_SIZE = 10 * 1024 * 1024;

    private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("jpeg", "jpg", "png", "bmp", "tiff", "gif");

    @Inject
    ImagesService imageService;

    @GET
    @Path(RestEndpoints.EMPLOYEE_IMAGE)
    @Produces("image/*")
    public Response get(@PathParam("id") String id, @PathParam("name") String name) {

	final java.nio.file.Path imagePath = imageService.getImage(id, name);

	final File imageFile = imagePath.toFile();

	final String contentType = new MimetypesFileTypeMap().getContentType(imageFile);

	return Response.ok(imageFile).type(contentType)
		.header("Content-Disposition", "attachment; filename=\"" + name + "\"").build();
    }

    @DELETE
    @Path(RestEndpoints.EMPLOYEE_IMAGE)
    public Response delete(@PathParam("id") String id, @PathParam("name") String name) {

	final java.nio.file.Path imagePath = imageService.getImage(id, name);

	final File imageFile = imagePath.toFile();

	if (!imageFile.exists() || !imageFile.isFile()) {
	    throw new ResourceNotFoundException();
	}
	else {
	    imageFile.delete();

	    return Response.status(Status.OK).type(MediaType.APPLICATION_JSON_TYPE).entity(name).build();
	}
    }

    @POST
    @Path(RestEndpoints.EMPLOYEE_IMAGES)
    @Consumes("multipart/form-data")
    public Response upload(@PathParam("id") String id, MultipartFormDataInput input) {

	final Map<String, List<InputPart>> uploadForm = input.getFormDataMap();

	final List<InputPart> inputParts = uploadForm.get("upload_file");

	final List<String> fileNames = new ArrayList<>();

	inputParts.forEach(inputPart -> {

	    // get file name
	    final String fileName = getFileName(inputPart.getHeaders());
	    final String ext = fileName.toLowerCase().substring(fileName.lastIndexOf('.') + 1);

	    // check for file type
	    if (!ALLOWED_FILE_TYPES.contains(ext)) {
		throw new RuntimeException("Unsupported filetype: " + ext);
	    }

	    try {
		final InputStream inputStream = inputPart.getBody(InputStream.class, null);

		final byte[] bytes = IOUtils.toByteArray(inputStream);

		// check for max allowed file size
		if (bytes.length > MAX_SIZE) {
		    throw new RuntimeException(
			    "Uploaded file size of " + bytes.length + " excedes max allowed " + MAX_SIZE);
		}

		// save stream to file
		java.nio.file.Path p = imageService.save(id, new ByteArrayInputStream(bytes), fileName);

		fileNames.add(p.getFileName().toString());

	    } catch (IOException e) {
		throw new RuntimeException(e);
	    }
	});

	return Response.status(Status.OK).type(MediaType.APPLICATION_JSON_TYPE).entity(String.join(",", fileNames))
		.build();
    }

    /**
     * Extract file name from part headers
     *
     * @param header
     * @return file name or unknown
     */
    private String getFileName(MultivaluedMap<String, String> header) {

	final String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

	for (String filename : contentDisposition) {

	    if (filename.trim().startsWith("filename")) {

		final String[] name = filename.split("=");

		final String finalFileName = name[1].trim().replaceAll("\"", "");

		return finalFileName;
	    }
	}

	return "unknown";
    }

}
