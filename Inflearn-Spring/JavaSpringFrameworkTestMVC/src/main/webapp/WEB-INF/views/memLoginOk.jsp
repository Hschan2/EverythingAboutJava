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
	비밀번호 : ${member.memPw}<br />
	이메일 : ${member.memMail} <br />
	핸드폰 : ${member.memPhones}<br />
	나이 : ${member.memAge} <br />
	성인 : 
	<c:if test="${member.memAdult} == '1'">
		네
	</c:if>
	<c:if test="${member.memAdult} != '1'">
		아니요
	</c:if>
	<br />
	성별 : ${member.memGender} <br />
	좋아하는 스포츠 : 
	<c:forEach var="fSport" items="${member.memFSports}">
		${fSport}, 
	</c:forEach> <br />
	
	<P>  The time on the server is ${serverTime}. </P>
	
	<a href="${cp}/">홈</a>
</body>
</html>
