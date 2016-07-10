/**
 * 月報承認画面JS
 */
$(function() {

	/** form */
	var fmMain = $("#formMain");

	/** デフォルトURL */
	var defaultUrl = "/tran/report/approval";

	/**
	 * 承認ボタン押下
	 */
	$("#approval").on("click", function() {
		if (!window.confirm("承認しますか？")) {
			return false;
		}
		var url = defaultUrl + "?approval";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * 否認ボタン押下
	 */
	$("#denial").on("click", function() {
		if (!window.confirm("否認しますか？")) {
			return false;
		}
		var url = defaultUrl + "?denial";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * 月報DLボタン押下
	 */
	$("#download").on("click", function() {
		var index = $(this).val();
		var url = defaultUrl + "?download";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * 戻るボタン押下
	 */
	$("#back").on("click", function() {
		var url = defaultUrl + "?back";
		fmMain.attr("action", url);
		return true;
	});

});