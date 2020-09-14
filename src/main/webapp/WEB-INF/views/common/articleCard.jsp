<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


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