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

<div
	style="background-color: white; margin: 80px 120px 50px 120px; padding: 30px; text-align: left; position: relative">
	<div style="margin: 20px 40px">
		<p>※情報を入力してください</p>

		<c:if test="${not empty msg}">
			<p class="error">${msg}</p>
		</c:if>

		<form:form action="registerConfirm" modelAttribute="registerForm">
			<fieldset class="label-200">
				<div>
					<label><fmt:message key="form.lbl.loginId" />：</label>
					<form:input path="loginId" style="font-size: 24px" />
					<form:errors path="loginId" cssStyle="color: red" />
				</div>
				<div>
					<label><fmt:message key="form.lbl.name" />：</label>
					<form:input path="name" style="font-size: 24px" />
					<form:errors path="name" cssStyle="color: red" />
				</div>
				<div>
					<label><fmt:message key="form.lbl.nickname" />：</label>
					<form:input path="nickname" style="font-size: 24px" />
					<form:errors path="nickname" cssStyle="color: red" />
				</div>
				<div>
					<label><fmt:message key="form.lbl.gender" />：</label> <label
						class="radio-container" for="male" style="display: inline;"><fmt:message
							key="form.lbl.male" /> <form:radiobutton path="gender" id="male"
							value="1" checked="checked" /> <span class="radio-checkmark"></span></label>
					<label class="radio-container" for="female"
						style="display: inline;"><fmt:message
							key="form.lbl.female" /> <form:radiobutton path="gender"
							id="female" value="2" /> <span class="radio-checkmark"></span></label>
				</div>
				<div>
					<label><fmt:message key="form.lbl.birthdate" />：</label>
					<form:input type="date" path="birthdate" style="font-size: 24px" />
					<form:errors path="birthdate" cssStyle="color: red" />
				</div>
				<div>
					<label><fmt:message key="form.lbl.password" />：</label>
					<form:input path="password" type="password" style="font-size: 24px" />
					<form:errors path="password" cssStyle="color: red" />
				</div>
				<br>
				<div style="margin-left: 230px;">
					<label></label>
					<form:button class="button">
						<fmt:message key="btn.register" />
					</form:button>
				</div>
				<div style="margin-left: 244px;">
					<label></label> <a href="/login"><fmt:message key="btn.return" /></a>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

<jsp:include page="commonFootTag.jsp" />