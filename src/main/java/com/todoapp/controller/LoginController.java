package com.todoapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.todoapp.dao.LoginDao;
import com.todoapp.model.LoginBean;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;
	
	public void init() {
		loginDao = new LoginDao();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.sendRedirect("login/login.jsp");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		authenticate(req, res);
	}

	private void authenticate(HttpServletRequest req, HttpServletResponse res) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		
		try {
			boolean status = loginDao.validate(loginBean);
			if(status) {
				System.out.println("login successfull");
				RequestDispatcher rd = req.getRequestDispatcher("todo/todo-list.jsp");
				rd.forward(req, res);
			}
			else {
				res.sendRedirect("Error.jsp");
			}
		} catch (Exception e) {
			System.out.println("status is false. error in loggin in.");
			e.printStackTrace();
		}
	}
}
