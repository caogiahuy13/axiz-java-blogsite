<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.top.title" />
</jsp:include>

<form:form action="/top" modelAttribute="searchForm">
	<form:input path="keyword" />
	<form:radiobutton id="all" path="searchType" value="all"
		checked="checked" />
	<label for="all"><fmt:message key="form.lbl.all" /></label>
	<c:if test="${not empty sessionScope.currentMember }">
		<form:radiobutton id="favorites" path="searchType" value="favorites" />
		<label for="favorites"><fmt:message key="form.lbl.favorites" /></label>
	</c:if>
	<form:radiobutton id="ranking" path="searchType" value="ranking" />
	<label for="ranking"><fmt:message key="form.lbl.ranking" /></label>
	<br>
	<form:button>
		<fmt:message key="btn.search" />
	</form:button>
</form:form>

<c:if test="${not empty msg}">
	<p class="error">${msg}</p>
</c:if>

<c:forEach var="article" items="${articles}">
	<c:set var="article" value="${article}" scope="request" />
	<jsp:include page="common/articleCard.jsp" />
</c:forEach>

<jsp:include page="common/footTag.jsp" />


