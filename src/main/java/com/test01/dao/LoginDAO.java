package com.test01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.test01.dto.LoginDTO;


public class LoginDAO {
	
public LoginDAO() {
		
	}
	
private static LoginDAO instance = new LoginDAO();
	
	public static LoginDAO getInstance() {
		return instance;
	}
	
	Connection getConnection() {
		Connection conn=null;
		Context initContext;
		
		try {
			initContext=new InitialContext();
			DataSource ds=(DataSource)initContext.lookup("java:/comp/env/jdbc/Oracle11g");
			conn=ds.getConnection();
		}catch(NamingException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public LoginDTO getMember(String user_id) {
		LoginDTO login=null;
		
		Connection conn=null;
		String sql="select * from users where user_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				login=new LoginDTO();
				login.setUser_index(rs.getInt("user_index"));
				login.setUser_id(rs.getString("user_id"));
				login.setUser_pw(rs.getString("user_pwd"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return login;
	}
	
	public int userCheck(String id,String pwd) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result = 1;
		String dbpwd = null;
		String sql="select * from users where user_id=?";
		try {
			
			conn= getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dbpwd = rs.getString("user_pwd");
				if(dbpwd.equals(pwd)) {
					result=1;	//로그인 성공
				}else {
					result=-1;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
