/**
 * 月報申請画面JS
 */
$(function() {

	/** form */
	var fmMain = $("#formMain");

	/** デフォルトURL */
	var defaultUrl = "/tran/report/applicant";

	/**
	 * 申請ボタン押下
	 */
	$("#applicant").on("click", function() {
		if (!window.confirm("申請しますか？")) {
			return false;
		}
		var url = defaultUrl + "?insert";
		fmMain.attr("action", url);
		fmMain.submit();
	});

	//	/**
	//	 * 更新ボタン押下
	//	 */
	//	$("#update").on("click", function() {
	//		if (!window.confirm("更新しますか？")) {
	//			return false;
	//		}
	//		var url = defaultUrl + "?update";
	//		fmMain.attr("action", url);
	//		fmMain.submit();
	//	});

	/**
	 * 戻るボタン押下
	 */
	$("#back").on("click", function() {
		var url = defaultUrl + "?back";
		fmMain.attr("action", url);
		fmMain.submit();
	});

});