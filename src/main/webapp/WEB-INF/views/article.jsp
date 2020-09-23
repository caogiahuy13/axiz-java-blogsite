<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="commonHeadTag.jsp">
	<jsp:param name="title" value="screen.article.title" />
</jsp:include>

<div
	style="background-color: white; margin: 80px 120px 50px 120px; text-align: left; padding: 30px;">

	<c:if
		test="${articleMemberReactionCount >= goldMilestone && reactionCount >= trophyMilestone}">
		<p>
			MySpace: <b>${articleMemberMySpace}</b>
		</p>
	</c:if>

	<h2>
		<span>${fn:escapeXml(article.title)}</span>
		<c:if test="${reactionCount >= trophyMilestone}">
			🏆
		</c:if>
	</h2>
	<p style="white-space: pre-line">${fn:escapeXml(article.content)}</p>

	<div class="popup" onclick="togglePopup()">
		<c:forEach var="entry" items="${reactions}">
			<c:if test="${entry.value > 0}">
				<span style="color: blue"> ${entry.value}
					${stampIcon[entry.key]} </span>
			</c:if>
		</c:forEach>
		<span class="popuptext" id="myPopup"> <c:forEach var="member"
				items="${reactedMembers}">
				<span>${member.name}</span>
				<br>
			</c:forEach>
		</span>
	</div>
	<div style="height: 10px"></div>

	<c:if test="${sessionScope.currentMember.memberId != article.memberId}">
		<c:choose>
			<c:when test="${articleMemberReactionCount >= bronzeMilestone}">
				<c:forEach var="entry" items="${stampIcon}">
					<c:if test="${entry.key != 1 }">
						<jsp:include page="commonReaction.jsp">
							<jsp:param name="stampId" value="${entry.key}" />
							<jsp:param name="stampName" value="${entry.value}" />
						</jsp:include>
					</c:if>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<jsp:include page="commonReaction.jsp">
					<jsp:param name="stampId" value="1" />
					<jsp:param name="stampName" value="&#x1f44d" />
				</jsp:include>
			</c:otherwise>
		</c:choose>
		<br>
		<br>
	</c:if>


	<c:if test="${sessionScope.currentMember.memberId == article.memberId}">
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
			<c:if
				test="${sessionScope.currentMember.memberId != comment.memberId}">
				<div>
					<b>${comment.name}</b><br>
					<div>
						<span>${comment.content}</span>
					</div>
				</div>
			</c:if>

			<c:if
				test="${sessionScope.currentMember.memberId == comment.memberId}">
				<div>
					<b>${comment.name}</b><br>
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
</div>
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
<jsp:include page="commonFootTag.jsp" />