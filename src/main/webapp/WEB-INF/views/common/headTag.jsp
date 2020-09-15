<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="java.util.HashMap"%>

<%
	HashMap<Integer, String> stampIcon = new HashMap<>();
	stampIcon.put(1, "&#x1f44d"); // 👍
	stampIcon.put(2, "&#x1f602"); // 😂
	stampIcon.put(3, "&#x1f625"); // 😥
	stampIcon.put(4, "&#x1f632"); // 😲
	stampIcon.put(5, "&#x1f917"); // 🤗

	request.setAttribute("stampIcon", stampIcon);
%>

<%
	final int ratio = 50;
	final int trophyMilestone = 200 / ratio;
	final int bronzeMilestone = 300 / ratio;
	final int silverMilestone = 500 / ratio;
	final int goldMilestone = 1500 / ratio;

	request.setAttribute("trophyMilestone", trophyMilestone);
	request.setAttribute("bronzeMilestone", bronzeMilestone);
	request.setAttribute("silverMilestone", silverMilestone);
	request.setAttribute("goldMilestone", goldMilestone);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="${param.title}" /></title>
<link href="css/commons.css" rel="stylesheet">
<link href="css/navbar.css" rel="stylesheet">
<link href="css/popup.css" rel="stylesheet">
<link href="css/card.css" rel="stylesheet">
<link href="css/pagination.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.1.0/css/all.css">
</head>
<body style="margin: 0px">

	<jsp:include page="navbar.jsp" />

	<div style="margin: 20px 40px">