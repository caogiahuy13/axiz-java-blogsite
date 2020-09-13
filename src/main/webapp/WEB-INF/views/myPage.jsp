<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.myPage.title" />
</jsp:include>

<%@ include file="common/checkLogin.jsp"%>

<fieldset class="label-120">
	<div>
		<label><fmt:message key="form.lbl.loginId" /></label>${sessionScope.currentUser.loginId}
	</div>
	<div>
		<label><fmt:message key="form.lbl.userName" /></label>${sessionScope.currentUser.userName}
	</div>
	<div>
		<label><fmt:message key="form.lbl.gender" /></label>${sessionScope.currentUser.gender}
	</div>
	<div>
		<label><fmt:message key="form.lbl.birthYear" /></label>${sessionScope.currentUser.birthYear}
	</div>
	<div>
		<label><fmt:message key="form.lbl.introduction" /></label>${sessionScope.currentUser.introduction}
	</div>
</fieldset>

<form action="updateMember">
	<button>
		<fmt:message key="btn.updateMember" />
	</button>
</form>
<br>

<c:if test="${sessionScope.totalReactions >=5 }">
	<form action="analytics">
		<button>
			<fmt:message key="btn.analytics" />
		</button>
	</form>
</c:if>

<form action="deleteMember">
	<button>
		<fmt:message key="btn.deleteMember" />
	</button>
</form>
<br>

<jsp:include page="common/footTag.jsp" />