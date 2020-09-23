<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="commonHeadTag.jsp">
	<jsp:param name="title" value="screen.deleteMember.title" />
</jsp:include>

<%@ include file="commonLoginCheck.jsp"%>

<div
	style="margin: 80px 120px 50px 120px; text-align: center; padding: 30px;">

	<h1>本当に退会してもよろしいですか？</h1>

	<form action="/deleteMember" method="post">
		<input type="hidden" name="memberId" value="${sessionScope.memberId}">
		<button class="button">
			<fmt:message key="btn.deleteMember" />
		</button>

		<p>
			<a href="javascript:history.back()"><fmt:message key="btn.return" /></a>
		</p>
	</form>

</div>


<jsp:include page="commonFootTag.jsp" />