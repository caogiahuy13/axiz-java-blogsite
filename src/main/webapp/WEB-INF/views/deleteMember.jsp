<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.deleteMember.title" />
</jsp:include>

<%@ include file="common/checkLogin.jsp"%>

<fmt:message key="member.delete.confirm"/>

<form action = "/deleteMember" method = "post">
	<input type="hidden" name="memberId" value="${sessionScope.memberId}">
	<button><fmt:message key="btn.deleteMember"/></button>
	<a href="javascript:history.back()"><fmt:message key="btn.return"/></a>
</form>

<jsp:include page="common/footTag.jsp" />