<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="java.util.HashMap"%>

<%
	HashMap<Integer, String> stampIcon = new HashMap<>();
	stampIcon.put(1, "fa-thumbs-up");
	stampIcon.put(2, "fa-angry");
	stampIcon.put(3, "fa-sad-cry");
	stampIcon.put(4, "fa-laugh-squint");
	request.setAttribute("stampIcon", stampIcon);
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
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.1.0/css/all.css">
</head>
<body style="margin: 0px">

	<jsp:include page="navbar.jsp" />

	<div style="margin: 20px 40px">