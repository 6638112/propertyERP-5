$(function(){
	//确认完成按钮
	/* $(document).on('click', '.billComplete', function(e){
		var billId = $(this).attr('data-billId');
		e.preventDefault();
		$.ajax({
			type:"post",
			url:"../common/toUrl.do",
			data:{"detailUrl" : "/dredge/finishBill.json", dredgeBillId: billId},
			dataType:"json",
			success:function(data){
				location.reload();
			},  
            error: function(){  
            	showMsg('网络不给力，请稍后重试'); 
            } 
		});
	}); */
	
	//取消订单20171010
	$('.cancel-btn').click(function(){
		if(!confirm('是否取消订单？')){
			return false;
		}
		var billId = $(this).attr('data-billId');

		$.ajax({
			type:"post",
			url:"cancelDredge.json",
			data:{id : billId},
			dataType:"json",
			beforeSend:function(){
				showMsg("正在取消…");
			},
			success:function(data){
				if (data.status == '0000') {
					showMsg('订单取消成功！');
					setTimeout(function(){
						window.location.reload();
					},1000);
				} else {
					showMsg(data.message);

				}
			}, 
            error: function(){  
            	showMsg('网络不给力，请稍后重试'); 
            } 
		});
	});
	
	//预估费用提示
	$(document).on('click', '.yugutip', function(e){
		e.preventDefault();
		$('#wrapBox').addClass('heightp100');
		$('.wrap-bg').removeClass('hide');
	});
	
	//费用提示
	$('.back-btn').click(function(){
		$('.wrap-bg').addClass('hide');
		$('#wrapBox').removeClass('heightp100');
	});
});

//星级评价
function setMasterStars(obj, widthPercent){
	$(obj).animate({'width':widthPercent}, 1200);
}

var orderType = 1;
var ishistroy = getUrlParam('histroy');
if(ishistroy == 1){
    orderType = 2;
    $('.header-title').text('历史订单');
	$('.histroylink, .item-list-arrow-box.order-pay-detail').addClass('hide');
	document.title = '历史订单';
}

var num = 5;//每页显示的个数
var n = 0;
var m = -num;
var $loading = $('.loading');
//分页加载订单
function ajax(pageType,tabTitle){
	$.ajax({
		type:"get",
		url:"qryMyDredgeBillList.json",
		data:{page:$(tabTitle).attr('data-page'), pageNum:num, type:orderType},
		dataType:"json",
		success:function(data){
			if( pageType=="next"){ //下一页
				n += num;
				m += num;
			}
			var $orderTabTitleOn = $(tabTitle);
			
			//listIndexOn等于0为待处理状态，listIndexOn等于1为已处理状态
			var listIndexOn = $orderTabTitleOn.index();
			
			//当前tab
			var listIndexCur = $('.order-tab-title').index();
			var $myOrderListBoxCur = $('.con').eq(listIndexCur).find('.myOrderList');
			
			//滚动时，当前tab加载下一页，status为当前状态的数据，加载到这里
				
			var page = $orderTabTitleOn.attr('data-page')*1;
			var $myOrderListBox = $('.con').eq(listIndexOn).find('.myOrderList');
			var $myOrderItem = $('.my-order-item.hide');
            var $itemListInfo = $('.order-info-box.hide');

			var tabText = $orderTabTitleOn.find('a').text();

			if(data.dataValue.list == '' && $myOrderListBox.find('.my-order-item:visible').length == 0){ //通过已存在Length数量判断当前tab是否在加载最后一页，避免同时判断为无订单状态
				var noItemTips = '<div id="noItemTips" class="list-box t-center bordertopgrey">暂无该类订单信息！</div>';
				$myOrderListBox.html(noItemTips);
			}else{
		
				$.each(data.dataValue.list,function(iPro,dataPro){

					var $myOrderItemClone = $myOrderItem.clone(true);

					var picSer = '?x-oss-process=image/resize,m_fill,w_92,h_92/format,jpg/interlace,1';
					var curUrl = dataPro.repairUrl;
					var newImgUrl = curUrl?curUrl:'images/master_info_img.png' + picSer;

					var dredgeTypePic = dataPro.dredgeTypePicURL;
					var dredgeTypePicSer = dredgeTypePic + picSer;
					var newDredgeProductName = '';

					//将订单数据更新至$myOrderItemClone
					$myOrderItemClone.find('.dataValueParentTypeName').text(dataPro.parentTypeName);
					$myOrderItemClone.find('.dataValueType').text(dataPro.type);
                    $myOrderItemClone.find('.icon-yyorder-type').attr('src',dredgeTypePicSer);
					$myOrderItemClone.find('.icon-address-text').text(dataPro.address);
					$myOrderItemClone.find('.order-number-text').text(dataPro.billNo);
					if(dataPro.billType == 5 && dataPro.dredgeProductName !== undefined){
						newDredgeProductName = dataPro.dredgeProductName + '(此单服务前付款)';
					}else if(dataPro.dredgeProductName !== undefined){
						newDredgeProductName = dataPro.dredgeProductName + '(此单服务后付款)';
					}
					$myOrderItemClone.find('.dredgeProductName').text(newDredgeProductName);

					$myOrderItemClone.find('.singleInfoBox').attr('href','qryDredgeBillDetail.do?id=' + dataPro.id + '&billType=' + dataPro.billType);

					//$myOrderItemClone.find('.order-title-buff-text').text(dataPro.statusDesc);	//statusDesc该字段已废弃
					
					//订单状态文案调整20171023
					var newStatusDesc = '';
					switch(dataPro.combineStatus){
						case 1: 
							newStatusDesc = '待付款'; break;
						case 2: 
							newStatusDesc = '待分配'; break;
						case 3: 
							newStatusDesc = '待服务'; break;
						case 4: 
							newStatusDesc = '已服务'; break;
						case 5: 
							newStatusDesc = '已完成'; break;
						case 6: 
							newStatusDesc = '已取消'; break;
						case 7: 
							newStatusDesc = '退款中'; break;
						case 8: 
							newStatusDesc = '已退款'; break;
					}
					$myOrderItemClone.find('.order-title-buff-text').text(newStatusDesc);

					if(dataPro.estimateDoorTime!==null && dataPro.estimateDoorTime!=='' && dataPro.estimateDoorTime!==undefined){
                        $myOrderItemClone.find('.icon-datedoortime').removeClass('hide');
                        $myOrderItemClone.find('.icon-datedoortime-text').text(dataPro.estimateDoorTime);
                        $myOrderItemClone.find('.icon-expectdate').addClass('hide');
					}else {
                        $myOrderItemClone.find('.icon-expectdate-text').text(dataPro.expectDate);
					}

                    //dataPro.status:0:预付款没付钱，1：派单中，2：已接单，3：完成未评价，4：已取消，5：完成已评价，6：物业关闭，7：师傅设置免费，8：师傅设置需要付款
					//dataPro.billType：1：普通维修单，2：物业转单，3：物业报修单（旧），4：物业报修单（新），5：先付款单

					//billType==5？Y：（看dataPro.status是不是等于8，是：确认完成，否：确认支付）N：（看dataPro.status是不是等于7、8，是：确认支付，否：隐藏）

					var $itemPayInfoClone = $myOrderItemClone.find('.order-pay-detail');
					var $itemPayAmount = $myOrderItemClone.find('.order-pay-amount');

					//订单列表新增【取消订单】按钮20171010
                    //服务后付款仅在待分配状态下提供取消订单按钮，其他情况均不支持用户自主取消订单
                    if(dataPro.billType !== 5 && dataPro.combineStatus === 2){
                    	//订单列表新增【取消订单】按钮20171010
                    	$myOrderItemClone.find('.cancel-btn').attr('data-billId', dataPro.id).removeClass('hide');	
                    	$itemPayInfoClone.removeClass('mright15').addClass('mright5');
                    }
                    //服务前付款仅在待付款状态下提供取消订单按钮，其他情况均不支持用户自主取消订单
                    if(dataPro.billType === 5 && dataPro.combineStatus === 1){
                    	//订单列表新增【取消订单】按钮20171010
                    	$myOrderItemClone.find('.cancel-btn').attr('data-billId', dataPro.id).removeClass('hide');	
                    }

					if(dataPro.status == 7 || dataPro.status == 8 || dataPro.status == 0){
						//跳转至订单支付页面
						$myOrderItemClone.find('.singleInfoBox').attr('href','../cart/prepareForPay.do?id=' + dataPro.id);

						if(dataPro.payAmount!==null && dataPro.payAmount!=='' && dataPro.payAmount!==undefined) {

							var payItem = '';
							if(dataPro.amountList.length > 0){
								$.each(dataPro.amountList,function(iItem,dataItem){
									payItem = payItem + dataItem.feeName + '+';
								});
								payItem = payItem.substring(0,payItem.length-1);
							}else{
								payItem = '人工费';
							}

							$itemPayInfoClone.find('.orderStatusStr').text(payItem);
							$itemPayInfoClone.find('.orderPayMoney').addClass('red').text(dataPro.payAmount.toFixed(2));

							if(dataPro.status == 8 && dataPro.billType == 5){
								//跳转至确认完成页面
								$myOrderItemClone.find('.singleInfoBox').attr('href','qryDredgeBillDetail.do?id=' + dataPro.id + '&billType=' + dataPro.billType);
								$itemPayInfoClone.find('.btnSubmit').addClass('billComplete').attr('data-billId', dataPro.id).val('确认完成');
							}else {
								if(dataPro.billType == 5){
									$itemPayInfoClone.find('.btnSubmit').removeClass('btn-borderred').addClass("btn-bgred").val('确认支付');
								}else{
									$itemPayInfoClone.find('.btnSubmit').removeClass('btn-borderred').addClass("btn-bgred").val('确认并支付');
								}
							}
						}
					}else if (dataPro.status == 1) {
						if(dataPro.billType == 5){
							var payItem = '';
							$.each(dataPro.amountList,function(iItem,dataItem){
								payItem = payItem + dataItem.feeName + '+';
							});
							payItem = payItem.substring(0,payItem.length-1);

							$itemPayInfoClone.find('.orderStatusStr').text(payItem);
							if(dataPro.payAmount!==null && dataPro.payAmount!=='' && dataPro.payAmount!==undefined){
								$itemPayInfoClone.find('.orderPayMoney').addClass('red').text(dataPro.payAmount.toFixed(2));
							}else {
								$itemPayInfoClone.find('.orderPayMoney').text(dataPro.priceRange);
							}
                            $itemPayInfoClone.find('.check-btn').addClass('hide');
                            
						}else{
							if(dataPro.selfBuyAmount > 0){
								$itemPayInfoClone.find('.orderStatusStr').addClass('black').text('耗材费');
								$itemPayInfoClone.find('.orderPayMoney').text(dataPro.selfBuyAmount.toFixed(2));
							}else if(dataPro.selfBuyAmount === 0){
								$itemPayAmount.addClass('hide');
							}else {
								$itemPayInfoClone.find('.orderStatusStr').addClass('blue').text('预估费用');
								if(dataPro.discountPrice!==null && dataPro.discountPrice!=='' && dataPro.discountPrice!==undefined){
									$itemPayInfoClone.find('.orderPayMoney').text(dataPro.discountPrice.toFixed(2));
								}else {
									$itemPayInfoClone.find('.orderPayMoney').text(dataPro.priceRange);
								}
	                            $itemPayInfoClone.find('.material-item').addClass('yugutip w100');
								$itemPayInfoClone.find('.icon-yugu').removeClass('hide');
							}
							$itemPayInfoClone.find('.btnSubmit').addClass('hide');
						}
						
					}else if(dataPro.status == 2){
						var $itemListInfoClone = $itemListInfo.clone(true);

						//插入师傅信息
						$itemListInfoClone.find('.order-info-phone-call a').attr("href", 'tel:' + dataPro.repairPhone);
						$itemListInfoClone.find('.master-info-img img').attr("src", newImgUrl);
						$itemListInfoClone.find('.master-info-name').text(dataPro.repairName);
						$itemListInfoClone.find('.star-bgred').attr('data-width', dataPro.level);

						//将师傅信息插入列表
						$itemListInfoClone.removeClass('hide').insertAfter($myOrderItemClone.find('.order-pay-detail').eq(0));

						$myOrderItemClone.find('.order-title-buff').addClass("orange");
						$myOrderItemClone.find('.order-title-buff-icon').attr("src", "images/icon_master.png");

						if(dataPro.payAmount!==null && dataPro.payAmount!=='' && dataPro.payAmount!==undefined){
							var payItem = '';
							$.each(dataPro.amountList,function(iItem,dataItem){
								payItem = payItem + dataItem.feeName + '+';
							});
							payItem = payItem.substring(0,payItem.length-1);

							$itemPayInfoClone.find('.orderStatusStr').text(payItem);
							$itemPayInfoClone.find('.orderPayMoney').text(dataPro.payAmount.toFixed(2));
						}else {
							//不显示预估费用
							$itemPayAmount.addClass('hide');
						}
						$itemPayInfoClone.find('.btnSubmit').addClass('hide');
					}else if(dataPro.status == 4 || dataPro.status == 6 ){
						$itemPayAmount.addClass('hide');
					}else {
						//dataPro.status =3||dataPro.status =5
						if(dataPro.payAmount!==null && dataPro.payAmount!=='' && dataPro.payAmount!==undefined){
							if(dataPro.payAmount > 0){
								var payItem = '';
								$.each(dataPro.amountList,function(iItem,dataItem){
									payItem = payItem + dataItem.feeName + '+';
								});
								payItem = payItem.substring(0,payItem.length-1);

								$itemPayInfoClone.find('.orderStatusStr').text(payItem);
								$itemPayInfoClone.find('.orderPayMoney').text(dataPro.payAmount.toFixed(2));

								$itemPayInfoClone.find('.btnSubmit').addClass('hide');
							}else {
								$itemPayAmount.addClass('hide');
							}
						}else {
							$itemPayAmount.addClass('hide');
						}
					}

					//未处理订单，插入订单号
					if($orderTabTitleOn.attr('status') == 1){
						$myOrderItemClone.find('.item-list-cancel-btn-box').attr('cancel-order-id', dataPro.id);
					}

					//插入页面
					$myOrderItemClone.removeClass('hide').insertBefore($myOrderListBox.find('.loading'));

					//设置星级
					setMasterStars($myOrderItemClone.find('.star-bgred'), ($myOrderItemClone.find('.star-bgred').attr('data-width')/5).toFixed(2) * 100 + '\%');

				});
				
				//当前页数
				page += 1;
				$orderTabTitleOn.attr('data-page', page);
				//重设外层高度
				$("#tabBox .tempWrap").height($myOrderListBoxCur.height());
				
				//删除最后一行的底部线条
				$myOrderListBox.find('.my-order-item:last-child').find('.divide-box').removeClass('bordertbgrey').addClass('bordertopgrey');
			}

			//最后一页，设置data-next为false
			if(data.dataValue.hasNext === false){
				$orderTabTitleOn.attr('data-next', false)
				
				$("#tabBox .tempWrap").height($myOrderListBoxCur.height());
				$myOrderListBox.find('.loading').html('已更新至最后一页！');
			}
		},  
        error: function(){  
        	showMsg('网络不给力，请稍后重试'); 
        } 
	});
};

//条件判断+延迟执行，避免scroll事件频繁触发
var isScrolling = true;

function scrollNextPage(){
	
	isScrolling = true;
	
	if($('.order-tab-title').attr('data-next') === 'true'){
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
		var docHeight = $(document).height() - $(window).height()-1; //当前文档高度
		
		//页面加载完成时，不触发
		if(scrollt === 0){
			return false;
		}
		
		if(scrollt > docHeight){ 
		 	ajax("next",'.order-tab-title');
		}
	}
}

//初始化，所有tab加载第一页
$(function(){ 
	ajax("next",'#orderTab01');
	
	//滚动到底部，当前tab（有.on的tab）加载下一页
	$(window).scroll(function(event){
		if(isScrolling){ 
			setTimeout(scrollNextPage,1000); 
			isScrolling = false;
		}
	});
});
