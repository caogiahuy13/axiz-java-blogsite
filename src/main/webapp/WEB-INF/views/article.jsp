<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="screen.article.title" /></title>
</head>
<body>
	<%@ include file="/WEB-INF/views/navbar.jsp"%>

	<h2>${fn:escapeXml(article.title)}</h2>

	<p>${fn:escapeXml(article.content)}</p>

	<c:if test="${sessionScope.currentUser.userId == article.userId}">
		<form action="editArticle">
			<button>
				<fmt:message key="btn.update" />
			</button>
		</form>
	</c:if>
</body>
</html>