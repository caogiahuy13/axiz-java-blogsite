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


<div style="text-align: center">

	<h1>お気に入り分析</h1>
	<div style="display: flex; flex-direction: row">
		<div style="flex-grow: 1"></div>
		<div style="justify-content: center; flex-grow: 0.5">
			<h2 style="display: inline">総アクセス数</h2>
			<br />
			<h2 style="display: inline">会員アクセス数</h2>
		</div>
		<div style="flex-grow: 0.25; text-align: left">
			<h2 style="display: inline">: ${loginAccess}</h2>
			<br />
			<h2 style="display: inline">: ${anonymousAccess }</h2>
		</div>
		<div style="flex-grow: 1"></div>
	</div>

	<div style="padding: 0px 10rem">
		<div style="text-align: left">
			<h3>＜性別年代＞</h3>
			<div id="ageAndGenderChart" style="height: 300px; width: 100%"></div>
		</div>

		<div style="text-align: left">
			<h3>＜お気に入り数とスタンプ種類＞</h3>
			<div id="articleChart" style="height: 300px; width: 100%"></div>
		</div>
	</div>

	<div id="back2Top">
		<a id="btnToTop" title="Back to top" href="#top">☝先頭</a> <br /> <a
			title="My page"
			href="/myPage?id=${sessionScope.currentMember.memberId }">マイ<br />ページ
		</a>

	</div>
</div>
<jsp:include page="common/footTag.jsp" />