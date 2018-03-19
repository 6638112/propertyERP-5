//文档加载完成后设置商品图片高度和行高
function setItemHeight() {
	var $itemListImgBox = $(".item-list-img:visible");
	var itemListImgBoxWidth = $itemListImgBox.width();
	var itemListImgBoxHeight = itemListImgBoxWidth*249/263;
	$itemListImgBox.css({'height':itemListImgBoxHeight + 'px', 'line-height':itemListImgBoxHeight + 'px'});
	//判断图片是否加载完成
	var t_img; // 定时器
	var isLoad = true; // 控制变量
	// 判断图片加载状况，加载完成后回调
	isImgLoad(function(){
		$itemListImgBox.each(function(){
			var $itemListPic = $(this).children('img');
			var itemListPicSrc = $itemListPic.attr('src');
			//if(!itemListPicSrc == 'images/pixel.gif'){
			var itemListPicWidth = $itemListPic.width();
			var itemListPicHeight = $itemListPic.height();
			if(itemListPicHeight < itemListImgBoxHeight){
				//商品图片高度小于外容器高度，设置商品图片与外容器一样高，并水平居中
				$itemListPic.height(itemListImgBoxHeight);
				$itemListPic.width(itemListImgBoxHeight*itemListPicWidth/itemListPicHeight);
				$itemListPic.css({'margin-left': - ( Math.round(itemListImgBoxHeight*itemListPicWidth/itemListPicHeight) - Math.round(itemListImgBoxWidth) )/2 + 'px'});
			}else if(itemListPicHeight > itemListImgBoxHeight && itemListPicSrc !== 'images/pixel.gif'){
				//商品图片宽度小于外容器宽度，设置商品图片与外容器一样宽，并垂直居中
				$itemListPic.css({'margin-top': ( Math.round(itemListImgBoxHeight) - Math.round(itemListPicHeight) )/2 + 'px'});
			}
			//}
		});
	});
	// 判断图片加载的函数
	function isImgLoad(callbackFun){
		// 查找所有封面图，迭代处理
		$itemListImgBox.each(function(i){
			// 找到为0就将isLoad设为false，并退出each
			var itemListPicHeight = $(this).children('img').height();
			var itemListPicSrc = $(this).children('img').attr('src');
			if(itemListPicHeight === 0){
				isLoad = false;
				return false;
			}
		});
		// 为true，没有发现为0的。加载完毕
		if(isLoad){
			clearTimeout(t_img); // 清除定时器
			// 回调函数
			callbackFun();
		// 为false，因为找到了没有加载完成的图，将调用定时器递归
		}else{
			isLoad = true;
			t_img = setTimeout(function(){
				isImgLoad(callbackFun); // 递归扫描
			},500); // 这里设置的是500毫秒就扫描一次，可以自己调整
		}
	}
};

$(function(){
	
	//改变文档宽度时设置商品图片高度和行高
	$(window).resize(function(){
		setItemHeight();
	});
	
	$(".scrollLoading").scrollLoading({
		container: $(window),
		callback: function() {
			setTimeout(function(){
				setItemHeight();
			},500);
			setTimeout(function(){
				setItemHeight();
			},1200);
			setTimeout(function(){
				setItemHeight();
			},5200);
		}
	});
	
}); 

