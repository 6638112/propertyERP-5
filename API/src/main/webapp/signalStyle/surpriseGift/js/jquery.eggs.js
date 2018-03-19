
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
					
					var $eggBrake = $('<li class="egg-brake"><img src="images/egg-brake0.png" /></li>');
					$('.eggs').append($eggBrake);
					$eggBrake.show();
					var i=0;
					setInterval(function(){ 
						if(i<1){
							i+=1;
							var url = 'images/egg-brake'+ i + '.png';
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
		/*setTimeout(function(){
			$('.stars').fadeIn();
		}, 700);*/
		setTimeout(function(){
			$giftTxt.removeClass('opacity0').addClass('animated4s slideInDown');
			$giftBtn.removeClass('dsn').addClass('animated4s slideInUp');
			$prizeInfo.show().addClass('animated4s slideInUp');
			$giftBtn.click(function(){
				$giftBtn.hide();
				$closeBtn.show().addClass('animated4s slideInDown');
				$shareInfo.show().find('li').addClass('animated4s slideInUp');
			});
		}, 200);
	}, 700);
	
};

//文字居中
$(function(){
	function setNumMarginLeft(){
		var $giftNum = $('.gift-num');
		var numMarginLeft = $giftNum.width()/2;
		$giftNum.css('margin-left',-numMarginLeft);
	}
	setNumMarginLeft();
	$(window).resize(function(){
		setNumMarginLeft();
	});
});
//文档最小高度
$(function(){
	function setDocHeight(){		
		var $docum = $('html');
		var docHeight = $docum.height();
		var docWidth = $docum.width();
		var docScale = docWidth/docHeight;
		var defaultScale = 640/1020;
		if(docScale > defaultScale){
			var conWidth = docHeight*640/1020;
			$('#container').css({'width':conWidth, 'height':docHeight});
		}else{
			var conHeight = conWidth*1020/640;
			$('#container').css({'width':docWidth, 'height':conHeight});
		}
	}
	setDocHeight();
	$(window).resize(function(){
		setDocHeight();
	});
});

//分享和关闭页面
$(function(){
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端

	var shareInfo = {
		title: '解放区两周年庆，百万好礼任性送',
		desc: '社区生活，就叫解放区。解放后，一切都会有',
		icon:  $('#container').data('path') + '/shareDiscount/images/logo-jfq.png',
		shareUrl: $('#container').data('path') + '/anniversary_share/index.htm'
	}
	//安卓
	if(isAndroid){
		//关闭按钮
		$('.close-btn').click(function(){
			window.amazing.giftClose();
		});
		//分享到好友
		$('#shareToFriend').click(function(){
			window.amazing.giftShareToFriend(shareInfo.title, shareInfo.desc, shareInfo.icon, shareInfo.shareUrl);
		});
		//分享到朋友圈
		$('#shareToQuan').click(function(){
			window.amazing.giftShareToQuan(shareInfo.title, shareInfo.icon, shareInfo.shareUrl);
		});
	}
	
	//ios
	if(isiOS){
		//关闭按钮
		$('.close-btn').click(function(){
			document.location="jfq://"+'giftClose';
		});
		//分享到好友
		$('#shareToFriend').click(function(){
			document.location="jfq://"+'giftshareToFriend'+"/"+shareInfo.title+","+shareInfo.desc+","+shareInfo.icon+","+shareInfo.shareUrl;
		});
		//分享到朋友圈
		$('#shareToQuan').click(function(){
			document.location="jfq://"+'giftShareToQuan'+"/"+shareInfo.title+","+shareInfo.desc+","+shareInfo.icon+","+shareInfo.shareUrl;
		});
	}
	
});
