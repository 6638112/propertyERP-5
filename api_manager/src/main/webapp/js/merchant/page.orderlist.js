$(function(){
	new FastClick(document.body);

	TouchSlide({ slideCell:"#tabBox",
		endFun:function(i){ //高度自适应
			var bd = document.getElementById("tabBox-bd");
			bd.parentNode.style.height = bd.children[i].children[0].offsetHeight + 55 + "px";
			if(i>0)bd.parentNode.style.transition="200ms";//添加动画效果
		}
	});
	
	var num = 6;//每页显示的个数
	var n = 0;
	var m = -num;
	var $loading = $('.loading');
	var isLoading = false;
	//分页加载订单
	function ajax(pageType,tabTitle){
		isLoading = true;
		var $orderTabTitleOn = $(tabTitle);
		
		//listIndexOn等于0为待处理状态，listIndexOn等于1为已处理状态
		var listIndexOn = $orderTabTitleOn.index();
		
		//滚动时，当前tab加载下一页，status为当前状态的数据，加载到这里
		
		var page = $orderTabTitleOn.attr('data-page')*1;
		var $myOrderListBox = $('.con').eq(listIndexOn).find('.myOrderList');
		var $myOrderItem = $('.my-order-item.hide');
		var $itemListInfo = $('.item-list-info.hide');
		//将hasNext转成boolean值才可比对
		var hasNext = $orderTabTitleOn.attr('data-next') == 'true';
		$.ajax({
			type:"get",
			url:"getOrderList.html",
			data:{page:$(tabTitle).attr('data-page'), pageNum:num, status:$(tabTitle).attr('status')},
			dataType:"json",
			beforeSend:function(data){
				if($myOrderListBox.find('.newLoading').length == 0){
					$loading.clone(true).addClass('newLoading').removeClass('hide').appendTo($('.con').eq($(tabTitle).index()).find('.myOrderList'));
				}
				
				if(hasNext === false){
					$("#tabBox .tempWrap").height($myOrderListBox.height() + 56);
					$myOrderListBox.find('.newLoading').html('已更新至最后一页！');
					return false;
				}
			},
			success:function(data){

				var tabText = $orderTabTitleOn.find('a').text();
				if(data.dataValue.list == ''){
					var noItemTips = '<div id="noItemTips" class="list-box t-center">暂无【' + tabText + '】订单！</div>';
					$myOrderListBox.html(noItemTips);
				}else{
			
					$.each(data.dataValue.list,function(iPro,dataPro){
						
						var $myOrderItemClone = $myOrderItem.clone(true);
						
						if(listIndexOn == 1){
							$myOrderItemClone.find('.order-item-check-box').remove();
						}

						//将订单数据更新至$myOrderItemClone
						if (dataPro.userDeliveryType == 2) {
                            $myOrderItemClone.find('.icon-address-text').text("自提");
                        } else {
                            $myOrderItemClone.find('.icon-address-text').text(dataPro.deliverAddr);
						}

						$myOrderItemClone.find('.icon-person').text(dataPro.receivePeople == null ? "" : dataPro.receivePeople);
						$myOrderItemClone.find('.icon-orderNo-text').text(dataPro.orderNo);
						$myOrderItemClone.find('.icon-time-text').text(dataPro.payTimeStr);
						$myOrderItemClone.find('.total-item-num').text(dataPro.totalQty);
						$myOrderItemClone.find('.total-item-price').text((dataPro.tatalPrice + dataPro.deliverMoney).toFixed(2));
						$myOrderItemClone.find('.deliv-item-price').text(dataPro.deliverMoney);
						$myOrderItemClone.find('.icon-tel-text').attr("href", 'tel:' + dataPro.phone);
						$myOrderItemClone.find('span.phone-number').text(dataPro.phone);
						$myOrderItemClone.find('span.app-number').text(dataPro.huaId);
						if(dataPro.status == 1) {
							$myOrderItemClone.find('.order-title-buff-text').text("待处理");
							$myOrderItemClone.find('.order-title-buff-icon').attr("src", "../images/merchant/icons-shop-clock.png");
						} else if(dataPro.status == 2 ) {
							$myOrderItemClone.find('.item-price').removeClass('borderbottomgrey');
							$myOrderItemClone.find('.order-title-info').addClass("border-left-green");
							$myOrderItemClone.find('.order-title-buff').addClass("green");
							$myOrderItemClone.find('.order-title-buff-text').text("已发货");
							$myOrderItemClone.find('.order-title-buff-icon').attr("src", "../images/merchant/icons-shop-bag.png");
							$myOrderItemClone.find('.divideBox01').remove();
						} else if(dataPro.status == 3) {
							$myOrderItemClone.find('.item-price').removeClass('borderbottomgrey');
							$myOrderItemClone.find('.order-title-info').addClass("border-left-green");
							$myOrderItemClone.find('.order-title-buff').addClass("green");
							$myOrderItemClone.find('.order-title-buff-text').text("已完成");
							$myOrderItemClone.find('.order-title-buff-icon').attr("src", "../images/merchant/icons-shop-checkdone.png");
							$myOrderItemClone.find('.divideBox01').remove();
						}
						
						$.each(dataPro.productList,function(plIndex,plData){
							
							var $itemListInfoClone = $itemListInfo.clone(true);
							
							//插入商品名称等
							$itemListInfoClone.find('.item-list-title').text(plData.productName);
							$itemListInfoClone.find('.single-item-price').text(plData.price);
							$itemListInfoClone.find('.single-item-num').text(plData.qty);
							$itemListInfoClone.find('.single-item-image').attr("src", plData.picBase);
							
							
							//将商品插入列表
							$itemListInfoClone.removeClass('hide').prependTo($myOrderItemClone.find('.item-list-box'));
							
						});
						
						//未处理订单，插入订单号
						if($orderTabTitleOn.attr('status') == 1){
							$myOrderItemClone.find('.item-list-check-btn-box').attr('deli-order-id', dataPro.id);
							$myOrderItemClone.find('.item-list-check-btn-box').attr('order-id', dataPro.orderId);

							//复制订单信息
							$myOrderItemClone.find('.order-item-check-box').attr('order-id', dataPro.id);
						}
						//已处理订单，去除按钮
						if($orderTabTitleOn.attr('status') == 2){
							$myOrderItemClone.find('.item-list-check-btn-box').remove();	
						}
						
						//插入页面
						$myOrderItemClone.removeClass('hide').insertBefore($myOrderListBox.find('.newLoading'));	

					});
					
					//当前页数
					page += 1;
					$orderTabTitleOn.attr('data-page', page);
					//重设外层高度
					$("#tabBox .tempWrap").height($myOrderListBox.height() + 58);
				}
				
				/*setTimeout(function(){
					$myOrderListBox.find('.newLoading').remove();
				},400);*/

				//最后一页，设置data-next为false
				if(data.dataValue.hasNext === false){
					$orderTabTitleOn.attr('data-next', false)
				}
				isLoading = false;
			},  
            error: function(){  
            	$.Showmsg('网络不给力，请稍后重试'); 
				isLoading = false;
            } 
		});
	};
	
	//订单按钮  确认发货
	$('.item-list-check-btn-box').click(function(){

		var deliverOrderId = $(this).attr('deli-order-id');
		var orderId = $(this).attr('order-id');
		$curMyOrderItem = $(this).parents('.my-order-item');
		$curOrderListBox = $(this).parents('.myOrderList');
		
		$.ajax({
			type:"post",
			url:"deliveryOrder.html",
			data:{deliverOrderId : deliverOrderId, orderId : orderId},
			dataType:"json",
			beforeSend:function(){
				$.Showmsg("正在发货…");
			},
			success:function(data){
				try {
					if (data.status == '0000') {
						$.Showmsg('确认发货成功！');
						$curMyOrderItem.addClass('animated2s rotateZoomOut');
						setTimeout(function(){
							//不刷新页面插入发货成功的订单，有可能导致下拉加载出刚发货的订单，出现重复订单的情况。此处在发货成功后刷新页面，避免此问题
							location.reload();
						}, 2000);
					} else {
						$.Showmsg(data.message);
						return;
					}
				} catch (e) {
					$.Showmsg(e);
				}

				
				
			},  
            error: function(){  
            	$.Showmsg('网络不给力，请稍后重试'); 
            } 
		});
	});
	
	//滚动到底部，当前tab（有.on的tab）加载下一页
	$(window).scroll(function(event){
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
		var docHeight = $(document).height() - $(window).height()-1; //当前文档高度
		if(scrollt > docHeight && !isLoading){
		 	ajax("next",'.order-tab-title.on');
		}
	});
	
	//初始化，所有tab加载第一页
	$(function(){ 
		ajax("next",'.order-tab-title.on');
		ajax("next",'.order-tab-title.off');
	});	
	
	//复制订单信息
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	
	$('.copy-order-info').click(function(event){
		var $thisCheck = $(this).find('.freight-check-icon');
		if($thisCheck.hasClass('on')){
			$thisCheck.removeClass('on');
			$('.my-order').find('.freight-check-icon').removeClass('on');
			//显示订单勾选
			$('.myOrderList').find('.order-item-check-box').addClass('hide');
		}else{
			$('.my-order').find('.freight-check-icon').addClass('on');
			$thisCheck.addClass('on');
			//隐藏订单勾选
			$('.myOrderList').find('.order-item-check-box').removeClass('hide');
		}
	});
	
	$('.order-item-check-box').click(function(event){
		var $thisCheck = $(this);
			if($thisCheck.hasClass('on')){
				$thisCheck.removeClass('on');
				$('.order-all-check').removeClass('on');
			}else{
				$thisCheck.addClass('on');
				var count = 0;
				$('.order-item-check-box').each(function(i, val){
					if($(this).hasClass('on')){
						count++;
					}
					if(count == $('.order-item-check-box:visible').length){
						$('.order-all-check').addClass('on');
					}
				});
			}
	});
	
	//复制到粘贴板
	$('#oncopy').click(function(event){
		if(checkOrderSelected().length === 0){
			showMsg('请先选择订单');
			return;
		}
		var $this = $(this);
		
		//获取订单信息
		getOrderInfo(checkOrderSelected());
		
	})
	
	$("#checkCopyBtn").click(function(){
		$('html,body').removeClass('overhidden');
		$("#tabBox").removeClass('minheightp100 heightp100');
		$("#copyTipsBox").addClass('dsn');
		OpenAppBySchema('weixin://');
	})
	
	function checkOrderSelected(){
		var ids = [];
		$('.order-item-check-box:visible').each(function(i, val){
			var orderId = $(this).attr('order-id');
			if($(this).hasClass('on')){
				ids.push(orderId);
			}
		});
		return ids;
	}
	
	function getOrderInfo(orderIdArray){
		$.ajax({
			type:"get",
			url:"../copyOrder.json",
			data:{ids:JSON.stringify(orderIdArray)},
			dataType:"json",
			async: false,
			success:function(data){
				$('#oncopy').attr('data-clipboard-text', '订单信息链接：' + data.dataValue.url + ' 提取码：' + data.dataValue.code);
				
				//显示提示信息
				$('html,body').addClass('overhidden');
				$("#tabBox").addClass('minheightp100 heightp100');
				$("#copyTipsBox").removeClass('dsn');

				if(!Clipboard.isSupported()){
					$(".copy-title").text('请复制订单信息');
					$(".copy-info").text('订单信息链接：' + data.dataValue.url + ' 提取码：' + data.dataValue.code).removeClass('dsn');
				}
				var clipboard = new Clipboard('#oncopy');
				//复制订单信息，安卓可以通过clipboard复制，ios的wkwebview不支持该方法复制，需将信息转给ios客户端进行复制
				if(isAndroid){
					var clipboard = new Clipboard('#oncopy');
				}
				if(isiOS && navigator.userAgent.indexOf('Safari') === -1){
					document.location='jfq://'+'copyShareInfo'+'/' + '订单信息链接：' + data.dataValue.url + ' 提取码：' + data.dataValue.code;
				}
			}
		});
	}
});