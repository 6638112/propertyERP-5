<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
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
<link rel="stylesheet" href="../css/common.css">
</head>

<body class="bggrey">
<form id="toPayForm" action="../order/submitOrderMulti.do" method="post">
	<input type="hidden" name="merchantIdDeliveryTypeList" />
	<input type="hidden" name="orderId" value="${jsonResponse.dataValue.orderId}" />
<section class="sectionBox addressInfo">
	<ul class="p10">
    	<li class="title">收货信息</li>
    	<li class="mtop5"><span class="f14 mright10">${jsonResponse.dataValue.deliveryAddress.userName }</span> ${jsonResponse.dataValue.deliveryAddress.userPhone }</li>
    	<li>${jsonResponse.dataValue.deliveryAddress.addressTotal }</li>
    </ul>
</section>
<c:forEach items="${jsonResponse.dataValue.productInfo}" var="item">
	<section class="divide-box bordertbgrey"></section>
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
		                	<span class="f14">￥${product.priceDiscount }</span><br/><span class="grey">x${product.productQty  }</span>
		                </span>
		                <input type="hidden" name="productIdQty" value="${product.id}:${product.productQty}">
		            </div>
		        </div>
	        </c:forEach>
	        <!-- 有运费 -->
			<c:if test="${item.isSelfGet == 0 }">	        
		        <ul class="item-price deliver-box displaybox noborder">
		            <li class="left grey displaybox">
						<div class="boxflex01">${item.deliveryMethod.desc}</div>
					</li>
		            <li class="boxflex01 t-right">运费：￥<span class="item-deliver-fee">${item.deliveryMethod.fee }</span></li>
		        </ul>
	        </c:if>
	        <!-- 到店自提 -->
	        <c:if test="${item.isSelfGet == 1 }">	
		        <ul class="item-price deliver-box displaybox noborder">
		            <li class="left grey displaybox">
						<div class="boxflex01">到店自提</div>
					</li>
		            <li class="boxflex01 t-right">运费：￥<span class="item-deliver-fee">0.00</span></li>
		        </ul>
	        </c:if>
	        <ul class="item-price item-sub-total">
	            <li class="left">共 ${item.signalMer_totalCount } 件商品</li>
	            <li class="right">应付：
	            <span class="red bold f16">￥
		            <c:if test="${item.isSelfGet == 0 }">
		            	<span class="merchant-price">${item.signalMer_totalMoney + item.deliveryMethod.fee }</span>
	            	</c:if>
		            <c:if test="${item.isSelfGet == 1 }">
		            	<span class="merchant-price">${item.signalMer_totalMoney }</span>
	            	</c:if>
	            </span>
	            </li>
	        </ul>
	    </div>
	</section> 
</c:forEach>
</form>
<section class="divide-box pbfooter"></section>
<section class="sectionBox">
	 <div class="exchang-fixed displaybox">
	     <div class="boxflex01 bggradient p010">
	     	<span class="f12">实付：</span><span class="red bold f16">￥</span>
	      	<span class="red bold f16" id="shouldPay">${jsonResponse.dataValue.totalAmount }</span>
	     </div>
	     <div class="btn-check-pay bgred"><a id="btnCheckPay" class="white disblock" href="javascript:void(0)" onclick="toPay();">确认付款</a></div>
	 </div>
</section>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script> 
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	})
</script>
<script type="text/javascript">
  	var isSubmited = false;
	function toPay(){
   	
	  var ajaxURL= "../order/submitOrderMulti.do";       
	  jQuery.ajax({
            url: ajaxURL,//ajax调用微信统一接口获取prepayId
            cache: false,
		    dataType:"json",
			async:false,
            data:$('#toPayForm').serialize(),
            beforeSend:function(){
         	   if(isSubmited){
         		   return false;
         	   }
            },
            success:function(data) {
         	   $('.btn-check-pay').removeClass('bgred').addClass('bggrey01');
         	   isSubmited = true;
               var obj = jQuery.parseJSON(data);
               
               //付款前确认促销商品库存以及活动是否过期
               $.ajax({
	       			url:'../common/toUrl.do',
	    			data:{orderId: obj.orderId, detailUrl: '/ebuyV2/checkOrderPrdtBeforePay.json'},
           			success: function(data){
           				if(data.status !== '0000'){
           					
           					alert(data.message);
							location.reload();
           					return false;
           					
           				}else{
               
			               WeixinJSBridge.invoke('getBrandWCPayRequest',{  
			   	                "appId":obj.appId,                 //公众号名称，由商户传入  
			   	                "timeStamp":obj.timeStamp,          //时间戳，自 1970 年以来的秒数  
			   	                "nonceStr":obj.nonceStr,         //随机串  
			   	                "package":obj.package,      //prepay_id=*** 
			   	                "signType":obj.signType,        //微信签名方式:MD5  
			   	                "paySign":obj.paySign           //微信签名  
			   	                },function(res){      
			   	                    //alert(res.err_msg);  get_brand_wcpay_request:ok
			   	                if(res.err_msg == "get_brand_wcpay_request:ok" ) {  
			   	                	alert("付款成功，您可继续选购其它商品");
			   	                    //window.location.href="../product/index.do";  
			   	                    history.replaceState({}, '我的订单', '../order/qryOrderList.do'); //这一行会导致android支付失败，因为修改了支付授权目录，不是./cart开头，因此须在支付成功后设置
			   	                 	window.location.href = "../order/viewOrderDetail.do?orderId="+obj.orderId+"&isNeedSendMsg=1";
			   	                }else{  
			   	                	alert("付款失败，您可查看订单，继续支付");
			   	                	//alert("详细错误" + res.err_msg);
			   	                    window.location.href="../order/qryOrderList.do";      
			   	                    //当失败后，继续跳转该支付页面让用户可以继续付款，贴别注意不能直接调转jsp，不然会报system:access_denied 
			   	                	isSubmited = false;
			   	                }  
			   	            }); 
			               
           				}
           			}
     			});
               
           },
           error: function(data) {
               alert("error:"+data.responseText);
               isSubmited = false;
           }
        });
	}
</script>
</body>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?37203afff68fe15791b03c221468237c";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</html>