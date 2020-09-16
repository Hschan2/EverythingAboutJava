<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/normal.css" />
</head>
<body>
	
	<h1>회원 로그인</h1>
	
	<form:form action="${cp}/member/login" method="post" commandName="member">
		<table>
			<tr>
				<td>아이디</td>
				<td><form:input path="memId" /></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><form:password path="memPw" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="로그인" ></td>
			</tr>
		</table>
	</form:form>
	
	<a href="${cp}/">메인화면</a>
</body>
</html>