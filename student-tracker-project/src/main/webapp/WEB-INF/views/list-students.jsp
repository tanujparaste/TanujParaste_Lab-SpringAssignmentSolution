<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Directory</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/styles.css" />
</head>
<body>
	<div class="main">
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
		<h1>Student Directory</h1>

		<a class="btn-add"
			href="${pageContext.request.contextPath}/students/add">Add
			Student</a>
		<c:choose>
			<c:when test="${empty students}">
				<p>No record found!</p>
			</c:when>
			<c:otherwise>
				<table>
					<thead>
						<tr>
							<th>Id</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Course</th>
							<th>Country</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${students}" var="student">
							<tr>
								<td>${student.id}</td>
								<td>${student.firstName}</td>
								<td>${student.lastName}</td>
								<td>${student.course}</td>
								<td>${student.country}</td>
								<td>
									<a class="btn-action"
									href="${pageContext.request.contextPath}/students/update?id=${student.id}">Edit</a>
									<a class="btn-action"
									href="${pageContext.request.contextPath}/students/delete?id=${student.id}">Remove</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>