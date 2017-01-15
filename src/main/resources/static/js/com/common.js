/**
 * CSSテーマ変更
 * 
 * @param theme
 */
function changeTheme(theme) {

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
}
