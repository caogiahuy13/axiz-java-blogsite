<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty sessionScope.currentUser}">
	<c:redirect url="top" />
</c:if>