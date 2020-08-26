<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>회원 가입 성공</title>
</head>
<body>
	<h1> 회원 가입 성공 </h1>
	아이디 : ${member.memId} <br />
	비밀번호 : ${member.memPw} <br />
	이메일 : ${member.memMail} <br />
	핸드폰 1 : ${member.memPhones[0].memPhone1} - ${member.memPhones[0].memPhone2} - ${member.memPhones[0].memPhone3} <br />
	핸드폰 2 : ${member.memPhones[1].memPhone1} - ${member.memPhones[1].memPhone2} - ${member.memPhones[1].memPhone3} <br />
	나이 : ${member.memAge} <br />
	성인 : ${member.memAdult} <br />
	성별 : ${member.memGender} <br />
	좋아하는 스포츠 : 
	<c:forEach var="fSport" items="${member.memFSports}">
		${fSport}, 
	</c:forEach> <br />
	
	<P>  The time on the server is ${serverTime}. </P>
	<!-- public String memJoin(Member member)로 선언했을 시. ${member.memId} ... 로 가져오면 된다 -->
	<!-- 핸드폰은 ${member.memPhone1}-${member.memPhone1}-${member.memPhone1}로 가져오면 된다 -->
	<!-- Controller에서 @ModelAttribute("mem") Member member로 선언했을 경우 -->
	
	<a href="/lec17/resources/html/login.html">로그인</a>
</body>
</html>
