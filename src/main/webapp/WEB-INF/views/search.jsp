<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="screen.search.title" /></title>
<link href="css/commons.css" rel="stylesheet">
<link href="css/card.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/WEB-INF/views/navbar.jsp"%>

	<c:if test="${not empty msg}">
		<p class="error">${msg}</p>
	</c:if>

	<h3>キーワード検索</h3>

	<form:form action="search" modelAttribute="searchForm">
		<fieldset>
			<div>
				<form:input path="keyword" />
				<form:errors path="keyword" cssStyle="color: red" />
			</div>
		</fieldset>
		<form:button>
			<fmt:message key="btn.search" />
		</form:button>
	</form:form>

	<c:forEach var="article" items="${articles}">
		<div class="card">
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
