package org.mt.web.resource.messages;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringEscapeUtils;
import org.mt.web.resource.RestEndpoints;

@Path(RestEndpoints.MESSAGES)
public class MessagesResource {

    @GET
    public Response getMessages() {

        final ResourceBundle resourceBundle = ResourceBundle.getBundle("resources", Locale.ENGLISH);

        final Map<String, String> messages = resourceBundle.keySet().stream()
                .collect(Collectors.toMap(k -> k, k -> StringEscapeUtils.escapeJava(resourceBundle.getString(k))));

        return Response.status(Status.OK).type(MediaType.APPLICATION_JSON_TYPE).entity(messages).build();

    }

}
