/**
 * CSSスタイル変更
 */
function changeStyle(style){
	$("body").fadeOut("normal", function(){
		$("#mystyle").attr("href", "../fw/bootstrap/css/bootswatch-theme-"+ style +".css");
	});
	setTimeout(function(){
		$("body").fadeIn("normal");
	},2000);

}
