<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/navbar.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="navbar">
		<a href="/search"><fmt:message key="btn.search" /></a>
		<c:if test="${not empty sessionScope.currentUser}">
			<a href="/createArticle"><fmt:message key="btn.createArticle" /></a>
		</c:if>

		<div class="dropdown">
			<c:if test="${empty sessionScope.currentUser}">
				<a href="/login"><fmt:message key="btn.login" /></a>
			</c:if>
			<c:if test="${not empty sessionScope.currentUser}">
				<button class="dropbtn">
					${sessionScope.currentUser.userName} <i class="fa fa-caret-down"></i>
				</button>
				<div class="dropdown-content">
					<a href="#">Link 1</a> <a href="#">Link 2</a> <a href="/logout"><fmt:message
							key="btn.logout" /></a>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>