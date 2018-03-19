$(function(){
	//设置swiper-container尺寸
	function resetConSize(){
		var resetContainerSize = setInterval(function(){
			var width = $('.swiper-container').width();
			if(width > 0){
				clearInterval(resetContainerSize);
				$('.swiper-container').height(467*width/640);
			}
		},100);
	}
	resetConSize();
	
	$(window).resize(function(){
		resetConSize();
	});
});
