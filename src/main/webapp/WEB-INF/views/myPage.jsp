<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.myPage.title" />
</jsp:include>

<h2>
	<fmt:message key="myPage" />
</h2>

<fieldset class="label-120">
	<div>
		<label><fmt:message key="form.lbl.loginId" />:</label>${member.loginId}
	</div>
	<div>
		<label><fmt:message key="form.lbl.nickname" />:</label>${member.nickname}
	</div>
	<div>
		<label><fmt:message key="form.lbl.totalReactions" />:</label>${memberTotalReactions}
	</div>
	<div>
		<label><fmt:message key="form.lbl.ranking" />:</label>${memberRank}
	</div>
	<div>
		<label><fmt:message key="form.lbl.introduction" />:</label>${member.introduction}
	</div>
</fieldset>

<c:if test="${not empty sessionScope.currentMember}">
	<c:if test="${sessionScope.totalReactions >= goldMilestone }">
		<div>
			<label><b style="color: red"><fmt:message
						key="form.lbl.mySpace" /></b></label><b>${sessionScope.currentmember.mySpace}</b>
		</div>
	</c:if>
	<form action="updateMember">
		<button>
			<fmt:message key="btn.updateMember" />
		</button>
	</form>

	<form action="deleteMember">
		<button>
			<fmt:message key="btn.deleteMember" />
		</button>
	</form>

	<form action="analytics">
		<button>
			<fmt:message key="btn.analytics" />
		</button>
	</form>

	<form action="createArticle">
		<button>
			<fmt:message key="btn.createArticle" />
		</button>
	</form>

</c:if>

<p>投稿記事一覧</p>

<c:if test="${not empty articles }">
	<c:forEach var="article" items="${articles}">
		<c:set var="article" value="${article}" scope="request" />
		<jsp:include page="common/articleCard.jsp" />
	</c:forEach>
	<br>
	<div class="pagination" style="text-align: center; width: 50%;">
		<c:if test="${articleCurPage > 1}">
			<a href="/myPage?id=${member.memberId }&pageNumber=${articleCurPage - 1}">&laquo;</a>
		</c:if>
		<c:if test="${articleCurPage == 1}">
			<a href="/myPage?id=${member.memberId }&pageNumber=${articleCurPage}">&laquo;</a>
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
			<a href="/myPage?id=${member.memberId }&pageNumber=${articleCurPage + 1}">&raquo;</a>
		</c:if>
		<c:if test="${articleCurPage == articleMaxPage}">
			<a href="/myPage?id=${member.memberId }&pageNumber=${articleCurPage}">&raquo;</a>
		</c:if>
	</div>
</c:if>
<br>


<jsp:include page="common/footTag.jsp" />