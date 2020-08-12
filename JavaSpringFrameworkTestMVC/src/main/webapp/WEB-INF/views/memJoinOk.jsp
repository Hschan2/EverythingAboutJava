<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>회원 가입 성공</title>
</head>
<body>
	<h1> 회원 가입 성공 </h1>
	아이디 : ${memId}<br />
	비밀번호 : ${memPw}<br />
	이메일 : ${memMail} <br />
	핸드폰 : ${memPhone} <br />
	
	<a href="/lec17/resources/html/login.html">로그인</a>
</body>
</html>
