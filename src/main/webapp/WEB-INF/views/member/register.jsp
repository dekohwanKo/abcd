<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function daumPost(){
 new daum.Postcode({
        oncomplete: function(data) {
            // 도로명일 경우R, 지번일 경우J
            console.log("data.userSelectedType : "+data.userSelectedType)
            console.log("data.roadAddress : "+data.roadAddress)
            console.log("data.jibunAddress : "+data.jibunAddress)
            console.log("data.zonecode(우편번호) : "+data.zonecode)
            var addr = ""
            if(data.userSelectedType === 'R'){//도로명
            	addr = data.roadAddress
            }else{ //지번
            	addr = data.jibunAddress
            }
            //document.getElementById('addr1').value = data.zonecode
            //document.getElementById('addr2').value = addr
            //document.getElementById('addr3').focus()
            $("#addr1").val(data.zonecode)
            $("#addr2").val(addr)
            $("#addr3").focus()
        }
    }).open();
}
function reg(){
	var addr = $("#addr1").val()+"/"+$("#addr2").val()+"/"+$("#addr3").val()
	$("#addr1").val(addr)
	fo.submit()
}

</script>
</head>
<body>
	<c:import url="../default/header.jsp"/>
	<div align="center">
	<form id="fo" action="register" method="post">
		<table border="1">
			<tr>
			<td>
				<input type="text" name="id" placeholder="input id"><br>
				<input type="text" name="pw" placeholder="input password"><br>
				<input type="text" id="addr1" name="addr" placeholder="우편번호">
	<input type="button" onclick="daumPost()" value="우편번호 찾기"><br>
				<input type="text" id="addr2"  placeholder="주 소">
				<input type="text" id="addr3"  placeholder="상세주소">
				<input type="button" onclick="reg()" value="register">
			</td>
			</tr>
		</table>
	</form>
	</div>
	<c:import url="../default/footer.jsp"/>
</body>
</html>











