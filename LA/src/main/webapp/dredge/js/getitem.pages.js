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
		 	getNextPage('.my-order-item');
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
	getNextPage('.my-order-item');
	
});

var pageOptions = {
	num: 10,//每页显示的个数
	page: 1,
	itemNum: 1
}

function getNextPage(obj,callbackFn){
	var $loading = $('.loading');
	var dredgeTypeId = $('#dredgeTypeId').val() || getUrlParam('dredgeTypeId');
	var subTypeId = $('#subTypeId').val() || getUrlParam('subTypeId');
	
	//分页加载订单
	(function ajaxPage(){
		$.ajax({
			url:"../common/toUrl.do",
			type:"get",
			data:{"detailUrl" : "/dredge/qryProductList.json", page: pageOptions.page, pageNum:pageOptions.num, dredgeTypeId: dredgeTypeId, subTypeId:subTypeId, appType:3},
			dataType:"json",
			beforeSend:function(data){
				if( $('#myOrderItemBox').attr('data-hasnext') === 'false' ){
					return false;
				}
				if($('.newLoading').length == 0){
					$loading.clone(true).addClass('newLoading').removeClass('hide').insertAfter(obj);
				}
			},
			success:function(data){
				
				//如果商品数量不为0，则显示选择耗材入口
				if(data.dataValue.count !== 0){
					$('.item-select-entrance').removeClass('hide');
				}
				
				//加载下一页数据
				var $myOrderListBox = $(obj);
				var $singleItemWrap = $('.single-item-wrap.hide');
					
				$.each(data.dataValue.list,function(iPro,dataPro){
						
					var $singleItemWrapClone = $singleItemWrap.clone(true);
					var dataList = dataPro;
					
					//将订单数据更新至$singleItemWrapClone
					$singleItemWrapClone.find('.single-item-name').text(dataList.name);
					$singleItemWrapClone.find('.single-item-price').text(dataList.price);
					$singleItemWrapClone.find('.order-info-box').attr('data-itemid', dataList.id);
					$singleItemWrapClone.find('.item-info-img img').attr('src', dataList.pic);
					$singleItemWrapClone.find('a.item-url').attr('href', '../product/productDetail.do?readonly=true&ptId=' + dataList.id);
					
					//插入唯一id，为本地存储商品信息提供定位，label对应checkbox勾选
					$singleItemWrapClone.find('.itemNum').attr('id', 'itemNum' + pageOptions.itemNum);
					$singleItemWrapClone.find('.item-check-box').attr('id', dataList.id);
					pageOptions.itemNum += 1;
					
					//插入页面
					$singleItemWrapClone.removeClass('hide').appendTo($myOrderListBox);						
				});
						
				//当前页数
				pageOptions.page += 1;
				
				//最后一页
				if(data.dataValue.hasNext === false){
					$('.newLoading').removeClass('dsn').html('已加载至最后一页');
					$('#myOrderItemBox').attr('data-hasnext', 'false');
				}else{
					setTimeout(function(){
						$('.newLoading').remove();
					},400);
				}
				
				//读取设置已选择商品数据
				readItemLocalStorage();
				
				//页面返回加载完时获取已选商品信息
				if($('.item-check-box.single-check.item-checked').length > 0){
					getItemSelectedNumAndPrice();
				}
			}
		});
	}());
}
