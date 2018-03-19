$(function(){
	new FastClick(document.body);

	//回到顶部
	$(window).scroll(function(){
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop;
		if( scrollt > 100 ){
			$("#gotop").removeClass('dsn');
		}else{
			$("#gotop").addClass('dsn');
		}
	});
	$("#gotop").click(function(){
		$("html,body").animate({scrollTop:"0px"},200);
	});
})

var isWeixin = location.search.indexOf('weixin') > -1;
var appType;

if(isWeixin){
	appType = '3';
}else{
	appType = '1';
}

//app分享交互方法
var appShareFuncNew;

//页面详情
var serverDetailsVM = new Vue({
	el: '#container',
	data: {
		productDetail: null,	//商品详情
		dredgeTypeId: '',	//一级分类id
		subTypeId: '',	//二级分类id
		share: {},	//分享信息
		themeAdDesc: '',	//活动说明字段
		tipsShow: true,	//是否显示提示框
		standardShow: false,	//是否显示规格
		standardSelectedList: [],	//规格列表
		hasServerItems: false,	//是否显示耗材入口
		itemSelectedList: [],	//已选耗材列表
		itemSelectedNum: 0,	//已选耗材数量
		
		productSpecList: null,
		standardShow: false,
		itemNum: function(){
			return 0;
	},
		totalCounter: 1
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
	        	params: {"dredgeProductId": getUrlParam('productId'), "detailUrl": "/dredge/qryDredgeProductDetail.json"}
	        })
	        .then(function(response){
	        	var dataList = response.data.dataValue.productDetail;
	        	dataList.productSpecList.forEach(function(value, index, arr){
	        		if(index === 0){
	        			value.counter = 1;
	        		}else{
	        			value.counter = 0;
	        		}
	        		value.sellPrice = ((value.sellPrice)/100).toFixed('2');
	        		value.marketPrice = ((value.marketPrice)/100).toFixed('2');
	        	})
	        	_self.productDetail = dataList;
	        	_self.share = response.data.dataValue.share;
	        	_self.themeAdDesc = response.data.dataValue.themeAdDesc;
	        	_self.share.url = response.data.dataValue.share.url + '&jfqsource=weixin';
	        	_self.dredgeTypeId = response.data.dataValue.dredgeTypeId;
	        	_self.subTypeId = response.data.dataValue.subTypeId;
	        	
	        	//插入已选规格(默认第一个)
	        	_self.standardSelectedList.push(dataList.productSpecList[0]);
	        	
	        	//插入规格列表
	        	standardsVM.productSpecList = _self.productDetail.productSpecList; 
	        	_self.productSpecList = _self.productDetail.productSpecList; 
	        	
	        	//插入商品类型(1：前付款；2：后付款)
	        	payBtnBoxVM.payType = _self.productDetail.payType;
	        })
	        .then(function(){

				//计算已选耗材及规格总金额
	        	_self.calcTotalPrice();
			
				//banner轮播图
			    TouchSlide({ 
			        slideCell:"#slideBox",
			        titCell:"#slideDot ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
			        mainCell:"#slidePic ul", 
			        effect:"left", 
			        autoPage:true,//自动分页
			        autoPlay:true //自动播放
			    });
			
			    //判断banner圆点是否显示
			    var liLength = document.getElementById("slideLi").children.length;
				if(liLength === 1){
					document.getElementById("slideDot").style.display = 'none';
				}
				
				//渐进式加载图片，图文混排时有点问题
			    (function(){
					new Progressive({
						el: '#container',
						lazyClass: 'lazy',
						removePreview: true
					}).fire()
			    })()
			    
			    //轻应用分享
				setShareInfo({
					title: _self.share.cycleTitle,		// 分享标题
					friendTitle: _self.share.friendTitle,		// 分享标题
					desc: _self.share.desc,		// 分享描述
					link: _self.share.url,	// 分享链接
					imgToFriend: _self.share.sharePic + '?x-oss-process=image/resize,m_fill,w_120,h_120/format,jpg/quality,q_90/interlace,1',	// 分享图标
					imgToQuan: _self.share.sharePic + '?x-oss-process=image/resize,m_fill,w_120,h_120/format,jpg/quality,q_90/interlace,1'	// 分享图标
				});
			    
			    var shareInfo = _self.share;
			    appShareFuncNew = appShareFn(shareInfo);

				//查询是否有耗材
			    serverItemsVM.getAjax(appType);
			    
	        }).catch(function(error){
	        	showMsg(error);
	        })
		},
		//显示规格
		toggleStandard: function() {
			standardsVM.standardShow = !standardsVM.standardShow;
			this.tipsShow = false;
			$('body').toggleClass('overhidden');
		},
		//显示耗材
		toggleServerItems: function() {
			var _self=this;
			serverItemsVM.serverItemsShow = !serverItemsVM.serverItemsShow;
			$('body').toggleClass('overhidden');
		},
		//计算预付金额总额
		calcTotalPrice: function() {
			var _self = this;
			var total = 0;
			//已选规格总金额
			_self.standardSelectedList.filter(function(val) {
				if(val.counter > 0) {
					total += val.sellPrice*val.counter;
				}
			})
			//已选耗材总金额
			_self.itemSelectedList.filter(function(val) {
				if(val.counter > 0) {
					total += val.price*val.counter;
				}
			})
			payBtnBoxVM.totalPrice = total.toFixed('2');
		},
		//计算已选规格总数量
		counterComputed: function(num){
			var _self = this;
			_self.productSpecList.forEach(function(value, index, arr){
				num += value.counter;
			})
			return num;
		},
		counterAdd: function(item) {
			var _self = this;
			var totalCounter = 0;
			item.counter += 1;
			
			totalCounter = _self.counterComputed(totalCounter);
			this.totalCounter = totalCounter;
			//计算已选耗材及规格总金额
			serverDetailsVM.calcTotalPrice();
		},
		counterReduce: function(item) {
			//计算选择已选规格总数，至少选择一个规格
			var _self = this;
			var totalCounter = 0;
			
			totalCounter = _self.counterComputed(totalCounter);
			
			if(totalCounter > 1 && item.counter > 0) {
				item.counter -= 1;
				totalCounter -= 1;
			}else if(totalCounter === 1){
				showMsg('至少选择一个规格');
		}
			
			this.totalCounter = totalCounter;
			//计算已选耗材及规格总金额
			serverDetailsVM.calcTotalPrice();
		}
	},
	watch: {
		itemSelectedList: function() {
			//计算已选择耗材的数量
			var _self = this;
			var totalCounter = 0;
			_self.itemSelectedList.filter(function(val) {
				if(val.counter > 0) {
					totalCounter += val.counter;
				}
			})
			this.itemSelectedNum = totalCounter;
		}
	},
	/*computed: {
		itemSelectedNum: function() {
			//计算已选择耗材的数量
			var _self = this;
			var totalCounter = 0;
			if(_self.itemSelectedList.length === 0){
				return;
			}
			_self.itemSelectedList.filter(function(val) {
				if(val.counter > 0) {
					totalCounter += val.counter;
				}
			})
			return totalCounter;
		}
	}*/
});

//规格列表
var standardsVM = new Vue({
	el: '#standardsBox',
	data: {
		productSpecList: null,
		standardShow: false,
		itemNum: function(){
			return 0;
		},
		totalCounter: 1
	},
	mounted: function(){
		var _self = this;
	},
	methods: {
		cancelStandard: function(){
			var _self = this;
			_self.standardShow = !_self.standardShow;
			$('body').toggleClass('overhidden');
		},
		checkStandard: function(){
			var _self = this;
			_self.standardShow = !_self.standardShow;
			$('body').toggleClass('overhidden');
			
			//将选中的规格插入到页面
			serverDetailsVM.standardSelectedList = [];
			_self.productSpecList.forEach(function(value, index, arr){
				if(value.counter > 0){
					serverDetailsVM.standardSelectedList.push(value);
				}
			})
			
			//计算已选耗材及规格总金额
			serverDetailsVM.calcTotalPrice();
			
		},
		//计算已选规格总数量
		counterComputed: function(num){
			var _self = this;
			_self.productSpecList.forEach(function(value, index, arr){
				num += value.counter;
			})
			return num;
		},
		counterAdd: function(item) {
			var _self = this;
			var totalCounter = 0;
			item.counter += 1;
			
			totalCounter = _self.counterComputed(totalCounter);
			this.totalCounter = totalCounter;
		},
		counterReduce: function(item) {
			//计算选择已选规格总数，至少选择一个规格
			var _self = this;
			var totalCounter = 0;
			
			totalCounter = _self.counterComputed(totalCounter);
			
			if(totalCounter > 1 && item.counter > 0) {
				item.counter -= 1;
				totalCounter -= 1;
			}else if(totalCounter === 1){
				showMsg('至少选择一个规格');
			}
			
			this.totalCounter = totalCounter;
		}
	}
});

//耗材列表
var serverItemsVM = new Vue({
	el: '#serverItemsBox',
	data: {
		serverItemsList: null,
		serverItemsShow: false,
		serverItemsSelectedList: [],
		hasNext: true,
		hasNoItems: false,
		isLoading: true,
		isScrolling: true,
		page: 1,
		itemProductHost: getCurHref('api')	//耗材商品链接到api
	},
	mounted: function(){
		var _self = this;
		
	    window.onscroll = function() {
	    	if(_self.serverItemsShow){
	    		_self.loadNextPage();
	    	}
		}
	},
	methods: {
		getAjax: function(appType){
			var _self=this;
			var url = "../common/toUrl.do";

	        if(!_self.hasNext){
	        	return false;
	        }
	        axios.get(url, {
	        	params: {"detailUrl":"/dredge/qryProductList.json", "page":_self.page, "pageNum":"10", "dredgeTypeId":serverDetailsVM.dredgeTypeId, "subTypeId":serverDetailsVM.subTypeId, "appType":appType}
	        })
	        .then(function(response){
	        	var dataList = response.data.dataValue;
	        	
	        	//有耗材，显示耗材入口
	        	if(dataList.count == 0){
	        		return;
	        	}else{
	        		serverDetailsVM.hasServerItems = true;
	        	}
	        	
	        	dataList.list.forEach(function(value, index, arr){
        			value.counter = 0;
	        	})
	        	_self.serverItemsList = dataList;
	        	
	        	_self.hasNext = dataList.hasNext;
	        	_self.isLoading = false;
	        	_self.page += 1;
	        	
	        	if(dataList.count === 0){
	        		_self.hasNoItems = true;
	        	}
	        }).catch(function(error){
	        	showMsg(error);
	        })
		},
		itemCounterAdd: function(item) {
			item.counter += 1;
		},
		itemCounterReduce: function(item) {
			if(item.counter > 0) {
				item.counter -= 1;
			}
		},
		onlyNumber: function(item, val) {
			var partten = /^[0-9]\d*$/;
			if(!partten.test(val) && val !== '') {
				item.counter = 1;
			}
		},
		noBlank: function(item, val){
			if(val === '') {
				item.counter = 1;
			}
		},
		cancelSelect: function(){
			var _self = this;
			_self.serverItemsShow = !_self.serverItemsShow;
		},
		checkItemSelected: function(){
			var _self = this;
			_self.serverItemsShow = !_self.serverItemsShow;
			$('body').toggleClass('overhidden');
			
			//将选中的耗材插入到页面
			serverDetailsVM.itemSelectedList = [];
			_self.serverItemsList.list.forEach(function(value, index, arr){
				if(value.counter > 0){
					serverDetailsVM.itemSelectedList.push(value);
				}
			})
			//计算已选耗材及规格总金额
			serverDetailsVM.calcTotalPrice();
		},
		scrollEnd: function() {
			var _self = this;
			
			_self.isScrolling = true;
			
			if(_self.hasNext){
				_self.isLoading = true;
			}
	
			var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
			var docHeight = document.body.scrollHeight - document.body.clientHeight - 1; //当前文档高度-当前窗口高度-1
	
			//页面加载完成时，不触发
			if(scrollt === 0) {
				return false;
			}
	
			if(scrollt > docHeight) {
				_self.getAjax(appType);
			}
		},
		loadNextPage: function() {
			var _self = this;
			
			if(_self.isScrolling) {
				setTimeout(_self.scrollEnd, 1000);
				_self.isScrolling = false;
			}
		}
	},
	/*watch: {
		itemSelectedList: function() {
			//计算选择耗材总数
			var _self = this;
			var totalCounter = 0;
			serverItemsVM.serverItemsList.list.filter(function(val) {
				if(val.counter > 0) {
					totalCounter += val.counter;
				}
			})
			this.itemSelectedInfo = totalCounter;
		}
	},*/
	computed: {
		itemSelectedInfo: function() {
			//计算选择耗材总数
			var _self = this;
			var totalCounter = 0;
			serverItemsVM.serverItemsList.list.filter(function(val) {
				if(val.counter > 0) {
					totalCounter += val.counter;
				}
			})
			return totalCounter;
		}
	}
});

//预付按钮
var payBtnBoxVM = new Vue({
	el: '#payBtnBox',
	data: {
		totalPrice: 0,
		payType: null
	},
	methods: {
		payThisBill: function(){
			//采集商品id，已选规格、耗材列表
			var productInfo = {};
			var productId = getUrlParam('productId');
			productInfo.dredgeProductId = Number(productId.replace(/"/g, ''));
			
			productInfo.specList = [];
			serverDetailsVM.standardSelectedList.forEach(function(value, index, arr){
				var singleItemInfo = {};
				singleItemInfo.specId = value.id;
				singleItemInfo.specNum = value.counter;
				productInfo.specList.push(singleItemInfo);
			})
			
			productInfo.productList = [];
			serverDetailsVM.itemSelectedList.forEach(function(value, index, arr){
				var singleItemInfo = {};
				singleItemInfo.productId = value.id;
				singleItemInfo.productQty = value.counter;
				productInfo.productList.push(singleItemInfo);
			})
			
			if(isWeixin){
				localStorage.setItem('productInfo', JSON.stringify(productInfo));
				location.href = 'maintainYuyue.do?dredgeTypeId=' + serverDetailsVM.dredgeTypeId + '&subTypeId=' + serverDetailsVM.subTypeId + '&appType=' + appType;
			}else{
				if(isAndroid){
					window.homeServerDate.serverProductSelected(JSON.stringify(productInfo));
				}
				if(isiOS){
					setupWebViewJavascriptBridge(function(bridge) {
						bridge.callHandler('serverProductSelected', productInfo, function(response) {});
					});
				}
			}
		}
	}
});
