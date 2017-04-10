/**
 * 月報申請画面JS
 */
$(function() {

	/** form */
	var fmMain = $("#formMain");

	/** デフォルトURL */
	var defaultUrl = fmMain.attr("action") + "tran/reportapplyregist";

	/**
	 * 申請ボタン押下
	 */
	$("#apply").on("click", function() {
		if (!window.confirm("申請しますか？")) {
			return false;
		}
		var url = defaultUrl + "?apply";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * 再申請ボタン押下
	 */
	$("#reApply").on("click", function() {
		if (!window.confirm("再申請しますか？")) {
			return false;
		}
		var url = defaultUrl + "?reApply";
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