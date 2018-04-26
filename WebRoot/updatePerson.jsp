<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updatePerson.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="UpdatePersonServlet" method="post">
    	<table align="center" border="1" width="30%">
	    	<tr>
	    		<td>号码：</td>
	    		<td><input type="text" name="id" value="${p.id }"/></td>
	    	</tr>
	    	<tr>
	    		<td>姓名：</td>
	    		<td><input type="text" name="personName" value="${p.name }"/></td>
	    	</tr>
	    	<tr>
	    		<td colspan="2">
	    			<input type="radio" name="sex" value="男" ${p.sex=="男"?"checked":"" }/>男
	    			<input type="radio" name="sex" value="女" ${p.sex=="女"?"checked":"" }/>女
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>年龄：</td>
	    		<td><input type="text" name="age" value="${p.age }"/></td>
	    	</tr>
	    	<tr>
	    		<td colspan="2">
	    			<input type="submit" value="确定"/>
	    			<input type="reset" value="重置"/>
	    		</td>
	    	</tr>
    	</table>
    </form>
  </body>
</html>
