<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${cp}/resources/css/normal.css" />
	<title>로그아웃 페이지</title>
</head>
<body>
	<h3 class="loginH3">로그아웃을 완료하였습니다.</h3>
	
	<P class="timeNow"> 로그아웃 시간 : ${serverTime}. </P>
	
	<div class="aLink">
		<a href="${cp}/">메인화면</a>
	</div>
</body>
</html>