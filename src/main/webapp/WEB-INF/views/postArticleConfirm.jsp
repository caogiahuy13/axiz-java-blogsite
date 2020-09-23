<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="commonHeadTag.jsp">
	<jsp:param name="title" value="screen.postArticleConfirm.title" />
</jsp:include>

<%@ include file="commonLoginCheck.jsp"%>

<div
	style="background-color: white; margin: 80px 120px 50px 120px; text-align: center; padding: 30px;">

	<h2>記事投稿確認</h2>

	<div
		style="margin-top: 3%; margin-left: 3%; font-size: 30px; text-align: left;">
		※以下の内容で投稿してもよろしいですか？</div>
	<br>

	<h2 style="text-align: center;">投稿イメージ</h2>

	<hr style="height: 3px; background-color: #4caf50;">
	<br>

	<div style="margin: 10px" class="center">
		<h2 class="center"
			style="top: 50%; width: 100%; font-size: 60px; margin: 0; resize: none; border-style: None; bottom: 0; padding: 0;">${article.title }</h2>
		<hr style="width: 45%;">
	</div>

	<p style="margin-left: 15%; font-size: 24px; text-align: left;">
		<span>${writer.nickname} ${rankIcon[writerRank]}</span> <span>＠${writer.loginId}</span>
		<span><fmt:formatDate type="date" value="${article.createdAt}" /></span>
	</p>

	<div style="margin: 10px" class="center">
		<div style="margin: 10px" class="center">
			<div class="card"
				style="text-align: left; margin: 0 auto; font-size: 24px; white-space: pre-line; border: 1px solid; border-width: 3px; border-color: yellowgreen; color: #000;">
				${article.content }</div>
		</div>
	</div>

	<div style="height: 10px; text-align: right; margin-right: 13%;">
		<button class="unstyled-button">
			<span style="opacity: 50%; font-size: 24px;"> &#x1f602</span>
		</button>
		<button class="unstyled-button">
			<span style="opacity: 50%; font-size: 24px;"> &#x1f625</span>
		</button>
		<button class="unstyled-button">
			<span style="opacity: 50%; font-size: 24px;"> &#x1f632</span>
		</button>
		<button class="unstyled-button">
			<span style="opacity: 50%; font-size: 24px;"> &#x1f917</span>
		</button>
	</div>

	<br>
	<div style="text-align: right; margin-right: 13%;">
		<button class="button">変更</button>
		<button class="button">削除</button>
	</div>
	<br>
	<div style="margin: 10px" class="center">
		<textarea col="15" rows="2" maxlength="150"
			style="text-align: left; height: 80px; width: 90%; font-size: 24px; resize: none;"
			readonly></textarea>
	</div>
	<div style="width: 95%; text-align: right;">
		<a class="btn btn--orange btn--cubic btn--shadow" type="button">コメント</a>
	</div>

	<br>

	<hr style="height: 3px; background-color: #4caf50;">

	<form action="createArticle" method="post">
		<br>
		<div style="width: 95%; text-align: right;">
			<button class="button" type="submit">
				<fmt:message key="btn.post" />
			</button>
			<div style="margin-right: 1%;">
				<a href="javascript:history.back()"><fmt:message
						key="btn.return" /></a>
			</div>
		</div>
	</form>

</div>
<jsp:include page="commonFootTag.jsp" />