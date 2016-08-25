/**
 * ユーザ一覧画面JS
 */
$(function() {

	/** form */
	var fmMain = $("#formMain");

	/** デフォルトURL */
	var defaultUrl = "/mst/user/list";

	/**
	 * 戻るボタン押下
	 */
	$("#back").on("click", function() {
		var url = defaultUrl + "?back";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * 検索ボタン押下
	 */
	$("#search").on("click", function() {
		var url = defaultUrl + "?search";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * 新規ボタン押下
	 */
	$("#insert").on("click", function() {
		var url = defaultUrl + "?insert";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * 選択ボタン押下<br>
	 * (テーブル明細内)
	 */
	$("#resultTable button[name='select']").on("click", function() {
		var index = $(this).val();
		var url = defaultUrl + "/" + index + "?select";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * Prevアンカー押下
	 */
	$("#pagePrev").on("click", function() {
		var url = defaultUrl + "?pagePrev";
		fmMain.attr("action", url);
		fmMain.submit();
	});

	/**
	 * Nextアンカー押下
	 */
	$("#pageNext").on("click", function() {
		var url = defaultUrl + "?pageNext";
		fmMain.attr("action", url);
		fmMain.submit();
	});

	/**
	 * 検索ボタン押下
	 */
	$("#search").on("click", function() {
		var url = defaultUrl + "?search";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * Enterキーで検索ボタン押下<br>
	 * ※control.jsの処理を上書き？
	 */
	$("input").keydown(function(e) {
		if ((e.which && e.which === 13) || (e.keyCode && e.keyCode === 13)) {
			$("#search").click();
		}
	});
});