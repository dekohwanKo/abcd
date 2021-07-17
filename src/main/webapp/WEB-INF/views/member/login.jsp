<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../default/header.jsp"/>
	<div align="right">
		<form action="${contextPath }/member/user_check" method="post">
			<input type="text" name="id" placeholder="input id"><br>
			<input type="password" name="pw" placeholder="input pwd"><br>
			<input type="submit" value="login">
			<a href="register_form">회원가입</a>
			<br>
			<input type="checkbox" name="autoLogin">로그인 유지
		</form>	
	</div>
	<c:import url="../default/footer.jsp"/>
</body>
</html>





