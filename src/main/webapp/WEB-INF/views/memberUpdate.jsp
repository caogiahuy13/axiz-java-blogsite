<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="commonHeadTag.jsp">
	<jsp:param name="title" value="screen.updateMember.title" />
</jsp:include>

<%@ include file="commonLoginCheck.jsp"%>

<div style="text-align: left;">
	<div style="margin: 20px 40px">
		<h2>
			<fmt:message key="screen.updateMember.caption" />
		</h2>
		<c:if test="${not empty msg}">
			<p class="error">${msg}</p>
		</c:if>
		<form:form action="updateMember" modelAttribute="updateMemberForm">
			<fieldset class="label-200">
				<div>
					<form:input id="loginId" type="hidden" path="memberId" />
				</div>
				<div>
					<label><fmt:message key="form.lbl.loginId" /></label>
					<form:input path="loginId" style="font-size: 24px;" />
					<form:errors path="loginId" cssStyle="color: red" />
				</div>
				<div>
					<label><fmt:message key="form.lbl.nickname" /></label>
					<form:input path="nickname" style="font-size: 24px;" />
					<form:errors path="nickname" cssStyle="color: red" />
				</div>
				<div>
					<label><fmt:message key="form.lbl.password" /></label>
					<form:input path="password" type="password"
						style="font-size: 24px;" />
					<form:errors path="password" cssStyle="color: red" />
				</div>
				<div>
					<label><fmt:message key="form.lbl.rePassword" /></label>
					<form:input path="rePassword" type="password"
						style="font-size: 24px;" />
					<form:errors path="rePassword" cssStyle="color: red" />
				</div>
				<div>
					<label><fmt:message key="form.lbl.introduction" /></label>
					<form:textarea path="introduction" rows="6" cols="40"
						style="vertical-align: top; resize: none; font-size: 24px;" />
				</div>
				<c:if test="${sessionScope.totalReactions >= goldMilestone }">
					<div>
						<label><fmt:message key="form.lbl.mySpace" /></label>
						<form:textarea path="mySpace" rows="6" cols="40"
							style="vertical-align: top; resize: none; font-size: 24px" />
					</div>
				</c:if>
				<form:button class="button" style="margin-left: 710px;">
					<fmt:message key="btn.update" />
				</form:button>
			</fieldset>
		</form:form>
	</div>
</div>

<jsp:include page="commonFootTag.jsp" />