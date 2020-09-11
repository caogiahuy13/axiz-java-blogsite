<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="navbar">
	<a href="/search"><fmt:message key="btn.search" /></a>
	<c:if test="${not empty sessionScope.currentUser}">
		<a href="/createArticle"><fmt:message key="btn.createArticle" /></a>
	</c:if>

	<div class="dropdown">
		<c:if test="${empty sessionScope.currentUser}">
			<a href="/login"><fmt:message key="btn.login" /></a>
		</c:if>
		<c:if test="${not empty sessionScope.currentUser}">
			<button class="dropbtn">
				${sessionScope.currentUser.userName} <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="/information"><fmt:message key="btn.information" /></a> <a
					href="/favorites"><fmt:message key="btn.favorites" /></a><a
					href="/ranking"><fmt:message key="btn.ranking" /></a> <a
					href="/logout"><fmt:message key="btn.logout" /></a>
			</div>
		</c:if>
	</div>
</div>
