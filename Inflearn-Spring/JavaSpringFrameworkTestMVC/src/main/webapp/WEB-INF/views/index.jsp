<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>홈페이지</title>
</head>
<body>
	
	<h1>메인 화면</h1>
	
	<c:if test="${empty member}"> <!-- 로그인이 안되어 있을 시 -->
		<a href="${cp}/resources/html/memJoin.html">회원가입</a> &nbsp;&nbsp; 
		<a href="${cp}/resources/html/login.html">로그인</a> &nbsp;&nbsp; 
	</c:if>
	
	<c:if test="${!empty member}"> <!-- 로그인이 되어 있을 시 -->
		<a href="${cp}/modifyForm">회원수정</a> &nbsp;&nbsp; 
		<a href="${cp}/logout">로그아웃</a> &nbsp;&nbsp;
		<a href="${cp}/removeForm">회원탈퇴</a> &nbsp;&nbsp; 
	</c:if>
	
</body>
</html>