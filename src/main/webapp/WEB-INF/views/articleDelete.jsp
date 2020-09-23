<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="commonHeadTag.jsp">
	<jsp:param name="title" value="screen.deleteArticle.title" />
</jsp:include>

<%@ include file="commonLoginCheck.jsp"%>

<h2>${fn:escapeXml(article.title)}</h2>

<p style="white-space: pre-line">${fn:escapeXml(article.content)}</p>

<fmt:message key="article.delete.confirm" />

<form action="/deleteArticle" method="post">
	<input type="hidden" name="articleId" value="${article.articleId}">
	<button>
		<fmt:message key="btn.delete" />
	</button>
	<a href="javascript:history.back()"><fmt:message key="btn.return" /></a>
</form>

<jsp:include page="commonFootTag.jsp" />