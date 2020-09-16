<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.Calendar, java.util.Map, java.util.LinkedHashMap"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.updateMember.title" />
</jsp:include>

<%@ include file="common/checkLogin.jsp"%>

<c:if test="${not empty msg}">
	<p class="error">${msg}</p>
</c:if>

<form:form action="updateMember" modelAttribute="updateMemberForm">
	<fieldset class="label-140">
		<div>
			<form:input type="hidden" path="memberId" />
		</div>
		<div>
			<label><fmt:message key="form.lbl.loginId" /></label>
			<form:input path="loginId" />
			<form:errors path="loginId" cssStyle="color: red" />
		</div>
		<div>
			<label><fmt:message key="form.lbl.nickname" /></label>
			<form:input path="nickname" />
			<form:errors path="nickname" cssStyle="color: red" />
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
			<label style="vertical-align: top"><fmt:message
					key="form.lbl.introduction" /></label>
			<form:textarea path="introduction" rows="6" cols="30" />
		</div>
		<c:if test="${sessionScope.totalReactions >=15 }">
			<div>
				<label style="vertical-align: top"><fmt:message
						key="form.lbl.mySpace" /></label>
				<form:textarea path="mySpace" rows="6" cols="30" />
			</div>
		</c:if>
	</fieldset>

	<form:button>
		<fmt:message key="btn.update" />
	</form:button>
	<a href="javascript:history.back()"><fmt:message key="btn.return" /></a>
</form:form>

<jsp:include page="common/footTag.jsp" />