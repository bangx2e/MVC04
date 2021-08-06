<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.bit.model.*" %>
<%@ page import="java.util.*" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <% ArrayList<MemberVO> list = (ArrayList<MemberVO>)request.getAttribute("list"); %>    --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
function deleteFn(num) {
	location.href="memberDelete.do?num="+num;
}
</script>
<title>Insert title here</title>
</head>
<body>
[MVC03 예제 - Controller + View]
	<table class='table table-bordered'>
		<tr>
			<td>번호</td>
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>나이</td>
			<td>이메일</td>
			<td>전화번호</td>
			<td>삭제</td>
		</tr>
<%-- 		<% --%>
<!--  			for (MemberVO vo : list) { -->
<%-- 		%> --%>
		<c:forEach var="vo" items="${list}">
		<tr>
			<td>${vo.num}</td>
			<td><a href="memberContent.do?num=${vo.num}">${vo.id}</a></td>
			<td>${vo.pass}</td>
			<td>${vo.name}</td>
			<td>${vo.age}</td>
			<td>${vo.email}</td>
			<td>${vo.phone}</td>
			<td><input type="button" value="삭제" onclick="deleteFn(${vo.num})" class="btn btn-warning"/></td>
		</tr>
		</c:forEach>
<%-- 		<% --%>
<!--  			} -->
<%-- 		%> --%>
	<tr>
		<td colspan='8' align='right'><input type="button" value="회원가입" class='btn btn-primary' onclick="location.href='member/memberRegister.html'"/></td>
	</tr>
	</table>
</body>
</html>