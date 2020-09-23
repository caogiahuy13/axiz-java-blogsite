<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty sessionScope.currentMember}">
	<c:redirect url="top" />
</c:if>