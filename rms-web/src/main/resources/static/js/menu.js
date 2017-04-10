/**
 * ログイン画面JS
 */
$(function() {

	/** form */
	var fmMain = $("#formMain");

	/** デフォルトURL */
	var defaultUrl = fmMain.attr("action") + "menu";

	/**
	 * メニュークリック
	 */
	$("#menuList button[type='submit']").on("click", function() {
		var url = defaultUrl;
		fmMain.attr("action", url);

		return true;
	});

});
