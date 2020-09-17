<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<div class="footer">
	<span>©2020 axiz.groupB All rights reserved. </span>
</div>

</div>

<script>
	$(window).scroll(function() {
		var height = $(window).scrollTop();
		if (height > 100) {
			$("#back2Top").fadeIn();
		} else {
			$("#back2Top").fadeOut();
		}
	});
	$(document).ready(function() {
		$("#back2Top").click(function(event) {
			console.log(window.innerHeight);
			event.preventDefault();
			$("html, body").animate({
				scrollTop : 0
			}, "slow");
			return false;
		});
	});
</script>

<script>
	window.onscroll = function() {
		toggleStickyNavbar();
	};

	var navbar = document.getElementById("navbar");
	var sticky = navbar.offsetTop;

	function toggleStickyNavbar() {
		if (window.pageYOffset >= sticky) {
			navbar.classList.add("sticky");
		} else {
			navbar.classList.remove("sticky");
		}
	}
</script>

</body>
</html>
