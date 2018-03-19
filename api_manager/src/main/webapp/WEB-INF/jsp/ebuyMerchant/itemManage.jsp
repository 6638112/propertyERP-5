<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<title>商品管理</title>
<link rel="stylesheet" href="../css/merchant/shopping.common.css?v20170809">
</head>

<body>
<header class="sectionBox fantasia-header order-top-bg">
	<a class="disblock p010 right" href="toPage.html?page=itemSearch"><img class="search-icon" src="../images/merchant/icons-shop-search-small.png" /></a>
    <div class="header-title">商品管理</div>
</header>
<section id="tabBox" class="tabBox pos-relative">
    <div class="hd tab-head">
        <ul class="order-tab">
            <li id="orderTab01" class="wp30 tap-nobg item-tab-title on" data-page="1" data-next="true" status="0"><a href="javascript:void(0)">出售中</a></li>
            <li id="orderTab02" class="wp30 tap-nobg item-tab-title off" data-page="1" data-next="true" status="1"><a href="javascript:void(0)">仓库中</a></li>
            <li id="orderTab03" class="wp30 tap-nobg item-promotion-title" data-page="1" data-next="true" status="1"><a href="javascript:void(0)">促销中</a></li>
        </ul>
    </div>
    <section class="divide-box bordertbgrey"></section>
    <div class="bd my-order" id="tabBox-bd">
        <div class="con">
            <div id="tabOne">
                <div class="myItemList sectionBox bgwhite">
                   
                   <ul class="displaybox item-manage-sort f16 t-center">
						<li class="boxflex01">
                        	<div class="sort-name"><span class="sort-name-text">按类别</span> <img class="animated" src="../images/merchant/icons-shop-small-arrow.png" /></div>
                            <ul class="sort-select sort01 dsn" data-productTypeId="">
                            	<li class="on" data-productTypeId="">全部</li>
                            	<c:forEach var="prodType" items="${ptOnShelfList}" >
									<li data-productTypeId="${prodType.id}">${prodType.typeName}</li>
								</c:forEach>
                            </ul>
                        </li>
						<li class="boxflex01">
                        	<div class="sort-name"><span class="sort-name-text">排序</span> <img class="animated" src="../images/merchant/icons-shop-small-arrow.png" /></div>
                            <ul class="sort-select sort02 dsn" data-productTypeId="">
                            	<li class="on" data-orderBy="0">综合排序</li>
                            	<li data-orderBy="1">价格从低到高</li>
                            	<li data-orderBy="2">价格从高到低</li>
                            	<li data-orderBy="3">上架时间升序</li>
                            	<li data-orderBy="4">上架时间倒序</li>
                            	<li data-orderBy="5">销量从低到高</li>
                            	<li data-orderBy="6">销量从高到低</li>
                            </ul>
                        </li>
                   </ul>
                   
                   <div class="item-list-box bordertopgrey">
                   
                   </div>
                   
                   <a class="displaybox item-list-info borderbottomgrey boxalign-start hide" href="#">
                        <div class="item-manage-list-img mright10"><img class="scrollLoading" data-url="../images/merchant/jfq-logo.png" style="background:url(../images/merchant/loading01.gif) no-repeat center; background-size:26px;" src="../images/merchant/pixel.gif" /></div>
                        <div class="item-list-title boxflex01 f16 mright15"><span class="word-break my-item-text">花生油 农家自炸非转基因食用新鲜花生油 农家自炸非转基因食用新鲜花生油</span>
                            <div class="f18 bold mtop10">￥<span class="my-item-price">349.00</span></div>
                            <div class="grey mtop10 mbottom10">库存<span class="my-item-leftcount">100</span><span class="my-item-sellcount mleft15"></span></div>
                            <div class="my-item-act" style="float:right">
                            	<span class="my-item-stick">
                           			<img src="../images/icon_product_stick.png" style="display:inline-block;width:12px;vertical-align: baseline;"><span class="f14" style="padding-left:4px;">排序置顶</span>
                           		</span>
                           		<span class="my-item-recomend mleft15" >
                           			<img src="../images/icon_recomend_no.png" style="display:inline-block;width:12px;vertical-align: baseline;"><span class="f14" style="padding-left:4px;">爆款推荐</span>
                           		</span>
                            </div>
                            <span class="mtop10 my-item-status">待审核</span>
                        </div>
                   </a>
                </div>
            </div>
        </div>
        <div class="con">
            <div id="tabTwo">
                <div class="myItemList sectionBox bgwhite">
                   
                   <ul class="displaybox item-manage-sort f16 t-center pos-relative">
						<li class="boxflex01">
                        	<div class="sort-name"><span class="sort-name-text">按类别</span> <img class="animated" src="../images/merchant/icons-shop-small-arrow.png" /></div>
                            <ul class="sort-select sort01 dsn">
                            	<li class="on" data-productTypeId="">全部</li>
                            	<c:forEach var="prodType" items="${ptOffShelfList}" >
									<li data-productTypeId="${prodType.id}">${prodType.typeName}</li>
								</c:forEach>
                            </ul>
                        </li>
						<li class="boxflex01">
                        	<div class="sort-name"><span class="sort-name-text">排序</span> <img class="animated" src="../images/merchant/icons-shop-small-arrow.png" /></div>
                            <ul class="sort-select sort02 dsn">
                            	<li class="on" data-orderBy="0">综合排序</li>
                                <%--<li data-orderBy="3">按排序从高到低</li>--%>
                                <%--<li data-orderBy="4">按排序从低到高</li>--%>
                            	<li data-orderBy="1">价格从低到高</li>
                            	<li data-orderBy="2">价格从高到低</li>
                            </ul>
                        </li>
                   </ul>
                   
                   <div class="item-list-box bordertopgrey">
                   </div>
                   
                </div>
            </div>
        </div>
        <div class="con">
            <div id="tabThree">
                <div class="myItemList sectionBox bgwhite">
                   <div class="item-list-box bordertopgrey">
                   </div>
                   
                </div>
            </div>
        </div>
    </div>
    <ul class="bottom-menu-box displaybox t-center bordertopgrey">
    	<li><a href="myOrder.html"><span class="menu-icon01"></span>我的订单</a></li>
    	<li class="on"><a href="itemManage.html"><span class="menu-icon02"></span>商品管理</a></li>
    	<!--<li><a href="#"><span class="menu-icon03"></span>账务中心</a></li>-->
    	<li><a href="setting.html"><span class="menu-icon04"></span>店铺设置</a></li>
		<li class="boxflex01"><a href="settleCenter.html"><span class="menu-icon03"></span>结算中心</a></li>
    </ul>
    <div class="item-add-box"><a href="saveProductPage.html"><div class="item-add-btn displaybox"><img src="../images/merchant/add-item-icon.png" /></div></a></div>
</section>

<div class="sectionBox loading grey hide"><img src="../images/merchant/loading01.gif" /> 加载中…</div>
<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	});
</script>
<script src="../js/merchant/TouchSlide.1.1.js"></script>
<script src="../js/merchant/jquery.scrollLoading.js"></script>
<script src="../js/Validform_v5.3.2.js"></script>
<script>	
$(function(){

	TouchSlide({ slideCell:"#tabBox",
		startFun:function(){
			return false;
		},
		endFun:function(i){ //高度自适应
			var itemLength = $('.con').eq(i).find('.item-list-info:visible').length;
			
			if(itemLength === 0){
				if($('.item-promotion-title').hasClass('on')){
					getPromotionList('.item-promotion-title.on');
				}else{
				 	ajax('.item-tab-title.on');
				}
			}
			
			var bd = document.getElementById("tabBox-bd");
			bd.parentNode.style.height = bd.children[i].children[0].offsetHeight + 56 + "px";
			if(i>0)bd.parentNode.style.transition="200ms";//添加动画效果
		}
	});
	
	var num = 10;//每页显示的个数
	var $loading = $('.loading');
	//分页加载订单
	function ajax(tabTitle){
		if($(tabTitle).length === 0){
			return;
		}

		var $orderTabTitleOn = $(tabTitle);
		//listIndexOn等于0为待处理状态，listIndexOn等于1为已处理状态
		var listIndexOn = $orderTabTitleOn.index();
		//滚动时，当前tab加载下一页，status为当前状态的数据，加载到这里
		var page = $orderTabTitleOn.attr('data-page')*1;
		var $myListBox = $('.con').eq(listIndexOn).find('.item-list-box');
		var $itemListInfo = $('.item-list-info.hide');
		var $noItemTips = $myListBox.find('.noItemTips');
		
		$.ajax({
			type:"get",
			url:"qryProductList.html",
			data:{page:$(tabTitle).attr('data-page'), pageNum:num, status:$(tabTitle).attr('status'), productTypeId:$('.con').eq($(tabTitle).index()).find('.sort-select.sort01').attr('data-productTypeId'), orderBy : $('.con').eq($(tabTitle).index()).find('.sort-select.sort02').attr('data-orderBy')},
			dataType:"json",
			beforeSend:function(data){
				if($('.con').eq($(tabTitle).index()).find('.noItemTips').length > 0){
					return false;
				}
				
				var curLoadingLength = $('.con').eq($(tabTitle).index()).find('.item-list-box').find('.newLoading').length;
				if(curLoadingLength == 0){
					$loading.clone(true).addClass('newLoading').removeClass('hide').appendTo($('.con').eq($(tabTitle).index()).find('.item-list-box'));
				}

				//将hasNext转成boolean值才可比对
				var hasNext = $(tabTitle).attr('data-next');
				
				if(hasNext === 'false'){
					$('.newLoading').html('已更新至最后一页！');
					$("#tabBox .tempWrap").height($('.con').eq($(tabTitle).index()).find('.item-list-box').height() + 107);
					return false;
				}
			},
			success:function(data){
				
				if($noItemTips){
					$noItemTips.remove();
				}

				if(data.dataValue.list == ''){
					var noItemTips = '<div class="list-box t-center noItemTips">该分类下暂无商品，您可以添加商品！</div>';
					$myListBox.html(noItemTips);
				}else{
					$.each(data.dataValue.list,function(iPro,dataPro){
						
						var $itemListInfoClone = $itemListInfo.clone(true);
						var imgUrl = dataPro.picBase;
						if(imgUrl != null){
							imgUrl = imgUrl + (imgUrl.indexOf('?') > '-1' ? '&' : '?') + 'x-oss-process=image/resize,m_fill,limit_0,w_196,h_165/format,jpg/interlace,1/quality,q_80';
							//将订单数据更新至$itemListInfoClone 
							$itemListInfoClone.find('.item-manage-list-img img').attr('data-url', imgUrl);
						}
						$itemListInfoClone.find('.my-item-text').text(dataPro.prodName);
						$itemListInfoClone.find('.my-item-price').text(dataPro.price.toFixed(2));
						$itemListInfoClone.find('.my-item-leftcount').text(dataPro.leftCount);
						if($(tabTitle).attr('status') == 0) {//出售中
							$itemListInfoClone.find('.my-item-status').remove();
							$itemListInfoClone.find('.my-item-sellcount').text('销量' + dataPro.selNum);
							if(dataPro.isHotSale){
								$itemListInfoClone.find('.my-item-recomend img').attr("src", "../images/icon_recomend_yes.png");
								$itemListInfoClone.attr("isRecomend", "1");
							}else{
								$itemListInfoClone.find('.my-item-recomend  img').attr("src", "../images/icon_recomend_no.png");
								$itemListInfoClone.attr("isRecomend", "0");
							}
							
						} else {//仓库中
							$itemListInfoClone.find('.my-item-sellcount').remove();
							$itemListInfoClone.find('.my-item-act').addClass('hide');
							if(dataPro.statusAudit == 2) {
								$itemListInfoClone.find('.my-item-status').addClass('item-status bgdarkgrey').text('未提交出售');
							} else if(dataPro.statusAudit == 3) {
								$itemListInfoClone.find('.my-item-status').addClass('item-status bggreen').text('待审核');
							} else if(dataPro.statusAudit == 4) {
								$itemListInfoClone.find('.my-item-status').addClass('item-status bgdarkgrey').text('审核不通过');
							} else if(dataPro.statusAudit == 5) {
								$itemListInfoClone.find('.my-item-status').addClass('item-status bggreen').text('审核通过-下架');
							}
						}
						
						$itemListInfoClone.attr("prdtId", dataPro.id);
						$itemListInfoClone.attr('href', 'saveProductPage.html?prodId=' + dataPro.id);
	
						//插入页面
						$itemListInfoClone.removeClass('hide').appendTo($myListBox);	

					});
					//当前页数
					page += 1;
					$orderTabTitleOn.attr('data-page', page);		
		
					$(".scrollLoading").scrollLoading();
					//重设外层高度
					$("#tabBox .tempWrap").height($myListBox.height() + 185);
				}
				
				setTimeout(function(){
					$('.newLoading').remove();
				},400);
				//最后一页，设置data-next为false
				if(data.dataValue.hasNext === false){
					$orderTabTitleOn.attr('data-next', false);
				}
				
			},  
            error: function(){  
            	$.Showmsg('网络不给力，请稍后重试'); 
            } 
		});
	};
	
	//获取促销中商品列表
	function getPromotionList(tabTitle){
		if($(tabTitle).length === 0){
			return;
		}
		$.ajax({
			type:"get",
			url:"../ebuyMerchant/activity/limitBuyList.json",
			data:{page:$(tabTitle).attr('data-page'), pageNum:num },
			dataType:"json",
			beforeSend:function(data){
				
				if($('#tabThree').find('.noItemTips').length > 0){
					return false;
				}
				
				var curLoadingLength = $('#tabThree').find('.item-list-box').find('.newLoading').length;
				if(curLoadingLength == 0){
					$loading.clone(true).addClass('newLoading').removeClass('hide').appendTo($('#tabThree').find('.item-list-box'));
				}

				//将hasNext转成boolean值才可比对
				var hasNext = $(tabTitle).attr('data-next');

				if(hasNext === 'false'){
					$('.newLoading').html('已更新至最后一页！');
					$("#tabBox .tempWrap").height($('#tabThree').find('.item-list-box').height() + 57);
					return false;
				}
			},
			success:function(data){

				var $orderTabTitleOn = $(tabTitle);
				
				//滚动时，当前tab加载下一页，status为当前状态的数据，加载到这里
				var page = $orderTabTitleOn.attr('data-page')*1;
				var $myListBox = $('#tabThree').find('.item-list-box');
				var $itemListInfo = $('.item-list-info.hide');
				
				var $noItemTips = $myListBox.find('.noItemTips');
				if($noItemTips){
					$noItemTips.remove();
				}

				if(data.dataValue.list == '' && $myListBox.find('.item-list-info').length === 0){
					var noItemTips = '<div class="list-box t-center noItemTips">该分类下暂无商品！</div>';
					$myListBox.html(noItemTips);
				}else{
					$.each(data.dataValue.list,function(iPro,dataPro){
						
						var $itemListInfoClone = $itemListInfo.clone(true);
						var imgUrl = dataPro.limitBuyPic;
						if(imgUrl != null){
							imgUrl = imgUrl + (imgUrl.indexOf('?') > '-1' ? '&' : '?') + 'x-oss-process=image/resize,m_fill,limit_0,w_196,h_165/format,jpg/interlace,1/quality,q_80';
							//将订单数据更新至$itemListInfoClone 
							$itemListInfoClone.find('.item-manage-list-img img').attr('data-url', imgUrl);
						}
						$itemListInfoClone.find('.my-item-text').text(dataPro.limitBuyTitle);
						$itemListInfoClone.find('.my-item-price').text(dataPro.limitBuyPrice.toFixed(2));
						$itemListInfoClone.find('.my-item-leftcount').text(dataPro.leftCount);
						$itemListInfoClone.find('.my-item-sellcount').remove();
						$itemListInfoClone.find('.my-item-act').addClass('hide');
						if(dataPro.status == 1) {
							$itemListInfoClone.find('.my-item-status').addClass('item-status bggreen').text('促销进行中');
						} else if(dataPro.status == 0) {
							$itemListInfoClone.find('.my-item-status').addClass('item-status bgdarkgrey').text('促销未开始');
						}
						
						$itemListInfoClone.attr('href', 'activity/editLimitBuy.html?limitBuyId=' + dataPro.limitBuyId);
	
						//插入页面
						$itemListInfoClone.removeClass('hide').appendTo($myListBox);	

					});
					//当前页数
					page += 1;
					$orderTabTitleOn.attr('data-page', page);		
		
					$(".scrollLoading").scrollLoading();
					//重设外层高度
					$("#tabBox .tempWrap").height($myListBox.height() + 185);
				}
				
				setTimeout(function(){
					$('.newLoading').remove();
				},400);
				//最后一页，设置data-next为false
				if(data.dataValue.list.length < 10){
					$orderTabTitleOn.attr('data-next', false);
				}
				
			},  
            error: function(){  
            	$.Showmsg('网络不给力，请稍后重试'); 
            } 
		});
	};
	
	//点击切换分类、排序
	$('.sort-select li').click(function(){
		//获取分类id
		if($(this).parents('.sort-select').hasClass('sort01')){
			var thisProductTypeId = $(this).attr('data-productTypeId');
			$(this).parents('.sort-select').attr('data-productTypeId', thisProductTypeId);
		}else{
			var thisOrderbyId = $(this).attr('data-orderby');
			$(this).parents('.sort-select').attr('data-orderby', thisOrderbyId);
		}
		$('.item-tab-title.on').attr({'data-page':'1', 'data-next':'true'});
		$(this).parents('.item-manage-sort').next('.item-list-box').html('');
		ajax('.item-tab-title.on');
	});
	
	$(document).on('click', '.item-list-info', function(event){
		//排序置顶
		if(event.target.parentNode.className === 'my-item-stick'){
			event.preventDefault();
			var that = $(this);
			var productId = that.attr("prdtId");
			$.ajax({
				type:"post",
				url:"../ebuyMerchant/upToTop.json",
				data:{prdtId: productId},
				dataType:"json",
				success:function(data){
					that.prependTo('.item-list-box');
				},  
	            error: function(){  
	            	$.Showmsg('网络不给力，请稍后重试'); 
	            } 
			});
		}
		//爆款推荐
		if(event.target.parentNode.className === 'my-item-recomend mleft15'){
			event.preventDefault();
			var that = $(this);
			var productId = that.attr("prdtId");
			var isRecomend = that.attr("isRecomend");
			var targetValue = (isRecomend==1?0:1);
			$.ajax({
				type:"post",
				url:"../ebuyMerchant/resetHotSale.json",
				data:{prdtId: productId, isHotSale: targetValue},
				dataType:"json",
				success:function(data){
					if(targetValue == 1){
						that.attr("isRecomend", "1");
						that.find('.my-item-recomend img').attr("src", "../images/icon_recomend_yes.png");
					}else{
						that.attr("isRecomend", "0");
						that.find('.my-item-recomend img').attr("src", "../images/icon_recomend_no.png");
					}
				},  
	            error: function(){  
	            	$.Showmsg('网络不给力，请稍后重试'); 
	            } 
			});
		}
	});

	//条件判断+延迟执行，避免scroll事件频繁触发
	var isScrolling = true;
	
	//滚动到底部，当前tab（有.on的tab）加载下一页
	$(window).scroll(function(event){
		if(isScrolling){ 
			setTimeout(scrollNextPage,1000); 
			isScrolling = false;
		}
	});
	
	function scrollNextPage(){
		
		isScrolling = true;
		
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
		var docHeight = $(document).height() - $(window).height()-1; //当前文档高度
		
		//页面加载完成时，不触发
		if(scrollt === 0){
			return false;
		}
		
		if(scrollt > docHeight){ 
			if($('.item-promotion-title').hasClass('on')){
				getPromotionList('.item-promotion-title.on');
			}else{
			 	ajax('.item-tab-title.on');
			}
		}
	}

});
</script>
<script src="../js/merchant/shopping.common.js?v20151027"></script>
</body>
</html>