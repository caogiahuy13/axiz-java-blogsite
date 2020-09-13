<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.analytics.title" />
</jsp:include>

<script type="text/javascript">
	var genderData = [];
	<c:forEach items="${genderAnalytics}" var="item">
	genderData.push({
		y : '${item.value}',
		indexLabel : '${item.key}'
	});
	</c:forEach>

	var ageData = [];
	<c:forEach items="${ageAnalytics}" var="item">
	ageData.push({
		y : '${item.value}',
		indexLabel : '${item.key}'
	});
	</c:forEach>

	var accessData = [];
	<c:forEach items="${accessAnalytics}" var="item">
	accessData.push({
		y : '${item.value}',
		indexLabel : '${item.key}'
	});
	</c:forEach>

	var reactionData = [];
	var eachReactionData = [];
	var curStampId = 1;
	var maxStampId = ${reactionAnalytics.size()};
	<c:forEach items="${reactionAnalytics}" var="item">
	if ("${item.stampId}" > curStampId) {
		reactionData.push({
			type : "stackedColumn",
			legendText : ""+curStampId,
			showInLegend : "true",
			dataPoints : eachReactionData
		});
		curStampId++;
		eachReactionData = [];
	}
	eachReactionData.push({
		y : ${item.count},
		label : '${item.title}'
	});
	</c:forEach>
	reactionData.push({
		type : "stackedColumn",
		legendText : ""+curStampId,
		showInLegend : "true",
		indexLabel : "#total",
		yValueFormatString : "#",
		indexLabelPlacement : "outside",
		dataPoints : eachReactionData
	});

	window.onload = function() {
		var genderChart = new CanvasJS.Chart("genderAnalytics", {
			theme : "light2",
			title : {
				text : "性別分析"
			},
			data : [ {
				type : "pie",
				showInLegend : true,
				toolTipContent : "{y} - #percent %",
				yValueFormatString : "#人",
				legendText : "{indexLabel}",
				dataPoints : genderData
			} ]
		});

		var ageChart = new CanvasJS.Chart("ageAnalytics", {
			theme : "light2",
			title : {
				text : "年代分析"
			},
			data : [ {
				type : "pie",
				showInLegend : true,
				toolTipContent : "{y} - #percent %",
				yValueFormatString : "#人",
				legendText : "{indexLabel}",
				dataPoints : ageData
			} ]
		});

		var accessChart = new CanvasJS.Chart("accessAnalytics", {
			theme : "light2",
			title : {
				text : "アクセス分析"
			},
			data : [ {
				type : "pie",
				showInLegend : true,
				toolTipContent : "{y} - #percent %",
				yValueFormatString : "#人",
				legendText : "{indexLabel}",
				dataPoints : accessData
			} ]
		});

		var reactionChart = new CanvasJS.Chart("reactionAnalytics",
				{
					title:{
						text: "スタンプ分析"
					},
					axisY:{
						title:"Coal (bn tonnes)",
						valueFormatString: "#",
					},
					data: reactionData
				});


		reactionChart.render();

		genderChart.render();
		ageChart.render();
		accessChart.render();

	}
</script>
<script type="text/javascript"
	src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

<div id="genderAnalytics" style="height: 300px; width: 100%;"></div>
<br>
<div id="ageAnalytics" style="height: 300px; width: 100%;"></div>
<br>
<div id="accessAnalytics" style="height: 300px; width: 100%;"></div>
<br>
<div id="reactionAnalytics" style="height: 300px; width: 100%;"></div>
<br>

<jsp:include page="common/footTag.jsp" />