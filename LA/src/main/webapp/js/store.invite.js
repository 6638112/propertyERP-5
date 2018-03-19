$(function(){
    var swiper = new Swiper('.swiper-container', {
        direction: 'vertical',
		effect: 'fade',
		loop: false,
		mousewheelControl: false,
		keyboardControl: true,
		noSwipingClass: 'swiper-no-swiping',
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
	
	$("#nextSlide").click(function(){
		swiper.slideTo(1);
	})
	
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
		//resetBoxSize();
		//resetConSize();
	});

	//判断获取当前域名
	function getCurHref(project){
		var curHref = '';
		var curOrigin = location.origin;
		if(curOrigin.indexOf('linlile.com.cn') > -1){
			curHref = 'https://'+project+'.linlile.com.cn';
		}else if(curOrigin.indexOf('linlile.cn') > -1){
			curHref = 'https://'+project+'.linlile.cn';
		}else if(curOrigin.indexOf('jiefangqu') > -1){
			curHref = 'https://'+project+'.jiefangqu.com';
		}else{
			curHref = 'http://localhost';
		}
		return curHref;
	}
    
	//校验
	function validInput(){
		var nameVal = $('.input-name').val();
		var phoneVal = $.trim($('.input-phone').val());
		var regMphone = /^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|17[0-9]{9}$|18[0-9]{9}$/;
		var regPhone = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
		
		if(nameVal == ''){
			alert('请填写姓名');
			return false;
		}else if(phoneVal == ''){
			alert('请填写手机号码');
			return false;
		}else if(!regMphone.test(phoneVal)){
			alert('请填写正确的手机号码格式');
			return false;
		}else{
			$.ajax({
    			  url: "../commercialOpportunity/saveCOinfo.json",
    			  data:{"companyName" : nameVal, "linkTel": phoneVal }, 
				  dataType : "json",  
    			  success: function(data){
    			    alert('提交成功！')
    			    swiper.slideTo(2);
    			  },  
                  error: function(XMLHttpRequest, textStatus, errorThrown) {
                	  alert('网络不给力，请稍后重试'); 
                  },
    		});
		}
	}
	
	$('.submit-btn').click(function(){
		validInput();
	});
});