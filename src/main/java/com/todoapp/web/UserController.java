package com.todoapp.web;

import java.io.IOException;

import javax.imageio.spi.RegisterableService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todoapp.dao.UserDao;
import com.todoapp.model.User;

@WebServlet("/register")
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static UserDao userDao;
	
	
	public void init() {
		userDao = new UserDao();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// register user
		register(req, res);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// register user
		res.sendRedirect("register/register.jsp");
	}
	
	// service layer code is included here only 
	// we did not create another layer for service
	private void register(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		User employee = new User();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setUsername(username);
		employee.setPassword(password);
		
		try {
			int result = userDao.registerEmployee(employee);
			if(result == 1) {
				req.setAttribute("NOTIFICATION", "User registered successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("register/register.jsp");
		rd.forward(req, res);
	}
}
