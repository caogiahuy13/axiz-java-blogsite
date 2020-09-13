<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form action="reaction" method="post">
	<input type="hidden" name="articleIdStr" value="${article.articleId}" />
	<input type="hidden" name="stampIdStr" value="${param.stampId}" />
	<button class="unstyled-button">
		<c:if test="${isReacted == param.stampId}">
			<i class="fas ${param.stampName}" style="font-size: 24px;"></i>
		</c:if>
		<c:if test="${isReacted != param.stampId}">
			<i class="far ${param.stampName}" style="font-size: 24px;"></i>
		</c:if>
	</button>
</form>