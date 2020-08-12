<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%
    // 인코딩
    request.setCharacterEncoding("utf-8");
%>
<html>
<head>
	<title>Login Success</title>
</head>
<body>
	<h1> Login Success </h1>
	ID : ${memId}<br />
	PASSWORD : ${memPw}<br />
	
	<a href="/lec17/resources/html/index.html">HOME</a>
</body>
</html>
