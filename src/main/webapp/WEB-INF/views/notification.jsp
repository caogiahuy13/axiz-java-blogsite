<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.notification.title" />
</jsp:include>

<c:if test="${not empty msg}">
	<h2>${msg}</h2>
</c:if>

<jsp:include page="common/footTag.jsp" />