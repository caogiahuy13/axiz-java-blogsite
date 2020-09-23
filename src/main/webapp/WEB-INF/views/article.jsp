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

	<div style="margin: 10px" class="center">
		<c:if
			test="${articleMemberReactionCount >= goldMilestone && reactionCount >= trophyMilestone}">
			<div class="fancy-border"
				style="width: 80%; font-size: 30px; text-align: left; margin-left: 8%;">${articleMemberMySpace}</div>
			<br>
		</c:if>
		<h2 class="center" style="font-size: 60px">${fn:escapeXml(article.title)}
			<c:if test="${reactionCount >= trophyMilestone}">
				<span>🏆</span>
			</c:if>
		</h2>
		<hr style="width: 45%;">
	</div>

	<p style="margin-left: 15%;">
		<span>${writer.nickname }${rankIcon[writerRank]}</span> <span>＠${writer.loginId }</span>
		<span><fmt:formatDate type="date" value="${article.createdAt}" /></span>
	</p>

	<div style="margin: 10px" class="center">
		<div class="card"
			style="text-align: left; padding: 8px; margin: 8px auto; font-size: 24px; white-space: pre-line; border: 1px solid; border-width: 3px; border-color: yellowgreen; color: #000;">
			${fn:escapeXml(article.content)}</div>

		<div style="text-align: left; margin-left: 15%">
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
		</div>

		<div style="text-align: right; margin-right: 12%">
			<c:if
				test="${sessionScope.currentMember.memberId != article.memberId}">
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
		</div>

		<c:if
			test="${sessionScope.currentMember.memberId == article.memberId}">
			<div style="text-align: right; margin-right: 12%;">
				<form action="editArticle" style="display: inline">
					<input type="hidden" name="id" value="${article.articleId}" />
					<button class="button" style="margin: 5px; float: right;">
						<fmt:message key="btn.update" />
					</button>
				</form>
				<form action="deleteArticle" style="display: inline">
					<input type="hidden" name="id" value="${article.articleId}" />
					<button class="button" style="margin: 5px; float: right;">
						<fmt:message key="btn.delete" />
					</button>
				</form>
			</div>
			<br>
			<br>
		</c:if>


		<form id="commentForm" action="comment" method="post">
			<input type="hidden" name="articleIdStr" value="${article.articleId}">
			<div style="margin: 10px" class="center">
				<textarea maxlength="150"
					style="text-align: left; height: 80px; width: 90%; font-size: 24px; resize: none;"
					name="contentStr" required></textarea>
			</div>
			<div style="width: 95%; display: flex; justify-content: flex-end;">
				<input class="btn btn--orange btn--shadow" type="submit"
					value=<fmt:message key="btn.comment" />></input>

			</div>
		</form>

		<br>
		<hr style="height: 3px; background-color: #4caf50;">
		<h2>コメント一覧</h2>


		<c:forEach var="comment" items="${comments}">
			<c:if
				test="${sessionScope.currentMember.memberId != comment.memberId}">
				<div
					style="display: flex; flex-direction: row; margin: 40px; padding: 0px 10px">
					<div style="text-align: left; flex-grow: 1">
						<p style="margin: 0px; padding-left: 120px;">
							<b>${comment.nickname}</b>
						</p>
						<p style="margin: 0px; padding-left: 120px;">${comment.content}</p>
					</div>
				</div>
			</c:if>

			<c:if
				test="${sessionScope.currentMember.memberId == comment.memberId}">
				<div
					style="width: 80%; display: flex; flex-direction: row; margin: 40px; padding: 0px 10px; text-align: right;">
					<div style="text-align: left; flex-grow: 1">
						<p style="margin: 0px; padding-left: 120px">
							<b>${comment.nickname}</b>
						</p>
						<div contenteditable="true" style="margin-left: 120px;">
							<p style="display: block; margin: 0px;"
								id="comment_${comment.commentId}">${comment.content}</p>
						</div>
					</div>

					<div
						style="justify-content: center; align-items: center; display: flex;">
						<a class="btn btn--orange btn--cubic btn--shadow" type="submit"
							style="margin: 5px" href="javascript:{}"
							onclick="updateComment('${comment.commentId}')"><fmt:message
								key="btn.update" /></a>
					</div>

					<div
						style="justify-content: center; align-items: center; display: flex;">
						<a class="btn btn--orange btn--cubic btn--shadow" type="submit"
							style="margin: 5px" href="javascript:{}"
							onclick="deleteComment('${comment.commentId}')"><fmt:message
								key="btn.delete" /></a>
					</div>

				</div>
			</c:if>
			<hr style="text-align: left; width: 80%;">
		</c:forEach>
	</div>
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