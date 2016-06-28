/**
 * ログイン画面JS
 */
$(function() {
	/** form */
	var fmMain = $('#formMain');

	/** デフォルトActionURL */
	var actionUrl = '/login_auth_validate';

	/**
	 * ログインボタン押下
	 */
	$('#login').on('click', function() {
		fmMain.attr('action', actionUrl);
		fmMain.submit();
	});
});