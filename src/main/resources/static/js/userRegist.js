/**
 * ユーザ一覧画面JS
 */
$(function() {

	/**
	 * 登録ボタン押下
	 */
	$('#insertBtn').on('click', function() {
		if (!window.confirm('登録しますか？')) {
			return false;
		}
		return true;
	});

	/**
	 * 更新ボタン押下
	 */
	$('#updateBtn').on('click', function() {
		if (!window.confirm('更新しますか？')) {
			return false;
		}
		return true;
	});

});