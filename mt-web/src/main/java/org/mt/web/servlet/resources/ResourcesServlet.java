package org.mt.web.servlet.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * This servlet serves javascript module that might be used for getting formatted text resource by it's key.
 *
 * All text resources are read from bundle located in mt-business-api module (resource class path folder)
 *
 */
@WebServlet("/resources.js")
public class ResourcesServlet extends HttpServlet {

    private static final long serialVersionUID = -4125128275943548132L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String resourcesJsonString = this.getResourcesJsonString();

        final String resourcesJsString = getResourcesJsToString();

        final String content = resourcesJsString.replaceFirst("@RESOURCES@", resourcesJsonString);

        try {
            resp.setContentType(MediaType.TEXT_HTML + ";charset=UTF-8");

            resp.getOutputStream().print(content);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     *
     * @return resources_locale.properties file from resources class path as a String
     * The file phisically is located in mt-business-api module
     */
    private String getResourcesJsonString() {

        final ResourceBundle resourceBundle = ResourceBundle.getBundle("resources", Locale.ENGLISH);

        final JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

        resourceBundle.keySet().forEach(key -> {
            jsonObjectBuilder.add(key, StringEscapeUtils.escapeJava(resourceBundle.getString(key)));
        });

        return jsonObjectBuilder.build().toString();
    }

    /**
     *
     * @return resources.js file from resources class path as a String
     */
    private String getResourcesJsToString() {

        final InputStream in = this.getClass().getClassLoader().getResourceAsStream("/resources.js");

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(in))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
