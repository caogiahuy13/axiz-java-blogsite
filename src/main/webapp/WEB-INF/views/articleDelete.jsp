<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="commonHeadTag.jsp">
	<jsp:param name="title" value="screen.deleteArticle.title" />
</jsp:include>

<%@ include file="commonLoginCheck.jsp"%>

<div
	style="background-color: white; margin: 80px 80px 50px 80px; text-align: center; padding: 30px;">
	<h2>記事削除確認</h2>
	<div
		style="margin-top: 3%; margin-left: 3%; font-size: 30px; text-align: left;">※この記事を削除してもよろしいですか？</div>
	<br>

	<h2 class="center" style="font-size: 60px">${fn:escapeXml(article.title)}
	</h2>
	<hr style="width: 45%;">

	<p style="text-align: left; margin-left: 15%;">
		<span>${writer.nickname }${rankIcon[writerRank]}</span> <span>＠${writer.loginId }</span>
		<span><fmt:formatDate type="date" value="${article.createdAt}" /></span>
	</p>

	<div style="margin: 10px" class="center">
		<div class="card"
			style="text-align: left; padding: 8px; margin: 8px auto; font-size: 24px; white-space: pre-line; border: 1px solid; border-width: 3px; border-color: yellowgreen; color: #000;">
			${fn:escapeXml(article.content)}</div>
	</div>

	<c:if test="${not empty msg}">
		<div class="center"
			style="margin-left: 2%; font-size: 24px; color: red;">${msg }</div>
	</c:if>

	<form action="/deleteArticle" method="post" style="margin-left: 70%;">
		<input type="hidden" name="articleId" value="${article.articleId}">
		<button class="button">
			<fmt:message key="btn.delete" />
		</button>
		<br> <a href="javascript:history.back()"><fmt:message
				key="btn.return" /></a>
	</form>
</div>

<jsp:include page="commonFootTag.jsp" />