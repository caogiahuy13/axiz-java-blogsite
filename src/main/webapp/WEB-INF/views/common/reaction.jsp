<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form action="reaction" method="post" style="display: inline">
	<input type="hidden" name="articleIdStr" value="${article.articleId}" />
	<input type="hidden" name="stampIdStr" value="${param.stampId}" />
	<button class="unstyled-button">
		<c:if test="${isReacted == param.stampId}">
			<span style="font-size: 20px;"> ${param.stampName}</span>
		</c:if>
		<c:if test="${isReacted != param.stampId}">
			<span style="opacity: 50%; font-size: 20px;"> ${param.stampName}</span>
		</c:if>
	</button>
</form>