/**
 * 月報状況一覧画面JS
 */
$(function() {

	/** form */
	var fmMain = $("#formMain");

	/** デフォルトURL */
	var defaultUrl = "/tran/report/search";

	/**
	 * 検索ボタン押下
	 */
	$("#search").on("click", function() {
		var url = defaultUrl + "?search";
		fmMain.attr("action", url);
		fmMain.submit();
	});

	/**
	 * 一括DLボタン押下
	 */
	$("#downloadBulk").on("click", function() {
		var url = defaultUrl + "?search";
		fmMain.attr("action", url);
		fmMain.submit();
	});

	/**
	 * 選択ボタン押下<br>
	 * (テーブル明細内)
	 */
	$("#resultTable button[name='select']").on("click", function() {
		var index = $(this).val();
		var url = defaultUrl + "/" + index + "?select";
		fmMain.attr("action", url);
		fmMain.submit();
	});

	/**
	 * 月報DLボタン押下<br>
	 * (テーブル明細内)
	 */
	$("#resultTable button[name='download']").on("click", function() {
		var index = $(this).val();
		var url = defaultUrl + "/" + index + "?download";
		fmMain.attr("action", url);
		fmMain.submit();
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

});