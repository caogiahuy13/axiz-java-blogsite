<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div class="card"
	onclick="window.location='/article?id=${article.articleId}';">
	<div class="container">
		<p>
			<b>${fn:escapeXml(article.title)}</b><br>
			<i style="font-size: 14px;">
				@${article.name }&nbsp
				${rankIcon[article.memberRank]}&nbsp
				<fmt:formatDate type="date" value="${article.createdAt}" />
			</i>
		</p>

		<p>${fn:escapeXml(article.getReviewContent())}</p>
		<div style="text-align: right;">
			<b>${article.articleReactionCount }</b> スタンプ
		</div>

	</div>
</div>