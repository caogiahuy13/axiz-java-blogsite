<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="commonHeadTag.jsp">
	<jsp:param name="title" value="screen.articleDeleteResult.title" />
</jsp:include>

<meta http-equiv="refresh"
	content="3;url=/myPage?id=${sessionScope.currentMember.memberId }" />

<div
	style="margin: 80px 120px 50px 120px; text-align: center; padding: 30px;">
	<p>記事を削除しました</p>
	<p>※３秒後にマイページへ遷移します</p>
</div>

<jsp:include page="commonFootTag.jsp" />