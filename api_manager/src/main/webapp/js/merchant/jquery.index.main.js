/*$(function(){
	//文档加载完成后设置商品图片高度和行高
	function setItemHeight() {
		var $itemListImg = $(".item-list-img");
		var itemListImgWidth = $itemListImg.width();
		var itemListImgHeight = itemListImgWidth*249/263;
		$itemListImg.css({'height':itemListImgHeight + 'px', 'line-height':itemListImgHeight + 'px'});
		
		//判断图片是否加载完成
		var t_img; // 定时器
		var isLoad = true; // 控制变量
		// 判断图片加载状况，加载完成后回调
		isImgLoad(function(){
			$itemListImg.each(function(){
				var itemListPicHeight = $(this).children('img').height();
				if(itemListPicHeight < itemListImgHeight){
					$(this).children('img').css({'margin-top':(itemListImgHeight - itemListPicHeight)/2 + 'px'});
				}
			});
		});
		// 判断图片加载的函数
		function isImgLoad(callbackFun){
			// 查找所有封面图，迭代处理
			$itemListImg.each(function(){
				// 找到为0就将isLoad设为false，并退出each
				var itemListPicHeight = $(this).children('img').height();
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
	};
	setItemHeight();
	//window.onload = function(){ //这种方式不仅要求页面的DOM tree全部加载完成，而且要求所有的外部图片和资源全部加载完成。如果外部资源，例如图片需要很长时间来加载，那么这个js效果就会让用户感觉失效了。 
//		setItemHeight();
//	};
//	$(function(){  //仅仅只需要加载所有的DOM结构，在浏览器把所有的HTML放入DOM tree之前就执行js效果。包括在加载外部图片和资源之前。 （会导致图片尚未加载完成，js已经执行。。。效果无法实现）
//		setItemHeight();
//	});
	//改变文档宽度时设置商品图片高度和行高
	$(window).resize(function(){
		setItemHeight();
	});
	
}); */


/*$(function(){
	//文档加载完成后设置商品图片高度和行高
	function setItemHeight() {
		var $itemListImgBox = $(".item-list-img");
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
				var itemListPicWidth = $itemListPic.width();
				var itemListPicHeight = $itemListPic.height();
				if(itemListPicHeight < itemListImgBoxHeight){
					//商品图片高度小于外容器高度，设置商品图片与外容器一样高，并水平居中
					$itemListPic.height(itemListImgBoxHeight);
					$itemListPic.width(itemListImgBoxHeight*itemListPicWidth/itemListPicHeight);
					$itemListPic.css({'margin-left': - ( itemListImgBoxHeight*itemListPicWidth/itemListPicHeight - itemListPicWidth )/2 + 'px'});
				}else{
					//商品图片宽度小于外容器宽度，设置商品图片与外容器一样宽，并垂直居中
					$itemListPic.css({'margin-top': ( itemListImgBoxHeight - itemListPicHeight )/2 + 'px'});
				}
			});
		});
		// 判断图片加载的函数
		function isImgLoad(callbackFun){
			// 查找所有封面图，迭代处理
			$itemListImgBox.each(function(){
				// 找到为0就将isLoad设为false，并退出each
				var itemListPicHeight = $(this).children('img').height();
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
	};
	setItemHeight();
	//改变文档宽度时设置商品图片高度和行高
	$(window).resize(function(){
		setItemHeight();
	});
	
}); */


$(function(){
	//文档加载完成后设置商品图片高度和行高
	function setItemHeight() {
		var $itemListImgBox = $(".item-list-img");
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
				console.log(itemListImgBoxHeight + ' ' + itemListPicHeight);
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
			$itemListImgBox.each(function(){
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
				},500); // 我这里设置的是500毫秒就扫描一次，可以自己调整
			}
		}
	};
	//setItemHeight();
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


//购物车动画
$(function(){
	var $shoppingCart = $('.shopping-cart-index');
	var $itemNum = $('.item-num');
	var itemNumText = $itemNum.text()*1;
	$('.join-btn').click(function(event){
		var $itemListImg = $(this).parents('.item-list-small').find('.item-list-img');
		var $itemListImgPic = $itemListImg.find('img').clone().css({width:'40px', height:'40px'});
		var itemListImgPicWidth = $itemListImgPic.width();
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop;
		
		var offset = $('.shopping-cart-index').offset();
		var $flyer = $itemListImgPic;
		$flyer.addClass('item-flyer');
		$flyer.fly({
			start: {
				left: event.pageX - 50,
				top: event.pageY - scrollt -60
			},
			end: {
				left: offset.left + 20,
				top: offset.top + 20 - scrollt,
				width: 0,
				height: 0
			}
		});
		setTimeout(function(){
			$flyer.remove();
		},1000);
		
		itemNumText += 1;
		$itemNum.text(itemNumText).addClass('animated pulse');
		setTimeout(function(){
			$itemNum.removeClass('animated pulse')
		}, 500);
		
	});
	
});

//关注公众号
$(window).scroll(function(){
	 var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
	 var docHeight = $(document).height() - $(window).height(); //当前文档高度
	 if(scrollt == docHeight){
	 	$('.jfq-btn').stop(true,false).fadeOut();
	 }else if(scrollt > docHeight-300){
		$('.jfq-btn').stop(true,false).fadeIn();	 
	 }
});