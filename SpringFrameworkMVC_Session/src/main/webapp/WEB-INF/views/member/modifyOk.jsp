<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${cp}/resources/css/normal.css" />
	<title>회원수정 성공 페이지</title>
</head>
<body>
	<h3 class="loginH3">회원 수정을 완료하였습니다.</h3>
	
	<%--
	<h3> Before Modify </h3>
	${memBef}
	ID : ${memBef.memId} <br />
	PW : ${memBef.memPw} <br />
	Mail : ${memBef.memMail} <br />
	--%>
	<h3> 회원수정 </h3>
	ID : ${memAft.memId} <br />
	PASSWORD : ${memAft.memPw} <br />
	E-MAIL : ${memAft.memMail} <br />
	
	<P class="timeNow"> 로그인 시간 : ${serverTime}. </P>
	
	<div class="aLink">
		<a href="/ch12/">메인화면</a>
	</div>
</body>
</html>
