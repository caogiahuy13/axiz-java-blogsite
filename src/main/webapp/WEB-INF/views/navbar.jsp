<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/navbar.css" rel="stylesheet">
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
					<fmt:message key="btn.user" />
					<i class="fa fa-caret-down"></i>
				</button>
				<div class="dropdown-content">
					<a href="#">Link 1</a> <a href="#">Link 2</a> <a href="/logout"><fmt:message
							key="btn.logout" /></a>
				</div>
			</c:if>

		</div>
		<a class="topnav-right" href="#news">News</a>
	</div>
</body>
</html>