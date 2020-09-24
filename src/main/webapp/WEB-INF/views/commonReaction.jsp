<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div style="display: inline">
	<button
		onclick="reaction(${article.articleId}, ${param.stampId}, ${sessionScope.currentMember.memberId})"
		class="unstyled-button">
		<c:if test="${isReacted == param.stampId}">
			<span id="stamp_${param.stampId}" style="font-size: 24px;">
				${param.stampName}</span>
		</c:if>
		<c:if test="${isReacted != param.stampId}">
			<span id="stamp_${param.stampId}"
				style="opacity: 50%; font-size: 24px;"> ${param.stampName}</span>
		</c:if>
	</button>
</div>

<script type="text/javascript">
	var stampIcon = {
			1: "👍",
			2: "😂",
			3: "😥",
			4: "😲",
			5: "🤗"
	};
	function reaction(articleId, stampId, memberId){
		if (memberId === undefined){
			window.location.href="/login";
		} else {
			let url = "/api/reaction?stampId=" + stampId + "&articleId=" + articleId + "&memberId=" + memberId;

			$.ajax({
			    type: "POST",
			    url: url,
			    headers: {
			      'Accept': 'application/json',
			      'Content-Type': 'application/json'
			    },
			    success: function(data) {
			      let {isReacted, reactedMembers, reactions } = data;
			      for (let i=1; i<6; i++){
			 		let id = "#stamp_" + i;
			 		if (isReacted == i){
						$(id).css("opacity", "100%");
					} else {
						$(id).css("opacity", "50%");
					}
			      }

			      var popupHtml = "";
			      for (const [key, value] of Object.entries(reactions)) {
			    	  popupHtml += "<span style='color: blue'> " + value + " " + stampIcon[key] +  "</span>"
			    	}

			      popupHtml += "<span class='popuptext' id='myPopup'> ";
			      for (const [key, value] of Object.entries(reactedMembers)) {
			    	  popupHtml += "<span>" + value.nickname + "</span><br>"
			    	}

			      popupHtml += "</span> ";

			      $(".popup").html(popupHtml);
			    }
			});
		}
	}
</script>