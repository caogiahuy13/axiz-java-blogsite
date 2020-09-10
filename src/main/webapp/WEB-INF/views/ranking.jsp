<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="screen.ranking.title" /></title>
<link href="css/card.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/checkLogin.jsp"%>

	<%@ include file="/WEB-INF/views/navbar.jsp"%>

	<h2>
		<fmt:message key="screen.ranking.title" />
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
			<p>${article.count} <i class="fa fa-thumbs-o-up"></i></p>

		</div>
	</c:forEach>

</body>
</html>
