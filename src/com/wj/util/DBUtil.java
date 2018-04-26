package com.wj.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	public static DBUtil util = new DBUtil();
	
	public DBUtil() {
		
	}
	
	public static DBUtil getInstance() {
		return util;
	}
	
	private String className = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String user = "weijia";
	private String password = "123456";
	private String sql = "";
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public Connection getConnection() {
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public ResultSet query(String sql,List<Object> params) {
		try {
			ps = conn.prepareStatement(sql);
			List<Object> list = new ArrayList<Object>();
			if(params != null && params.size() > 0) {
				for (int i = 0; i < params.size(); i++) {
					ps.setObject(i+1, params.get(i));
				}
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void update(String sql,List<Object> params) {
		try {
			ps = conn.prepareStatement(sql);
			List<Object> list = new ArrayList<Object>();
			if(params != null && params.size() > 0) {
				for (int i = 0; i < params.size(); i++) {
					ps.setObject(i+1, params.get(i));
				}
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
