/* *
 * *weijc
 * *20170829
 * */

//判断安卓ios系统
var u = navigator.userAgent, app = navigator.appVersion;
var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端

var isfromApp = location.search.indexOf('jfqapp') > -1;

var $itemWrapBox = $('.item-list-box');
var $storeItemSingle = $('.store-item.dsn');
var $searchInput = $('#storeSearch');
var $loading = $('.loading.hide');
var curPtData = null;

$(function(){
	new FastClick(document.body);
	
	//读取缓存数据，恢复数据，恢复滚动条位置
	if(sessionStorage.searchData && location.search.indexOf('jfqstore') > -1){
		
		history.replaceState({}, '',  location.href.replace('&jfqsource=jfqstore', ''));
		$searchInput.focus();
		$searchInput.click();
		
	} else if(sessionStorage.searchData && location.search.indexOf('jfqstore') === -1){
		
		$searchInput.val(sessionStorage.searchKey);
		$itemWrapBox.find('.store-item').remove();
		$loading.clone(true).addClass('newLoading').removeClass('hide').prependTo($itemWrapBox);

		parseItemData(JSON.parse(sessionStorage.searchData));
		$('.newLoading').remove();
		
		document.body.scrollTop = sessionStorage.scrollTop;
		
	}
	if(!sessionStorage.searchData){
		$searchInput.focus();
		$searchInput.click();
	}
	
	$(document).on('click', '.item-link', function(){
		sessionStorage.scrollTop = document.body.scrollTop;
	});

	//苹果手机输入法，选中中文时无法触发keyup事件，因此换成点击按钮触发搜索
	$('.btn-search').click(function(event){
		//触发搜索，将搜索框滚动至顶部
		var currentSearchInputVal = $.trim($searchInput.val());
		
		//查询小区
		if(currentSearchInputVal !== ""){
			inputSearch(currentSearchInputVal);
		}
	});
	//键盘回车或输入法搜索按键触发
	$searchInput.on('keyup', function(event){
		var myEvent = event || window.event;
		currentVal = $.trim($searchInput.val());
		
		if(myEvent.keyCode === 13){
			inputSearch(currentVal);
		}
	});
	/*if(isAndroid){
		//时间戳+定时器，避免搜索时频繁触发请求
		var lastTimeStamp = 0;
		$searchInput.keyup(function(event){
			var $this = $(this);
			
			//标记当前事件函数的时间戳
			lastTimeStamp = event.timeStamp;
			
			//触发搜索，将搜索框滚动至顶部
			currentSearchInputVal = $.trim($searchInput.val());
			
			////800ms后比较二者是否还相同（因为只要还有事件触发，lastTimeStamp就会被改写，不再是当前事件函数的时间戳）
			setTimeout(function(){
				if(lastTimeStamp == event.timeStamp){
					//查询小区
					if(currentSearchInputVal !== ""){
						inputSearch('#storeSearch', currentSearchInputVal);
					}
				}
			},800);
		});
	}*/
	
	if(isfromApp){
		//安卓
		if(isAndroid){
			//进入商品详情
			$(document).on('click', '.item-link', function(e){
				e.preventDefault();
				var itemId = $(this).attr('data-itemid');
				window.itemSearch.singleItemBtn(itemId);
			});
			
			//取消搜索
			$(document).on('click', '#goBackBtn', function(){
				window.itemSearch.goBackToStore();
			});
		}

		//ios
		if(isiOS){
			//fixed搜索框在ios手机弹出输入法时位移问题
			/*$('.search-box').addClass('iosfixed');
			$('.search-list').addClass('iosfixed');*/
			
			//进入商品详情
			$(document).on('click', '.item-link', function(e){
				e.preventDefault();
				var itemId = $(this).attr('data-itemid');
				
				setupWebViewJavascriptBridge(function(bridge) {
					bridge.callHandler('singleItemBtn', {'itemId': itemId}, function(response) {});
				});
			});
			
			//取消搜索
			$(document).on('click', '#goBackBtn', function(){
				setupWebViewJavascriptBridge(function(bridge) {
					bridge.callHandler('goBackToStore', {}, function(response) {});
				});
			});
		}
	}else{
		//取消搜索
		$(document).on('click', '#goBackBtn', function(){
			window.history.back(-1);
		});
	}
	
});

//查询
function inputSearch(searchKey){
	$.ajax({
		type:"get",
		url:"../common/toUrl.do",
		data:{page:1, pageNum:30, searchKey: searchKey, supplyType: 2, storeId: getUrlParam('storeId'), detailUrl: '/ebuyV2/qryProdList4ExperienceStore.json'},
		dataType:"json",
		beforeSend:function(data){
			if($('.newLoading').length == 0){
				$loading.clone(true).addClass('newLoading').removeClass('hide').prependTo($itemWrapBox);
			}else{
				$('.newLoading').remove();
				$loading.clone(true).addClass('newLoading').removeClass('hide').prependTo($itemWrapBox);
			}
		},
		success:function(data){
			
			$itemWrapBox.find('.store-item').remove();
			
			if(data.dataValue.productList.length === 0){ 
				$('.newLoading').html('暂无此类商品信息');
				return;
			}

			sessionStorage.searchKey = searchKey;
			sessionStorage.searchData = JSON.stringify(data.dataValue.productList);
			
			parseItemData(data.dataValue.productList);
			
			$('.newLoading').remove();
		}
	});
}

//解析数据
function parseItemData(itemData){
	
	$.each(itemData,function(iPro,dataPro){
		
		var $storeItemSingleClone = $storeItemSingle.clone(true);
		var curUrl = dataPro.picBase;
		if(curUrl){
			var newImgUrl = curUrl + (curUrl.indexOf('?') > -1 ? '&':'?') + 'x-oss-process=image/resize,m_fill,w_210,h_210,limit_0/format,jpg/interlace,1/quality,q_95';
		}
		
		$storeItemSingleClone.find('.bgloading').attr('data-original', newImgUrl);
		$storeItemSingleClone.find('.store-item-name').text(dataPro.name);
		$storeItemSingleClone.find('.store-item-desc').text(dataPro.desc);
		$storeItemSingleClone.find('.store-item-price').text(dataPro.priceOnShelf.toFixed(2));
		$storeItemSingleClone.find('.market-price').text(dataPro.price.toFixed(2));
		$storeItemSingleClone.find('.item-link').attr('href', '../product/productDetail.do?jfqsource=jfqstore&ptId=' + dataPro.id);
		$storeItemSingleClone.find('.item-link').attr('data-itemid', dataPro.id);
		$storeItemSingleClone.find('.store-item-img').attr('prdtId', dataPro.id);
		$storeItemSingleClone.find('.join-btn').attr('leftCount', dataPro.leftCount);
		
		if(dataPro.sosName){
			$storeItemSingleClone.find('.store-supplier').removeClass('dsn').find('.supplier-name').text(dataPro.sosName).attr('href');
		}
		if(dataPro.sosIntroduceUrl){
			$storeItemSingleClone.find('.supplier-name').removeClass('grey').addClass('blue').attr('href', dataPro.sosIntroduceUrl);
		}
		
		$storeItemSingleClone.removeClass('dsn').insertBefore($('.newLoading'));
		
		$storeItemSingleClone.find('img.bgloading').lazyload(
				{effect: "fadeIn"}
		);
		
	});
	
}