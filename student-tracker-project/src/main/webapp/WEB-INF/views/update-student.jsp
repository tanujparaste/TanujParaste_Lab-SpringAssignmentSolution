<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Record</title>
<link rel="stylesheet" href ="${pageContext.request.contextPath}/css/styles.css"/>
</head>
<body>
	<header>
		<h1>Student Management Portal</h1>
		<div class="user-info">
			<p>User: ${username}</p>
			|
			<p>
				<a href="${pageContext.request.contextPath}/logout">Logout</a>
			</p>
		</div>
	</header>
	<h1>Update student data</h1>
	<p>Please enter the details below.</p>
	<c:choose>
		<c:when test="${empty emptyFields}">
			<p style="color: red">*None of the fields should be left blank</p>
		</c:when>
		<c:otherwise>
			<p style="display: hidden"></p>
		</c:otherwise>
	</c:choose>
	<form action="${pageContext.request.contextPath}/students/save"
		method="post">
		<input type="hidden" name="id" value="${student.id}" />
		
		<label>First Name:</label>
		<input type="text" name="firstName" value="${student.firstName}" /><br />
		
		<label>Last Name:</label>
		<input type="text" name="lastName" value="${student.lastName}" /><br />
		
		<label>Course:</label>
		<input type="text" name="course" value="${student.course}" /><br />
		
		<label>Country:</label>
		<input type="text" name="country" value="${student.country}" /><br />
		
		<label></label>
		<button class="btn-update">Save</button>
		<button class="back-to-list back-to-list-update" type="button" onclick="location.href='${pageContext.request.contextPath}/students/list'" >Back to
			List</button>
	</form>
</body>
</html>