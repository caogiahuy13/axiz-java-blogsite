<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.myPage.title" />
</jsp:include>

<div class="center">
	<h2>
		<table align="center" style="border: none;">
			<tr style="border: none;">
				<td style="border: none; width: 1000px;" align="right"><strong>${member.nickname }&nbsp
						🥇&nbsp さんのマイページ</strong></td>
				<td style="border: none; width: 200px;" align="right">
					<form action="deleteMember">
						<button class="button">
							<fmt:message key="btn.deleteMember" />
						</button>
					</form>
				</td>
				<td style="border: none; width: 200px;" align="left">
					<form action="updateMember">
						<button class="button">
							<fmt:message key="btn.update" />
						</button>
					</form>
				</td>
			</tr>
		</table>
	</h2>

	<table align="center" class="myPageTable">
		<tr>
			<td style="border-bottom: 3px #4caf50 solid;"><strong
				style="color: #4caf50;"><fmt:message key="form.lbl.loginId" /></strong></td>
			<td colspan="4"
				style="width: 850px; border-bottom: 3px #4caf50 solid;">${member.loginId }</td>
		</tr>
		<tr>
			<td style="border-bottom: 3px #4caf50 solid;"><strong
				style="color: #4caf50;"><fmt:message
						key="form.lbl.nickname" /></strong></td>
			<td colspan="4" style="border-bottom: 3px #4caf50 solid;">${member.nickname }&nbsp
				🥇</td>
		</tr>
		<tr>
			<td style="border-bottom: 3px #4caf50 solid;"><strong
				style="color: #4caf50;"><fmt:message
						key="form.lbl.totalReactions" /></strong></td>
			<td colspan="4" style="border-bottom: 3px #4caf50 solid;">${memberTotalReactions}</td>
		</tr>
		<tr>
			<td style="border-bottom: 3px #4caf50 solid;"><strong
				style="color: #4caf50;"><fmt:message
						key="form.lbl.totalRanking" /></strong></td>
			<td colspan="4" style="border-bottom: 3px #4caf50 solid;">${memberRank}位</td>
		</tr>
		<tr>
			<td style="border-bottom: 3px #4caf50 solid;"><strong
				style="color: #4caf50;"><fmt:message
						key="form.lbl.introduction" /></strong></td>
			<td colspan="4" align="left"
				style="border-bottom: 3px #4caf50 solid;">${member.introduction }</td>
		</tr>
		<c:if test="${sessionScope.totalReactions >= goldMilestone }">
			<tr>
				<td rowspan="2" style="border-bottom: 3px #4caf50 solid;"><strong
					style="color: #4caf50;"><fmt:message
							key="form.lbl.mySpace" /></strong></td>
				<td colspan="4" rowspan="2" align="left"
					style="border-bottom: 3px #4caf50 solid;">${member.mySpace }</td>
			</tr>
		</c:if>
	</table>

	<c:if test="${sessionScope.totalReactions >= goldMilestone }">
		<p>
		<form action="analytics">
			<button class="button" type="submit">お気に入り分析</button>
		</form>
		</p>
	</c:if>

	<p>
	<form action="createArticle">
		<button class="button" type="submit">記事投稿</button>
	</form>
	</p>

	<br>

	<h2>投稿記事一覧</h2>

	<c:if test="${not empty articles }">
		<c:forEach var="article" items="${articles}">
			<c:set var="article" value="${article}" scope="request" />
			<jsp:include page="common/articleCard.jsp" />
		</c:forEach>
		<br>
		<div class="pagination" style="text-align: center; width: 100%;">
			<c:if test="${articleCurPage > 1}">
				<a
					href="/myPage?id=${member.memberId }&pageNumber=${articleCurPage - 1}">&laquo;</a>
			</c:if>
			<c:if test="${articleCurPage == 1}">
				<a
					href="/myPage?id=${member.memberId }&pageNumber=${articleCurPage}">&laquo;</a>
			</c:if>
			<c:forEach begin="1" end="${articleMaxPage }" varStatus="loop">
				<c:if test="${articleCurPage == loop.index}">
					<a href="/myPage?id=${member.memberId }&pageNumber=${loop.index}"
						class="active">${loop.index}</a>
				</c:if>
				<c:if test="${articleCurPage != loop.index}">
					<a href="/myPage?id=${member.memberId }&pageNumber=${loop.index}">${loop.index}</a>
				</c:if>
			</c:forEach>
			<c:if test="${articleCurPage < articleMaxPage}">
				<a
					href="/myPage?id=${member.memberId }&pageNumber=${articleCurPage + 1}">&raquo;</a>
			</c:if>
			<c:if test="${articleCurPage == articleMaxPage}">
				<a
					href="/myPage?id=${member.memberId }&pageNumber=${articleCurPage}">&raquo;</a>
			</c:if>
		</div>
	</c:if>
	<br>
</div>

<jsp:include page="common/footTag.jsp" />