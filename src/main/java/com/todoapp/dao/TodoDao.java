package com.todoapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.todoapp.model.Todo;

public interface TodoDao {
	
	void insert(Todo todo) throws SQLException;
	
	Todo selectTodo(long todoId);
	
	List<Todo> selectAllTodos();
	
	boolean deleteTodo(int id) throws SQLException;
	
	boolean update(Todo todo) throws SQLException;
	
}
