<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.article.title" />
</jsp:include>

<c:if test="${articleUserReactionCount >= 15 && reactionCount >= 3}">
	<p>
		MySpace: <b>${articleUserMySpace}</b>
	</p>
</c:if>

<h2>
	<span>${fn:escapeXml(article.title)}</span>
	<c:if test="${reactionCount >= 3 }">
		<i class="fas fa-trophy" style="color: orange"></i>
	</c:if>
</h2>

<p style="white-space: pre-line">${fn:escapeXml(article.content)}</p>

<div class="popup" onclick="togglePopup()">
	<c:forEach var="entry" items="${reactions}">
		<c:if test="${entry.value > 0}">
			<span style="color: blue"> ${entry.value} <i
				class="far ${stampIcon[entry.key]}"></i>
			</span>
		</c:if>
	</c:forEach>
	<span class="popuptext" id="myPopup"> <c:forEach var="user"
			items="${reactedUsers}">
			<span>${user.userName}</span>
			<br>
		</c:forEach>
	</span>
</div>
<br>

<c:if test="${sessionScope.currentUser.userId != article.userId}">
	<c:choose>
		<c:when test="${sessionScope.totalReactions >= 1}">
			<c:forEach var="entry" items="${stampIcon}">
				<jsp:include page="common/reaction.jsp">
					<jsp:param name="stampId" value="${entry.key}" />
					<jsp:param name="stampName" value="${entry.value}" />
				</jsp:include>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:if test="${sessionScope.totalReactions < 1}">
				<jsp:include page="common/reaction.jsp">
					<jsp:param name="stampId" value="1" />
					<jsp:param name="stampName" value="fa-thumbs-up" />
				</jsp:include>
			</c:if>
		</c:otherwise>
	</c:choose>
	<br>
	<br>
</c:if>


<c:if test="${sessionScope.currentUser.userId == article.userId}">
	<form action="editArticle" style="display: inline">
		<input type="hidden" name="id" value="${article.articleId}" />
		<button>
			<fmt:message key="btn.update" />
		</button>
	</form>
	<form action="deleteArticle" style="display: inline">
		<input type="hidden" name="id" value="${article.articleId}" />
		<button>
			<fmt:message key="btn.delete" />
		</button>
	</form>
	<br>
	<br>
</c:if>

<form action="comment" method="post">
	<input type="hidden" name="articleIdStr" value="${article.articleId}">
	<input name="contentStr" required />
	<button>
		<fmt:message key="btn.comment" />
	</button>
</form>

<c:forEach var="comment" items="${comments}">
	<div>
		<c:if test="${sessionScope.currentUser.userId != comment.userId}">
			<div>
				<b>${comment.userName}</b><br>
				<div>
					<span>${comment.content}</span>
				</div>
			</div>
		</c:if>

		<c:if test="${sessionScope.currentUser.userId == comment.userId}">
			<div>
				<b>${comment.userName}</b><br>
				<div contenteditable="true">
					<span style="display: block" id="comment_${comment.commentId}">${comment.content}</span>
				</div>
			</div>

			<button onClick="updateComment('${comment.commentId}')">
				<fmt:message key="btn.update" />
			</button>
			<button onClick="deleteComment('${comment.commentId}')">
				<fmt:message key="btn.delete" />
			</button>
		</c:if>
	</div>
</c:forEach>

<script>
	function togglePopup() {
		var popup = document.getElementById("myPopup");
		popup.classList.toggle("show");
	}

	function updateComment(commentId) {
		var comment = document.getElementById("comment_" + commentId).textContent;
		if (comment == "") {
			alert("コンテンツは必須です");
		} else {
			post('/updateComment', {commentId: commentId, content: comment});
		}
	}

	function deleteComment(commentId) {
		var result = confirm('次の処理を続けますか？');

		if(result) {
			post('/deleteComment', {commentId: commentId});
		}
	}

	function post(path, params, method="post"){
		const form = document.createElement("form");
		form.method = method;
		form.action = path;

		for (const key in params){
			if (params.hasOwnProperty(key)){
				const hiddenField = document.createElement("input");
				hiddenField.type = "hidden";
				hiddenField.name = key;
				hiddenField.value = params[key];

				form.appendChild(hiddenField);
			}
		}

		document.body.appendChild(form);
		form.submit();
	}
</script>

<jsp:include page="common/footTag.jsp" />