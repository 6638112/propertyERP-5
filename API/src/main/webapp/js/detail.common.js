//仅限输入大于0的整数
function onlyNumber(obj){
    var partten = /^[1-9]\d*$/;
    if(!partten.test($(obj).val()) && $(obj).val() !== ''){
        $(obj).val('1');
    }
}

//鍟嗗搧鏁伴噺澧炲噺
$(function(){
	var $itemNumAdd = $(".btnAdd");
	var $itemNumReduce = $(".btnReduce");
	var itemNumText = $('#itemNum').text()*1; //购物车数量
	
	if(itemNumText*1 > 99){
		$itemNum.addClass('padding05');
	}
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
    $(".itemNum").on('keyup',function(){
    	onlyNumber('.itemNum');
    });
    $(".itemNum").on('blur', function(){
    	if($(this).val() === ''){
	        $(this).val('1');
	    }
    });

	//杩斿洖椤堕儴
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

		//璁剧疆棰勮鍥剧墖涓哄師濮嬪昂瀵�
	$('.my-gallery figure').each(function(){
		var $this = $(this);
		var $swiperImg = $(this).find('a');
		var thisSrc = $swiperImg.attr('href');
		getImageWidth(thisSrc,function(swiperImgWidth,swiperImgHeight){
			$this.find('a').attr('data-size', swiperImgWidth + 'x' + swiperImgHeight);
		});
		
	});

	//鑾峰彇鍘熷鍥剧墖灏哄
	function getImageWidth(url,callback){
		var img = new Image();
		img.src = url;
		
		// 濡傛灉鍥剧墖琚紦瀛橈紝鍒欑洿鎺ヨ繑鍥炵紦瀛樻暟鎹�
		if(img.complete){
			callback(img.width, img.height);
		}else{
			// 瀹屽叏鍔犺浇瀹屾瘯鐨勪簨浠�
			img.onload = function(){
				callback(img.width, img.height);
			}
		}
	}
	
	//璐墿杞﹀姩鐢�
	var $shoppingCart = $('.shopping-cart');
	var $itemNum = $('#cartNum');
	if($itemNum.text() != 0 && $itemNum.text() != ''){
		$itemNum.show();
	}
	$('.btn-cart').click(function(event){
		if($itemNum.is(':hidden')){
			$itemNum.show();
		}
		var productQty = $(".itemNum").val();
		var itemNumText = $itemNum.text()*1; //璐墿杞︽暟閲�
		var itemNumSelect = $('.number .itemNum').val(); //閫夋嫨鍟嗗搧鏁伴噺
		var $itemListImg = $('.my-gallery figure').eq(0).find('img');
		var $itemListImgPic = $itemListImg.clone().css({width:'40px', height:'40px'});
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop;
		
		var offset = $shoppingCart.offset();
		var $flyer = $itemListImgPic;
		
		//app端，H5页面不校验库存
		if(location.href.indexOf('/API') > -1){
			
			$flyer.addClass('item-flyer');
			$flyer.fly({
				start: {
					left: event.pageX - 50,
					top: event.pageY - scrollt -60
				},
				end: {
					left: offset.left + 30,
					top: offset.top + 20 - scrollt,
					width: 0,
					height: 0
				}
			});
			setTimeout(function(){
				$flyer.remove();
			},1000);
			
			itemNumText += itemNumSelect*1;
			
			if(itemNumText*1 > 99){
				$itemNum.addClass('padding05');
			}
			
			$itemNum.text(itemNumText).addClass('animated pulse');
			setTimeout(function(){
				$itemNum.removeClass('animated pulse')
			}, 500);
		
		}else{

			$.post("../cart/add2BuyCar.do", "ptId=" + $('.swiper-container').attr('data-id') + "&productQty="+ productQty).success(function(response){
				 if(typeof response === "object"){
					 if(response.status != '0000') {
						 if(response.message.indexOf("库存不足") > -1){
							 $.Showmsg("该商品库存不足");
						 }else {
							 $.Showmsg(response.message);
						 }
						 return;
					 }
				 }
				
				$flyer.addClass('item-flyer');
				$flyer.fly({
					start: {
						left: event.pageX - 50,
						top: event.pageY - scrollt -60
					},
					end: {
						left: offset.left + 30,
						top: offset.top - scrollt + 20,
						width: 0,
						height: 0
					}
				});
				setTimeout(function(){
					$flyer.remove();
				},1000);
				
				itemNumText += itemNumSelect*1;
				
				if(itemNumText*1 > 99){
					$itemNum.addClass('padding05');
				}
				
				$itemNum.text(itemNumText).addClass('animated pulse');
				setTimeout(function(){
					$itemNum.removeClass('animated pulse');
				}, 500);
			
			});
			
		}
		
	});
	
	//璁剧疆杞挱鍥剧墖灏哄
//	setTimeout(function(){
//		$('.my-gallery figure').each(function(){
//			var thisImgHeight = Math.round($(this).find('img').height());
//			var thisImgWidth = Math.round($(this).find('img').width());
//			var thisImgScale = thisImgWidth/thisImgHeight;
//			
//			var thisBoxHeight = Math.round($(this).height());
//			var thisBoxWidth = Math.round($(this).width());
//			var thisBoxScale = thisBoxWidth/thisBoxHeight;
//			console.log(11,thisImgScale,thisBoxScale);
//			
//			//瀹藉害灏忎簬澶栨
//			if(thisImgScale < thisBoxScale){
//				$(this).find('img').width(thisBoxWidth);
//				var curImgHeight = Math.round(thisBoxWidth/thisImgScale);
//				$(this).find('img').height(curImgHeight);
//				$(this).find('img').css('margin-top',(thisBoxHeight - curImgHeight)/2);
//			//楂樺害灏忎簬澶栨
//			}else if(thisImgScale > thisBoxScale){
//				$(this).find('img').height(thisBoxHeight);
//				var curImgWidth = Math.round(thisBoxHeight*thisImgScale);
//				$(this).find('img').width(curImgWidth);
//				$(this).find('img').css('margin-left',(thisBoxWidth - curImgWidth)/2);
//			}
//		});
//	},100);
	
	
	var t_img; // 瀹氭椂鍣�
	var isLoad = true; // 鎺у埗鍙橀噺
	
	// 鍒ゆ柇鍥剧墖鍔犺浇鐘跺喌锛屽姞杞藉畬鎴愬悗鍥炶皟
	isImgLoad(function(){
	    // 鍔犺浇瀹屾垚锛岃缃疆鎾浘鐗囧昂瀵�
		$('.my-gallery figure').each(function(){
			var thisImgHeight = Math.round($(this).find('img').height());
			var thisImgWidth = Math.round($(this).find('img').width());
			var thisImgScale = thisImgWidth/thisImgHeight;
			
			var thisBoxHeight = Math.round($(this).height());
			var thisBoxWidth = Math.round($(this).width());
			var thisBoxScale = thisBoxWidth/thisBoxHeight;
			
			//瀹藉害灏忎簬澶栨
			if(thisImgScale < thisBoxScale){
				$(this).find('img').width(thisBoxWidth);
				var curImgHeight = Math.round(thisBoxWidth/thisImgScale);
				$(this).find('img').height(curImgHeight);
				$(this).find('img').css('margin-top',(thisBoxHeight - curImgHeight)/2);
			//楂樺害灏忎簬澶栨
			}else if(thisImgScale > thisBoxScale){
				$(this).find('img').height(thisBoxHeight);
				var curImgWidth = Math.round(thisBoxHeight*thisImgScale);
				$(this).find('img').width(curImgWidth);
				$(this).find('img').css('margin-left',(thisBoxWidth - curImgWidth)/2);
			}
		});
	});
	
	// 鍒ゆ柇鍥剧墖鍔犺浇鐨勫嚱鏁�
	function isImgLoad(callback){
	    // 娉ㄦ剰鎴戠殑鍥剧墖绫诲悕閮芥槸cover锛屽洜涓烘垜鍙渶瑕佸鐞哻over銆傚叾瀹冨浘鐗囧彲浠ヤ笉绠°�
	    // 鏌ユ壘鎵�湁灏侀潰鍥撅紝杩唬澶勭悊
	    $('.my-gallery figure').each(function(){
	    	var thisImgHeight = $(this).find('img').height();
	        // 鎵惧埌涓�灏卞皢isLoad璁句负false锛屽苟閫�嚭each
	        if(thisImgHeight === 0){
	            isLoad = false;
	            return false;
	        }
	    });
	    // 涓簍rue锛屾病鏈夊彂鐜颁负0鐨勩�鍔犺浇瀹屾瘯
	    if(isLoad){
	        clearTimeout(t_img); // 娓呴櫎瀹氭椂鍣�
	        // 鍥炶皟鍑芥暟
	        callback();
	    // 涓篺alse锛屽洜涓烘壘鍒颁簡娌℃湁鍔犺浇瀹屾垚鐨勫浘锛屽皢璋冪敤瀹氭椂鍣ㄩ�褰�
	    }else{
	        isLoad = true;
	        t_img = setTimeout(function(){
	            isImgLoad(callback); // 閫掑綊鎵弿
	        },500); // 鎴戣繖閲岃缃殑鏄�00姣灏辨壂鎻忎竴娆★紝鍙互鑷繁璋冩暣
	    }
	}
	
});