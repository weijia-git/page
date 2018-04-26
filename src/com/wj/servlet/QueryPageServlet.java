package com.wj.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wj.bean.Page;
import com.wj.bean.Person;
import com.wj.dao.PersonDAO;
import com.wj.service.PageService;

public class QueryPageServlet extends HttpServlet {

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
		int pageSize = 10;
		if(request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		int pageNum = 1;
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		 
		
		List<Person> list = new ArrayList<Person>();
		PageService pageservice = new PageService();
		int start = pageSize * pageNum - pageSize;
		int end = pageSize * pageNum;
		
		int recordCount = pageservice.getCount();
		int pageCount = (recordCount-1)/pageSize + 1;
		
		list = pageservice.queryPage(start, end);
		
		Page page = new Page();
		page.setPageSize(pageSize);
		page.setPageNum(pageNum);
		page.setPageCount(pageCount);
		page.setRecordCount(recordCount);
		
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		request.getRequestDispatcher("listPerson.jsp").forward(request, response);
	}

}
