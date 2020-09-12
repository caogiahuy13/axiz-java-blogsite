<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.article.title" />
</jsp:include>

<h2>${fn:escapeXml(article.title)}</h2>

<p style="white-space: pre-line">${fn:escapeXml(article.content)}</p>

<div class="popup" onclick="togglePopup()">
	<span style="color: blue">${reactions}<i
		class="fa fa-thumbs-o-up"></i></span><span class="popuptext" id="myPopup">
		<c:forEach var="user" items="${reactedUsers}">
			<span>${user.userName}</span>
			<br>
		</c:forEach>
	</span>
</div>


<c:if test="${sessionScope.currentUser.userId != article.userId}">
	<form action="reaction" method="post">
		<input type="hidden" name="articleIdStr" value="${article.articleId}" />
		<input type="hidden" name="stampIdStr" value="1" />
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
				<fmt:message key="btn.update" />
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

	function post(path, params, method='post') {
		// The rest of this code assumes you are not using a library.
		// It can be made less wordy if you use one.
		const form = document.createElement('form');
		form.method = method;
		form.action = path;

		for (const key in params) {
		  if (params.hasOwnProperty(key)) {
		    const hiddenField = document.createElement('input');
		    hiddenField.type = 'hidden';
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