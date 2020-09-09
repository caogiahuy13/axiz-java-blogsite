<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="screen.register.title" /></title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<c:if test="${not empty msg}">
		<p class="error">${msg}</p>
	</c:if>

	<form:form action="register" modelAttribute="registerForm">
		<fieldset class="label-130">
			<div>
				<label><fmt:message key="form.lbl.loginId" /></label>
				<form:input path="loginId" />
				<form:errors path="loginId" cssStyle="color: red" />
			</div>
			<div>
				<label><fmt:message key="form.lbl.userName" /></label>
				<form:input path="userName" />
				<form:errors path="userName" cssStyle="color: red" />
			</div>
			<div>
				<label><fmt:message key="form.lbl.password" /></label>
				<form:input path="password" type="password" />
				<form:errors path="password" cssStyle="color: red" />
			</div>
			<div>
				<label><fmt:message key="form.lbl.rePassword" /></label>
				<form:input path="rePassword" type="password" />
				<form:errors path="rePassword" cssStyle="color: red" />
			</div>
		</fieldset>
		<form:button>
			<fmt:message key="btn.register" />
		</form:button>
	</form:form>

	<br>

	<a href="/login"><fmt:message key="btn.returnToLogin" /></a>

</body>
</html>
