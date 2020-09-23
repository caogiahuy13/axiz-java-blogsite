<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="footer">
	<span>©2020 axiz.groupB All rights reserved. </span>
</div>

<script>
	$("#back2Top").hide();
	$(window).scroll(function() {
		var height = $(window).scrollTop();
		if (height > 100) {
			$("#back2Top").fadeIn();
		} else {
			$("#back2Top").fadeOut();
		}
	});

	$(document).ready(function() {
		$("#btnToTop").click(function(event) {
			event.preventDefault();
			$("html, body").animate({
				scrollTop : 0
			}, "slow");
			return false;
		});
	});
</script>
</body>
</html>
