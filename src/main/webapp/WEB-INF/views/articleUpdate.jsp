<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="commonHeadTag.jsp">
	<jsp:param name="title" value="screen.createArticle.title" />
</jsp:include>

<%@ include file="commonLoginCheck.jsp"%>

<h3>
	<fmt:message key="screen.editArticle.caption" />
</h3>

<form:form action="editArticle" modelAttribute="editArticleForm">
	<div>
		<form:hidden path="articleId" />
	</div>
	<div>
		<label><fmt:message key="form.lbl.title" /></label>
		<form:input path="title" />
		<form:errors path="title" cssStyle="color: red" />
	</div>
	<div>
		<label><fmt:message key="form.lbl.content" /></label><br>
		<form:textarea path="content" rows="4" cols="40" />
		<form:errors path="content" cssStyle="color: red" />
	</div>
	<form:button>
		<fmt:message key="btn.update" />
	</form:button>
</form:form>

<jsp:include page="commonFootTag.jsp" />