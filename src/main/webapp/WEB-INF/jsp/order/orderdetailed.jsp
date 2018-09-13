<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/jquery/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="table table-hover">
  <thead>
  	<th align="center">#</th>
  	<th align="center">商品</th>
  	<th align="center">购买数量</th>
  	<th align="center">小计</th>
  </thead>
  <tbody>
  	<c:forEach items="${list }" var="o" varStatus="s">
  		<tr align="center">
  			<td>${s.count }</td> 			
  			<td>${o.pname }</td>
  			<td>${o.count }</td>
  			<td>${o.subtotal }</td>
  		</tr>
  	</c:forEach>
  </tbody>
</table>
</body>
</html>