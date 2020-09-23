<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="commonHeadTag.jsp">
	<jsp:param name="title" value="screen.top.title" />
</jsp:include>

<div
	style="margin: 80px 120px 50px 120px; text-align: left; padding: 30px; font-size: 24px; background-color: white;">
	<p>
		<fmt:message key="form.lbl.search" />
		：
	</p>
	<form:form action="/top" modelAttribute="searchForm">
		<form:radiobutton id="newest" path="sortType" value="newest" />
		<label for="newest"><fmt:message key="form.lbl.newestOrder" /></label>
		<form:radiobutton id="ranking" path="sortType" value="ranking" />
		<label for="ranking"><fmt:message key="form.lbl.rankingOrder" /></label>
		<br>
		<c:if test="${not empty sessionScope.currentMember }">
			<form:checkbox id="favorites" path="searchType" value="favorites" />
			<label for="favorites"><fmt:message
					key="form.lbl.sortType.favorites" /></label>
			<br>
		</c:if>
		<form:input path="keyword" />
		<form:button name="keywordSearch">
			<fmt:message key="btn.keywordSearch" />
		</form:button>
		<form:button name="allSearch">
			<fmt:message key="btn.allSearch" />
		</form:button>
	</form:form>

	<c:if test="${not empty msg}">
		<p class="error">${msg}</p>
	</c:if>

	<c:forEach var="article" items="${articles}">
		<c:set var="article" value="${article}" scope="request" />
		<jsp:include page="commonArticleCard.jsp" />
	</c:forEach>
</div>
<jsp:include page="commonFootTag.jsp" />