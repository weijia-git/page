package com.wj.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wj.bean.Person;
import com.wj.util.DBUtil;

public class PersonDAO {
	private DBUtil util = DBUtil.getInstance();
	private String sql = "";
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public List<Person> queryPage(int start,int end) {
		util.getConnection();
		List<Object> params = new ArrayList<Object>();
		List<Person> list = new ArrayList<Person>();
		sql = "select * from (select p.*,rownum rn from " +
				"(select * from person order by age) p " +
				"where rownum <= ?) where rn>?";
		params.add(end);
		params.add(start);
		rs = util.query(sql, params);
		try {
			while(rs.next()) {
				Person p = new Person();
				p.setId(rs.getString("id"));
				p.setName(rs.getString("name"));
				p.setSex(rs.getString("sex"));
				p.setAge(rs.getInt("age"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(util != null) {
				util.close();
			}
		}
		
		return list;
	}
	
	public Person quertPersonById(String id) {
		util.getConnection();
		sql = "select * from person where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		rs = util.query(sql, params);
		Person p = new Person();
		try {
			while(rs.next()) {
				p.setId(rs.getString("id"));
				p.setName(rs.getString("name"));
				p.setSex(rs.getString("sex"));
				p.setAge(rs.getInt("age"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public void delPerson(String id) {
		util.getConnection();
		sql = "delete from person where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		util.update(sql, params);
	}
	
	public void insertPerson(Person p) {
		util.getConnection();
		sql = "insert into person values(?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(p.getId());
		params.add(p.getName());
		params.add(p.getSex());
		params.add(p.getAge());
		util.update(sql, params);
	}
	
	public void updatePerson(Person p) {
		util.getConnection();
		sql = "update person set name=?,sex=?,age=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(p.getName());
		params.add(p.getSex());
		params.add(p.getAge());
		params.add(p.getId());
		util.update(sql, params);
	}
	
	
	public int getCount() {
		util.getConnection();
		sql = "select count(*) c from person";
		int count = 0;
		rs = util.query(sql, null);
		try {
			while(rs.next()) {
				count = rs.getInt("c");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
}
