$(function(){
	
	var userId = getUrlParam('userId');
	var userIdParam = userId === null ? '' : '&userId=' + userId;
	//商品跳转
	$(".buynow-btn").click(function(){
		var thisItemId = $(this).attr("data-id");
		location.href = getCurHref('www') + '/LA/product/productDetail.do?ptId=' + thisItemId + userIdParam;
	});
	
	//到家服务跳转
	$(".booknow-btn").click(function(){
		var thisServerId = this.getAttribute('data-id');
		location.href = getCurHref('www') + '/LA/dredge/productDetail.html?productId=' + thisServerId + '&jfqsource=weixin' + userIdParam;
	});
	
	//h5页面跳转
	$(".toh5page-btn").click(function(){
		var thisLink = this.getAttribute('data-href');
		location.href = thisLink;
	});
	
    //lazyload
    $("img.bgloading").lazyload(
    	{effect: "fadeIn"},
    	setImgBlock
    );
    
    //在app端需要滚动一下，才能显示图片。。。
	$('body,html').animate({'scrollTop':1}, 200);

	//微信分享
	setTimeout(function(){
		$.getScript('https://res.wx.qq.com/open/js/jweixin-1.0.0.js', function(){
		   $.getScript('../js/wx.share.info.js', function(){
			    //轻应用分享
				setShareInfo({
//					title: '供港品质好生鲜，尽在解放区',		// 分享标题
//					desc: '解放区提供便利快捷物业服务、供港高品生鲜，到店免费体验还有优惠~',		// 分享描述
					title: '双周团惊喜来袭',		// 分享标题
					desc: '解放区空调、抽油烟机保养专场。超值惊喜，尽在双周团',		// 分享描述
					link: location.href,	// 分享链接
					imgIcon: getCurHref('resource') + '/docs/commonimages/jfqlogo_share.png'
				});
			});
		});
	}, 800);
});

//图片外边框有1像素空白，须设置图片为块级元素
function setImgBlock(){
	$("img.bgloading").css('display', 'block');
}

function getUrlParam(name) {
    //构造一个含有目标参数的正则表达式对象
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    //匹配目标参数
    var r = window.location.search.substr(1).match(reg);
    //返回参数值
    if (r != null)
        return unescape(r[2]);
    return null;
}

//判断获取当前域名
function getCurHref(project){
	var curHref = '';
	var curOrigin = location.origin;
	var curProtocol = location.protocol;
	if(curOrigin.indexOf('linlile.com.cn') > -1){
		curHref = curProtocol + '//'+project+'.linlile.com.cn';
	}else if(curOrigin.indexOf('linlile.cn') > -1){
		curHref = curProtocol + '//'+project+'.linlile.cn';
	}else if(curOrigin.indexOf('jiefangqu') > -1){
		curHref = curProtocol + '//'+project+'.jiefangqu.com';
	}else{
		curHref = curProtocol + '//localhost';
	}
	return curHref;
}
	
function showMsg(txt, hidesoon){
	$(".bg-wrap").removeClass('hide');
	$(".pop-tips-text").text(txt);
	$(".pop-tips").fadeIn();
	if(hidesoon === 'hidesoon'){
		setTimeout(function(){
			$(".pop-tips").fadeOut();
			$(".bg-wrap").addClass('hide');
		}, 2500)
	}
}
	
function hideMsg(txt){
	$(".pop-tips-text").text(txt);
	setTimeout(function(){
		$(".pop-tips").fadeOut();
		$(".bg-wrap").addClass('hide');
	}, 2500)
}
