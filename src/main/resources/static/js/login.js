/**
 * ログイン画面JS
 */
$(function() {

	/** form */
	var fmMain = $("#formMain");

	/** デフォルトURL */
	var defaultUrl = "/login";

	/**
	 * 初期処理
	 */
	{
		// TODO
		// ローカルストレージから値の取得
		var userId = localStorage.getItem("userId");
		var password = localStorage.getItem("password");
		// 値の反映
		if (userId != null) {
			$("#userId").val(userId);
		}
		if (password != null) {
			$("#password").val(password);
		}
	}

	/**
	 * ログインボタン押下
	 */
	$("#login").on("click", function() {
		var url = defaultUrl + "?validate";
		fmMain.attr("action", url);

		// ローカルストレージに値の保存
		localStorage.setItem("userId", $("#userId").val());
		localStorage.setItem("password", $("#password").val());

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
