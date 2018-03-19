//店铺端
$(function(){
	//修改密码
	$('.password-check-wrap').click(function(){
		var $passwordCheckBox = $(this).find('.password-check-box');
		$passwordCheckBox.toggleClass('on');
		if($('.show-password').hasClass('on')){
			$('.password-box').attr('type','text');			
		}else{
			$('.password-box').attr('type','password');		
		}
	});
	
	$('.change-password-btn').click(function(){
		$('.welcome-img').fadeOut();
	});
	
	//重置tab内容高度
	$('.item-list-arrow-box').click(function(){
		$(this).prev('.item-list-box').stop(true, false).animate({'height':'toggle'}, 200);
		$(this).children().swapAddClass('rotateIn', 'rotateOut');
		setTimeout(function(){
			tabBoxTuneHeight();
		},201);
	});
	
	function tabBoxTuneHeight(){
		var conHeight = $('#tabBox-bd').height() + 55;
		$('.tempWrap').css('height',conHeight);
	};
	
	//样式切换函数
	$.fn.swapAddClass = function(class1, class2){
		return this.each(function(){
			var $elem = $(this);
			if($elem.hasClass(class1)){
				$elem.removeClass(class1).addClass(class2);
			}else if($elem.hasClass(class2)){
				$elem.removeClass(class2).addClass(class1);
			}else{
				$elem.addClass(class1);
			}
		});
	};
	//切换文本
	$.fn.swapText = function(txt1, txt2){
		return this.each(function(){
			var $element = $(this);
			if($element.text() == txt1){
				$element.text(txt2);
			}
			else if($element.text() == txt2){
				$element.text(txt1);	
			}
		});
	};
	
	//tab切换样式
	var $orderTab = $(".order-tab li");
	$orderTab.click(function(){
		if($(this).hasClass('on')){
			$(this).find('label').addClass('on');
			$(this).siblings().find('label').removeClass('on');
			
			//复制订单信息按钮切换 隐藏显示
			if($(this).index() == 1){
				$(".copy-order-info-fixed").hide();
			}else{
				$(".copy-order-info-fixed").show();
			}
		}
	});	
	setInterval(function(){
		$orderTab.each(function(){
			if($(this).hasClass('on')){
				$(this).find('label').addClass('on');
				$(this).siblings().find('label').removeClass('on');
				
				//复制订单信息按钮切换 隐藏显示
				if($(this).index() == 1){
					$(".copy-order-info-fixed").hide();
				}else{
					$(".copy-order-info-fixed").show();
				}
			}
		});	
	},100);

	
	//商品管理-切换类别、排序
	$('.sort-name').click(function(){
		var $sortSelect = $(this).next('.sort-select');
		var $sortSiblingsSelect = $('.sort-select').not($sortSelect);
		$sortSiblingsSelect.each(function() {
            if($(this).is(':visible')){
				$(this).hide();
				$(this).siblings('.sort-name').find('img').swapAddClass('rotateIn', 'rotateOut');
			}
        });
		
		$sortSelect.toggle();
		if($sortSelect.is(':visible')){
			$('body').addClass('overhidden');
		}else{
			$('body').removeClass('overhidden');
		}
		$(this).find('img').swapAddClass('rotateIn', 'rotateOut');
	});
	$('.sort-select li').click(function(){
		var thisNameText = $(this).text();		
		$(this).addClass('on').siblings('li').removeClass();
		$(this).parents('.sort-select').hide();
		$(this).parents('.sort-select').siblings('.sort-name').find('img').swapAddClass('rotateIn', 'rotateOut');
		$(this).parents('.sort-select').siblings('.sort-name').find('.sort-name-text').text(thisNameText);
		$('body').removeClass('overhidden');
	});

	//设置预览图片为原始尺寸
	$('.my-gallery figure').each(function(){
		var $this = $(this);
		var $swiperImg = $(this).find('a');
		var thisSrc = $swiperImg.attr('href');
		getImageWidth(thisSrc,function(swiperImgWidth,swiperImgHeight){
			$this.find('a').attr('data-size', swiperImgWidth + 'x' + swiperImgHeight);
		});
		
	});
	
});

//获取原始图片尺寸
function getImageWidth(url,callback){
	var img = new Image();
	img.src = url;
	
	var resetImgSize = setInterval(function(){
		if(img.width > 0){
			clearInterval(resetImgSize);
			callback(img.width, img.height);
		}
	},100)
}

/*url scheme 跳转app*************************************************************/
function OpenAppBySchema(scheme) {
	var ua = navigator.userAgent.toLowerCase();    
	if (ua.indexOf('safari') > -1 && (ua.indexOf('os 8') == -1
			|| ua.indexOf('os 7') == -1
			|| ua.indexOf('os 6') == -1
			|| ua.indexOf('os 5') == -1)) {
		var schemeLinkOpen = document.getElementById('schemeLinkOpen');
		if (!schemeLinkOpen) {
			schemeLinkOpen = document.createElement('a');
			schemeLinkOpen.id = 'schemeLinkOpen';
			schemeLinkOpen.style.display = 'none';
			document.body.appendChild(schemeLinkOpen);
		}
		schemeLinkOpen.href = scheme;
		// 执行click
		schemeLinkOpen.dispatchEvent(customClickEvent());
	}
	var iframeObj = document.createElement("iframe");
	var startTime = (new Date()).getTime();
	if (iframeObj != null) {
		iframeObj.setAttribute("style", "height:0px;width:0px;display:none;")
		iframeObj.setAttribute("src", scheme);
		document.body.appendChild(iframeObj);
		document.body.removeChild(iframeObj);
	}
}

function customClickEvent() {
	var clickEvent;
	if (window.CustomEvent) {
		clickEvent = new window.CustomEvent("click", {
			canBubble: true,
			cancelable: true
		}
		);
	} else {
		clickEvent = document.createEvent('Event');
		clickEvent.initEvent('click', true, true);
	}
	return clickEvent;
}

function showMsg(txt){
	if($(".pop-tips").is(':visible')){
		return;
	}
	$(".pop-tips-text").text(txt);
	$(".pop-tips").fadeIn();
	setTimeout(function(){
		$(".pop-tips").fadeOut();
	}, 2500)
}