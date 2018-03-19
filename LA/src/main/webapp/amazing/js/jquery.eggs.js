
function eggsFlipIn(){
	//鸡蛋落下
	var $eggs = $('.eggs li');
	$eggs.eq(0).show().addClass('animated1s bounceInDown');
	$eggs.eq(1).show().addClass('animated2s bounceInDown');
	$eggs.eq(2).show().addClass('animated3s bounceInDown');
	//鸡蛋晃动
	setTimeout(function(){
		$eggs.eq(0).attr('class', 'animated3s swing');
	}, 1000);
	setTimeout(function(){
		$eggs.eq(1).attr('class', 'animated3s swing');
	}, 1200);
	setTimeout(function(){
		$eggs.eq(2).attr('class', 'animated3s swing');
	}, 1400);
	//选择鸡蛋，放大、居中、砸开动画
	setTimeout(function(){
		$eggs.one('click', function(){
			var dist01 = $eggs.eq(1).offset().left;
			var dist02 = $(this).offset().left;
			$(this).attr('class','pos-relative').siblings('li').attr('class','animated0s fadeOutDown');
			$(this).find('img').attr('class','animated0s zoomIn')
			if(!$eggs.is(':animated')){
				$(this).stop(true, false).animate({left:(dist01- dist02)}, 300).animate({'opacity': '0'}, 300).hide(100);
				setTimeout(function(){
					
					var $eggBrake = $('<li class="egg-brake"><img src="/LA/amazing/images/egg-brake0.png" /></li>');
					$('.eggs').append($eggBrake);
					$eggBrake.show();
					var i=0;
					setInterval(function(){ 
						if(i<1){
							i+=1;
							var url = '/LA/amazing/images/egg-brake'+ i + '.png';
							$eggBrake.find('img').attr('src', url);
						}
					}, 150);
					setTimeout(function(){
						giftFadeIn();
					}, 50);

				}, 400);
			}
		});
	}, 2800);
};

//礼物呈现动画
function giftFadeIn(){
	var $giftImg = $('.gift-img');
	var $giftNum = $('.gift-num');
	var $giftTxt = $('.gift-txt');
	var $giftBtn = $('.gift-btn');
	var $shareInfo = $('.share-info');
	var $closeBtn = $('.close-btn');
	var $prizeInfo = $('.prize-info');
	$giftNum.hide();
	$('.gift').show();
	$giftImg.addClass('animated4s bounceIn');
	$('.lightbg').fadeIn();
	setTimeout(function(){
		var numMarginLeft = $giftNum.width()/2;
		$giftNum.css('margin-left',-numMarginLeft).show().addClass('animated4s bounceIn');
		setTimeout(function(){
			$giftTxt.removeClass('opacity0').addClass('animated4s slideInDown');
			$giftBtn.removeClass('dsn').addClass('animated4s slideInUp');
			$closeBtn.removeClass('dsn').addClass('animated4s slideInDown');
			$prizeInfo.show().addClass('animated4s slideInUp');
		}, 200);
	}, 700);
	
};



//文字居中
$(function(){
	function setNumMarginLeft(){
		var $giftNum = $('.gift-num');
		var numMarginLeft = $giftNum.width()/2;
		$giftNum.css('margin-left',-numMarginLeft+5);
	}
	setNumMarginLeft();
	$(window).resize(function(){
		setNumMarginLeft();
	});
});

//文档最小高度
function setDocHeight(){
	var $docum = $('html');
	var DocWidth = $('#container').width();
	$docum.css('min-height',DocWidth*1136/640);
}
setDocHeight();
$(window).resize(function(){
	setDocHeight();
});

//重置文档高度
function resetDocHeight(){
	var $docum = $('html');
	var giftHeight = $('.gift').height();
	$docum.css('height',giftHeight);
}


//显示关注二维码
$(function(){
	$('.erweima-btn').click(function(){
		$(this).hide();
		$('.gift-txt').hide();
		$('.erweima-info').show();
		resetDocHeight();
	});
});