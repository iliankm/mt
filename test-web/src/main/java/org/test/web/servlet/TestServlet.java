package org.test.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.test.business.api.boundary.service.EmployeeService;

@WebServlet("/hello")
public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 7047447390031215877L;

    @Inject
    EmployeeService employeeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {

	resp.setContentType("text/html");
	resp.setBufferSize(8192);
        PrintWriter out = resp.getWriter();

        out.println("<div>Hello from servlet!</div>");

        out.println("<div>test:" + employeeService.test("test") + "</div>");
        out.println("<div>test:" + employeeService.test("test") + "</div>");
        out.println("<div>test2:" + employeeService.test("test2") + "</div>");
        out.println("<div>test2:" + employeeService.test("test2") + "</div>");

        out.close();
    }




}
