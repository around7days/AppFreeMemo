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

	/**
	 * Enterキーでログインボタン押下<br>
	 * ※control.jsの処理を上書き
	 */
	$("input").keydown(function(e) {
		if ((e.which && e.which === 13) || (e.keyCode && e.keyCode === 13)) {
			$("#login").click();
		}
	});
});