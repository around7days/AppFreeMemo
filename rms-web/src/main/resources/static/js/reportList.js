/**
 * 月報一覧画面JS
 */
$(function() {

	/** form */
	var fmMain = $("#formMain");

	/** デフォルトURL */
	var defaultUrl = fmMain.attr("action") + "tran/reportlist";

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
	 * 月報一括DLボタン押下
	 */
	$("#bulkDownload").on("click", function() {
		if (!window.confirm("一括ダウンロードしますか？")) {
			return false;
		}
		var url = defaultUrl + "?bulkDownload";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * 月報DLボタン押下<br>
	 * (テーブル明細内)
	 */
	$("#resultTable button[name='download']").on("click", function() {
		var index = $(this).val();
		var url = defaultUrl + "/" + index + "?download";
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
	 * 一括ON/OFFチェックボックス押下
	 */
	$("#switchReportDLCheck").on("click", function() {
		if ($("#switchReportDLCheck").prop("checked")) {
			// チェックON
			$("input[name=reportDLCheck]").prop("checked", true);
		} else {
			// チェックOFF
			$("input[name=reportDLCheck]").prop("checked", false);
		}
	});

	/**
	 * Enterキーで検索ボタン押下<br>
	 * ※control.jsの処理を上書き
	 */
	$("input").keydown(function(e) {
		if ((e.which && e.which === 13) || (e.keyCode && e.keyCode === 13)) {
			$("#search").click();
		}
	});
});