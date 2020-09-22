<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="common/headTag.jsp">
	<jsp:param name="title" value="screen.analytics.title" />
</jsp:include>

<script type="text/javascript">
	var stampIconName = {
			1: "👍",
			2: "😂",
			3: "😥",
			4: "😲",
			5: "🤗"
	};

	let ageAndGenderData = [];
	let eachAgeAndGenderData = [];
	let curAgeRange = "10~20";
	<c:forEach items="${ageRangeAndGenderAnalytics}" var="item" varStatus="loop">
		if (${loop.index} > 0 && ${loop.index} % 2 == 0){
			ageAndGenderData.push({
				type: "stackedBar",
	              legendText: curAgeRange,
	              showInLegend: "true",
	              dataPoints: eachAgeAndGenderData,
			});
			curAgeRange = "${item.ageRange}";
			eachAgeAndGenderData = [];
		}
		eachAgeAndGenderData.push({
			label : '${item.gender}',
			y : ${item.count}
		});
	</c:forEach>
	ageAndGenderData.push({
		type : "stackedBar",
		legendText : curAgeRange,
		showInLegend : "true",
		dataPoints : eachAgeAndGenderData
	});

	let curStampId = 1;
	let reactionData = [];
	let eachReactionData = [];
	<c:forEach items="${reactionAnalytics}" var="item">
		if ("${item.stampId}" > curStampId) {
			reactionData.push({
				type : "stackedBar",
				legendText : stampIconName[curStampId],
				showInLegend : "true",
				dataPoints : eachReactionData
			});
			curStampId++;
			eachReactionData = [];
		}
		eachReactionData.push({
			label : '${item.title}',
			y : ${item.count}
		});
	</c:forEach>
	reactionData.push({
		type : "stackedBar",
		legendText : stampIconName[curStampId],
		showInLegend : "true",
		dataPoints : eachReactionData
	});

	window.onload = function() {

		var ageAndGenderChart = new CanvasJS.Chart("ageAndGenderChart", {
	          axisX: {
	            interval: 1,
	            labelFontSize: 16,
	          },

	          legend: {
	            fontSize: 16,
	          },

	          data: ageAndGenderData
	        });

		var articleChart = new CanvasJS.Chart("articleChart", {
	          axisX: {
	            interval: 1,
	            labelFontSize: 16,
	          },

	          legend: {
	            fontSize: 20,
	          },

	          data: reactionData
	        });

		ageAndGenderChart.render();
		articleChart.render();
	}
</script>
<script type="text/javascript"
	src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

<div style="margin: 40px" align="center">
	<div id="ageAndGenderChart" style="height: 300px; width: 50%;"></div>
	<br> <br>
	<div id="articleChart" style="height: 300px; width: 50%;"></div>
	<br> <br>
</div>

<jsp:include page="common/footTag.jsp" />