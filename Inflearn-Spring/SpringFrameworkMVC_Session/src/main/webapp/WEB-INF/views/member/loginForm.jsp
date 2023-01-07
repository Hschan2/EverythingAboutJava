<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/webjars/bootstrap/4.5.0/css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${cp}/resources/css/normal.css" />
	<title>로그인 페이지</title>
</head>
<body>
	<div class="loginSet">
		<h3 class="loginH3">회원 로그인</h3>
		
		<form:form action="${cp}/member/login" method="post" commandName="member" class="loginForm">
			<form:input path="memId" placeholder="ID" class="loginInput" />
			<form:password path="memPw" placeholder="PASSWORD" class="loginInput" />
			<input type="submit" value="로그인" class="loginBtn">
		</form:form>
	</div>
	
	<div class="aLink">
		<a href="${cp}/">메인화면</a>
	</div>
</body>
</html>