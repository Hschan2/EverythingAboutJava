<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@page import="org.springframework.web.bind.annotation.SessionAttributes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- session 설정 켜기 (session="true", 세션 값 불러오기 등) --%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<link href="/webjars/bootstrap/4.5.0/css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${cp}/resources/css/normal.css" />
	<title>로그인 성공 페이지</title>
</head>
<body>
	<h3 class="loginH3">로그인에 성공하였습니다.</h3>
	
	<div class="loginSet">
		<%-- session.setAttribute로 저장된 데이터 불러오기 => sessionScope~ --%>
		안녕하세요. ${sessionScope.member.memId}님
		
		<hr>
		<div class="Informations">
			${sessionScope.member.memId}님의 회원 정보 <br><br>
			ID: ${sessionScope.member.memId} <br>
			PASSWORD : ${sessionScope.member.memPw} <br>
			E-MAIL : ${sessionScope.member.memMail}
		</div>
	</div>
	
	<P class="timeNow"> 로그인 시간 : ${serverTime}. </P>
	
	<div class="aLink">
		<a href="${cp}/">메인화면</a>
	</div>
</body>
</html>