<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="screen.favorites.title" /></title>
<link href="css/card.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/WEB-INF/views/checkLogin.jsp"%>

	<%@ include file="/WEB-INF/views/navbar.jsp"%>

	<h2>
		<fmt:message key="screen.favorites.title" />
	</h2>

	<c:forEach var="article" items="${articles}">
		<div class="card"
			onclick="window.location='/article?id=${article.articleId}';">
			<div class="container">
				<h4>
					<b>${fn:escapeXml(article.title)}</b>
				</h4>
				<p>${fn:escapeXml(article.getReviewContent())}</p>
			</div>
		</div>
	</c:forEach>

</body>
</html>
