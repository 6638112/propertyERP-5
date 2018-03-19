//商品数量增减
$(function(){

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
	
	
	var t_img; // 定时器
	var isLoad = true; // 控制变量
	
	// 判断图片加载状况，加载完成后回调
	isImgLoad(function(){
	    // 加载完成，设置轮播图片尺寸
		$('.my-gallery figure').each(function(){
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
	});
	
	// 判断图片加载的函数
	function isImgLoad(callback){
	    // 注意我的图片类名都是cover，因为我只需要处理cover。其它图片可以不管。
	    // 查找所有封面图，迭代处理
	    $('.my-gallery figure').each(function(){
	    	var thisImgHeight = $(this).find('img').height();
	        // 找到为0就将isLoad设为false，并退出each
	        if(thisImgHeight === 0){
	            isLoad = false;
	            return false;
	        }
	    });
	    // 为true，没有发现为0的。加载完毕
	    if(isLoad){
	        clearTimeout(t_img); // 清除定时器
	        // 回调函数
	        callback();
	    // 为false，因为找到了没有加载完成的图，将调用定时器递归
	    }else{
	        isLoad = true;
	        t_img = setTimeout(function(){
	            isImgLoad(callback); // 递归扫描
	        },500); // 我这里设置的是500毫秒就扫描一次，可以自己调整
	    }
	}
	
	//商品数量增减
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
	//仅限输入大于0的整数
	$('.itemNum').blur(function(){
		var thisVal = $(this).val();
		var reg = /^\+?[1-9]\d*$/;
		if(!reg.test(thisVal)){
			$.Showmsg('数量为大于0的整数');
			$(this).val('1');
		}
	});
	
});