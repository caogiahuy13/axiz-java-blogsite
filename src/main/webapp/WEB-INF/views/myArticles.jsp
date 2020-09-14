<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.myArticles.title" />
</jsp:include>

<%@ include file="common/checkLogin.jsp"%>

<c:if test="${not empty msg}">
	<p class="error">${msg}</p>
</c:if>

<c:forEach var="article" items="${articles}">
	<c:set var="article" value="${article}" scope="request" />
	<jsp:include page="common/articleCard.jsp" />
</c:forEach>

<jsp:include page="common/footTag.jsp" />