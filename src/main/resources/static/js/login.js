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

	$(".animsition").animsition({
		inClass : 'fade-in', // ロード時のエフェクト
		outClass : 'fade-out', //離脱時のエフェクト
		inDuration : 1500, //ロード時の演出時間
		outDuration : 800, //離脱時の演出時間
		linkElement : '.animsition-link', //アニメーションを行う要素
		// e.g. linkElement   :   'a:not([target="_blank"]):not([href^=#])'
		loading : true, //ローディングの有効/無効
		loadingParentElement : 'body', //ローディング要素のラッパー
		loadingClass : 'animsition-loading', //ローディングのクラス
		unSupportCss : [ 'animation-duration', '-webkit-animation-duration', '-o-animation-duration' ],
		overlay : false, //オーバーレイの有効/無効
		overlayClass : 'animsition-overlay-slide', //オーバーレイのクラス
		overlayParentElement : 'body' //オーバーレイ要素のラッパー
	});
});
