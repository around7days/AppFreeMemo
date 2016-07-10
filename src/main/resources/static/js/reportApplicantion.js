/**
 * 月報申請画面JS
 */
$(function() {

	/** form */
	var fmMain = $("#formMain");

	/** デフォルトURL */
	var defaultUrl = "/tran/report/applicantion";

	/**
	 * 申請ボタン押下
	 */
	$("#applicantion").on("click", function() {
		if (!window.confirm("申請しますか？")) {
			return false;
		}
		var url = defaultUrl + "?insert";
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