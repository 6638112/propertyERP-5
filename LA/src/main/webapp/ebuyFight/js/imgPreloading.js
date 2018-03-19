$(function(){
	var $itemPicBig = $('.item-pic-big');	
	//判断图片是否加载完成
	var t_img; // 定时器
	var isLoad = true; // 控制变量
	// 判断图片加载状况，加载完成后回调
	isImgLoad(function(){
		
	});
	// 判断图片加载的函数
	function isImgLoad(callbackFun){
		// 查找所有封面图，迭代处理
		$itemPicBig.each(function(i){
			// 找到为0就将isLoad设为false，并退出each
			var itemListPicHeight = $(this).height();
			var itemListPicSrc = $(this).attr('src');
			
			
			
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
			},500); // 我这里设置的是500毫秒就扫描一次，可以自己调整
		}
	}
	
	$(".scrollLoading").scrollLoading();
	
	//滚动重置tabbox高度
	$(window).scroll(function(){
		var liIndex = $('#tabBox').find('li.wp50.on').index();
		if(liIndex == 0){
			var itemImgLength = $itemPicBig.length;
			var imgLoadedNum = itemImgLength;
			$itemPicBig.each(function(i){
				var itemListPicSrc = $(this).attr('src');
				if(itemListPicSrc !== 'images/pixel.gif'){
					imgLoadedNum -= 1;
					if(imgLoadedNum == 0){
						var $myOrderListBox = $('#tabOne');
						$("#tabBox .tempWrap").height($myOrderListBox.height());
					}
				}
			});
		}
	});
	
}); 
