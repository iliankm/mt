package org.mt.web.resource;

public interface RestEndpoints {

    String APP_BASE_PATH = "resources";

    String MESSAGES = "messages";

    String EMPLOYEES = "employees";

    String EMPLOYEE = "{id}";

    String EMPLOYEE_ADDRESS = "{id}/address";

    String EMPLOYEE_PHONES = "{id}/phones";

    String UPLOAD = "{id}/upload";

}
