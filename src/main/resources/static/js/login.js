/**
 * ログイン画面JS
 */
$(function() {

	/** form */
	var fmMain = $("#formMain");

	/** デフォルトURL */
	var defaultUrl = "/login_validate";

	/**
	 * ログインボタン押下
	 */
	$("#login").on("click", function() {
		var url = defaultUrl;
		fmMain.attr("action", url);
		fmMain.submit();
	});
});