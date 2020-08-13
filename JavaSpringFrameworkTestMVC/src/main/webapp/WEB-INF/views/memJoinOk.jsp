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
	<!-- public String memJoin(Member member)로 선언했을 시. ${member.memId} ... 로 가져오면 된다 -->
	<!-- 핸드폰은 ${member.memPhone1}-${member.memPhone1}-${member.memPhone1}로 가져오면 된다 -->
	
	<a href="/lec17/resources/html/login.html">로그인</a>
</body>
</html>
