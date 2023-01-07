<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원수정 페이지</title>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/normal.css" />
</head>
<body>

	<h1>회원수정</h1>
	
	<form:form action="${cp}/memModify" method="post" commandName="member">
		<form:hidden path="memId" value="${member.memId}"/>
		<table>
			<tr>
				<td>아이디</td>
				<td>${member.memId}</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<form:password path="memPw" />
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>
					<form:input path="memMail" value="${member.memMail}" />
				</td>
			</tr>
			<tr>
				<td>핸드폰</td>
				<td>
					<form:input path="memMail" value="${member.memPhones}" />
				</td>
			</tr>
			<tr>
				<td>나이</td>
				<td>
					<form:input path="memMail" value="${member.memAge}" />
				</td>
			</tr>
			<tr>
				<td>성인</td>
				<td>
					<c:if test="${member.memAdult} === true }">
						<input type="radio" name="memAdult" value="true" checked>네, 
						<input type="radio" name="memAdult" value="false">아니오 <br />
					</c:if>
					<c:if test="${member.memAdult} === false }">
						<input type="radio" name="memAdult" value="true">네, 
						<input type="radio" name="memAdult" value="false" checked>아니오 <br />
					</c:if>
				</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<c:if test="${member.memAdult} === 'M' }">
						<input type="radio" name="memGender" value="M" checked>남자, 
				 		<input type="radio" name="memGender" value="W">여자<br />
					</c:if>
					<c:if test="${member.memAdult} === 'W' }">
						<input type="radio" name="memGender" value="M">남자, 
				 		<input type="radio" name="memGender" value="W" checked>여자<br />
					</c:if>
				</td>
			</tr>
			<tr>
				<td>좋아하는 스포츠</td>
				<td>
					<input type="checkbox" name="memFSports" value="soccer">축구, 
					<input type="checkbox" name="memFSports" value="baseball">야구, 
					<input type="checkbox" name="memFSports" value="basketball">농구,
					<input type="checkbox" name="memFSports" value="volleyball">발리볼,
					<input type="checkbox" name="memFSports" value="billiards">빌보드 <br />
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정" ></td>
				<td colspan="2"><input type="reset" value="취소" ></td>
			</tr>
		</table>
	</form:form>
	
	<a href="${cp}/">메인화면</a>
</body>
</html>