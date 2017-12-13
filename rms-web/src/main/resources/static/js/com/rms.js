/**
 * RMS固有クラス
 */
var Rms = function() {

};

/**
 * CSSテーマ変更
 * 
 * @param theme
 */
Rms.prototype.changeTheme = function(theme) {

	if (theme === $("#theme").val()) {
		// 現在と同じテーマの場合は何もしない
		return;
	}

	var cssBaseUrl = $("form").attr("action") + "fw/bootstrap/css/";
	var cssName = "bootswatch-theme-" + theme + ".min.css";

	// フェードアウト後にCSS変更
	$("body").fadeOut("normal", function() {
		$("#myTheme").attr("href", cssBaseUrl + cssName);
	});

	// N秒後にフェードイン
	setTimeout(function() {
		$("body").fadeIn("normal");
	}, 1500);

	// ローカルストレージにテーマを保存
	localStorage.setItem("theme", theme);
	// hiddenの値を更新
	$("#theme").val(theme);
};

/*
 * グローバル変数
 */
// RMS共通関数
var rms = new Rms();
