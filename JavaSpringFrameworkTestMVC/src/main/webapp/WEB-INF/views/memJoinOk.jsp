<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%
    // 인코딩
    request.setCharacterEncoding("utf-8");
%>
<html>
<head>
	<title>Join Success</title>
</head>
<body>
	<h1> Join Success </h1>
	ID : ${memId}<br />
	PASSWORD : ${memPw}<br />
	E-MAIL : ${memMail} <br />
	PHONE : ${memPhone} <br />
	
	<a href="/lec17/resources/html/login.html">LOGIN</a>
</body>
</html>
