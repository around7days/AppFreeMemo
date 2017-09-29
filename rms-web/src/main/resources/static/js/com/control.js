$(function() {

	/**
	 * 多重submit防止<br>
	 * （一定秒画面をロック）
	 */
	$("form").on("submit", function() {
		$.blockUI({
			overlayCSS : {
				backgroundColor : "#ffffff",
				opacity : 0.0
			},
			message : null
		});

		setTimeout($.unblockUI, 3000);
	});

	/**
	 * inputタグ内のEnterキー無効
	 */
	$("input").keydown(function(e) {
		if ((e.which && e.which === 13) || (e.keyCode && e.keyCode === 13)) {
			return false;
		} else {
			return true;
		}
	});

	/**
	 * CSSテーマ変更
	 */
	$("#navbarTheme a").on("click", function() {
		var theme = $(this).text();
		// テーマ変更
		changeTheme(theme);
	});

	/**
	 * 年月カレンダー表示
	 */
	$(".datepicker-ym").focus(function(){ $(this).blur();});
	$(".datepicker-ym").keydown(function(){ return false;});
	$(".datepicker-ym").datepicker({
		format : "yyyymm",
		language : "ja",
		minViewMode : "months",
		maxViewMode : "years",
		orientation : "bottom auto",
		clearBtn : true,
		autoclose : true
	});

	/**
	 * DataTables設定
	 */
	$(".dataTable").dataTable({
		lengthChange : false,
		paging : false,
		searching : true,
		ordering : true,
		info : false,
		order : []
	});

	/**
	 * ダミーファイルボタン押下
	 */
	$("#fakeFile").on("click", function() {
		$("#file").click();
	});

	/**
	 * ダミーファイルテキストへの値反映
	 */
	$("#file").change(function() {
		if ($("#fakeFileText").length) {
			$("#fakeFileText").val($("#file").val().replace("C:\\fakepath\\", ""));
		}
	});

	/**
	 * 上に戻るアンカー操作（スクロールイベント）
	 */
	$(window).on("scroll", function() {
		if ($(this).scrollTop() > 100) {
			$("#pageTop").fadeIn("slow");
		} else {
			$("#pageTop").fadeOut("slow");
		}
	});

	/**
	 * 上に戻るアンカー押下時
	 */
	$("#pageTop").on("click", function() {
		// スクロールの速度（ミリ秒）
		var speed = 400;
		// 移動先を数値で取得
		var position = $("html").offset().top;
		// スムーススクロール
		$("body,html").animate({
			scrollTop : position
		}, speed, "swing");
		return false;
	});

});