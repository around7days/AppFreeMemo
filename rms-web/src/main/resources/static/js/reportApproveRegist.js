/**
 * 月報承認画面JS
 */
$(function() {

	/** form */
	var fmMain = $("#formMain");

	/** デフォルトURL */
	var defaultUrl = fmMain.attr("action") + "tran/reportapproveregist";

	/**
	 * 初期処理
	 */
	{
		// ツールチップ追加
		$("#remand").attr("data-toggle", "tooltip").attr("data-title", "承認状況を１つ戻します");
		$("#deny").attr("data-toggle", "tooltip").attr("data-title", "申請者からやり直します");
		// ツールチップ初期化
		$("[data-toggle='tooltip']").tooltip();
	}

	/**
	 * 承認ボタン押下
	 */
	$("#approve").on("click", function() {
		if (!window.confirm("承認しますか？")) {
			return false;
		}
		var url = defaultUrl + "?approve";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * 差戻ボタン押下
	 */
	$("#remand").on("click", function() {
		if (!window.confirm("差戻しますか？")) {
			return false;
		}
		var url = defaultUrl + "?remand";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * 否認ボタン押下
	 */
	$("#deny").on("click", function() {
		if (!window.confirm("否認しますか？")) {
			return false;
		}
		var url = defaultUrl + "?deny";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * 月報DLボタン押下<br>
	 * (テーブル明細内)
	 */
	$("#download").on("click", function() {
		var index = $(this).val();
		var url = defaultUrl + "?download";
		fmMain.attr("action", url);
		return true;
	});

	/**
	 * 戻るボタン押下
	 */
	$("#back").on("click", function() {
		var url = defaultUrl + "?back";
		fmMain.attr("action", url);
		return true;
	});
});