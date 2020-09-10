<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="screen.article.title" /></title>
<link href="css/commons.css" rel="stylesheet">
<link href="css/popup.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/navbar.jsp"%>

	<h2>${fn:escapeXml(article.title)}</h2>

	<p>${fn:escapeXml(article.content)}</p>

	<c:if test="${sessionScope.currentUser.userId != article.userId}">
		<div class="popup" onclick="togglePopup()">
			<span style="color:blue">${reactions} いいね</span><span class="popuptext" id="myPopup">
				<c:forEach var="user" items="${reactedUsers}">
					<span>${user.userName}</span>
					<br>
				</c:forEach>
			</span>
		</div>
	</c:if>

	<c:if test="${sessionScope.currentUser.userId != article.userId}">
		<form action="reaction" method="post">
			<input type="hidden" name="articleIdStr" value="${article.articleId}" />
			<input type="hidden" name="reactionIconIdStr" value="1" />
			<button class="unstyled-button">
				<i class="fa fa-thumbs-o-up" style="font-size: 24px;"></i>
			</button>
		</form>
	</c:if>

	<c:if test="${sessionScope.currentUser.userId == article.userId}">
		<form action="editArticle">
			<input type="hidden" name="id" value="${article.articleId}" />
			<button>
				<fmt:message key="btn.update" />
			</button>
		</form>
	</c:if>

	<form action="comment" method="post">
		<input type="hidden" name="articleIdStr" value="${article.userId}">
		<input name="commentStr" />
		<button>
			<fmt:message key="btn.comment" />
		</button>
	</form>

	<c:forEach var="comment" items="${comments}">
		<div>
			<p>
				<b>${comment.userName}</b><br> ${comment.content}
			</p>
		</div>
	</c:forEach>

	<script>
		function togglePopup() {
			var popup = document.getElementById("myPopup");
			popup.classList.toggle("show");
		}
	</script>
</body>
</html>