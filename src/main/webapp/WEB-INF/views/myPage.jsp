<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.myPage.title" />
</jsp:include>

<%@ include file="common/checkLogin.jsp"%>

<fieldset class="label-120">
	<div>
		<label><fmt:message key="form.lbl.loginId" /></label>${sessionScope.currentUser.loginId}
	</div>
	<div>
		<label><fmt:message key="form.lbl.userName" /></label>${sessionScope.currentUser.userName}
	</div>
	<div>
		<label><fmt:message key="form.lbl.gender" /></label>${sessionScope.currentUser.gender}
	</div>
	<div>
		<label><fmt:message key="form.lbl.birthYear" /></label>${sessionScope.currentUser.birthdate}
	</div>
	<div>
		<label><fmt:message key="form.lbl.introduction" /></label>${sessionScope.currentUser.introduction}
	</div>
	<c:if test="${sessionScope.totalReactions >= goldMilestone }">
		<div>
			<label><b style="color: red"><fmt:message
						key="form.lbl.mySpace" /></b></label><b>${sessionScope.currentUser.mySpace}</b>
		</div>
	</c:if>
</fieldset>

<form action="updateMember">
	<button>
		<fmt:message key="btn.updateMember" />
	</button>
</form>
<br>

<c:if test="${not empty articles }">
	<c:forEach var="article" items="${articles}">
		<c:set var="article" value="${article}" scope="request" />
		<jsp:include page="common/articleCard.jsp" />
	</c:forEach>
	<div class="pagination" style="text-align: center; width: 50%;">
		<c:if test="${articleCurPage > 1}">
			<a href="/myPage?pageNumber=${articleCurPage - 1}">&laquo;</a>
		</c:if>
		<c:if test="${articleCurPage == 1}">
			<a href="/myPage?pageNumber=${articleCurPage}">&laquo;</a>
		</c:if>
		<c:forEach begin="1" end="${articleMaxPage }" varStatus="loop">
			<c:if test="${articleCurPage == loop.index}">
				<a href="/myPage?pageNumber=${loop.index}" class="active">${loop.index}</a>
			</c:if>
			<c:if test="${articleCurPage != loop.index}">
				<a href="/myPage?pageNumber=${loop.index}">${loop.index}</a>
			</c:if>
		</c:forEach>
		<c:if test="${articleCurPage < articleMaxPage}">
			<a href="/myPage?pageNumber=${articleCurPage + 1}">&raquo;</a>
		</c:if>
		<c:if test="${articleCurPage == articleMaxPage}">
			<a href="/myPage?pageNumber=${articleCurPage}">&raquo;</a>
		</c:if>
	</div>
</c:if>
<br>

<form action="deleteMember">
	<button>
		<fmt:message key="btn.deleteMember" />
	</button>
</form>
<br>

<jsp:include page="common/footTag.jsp" />