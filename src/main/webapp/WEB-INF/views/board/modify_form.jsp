<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${contextPath }/resources/js/img.js"></script>
</head>
<body>
	<c:import url="../default/header.jsp" />
	<div style="width: 300px; margin: 0 auto;">
		<form action="${contextPath }/board/modify"
			enctype="multipart/form-data" method="post">
			<input type="hidden" name="writeNo" value="${personalData.writeNo }">
			<input type="hidden" name="originFileName" value="${personalData.imageFileName }">
		제목  <input type="text" name="title" size="30" value="${personalData.title }"><hr>
		내용	<textarea rows="5" cols="30" name="content">${personalData.content }</textarea><hr>
		<c:if test="${personalData.imageFileName != 'nan' }">
			<img width="200px" height="100px" id="preview" 
			src="${contextPath }/board/download?imageFileName=${personalData.imageFileName}">
		</c:if>
			<input type="file" name="imageFileName" onchange="readURL(this)">
			<hr>
			<input type="submit" value="수정">
			<input type="button" onclick="history.vack()" value="이전으로 돌아가기">
		</form>
	</div>


	<c:import url="../default/footer.jsp" />
</body>
</html>