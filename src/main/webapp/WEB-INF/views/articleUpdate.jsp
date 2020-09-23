<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="commonHeadTag.jsp">
	<jsp:param name="title" value="screen.createArticle.title" />
</jsp:include>

<%@ include file="commonLoginCheck.jsp"%>

<div
	style="background-color: white; margin: 80px 80px 50px 80px; text-align: center; padding: 30px;">

	<h2>記事編集</h2>

	<div class="common">
		<form:form action="editArticle" modelAttribute="editArticleForm">
			<div>
				<form:hidden path="articleId" />
			</div>

			<div style="margin: 10px; font-size: 24px; text-align: left;">
				<label><fmt:message key="form.lbl.title" /></label>
				<form:input maxlength="20" style="width: 250px; font-size: 24px;"
					onkeyup="if (this.value.length > 20) this.blur(); else ShowLength(value,'inputlength1');"
					path="title" />
				<form:errors path="title" cssStyle="color: red" />
				<span id="inputlength1">${editArticleForm.title.length()}</span> <span>/20文字</span>
			</div>

			<div style="margin: 10px; font-size: 24px; text-align: left;">
				<label><fmt:message key="form.lbl.content" /></label><br>
				<div style="display: flex; flex-direction: rows;">
					<div style="flex-grow: 1;">
						<form:textarea path="content" cols="20" rows="15" maxlength="3000"
							style="text-align: left; width: 100%; font-size: 24px;"
							onkeyup="if (this.value.length > 3000) this.blur(); else ShowLength(value,'inputlength2');" />
						<form:errors path="content" cssStyle="color: red" />
						<span id="inputlength2">${editArticleForm.content.length()}</span>
						<span>/3000文字</span>

						<div id="btn-container"
							style="text-align: right; flex-grow: 2; font-size: 24px;">
							<form:button class="button">
								<fmt:message key="btn.update" />
							</form:button>
							<div style="margin-right: 1.5%;">
								<a href="javascript:history.back()"><fmt:message
										key="btn.return" /></a>
							</div>
						</div>
					</div>
					<div style="flex-grow: 0.8;"></div>
				</div>
			</div>
		</form:form>
	</div>
</div>

<script>
	function ShowLength(str, resultid) {
		document.getElementById(resultid).innerHTML = str.length;
	}
</script>

<jsp:include page="commonFootTag.jsp" />