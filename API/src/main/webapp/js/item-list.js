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

	
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	
	//安卓
	if(isAndroid){
		//商品详情按钮
		$('.single-item-btn').click(function(){
			var itemId = $(this).attr('data-id');
			window.itemList.singleItemBtn(itemId);
		});
		
		//加入购物车按钮
		$('.join-shoppingCart-btn').click(function(){
			var itemId = $(this).attr('data-id');
			window.itemList.joinShoppingCartBtn(itemId);
		});
	}
	
	//ios
	if(isiOS){
		//商品详情按钮		
		$('.single-item-btn').click(function(){
			var itemId = $(this).attr('data-id');
			document.location="jfq://"+'singleItemBtn'+":/"+itemId;
		});
		
		//加入购物车按钮		
		$('.join-shoppingCart-btn').click(function(){
			var itemId = $(this).attr('data-id');
			document.location="jfq://"+'joinShoppingCart'+":/"+itemId;
		});
	}
	
}); 


		
//获取商品列表
var num = 10;//每页显示的个数的一半，pageNum为num*2，保证有多页时，前面页的个数为偶数
var n = 0;
var m = -num;
var $loading = $('.loading');
var productTypeId = location.search.substring(15);
//分页加载订单
function getItemList(pageType, obj){
	$.ajax({
		type:"get",
		url:"qryProdList.json",
		data:{page:$(obj).attr('data-page'), pageNum:num*2, appType:4, productTypeId:productTypeId},
		dataType:"json",
		beforeSend:function(data){
			if($('.newLoading').length == 0){
				$loading.clone(true).addClass('newLoading').removeClass('hide').appendTo($('#itemListBox'));
			}
		},
		success:function(data){
			if( pageType=="next"){ //下一页
				n += num;
				m += num;
			}
			var $orderobjOn = $(obj);
			
			//当前tab
			var $itemListBox = $('#itemListBox'),
				$itemSingle = $('.item-single.dsn'),
				$itemSingleUl = $('.item-list.displaybox.dsn');
			
			//滚动时，当前tab加载下一页，status为当前状态的数据，加载到这里
				
			var page = $orderobjOn.attr('data-page')*1;
			var loadingLength = $itemListBox.find('.loading:visible').length;
			//将hasNext转成boolean值才可比对
			var hasNext = $orderobjOn.attr('data-next') == 'true';
			
			if(hasNext === false){
				$('.newLoading').html('已更新至最后一页！');
				return false;
			}

			if(data.dataValue.list == '' && $itemListBox.find('.my-order-item:visible').length == 0){ //通过已存在Length数量判断当前tab是否在加载最后一页，避免同时判断为无订单状态
				var noItemTips = '<div id="noItemTips" class="list-box t-center borderbottomgrey">商品已下架</div>';
				$itemListBox.html(noItemTips);
			}else{
				var itemCountNum = 0;
				
					
				$.each(data.dataValue.list,function(iPro,dataPro){
					
					var $itemSingleClone = $itemSingle.clone(true);
					var $itemSingleUlClone = $itemSingleUl.clone(true);
					
					var curUrl = dataPro.picBase;
					var newImgUrl = curUrl + (curUrl.indexOf('?') > -1 ? '&':'?') + 'x-oss-process=image/resize,m_fill,w_263,h_263/format,jpg/quality,q_80/interlace,1';
					
					//将订单数据更新至$itemSingleClone
					$itemSingleClone.find('.single-item-btn').attr('data-id', dataPro.id);
					$itemSingleClone.find('.imgloading').attr('data-url', newImgUrl);
					$itemSingleClone.find('.imgloading').attr('src', newImgUrl);
					$itemSingleClone.find('.item-name').text(dataPro.name);
					$itemSingleClone.find('.priceOnShelf').text(dataPro.priceOnShelf);
					$itemSingleClone.find('.priceMarket').text(dataPro.price);
					$itemSingleClone.find('.join-shoppingCart-btn').attr('data-id', dataPro.id);
					
					if(dataPro && itemCountNum < 1){
						//插入页面
						$itemSingleClone.removeClass('dsn').appendTo($itemSingleUlClone);	
						$itemSingleUlClone.removeClass('dsn').appendTo($itemListBox);	
						itemCountNum += 1;
					}else if(dataPro && itemCountNum == 1){
						//插入页面
						$itemSingleClone.removeClass('dsn').appendTo($('.newCreate:visible'));
						$('.newCreate:visible').removeClass('newCreate');	
						itemCountNum = 0;
					}

				});
				
				//当前页数
				page += 1;
				$orderobjOn.attr('data-page', page);
				
				var liLength = $('.newCreate:visible li').length;
				if(liLength == 1){
					$('.newCreate:visible').append('<li class="boxflex01"></li>').removeClass('newCreate');
				}
			}
			
			$('.newLoading').remove();
			//重设图片高度
			setItemHeight();

			//最后一页，设置data-next为false
			if(data.dataValue.hasNext === false){
				$orderobjOn.attr('data-next', false)
			}
		},  
        error: function(){  
        	$.Showmsg('网络不给力，请稍后重试'); 
        } 
	});
};

/*//滚动到底部，当前tab（有.on的tab）加载下一页
$(window).scroll(function(event){
	var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
	var docHeight = $(document).height() - $(window).height()-1; //当前文档高度
	if(scrollt > docHeight){
		getItemList("next",'.con');
	}
});

//初始化，所有tab加载第一页
$(function(){ 
	getItemList("next",'.con');
});*/
