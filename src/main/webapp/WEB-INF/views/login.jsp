<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.login.title" />
</jsp:include>

<c:if test="${not empty sessionScope.currentUser}">
	<c:redirect url="top" />
</c:if>

<c:if test="${not empty msg}">
	<p class="error">${msg}</p>
</c:if>

<form:form action="login" modelAttribute="loginForm">
	<fieldset>
		<div>
			<label><fmt:message key="form.lbl.loginId" /></label>
			<form:input path="loginId"/>
			<form:errors path="loginId" cssStyle="color: red" />
		</div>
		<div>
			<label><fmt:message key="form.lbl.password" /></label>
			<form:input path="password" type="password" />
			<form:errors path="password" cssStyle="color: red" />
		</div>
	</fieldset>
	<form:button>
		<fmt:message key="btn.login" />
	</form:button>
</form:form>

<br>

<a href="/register"><fmt:message key="btn.toRegister" /></a>

<jsp:include page="common/footTag.jsp" />
