<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="screen.createArticle.title" /></title>
</head>
<body>
	<%@ include file="/WEB-INF/views/checkLogin.jsp"%>

	<%@ include file="/WEB-INF/views/navbar.jsp"%>

	<c:if test="${not empty msg}">
		<p class="error">${msg}</p>
	</c:if>

	<h3>
		<fmt:message key="screen.createArticle.caption" />
	</h3>

	<form:form action="createArticle" modelAttribute="createArticleForm">
		<div>
			<label><fmt:message key="form.lbl.title" /></label>
			<form:input path="title" />
			<form:errors path="title" cssStyle="color: red" />
		</div>
		<div>
			<label><fmt:message key="form.lbl.content" /></label><br>
			<form:textarea path="content" rows="4" cols="40" />
			<form:errors path="content" cssStyle="color: red" />
		</div>
		<form:button>
			<fmt:message key="btn.post" />
		</form:button>
	</form:form>
</body>
</html>