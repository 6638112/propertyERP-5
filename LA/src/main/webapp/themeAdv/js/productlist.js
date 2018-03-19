/**
 * Created by apple on 2017/7/17.
 */

var advId = getUrlParam('advId');
var data = {"detailUrl" : "/themeAdv/productList.json", "advId":advId};

if(location.search.indexOf('weixin') > -1){
    //轻应用来
    data = {"detailUrl" : "/themeAdv/productList.json", "advId":advId, "from":"LA"};

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
}

if(location.href.indexOf('showAd=0') > -1){
	$('.showAd').addClass('dsn');
}

$.ajax({
    url: "../common/toUrl.do",
    dataType:"json",
    data:data,
    success: function(data){
        if(data.status === '0000'){
            var adv = data.dataValue.adv;
            
            if(!adv){
            	alert('暂无该类商品信息');
            }

            if(adv.advTitle !== undefined){
                document.title = adv.advTitle;

				//ios客户端下，触发事件重新获取标题
				if(location.search.indexOf('weixin') === -1 && isiOS){
					setupWebViewJavascriptBridge(function(bridge) {
						bridge.callHandler('resetTitle', {}, function(response) {});
					});
				}
            }
            
            var advPic = adv.advPic + (adv.advPic.indexOf('?') > -1 ? '&':'?') + 'x-oss-process=image/resize,m_fill,w_640,h_299/format,jpg/quality,q_95/interlace,1';
            $('#advPic').attr('src',advPic);

            if(adv.ebuyProductList.length > 0){
                //社区店商品信息
                $('#shoppingCart').removeClass('dsn');//购物车
                var products = adv.ebuyProductList;
                var $probox = $('#probox');
                var $productUl = $('.item-list').removeClass('dsn');
                var imgAppend = '?x-oss-process=image/resize,m_fill,w_263,h_263,limit_0/format,jpg/interlace,1/quality,q_90';

                var ulcount = products.length%2==0?Math.floor(products.length/2):Math.floor(products.length/2)+1;

                for(var i=0; i< ulcount; i++){
                    var $productUlClone = $productUl.clone(true);
                    $probox.append($productUlClone);

                    var product01 = products[i*2];
                    var $itemli01 = $productUlClone.find('.pro-item01').removeClass('dsn');
                    $itemli01.find('.single-item-btn').attr('data-id',product01.id).addClass('ebuyProductList');
                    var proimg = product01.picBase+imgAppend;
                    $itemli01.find('.bgloading').attr('src',proimg);
                    $itemli01.find('.item-name').text(product01.name);
                    $itemli01.find('.item-priceOnShelf').text(product01.priceOnShelf.toFixed(2));
                    $itemli01.find('.market-price-small').text(product01.price.toFixed(2));
                    $itemli01.find('.join-shoppingCart-btn').attr('data-id',product01.id);

                    if(i*2+1<products.length){
                        var product02 = products[i*2+1];
                        var $itemli02 = $productUlClone.find('.pro-item02').removeClass('dsn');
                        $itemli02.find('.single-item-btn').attr('data-id',product02.id).addClass('ebuyProductList');
                        var proimg = product02.picBase+imgAppend;
                        $itemli02.find('.bgloading').attr('src',proimg);
                        $itemli02.find('.item-name').text(product02.name);
                        $itemli02.find('.item-priceOnShelf').text(product02.priceOnShelf.toFixed(2));
                        $itemli02.find('.market-price-small').text(product02.price.toFixed(2));
                        $itemli02.find('.join-shoppingCart-btn').attr('data-id',product02.id);

                        $productUlClone.find('.emptyli').remove();
                    }else {
                        $productUlClone.find('.pro-item02').remove();
                        $productUlClone.find('.emptyli').removeClass('dsn');
                    }
                }
            }else if(adv.dredgeProductList.length > 0){
                var products = adv.dredgeProductList;
                var $probox = $('#probox');
                var $productUl = $('.item-list').removeClass('dsn');
                var imgAppend = '?x-oss-process=image/resize,m_fill,w_263,h_263,limit_0/format,jpg/interlace,1/quality,q_90';

                var ulcount = products.length%2==0?Math.floor(products.length/2):Math.floor(products.length/2)+1;

                for(var i=0; i< ulcount; i++){
                    var $productUlClone = $productUl.clone(true);
                    $probox.append($productUlClone);

                    var product01 = products[i*2];
                    var $itemli01 = $productUlClone.find('.pro-item01').removeClass('dsn');
                    $itemli01.find('.single-item-btn').attr('data-id',product01.id).addClass('dredgeProductList');
                    if (product01.productPic.length > 0){
                        var proimg = product01.productPicList[0]+imgAppend;
                        $itemli01.find('.bgloading').attr('src',proimg);
                    }
                    $itemli01.find('.bgloading').attr('src',proimg);
                    $itemli01.find('.item-name').text(product01.name);
                    $itemli01.find('.item-priceOnShelf').text(product01.sellPriceDecimal.toFixed(2));
                    $itemli01.find('.market-price-small').text(product01.marketPriceDecimal.toFixed(2));
                    $itemli01.find('.join-shoppingCart-btn').addClass('dsn');

                    if(i*2+1<products.length){
                        var product02 = products[i*2+1];
                        var $itemli02 = $productUlClone.find('.pro-item02').removeClass('dsn');
                        $itemli02.find('.single-item-btn').attr('data-id',product02.id).addClass('dredgeProductList');
                        if (product02.productPic.length > 0){
                            var proimg = product02.productPicList[0]+imgAppend;
                            $itemli02.find('.bgloading').attr('src',proimg);
                        }
                        $itemli02.find('.item-name').text(product02.name);
                        $itemli02.find('.item-priceOnShelf').text(product02.sellPriceDecimal.toFixed(2));
                        $itemli02.find('.market-price-small').text(product02.marketPriceDecimal.toFixed(2));
                        $itemli02.find('.join-shoppingCart-btn').addClass('dsn');

                        $productUlClone.find('.emptyli').remove();
                    }else {
                        $productUlClone.find('.emptyli').removeClass('dsn');
                        $productUlClone.find('.pro-item02').remove();
                    }
                }
            }


        }
    },
    error: function(){
        alert('网络不给力，请稍后重试');
    }
});
