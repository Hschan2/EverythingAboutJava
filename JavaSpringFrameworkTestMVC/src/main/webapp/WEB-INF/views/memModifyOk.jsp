<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>회원 정보 수정 완료</title>
</head>
<body>
	<h1> 회원 정보 수정 완료 </h1>
	
	<h3> 현재 회원 정보 </h3>
	아이디 : ${member.memId}<br />
	비밀번호 : ${member.memPw}}<br />
	이메일 : ${member.memMail} <br />
	핸드폰 : ${member.memPhones}<br />
	나이 : ${member.memAge} <br />
	성인 : ${member.memAdult} <br />
	성별 : ${member.memGender} <br />
	좋아하는 스포츠 : 
	<c:forEach var="fSport" items="${member.memFSports}">
		${fSport}, 
	</c:forEach> <br />
	
	<P>  The time on the server is ${serverTime}. </P>
	
	<a href="${cp}/"> 홈 </a>
</body>
</html>
