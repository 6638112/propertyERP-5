<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>确认订单</title>
<link rel="stylesheet" href="../ebuyFight/css/groupbuying.css">
</head>

<body class="bggrey heightp100">
<form class="inputform heightp100">
<section class="main-part01 heightp100">	
	<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100">
		<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
			<section class="sectionBox">
			    <ul class="item-price f16 bggrey ptb1210">
			    	<a class="f14" href="../ebuyFight/groupBuyingDesc.jsp">
				        <li class="left"><img class="wp60" src="../ebuyFight/images/pgsm.png" /></li>
				        <li class="right blue mtop2">详情</li>
			        </a>
			    </ul>
			    <section class="sectionBox addressInfo">
			        <ul class="p1510 f14">
			            <a id="setAddressBtn" class="white">
			            	<li class="opacity50">收货信息</li>
			            	<li><img class="right mtop10" src="../ebuyFight/images/arrow-right-white.png" /><span class="mright10 group-name">${userName}</span> <span class="group-phone-num">${userPhone}</span></li>
			            	<li>${fightProduct.zitiDian.name}(${fightProduct.zitiDian.delivAddress})</li>
			            </a>
			        </ul>
			    </section>
			    <section class="divide-box bordertop borderbottom"></section>
			   	<input id="fightProdcutId" class="productId" type="hidden" name="productId" value="${fightProduct.fightProductId}">
			    <section class="sectionBox payList borderbottomgrey">
			       <div class="pay-list-info mleft10">
			           <h3 class="item-sort">${fightProduct.merchantName.name}</h3>
			           <div class="info ptb10 borderbottomgrey">
			                <div class="tbl">
			                    <span class="item-img-small01 inline-table"><img src="${picserverUrl}${fightProduct.picBaseMini}" /></span>
			                    <span class="item-info tbl-cell">
			                        <div class="f14">${fightProduct.name}</div>
			                        <div class="message">${fightProduct.desc}</div>
			                    </span>
			                    <span class="price-num tbl-cell">
			                        <span class="f14">￥${fightProduct.fightPrice/100}</span><br/><span class="grey">x${productNum}</span>
			                    </span>
			                </div>
			            </div>
			            <ul class="item-price borderbottomgrey">
			                <li class="left grey">自提</li>
			                <li class="right">运费：￥0</li>
			            </ul>
			            <ul class="item-price">
			                <li class="left">共 1 件商品</li>
			                <li class="right">合计：<span class="red bold f16">￥${fightProduct.fightPrice/100 * productNum}</span></li>
			            </ul>
			        </div>
			    </section> 
			    
			    <c:if test="${containsCoupon }">
				    <section class="divide-box borderbottomgrey"></section>
				    <section class="sectionBox borderbottomgrey">
				        <ul class="user-menu-list m0">
				            <li>
				                <div class="left mtop5"><label id="allCheckBox" class="item-check-box single-check item-checked"></label ></div>
				                <div class="prize-list">
				                <span class="right red">
							    	<span>￥<span id="couponSelectedVal">${coupons[0].discountMoney }</span></span>
				                	<img src="../images/arrow-right.png" />
				                </span> 
				                <span>消费券(${fn:length(coupons) })<c:if test="${!containsCoupon }"><span class="un-use bgred mtop3">无可用</span></c:if></span></div>
				            </li>
				        </ul>
				    </section>
			    </c:if>
			    
				<section class="divide-box borderbottomgrey"></section>
				<section class="sectionBox">
					<ul class="item-price noborder p10">
				        <li class="left grey">商品合计</li>
				        <li id="product-total" class="right">￥${fightProduct.fightPrice/100 * productNum}</li>
				    </ul>
					<ul class="item-price noborder p10">
				        <li class="left grey">消费券抵扣</li>
				        <input type="hidden" id="maxCouponAmount" value='${fightProduct.fightPrice/100 * productNum} '/>
				        <c:if test="${containsCoupon }">
				        	<li id="coupon-total" class="right">-￥${coupons[0].discountMoney }</li>
				        </c:if>
				        <c:if test="${!containsCoupon }">
				        	<li class="right">-￥0</li>
				        </c:if>
				        
				    </ul>
				</section>
				
			    <section class="divide-box pbfooter bordertopgrey"></section>
			    
			    <section class="sectionBox">
				    <div class="exchang-fixed displaybox">
				        <div class="boxflex01 bggradient p010"><span class="f12">实付金额：</span><span id="shouldPay" class="red bold f16">￥${fightProduct.fightPrice/100 * productNum}</span></div>
				        <div class="btn-check-pay bgred"><a id="btnCheckPay" class="white" href="javascript:void(0)">确认付款</a></div>
				    </div>
			    </section>
			</section>
		</section>
		
		<section class="sectionBox wrap-bg pop-box02 dsn">
			<div class="tips-box">
				<div class="t-center ptb30 borderbottomgrey">
					<div class="f16">请先完善收货地址哦</div>
				</div>
				<ul class="displaybox">
					<li id="setAddressNow" class="boxflex01 ptb10 t-center red borderleft pay-check-btn f16 lineheight36"><a class="disblock red" href="javascript:void(0)">立即填写</a></li>
				</ul>	
			</div>
		</section>
	</section>
</section>

<section class="main-part02 dsn">
	<section class="sectionBox bggrey pb48">
	    <section class="divide-box bordertbgrey"></section>
	    <ul class="register-list">
	        <li>
	            <div class="displaybox">
	                <div class="boxflex01 f16 grey"><input id="orderPersonName" class="input-text wp100 f16" type="text" placeholder="收货人" value="${userName}" maxlength="100" datatype="*" nullmsg="请填写收货人！" /></div>
	            </div>
	        </li>
	    </ul>
	    <section class="divide-box bordertbgrey"></section>
	    <ul class="register-list">
	        <li>
	            <div class="displaybox">
	                <div class="boxflex01 f16 grey"><input id="orderPersonPhoneNum" name="" class="input-text wp100 f16" type="text" placeholder="手机号码" value="${userPhone}" maxlength="11" datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码格式！" /></div>
	            </div>
	        </li>
	    </ul>
	    <section class="divide-box bordertbgrey"></section>
	    <div class="p010 bgwhite displaybox">
	        <div class="item-standard-name f16 boxflex01 ptb20">
				<span>${fightProduct.zitiDian.name}</span>
	        	<div class="f14 grey mtop10">${fightProduct.zitiDian.delivAddress}</div>
	        </div>
	    </div>
	    <section class="divide-box bordertopgrey"></section>
	    <ul class="bottom-menu-box displaybox t-center">
	    	<li class="p00"><input id="saveAddressBtn" class="btn-submit bgred noradius noborder height48" type="submit" name="submit" id="toPayForm" value="保存" /></li>
	    </ul>
	</section>
</section>

<div class="prize-box dsn">
    <section class="sectionBox">
	    <header class="fantasia-header">
	        <div class="header-title">消费券</div>
	        <a id="prizeCheck" class="disblock p010 right" href="javascript:void(0)"><span  class="red">确定</span></a>
	    </header>
    </section>
    <section class="divide-box bordertop"></section>
    <section class="sectionBox">
        <ul class="item-price">
            <li class="left">订单金额</li>
            <li class="right"><span class="red bold f16">￥<span class="items-total">${fightProduct.fightPrice/100 * productNum }</span></span></li>
        </ul>
        <ul class="item-price">
            <li class="left">使用消费券抵扣</li>
            <li class="right">
            	<span class="blue bold f16">￥<span class="prize-total">0.00</span></span>
            </li>
        </ul>
    </section> 
    <section class="divide-box bordertopgrey"></section>
    <section class="sectionBox bggrey">
    	<c:if test="${!containsCoupon }">
    	<ul class="p10 bgwhite bordertbgrey">
            <li class="t-center">无可用消费券</li>
        </ul>
        </c:if>
        <c:forEach var="coupon" items="${coupons }">
        <div class="displaybox pos-relative prizeListBox">
            <div class="prize-check new-ticket-check">
	            <input id="${coupon.id }" name="couponIdList" type="radio" ${coupon.id == coupons[0].id ? "checked" :""} hidden="hidden" value="${coupon.discountMoney }" />
	            <label for="${coupon.id }" class="item-check-box single-check ${coupon.id == coupons[0].id ? "item-checked" :""}"></label >
            </div>
            <div class="record-list-bg bgred boxflex01">
	            <ul class="displaybox record-list p10 mleft5 noborderleft">
	                <li class="new-ticket-num">￥<span class="f30">${coupon.discountMoney }</span></li>
	                <li class="record-txt boxflex01 borderleft"><span class="f16">解放区现金券</span><div class="f12 grey mtop10">每笔订单限用1张，消费满60可用</div><div class="f12 grey">有效期至 ${coupon.useEndDate }</div></li>
	            </ul>
	        </div>
        </div>
        </c:forEach>
    </section>   
    <section class="divide-box pbfooter"></section>
</div>


</form>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="${resourcePathHttps}/commonjs/Validform_v5.3.2.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
		//表单验证，保存收货人信息
		$(".main-part02").Validform({
			tiptype:1,
			btnSubmit:"#saveAddressBtn",
			ajaxPost:true,
			beforeSubmit:function(){
				$('.main-part02').hide();
				
				$('.group-name').text($('#orderPersonName').val());
				$('.group-phone-num').text($('#orderPersonPhoneNum').val());
				
				$('#wrapBox').removeClass('heightp100');
				$('.pop-box02').addClass('dsn');
				$('.main-part01').show();
				return false;
			}
		});
		
		//确认付款按钮
		$(".main-part01").Validform({
			tiptype:1,
			btnSubmit:"#btnCheckPay",
			ajaxPost:true,
			postonce:true,
			beforeSubmit:function(){
				//全部用券支付，校验
				  var shouldPayAmt =  parseFloat($("#shouldPay").text().trim().substring(1));
				  
				  if(shouldPayAmt<0.000001){//100%用券支付成功
					   var couponSelectedVal = $('#couponSelectedVal').text(),
					       alertInfo = '确认使用 ￥' + couponSelectedVal + ' 消费券吗？';
			   		   if(!confirm(alertInfo)){
			   			   $("#toPayForm").Validform().resetStatus();
			       		   return false;
			   		   }
			   	  }
				  
				//如果收货人信息为空，则提示填写
				if($('.group-name').text() =='' || $('.group-phone-num').text() == ''){
					$('#wrapBox').addClass('heightp100');
					$('.pop-box02').removeClass('dsn');
				}else{
				//非空，提交
					  var ajaxURL= "../order/submitFightOrderMulti.do";  
					  var couponId = $('.new-ticket-check .item-check-box.single-check.item-checked').attr('for');
					  if(couponId == undefined){
						  couponId = "";
					  }
					  jQuery.ajax({
				            url: ajaxURL,//ajax调用微信统一接口获取prepayId
				            cache: false,
						    dataType:"json",
							async:false,
				            data:$('#toPayForm').serialize() + "&productId=" + $("#fightProdcutId").val() + "&userName=" + $('.group-name').text()+"&phone="+$('.group-phone-num').text()+"&productNum="+'${productNum}'+"&couponIdList="+"["+couponId+"]" + "&shouldPayAmt=" + shouldPayAmt,
				            success:function(data) {
						            
				                   var obj = jQuery.parseJSON(data);
				                   if(obj.status !=undefined && "0000"!=obj.status){//提交订单失败
				                	   $.Showmsg(obj.message);
				                   	   return;
				                   }
				                   
				            	   if(shouldPayAmt<0.000001){//100%用券支付成功
				            		   window.location.href = "../order/viewOrderDetail.do?orderId="+obj.orderId+"&isNeedSendMsg=1";
				            		   return false;
				            	   }
				            		
				            	   $.Showmsg("正在提交，请稍候");
				                   WeixinJSBridge.invoke('getBrandWCPayRequest',{  
				   	                "appId":obj.appId,                 //公众号名称，由商户传入  
				   	                "timeStamp":obj.timeStamp,          //时间戳，自 1970 年以来的秒数  
				   	                "nonceStr":obj.nonceStr,         //随机串  
				   	                "package":obj.package,      //prepay_id=*** 
				   	                "signType":obj.signType,        //微信签名方式:MD5  
				   	                "paySign":obj.paySign           //微信签名  
				   	                },function(res){      
				   	                    //alert(res.err_msg);  //get_brand_wcpay_request:ok
					   	                if(res.err_msg == "get_brand_wcpay_request:ok" ) {  
					   	                	//alert("付款成功，您可继续选购其它商品");
					   	                	window.location.href = "../order/viewOrderDetail.do?orderId="+obj.orderId+"&isNeedSendMsg=1";
					   	                    //window.location.href="../product/index.do";  
					   	                }else{  
					   	                	alert("付款失败，您可查看订单，继续支付");
					   	                	//alert("详细错误" + res.err_msg);
					   	                    window.location.href="../order/qryOrderList.do";     
					   	                   //当失败后，继续跳转该支付页面让用户可以继续付款，特别注意不能直接调转jsp，不然会报system:access_denied 
					   	                }  
				   	            });  
				            },
				            error: function(data) {
				            	// alert(data.responseText);
				                alert("确认付款失败，请稍后再试");
				            }
				      });
					  //按钮置灰
					  $('.btn-check-pay').removeClass('bgred').addClass('bgdarkgrey');
				}
				return false;
			}
		});
		
		//设置收货人信息
		$('#setAddressBtn, #setAddressNow').click(function(){
			$('.main-part01').hide();
			$('.main-part02').show();
		});
	});
	function toPay(){
		if($('span.address-fill').length > 0){
			//alert("请在页面顶部完善收货信息");
			$('#wrapBox').addClass('heightp100');
			$('.pop-box02').removeClass('dsn');
			return false;
		}
	}
	
	
	
	//弹出消费券
	$(function(){
		var $prizeBox = $('.prize-box');
		var $checkPayBox = $('.main-part01');
		var $doc = $(document);
		$('.prize-list').click(function(){
			$checkPayBox.hide();
			$prizeBox.fadeIn(100);
			$doc.scrollTop('0px');
		});
		$('#prizeCheck').click(function(){
			$prizeBox.hide();
			$checkPayBox.show();
			$doc.scrollTop($('#allCheckBox').offset().top);
		});
		
	});
	//单选
	//选择反选消费券
	$(function(){
		var $prizeListBox = $('.prizeListBox');
		var $allCheckBox = $('#allCheckBox');
		$allCheckBox.click(function(){
			var inputCheckedNum = 0;
			$(this).toggleClass('item-checked');

			$prizeListBox.each(function() {
				var $thisInput = $(this).find('input[name=couponIdList]');
				
				if( $thisInput.hasClass('toggleCheck') ){
					$thisInput.prop('checked', true).removeClass('toggleCheck').siblings('label').addClass('item-checked');	
					inputCheckedNum += 1;	
				}else if( $thisInput.is(":checked") ){
					$thisInput.addClass('toggleCheck').prop('checked', false).siblings('label').removeClass('item-checked');	
					inputCheckedNum += 1;	
				}
	        });
	        
	        if(inputCheckedNum == 0){
	        	$prizeListBox.eq(0).find('input[name=couponIdList]').prop('checked', true).siblings('label').addClass('item-checked');
	        }
	        
			prizeCount();
			//设置已选用消费券金额
			var thisHbamout = $('.item-check-box.single-check.item-checked').siblings('input[name=couponIdList]').val();
			thisHbamout = thisHbamout == undefined ? 0:thisHbamout;
			$('#couponSelectedVal').text(thisHbamout);
		});
		
		//按确定按钮
		$('#prizeCheck').click(function(){
			$prizeListBox.each(function() {
				$('input[name=couponIdList]', this).each(function () {
					if ( $('input[name=couponIdList]:checked').length ){
						$allCheckBox.addClass('item-checked');	
						$(this).removeClass('toggleCheck');
					}else{
						$allCheckBox.removeClass('item-checked');	
					}
				});
			});
		});
	});

	//选择消费券
	$(function(){
		prizeCount();
		
		$('.prizeListBox').click(function(){
			var $cBox = $(this).find('.item-check-box');
			if($cBox.hasClass('item-checked')){
				$cBox.siblings('input').prop('checked', false);
				$cBox.removeClass('item-checked');
			}else{
				//反选已选消费券
				$('.prizeListBox').find('.item-check-box').siblings('input').prop('checked', false);
				$('.prizeListBox').find('.item-check-box').removeClass('item-checked');
				//选中当前
				$cBox.siblings('input').prop('checked', true);
				$cBox.addClass('item-checked');
			}
			
			prizeCount();
		});	
	});


	//消费券叠加计算
/* 	function prizeCount() {
		var $prizeListBox = $('.prizeListBox');
		var conts = 0;
		$prizeListBox.each(function() {
			$('input[name=couponIdList]', this).each(function () {
				if ($(this).is(":checked")) {
					conts += $(this).val()*1; //乘1将值转换
				}
			});
			$('.prize-total').text(conts);
		});
	} */
	
	//消费券叠加计算
	function prizeCount() {
		var $prizeListBox = $('.prizeListBox');
		var conts = 0;
		var product_total = parseFloat($("#product-total").text().substr(1)); //商品总价
		$prizeListBox.each(function() {
			$('input[name=couponIdList]', this).each(function () {
				if ($(this).is(":checked")) {
					conts += $(this).val()*1; //乘1将值转换
				}
			});
			var maxCouponAmount = $('#maxCouponAmount').val()*1;
			// $('.prize-total').text(conts);
			$('.prize-total').text(Math.min(conts, maxCouponAmount));
			$('#coupon-total').text("-￥"+ Math.min(conts, maxCouponAmount));
			$("#shouldPay").text("￥"+ Math.max(product_total -Math.min(conts, maxCouponAmount), 0).toFixed(2));//实付
		});
	}
</script>
</body>
</html>