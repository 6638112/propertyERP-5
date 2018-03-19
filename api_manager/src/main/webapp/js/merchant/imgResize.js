$(function(){

		//设置预览图片为原始尺寸
//	$('.swiper-wrapper .swiper-slide').each(function(){
//		var $this = $(this);
//		var $swiperImg = $(this).find('img');
//		var thisSrc = $swiperImg.attr('src');
//		getImageWidth(thisSrc,function(swiperImgWidth,swiperImgHeight){
//			$this.find('a').attr('data-size', swiperImgWidth + 'x' + swiperImgHeight);
//		});
//		
//	});
//
//	//获取原始图片尺寸
//	function getImageWidth(url,callback){
//		var img = new Image();
//		img.src = url;
//		
//		// 如果图片被缓存，则直接返回缓存数据
//		if(img.complete){
//			callback(img.width, img.height);
//		}else{
//			// 完全加载完毕的事件
//			img.onload = function(){
//				callback(img.width, img.height);
//			}
//		}
//	}

	//设置轮播图片尺寸
	setTimeout(function(){
		reSetSwiperImgSize();
	},100);
});
	
function reSetSwiperImgSize(){
	$('.swiper-wrapper .swiper-slide').each(function(){
		var thisImgHeight = Math.round($(this).find('img').height());
		var thisImgWidth = Math.round($(this).find('img').width());
		var thisImgScale = thisImgWidth/thisImgHeight;
		
		var thisBoxHeight = Math.round($(this).height());
		var thisBoxWidth = Math.round($(this).width());
		var thisBoxScale = thisBoxWidth/thisBoxHeight;
		
		//宽度小于外框
		if(thisImgScale < thisBoxScale){
			$(this).find('img').width(thisBoxWidth);
			var curImgHeight = Math.round(thisBoxWidth/thisImgScale);
			$(this).find('img').height(curImgHeight);
			$(this).find('img').css('margin-top',(thisBoxHeight - curImgHeight)/2);
		//高度小于外框
		}else if(thisImgScale > thisBoxScale){
			$(this).find('img').height(thisBoxHeight);
			var curImgWidth = Math.round(thisBoxHeight*thisImgScale);
			$(this).find('img').width(curImgWidth);
			$(this).find('img').css('margin-left',(thisBoxWidth - curImgWidth)/2);
		}
	});
}