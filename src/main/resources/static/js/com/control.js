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
		orientation: "bottom auto",
		autoclose : true
	});

	/**
	 * ダミーファイルボタン押下
	 */
	$("#fakeFile").on("click", function() {
		$('#file').click();
	});

	/**
	 * ダミーファイルテキストへの値反映
	 */
	$("#file").change(function() {
		if ($("#fakeFileText").length) {
			$("#fakeFileText").val($("#file").val().replace("C:\\fakepath\\", ""));
		}
	});

});