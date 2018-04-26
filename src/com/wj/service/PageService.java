package com.wj.service;

import java.util.List;

import com.wj.bean.Person;
import com.wj.dao.PersonDAO;

public class PageService {
	private PersonDAO persondao = new PersonDAO();
	
	public List<Person> queryPage(int start,int end) {
		return persondao.queryPage(start, end);
	}
	
	public Person quertPersonById(String id) {
		return persondao.quertPersonById(id);
	}
	
	public void delPerson(String id) {
		persondao.delPerson(id);
	}
	
	public void insertPerson(Person p) {
		persondao.insertPerson(p);
	}
	
	public void updatePerson(Person p) {
		persondao.updatePerson(p);
	}
	
	
	public int getCount() {
		return persondao.getCount();
	}
}
