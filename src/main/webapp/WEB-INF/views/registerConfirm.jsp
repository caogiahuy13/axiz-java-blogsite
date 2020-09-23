<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="commonHeadTag.jsp">
	<jsp:param name="title" value="screen.register.title" />
</jsp:include>

<c:if test="${not empty sessionScope.currentMember}">
	<c:redirect url="top" />
</c:if>

<div style="text-align: left;">
	<div style="margin: 20px 40px">
		<p>※以下の内容でよろしいですか？</p>

		<c:if test="${not empty msg}">
			<p class="error">${msg}</p>
		</c:if>

		<form:form action="register" modelAttribute="rePassForm">
			<fieldset class="label-200">
				<div>
					<label><fmt:message key="form.lbl.loginId" /></label> <input
						value="${sessionScope.registerMember.loginId }"
						style="font-size: 24px" readonly />
				</div>
				<div>
					<label><fmt:message key="form.lbl.name" /></label> <input
						value="${sessionScope.registerMember.name }"
						style="font-size: 24px" readonly />
				</div>
				<div>
					<label><fmt:message key="form.lbl.nickname" /></label> <input
						value="${sessionScope.registerMember.nickname }"
						style="font-size: 24px" readonly />
				</div>
				<div>
					<label><fmt:message key="form.lbl.gender" /></label>
					<c:if test="${sessionScope.registerMember.gender == 1}">
						<input value="男" style="font-size: 24px" readonly />
					</c:if>
					<c:if test="${sessionScope.registerMember.gender == 2}">
						<input value="女" style="font-size: 24px" readonly />
					</c:if>
				</div>
				<div>
					<label><fmt:message key="form.lbl.birthdate" /></label> <input
						value="${sessionScope.registerMember.birthdate }"
						style="font-size: 24px" readonly />
				</div>
				<div>
					<label><fmt:message key="form.lbl.rePassword" /></label>
					<form:input path="rePass" type="password" style="font-size: 24px" />
					<form:errors path="rePass" cssStyle="color: red" />
				</div>
				<div style="margin-left: 230px;">
					<label></label>
					<form:button class="button" name="register">
						<fmt:message key="btn.register" />
					</form:button>
				</div>
				<div style="margin-left: 172px;">
					<label></label>
					<form:button class="button-link" name="return">
						<fmt:message key="btn.returnToRegister" />
					</form:button>
				</div>
			</fieldset>
		</form:form>
	</div>

</div>

<jsp:include page="commonFootTag.jsp" />