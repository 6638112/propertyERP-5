//兑换说明
$(function(){
	
	var $itemStandard = $(".item-standard .title");
	$itemStandard.click(function(){
		var $arrow = $(this).find('.item-arrow-show');
		$arrow.swapAddClass('rotateIn', 'rotateOut');
		$(this).siblings().stop(true, false).animate({height: 'toggle'}, 300);
	});	
	
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
	
}); 
