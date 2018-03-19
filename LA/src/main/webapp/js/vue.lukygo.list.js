
//重设图片宽度
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
//标签切换，加载时显示默认标签
function tabSwap(tabObj, conObj){
	
	$(tabObj).click(function(){
		var $this = $(this);
		var thisIndex = $this.index();
		
		//点击切换标签
		$this.addClass('red').siblings().removeClass('red');
		$(conObj).eq(thisIndex).show().siblings(conObj).hide();
	});
}
//获取slide宽度
function setSlideWidth(obj){
	var totalWidth = $(obj).find('li').width()*$(obj).find('li').length;
	$(obj).width(totalWidth);
}

//页面详情
new Vue({
	el: '#wrapBox',
	data: {
		actDetail: null,	//商品详情
		userId: null,
		supplyMerchantId: null,
		tipsShow: true,	//是否显示提示框
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
	        	params:{ supplyMerchantId: getUrlParam('supplyMerchantId'), detailUrl: '/flashDealActivity/activityList.json' }
	        })
	        .then(function(response){
	        	var actDetailData = response.data.dataValue;
	        	_self.supplyMerchantId = response.data.dataValue.supplyMerchantId;
	        	_self.userId = response.data.dataValue.userId;
	        	
	        	//设置倒计时时间
	        	actDetailData.activityList.forEach(function(value, index, arr){
	        		value.flashDealActivityDetailEntities.forEach(function(value01, index01, arr01){
		        		if(value01.activityStatus === 1){
		        			value01.dataTime = value01.activityEndTime;
		        		}else if(value01.activityStatus === 2){
		        			value01.dataTime = value01.activityStartTime;
		        		}
	        		})
	        	})
	        	
	        	_self.actDetail = actDetailData;
	        	
	        })
	        .then(function(){

	        	//日期滑动切换
	        	var swiper = new Swiper('.swiper-container', {
	                pagination: '.swiper-pagination',
	                slidesPerView: 3.5,
	                spaceBetween: 0,
	                freeMode: true
	            });
	            setSlideWidth('.sell-date');
	            tabSwap('.swiper-slide', '.swiper-con-box');
	            
	        	new FastClick(document.body);

	            if( $('.sell-list').length > 0 || ( $('.sell-list').length === 0 && getUrlParam('pic') == null )){
	        		$('body').css('opacity','1');
	        	}
	        	//如果没有商品，则跳转到活动预告
	        	if( $('.sell-list').length === 0 && getUrlParam('pic') != null ){
	        		location.href = 'https://resource.jiefangqu.com/docs/actPreview/index.htm?pic=' + getUrlParam('pic');
	        	}
	        	
	        	//提醒我按钮
	        	var hasReminded = false;
	        	$(document).on('click', '.remind-btn', function(){
	        		if(hasReminded){
	        			return;
	        		}
	        		var $this = $(this);

	        		var thisDateTime = $this.parents('.countdown').attr('data-time');
	        		var thisDate = thisDateTime.substring(3,5)*1;
	        		var thisHour = thisDateTime.substring(11,13)*1;
	        		var thisMinutes = thisDateTime.substring(14,16)*1;
	        		var curDate = new Date().getDate();
	        		var curHour = new Date().getHours();
	        		var curMinutes = new Date().getMinutes();
	        		if((thisDate-curDate) === 0 && (thisHour-curHour) === 0 && (thisMinutes-curMinutes) <= 3){
	        			layer.alert('活动即将开始!');
	        			return;
	        		}
	        		
	        		var thisActivityId = $this.parents('.sell-list').attr('data-id');
	        		hasReminded = true;
	        		$.ajax({
	        			url: '../common/toUrl.do',
	        			dataType: 'json',
	        			data:{'activityId':thisActivityId, 'detailUrl': '/flashDealActivity/flashDealRemind.json'},
	        			async: true,
	        			timeout: 10000,
	        			success: function(data){
	        				if(data.status=="0000"){
	        					layer.alert('设置成功，开始前3分钟提醒您');
	        					var thisSrc = $this.find('img').attr('src');
	        					var newSrc = thisSrc.replace('remind_btn.png', 'has_remind.png');
	        					$this.find('img').attr('src', newSrc);
	        					$this.off('click');
	        					hasReminded = false;
	        				} else {
	        					layer.alert(data.message);
	        					hasReminded = false;
	        				}
	        			},
	        			complete: function(XMLHttpRequest,status){
	        				if(status === 'timeout'){
	        					layer.alert('网络不给力，请重试');
	        					hasReminded = false;
	        				}
	        			},
	        			error: function(){
	        				hasReminded = false;
	        			}
	        		});
	        	});
	        	
	        	//倒计时
	        	if($('.countdown').length){
	        		$('.countdown').each(function(){
	        		    $(this).downCount({
	        		        offset: +8
	        		    }, function () {
	        		        locaReload();
	        		    });	
	        		})
	        	}
	            
	        	//查看活动说明详情
	        	$('#viewDescDetail').click(function(){
	        		$('#wrapBox').addClass('heightp100');
	        		$('.pop-box02').removeClass('dsn');
	        	});
	        	//关闭活动说明详情
	        	$('#closeTipsBtn').click(function(){
	        		$('#wrapBox').removeClass('heightp100');
	        		$('.pop-box02').addClass('dsn');
	        	});
	        	
	        	//重设弹出框尺寸
	        	resetPopBoxSize();
	        	setInterval(function(){
	        		resetPopBoxSize();
	        	},1000);


	            //重设图片尺寸
	            var itemImgWidth = $(".item-img-thumb").width();
	            $(".item-img-thumb").height(itemImgWidth);
	            $('.item-img-thumb').reSetSwiperImgSize(itemImgWidth,itemImgWidth);

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

