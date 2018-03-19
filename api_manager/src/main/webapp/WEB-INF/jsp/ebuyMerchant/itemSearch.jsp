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

<title>商品搜索</title>
<link rel="stylesheet" href="../css/merchant/shopping.common.css" type="text/css">
</head>

<body>
<header class="sectionBox fantasia-header order-top-bg">
	<a class="disblock mleft15 left" href="javascript:history.back();"><img class="back-arrow" src="../images/merchant/back-arrow.png" /></a>
    <div class="header-title mtop5 displaybox"><input class="order-search boxflex01" type="text" name="search" /><div class="quick-delete hide"></div></div>
	<a id="orderSearch" class="disblock p010 right white" href="javascript:void(0)">搜索</a>
</header>

<section class="divide-box bordertbgrey"></section>
<section class="sectionBox">
    <div class="myOrderList bgwhite">
       
    </div>
    
    <a class="displaybox item-list-info borderbottomgrey boxalign-start hide">
         <div class="item-manage-list-img mright10"><img class="scrollLoading" data-url="../images/merchant/jfq-logo.png" style="background:url(../images/merchant/loading01.gif) no-repeat center; background-size:26px;" src="../images/merchant/pixel.gif" /></div>
         <div class="item-list-title boxflex01 f16 mright15"><span class="word-break my-item-text">花生油 农家自炸非转基因食用新鲜花生油 农家自炸非转基因食用新鲜花生油</span>
             <div class="f18 bold mtop10">￥<span class="my-item-price">349.00</span></div>
             <div class="grey mtop10 mbottom10">库存<span class="my-item-leftcount">100</span></div>
             <span class="mtop10 my-item-status">待审核</span>
         </div>
    </a>
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
				$('.item-list-info:visible').remove();
				ajaxSearch();
			}
		});
		$orderSearch.on('keyup', function(event){
			var myEvent = event || window.event;
			currentVal = $.trim($orderSearch.val());
			
			if(myEvent.keyCode == 13 && !currentVal == ''){
				//开始搜索
				$('.item-list-info:visible').remove();
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
				url:"qryProductList.html",
				data:{page : 1, pageNum : 20, searchKey : $orderSearch.val()},
				dataType:"json",
				success:function(data){
					
					var searchNum = 0;
					var $myOrderListBox = $('.myOrderList');
					var $itemListInfo = $('.item-list-info.hide');
					
					$.each(data.dataValue.list, function(i, dataPro){	
						
						var $itemListInfoClone = $itemListInfo.clone(true);
						//将订单数据更新至$itemListInfoClone
						$itemListInfoClone.find('.item-manage-list-img img').attr('src', dataPro.picBase);
						$itemListInfoClone.find('.my-item-text').text(dataPro.prodName);
						$itemListInfoClone.find('.my-item-price').text(dataPro.price.toFixed(2));
						$itemListInfoClone.find('.my-item-leftcount').text(dataPro.leftCount);
						if(dataPro.statusAudit == 2) {
							$itemListInfoClone.find('.my-item-status').addClass('item-status bgdarkgrey').text('未提交出售');
						} else if(dataPro.statusAudit == 3) {
							$itemListInfoClone.find('.my-item-status').addClass('item-status bggreen').text('待审核');
						} else if(dataPro.statusAudit == 4) {
							$itemListInfoClone.find('.my-item-status').addClass('item-status bgdarkgrey').text('审核不通过');
						} else if(dataPro.statusAudit == 5) {
							if(dataPro.status == 0) {
								$itemListInfoClone.find('.my-item-status').remove();
							} else {
								$itemListInfoClone.find('.my-item-status').addClass('item-status bggreen').text('审核通过-下架');
							}
						}
						$itemListInfoClone.attr('href', 'saveProductPage.html?prodId=' + dataPro.id);

						//插入页面
						$('.noContent').remove();
						//$itemListInfoClone.removeClass('hide').appendTo($myOrderListBox);	
						$myOrderListBox.append($itemListInfoClone.removeClass('hide'));
						searchNum += 1;
					});
					
					setItemHeight();	

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
		

		//文档加载完成后设置商品图片高度和行高
		function setItemHeight() {
			var $itemListImgBox = $(".myOrderList .item-manage-list-img");
			var itemListImgBoxWidth = $itemListImgBox.width();
			var itemListImgBoxHeight = Math.round(itemListImgBoxWidth*170/200);
			$itemListImgBox.css({'height':itemListImgBoxHeight + 'px', 'line-height':itemListImgBoxHeight + 'px'});
			
			//判断图片是否加载完成
			var t_img; // 定时器
			var isLoad = true; // 控制变量
			// 判断图片加载状况，加载完成后回调
			isImgLoad(function(){
				$itemListImgBox.each(function(){
					var $itemListPic = $(this).children('img');
					var itemListPicSrc = $itemListPic.attr('src');

					var itemListPicWidth = $itemListPic.width();
					var itemListPicHeight = $itemListPic.height();

					if(itemListPicHeight < itemListImgBoxHeight){
						//商品图片高度小于外容器高度，设置商品图片与外容器一样高，并水平居中
						$itemListPic.height(itemListImgBoxHeight);
						$itemListPic.width(itemListImgBoxHeight*itemListPicWidth/itemListPicHeight);
						$itemListPic.css({'margin-left': - ( Math.round( itemListImgBoxHeight*itemListPicWidth/itemListPicHeight ) - Math.round(itemListImgBoxWidth) )/2 + 'px'});
					}else if(itemListPicHeight > itemListImgBoxHeight && itemListPicSrc !== 'images/pixel.gif'){
						//商品图片宽度小于外容器宽度，设置商品图片与外容器一样宽，并垂直居中
						$itemListPic.css({'margin-top': ( Math.round(itemListImgBoxHeight) - Math.round(itemListPicHeight) )/2 + 'px'});
					}

				});
			});
			// 判断图片加载的函数
			function isImgLoad(callbackFun){
				// 查找所有封面图，迭代处理
				$itemListImgBox.each(function(){
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
					},500); // 我这里设置的是500毫秒就扫描一次，可以自己调整
				}
			}
		};
		
	});
</script>
<script src="../js/merchant/shopping.common.js"></script>

</body>
</html>