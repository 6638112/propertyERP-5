//设置图片居中显示	
$.fn.reSetSwiperImgSize = function(fixedWidth,fixedHeight){
	$(this).each(function(){
		var thisImgHeight = Math.round($(this).find('img.disblock').height());
		var thisImgWidth = Math.round($(this).find('img.disblock').width());
		var thisImgScale = thisImgWidth/thisImgHeight;
		
		var thisBoxWidth = Math.round($(this).width());
		var thisBoxHeight = Math.round(thisBoxWidth*fixedHeight/fixedWidth);
		var thisBoxScale = fixedWidth/fixedHeight;
		$(this).height(thisBoxHeight);
		
		//宽度小于外框
		if(thisImgScale < thisBoxScale){
			$(this).find('img.disblock').width(thisBoxWidth);
			var curImgHeight = Math.round(thisBoxWidth/thisImgScale);
			$(this).find('img.disblock').height(curImgHeight);
			$(this).find('img.disblock').css('margin-top',(thisBoxHeight - curImgHeight)/2);
		//高度小于外框
		}else if(thisImgScale > thisBoxScale){
			$(this).find('img.disblock').height(thisBoxHeight);
			var curImgWidth = Math.round(thisBoxHeight*thisImgScale);
			$(this).find('img.disblock').width(curImgWidth);
			$(this).find('img.disblock').css('margin-left',(thisBoxWidth - curImgWidth)/2);
		}
	});
}