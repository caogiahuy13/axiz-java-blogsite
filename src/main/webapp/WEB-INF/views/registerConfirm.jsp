<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.register.title" />
</jsp:include>

<c:if test="${not empty sessionScope.currentMember}">
	<c:redirect url="top" />
</c:if>

<p>※以下の内容でよろしいですか？</p>

<c:if test="${not empty msg}">
	<p class="error">${msg}</p>
</c:if>

<form:form action="register" modelAttribute="rePassForm">
	<fieldset class="label-130">
		<div>
			<label><fmt:message key="form.lbl.loginId" /></label> <input
				value="${sessionScope.registerMember.loginId }" readonly />
		</div>
		<div>
			<label><fmt:message key="form.lbl.name" /></label> <input
				value="${sessionScope.registerMember.name }" readonly />
		</div>
		<div>
			<label><fmt:message key="form.lbl.nickname" /></label> <input
				value="${sessionScope.registerMember.nickname }" readonly />
		</div>
		<div>
			<label><fmt:message key="form.lbl.gender" /></label> <input
				value="${sessionScope.registerMember.gender }" readonly />
		</div>
		<div>
			<label><fmt:message key="form.lbl.birthdate" /></label> <input
				value="${sessionScope.registerMember.birthdate }" readonly />
		</div>
		<div>
			<label><fmt:message key="form.lbl.rePassword" /></label>
			<form:input path="rePass" type="password" />
			<form:errors path="rePass" cssStyle="color: red" />
		</div>
	</fieldset>
	<form:button>
		<fmt:message key="btn.register" />
	</form:button>
</form:form>

<a href="/register"><fmt:message key="btn.returnToRegister" /></a>

<jsp:include page="common/footTag.jsp" />