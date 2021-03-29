<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${cp}/resources/css/normal.css" />
	<title>회원가입 페이지</title>
</head>
<body>
	<h3 class="loginH3">회원가입</h3>
	
	<div class="loginSet">
		<form:form action="${cp}/member/join" method="post" commandName="member" class="loginForm">
		
			<form:input path="memId" placeholder="ID" class="loginInput" />
			<form:password path="memPw" placeholder="PASSWORD" class="loginInput" />
			<form:input path="memMail" placeholder="E-MAIL" class="loginInput" />
			<input type="submit" value="가입" class="loginBtn">
			<input type="button" value="취소" class="loginBtn" onClick="history.go(-1)">
		</form:form>
	</div>
	
	<div class="aLink">
		<a href="${cp}/">메인화면</a>
	</div>
</body>
</html>