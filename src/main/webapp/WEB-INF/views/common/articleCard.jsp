<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="card"
	onclick="window.location='/article?id=${article.articleId}';">
	<div class="container">
		<p style="text-align: left">
			<b>${fn:escapeXml(article.title)}</b>
			<c:if test="${article.trophy eq true }">
				<i class="fas fa-trophy" style="color: orange"></i>
			</c:if>
			<br> <i style="font-size: 14px;"> @${article.name }&nbsp
				${rankIcon[article.memberRank]}&nbsp <fmt:formatDate type="date"
					value="${article.createdAt}" />
			</i>
		</p>

		<p style="text-align: left">${fn:escapeXml(article.getReviewContent())}</p>
		<div style="text-align: right;">
			<b>${article.articleReactionCount }</b> スタンプ
		</div>

	</div>
</div>