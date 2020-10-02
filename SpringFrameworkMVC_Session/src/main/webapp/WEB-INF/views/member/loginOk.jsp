<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>로그인 성공 페이지</title>
</head>
<body>
	<h1>로그인 성공</h1>
	
	아이디 : ${member.memId} <br />
	비밀번호 : ${member.memPw} <br />
	이메일 : ${member.memMail} <br />
	
	<P>  현재 시간은 ${serverTime}. </P>
	
	<a href="${cp}/">메인화면</a>
</body>
</html>