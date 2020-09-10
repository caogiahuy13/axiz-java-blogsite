<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="screen.articleReactions.title" /></title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/WEB-INF/views/navbar.jsp"%>

	<c:forEach var="user" items="${users}">
		<p>${user.loginId}</p>
	</c:forEach>
</body>
</html>