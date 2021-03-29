<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/normal.css" />
<title>홈페이지</title>
</head>
<body>
	<h3 class="loginH3">메인 화면</h3>
	
	<div class="indexSet">
		<div class="aLink">
			<c:if test="${empty member}"> <!-- 로그인이 안되어 있을 시 -->
				<a href="${cp}/member/joinForm">회원가입</a>
				<a href="${cp}/member/loginForm">로그인</a>
			</c:if>
			
			<c:if test="${!empty member}"> <!-- 로그인이 되어 있을 시 -->
				<a href="${cp}/member/modifyForm">회원수정</a> 
				<a href="${cp}/member/logout">로그아웃</a>
				<a href="${cp}/member/removeForm">회원탈퇴</a>
			</c:if>
		</div>
	</div>
</body>
</html>