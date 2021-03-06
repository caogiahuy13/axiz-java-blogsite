<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="navbar sticky" id="navbar">
	<a href="/top"><fmt:message key="btn.top" /></a>

	<div class="topnav-centered">
		<b>gro</b>
	</div>

	<div class="dropdown">
		<c:if test="${empty sessionScope.currentMember}">
			<a href="/login"><fmt:message key="btn.login" /></a>
		</c:if>
		<c:if test="${not empty sessionScope.currentMember}">
			<button class="dropbtn">
				${sessionScope.currentMember.name} <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="/myPage?id=${sessionScope.currentMember.memberId }"><fmt:message
						key="btn.myPage" /></a> <a href="/createArticle"><fmt:message
						key="btn.createArticle" /></a>
				<c:if test="${sessionScope.totalReactions > silverMilestone }">
					<a href="/analytics"><fmt:message key="btn.analytics" /></a>
				</c:if>
				<a href="/logout"><fmt:message key="btn.logout" /></a>
			</div>
		</c:if>
	</div>
</div>