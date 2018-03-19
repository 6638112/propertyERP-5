<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<% 
	String jfqsource = request.getParameter("jfqsource");//用request得到 
	String limitbuyId = request.getParameter("limitbuyId");
%>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">

<title>确认订单</title>
<link rel="stylesheet" href="../css/common.css?20171030">
</head>
<body class="heightp100 bggrey pos-relative">
<form id="toPayForm" class="heightp100" action="../order/submitOrderMulti.do" method="post">
<input type="hidden" name="merchantIdDeliveryTypeList" />
<div class="check-pay-box heightp100">
	<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100">
		<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
			<section class="sectionBox addressInfo f14">
			    
			    <c:if test="${!empty addresResponse.dataValue and !empty addresResponse.dataValue.userName }">
		        <div class="m1510 displaybox">
		    		<div><img class="left mtop5 mright10" style="width: 14px;" src="../images/icon-address-map.png" /></div>
			        <ul class="boxflex01">
				        <!-- 正常商品收货地址 -->
			            <a class="white" href="../address/qryDeliveryAddressList.do?fromWhere=${param.jfqsource eq null ? 'checkPay' : param.jfqsource }">
			            	<li class="f16">
				            	<img class="right mtop20 mleft10" src="../images/arrow-right-white.png" />
				            	<span class="mright10 user-name">${addresResponse.dataValue.userName }</span> ${addresResponse.dataValue.userPhone }
			            	</li>
							<input type="hidden" name="gbId" value="${addresResponse.dataValue.gbId }">
			            	<input type="hidden" name="addressId" value="${addresResponse.dataValue.id}">
			            	<li>${addresResponse.dataValue.addressTotal }</li>
			            </a>
			        </ul>
		        </div>
		        </c:if>
		        
			    <c:if test="${empty addresResponse.dataValue or empty addresResponse.dataValue.userName }">
			        <ul class="p10">
			            <a class="white" href="../address/addAddressInfo.do?fromWhere=${param.jfqsource eq null ? 'checkPay' : param.jfqsource  }">
			            	<li class="mtop5 ptb20 t-center"><span class="address-fill">完善收货信息</span></li>
			            </a>
			        </ul>
		        </c:if>
			</section>
			<section class="divide-box borderbottomgrey borderbottomgrey"></section>
			<c:forEach items="${jsonResponse.dataValue.list}" var="item">
				<section class="sectionBox payList">
				   <div class="pay-list-info mleft10">
				       <h3 class="item-sort">${item.name }</h3>
				       <c:forEach items="${item.productList }" var="product">
					       <div class="info ptb10 borderbottomgrey">
					            <div class="tbl">
					                <span class="item-img-small01 inline-table"><img src="${product.picBase}<c:choose><c:when test="${fn:contains(product.picBase, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_110,h_110,limit_0/format,jpg/interlace,1/quality,q_90" /></span>
					                <span class="item-info tbl-cell">
					                    <div class="f14">${product.name }</div>
					                    <div class="message">${product.specification }</div>
					                </span>
					                <span class="price-num tbl-cell">
					                	<span class="f14">￥<span class="limitBuyPrice">${product.priceDiscount }</span></span><br/><span class="grey">x<span class="item-qty">${product.productQty}</span></span>
					                </span>
					                <input type="hidden" name="productIdQty"
					                 data-ptId="${product.id}" data-ptTotal="${product.priceDiscount * product.productQty}"
					                 value="${product.id}:${product.productQty}:${product.productSpecId}">
					            </div>
					        </div>
				        </c:forEach>
				        
				        <!-- 商品运费 -->
				        <ul class="item-price deliver-box displaybox p210 noborder">
				            <li class="left grey displaybox">
					            <div class="item-check-box item-checked mleft_20 <c:if test="${item.signalMer_Deliv_Info == '到店自提' }">item-no-deliver</c:if>" data-merchantid="${item.id }"></div>
								<div class="boxflex01">${item.signalMer_Deliv_Info}</div>
							</li>
							<c:if test="${limitBuyData.signalMer_Deliv_Info != '到店自提' }">
				            	<li class="boxflex01 t-right minwidth90">运费：￥<span class="item-deliver-fee has-deliver-fee">${item.signalMer_Fee }</span></li>
				            </c:if>
							<c:if test="${limitBuyData.signalMer_Deliv_Info == '到店自提' }">
				            	<li class="boxflex01 t-right minwidth90">免运费<span class="item-deliver-fee has-deliver-fee dsn">0.00</span></li>
				            </c:if>
				        </ul>
				        <c:if test="${item.signalMer_Deliv_Info != '到店自提'}">
					        <ul class="item-price deliver-box displaybox p210">
					            <li class="left grey displaybox">
						            <div class="item-check-box item-no-deliver mleft_20" data-merchantid="${item.id }"></div>
									<div class="boxflex01">到店自提</div>
								</li>
					            <li class="boxflex01 t-right minwidth90">免运费<span class="item-deliver-fee dsn">0.00</span></li>
					        </ul>
				        </c:if>
				        
				        <ul class="item-price item-sub-total">
				            <li class="left">共 ${item.signalMer_totalCount } 件商品</li>
				            <li class="right">应付：<span class="red bold f16">￥<span id="merchant-id-${item.id }" class="merchant-price">${item.signalMer_totalMoney_Fee }</span></span></li>
				        </ul>
				    </div>
				</section> 
		    	<section class="divide-box borderbottomgrey"></section>
			</c:forEach>
			
			<section class="sectionBox borderbottomgrey">
				<ul class="user-menu-list m0">
			    	<li class="displaybox f14">
				    	<div class="has-ticket dsn">
				    		<label id="ticketCheck" class="item-check-box"></label >
						</div>
				    	<div class="boxflex01 ticket-text mleft10">
					    	<span class="right has-ticket dsn">
					    		<input id="ticketAmount" class="input-normal radius2 w80 p510 t-right red" type="text" name="" maxlength="7" data-max="100" />
					    	</span>
					    	<span>
					    		粮票
					    		<span class="mtop3">(可用金额￥<span class="ticket-amount"></span>)</span>
					    	</span>
				    	</div>
			    	</li>
			    	<li class="displaybox">
				    	<c:if test="${jsonResponse.dataValue.ext_isContainCoupon }">
					    	<div>
					    		<label for="cbox02a" id="allCheckBox" class="item-check-box single-check item-checked"></label >
							</div>
						</c:if>
				    	<div id="viewCouponList" class="prize-list boxflex01 f14">
					    	<span class="right red">
						    	<c:if test="${jsonResponse.dataValue.ext_isContainCoupon}">
						    		<span>￥<span id="couponSelectedVal"></span></span>
							    	<img src="../images/arrow-right.png" />
						    	</c:if>
					    	</span>
					    	<c:set var="totalAmount" value="${jsonResponse.dataValue.ext_totalMoney_Fee}" scope="session" />
					    	<c:set var="ext_couponCombiInfo" value="${jsonResponse.dataValue.ext_couponCombiInfo}" scope="session" />
					    	<span <c:if test="${not jsonResponse.dataValue.ext_isContainCoupon}">class=" mleft10"</c:if>>消费券
					    		<input type="hidden" id="percent" value="${jsonResponse.dataValue.ext_couponCombiInfo[0].maxDiscountPercent}">
					    		<c:if test="${not jsonResponse.dataValue.ext_isContainCoupon}">
						    		<span class="un-use bgred mtop3">无可用</span>
					    		</c:if>
					    	</span>
				    	</div>
			    	</li>
			    </ul>
			</section>
			<section class="divide-box borderbottomgrey"></section>
			<section class="sectionBox borderbottomgrey">
				<ul class="item-price noborder p10">
			        <li class="left grey">商品合计</li>
			        <li id="product-total" class="right">￥${jsonResponse.dataValue.ext_totalMoney }</li>
			    </ul>
				<ul class="item-price noborder p10">
			        <li class="left grey">运费合计</li>
			        <li class="right">+￥<span id="transport-total">${jsonResponse.dataValue.ext_Fee}</span></li>
			    </ul>
				<ul class="item-price noborder p10">
			        <li id="discountName" class="left grey">消费券抵扣</li>
					<c:set var="suggestSavedAmount" value="${jsonResponse.dataValue.ext_totalMoney + jsonResponse.dataValue.ext_Fee}" scope="page"></c:set>
					<c:set var="firstSavedAmount" value="${ext_couponCombiInfo[0].discountMoney > ext_couponCombiInfo[0].maxDiscountPercent/100 * (jsonResponse.dataValue.ext_totalMoney + jsonResponse.dataValue.ext_Fee) ? ext_couponCombiInfo[0].maxDiscountPercent/100 * (jsonResponse.dataValue.ext_totalMoney + jsonResponse.dataValue.ext_Fee) :ext_couponCombiInfo[0].discountMoney}" scope="page"></c:set>
			        <input type="hidden" id="maxCouponAmount" value='${suggestSavedAmount} '/>
			        <li id="coupon-total" class="right">-￥${firstSavedAmount}</li>
			    </ul>
			</section>
			<section class="divide-box pbfooter"></section>
		    
		    <section class="sectionBox">
			    <div class="exchang-fixed displaybox">
			        <div class="boxflex01 bggradient p010">
			        	<span class="f12">实付：</span><span class="red bold f16">￥</span>
				        <span class="red bold f16" id="shouldPay">
							<fmt:formatNumber value="${jsonResponse.dataValue.ext_totalMoney + jsonResponse.dataValue.ext_Fee - firstSavedAmount}" pattern="0.00" />
						</span>
			        </div>
			        <div class="btn-check-pay bgred">
		        		<a id="btnCheckPay" class="white disblock" href="javascript:void(0)" onclick="toPay();">确认付款</a>
			        </div>
			    </div>
		    </section>
		</section>
		
		<section class="sectionBox wrap-bg pop-box02 dsn">
			<div class="tips-box">
				<div class="t-center ptb25 borderbottomgrey">
					<div class="f16">请先完善收货地址哦</div>
				</div>
				<ul class="displaybox">
					<li class="boxflex01 ptb5 t-center red pay-check-btn f16 lineheight36"><a class="disblock red" href="../address/addAddressInfo.do?fromWhere=${param.jfqsource eq null ? 'checkPay' : param.jfqsource }">立即填写</a></li>
				</ul>	
			</div>
		</section>
		
	</section>
</div>

<div class="prize-box dsn">
    <section class="sectionBox">
		<header class="fantasia-header">
			<!-- <a id="prizeCancel" class="disblock p010 left" href="#">取消</a> -->
		    <div class="header-title">消费券</div>
		    <a id="prizeCheck" class="disblock p010 right" href="javascript:void(0)"><span id="confirmCouponSelect" class="red">确定</span></a>
		</header>
	</section>
	<section class="divide-box bordertopgrey"></section>
	<section class="sectionBox">
	    <ul class="item-price">
	    	<li class="left">订单金额</li>
	        <li class="right"><span class="red bold f16">￥ <span class="items-total">${totalAmount}</span></span></li>
	    </ul>
	    <%--<ul class="item-price">
	    	<li class="left">最多可使用订单金额<fmt:formatNumber value="${ext_couponCombiInfo.payPercent}" type="percent" pattern="#0%" /></li>
	        <li class="right"><span class="blue bold f16">￥<span >${ext_couponCombiInfo.maxCouponAmount }</span></span></li>
	    </ul>--%>
	    <ul class="item-price">
	    	<li class="left">使用消费券抵扣</li>
	        <li class="right">
		        <span class="blue bold f16">￥
					<span class="prize-total">${firstSavedAmount}</span>
		        </span>
	        </li>
	    </ul>
	</section> 
	<section class="divide-box bordertopgrey"></section>
	<section class="sectionBox bggrey">
		<c:if test="${empty ext_couponCombiInfo }">
			<ul class="p10 bgwhite bordertbgrey">
	            <li class="t-center">无可用消费券</li>
	        </ul>
        </c:if>
		<c:forEach items="${ext_couponCombiInfo }" var="coupon">
		<div class="displaybox pos-relative prizeListBox">
            <div class="prize-check new-ticket-check">
            	<input id="${coupon.id }" name="cpId" type="radio" ${coupon.id == ext_couponCombiInfo[0].id ? "checked" :""} hidden="hidden" value="${coupon.id }" 
            	hbAmout="${coupon.discountMoney }" 
            	data-useType="${coupon.useType }" 
            	data-merchantId="${coupon.supplyMerchantId }" 
            	data-discountIdList="${coupon.discountProductShelfIdList }"/>
				<label class="item-check-box single-check ${coupon.id == ext_couponCombiInfo[0].id ? "item-checked" :""}"></label >
            </div>
            <div class="record-list-bg bgred boxflex01">
	            <ul class="displaybox record-list p10 mleft5 noborderleft">
	                <li class="new-ticket-num">
	               		 ￥<span class="f30">${coupon.discountMoney }</span>
	                	<c:choose>
						     <c:when test="${coupon.leastSpendUse == 0 }">  
    							<div class="f12 mtop10">消费即可用</div>
						     </c:when>
						     <c:otherwise> 
								<div class="f12 mtop10">满${coupon.leastSpendUse }元可用</div>
						     </c:otherwise>
						</c:choose>
	                </li>
	                <li class="record-txt boxflex01 borderleft mright20 pleft10">
	                <span class="f16">${coupon.couponName }</span>
	                <div class="f12 grey mtop10">${coupon.couponDesc }</div>
	                <div class="f12 grey">有效期至  ${coupon.useEndDate }</div></li>
	            </ul>
	        </div>
        </div>
		</c:forEach>
	</section>
</div>
<!-- 消息提示-->
<section class="pop-tips dsn">
	<div class="pop-tips-text">请选择</div>
</section>
</form>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script> 
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="../js/ebuy.common.js?20171113"></script>
<script>
	$(function(){
		new FastClick(document.body);
	});
</script>
<script type="text/javascript">
	
	//是否已提交付款
	var issubmited = false;
	//正常商品支付
    function toPay(){
   	  //遍历选择为自提的货单
   	  getNoDeliveryList();
	
   	  if($('span.address-fill').length > 0){
			$('#wrapBox').addClass('heightp100');
			$('.pop-box02').removeClass('dsn');
			return false;
	  }
   	 
	  var shouldPayAmt =  parseFloat($("#shouldPay").text().trim());

	  var ajaxURL= "../order/submitOrderMulti.do";  
	  var dataParams = $('#toPayForm').serialize();  
	  
	  if(issubmited){//防止二次提交付款
		 return false; 
	  }
	  
	  //使用消费券支付
	  if(shouldPayAmt<0.000001 && $('#allCheckBox').length && $('#allCheckBox').hasClass('item-checked')){//100%用券支付成功
		   var couponSelectedVal = $('#couponSelectedVal').text(),
		       alertInfo = '确认使用 ￥' + couponSelectedVal + ' 消费券吗？';
   		   if(!confirm(alertInfo)){
       		   return false;
   		   }
   	  }

	  //使用粮票支付
	  if($('#ticketCheck').length && $('#ticketCheck').hasClass('item-checked')){
		  var ticketUseAmount = $('#ticketAmount').val(),
		      alertInfo = '确认使用 ￥' + ticketUseAmount + ' 粮票吗？';
   		  if(!confirm(alertInfo)){
       		  return false;
   		  }
		  dataParams += "&hbAmount=" + ticketUseAmount;
	  }

  	      $('.btn-check-pay').removeClass('bgred').addClass('bggrey01');
         issubmited = true;
	  
	  jQuery.ajax({
            url: ajaxURL,//ajax调用微信统一接口获取prepayId
            cache: false,
		    dataType:"json",
			async:false,
            data: dataParams + "&shouldPayAmt=" + shouldPayAmt,
            success:function(data) {
                  var obj = jQuery.parseJSON(data);
                  if(obj.status !=undefined && "0000"!=obj.status){//提交订单失败
			           issubmited = false;
              	       $('.btn-check-pay').removeClass('bggrey01').addClass('bgred');
                  	   return;
                  }
           		
	           	   if(shouldPayAmt<0.000001){//100%用券支付成功
 	                   history.replaceState({}, '我的订单', '../order/qryOrderList.do'); //这一行会导致android支付失败，因为修改了支付授权目录，不是./cart开头
	           		   window.location.href = "../order/viewOrderDetail.do?orderId="+obj.orderId+"&isNeedSendMsg=1";
	           		   return false;
	           	   }
           	   
                  WeixinJSBridge.invoke('getBrandWCPayRequest',{  
   	                "appId":obj.appId,                 //公众号名称，由商户传入  
   	                "timeStamp":obj.timeStamp,          //时间戳，自 1970 年以来的秒数  
   	                "nonceStr":obj.nonceStr,         //随机串  
   	                "package":obj.package,      //prepay_id=*** 
   	                "signType":obj.signType,        //微信签名方式:MD5  
   	                "paySign":obj.paySign           //微信签名  
   	                },function(res){      
   	                    //alert(res.err_msg); // get_brand_wcpay_request:ok
   	                if(res.err_msg == "get_brand_wcpay_request:ok" ) {  
   	                	history.replaceState({}, '我的订单', '../order/qryOrderList.do'); //这一行会导致android支付失败，因为修改了支付授权目录，不是./cart开头，需在支付成功后设置
   	                	window.location.href = "../order/viewOrderDetail.do?orderId="+obj.orderId+"&isNeedSendMsg=1";
   	                }else{  
   	                	alert("付款失败，您可查看订单，继续支付");
   	                	//alert("详细错误" + res.err_msg);
   	                    window.location.href="../order/qryOrderList.do";     
   	                   //当失败后，继续跳转该支付页面让用户可以继续付款，特别注意不能直接调转jsp，不然会报system:access_denied 
   	                }  
   	           });
               issubmited = false;
          	   	   $('.btn-check-pay').removeClass('bggrey01').addClass('bgred');
                  
            },
            error: function(data) {
                alert("确认付款失败，请稍后再试");
	            issubmited = false;
           	    $('.btn-check-pay').removeClass('bggrey01').addClass('bgred');
            }
        });
    }
	
</script>
</body>
</html>