<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.top.title" />
</jsp:include>

<form action="/top" method="post">
	<input type="text" name="keyword"> <input type="radio" id="all"
		name="searchType" value="all" checked="checked" /> <label for="all"><fmt:message
			key="form.lbl.all" /></label>
	<c:if test="${not empty sessionScope.currentUser}">
		<input type="radio" id="favorites" name="searchType" value="favorites" />
		<label for="favorites"><fmt:message key="form.lbl.favorites" /></label>
	</c:if>
	<input type="radio" id="ranking" name="searchType" value="ranking" />
	<label for="ranking"><fmt:message key="form.lbl.ranking" /></label> <br>
	<br>
	<button type="submit">
		<fmt:message key="btn.search" />
	</button>
</form>

<c:forEach var="article" items="${articles}">
	<div class="card"
		onclick="window.location='/article?id=${article.articleId}';">
		<div class="container">
			<h4>
				<b>${fn:escapeXml(article.title)}</b>
			</h4>
			<p>${fn:escapeXml(article.getReviewContent())}</p>
		</div>
	</div>
</c:forEach>

<jsp:include page="common/footTag.jsp" />


