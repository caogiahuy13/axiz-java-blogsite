<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.login.title" />
</jsp:include>

<c:if test="${not empty sessionScope.currentMember}">
	<c:redirect url="top" />
</c:if>



<div style="text-align: left">
	<div style="margin: 20px 40px">
		<c:if test="${not empty msg}">
			<p class="error">${msg}</p>
		</c:if>

		<form:form action="login" modelAttribute="loginForm">
			<fieldset class="label-160">
				<div>
					<label><fmt:message key="form.lbl.loginId" />：</label>
					<form:input path="loginId" style="font-size: 24px" />
					<form:errors path="loginId" cssStyle="color: red" />
				</div>
				<div>
					<label><fmt:message key="form.lbl.password" />：</label>
					<form:input path="password" type="password" style="font-size: 24px" />
					<form:errors path="password" cssStyle="color: red" />
				</div>
				<div style="margin-left: 3px;">
					<label></label>
					<form:button class="button">
						<fmt:message key="btn.login" />
					</form:button>
				</div>
				<div style="margin-left: 12px;">
					<label></label> <a href="register"><fmt:message
							key="btn.toRegister" /></a>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

<jsp:include page="common/footTag.jsp" />
