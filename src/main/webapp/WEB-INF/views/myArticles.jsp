<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.myArticles.title" />
</jsp:include>

<%@ include file="common/checkLogin.jsp"%>

<c:if test="${not empty msg}">
	<p class="error">${msg}</p>
</c:if>

<c:forEach var="article" items="${articles}">
	<div class="card"
		onclick="window.location='/article?id=${article.articleId}';">
		<div class="container">
			<h4>
				<b>${fn:escapeXml(article.title)}</b>
			</h4>
			<p>${fn:escapeXml(article.getReviewContent())}</p>
			<div>
				<fmt:formatDate type="both" value="${article.createdAt}" />
			</div>
		</div>
	</div>
</c:forEach>

<jsp:include page="common/footTag.jsp" />