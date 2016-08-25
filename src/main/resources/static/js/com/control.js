/**
 * ボタン制御JS
 */
$(function() {
	/*
	 * Enterキー無効
	 */
	$("input").keydown(function(e) {
		if ((e.which && e.which === 13) || (e.keyCode && e.keyCode === 13)) {
			return false;
		} else {
			return true;
		}
	});
});