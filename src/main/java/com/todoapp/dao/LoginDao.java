package com.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;
import com.todoapp.model.LoginBean;
import com.todoapp.utils.JDBCUtils;

public class LoginDao {
	
	public boolean validate(LoginBean loginBean) {
		
		boolean status = false;
		
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement psmt = conn.prepareStatement("select * from users where username = ? and password = ?");
			psmt.setString(1, loginBean.getUsername());
			psmt.setString(2, loginBean.getPassword());
			
			ResultSet rs = psmt.executeQuery();
			
			status = rs.next();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("error in finding user in DB");
			JDBCUtils.printSQLException(e);
		}
		
		return status;
	}
	
}
