package com.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.todoapp.model.Todo;
import com.todoapp.utils.JDBCUtils;

public class TodoDaoImpl implements TodoDao {
	
	private static final String INSERT_TODOS = "INSERT INTO todos" + 
			" (title, username, description, target_date, is_done) VALUES " +
			"(?, ?, ?, ?, ?);";
	
	private static final String SELECT_TODO_BY_ID = "select id,title,username,description,target_date,is_done from todos where id =?";
    private static final String SELECT_ALL_TODOS = "select * from todos";
    private static final String DELETE_TODO_BY_ID = "delete from todos where id = ?;";
    private static final String UPDATE_TODO = "update todos set title = ?, username= ?, description =?, target_date =?, is_done = ? where id = ?;";
	
	
	 

	@Override
	public void insert(Todo todo) throws SQLException {

		System.out.println(INSERT_TODOS);
		
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement psmt = conn.prepareStatement(INSERT_TODOS);
			psmt.setString(1, todo.getTitle());
			psmt.setString(2, todo.getUsername());
			psmt.setString(3, todo.getDescription());
			psmt.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
			psmt.setBoolean(5, todo.getStatus());
			
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
	}

	@Override
	public Todo selectTodo(long todoId) {
		
		Todo todo = null;
		
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement psmt = conn.prepareStatement(SELECT_TODO_BY_ID);
			psmt.setLong(1, todoId);
			
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("Description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				boolean isDone = rs.getBoolean("is_done");
				
				todo = new Todo(id, title, username, description, targetDate, isDone);
				
			}
			
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		
		return todo;
	}

	@Override
	public List<Todo> selectAllTodos() {
		
		List<Todo> todos = new ArrayList<>();
		
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement psmt = conn.prepareStatement(SELECT_ALL_TODOS);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String username = rs.getString("username");
				String description = rs.getString("Description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				boolean isDone = rs.getBoolean("is_done");
				
				todos.add(new Todo(id, title, username, description, targetDate, isDone));
			}
			
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		
		return null;
	}

	@Override
	public boolean deleteTodo(int id) throws SQLException {
		
		boolean rowDeleted;
		
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement psmt = conn.prepareStatement(DELETE_TODO_BY_ID);
			psmt.setInt(1, id);
			rowDeleted = psmt.executeUpdate() > 0;
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		
		return true;
	}

	@Override
	public boolean update(Todo todo) throws SQLException {
		
		boolean rowUpdated;
		
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement psmt = conn.prepareStatement(UPDATE_TODO);
			psmt.setString(1, todo.getTitle());
			psmt.setString(2, todo.getUsername());
            psmt.setString(3, todo.getDescription());
            psmt.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
            psmt.setBoolean(5, todo.getStatus());
            psmt.setLong(6, todo.getId());
            
            rowUpdated = psmt.executeUpdate() > 0;
            
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		
		return false;
	}
	
}
