package org.test.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.test.business.api.boundary.EntityInstancer;
import org.test.business.api.boundary.service.EmployeeService;
import org.test.business.api.entity.AddressEntity;
import org.test.business.api.entity.EmployeeEntity;

@WebServlet("/hello")
public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 7047447390031215877L;

    @Inject
    EntityInstancer entityInstancer;

    @Inject
    EmployeeService employeeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {

	resp.setContentType("text/html");
	resp.setBufferSize(8192);
        PrintWriter out = resp.getWriter();

        out.println("<div>Hello from servlet!</div>");

        EmployeeEntity emp1 = entityInstancer.create(EmployeeEntity.class);
        //emp1.setName("Petko Voivoda");

        AddressEntity adr1 = entityInstancer.create(AddressEntity.class);
        //adr1.setCity("Varna");

        EmployeeEntity emp2 = entityInstancer.create(EmployeeEntity.class);
        //emp2.setName("Vasil Levski");

        AddressEntity adr2 = entityInstancer.create(AddressEntity.class);
        //adr2.setCity("Karlovo");

        out.println("<div>emp1:" + emp1.getName() + "</div>");

        out.println("<div>adr1:" + adr1.getCity() + "</div>");

        out.println("<div>emp2:" + emp2.getName() + "</div>");

        out.println("<div>adr2:" + adr2.getCity() + "</div>");

        out.println("<div>test:" + employeeService.test("test") + "</div>");
        out.println("<div>test:" + employeeService.test("test") + "</div>");
        out.println("<div>test2:" + employeeService.test("test2") + "</div>");
        out.println("<div>test2:" + employeeService.test("test2") + "</div>");

        EmployeeEntity emp3 = entityInstancer.create(EmployeeEntity.class);
        //emp3.setName("Iliyan");
        employeeService.save(emp3);

        Object id = emp3.getId();

        employeeService.findById(id);

        employeeService.findById(id);

        employeeService.findById(id);

        employeeService.findById(id);

        out.close();
    }




}
