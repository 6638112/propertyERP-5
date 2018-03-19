$(function(){
	
	//条件判断+延迟执行，避免scroll事件频繁触发
	var isScrolling = true;
	
	function scrollNextPage(){
		
		isScrolling = true;
		
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
		var docHeight = $(document).height() - $(window).height()-1; //当前文档高度
		
		//页面加载完成时，不触发
		if(scrollt === 0){
			return false;
		}
		
		if(scrollt > docHeight){ 
		 	getNextPage('.cash-out-order-list-box');
		}
	}
	
	//滚动到底部，当前tab（有.on的tab）加载下一页
	$(window).scroll(function(){
		if(isScrolling){ 
			setTimeout(scrollNextPage,1000); 
			isScrolling = false;
		}
	});

	//初始化，加载第一页
	getNextPage('.cash-out-order-list-box');
	
});

var pageOptions = {
	num: 10,//每页显示的个数
	page: 1,
	itemNum: 1
}

function getNextPage(obj){
	var $loading = $('.loading');
	
	//分页加载订单
	(function ajaxPage(){
		$.ajax({
			url:"ebuyMerchant/settleCenter/orderNotSettle.json",
			type:"get",
			data:{page: pageOptions.page, pageNum:pageOptions.num},
			dataType:"json",
			beforeSend:function(data){
				if( $('.cash-out-order-list-box').attr('data-hasnext') === 'false' ){
					return false;
				}
				if($('.newLoading').length == 0){
					$loading.clone(true).addClass('newLoading').removeClass('hide').appendTo(obj);
				}
			},
			success:function(data){
				
				//加载下一页数据
				var $myOrderListBox = $(obj);
				var $singleItemWrap = $('.cash-out-order-single.hide');
				
				//没有可结算订单
				if(data.dataValue.list.length === 0){
					$('.newLoading').remove();
					$('.big-text-tips-box').removeClass('hide');
					return false;
				}
					
				$.each(data.dataValue.list,function(iPro,dataPro){
						
					var $singleItemWrapClone = $singleItemWrap.clone(true);
					var dataList = dataPro;
					
					//将订单数据更新至$singleItemWrapClone
					$singleItemWrapClone.find('.deliveryAddress').text(dataList.deliveryAddress);
					$singleItemWrapClone.find('.deliveryOrderTotalAmount').text( (dataList.deliveryOrderTotalAmount - (dataList.hasDelivRefund ? dataList.deliveryOrderRefundAmount:0)).toFixed(2) );
					$singleItemWrapClone.find('.productQty').text(dataList.productQty);
					$singleItemWrapClone.find('.deliveryOrderAddTime').text(dataList.deliveryOrderAddTime);
					$singleItemWrapClone.attr('href', 'ebuyMerchant/settleCenter/orderDetail.html?deliveryOrderId=' + dataList.deliveryOrderId);
					if(dataList.hasDelivRefund){
						$('<span class="bgred order-status">退</span>').insertAfter($singleItemWrapClone.find('.deliveryAddress'));
					}
					
					//插入页面
					$singleItemWrapClone.removeClass('hide').appendTo($myOrderListBox);						
				});

				$('.noOrderList').removeClass('hide');
				//当前页数
				pageOptions.page += 1;
				
				setTimeout(function(){
					$('.newLoading').remove();
					
					//最后一页
					if(data.dataValue.hasNext === false){
						$('.cash-out-order-list-box').attr('data-hasnext', 'false');
						$loading.clone(true).addClass('newLoading').removeClass('hide').html('已加载至最后一页').appendTo(obj);
					}
				},400);
			}
		});
	}());
}
