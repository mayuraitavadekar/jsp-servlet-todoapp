package com.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.todoapp.model.User;
import com.todoapp.utils.JDBCUtils;

public class UserDao {
	
	public int registerEmployee(User employee) {
		
		String INSERT_USERS_SQL = "INSERT INTO users" +
	            "  (first_name, last_name, username, password) VALUES " +
	            " (?, ?, ?, ?);";
		
		int result = 0;
		
		try {
			Connection conn = JDBCUtils.getConnection();
			
			PreparedStatement psmt = conn.prepareStatement(INSERT_USERS_SQL);
			psmt.setString(1, employee.getFirstName());
			psmt.setString(2, employee.getLastName());
			psmt.setString(3, employee.getUsername());
			psmt.setString(4, employee.getPassword());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			JDBCUtils.printSQLException(e);
		}
		
		return result;
		
	}
	
	
}
