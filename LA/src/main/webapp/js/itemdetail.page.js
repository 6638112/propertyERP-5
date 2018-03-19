//购物车动画
$(function(){
	new FastClick(document.body);
	
	var $shoppingCart = $('.shopping-cart');
	var $itemNum = $('#cartNum');
	var itemNumText = $itemNum.text()*1; //购物车数量
	
	if(itemNumText*1 > 99){
		$itemNum.addClass('padding05');
	}
	$('#btnCart').click(function(event){
		var productQty = $(".itemNum").val();
		var itemNumSelect = $('.number .itemNum').val(); //选择商品数量
		var $itemListImg = $('.itemDetails-img ul li').eq(0).find('img');
		var $itemListImgPic = $itemListImg.clone().css({width:'40px', height:'40px'});
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop;
		
		var offset = $shoppingCart.offset();
		var $flyer = $itemListImgPic;
		

		$.post("../cart/add2BuyCar.do", "ptId=" + $('#slideBox').attr('data-id') + "&productQty="+ productQty).success(function(response){
			 if(typeof response === "object"){
				 if(response.status != '0000') {
					 if(response.message.indexOf("库存不足") > -1){
						 showMsg("该商品库存不足");
					 }else {
						 showMsg(response.message);
					 }
					 return;
				 }
			 }
			
			$flyer.addClass('item-flyer');
			$flyer.fly({
				start: {
					left: event.pageX - 50,
					top: event.pageY - scrollt -60
				},
				end: {
					left: offset.left + 30,
					top: offset.top - scrollt + 20,
					width: 0,
					height: 0
				}
			});
			setTimeout(function(){
				$flyer.remove();
			},1000);
			
			itemNumText += itemNumSelect*1;
			
			if(itemNumText*1 > 99){
				$itemNum.addClass('padding05');
			}
			
			$itemNum.text(itemNumText).addClass('animated pulse');
			setTimeout(function(){
				$itemNum.removeClass('animated pulse');
			}, 500);
		
		});
		//ajaxPost("../cart/add2BuyCar.do", "ptId=" + "${product.id }" + "&productQty="+ productQty +"&specId="+specId);
	});	
	
	//设置页面为只读状态
	if(location.search.indexOf('readonly') > -1){
		$('.readonly-box').remove();
	}
	
	tabSwap('.tab-head li', '#tabBox-bd .con');
	
    TouchSlide({ 
        slideCell:"#slideBox",
        titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
        mainCell:".bd ul", 
        effect:"left", 
        autoPage:true,//自动分页
        autoPlay:false //自动播放
    });
    
	var $li = $('#slideBox .hd ul li');
	if($li.length == 1){
		$li.hide();
	}
	
	//立即抢购
	$('#buyNowBtn').click(function(event){
		
		var e = event || window.event;
		var curLink = $(this).attr('href');
		var productQty = $(".itemNum").val();
		
		e.preventDefault();
		$.post("../cart/add2BuyCar.do", "ptId=" + $('#slideBox').attr('data-id') + "&productQty="+ productQty).success(function(response){

			 if(typeof response === "object"){
				 if(response.status != '0000') {
					 if(response.message.indexOf("库存不足") > -1){
						 showMsg("该商品库存不足");
					 }else {
						 showMsg(response.message);
					 }
					 return;
				 }
			 }
			 
			 location.href = curLink;
		});
	})
});

//标签切换
function tabSwap(tabObj, conObj){
	
	$(tabObj).click(function(){
		var $this = $(this);
		var thisIndex = $this.index();
		
		//点击切换标签
		$this.addClass('on').siblings().removeClass('on');
		$(conObj).eq(thisIndex).show().siblings().hide();
	});
}

function showMsg(txt){
	if($(".pop-tips").is(':visible')){
		return;
	}
	$(".pop-tips-text").text(txt);
	$(".pop-tips").fadeIn();
	setTimeout(function(){
		$(".pop-tips").fadeOut();
	}, 2000)
}