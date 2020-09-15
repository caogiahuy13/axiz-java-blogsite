<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.analytics.title" />
</jsp:include>

<script type="text/javascript">
	var genderData = [];
	<c:forEach items="${genderAnalytics}" var="item">
	genderData.push({
		y : ${item.value},
		indexLabel : '${item.key}'
	});
	</c:forEach>

	var ageData = [];
	<c:forEach items="${ageAnalytics}" var="item">
	ageData.push({
		y : ${item.value},
		indexLabel : '${item.key}'
	});
	</c:forEach>

	var accessData = [];
	<c:forEach items="${accessAnalytics}" var="item">
	accessData.push({
		y : ${item.value},
		indexLabel : '${item.key}'
	});
	</c:forEach>

	var stampIconName = {
			1: "👍",
			2: "😂",
			3: "😥",
			4: "😲",
			5: "🤗"
	};

	var reactionData = [];
	var eachReactionData = [];
	var curStampId = 1;
	var maxStampId = ${reactionAnalytics.size()};
	<c:forEach items="${reactionAnalytics}" var="item">
	if ("${item.stampId}" > curStampId) {
		reactionData.push({
			type : "stackedColumn",
			legendText : stampIconName[curStampId],
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
		legendText : stampIconName[curStampId],
		showInLegend : "true",
		indexLabel : "#total",
		yValueFormatString : "#",
		indexLabelPlacement : "outside",
		dataPoints : eachReactionData
	});

	function getPieChart(chartName, chartLabel, data){
		return new CanvasJS.Chart(chartName, {
			theme : "light2",
			title : {
				text : chartLabel
			},
			data : [ {
				type : "pie",
				showInLegend : true,
				toolTipContent : "{y} - #percent %",
				yValueFormatString : "#人",
				legendText : "{indexLabel}",
				dataPoints : data
			} ]
		});
	}

	window.onload = function() {
		var genderChart = getPieChart("genderAnalytics", "性別分析",genderData);
		var ageChart = getPieChart("ageAnalytics", "年代分析",ageData);
		var accessChart =getPieChart("accessAnalytics", "アクセス分析",accessData);
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

		genderChart.render();
		ageChart.render();
		accessChart.render();
		reactionChart.render();
	}
</script>
<script type="text/javascript"
	src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

<div style="margin: 40px" align="center">
	<div id="genderAnalytics"
		style="height: 300px; width: 30%; display: inline-block"></div>
	<div id="ageAnalytics"
		style="height: 300px; width: 30%; display: inline-block"></div>
	<div id="accessAnalytics"
		style="height: 300px; width: 30%; display: inline-block"></div>
	<div style="height: 50px"></div>
	<div id="reactionAnalytics" style="height: 300px; width: 50%;"></div>
	<br> <br>
</div>

<jsp:include page="common/footTag.jsp" />