<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>회원수정 성공 페이지</title>
</head>
<body>
	<h1>회원수정 성공</h1>
	
	<%--
	<h3> Before Modify </h3>
	${memBef}
	ID : ${memBef.memId} <br />
	PW : ${memBef.memPw} <br />
	Mail : ${memBef.memMail} <br />
	--%>
	<h3> 회원수정 </h3>
	아이디 : ${memAft.memId} <br />
	비밀번호 : ${memAft.memPw} <br />
	이메일 : ${memAft.memMail} <br />
	
	<P>  현재 시간은 ${serverTime}. </P>
	
	<a href="/ch12/">메인화면</a>
</body>
</html>
