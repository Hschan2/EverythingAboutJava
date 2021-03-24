<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@page import="org.springframework.web.bind.annotation.SessionAttributes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- session 설정 켜기 (session="true", 세션 값 불러오기 등) --%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>로그인 성공 페이지</title>
</head>
<body>
	<h1>로그인 성공</h1>
	
	<%-- session.setAttribute로 저장된 데이터 불러오기 => sessionScope~ --%>
	아이디 : ${sessionScope.member.memId} <br />
	비밀번호 : ${sessionScope.member.memPw} <br />
	이메일 : ${sessionScope.member.memMail} <br />

	<P> 현재 시간은 ${serverTime}. </P>
	
	<a href="${cp}/">메인화면</a>
</body>
</html>