//购物车动画
$(function(){
	$(".scrollLoading").scrollLoading({
		container: $(window)
	});
	
	var $shoppingCart = $('.shopping-cart-index');
	var $itemNum = $('.item-num');
	var itemNumText = $itemNum.html()*1;

	jQuery.get("../cart/qryBuyCarProductCount.do", function(data,status){
		if(typeof data === "object"){
			$itemNum.html(data.dataValue.productCount);
		}
	});
	
	$('.join-btn').click(function(event){
		var leftCount = $.trim($(this).attr("leftCount"));
		if(leftCount<=0||leftCount==""){
			alert("该商品库存不足");
			return;
		}
		var $itemListImg = $(this).parents('.item-list-small').find('.item-list-img');
		var $itemListImgPic = $itemListImg.find('img').clone().css({width:'40px', height:'40px'});
		var itemListImgPicWidth = $itemListImgPic.width();
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop;
		
		var offset = $('.shopping-cart-index').offset();
		var $flyer = $itemListImgPic;

		//加入购物车
		 var prdtId = $(this).parents('.item-list-small').find('a').attr("prdtId");
		 jQuery.post("../cart/add2BuyCar.do", "ptId=" + prdtId + "&productQty="+ 1).success(function(response){
			 if(typeof response === "object"){
				 if(response.status != '0000') {
					 if(response.message.indexOf("库存不足")){
						 alert("该商品库存不足");
					 }else {
						 alert(response.message);
					 }
					 return;
				 }
			 }
			 
			 var curCount = +$itemNum.text();
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
		
	});

});

//关注公众号
$(window).scroll(function(){
	 var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
	 var docHeight = $(document).height() - $(window).height(); //当前文档高度
	 if(scrollt == docHeight){
	 	$('.jfq-btn').stop(true,false).fadeOut();
	 }else if(scrollt > docHeight-300){
		$('.jfq-btn').stop(true,false).fadeIn();	 
	 }
});