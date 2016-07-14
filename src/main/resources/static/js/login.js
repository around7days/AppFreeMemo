/**
 * ログイン画面JS
 */
$(function() {

	/** form */
	var fmMain = $("#formMain");

	/** デフォルトURL */
	var defaultUrl = "/login";

	/**
	 * ログインボタン押下
	 */
	$("#login").on("click", function() {
		var url = defaultUrl + "?validate";
		fmMain.attr("action", url);
		return true;
	});
});