package com.wj.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.bean.Person;
import com.wj.dao.PersonDAO;
import com.wj.service.PageService;

public class UpdatePersonServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PageService pageservice = new PageService();
		Person person = new Person();
		String id = request.getParameter("id");
		String name = request.getParameter("personName");
		String sex = request.getParameter("sex");
		int age = Integer.parseInt(request.getParameter("age"));
		person.setId(id);
		person.setName(name);
		person.setSex(sex);
		person.setAge(age);
		pageservice.updatePerson(person);
		
		request.getRequestDispatcher("QueryPageServlet").forward(request, response);
	}

}
