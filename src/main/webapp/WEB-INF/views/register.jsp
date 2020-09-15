<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.Calendar, java.util.Map, java.util.LinkedHashMap"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%
	final int minYear = 1900;
	final int currentYear = Calendar.getInstance().get(Calendar.YEAR);

	Map<String, Integer> years = new LinkedHashMap<String, Integer>();

	for (int i = currentYear; i >= minYear; i--) {
		years.put(String.valueOf(i), i);
	}
%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.register.title" />
</jsp:include>

<c:if test="${not empty sessionScope.currentUser}">
	<c:redirect url="top" />
</c:if>

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
		<div>
			<label><fmt:message key="form.lbl.gender" /></label>
			<form:radiobutton path="gender" id="male" value="男" checked="checked" />
			<label for="male" style="width: auto"><fmt:message
					key="form.lbl.male" /></label>
			<form:radiobutton path="gender" id="female" value="女" />
			<label for="female" style="width: auto"><fmt:message
					key="form.lbl.female" /></label>
		</div>
		<div>
			<label><fmt:message key="form.lbl.birthYear" /></label>
			<form:select path="birthYear">
				<form:options items="<%=years%>" />
			</form:select>
		</div>
		<div>
			<form:input type="date" path=""/>
		</div>
	</fieldset>
	<form:button>
		<fmt:message key="btn.register" />
	</form:button>
</form:form>

<br>

<a href="/login"><fmt:message key="btn.returnToLogin" /></a>

<jsp:include page="common/footTag.jsp" />