/**
 * CSSテーマ変更
 * 
 * @param theme
 */
function changeTheme(theme) {

	var cssUrl = "/fw/bootstrap/css/bootswatch-theme-" + theme + ".css";

	// フェードアウト後にCSS変更
	$("body").fadeOut("normal", function() {
		$("#myStyle").attr("href", cssUrl);
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
