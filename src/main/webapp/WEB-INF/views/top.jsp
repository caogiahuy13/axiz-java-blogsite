<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="commonHeadTag.jsp">
	<jsp:param name="title" value="screen.top.title" />
</jsp:include>

<div
	style="margin: 80px 120px 50px 120px; text-align: center; padding: 30px; font-size: 24px; background-color: white;">
	<div>
		<img src="img/top.10.png" width="100%" height="450px" alt="トップ画像">
	</div>

	<h2>記事検索</h2>

	<form:form action="/top" modelAttribute="searchForm"
		style="padding: 0px 25%">

		<div style="text-align: left">
			<label for="newest" class="radio-container" style="display: inline">
				<form:radiobutton id="newest" path="sortType" value="newest" /> <fmt:message
					key="form.lbl.newestOrder" /> <span class="radio-checkmark"></span>
			</label> <label for="ranking" class="radio-container" style="display: inline">
				<form:radiobutton id="ranking" path="sortType" value="ranking" /> <fmt:message
					key="form.lbl.rankingOrder" /><span class="radio-checkmark"></span>
			</label> <br>
			<c:if test="${not empty sessionScope.currentMember }">
				<label for="favorites" class="checkbox-container"> <form:checkbox
						id="favorites" path="searchType" value="favorites" /> <fmt:message
						key="form.lbl.sortType.favorites" /><span
					class="checkbox-checkmark"></span>
				</label>
			</c:if>

		</div>

		<div style="display: flex; text-align: left">
			<form:input path="keyword"
				style="height: 52px; font-size: 24px; margin: 4px 2px" />
			<div style="margin-left: 10px">
				<form:button class="button" name="keywordSearch">
					<fmt:message key="btn.keywordSearch" />
				</form:button>
				<br>
				<form:button class="button" name="allSearch">
					<fmt:message key="btn.allSearch" />
				</form:button>
			</div>
		</div>
	</form:form>
	<br>

	<h2>投稿記事一覧</h2>

	<c:if test="${not empty msg}">
		<p class="error">${msg}</p>
	</c:if>

	<c:forEach var="article" items="${articles}">
		<c:set var="article" value="${article}" scope="request" />
		<jsp:include page="commonArticleCard.jsp" />
	</c:forEach>

	<div id="back2Top">
		<a id="btnToTop" title="Back to top" href="#top">☝先頭</a> <br />
	</div>
</div>
<jsp:include page="commonFootTag.jsp" />