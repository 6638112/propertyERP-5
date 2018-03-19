$(function(){
	function initSwiper(){
	    var swiper = new Swiper('.swiper-container', {
	        direction: 'vertical',
			effect: 'slide',
			loop: false,
			mousewheelControl: true,
			keyboardControl: true,
			onSlideChangeStart: function(){
				changeClass();
			},
			onTouchMove: function(){
				changeClass();	
			}
	    });
		
		function changeClass(){
			var $activeSlide = $('.swiper-slide-active');
			$activeSlide.find('.elements').each(function(){
				var dataClass = $(this).attr('data-class');
				$(this).show().addClass(dataClass);
			});
			
			var $prevSlide = $('.swiper-slide-prev');
			$prevSlide.find('.elements').each(function(){
				var dataClass = $(this).attr('data-class');
				if($(this).hasClass(dataClass)){
					$(this).removeClass(dataClass).addClass('hide');	
				}
			});
			
			var $nextSlide = $('.swiper-slide-next');
			$nextSlide.find('.elements').each(function(){
				var dataClass = $(this).attr('data-class');
				if($(this).hasClass(dataClass)){
					$(this).removeClass(dataClass).addClass('hide');	
				}
			});	
		};
		changeClass();
	}
	
	//重置容器尺寸
	function resetBoxSize(){
		var windowHeight = $('.swiper-container').height();
		var windowWidth = $('.swiper-container').width();
		var scaleWindow = windowWidth/windowHeight;
		var scaleDefault = 750/1254;
		if(scaleWindow > scaleDefault){
			var boxWidth = 750*windowHeight/1254;
			$('.page-elements').css({'width':boxWidth, 'height':windowHeight});
		}else{
			var boxHeight = 1254*windowWidth/750;
			$('.page-elements').css({'width':windowWidth, 'height':boxHeight, 'margin-top':(windowHeight-boxHeight)/2});
		}
	}
	resetBoxSize();
	
	//重设图片高度
	function resetConSize(){
		var windowHeight = $('.swiper-container').height();
		var windowWidth = $('.swiper-container').width();
		$('.pagebg img').css({'height':windowHeight,'width':windowWidth});
	}
	resetConSize();
	
	$(window).resize(function(){
		resetConSize();
		resetBoxSize();
	});
	

	//音乐控制
	$("#audioPlay").click(function(){
        if(audio.paused){
            audio.play();
            $(this).addClass('circleTiming infinite rotateInCircle');
        }else{
            audio.pause();
            $(this).removeClass('circleTiming infinite rotateInCircle');
        }
    });
    
    // 方法1: 现在微信官方已经推出了微信JS-SDK, 最好还是不要使用"野生"方式, 因为不知道什么时候就可以不能用了!
    // http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html
    // 通过config接口注入权限验证配置后, 在 ready 中 play 一下 audio
    function autoPlayAudio() {
        wx.config({
            // 配置信息(必需，即使不正确或为空)，才能使用 wx.ready
            debug: false,
            appId: '',
            timestamp: 1,
            nonceStr: '',
            signature: '',
            jsApiList: []
        });
        wx.ready(function() {
            document.getElementById('audio').play();
        });
    }
    //autoPlayAudio();
    
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	
	var shareInfo = {
		title: '解放区两周年庆，百万好礼任性送',
		desc: '社区生活，就叫解放区。解放后，一切都会有',
		icon: $('#mainWrap').data('path') + '/shareDiscount/images/logo-jfq.png',
		shareUrl: $('#mainWrap').data('path') + '/anniversary_share/index.htm'
	}
	//安卓
	if(isAndroid){
		//分享到好友
		document.getElementById("shareToFriend").onclick = function(){
			window.confession.confessionShareToFriend(shareInfo.title, shareInfo.desc, shareInfo.icon, shareInfo.shareUrl);
		};
		//分享到朋友圈
		document.getElementById("shareToQuan").onclick = function(){
			window.confession.confessionShareToQuan(shareInfo.title, shareInfo.icon, shareInfo.shareUrl);
		};
	}
	
	//ios
	if(isiOS){
		//分享到好友
		document.getElementById("shareToFriend").onclick = function(){
			document.location="jfq://"+'confessionShareToFriend'+"/"+shareInfo.title+","+shareInfo.desc+","+shareInfo.icon+","+shareInfo.shareUrl;
		};
		//分享到朋友圈
		document.getElementById("shareToQuan").onclick = function(){
			document.location="jfq://"+'confessionShareToQuan'+"/"+shareInfo.title+","+shareInfo.desc+","+shareInfo.icon+","+shareInfo.shareUrl;
		};
	}
    
    //获取用户轨迹信息
	var userInfo = {
		dataInfo: null,
		ajax: function(){
			var me = this;
			$.ajax({
				url:"http://api.linlile.com.cn/API/msAnnualConfession/getUserInfo.json",
				data:{callBack:'jsonpCallback'},
				dataType : "jsonp",	//数据类型为jsonp  
        		jsonpCallback: "jsonpCallback",	//服务端用于接收callback调用的function名的参数
				async:false,
				success:function(data){
					me.dataInfo = data.dataValue;
					
					//设置用户信息
					me.setUserInfo();
				}
			});
		},
		setUserInfo: function(){
			var me = this;
			var data = me.dataInfo;
			
			if(data.dateRegister !== null){
				$('#dateRegister').removeClass('hide');
				$('#metTime').text(data.dateRegister);
				$('#countLogin').text(data.countLogin);
			}else{
				$('#metTime').remove();
			}
			if(data.dateFirstFee !== null){
				$('#dateFirstFee').removeClass('hide');
				$('#payTime').text(data.dateFirstFee);
			}else{
				$('#dateFirstFee').remove();
			}
			if(data.dateFirstMarket !== null){
				$('#dateFirstMarket').removeClass('hide');
				$('#shoppingTime').text(data.dateFirstMarket);
			}else{
				$('#dateFirstMarket').remove();
			}
			if(data.dateFirstService !== null){
				$('#dateFirstService').removeClass('hide');
				$('#housekeepingTime').text(data.dateFirstService);
			}else{
				$('#dateFirstService').remove();
			}
			if(data.amountPayment !== null){
				$('#amountPayment').removeClass('hide');
				$('#spendAmount').text(data.amountPayment/100);
			}else{
				$('#amountPayment').remove();
			}
			
			initSwiper();
		}
	};
	
	//发起接口请求
	//userInfo.ajax();
	initSwiper();
});


