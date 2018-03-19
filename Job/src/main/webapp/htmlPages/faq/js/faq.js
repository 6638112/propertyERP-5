$(function(){
	
	$('.sub-title').click(function(){
		$(this).find('span.item-arrow-show').swapAddClass('rotateIn', 'rotateOut');
		$(this).siblings('.faq-list-box').stop(true, false).animate({height:'toggle'},200).swapAddClass('show-list', 'hide-list');
	});
	
		
	//样式切换函数
	$.fn.swapAddClass = function(class1, class2){
		return this.each(function(){
			var $elem = $(this);
			if($elem.hasClass(class1)){
				$elem.removeClass(class1).addClass(class2);
			}else if($elem.hasClass(class2)){
				$elem.removeClass(class2).addClass(class1);
			}else{
				$elem.addClass(class1);
			}
		});
	};
	
	//跳转填写其他提问
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	
	//安卓
	if(isAndroid){
		$('.ask-btn').click(function(param){
			window.faq.askBtn('param');
		});
	}
	
	//ios
	if(isiOS){
		function askBtn(param01,param02){
			document.location="jfq://"+param01+":/"+param02;
		};
		
		$('.ask-btn').click(function(){
			askBtn('askBtn','param');
		});
	}
	
}); 