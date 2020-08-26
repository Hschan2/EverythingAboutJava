<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>회원 삭제</title>
</head>
<body>
	<h1> 회원 삭제 </h1>
	
	<!-- Controller에서 @ModelAttribute("mem")로 설정했기 때문에 -->
	ID : ${mem.memId}<br />
	
	<P>  The time on the server is ${serverTime}. </P>
	
	<a href="/lec17/resources/html/index.html"> 홈 </a>
</body>
</html>