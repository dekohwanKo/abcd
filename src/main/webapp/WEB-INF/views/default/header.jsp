<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="contextPath" value="${pageContext.request.contextPath }" />


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
	<style type="text/css">
		ul li{ display: inline;  padding: 0 10px; }
	</style>
</head>
<body>
	<div align="center">	
		<h1 style="color:burlywood; font-size:60px; font-family: Gabriola;">
			CARE _ LAB
		</h1>
	</div>
	<div align="right">
		<hr>
		<ul>
			<li><a href="${contextPath }/index">HOME</a></li>
	<li><a href="${contextPath }/board/boardAllList">BOARD</a></li>
			<li>
			<a href="${contextPath }/member/memberInfo">MEMBER-SHIP</a>
			<%-- 
				<c:if test="${loginUser != null }">
					<a href="${contextPath }/member/memberInfo">MEMBER-SHIP</a>
				</c:if>
				
				<c:if test="${loginUser == null }">
					<a href="${contextPath }/member/login">MEMBER-SHIP</a>
				</c:if>
			--%>
			</li>
			
			<li>
				<c:if test="${loginUser != null }">
					<a href="${contextPath }/member/logout">LOGOUT</a>
				</c:if>
				
				<c:if test="${loginUser == null }">
					<a href="${contextPath }/member/login">LOGIN</a>
				</c:if>
			</li>
		</ul>
		<hr>
	</div>
</body>
</html>












