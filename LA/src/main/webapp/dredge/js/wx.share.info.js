function setShareInfo(newOptions){
	var shareInfo = {
			title: '社区物业金矿 等你来挖',		// 分享标题
			desc: '社区生活，就叫解放区。解放后，一切都会有',		// 分享描述
			link: '',	// 分享链接
			imgToFriend: $('#sharePath').data('path') + '/commonimages/jfq_share_logo_bgwhite.png',	// 分享图标
			imgToQuan: $('#sharePath').data('path') + '/commonimages/jfq_share_logo_bggrey.png'	// 分享图标
	}
	var newOptions = newOptions || null;
	//合并自定义分享信息
	if(newOptions !== null){
		shareInfo = $.extend({}, shareInfo, newOptions);
	}
	
	//alert(shareInfo.link);
	//如果没定义分享link，则默认为分享当前链接
//	if(shareInfo.link === ''){
//		shareInfo.link = location.href.split('#')[0];
//	}
//	
	//必须是当前链接参与签名才可以，因为用户可能会二次分享，微信会自带参数
	shareInfo.link = location.href.split('#')[0];
	
	//alert(location.href.split('#')[0]);
	
	//获取分享信息
	var wxInfo = {
			signInfo: null,
			ajax: function(){
				var me = this;
				$.ajax({
					url:"../share/getShareParam.do",
					data:{currentURL:shareInfo.link},
					dataType:"json",
					async:false,
					success:function(data){
						me.signInfo = data.dataValue.signInfo;
					}
				});
			}
	};
	wxInfo.ajax();
	
	//统一微信分享风格 
	wx.config({
		debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		appId: wxInfo.signInfo.appId, // 必填，公众号的唯一标识
		timestamp: wxInfo.signInfo.timestamp, // 必填，生成签名的时间戳
		nonceStr: wxInfo.signInfo.nonceStr, // 必填，生成签名的随机串
		signature: wxInfo.signInfo.signature,// 必填，签名，见附录1
		jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage', 'onMenuShareQQ'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	wx.ready(function(){
		// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
		
		//分享朋友圈
		wx.onMenuShareTimeline({
			title: shareInfo.title, // 分享标题
			link: shareInfo.link, // 分享链接
			imgUrl: shareInfo.imgToFriend // 分享图标
		});
		
		//发送给朋友
		wx.onMenuShareAppMessage({
			title: shareInfo.friendTitle, // 分享标题
			desc: shareInfo.desc, // 分享描述
			link: shareInfo.link, // 分享链接
			imgUrl: shareInfo.imgToFriend // 分享图标
		});

		//发送到QQ
		wx.onMenuShareQQ({
			title: shareInfo.title, // 分享标题
			desc: shareInfo.desc, // 分享描述
			link: shareInfo.link, // 分享链接
			imgUrl: shareInfo.imgToFriend // 分享图标
		});
		
		
	});
	wx.getLocation({
	    type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
	    success: function (res) {
	        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
	        var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
	        var speed = res.speed; // 速度，以米/每秒计
	        var accuracy = res.accuracy; // 位置精度
	    }
	});
}
