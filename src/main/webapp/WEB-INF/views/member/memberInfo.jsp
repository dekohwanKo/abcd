<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../default/header.jsp"/>
	<div align="center">
		<table border="1">
			<tr>
				<th>아이디</th><th>비밀번호</th><th>주소</th>
			</tr>
			<c:forEach var="mem" items="${memberList }">
			<tr>
				<td>
					<a href="info?id=${mem.id }">${mem.id }</a>
				</td>
				
				<td>${mem.pw }</td><td>${mem.addr }</td>
			</tr>				
			</c:forEach>
		</table>
	</div>
	<c:import url="../default/footer.jsp"/>
</body>
</html>











