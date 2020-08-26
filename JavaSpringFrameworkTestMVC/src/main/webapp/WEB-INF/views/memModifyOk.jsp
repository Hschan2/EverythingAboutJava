<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>회원 정보 수정 완료</title>
</head>
<body>
	<h1> 회원 정보 수정 완료 </h1>
	
	<h3> 전 회원 정보 </h3>
	아이디 : ${memBef.memId} <br />
	비밀번호 : ${memBef.memPw} <br />
	이메일 : ${memBef.memMail} <br />
	핸드폰 1 : ${memBef.memPhones[0].memPhone1} - ${memBef.memPhones[0].memPhone2} - ${memBef.memPhones[0].memPhone3} <br />
	핸드폰 2 : ${memBef.memPhones[1].memPhone1} - ${memBef.memPhones[1].memPhone2} - ${memBef.memPhones[1].memPhone3} <br />
	나이 : ${memBef.memAge} <br />
	성인 : ${memBef.memAdult} <br />
	성별 : ${memBef.memGender} <br />
	좋아하는 스포츠 : 
	<c:forEach var="fSport" items="${memBef.memFSports}">
		${fSport}, 
	</c:forEach> <br />
	
	<h3> 현재 회원 정보 </h3>
	아이디 : ${memAft.memId} <br />
	비밀번호 : ${memAft.memPw} <br />
	이메일 : ${memAft.memMail} <br />
	핸드폰 1 : ${memAft.memPhones[0].memPhone1} - ${memAft.memPhones[0].memPhone2} - ${memAft.memPhones[0].memPhone3} <br />
	핸드폰 2 : ${memAft.memPhones[1].memPhone1} - ${memAft.memPhones[1].memPhone2} - ${memAft.memPhones[1].memPhone3} <br />
	나이 : ${memAft.memAge} <br />
	성인 : ${memAft.memAdult} <br />
	성별 : ${memAft.memGender} <br />
	좋아하는 스포츠 : 
	<c:forEach var="fSport" items="${memAft.memFSports}">
		${fSport}, 
	</c:forEach> <br />
	
	<P>  The time on the server is ${serverTime}. </P>
	
	<a href="/lec17/resources/html/index.html"> 홈 </a>
</body>
</html>
