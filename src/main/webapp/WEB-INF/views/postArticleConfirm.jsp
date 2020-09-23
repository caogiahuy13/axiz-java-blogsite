<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="commonHeadTag.jsp">
	<jsp:param name="title" value="screen.createArticle.title" />
</jsp:include>

<%@ include file="commonLoginCheck.jsp"%>

<div
	style="background-color: white; margin: 80px 120px 50px 120px; text-align: left; padding: 30px;">

</div>
<jsp:include page="commonFootTag.jsp" />