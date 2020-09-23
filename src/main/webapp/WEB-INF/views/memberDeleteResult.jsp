<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="commonHeadTag.jsp">
	<jsp:param name="title" value="screen.deleteMemberResult.title" />
</jsp:include>

<meta http-equiv="refresh" content="3;url=/top" />

<div
	style="margin: 80px 120px 50px 120px; text-align: center; padding: 30px;">
	<p>退会が完了しました</p>
	<p>※３秒後にトップへ遷移します</p>
</div>


<jsp:include page="commonFootTag.jsp" />