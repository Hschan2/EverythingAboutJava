<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>로그인 성공</title>
</head>
<body>
	<h1> 로그인 성공 </h1>
	아이디 : ${member.memId}<br />
	비밀번호 : ${member.memPw}}<br />
	
	<P>  The time on the server is ${serverTime}. </P>
	
	<a href="/lec17/resources/html/index.html">홈</a>
</body>
</html>