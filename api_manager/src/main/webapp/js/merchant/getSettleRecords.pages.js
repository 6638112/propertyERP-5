//标签切换，加载时显示默认标签
function tabSwap(tabObj, conObj){
	var hashNum;
	
	$(tabObj).click(function(){
		var $this = $(this);
		var thisIndex = $this.index();
		var	state = {
				'url':'#orderNum=' + thisIndex
			};
		
		//点击切换标签
		$this.addClass('on').siblings().removeClass('on');
		$(conObj).eq(thisIndex).show()
				.siblings().hide();
			
		//记录上一次选中的tab
		history.replaceState(state, '', location.origin + location.pathname + state.url);
	});
	
	//加载时显示默认标签。如果存在浏览记录，则按照浏览记录显示标签
	if(location.hash){
		hashNum = location.hash.substring(10);
	}else{
	//否则显示第一个标签	
		hashNum = 0;
	}
			
	//页面加载时触发默认显示标签
	$(tabObj).eq(hashNum).click();
}

$(function(){
	tabSwap('.order-tab li', '.order-con');
});

var num = 10;//每页显示的个数
var $loading = $('.loading');
//分页加载订单
function ajax(tabTitle){
	$.ajax({
		type:"get",
		url:"ebuyMerchant/settleCenter/settleRecords.json",
		data:{page:$(tabTitle).attr('data-page'), pageNum:num, settleStatus:$(tabTitle).attr('data-settleStatus')},
		dataType:"json",
		beforeSend:function(data){
			var $curConBox = $('.order-con').eq($(tabTitle).index());
			
			if($curConBox.find('.newLoading').length == 0){
				$loading.clone(true).addClass('newLoading').removeClass('hide').appendTo($curConBox.find('.cash-out-order-list-box'));
			}

		},
		success:function(data){
			var $orderTabTitleOn = $(tabTitle);
			
			//listIndexOn等于0为待处理状态，listIndexOn等于1为已处理状态
			var listIndexOn = $orderTabTitleOn.index();
			
			//滚动时，当前tab加载下一页，status为当前状态的数据，加载到这里
			var page = $orderTabTitleOn.attr('data-page')*1;
			var $myOrderListBox = $('.order-con').eq(listIndexOn).find('.cash-out-order-list-box');
			var $myOrderItem = $('.order-list.hide');					
			var loadingLength = $myOrderListBox.find('.loading:visible').length;

			var tabText = $orderTabTitleOn.find('a').text();

			if(data.dataValue.list == '' && $myOrderListBox.find('.order-list:visible').length == 0){ //通过已存在Length数量判断当前tab是否在加载最后一页，避免同时判断为无订单状态
				var noItemTips = '<div id="noItemTips" class="list-box t-center borderbottomgrey">暂无【' + tabText + '】订单！</div>';
				$myOrderListBox.html(noItemTips);
			}else{
		
				$.each(data.dataValue.list,function(iPro,dataPro){
					var $myOrderItemClone = $myOrderItem.clone(true);

					//将订单数据更新至$myOrderItemClone
					$myOrderItemClone.find('.settle-date').text(dataPro.sys0AddTime);
					$myOrderItemClone.find('.settle-amount').text(dataPro.totalAmount);
					$myOrderItemClone.find('.settle-count').text(dataPro.settleOrderCount);
					$myOrderItemClone.attr('href','ebuyMerchant/settleCenter/settleRecordDetail.html?applyId=' + dataPro.id);

					if(dataPro.auditStatus === 1){
						$myOrderItemClone.find('.settle-status').text('审核中');
					}else if(dataPro.auditStatus === 2){
						$myOrderItemClone.find('.settle-status').removeClass('grey').addClass('red').text('审核不通过');
						$myOrderItemClone.find('.divide-box').addClass('bggrey02 ptb10').html('<div class="mleft15 red">' + dataPro.handlerNote + '</div>');
					}else if(dataPro.auditStatus === 3){
						$myOrderItemClone.find('.settle-status').text('已结算');
					}
					
					//插入页面
					$myOrderItemClone.removeClass('hide').insertBefore($myOrderListBox.find('.newLoading'));
				});
				
				//当前页数
				page += 1;
				$orderTabTitleOn.attr('data-page', page);
			}

			//最后一页，设置data-next为false
			if(data.dataValue.hasNext === false){
				$orderTabTitleOn.attr('data-next', false)
				
				$myOrderListBox.find('.newLoading').html('已更新至最后一页！');
			}else{
				setTimeout(function(){
					$myOrderListBox.find('.newLoading').remove();
				},400);
			}
		},  
        error: function(){  
        	$.Showmsg('网络不给力，请稍后重试'); 
        } 
	});
};

//条件判断+延迟执行，避免scroll事件频繁触发
var isScrolling = true;

function scrollNextPage(){
	
	isScrolling = true;
	
	if($('.order-tab-title.on').attr('data-next') === 'true'){
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
		var docHeight = $(document).height() - $(window).height()-1; //当前文档高度
		
		//页面加载完成时，不触发
		if(scrollt === 0){
			return false;
		}
		
		if(scrollt > docHeight){ 
		 	ajax('.order-tab-title.on');
		}
	}
}

//初始化，所有tab加载第一页
$(function(){ 
	ajax('#orderTab01');
	ajax('#orderTab02');
	
	//滚动到底部，当前tab（有.on的tab）加载下一页
	$(window).scroll(function(event){
		if(isScrolling){ 
			setTimeout(scrollNextPage,1000); 
			isScrolling = false;
		}
	});
});