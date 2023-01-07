<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${cp}/resources/css/normal.css" />
	<title>회원가입 성공 페이지</title>
</head>
<body>
	<h1>회원 가입을 완료하였습니다.</h1>
	
	ID : ${member.memId} <br />
	PASSWORD : ${member.memPw} <br />
	E-MAIL : ${member.memMail} <br />
	
	<P class="timeNow"> 가입 시간 : ${serverTime}. </P>
	
	<div class="aLink">
		<a href="${cp}/">메인화면</a>
	</div>
</body>
</html>
