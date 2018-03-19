
//是否已提交付款
var issubmited = false;

//幸运go支付
function toPay(){
	
	if($('#userName').val() === '' || $('#userPhone').val() === ''){
		$.Showmsg('请完善收货信息')
		$("#wrapBox").addClass('dsn');
		$("#userInfoBox").removeClass('dsn');
		return false;
	}
	
	var shouldPayAmt =  parseFloat($("#shouldPay").text().trim());
	
	var couponId = $('#allCheckBox').hasClass('item-checked') ? $('[name=couponId]').val() : '';
	
	if(issubmited){//防止二次提交付款
		return false; 
	}
	
	if(shouldPayAmt<0.000001){//100%用券支付成功
		var couponSelectedVal = $('#couponSelectedVal').text(),
		alertInfo = '确认使用 ￥' + couponSelectedVal + ' 消费券吗？';
		if(!confirm(alertInfo)){
			return false;
		}
	}
	
	$('.btn-check-pay').removeClass('bgred').addClass('bggrey01');
	issubmited = true;
	
	jQuery.ajax({
		url: "../common/toUrl.do",	//ajax调用微信统一接口获取prepayId
		cache: false,
		dataType:"json",
		type: 'post',
		async:false,
		data:{detailUrl: '/flashDealActivity/confirmPay.json', activityId: getUrlParam('activityId'), userName: $('.user-name').text(), userMobile: $('.user-phone').text() },
		success:function(data) {
			var obj = data;
			var orderId = obj.dataValue.orderId;
			
			if(obj.status !=undefined && "0000"!=obj.status){//提交订单失败
				alert(obj.message);
				issubmited = false;
				$('.btn-check-pay').removeClass('bggrey01').addClass('bgred');
				return;
			}
			
			$.ajax({
				url:"../order/getWXPayParams.do",
				data:{"orderId": orderId},
				dataType:"json",
				async:false,
				success:function(data){
					var signInfo = JSON.parse(data);
					
					WeixinJSBridge.invoke('getBrandWCPayRequest',{  
						"appId":signInfo.appId,                 //公众号名称，由商户传入  
						"timeStamp":signInfo.timeStamp,          //时间戳，自 1970 年以来的秒数  
						"nonceStr":signInfo.nonceStr,         //随机串  
						"package":signInfo.package,      //prepay_id=*** 
						"signType":signInfo.signType,        //微信签名方式:MD5  
						"paySign":signInfo.paySign           //微信签名  
					},function(res){      
						//alert(res.err_msg); // get_brand_wcpay_request:ok
						if(res.err_msg == "get_brand_wcpay_request:ok" ) {  
							window.location.href = "../flashDealActivity/activityDetail.html?userId="+orderId+"&fromPayDone=true";
						}else{  
							//alert("付款失败，请重新支付");
							alert("付款失败：" + res.err_msg);
							location.reload();     
							//当失败后，继续跳转该支付页面让用户可以继续付款，特别注意不能直接调转jsp，不然会报system:access_denied 
						}  
					});
					issubmited = false;
					$('.btn-check-pay').removeClass('bggrey01').addClass('bgred');
				}
			});
		},
		error: function(data) {
			alert("确认付款失败，请稍后再试");
			issubmited = false;
			$('.btn-check-pay').removeClass('bggrey01').addClass('bgred');
		}
	});
}


function resetPopBoxSize(){
	var windowWidth = $('#wrapBox').width();
	var windowHeight = $('body').height();
	if((windowWidth/windowHeight) > 508/840){
		$('.desc-box').width(508*windowHeight*0.8/840).css({'top':'10%', 'margin-left':(windowWidth-508*windowHeight*0.8/840)/2});
	}
}

function locaReload(){
	location.reload();
}

function PayDoneReload(){
	history.replaceState(null, '', '#fromPayDone=true');
	location.reload();
}

//页面详情
new Vue({
	el: '#wrapBox',
	data: {
		actDetail: null,	//商品详情
	},
	mounted: function(){
		var _self = this;
		_self.getAjax();
	},
	methods: {
		//服务详情页
		getAjax: function(){
			var _self=this;
			var url = "../common/toUrl.do";
	        
	        axios.get(url, {
	        	params:{ activityId: getUrlParam('activityId'), detailUrl: '/flashDealActivity/activityDetail.json' }
	        })
	        .then(function(response){
	        	_self.actDetail = response.data.dataValue;
	        })
	        .then(function(){
	        	new FastClick(document.body);
	        	
	        	if($('.swiper-container').length){
	        		var swiper = new Swiper('.swiper-container', {
	        			pagination: '.swiper-pagination',
	        			paginationClickable: false,
	        			spaceBetween: 0,
	        			loop:false
	        		});
	        		
	        		var swiperImgLength = $('.my-gallery figure').length;
	        		if(swiperImgLength <= 1){
	        			$('.swiper-pagination').hide();
	        		}
	        		
	        		//设置轮播图片高度
	        		var $swiperContainer = $('.swiper-container');
	        		var swiperContainerWidth = $swiperContainer.width();
	        		$swiperContainer.height(swiperContainerWidth*540/640);
	        	}
	        	

	        	//倒计时
	        	if($('.countdown').length){
	        		$('.countdown').each(function(){
	        		    $(this).downCount({
	        		        offset: +8,
	        		        curDate: '${sysdate }'
	        		    }, function () {
	        		        locaReload();
	        		    });	
	        		})
	        	}
	        		
	        	//从支付完成页面过来，显示参与成功提示
	        	if(location.search && location.search.indexOf('fromPayDone') > -1){
	        		$('#wrapBox, #tabBox').addClass('heightp100');	
	        		$('.tips-box.tips-done').removeClass('dsn');
	        		
	        		setTimeout(function(){
	        			$('.tips-box.tips-done').addClass('dsn');
	        			$('#wrapBox, #tabBox').removeClass('heightp100');
	        		},3000);
	        	}
	        	
	        	//重设弹出框尺寸
	        	resetPopBoxSize();
	        	
	        	
	        	//收货信息校验
	        	if($('#userInfoBox').length){
	        		$('#userPhone').val($(".user-phone").text());
	        		
	        		$("#userInfoBox").Validform({
	        			tiptype:1,
	        			btnSubmit:".btnSubmit",
	        			beforeSubmit: function(){
	        				$(".user-name").text($('#userName').val());
	        				$(".user-phone").text($('#userPhone').val());
	        				
	        				$("#userInfoBox").addClass('dsn');
	        				$("#wrapBox").removeClass('dsn');
	        				return false;
	        			}
	        		});
	        	}
	        	

	        	//查看弹框
	        	$(document).on('click', '.view-pop-box-btn', function(){
	        		var thisClass = $(this).attr('data-class');
	        		$('#wrapBox, #tabBox').addClass('heightp100');
	        		$('.' + thisClass).removeClass('dsn');
	        	});
	        	//关闭弹框
	        	$(document).on('click', '.common-close-btn', function(){
	        		$('#wrapBox, #tabBox').removeClass('heightp100');
	        		$('.wrap-bg').addClass('dsn');
	        	});

	        	//收货信息弹框
	        	$(document).on('click', '.addressInfo', function(){
	        		$("#wrapBox").addClass('dsn');
	        		$("#userInfoBox").removeClass('dsn');
	        	});

	        	//微信分享
	        	setTimeout(function(){
	        		$.getScript('https://res.wx.qq.com/open/js/jweixin-1.0.0.js', function(){
	        		   $.getScript('../js/wx.share.info.js', function(){
	        			    //轻应用分享
	        				setShareInfo({
	        					title: '供港品质好生鲜，尽在解放区',		// 分享标题
	        					desc: '解放区提供便利快捷物业服务、供港高品生鲜，到店免费体验还有优惠~',		// 分享描述
	        					link: location.href,	// 分享链接
	        					imgIcon: getCurHref('resource') + '/docs/commonimages/jfqlogo_share.png'
	        				});
	        			});
	        		});
	        	}, 800);
				
	        }).catch(function(error){
	        	alert(error);
	        })
		}
	}
});

