//判断安卓ios系统
var u = navigator.userAgent, app = navigator.appVersion;
var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端

var isWeixin = location.search.indexOf('weixin') > -1;

function setupWebViewJavascriptBridge(callback) {
    if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
    if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
    window.WVJBCallbacks = [callback];
    var WVJBIframe = document.createElement('iframe');
    WVJBIframe.style.display = 'none';
    WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
    document.documentElement.appendChild(WVJBIframe);
    setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0);
}

//设置当前要传递的信息：app内跳转链接-https://www.linlile.com.cn/LA/themeAdv/productList.html?advId=5014413
function setPostInfo(obj){
	var postInfo = {
			jumpType: '6',
			typeId: ''
		}
	
	var thisUrl = obj.getAttribute('data-link');
	if(!thisUrl){
		return;
	}
	var thisTypeId = thisUrl.substring(thisUrl.indexOf('=') + 1);
	postInfo.typeId = thisTypeId;
	
	return postInfo;
}

//绑定banner点击事件
function setBannerClickEvent(){
	var itemArray = document.querySelectorAll('.slide-li');
	
	if(isWeixin){
		[].forEach.call(itemArray, function(obj){
			obj.addEventListener('click',function(){
				var thisUrl = this.getAttribute('data-link');
				if(!thisUrl){
					return;
				}
				document.location.href = thisUrl + '&jfqsource=weixin';
			});
		});
	}else{
		//安卓
		if(isAndroid){
			[].forEach.call(itemArray, function(obj){
				obj.addEventListener('click',function(){
					var me = this;
					window.couponJump.jumpToTarget(JSON.stringify(setPostInfo(me)));
				});
			});
		}
		
		//ios
		if(isiOS){
			[].forEach.call(itemArray, function(obj){
				obj.addEventListener('click',function(){
					var me = this;
					setupWebViewJavascriptBridge(function(bridge) {
						bridge.callHandler('jumpToTarget', setPostInfo(me), function(response) {});
					});
				});
			});
		}
	}
}

//页面详情
var feePayData = new Vue({
	el: '#wrapBox',
	data: {
		dataValue: [],	//缴费服务图标数据
		newList: [],	//重新分组服务图标
		adList: []	//缴费banner广告数据
	},
	mounted: function(){
		var self = this;
		self.getAjax();
	},
	methods: {
		getAjax: function(){
			var self=this;
			var url = '../common/toUrl.do';
	        
	        axios.get(url, {
	        	params:{ 'detailUrl': '/livingPay/qryLivingPayItemList.json' }
	        })
	        .then(function(response){
	        	self.dataValue = response.data.dataValue.list;	//缴费服务图标数据
	        	
	        	self.adList = response.data.dataValue.adsList;	//缴费banner广告数据
	        	
	        	self.reSortArr(3);
	        })
	        .then(function(){
	        	new FastClick(document.body);

			    TouchSlide({ 
					slideCell:"#slideBox",
					titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
					mainCell:".bd ul", 
					effect:"left", 
					autoPage:true,//自动分页
					autoPlay:true //自动播放
			    });
				var liLength = document.getElementById('dotsLi').children.length;
				if(liLength == 1){
					document.getElementById('dotsLi').style.display = "none";
				}
				
				//TouchSlide初始化后，vue上面的method无法生效，改用原生事件
				setBannerClickEvent();
	        })
	        .catch(function(error){
	        	alert(error);
	        })
		},
		//将数据分组排列
		reSortArr: function(num){
			var self = this;
			if(self.dataValue.length > num){
				self.newList.push(self.dataValue.splice(0,num));
        		self.reSortArr(num);
        	}else{
        		self.dataValue.length = num;
        		self.newList.push(self.dataValue);
        	}
		},
	}
});

