/**
 * ユーザ一覧画面JS
 */
$(function() {

	/** form */
	var fmMain = $('#formMain');

	/** デフォルトURL */
	var defaultUrl = '/mst/user/search';

	/**
	 * 検索ボタン押下
	 */
	$('#search').on('click', function() {
		var url = defaultUrl + "?search";
		fmMain.attr('action', url);
		fmMain.submit();
	});

	/**
	 * 新規ボタン押下
	 */
	$('#new').on('click', function() {
		var url = defaultUrl + "?new";
		fmMain.attr('action', url);
		fmMain.submit();
	});

	/**
	 * Prevアンカー押下
	 */
	$('#pagePrev').on('click', function() {
		var url = defaultUrl + "?pagePrev";
		fmMain.attr('action', url);
		fmMain.submit();
	});

	/**
	 * Nextアンカー押下
	 */
	$('#pageNext').on('click', function() {
		var url = defaultUrl + "?pageNext";
		fmMain.attr('action', url);
		fmMain.submit();
	});

});