//商品数量增减
$(function(){
	var $itemNumAdd = $(".btnAdd");
	var $itemNumReduce = $(".btnReduce");
	$itemNumAdd.on("click", function(){
		var currentValue = $(this).siblings(".itemNum").val();
		currentValue++;
		$(this).siblings(".itemNum").val(currentValue);
	});	
	$itemNumReduce.on("click", function(){
		var currentValue = $(this).siblings(".itemNum").val();
		if(currentValue>1){
			currentValue--;
			$(this).siblings(".itemNum").val(currentValue);
		};
	});

	//返回顶部
	$("#gotop").hide();
	$(window).scroll(function(){
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop;
		if( scrollt > 100 ){
			$("#gotop").show();
		}else{
			$("#gotop").hide();
		}
	});
	$("#gotop").click(function(){
		$("html,body").animate({scrollTop:"0px"},200);
	});

		//设置预览图片为原始尺寸
	$('.my-gallery figure').each(function(){
		var $this = $(this);
		var $swiperImg = $(this).find('img');
		var thisSrc = $swiperImg.attr('src');
		getImageWidth(thisSrc,function(swiperImgWidth,swiperImgHeight){
			$this.find('a').attr('data-size', swiperImgWidth + 'x' + swiperImgHeight);
		});
		
	});

	//获取原始图片尺寸
	function getImageWidth(url,callback){
		var img = new Image();
		img.src = url;
		
		// 如果图片被缓存，则直接返回缓存数据
		if(img.complete){
			callback(img.width, img.height);
		}else{
			// 完全加载完毕的事件
			img.onload = function(){
				callback(img.width, img.height);
			}
		}
	}
	
	//购物车动画
	var $shoppingCart = $('.shopping-cart');
	var $itemNum = $('#cartNum');
	var itemNumText = $itemNum.text()*1; //购物车数量
	$('.btn-cart').click(function(event){
		var itemNumSelect = $('.number .itemNum').val(); //选择商品数量
		var $itemListImg = $('.my-gallery figure').eq(0).find('img');
		var $itemListImgPic = $itemListImg.clone().css({width:'40px', height:'40px'});
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop;
		
		var offset = $shoppingCart.offset();
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
		
		itemNumText += itemNumSelect*1;
		$itemNum.text(itemNumText).addClass('animated pulse');
		setTimeout(function(){
			$itemNum.removeClass('animated pulse')
		}, 500);
		
	});
	
	//设置轮播图片尺寸
	setTimeout(function(){
		$('.my-gallery figure').each(function(){
			var thisImgHeight = Math.round($(this).find('img').height());
			var thisImgWidth = Math.round($(this).find('img').width());
			var thisImgScale = thisImgWidth/thisImgHeight;
			
			var thisBoxHeight = Math.round($(this).height());
			var thisBoxWidth = Math.round($(this).width());
			var thisBoxScale = thisBoxWidth/thisBoxHeight;
			console.log(11,thisImgScale,thisBoxScale);
			
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
	},300);
	
});