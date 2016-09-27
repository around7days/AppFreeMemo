$(function() {
	/**
	 * inputタグ内のEnterキー無効
	 */
	$("input").keydown(function(e) {
		if ((e.which && e.which === 13) || (e.keyCode && e.keyCode === 13)) {
			return false;
		} else {
			return true;
		}
	});

	/**
	 * 年月カレンダー表示
	 */
	$(".datepicker-ym").datepicker({
		format : "yyyymm",
		language : "ja",
		minViewMode : "months",
		autoclose : true
	});

});