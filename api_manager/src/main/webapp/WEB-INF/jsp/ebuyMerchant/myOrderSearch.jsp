<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>订单搜索</title>
<link rel="stylesheet" href="../css/merchant/animate.css">
<link rel="stylesheet" href="../css/merchant/shopping.common.css?20151028" type="text/css">
</head>

<body>
<header class="sectionBox fantasia-header order-top-bg">
	<a class="disblock mleft15 left" href="javascript:history.back();"><img class="back-arrow" src="../images/merchant/back-arrow.png" /></a>
    <div class="header-title mtop5 displaybox"><input class="order-search boxflex01" type="text" name="search" placeholder="手机号/解放号/订单号" /><div class="quick-delete hide"></div></div>
	<a id="orderSearch" class="disblock p010 right white" href="javascript:void(0)">搜索</a>
</header>

<section class="divide-box bordertbgrey"></section>
<section class="sectionBox">
    <div class="myOrderList bgwhite">
       
    </div>
    <div class="my-order-item hide">
           <div class="displaybox order-title-info">
                <%--<div class="order-title-num">02</div> --%>
                <div class="order-title-time boxflex01 grey mleft15"><span class="icon-orderNo-text">2015080111112220</span><br><span class="icon-time-text">2分钟前</span></div>
                <div class="order-title-buff mright15 orange"><img class="order-title-buff-icon" src="../images/merchant/icons-shop-clock.png" /> <span class="order-title-buff-text">等待处理</span></div>
           </div>
		   <div class="displaybox ptb10 bordertopgrey">
		        <div class="boxflex01 f14 pleft20 lineheight140">解放号：<span class="app-number">50036</span><br>手机号：<span class="phone-number">18966663333</span></div>
		   </div>
           <div class="displaybox order-info-box bordertopgrey">
                <ul class="order-info-address boxflex01">
                    <li class="icon-address"><span class="icon-address-text">星海名城1期A栋5008室</span><br><%--<span class="orange f12">距您 1.2km</span> --%></li>
                    <li class="icon-person">熊猫爸爸</li>
                </ul>
                <div class="order-info-phone-call"><a href="tel:400-960-2228"><img src="../images/merchant/icon-phone-call.png" /></a></div>
           </div>
           
           <div class="item-list-box bggrey bordertopgrey">
	           <div class="displaybox item-list-info borderbottomgrey hide">
				    <div class="item-list-img mright10"><img class="single-item-image" src="../images/merchant/good-small-img001.jpg" /></div>
				    <div class="item-list-title boxflex01 word-break f16">花生油 农家自炸非转基因食用新鲜花生油 农家自炸非转基因食用新鲜花生油</div>
				    <div class="item-list-price mright15"><span class="f14">￥<span class="single-item-price">349.00</span></span><br/><span class="grey">x<span class="single-item-num">2</span></span></div>
				</div>
                <ul class="item-price f14 borderbottomgrey">
                    <li class="left">共 <span class="total-item-num">2</span> 件商品</li>
                    <li class="right"><span>合计: ¥<span class="total-item-price">30.5</span>(含运费: ¥<span class="deliv-item-price">5.0</span>)</span></li>
                </ul>
                <div class="item-list-check-btn-box bgwhite displaybox"><input class="item-list-check-btn bgred boxflex01" type="button" value="确认发货" /></div>
           </div>
           <%--<div class="item-list-arrow-box bordertbgrey"><div class="item-list-arrow animated rotateIn"></div></div>--%>
           <section class="divide-box bordertbgrey"></section>
	</div>
</section>

<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	})
</script>
<script src="../js/merchant/Validform_v5.3.2.js"></script>
<script type="text/javascript">
	$(function(){
		
		var $orderSearch = $('.order-search');
		var $orderSearchBtn = $('#orderSearch');
		var $quickDelete = $('.quick-delete');
		
		$orderSearchBtn.on('click', function(event){
			currentVal = $.trim($orderSearch.val());
			
			if(!currentVal == ''){
				//开始搜索
				$('.my-order-item:visible').remove();
				ajaxSearch();
			}
		});
		$orderSearch.on('keyup', function(event){
			var myEvent = event || window.event;
			currentVal = $.trim($orderSearch.val());
			
			if(myEvent.keyCode == 13 && !currentVal == ''){
				//开始搜索
				$('.my-order-item:visible').remove();
				ajaxSearch();
			}
		});
		$orderSearch.focus(function(){
			$quickDelete.removeClass('hide');
		});
		$orderSearch.blur(function(){
			currentVal = $.trim($orderSearch.val());
			if(currentVal == ''){
				$quickDelete.addClass('hide');
			}
		});	
		$quickDelete.click(function(){
			$orderSearch.val('');
			$quickDelete.addClass('hide');
		});
		
		//查询订单列表
		function ajaxSearch(){
			$.ajax({
				type:"get",
				url:"getOrderList.html",
				data:{page : 1, pageNum : 20, searchKey : $orderSearch.val()},
				dataType:"json",
				success:function(data){
					
					var searchNum = 0;
					var $myOrderListBox = $('.myOrderList');
					var $myOrderItem = $('.my-order-item.hide');
					var $itemListInfo = $('.item-list-info.hide');
					
					$.each(data.dataValue.list, function(i, dataPro){	
						
						var $myOrderItemClone = $myOrderItem.clone(true);
						//将订单数据更新至$myOrderItemClone
						$myOrderItemClone.find('.icon-address-text').text(dataPro.deliverAddr);
						$myOrderItemClone.find('.icon-person').text(dataPro.receivePeople == null ? "" : dataPro.receivePeople);
						$myOrderItemClone.find('.icon-orderNo-text').text(dataPro.orderNo);
						$myOrderItemClone.find('.icon-time-text').text(dataPro.payTimeStr);
						$myOrderItemClone.find('.icon-time-text').text(dataPro.payTimeStr);
						$myOrderItemClone.find('.total-item-num').text(dataPro.totalQty);
						$myOrderItemClone.find('.total-item-price').text((dataPro.tatalPrice + dataPro.deliverMoney).toFixed(2));
						$myOrderItemClone.find('.deliv-item-price').text(dataPro.deliverMoney);
						$myOrderItemClone.find('.icon-tel-text').attr("href", 'tel:' + dataPro.phone);
						$myOrderItemClone.find('span.phone-number').text(dataPro.phone);
						$myOrderItemClone.find('span.app-number').text(dataPro.huaId);
						$myOrderItemClone.find('.item-list-check-btn-box').attr('deli-order-id', dataPro.id);
						$myOrderItemClone.find('.item-list-check-btn-box').attr('order-id', dataPro.orderId);
						console.log('status:', dataPro.status);

						
						if(dataPro.status == 1) {
							$myOrderItemClone.find('.order-title-buff-text').text("待处理");
							$myOrderItemClone.find('.order-title-buff-icon').attr("src", "../images/merchant/icons-shop-clock.png");
						} else if(dataPro.status == 2 ) {
							$myOrderItemClone.find('.item-price').removeClass('borderbottomgrey');
							$myOrderItemClone.find('.order-title-info').addClass("border-left-green");
							$myOrderItemClone.find('.order-title-buff').addClass("green");
							$myOrderItemClone.find('.order-title-buff-text').text("已发货");
							$myOrderItemClone.find('.order-title-buff-icon').attr("src", "../images/merchant/icons-shop-bag.png");
							$myOrderItemClone.find('.item-list-check-btn-box').remove();
						} else if(dataPro.status == 3) {
							$myOrderItemClone.find('.item-price').removeClass('borderbottomgrey');
							$myOrderItemClone.find('.order-title-info').addClass("border-left-green");
							$myOrderItemClone.find('.order-title-buff').addClass("green");
							$myOrderItemClone.find('.order-title-buff-text').text("已完成");
							$myOrderItemClone.find('.order-title-buff-icon').attr("src", "../images/merchant/icons-shop-checkdone.png");
							$myOrderItemClone.find('.item-list-check-btn-box').remove();
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
							searchNum += 1;
							
						});
						//插入页面
						$('.noContent').remove();
						//$myOrderItemClone.removeClass('hide').appendTo($myOrderListBox);
						$myOrderListBox.append($myOrderItemClone.removeClass('hide'));	


					});

					if(searchNum == 0){
						noOrderList = '<li class="t-center list-box noContent">' + '没有找到相关内容' + '</li>';
						//插入提示信息
						$myOrderListBox.html(noOrderList);	
					}
				},  
                error: function(){  
                	$.Showmsg('网络不给力，请稍后重试'); 
                } 
			});
		}
		
		
	});
	
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
			success:function(data){
				try {
					if (data.status == '0000') {
						$.Showmsg('确认发货成功！');

						$curMyOrderItem.find('.item-list-check-btn-box').remove();

						$curMyOrderItem.find('.item-price').removeClass('borderbottomgrey');
						$curMyOrderItem.find('.order-title-info').addClass('border-left-green');
						$curMyOrderItem.find('.order-title-buff').removeClass('orange').addClass('green');
						$curMyOrderItem.find('.order-title-buff-icon').attr('src', '../images/merchant/icons-shop-bag.png');
						$curMyOrderItem.find('.order-title-buff-text').text('已发货');

						//重设外层高度
						setTimeout(function(){
							$("#tabBox .tempWrap").height($curOrderListBox.height() + 58);
						},202);
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
</script>
<script src="../js/merchant/shopping.common.js"></script>

</body>
</html>