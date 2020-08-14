<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>회원 가입 성공</title>
</head>
<body>
	<h1> 회원 가입 성공 </h1>
	아이디 : ${mem.memId}<br />
	비밀번호 : ${mem.memPw}<br />
	이메일 : ${mem.memMail} <br />
	핸드폰 : ${mem.memPhone1} - ${mem.memPhone2} - ${mem.memPhone3} <br />
	<!-- public String memJoin(Member member)로 선언했을 시. ${member.memId} ... 로 가져오면 된다 -->
	<!-- 핸드폰은 ${member.memPhone1}-${member.memPhone1}-${member.memPhone1}로 가져오면 된다 -->
	<!-- Controller에서 @ModelAttribute("mem") Member member로 선언했을 경우 -->
	
	<a href="/lec17/resources/html/login.html">로그인</a>
</body>
</html>
