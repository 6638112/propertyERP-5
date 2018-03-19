/*weijc 20170613*/
var listVM = new Vue({
	el: '#container',
	data: {
		list: [],
		hasNext: true,
		isLoading: true,
		isScrolling: true,
		noUrlParam: false,
		isPrevShow: true,
		page: 1,
		isLogin: false,
		tipsShow: false,
		mobile: ''
	},
	mounted: function(){
		var _self = this;
		_self.getAjax();
	},
	methods: {
		getAjax: function(){
	        var _self=this;
			var url = "index.json?page=1";
	        
	        if(!_self.hasNext){
	        	return false;
	        }
	        
	       axios.get(url).then(function(response){
	       	
	        	_self.isPrevShow = false;
	        	
	        	_self.isLogin = response.data.dataValue.isLogin;
	        	_self.mobile = response.data.dataValue.mobile;
	        	_self.list = response.data.dataValue.loanEntityList;
	        	
	        });
		},
		jumpEvent: function(item){
			var _self = this;
			
			//团贷网，直接跳转
			if(item.code === 'TUAN_DAI_WANG'){
				location.href = 'goThirdPage.json?lpId=' + item.lpId;
				
			//花易借，需要登录
			}else if(_self.isLogin){
				
				if(_self.mobile){
					location.href = 'goThirdPage.json?lpId=' + item.lpId;
				}else if(!_self.mobile){
					
					//提示需要手机号
		        	_self.tipsShow = true;
		        	
		        	setTimeout(function(){
		        		_self.tipsShow = false;
		        	},2000);
		        	
				}
			
			//没有登录，则跳转到登录
			}else if(!_self.isLogin){
				//判断安卓ios系统
				var u = navigator.userAgent, app = navigator.appVersion;
				var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
				var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
				
				if(isAndroid){
					window.itemList.jumpToLoginPage();
				}
				
				if(isiOS){
					document.location='jfq://'+'jumpToLoginPage';
				}
			}
			
		}
	}
})

function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}