/**
 * Created by apple on 2017/11/7.
 */

function setShareInfo(){
    var curHost = getCurHref('www');
    var shareInfo = {
        title: '解放区优选体验店',// 分享标题
        desc: '解放区提供便利快捷物业服务、供港高品生鲜，到店免费体验还有优惠~',// 分享描述
        link:  '',// 分享链接
        imgIcon: getCurHref('resource') + '/docs/commonimages/jfqlogo_share.png' // 分享图标
    };

    //必须是当前链接参与签名才可以，因为用户可能会二次分享，微信会自带参数
     shareInfo.link = location.href.split('#')[0];

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
        jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    wx.ready(function(){
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
        //分享朋友圈
        wx.onMenuShareTimeline({
            title: shareInfo.title, // 分享标题
            link: curHost + '/LA/product/viewStore.do?storeId='+ getCurHostStoreId(curHost), // 分享链接
            imgUrl: shareInfo.imgIcon, // 分享图标
            success:function (res) {
                getShareCoupons();
            }
        });

        //发送给朋友
        wx.onMenuShareAppMessage({
            title: shareInfo.title, // 分享标题
            desc: shareInfo.desc, // 分享描述
            link: curHost + '/LA/product/viewStore.do?storeId='+ getCurHostStoreId(curHost), // 分享链接
            imgUrl: shareInfo.imgIcon, // 分享图标
            success:function (res) {
                getShareCoupons();
            }
        });
    });
}

function getShareCoupons() {
    $('#wrapBox').removeClass('heightp100');
    $('.pop-box02').addClass('dsn');
    var $shareCoupon = $('.share_coupon');

    $.ajax({
        type:"get",
        url:"../common/toUrl.do",
        data:{detailUrl: '/coupon/receiveShareOrderCoupon.json', orderId:$('[name=orderId]').val(),isNeedLogin:"1"},
        dataType:"json",
        success: function(data){
            if (data.status == '0000' ) {
                if(data.dataValue.couponList.length > 0){
                    $shareCoupon.removeClass('dsn');
                    var $record = $shareCoupon.find('.record-list-bg');
                    var $couponInfo = $('.coupon_info');
                    var couponCount = data.dataValue.couponList.length;
                    $shareCoupon.find('.coupon_count').text('本次共获得'+couponCount+'张消费券');
                    if(couponCount > 2){
                        couponCount = 2;
                    }
                    var $lastcouponInfo = null;
                    $record.empty();
                    for (var i= 0;i< couponCount ;i++){
                        var coupon = data.dataValue.couponList[i];
                        var $couponInfoClone = $couponInfo.clone().removeClass('dsn');
                        $record.append($couponInfoClone);
                        $couponInfoClone.find('.coupon_discountmoney').text(coupon.discountMoney);
                        $couponInfoClone.find('.coupon_name').text(coupon.couponName);
                        if(coupon.leastSpendUse > 0){
                            $couponInfoClone.find('.coupon_desc').text('满'+coupon.leastSpendUse+'元可用');
                        }else {
                            $couponInfoClone.find('.coupon_desc').text('任意金额可用');
                        }
                        $couponInfoClone.find('.coupon_enddate').text('有效期至 '+coupon.useEndDate);

                        $couponInfoClone.addClass('borderbottomgrey');
                        $lastcouponInfo=$couponInfoClone;
                    }
                    $lastcouponInfo.removeClass('borderbottomgrey');
                }
            }else{
                $.Showmsg(data.message);
            }
        },
        error: function(){
            $.Showmsg('网络不给力，请稍后重试');
        }
    });
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

//判断获取当前域名下的体验店id
function getCurHostStoreId(host){
    if(host.indexOf('linlile.com.cn') > -1){
        curHostStoreId = '10012278';
    }else if(host.indexOf('linlile.cn') > -1){
        curHostStoreId = '110211';
    }else if(host.indexOf('jiefangqu.com') > -1){
        curHostStoreId = '110214';
    }else{
        curHostStoreId = '10012278';
    }
    return curHostStoreId;
}