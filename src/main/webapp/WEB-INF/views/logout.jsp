<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.logout.title" />
</jsp:include>

<meta http-equiv="refresh" content="3;url=/top" />

<h2>ログアウトしました</h2>
<h2>3秒後にトップへ遷移します</h2>

<jsp:include page="common/footTag.jsp" />