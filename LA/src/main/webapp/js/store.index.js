/* *
 * *weijc
 * *20170726
 * */

var productData = null;
var androidAnniversaryShare, iosShareAnniversary, androidCustomerService, iosCustomerService;

//app进入，设置购物车数量
function setCartNum(param){
	$('#cartNum').text(param);
	if(param != '0'){
		$('#cartNum').removeClass('dsn');
	}
}

//判断安卓ios系统
var u = navigator.userAgent, app = navigator.appVersion;
var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端

var isfromApp = location.search.indexOf('jfqapp') > -1;

//更新体验店banner
function updateStoreBanner(){
	var dataParams = {'code':'EXPERIENCE_STORE_AD', 'city':'深圳', "detailUrl":"/ebuyNew/qryAds.json"};    	
	$.ajax({
		  url: "../common/toUrl.do",
		  async: true,
		  dataType:"json",
		  data:dataParams,
		  success: function(data){
			  if(data.status!="0000"){
				  showMsg(data.message);
			  }else{
				  if(data.dataValue.list.length != 0){
					  $('#storeBanner').html('');
					  var li_item = '';
					  var $productLi = $('.banner-li.dsn');
					  $.each(data.dataValue.list, function(i,item){
							  
						  var $productLiClone = $productLi.clone(true);
						  var curUrl = item.picUrl;
						  var newImgUrl = curUrl + (curUrl.indexOf('?') > -1 ? '&':'?') + 'x-oss-process=image/resize,m_fill,w_560,h_261/format,jpg/quality,q_95/interlace,1';
						  
						  $productLiClone.find('img').attr('src', newImgUrl);
						  
						  //item.linkUrl，为id时，跳转商品；为链接时，直接跳转纯静态页面
						  if(item.linkUrl.indexOf('http') > -1){
							  //纯静态H5页面广告链接
							  $productLiClone.find('a').attr('href', item.linkUrl);
						  }else{
							  $productLiClone.attr('data-id', item.linkUrl).addClass('product-jump');
						  }
						  
						  $productLiClone.removeClass('dsn').appendTo($('#storeBanner'));
					  
					  }); 
					  
					  TouchSlide({ 
					        slideCell:"#slideBox",
					        titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
					        mainCell:".bd ul", 
					        effect:"left", 
					        autoPage:true,//自动分页
					        autoPlay:true //自动播放
					  });
					    
					  var $li = $('#slideBox .hd ul li');
					  if($li.length == 1){
						  $li.hide();
					  }else{
						  $li.show();
					  }

				  }
				  
				  $('#slideBox').removeClass('opacity0');
			 
				  var productJump = document.querySelectorAll('.product-jump');
					if(isfromApp){
						if(isAndroid){
							//普通商品跳转详情页
							$(document).on('click', '.product-jump', function(e){
								e.preventDefault();
								var productId = $(this).attr('data-id'); 
								window.itemList.singleItemBtn(productId);
							})

						}
						if(isiOS){
							//普通商品跳转详情页
							$(document).on('click', '.product-jump', function(e){
								e.preventDefault();
							    var thisProductId = $(this).attr('data-id'); 
							    if(thisProductId){
							        setupWebViewJavascriptBridge(function(bridge) {
							            bridge.callHandler('singleItemBtn', {'itemId': thisProductId}, function(response) {});
							        });
							    }
							});

						}
					}else{
						$(document).on('click', '.product-jump', function(e){
							e.preventDefault();
							var productId = $(this).attr('data-id'); 
							if(productId){
								location.href = '../product/productDetail.do?ptId=' + productId;
							}
						})
					}
					
			  }
		  },
		  error: function(){  
           	showMsg('网络不给力，请稍后重试'); 
         }
	});
}

$(function(){
	new FastClick(document.body);
	
	//体验店时，才更新banner
	if($('#slideBox').length){
		updateStoreBanner();
	}
	
	//显示购物车
	$('.shopping-cart-index').removeClass('dsn');

	var $win = $(window),
		$fixtopbar = $('#tabMenu'),
		$storeListBox = $('#storeListBox'),
		topbar_top,
		win_top = 0;
	
	var shareLink = location.href;
	shareLink = shareLink + ((shareLink.indexOf('?') > -1 ? '&' : '?') + 'jfqsource=weixin');
	
	//解放区体验店banner
	if($('#slideBox').length){
		setTimeout(function(){
		    TouchSlide({ 
		        slideCell:"#slideBox",
		        titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
		        mainCell:".bd ul", 
		        effect:"left", 
		        autoPage:true,//自动分页
		        autoPlay:true //自动播放
		    });
		    
			var $li = $('#slideBox .hd ul li');
			if($li.length == 1){
				$li.hide();
			}
		}, 400)
	}
	//延迟200毫秒，避免ios下页面渲染抖动问题
	setTimeout(function(){
		$('body').removeClass('opacity0').scrollTop('0');

		//限时促销拖动效果
		if($("#limitBuyBox .swiper-container").length){
			var swiperLimitBuy = new Swiper('#limitBuyBox .swiper-container', {
		        slidesPerView: 3.3,
		        spaceBetween: 10,
				freeMode: true,
		        
		        //3.4.2版本才有效
		        slidesOffsetBefore: 10,
		        slidesOffsetAfter: 10,
		        
		        onInit: function(swiper){
		        	var slideWidth = $('#limitBuyBox .swiper-slide').width();
		        	$('#limitBuyBox .swiper-slide').find('img').height(slideWidth);
		        	//初始化前加上opacity0，防止初始化时布局抖动，完成后移除class
		        	$("#limitBuyBox .swiper-container").removeClass('opacity0');
		        }
		    });
		}
		
		//今日爆款拖动效果
		if($("#hotItemsBox .swiper-container").length){
			var swiperHotItems = new Swiper('#hotItemsBox .swiper-container', {
				slidesPerView: 3.3,
				spaceBetween: 10,
				freeMode: true,
				
				//3.4.2版本才有效
				slidesOffsetBefore: 10,
				slidesOffsetAfter: 10,
				
				onInit: function(swiper){
		        	var slideWidth = $('#hotItemsBox .swiper-slide').width();
		        	$('#hotItemsBox .swiper-slide').find('img').height(slideWidth);
					//初始化前加上opacity0，防止初始化时布局抖动，完成后移除class
					$("#hotItemsBox .swiper-container").removeClass('opacity0');
				}
			});
		}

	}, 200);
	
	//标签切换，加载时显示默认标签
	$(document).on('click', '#tabMenu .swiper-slide', function(){
		$(this).addClass('red').siblings().removeClass('red');
	});
	
	//购物车动画
	var $shoppingCart = $('.shopping-cart-index');
	var $itemNum = $('.item-num');
	var itemNumText = $itemNum.html()*1;
	
	$('.join-btn').click(function(event){
		var leftCount = $.trim($(this).attr("leftCount"));
		if(leftCount<=0||leftCount==""){
			showMsg("库存不足！暂时不能购买！");
			return;
		}
		var $itemListImg = $(this).siblings('.store-item-img').find('img');
		var $itemListImgPic = $itemListImg.clone().css({width:'40px', height:'40px'});
		var itemListImgPicWidth = $itemListImgPic.width();
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop;
		
		var offset = $('.shopping-cart-index').offset();
		var $flyer = $itemListImgPic;

		//加入购物车
		var prdtId = $(this).siblings('.store-item-img').attr("prdtId");
		
		//微信先调购物车接口
		if(!isfromApp){
			$.post("../cart/add2BuyCar.do", "ptId=" + prdtId + "&productQty="+ 1).success(function(response){
				 
				 if(response.status != '0000') {
					 if(response.message.indexOf("库存不足") > -1){
						 showMsg("该商品库存不足");
					 }else {
						 showMsg(response.message);
					 }
					 return;
				 }
				 
				 var curCount = +$itemNum.text();
				 if($itemNum.hasClass('dsn')){
					 $itemNum.removeClass('dsn')
				 }
				 $itemNum.html(curCount+=1).addClass('animated pulse');
				 
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
				 
				 setTimeout(function(){
					 $itemNum.removeClass('animated pulse');
				 }, 500);
			});
			
		//app 不用调接口
		}else{
			 
			 var curCount = +$itemNum.text();
			 if($itemNum.hasClass('dsn')){
				 $itemNum.removeClass('dsn')
			 }
			 $itemNum.html(curCount+=1).addClass('animated pulse');
			 
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
			 
			 setTimeout(function(){
				 $itemNum.removeClass('animated pulse');
			 }, 500);
			 
		}
		
	});
	
	//点击获取对应分类商品
	var isLoading = false;
	$(document).on('click', '#tabMenu .swiper-slide:not(".swiper-slide.red")', function(){
		var curIndex = $('#tabMenu .swiper-slide').index($(this));
		var curProductData = productData[curIndex].ptList;
		
		if(isLoading){
			return;
		}
		
		//loading时，打开遮罩层，禁止加载时切换分类
		isLoading = true;
		$('#loadingToast').removeClass('hide');
		
		$myOrderListBox.html('');
		$loading.clone(true).removeClass('hide').addClass('newLoading').appendTo($myOrderListBox);

		if(curProductData){
			loadCacheData($(this));
		}else{
			ajaxNext($(this));
		}
	});
	
	//获取店铺商品分类
	getProductType();
	
	function getProductType(){
		$.ajax({
			type:"get",
			url:"../common/toUrl.do",
			data:{supplyMerchantId: $('.float-menu').attr('data-id'), detailUrl: '/ebuyV2/qryStoreInfo.json'},
			dataType:"json",
			beforeSend:function(data){
			},
			success:function(data){
				if(data.status === '0000'){

					//更新店铺信息
					var oriDataValue = data.dataValue;
					if(oriDataValue.viewCountTodayStr === ''){
						$('#viewCount').text('今天3人逛过');
					}else{
						$('#viewCount').text(oriDataValue.viewCountTodayStr);
					}
					$('#storePhoneLink').attr('href', 'tel:' + oriDataValue.phone);
					$('#storePhone').text(oriDataValue.phone);
					$('#storeInfo').removeClass('opacity0');
					
					if(oriDataValue.id === '110214'){
						document.title = '解放区优选体验店';
					}else{
						document.title = oriDataValue.storeName;
					}
					//ios客户端下，触发事件重新获取标题
					if(isfromApp && isiOS){
						setupWebViewJavascriptBridge(function(bridge) {
							bridge.callHandler('resetTitle', {}, function(response) {});
						});
					}
					
					//店铺图片
					if(oriDataValue.shopPicList.length){
						
						$.each(oriDataValue.shopPicList, function(index, item){
							var $imgLi;
							var newImgUrl = item + (item.indexOf('?') > -1 ? '&':'?') + 'x-oss-process=image/resize,m_fill,w_320,h_240,limit_0/format,jpg/interlace,1/quality,q_95';
							
							$imgLi = $('<li class="swiper-slide"><img class="store-img" src="' + newImgUrl + '"/></li>')
							$imgLi.appendTo('#storeImgBox .swiper-wrapper');
						});

	                    var imgLength = oriDataValue.shopPicList.length < 3 ? 2 : 2.3;
						
						//店铺图片拖动效果
						var swiperStoreImg = new Swiper('#storeImgBox .swiper-container', {
							pagination: '#storeImgBox .swiper-pagination',
							slidesPerView: imgLength,
							spaceBetween: 10,
							freeMode: true,
							onInit: function(swiper){
								//初始化前加上opacity0，防止初始化时布局抖动，完成后移除class
								$('#storeImgBox .swiper-container').removeClass('opacity0');
							}
						});
						
					}
					
					//处理商品分类，缓存商品数据
					var oriData = data.dataValue.ebuyProdTypeList;
					
					$.each(oriData, function(index, item){
						var $typeLi;
						if(index === 0){
							$typeLi = $('<li class="swiper-slide red" data-page="1" data-next="false" data-id="' + item.id + '"><div>' + item.typeName + '</div></li>');
						}else{
							$typeLi = $('<li class="swiper-slide" data-page="1" data-next="false" data-id="' + item.id + '"><div>' + item.typeName + '</div></li>');
						}
						
						$typeLi.appendTo('#tabMenu .swiper-wrapper');
						
					});

                    productData = oriData;

                    var typeLength = productData.length < 5 ? productData.length : 4.3;
                    
                    //只有一个分类时，居左显示
                    if(typeLength === 1){
                    	$('.sell-date').addClass('nomuch-type');
                    }
					
					//商品分类拖动效果
					var swiper = new Swiper('#tabBox .swiper-container', {
						pagination: '#tabBox .swiper-pagination',
						slidesPerView: typeLength,
						spaceBetween: 0,
						freeMode: true,
						onInit: function(swiper){
							//初始化前加上opacity0，防止初始化时布局抖动，完成后移除class
							$("#tabBox .swiper-container").removeClass('opacity0');
						}
					});
					setSlideWidth('.sell-date');
					
					//初始化，tab加载第一页
					ajaxNext('#tabBox .swiper-slide.red');
					
				}else{
					showMsg(data.message);
				}
			}
		})
	}
	
	//翻页
	var num = 5;//每页显示的个数
	var $loading = $('.loading.hide');
	var $myOrderListBox =$('#storeListBox');
	var $myOrderItem = $('.store-item.dsn');
	//分页加载订单
	function ajaxNext(tabBox){
		
		//点击加载时，禁止滚动加载
		isScrolling = false;
		
		//loading时，打开遮罩层，禁止加载时切换分类
		isLoading = true;
		$('#loadingToast').removeClass('hide');
		
		$.ajax({
			type:"get",
			url:"../common/toUrl.do",
			data:{page:$(tabBox).attr('data-page'), pageNum:num, productTypeId: $(tabBox).attr('data-id'), supplyType: 2, storeId: $('.float-menu').attr('data-id'), detailUrl: '/ebuyV2/qryProdList4ExperienceStore.json'},
			dataType:"json",
			beforeSend:function(data){

				if($myOrderListBox.find('.newLoading').length){
					$myOrderListBox.find('.newLoading').remove();
				}
				$loading.clone(true).removeClass('hide').addClass('newLoading').appendTo($myOrderListBox);
				
			},
			success:function(data){
				
				if(data.status !== '0000'){
	            	showMsg(data.message); 
	        		isLoading = false;
					$('#loadingToast').addClass('hide');
					return;
				}
				
				var page = $(tabBox).attr('data-page')*1;
				var curIndex = $('#tabBox .swiper-slide').index($(tabBox));

				//通过已存在Length数量判断当前tab是否在加载最后一页，避免同时判断为无订单状态
				if(data.dataValue.productList.length === 0 && $myOrderListBox.find('.store-item:visible').length === 0){ 
					
					var noItemTips = '<div id="noItemTips" class="list-box bordertopgrey ptb20 t-center">该分类暂无商品</div>';
					$myOrderListBox.html(noItemTips);
					
				}else{
					//缓存商品数据
					//当前分类的第一页数据
					if(productData[curIndex].ptList === undefined){	
						productData[curIndex].ptList = data.dataValue;
						
					//第二页起，push到已缓存的数据里
					}else{
						$.each(data.dataValue.productList,function(iPro,dataPro){
							productData[curIndex].ptList.productList.push(dataPro);
						})
						productData[curIndex].ptList.hasNext = data.dataValue.hasNext;
					}

					$.each(data.dataValue.productList,function(iPro,dataPro){
						
						var $myOrderItemClone = $myOrderItem.clone(true);
						var curUrl = dataPro.picBase;
						if(curUrl){
							var newImgUrl = curUrl + (curUrl.indexOf('?') > -1 ? '&':'?') + 'x-oss-process=image/resize,m_fill,w_210,h_210,limit_0/format,jpg/interlace,1/quality,q_95';
						}
						
						$myOrderItemClone.find('.bgloading').attr('data-original', newImgUrl);
						$myOrderItemClone.find('.store-item-name').text(dataPro.name);
						$myOrderItemClone.find('.store-item-desc').text(dataPro.desc);
						$myOrderItemClone.find('.store-item-price').text(dataPro.priceOnShelf.toFixed(2));
						$myOrderItemClone.find('.market-price').text(dataPro.price.toFixed(2));
						$myOrderItemClone.find('.item-link').attr('href', '../product/productDetail.do?jfqsource=jfqstore&ptId=' + dataPro.id);
						$myOrderItemClone.find('.item-link').attr('data-itemid', dataPro.id);
						$myOrderItemClone.find('.store-item-img').attr('prdtId', dataPro.id);
						$myOrderItemClone.find('.join-btn').attr('leftCount', dataPro.leftCount);
						
						if(dataPro.sosName){
							$myOrderItemClone.find('.store-supplier').removeClass('dsn').find('.supplier-name').text(dataPro.sosName).attr('href');
						}
						if(dataPro.sosIntroduceUrl){
							$myOrderItemClone.find('.supplier-name').removeClass('grey').addClass('blue').attr('href', dataPro.sosIntroduceUrl);
						}
						
						$myOrderItemClone.removeClass('dsn').insertBefore($('.newLoading'));
						
						$myOrderItemClone.find('img.bgloading').lazyload(
								{effect: "fadeIn"}
						);
						
					});
				}

				//最后一页，设置data-next为false
				if(data.dataValue.hasNext === false){
					$(tabBox).attr('data-next', 'false');
					$myOrderListBox.find('.newLoading').addClass('bordertopgrey').html('亲，已经到底啦~');
				}else{
					page += 1;
					$(tabBox).attr('data-next', 'true');
					$(tabBox).attr('data-page', page);
				}
				
				//恢复商品分类可点击加载
				isLoading = false;
				$('#loadingToast').addClass('hide');
				
				//恢复可滚动加载
				isScrolling = true;
				
				//获取分类菜单到顶部距离
		        //topbar_top = $fixtopbar.offset().top;
			},  
            error: function(){  
            	showMsg('网络不给力，请稍后重试'); 
        		isLoading = false;
				$('#loadingToast').addClass('hide');
				//恢复可滚动加载
				isScrolling = true;
            } 
		});
	};
	
	function loadCacheData(tabBox){
		
		//点击加载时，禁止滚动加载
		isScrolling = false;
		
		var page = $(tabBox).attr('data-page')*1;
		var curIndex = $('#tabMenu .swiper-slide').index($(tabBox));
		var curProductData = productData[curIndex].ptList;
		
		if(curProductData !== undefined){
			//通过已存在Length数量判断当前tab是否在加载最后一页，避免同时判断为无订单状态
			if(curProductData.productList.length === 0 && $myOrderListBox.find('.store-item:visible').length === 0){ 
				
				var noItemTips = '<div id="noItemTips" class="list-box bordertopgrey ptb20 t-center">该分类暂无商品</div>';
				$myOrderListBox.html(noItemTips);
				
			}else{
		
				$.each(curProductData.productList,function(iPro,dataPro){

					var $myOrderItemClone = $myOrderItem.clone(true);
					var curUrl = dataPro.picBase;
					if(curUrl){
						var newImgUrl = curUrl + (curUrl.indexOf('?') > -1 ? '&':'?') + 'x-oss-process=image/resize,m_fill,w_210,h_210,limit_0/format,jpg/interlace,1/quality,q_95';
					}
					
					$myOrderItemClone.find('.bgloading').attr('data-original', newImgUrl);
					$myOrderItemClone.find('.store-item-name').text(dataPro.name);
					$myOrderItemClone.find('.store-item-desc').text(dataPro.desc);
					$myOrderItemClone.find('.store-item-price').text(dataPro.priceOnShelf.toFixed(2));
					$myOrderItemClone.find('.market-price').text(dataPro.price.toFixed(2));
					$myOrderItemClone.find('.item-link').attr('href', '../product/productDetail.do?jfqsource=jfqstore&ptId=' + dataPro.id);
					$myOrderItemClone.find('.item-link').attr('data-itemid', dataPro.id);
					$myOrderItemClone.find('.store-item-img').attr('prdtId', dataPro.id);
					$myOrderItemClone.find('.join-btn').attr('leftCount', dataPro.leftCount);

					if(dataPro.sosName){
						$myOrderItemClone.find('.store-supplier').removeClass('dsn').find('.supplier-name').text(dataPro.sosName).attr('href');
					}
					if(dataPro.sosIntroduceUrl){
						$myOrderItemClone.find('.supplier-name').removeClass('grey').addClass('blue').attr('href', dataPro.sosIntroduceUrl);
					}
					
					$myOrderItemClone.removeClass('dsn').insertBefore($('.newLoading'));
					
					$myOrderItemClone.find('img.bgloading').lazyload(
					    	{effect: "fadeIn"}
				    );
					
				});
			}

			//最后一页，设置data-next为false
			if(curProductData.hasNext === false){
				$(tabBox).attr('data-next', 'false');
				$myOrderListBox.find('.newLoading').addClass('bordertopgrey').html('亲，已经到底啦~');
			}
		}

		isLoading = false;
		$('#loadingToast').addClass('hide');
		
		//恢复可滚动加载
		isScrolling = true;
		
	}
	
	//条件判断+延迟执行，避免scroll事件频繁触发，页面加载时禁止滚动触发，避免重复加载同样商品，商品加载完成后才设为可滚动触发
	var isScrolling = false;
	
	function scrollNextPage(){
		
		isScrolling = true;
		
		if($('#tabMenu .swiper-slide.red').attr('data-next') === 'true'){
			var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
			var docHeight = $(document).height() - $(window).height() - 57; //当前文档高度 - 底部高度
			
			//页面加载完成时，不触发
			if(scrollt === 0){
				return false;
			}
			
			if(scrollt > docHeight){ 
			 	ajaxNext('#tabMenu .swiper-slide.red');
			}
		}
	}
	
	//滚动到底部，当前tab（有.on的tab）加载下一页
	$(window).scroll(function(event){
		if(isScrolling){ 
			setTimeout(scrollNextPage,1000); 
			isScrolling = false;
		}
	});
	
	//交互app及微信交互逻辑
	//app进入页面
	if(isfromApp){

		//安卓
		if(isAndroid){
			//进入限时促销详情
			$(document).on('click', '.limitbuy-link', function(e){
				e.preventDefault();
				var itemId = $(this).attr('data-itemid');
				window.itemList.limitbuyItemBtn(itemId);
			});
			
			//进入商品详情
			$(document).on('click', '.item-link', function(e){
				e.preventDefault();
				var itemId = $(this).attr('data-itemid');
				window.itemList.singleItemBtn(itemId);
			});
			
			//加入购物车按钮
			$(document).on('click', '.join-btn', function(){
				var itemId = $(this).siblings('.store-item-img').attr("prdtId");
				window.itemList.joinShoppingCartBtn(itemId);
			});
	        
		}

		//ios
		if(isiOS){
			//进入限时促销详情
			$(document).on('click', '.limitbuy-link', function(e){
				e.preventDefault();
				var itemId = $(this).attr('data-itemid');
				setupWebViewJavascriptBridge(function(bridge) {
					bridge.callHandler('limitbuyItemBtn', {'itemId': itemId}, function(response) {});
				});
			});
			
			//进入商品详情
			$(document).on('click', '.item-link', function(e){
				e.preventDefault();
				var itemId = $(this).attr('data-itemid');
				//document.location="jfq://"+"singleItemBtn"+":/"+itemId;	//ios同一个页面不能用两种方式交互
				setupWebViewJavascriptBridge(function(bridge) {
					bridge.callHandler('singleItemBtn', {'itemId': itemId}, function(response) {});
				});
			});
			
			//加入购物车按钮	
			$(document).on('click', '.join-btn', function(){
				var itemId = $(this).siblings('.store-item-img').attr("prdtId");
				setupWebViewJavascriptBridge(function(bridge) {
					bridge.callHandler('joinShoppingCart', {'itemId': itemId}, function(response) {});
				});
			});
	        
		}
		
		//购物车跳转
		var testNum = 0;
	    $(document).on('click','.shopping-cart-index',function(e){
	    	testNum += 1; 
	    	if($('#testNum').length){
	    		$('#testNum').text(testNum);
	    	}
			e.preventDefault();
			if(isAndroid){
				window.itemDetails.shoppingCartBtn('param');
			}
			if(isiOS){
				setupWebViewJavascriptBridge(function(bridge) {
					bridge.callHandler('clientDefineAction', {'type':'1','controll':'ShopCarOrderingViewController'}, function(response) {});
				});
			}
			
		})

	    //app分享交互方法
	    var shareInfo = {
				title: '供港品质好生鲜，尽在解放区',		// 分享标题
				friendTitle: '供港品质好生鲜，尽在解放区',		// 分享标题	//app目前取这个标题
				desc: '解放区提供便利快捷物业服务、供港高品生鲜，到店免费体验还有优惠~',		// 分享描述
				link: getCurHref('www') + '/LA/product/viewStore.do?storeId=' + $('.float-menu').attr('data-id'),	// 分享链接
				imgIcon: getCurHref('resource') + '/docs/commonimages/jfqlogo_share.png'
			};
	    
	    androidAnniversaryShare = function(){
	    	window.storeShare.shareToFriendStore(JSON.stringify(shareInfo));
	    }
	    
	    iosShareAnniversary = function(){
	    	setupWebViewJavascriptBridge(function(bridge) {
				bridge.callHandler('shareToFriendServer', shareInfo, function(response) {});
			});
	    }
	    
	    //联系卖家
	    androidCustomerService = function(){
	    	window.callStorePhone.customerService(JSON.stringify({'storePhone': $('#storePhone').text()}));
	    }
	    
	    iosCustomerService = function(){
	    	setupWebViewJavascriptBridge(function(bridge) {
				bridge.callHandler('customerService', {'storePhone': $('#storePhone').text()}, function(response) {});
			});
	    }
		
	//微信进入页面
	}else{

		//获取购物车商品数量
		$.get("../cart/qryBuyCarProductCount.do", function(data,status){
			if(data.dataValue.productCount !== 0){
				$itemNum.removeClass('dsn').html(data.dataValue.productCount);
			}
		});
		
		//显示消费券入口
		$('.coupon-list-icon').removeClass('dsn');

		//微信分享
		setTimeout(function(){
			$.getScript('https://res.wx.qq.com/open/js/jweixin-1.0.0.js', function(){
			   $.getScript('../js/wx.share.info.js', function(){
				    //轻应用分享
					setShareInfo({
						title: '供港品质好生鲜，尽在解放区',		// 分享标题
						desc: '解放区提供便利快捷物业服务、供港高品生鲜，到店免费体验还有优惠~',		// 分享描述
						link: location.href,	// 分享链接
						imgIcon: getCurHref('resource') + '/docs/commonimages/jfqlogo_share.png'
					});
				});
			});
		}, 1500);
		
		//跳转商品，缓存数据及tab状态
		/*$(document).on('click', '.item-link', function(e){
			e.preventDefault();
			
			var curTabIndex = $('#tabMenu .swiper-slide').index($('#tabMenu .swiper-slide.red')); 
			var curPtData = productData;
			var stateData = {curTabIndex:curTabIndex, curPtData:curPtData};
			
			localStorage.stateData = JSON.stringify(stateData);
			
			location.href = $(this)[0].href;
			
		})*/
		
		/*if(localStorage.stateData) {
			
			var stateData = JSON.parse(localStorage.stateData);  
			var curTabIndex = stateData.curTabIndex;
			var productData = stateData.curPtData;
			
			var curProductData = productData[curTabIndex].ptList;
			
			$('#storeListBox').html('');
			
			//恢复商品列表
			loadCacheData($('#tabMenu .swiper-slide').eq(curTabIndex)); 
			
			return;
			
		};*/
		
	}
	
	function setSlideWidth(obj){
		var totalWidth = $(obj).find('li').width()*$(obj).find('li').length;
		$(obj).width(totalWidth);
	}

	//获取元素的纵坐标（相对于窗口），如果元素e的父元素刚好设置了定位，此时e.offsetTop的距离为e到父元素的距离，再加上父元素到页面顶部的距离e.offsetParent，才等于e到页面顶部的距离
	function getTop(e){
	    var offset=e.offsetTop;
	    if(e.offsetParent!=null){
	    	offset+=getTop(e.offsetParent);
	    }
	    return offset;
	}

	//检测是否支持sticky
	function isSupportSticky() {
	    var prefixTestList = ['', '-webkit-'];
	    var stickyText = '';
	    for (var i = 0; i < prefixTestList.length; i++ ) {
	        stickyText += 'position:' + prefixTestList[i] + 'sticky;';
	    }
	    //创建一个dom来检查
	    var stickydiv = document.createElement('div');
	    stickydiv.style.cssText = stickyText;
	    document.body.appendChild(stickydiv);
	    var isSupport = /sticky/i.test(window.getComputedStyle(stickydiv).position);
	    document.body.removeChild(stickydiv);
	    stickydiv = null;
	    return isSupport;
	}
	var isSupportSticky = isSupportSticky();
	
	//不支持sticky的情况
	if(!isSupportSticky){
		
		//页面刚开始加载时获取的悬浮菜单offsetTop不准确(此时页面元素尚未加载完成，高度尚未初始化完成)，定时获取悬浮菜单的offsetTop
		setTimeout(function(){
			topbar_top = $fixtopbar.offset().top;
		},400)
		
	    changeFixedStatus();
	    $win.on('scroll', changeFixedStatus);
	    
	    function changeFixedStatus(){
	        win_top = $win.scrollTop();
	        
	        if(topbar_top === undefined){
	        	topbar_top = $fixtopbar.offset().top;
	        }
	        
	        if(win_top >= topbar_top){
	            $fixtopbar.addClass('nav-fixed');
				$storeListBox.addClass('padding-tab');
	        }else{
	            $fixtopbar.removeClass('nav-fixed padding-tab');
				$storeListBox.removeClass('padding-tab');
	        }
	    }
	}
	
});