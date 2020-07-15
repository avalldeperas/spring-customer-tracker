package com.avalldeperas.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avalldeperas.springdemo.entity.Customer;

import java.sql.*;

/**
 * Servlet implementation class TestDBServlet
 */
@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = "springstudent";
		String password = "springstudent";
		
		String jdbcURL = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false"; // serverTimezone=UTC
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			PrintWriter out = response.getWriter();
			
			out.println("Connecting to db: " + jdbcURL);
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(jdbcURL, user, password);
			
			out.println("Connection successful!");
			
			Customer customer = new Customer();
			customer.setFirstName("Albert");
			System.out.println(customer);
			
			connection.close();		
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

}
