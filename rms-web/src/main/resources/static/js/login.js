/**
 * ログイン画面JS
 */
$(function() {

	/** form */
	var fmMain = $("#formMain");

	/** デフォルトURL */
	var defaultUrl = fmMain.attr("action") + "login";

	/**
	 * 初期処理
	 */
	{
		// TODO 開発中のお手軽ログイン用設定
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

		// ローカルストレージからテーマを取得
		var theme = localStorage.getItem("theme");
		if (theme != null && theme !== "") {
			if (theme !== $("#theme").val()) {
				// テーマ変更
				changeTheme(theme);
			}
		}
	}

	/**
	 * ログインボタン押下
	 */
	$("#login").on("click", function() {
		var url = defaultUrl + "?validate";
		fmMain.attr("action", url);

		// TODO 開発中のお手軽ログイン用設定
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
