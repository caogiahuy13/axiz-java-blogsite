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

<p>※情報を入力してください</p>

<c:if test="${not empty msg}">
	<p class="error">${msg}</p>
</c:if>

<form:form action="registerConfirm" modelAttribute="registerForm">
	<fieldset class="label-130">
		<div>
			<label><fmt:message key="form.lbl.loginId" /></label>
			<form:input path="loginId" />
			<form:errors path="loginId" cssStyle="color: red" />
		</div>
		<div>
			<label><fmt:message key="form.lbl.name" /></label>
			<form:input path="name" />
			<form:errors path="name" cssStyle="color: red" />
		</div>
		<div>
			<label><fmt:message key="form.lbl.nickname" /></label>
			<form:input path="nickname" />
			<form:errors path="nickname" cssStyle="color: red" />
		</div>
		<div>
			<label><fmt:message key="form.lbl.gender" /></label>
			<form:radiobutton path="gender" id="male" value="1" checked="checked" />
			<label for="male" style="width: auto"><fmt:message
					key="form.lbl.male" /></label>
			<form:radiobutton path="gender" id="female" value="2" />
			<label for="female" style="width: auto"><fmt:message
					key="form.lbl.female" /></label>
		</div>
		<div>
			<label><fmt:message key="form.lbl.birthdate" /></label>
			<form:input type="date" path="birthdate" value="2000-01-01" />
			<form:errors path="birthdate" cssStyle="color: red" />
		</div>
		<div>
			<label><fmt:message key="form.lbl.password" /></label>
			<form:input path="password" type="password" />
			<form:errors path="password" cssStyle="color: red" />
		</div>
	</fieldset>
	<form:button>
		<fmt:message key="btn.register" />
	</form:button>
</form:form>

<a href="/login"><fmt:message key="btn.return" /></a>

<jsp:include page="common/footTag.jsp" />