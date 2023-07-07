package com.todoapp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todoapp.dao.TodoDao;
import com.todoapp.dao.TodoDaoImpl;
import com.todoapp.model.Todo;

@WebServlet("/")
public class TodoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	TodoDao todoDao;
	
	public void init() {
		todoDao = new TodoDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String action = req.getServletPath();
		System.out.println("action = " + action);
		
		try {
			switch (action) {
			
			case "/new": {
				showNewForm(req, res);
				break;
				
			}
			
			case "/insert": {
				insertTodo(req, res);
				break;
			}
			
			case "/edit": {
				showEditForm(req, res);
				break;
			}
			
			case "/update": {
				updateTodo(req, res);
				break;
			}
			
			case "/delete": {
				deleteTodo(req, res);
				break;
			}
			
			case "/list": {
				listTodo(req, res);
				break;
			}
			
			default:
				RequestDispatcher rd = req.getRequestDispatcher("login/login.jsp");
				rd.forward(req, res);
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void deleteTodo(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		todoDao.deleteTodo(id);
		res.sendRedirect("list");
		
	}

	private void listTodo(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {
		
		List<Todo> todos = todoDao.selectAllTodos();
		req.setAttribute("listTodo", todos);
		
		RequestDispatcher rd = req.getRequestDispatcher("todo/todo-list.jsp");
		rd.forward(req, res);
	}

	private void updateTodo(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		String title = req.getParameter("title");
		String username = req.getParameter("username");
		String description = req.getParameter("description");
		LocalDate targetDate = LocalDate.parse(req.getParameter("targetDate"));
		
		boolean isDone = Boolean.valueOf(req.getParameter("isDone"));
		Todo updateTodo = new Todo(id, title, username, description, targetDate, isDone);
		
		todoDao.update(updateTodo);
		res.sendRedirect("list");
	}

	private void showEditForm(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
        Todo existingTodo = todoDao.selectTodo(id);
        RequestDispatcher rd = req.getRequestDispatcher("todo/todo-form.jsp");
        req.setAttribute("todo", existingTodo);
        rd.forward(req, res);
	}

	private void insertTodo(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
		
		String title = req.getParameter("title");
		String username = req.getParameter("username");
		String description = req.getParameter("description");
		
		boolean isDone = Boolean.valueOf(req.getParameter("isDone"));
		
		Todo todo = new Todo(title, username, description, LocalDate.now(), isDone);
		todoDao.insert(todo);
		
		res.sendRedirect("list");
		
	}

	private void showNewForm(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("todo/todo-form.jsp");
		rd.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
