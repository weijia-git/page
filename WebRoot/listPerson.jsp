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
    
    <title>My JSP 'listPerson.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function delperson(id) {
			if(confirm("您确定要删除该项吗？")){
				location.href="DelPersonServlet?id="+id;
			}			
		}
	
		function change() {
			var recordCount = document.getElementById("prc").value;
			var size = document.getElementById("size");
			if( size.value != null && !isNaN(size.value) && parseInt(size.value) <= parseInt(recordCount) ) {
				location.href = "QueryPageServlet?pageSize="+size.value;
			}else {
				alert("请输入正确的数字！");
			}
		}
		
		function forward() {
			var pageCount = document.getElementById("pc").value;
			var number = document.getElementById("number");
			var size = document.getElementById("size");
			if( size.value != null && !isNaN(size.value) && number.value > 0 && parseInt(number.value) <= parseInt(pageCount) ) {
				location.href = "QueryPageServlet?pageSize="+ size.value + "&pageNum=" + number.value;
			}
		}
	</script>
  </head>
  
  <body>
  	<input type="hidden" id="prc" value="${page.recordCount }" />
	<input type="hidden" id="pc" value="${page.pageCount }" />
  	<h1 align="center">人事管理系统</h1>
	<form action="QueryPageServlet" method="post">
		<table align="center" border="1" width="40%">
		<tr>
			<td>id</td>
			<td>姓名</td>
			<td>性别</td>
			<td>年龄</td>
			<td>操作 | <a href="insertPerson.jsp">增加</a></td>
		</tr>
		<c:forEach  items="${list }" var="p">
			<tr>
				<td>${p.id }</td>
				<td>${p.name }</td>
				<td>${p.sex }</td>
				<td>${p.age }</td>
				<td>
					<a href="PreUpdatePersonServlet?id=${p.id }">更新</a> | 
					<a href="javascript:delperson(${p.id })">删除</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
				共  ${page.recordCount } 条记录，每页显示
				<input id="size" type="text" value="${page.pageSize }" size="1"/>
				<input type="button" value="更改" onclick="change()" />条记录，
				共 ${page.pageCount } 页 
			</td>
		</tr>
		<tr>
			<td colspan="5" align="center">
				<c:choose>
					<c:when test="${page.pageNum == 1}">
						首页
						上一页
					</c:when>
					<c:otherwise>
						<a href="QueryPageServlet?pageSize=${page.pageSize }&pageNum=1">首页</a>
						<a href="QueryPageServlet?pageSize=${page.pageSize }&pageNum=${page.pageNum-1 }">上一页</a>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${page.pageNum == page.pageCount}">
						下一页
						尾页
					</c:when>
					<c:otherwise>
						<a href="QueryPageServlet?pageSize=${page.pageSize }&pageNum=${page.pageNum+1 }">下一页</a>
						<a href="QueryPageServlet?pageSize=${page.pageSize }&pageNum=${page.pageCount }">尾页</a>
					</c:otherwise>
				</c:choose>
				第 <input id="number" type="text" value="${page.pageNum }" size="1"/> 页
				<input type="button" value="跳转" onclick="forward()" />
			</td>
		</tr>
	</table>
	</form>
  </body>
</html>
