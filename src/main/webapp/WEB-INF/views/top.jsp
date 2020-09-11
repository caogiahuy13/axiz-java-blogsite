<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.top.title" />
</jsp:include>

<jsp:include page="common/navbar.jsp" />

<form action="/search">
	<input type="text" name="keyword">

	<input type="radio" id="all" name="searchType" value="all"/>
	<label for="all"><fmt:message key="form.lbl.all" /></label>

	<input type="radio" id="favorites" name="searchType" value="favorites"/>
	<label for="favorites"><fmt:message key="form.lbl.favorites" /></label>

	<input type="radio" id="ranking" name="searchType" value="ranking"/>
	<label for="ranking"><fmt:message key="form.lbl.ranking" /></label>

	<br><br>
	<button type="submit"><fmt:message key="btn.search" /></button>
</form>


<jsp:include page="common/footTag.jsp" />


