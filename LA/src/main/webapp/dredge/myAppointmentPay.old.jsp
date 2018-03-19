<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>订单支付</title>
<link rel="stylesheet" href="../dredge/css/shopping.common.css?v201600923">
</head>

<body class="bggrey">
<section id="tabBox" class="tabBox pos-relative check-pay-box bgwhite">
	<input id="dredgeBillId" type="hidden" value="${dataValue.id }" >
	<input id="orderId" type="hidden" value="${dataValue.orderId }" >
    <div class="bd my-order" id="tabBox-bd">
        <div class="myOrderList bgwhite">
           <div class="my-order-item">
               <div class="displaybox order-info-box ptb10 bordertopgrey">
                    <div class="master-info-img master-pay-img"><img src="${dataValue.repairUrl }" /></div>
                    <ul class="order-info-address boxflex01 lineheight180 mtop3">
                        <li>
                        	<div class="icon-person master-info-name f16">${dataValue.repairName}</div>
                        	<div class="f12">身份证：${fn:substring(dataValue.repairIdNumber,0,4)}****${fn:substring(dataValue.repairIdNumber,fn:length(dataValue.repairIdNumber)-4,fn:length(dataValue.repairIdNumber))}</div>
			            	<div class="star-box">
			            		<div class="star-shape"><img src="../dredge/images/stars_small.png" /></div>
			            		<div class="star-bgred" data-width="${dataValue.level}"></div>
			            		<div class="star-bggrey"></div>
			            	</div>
                        </li>
                    </ul>
                    <div class="order-info-phone-call noborder"><a href="tel:${dataValue.repairPhone }"><img src="../dredge/images/icon-phone-call.png" /></a></div>
               </div>
				<section class="sectionBox addressInfo black paid-info bordertopgrey">
					<ul class="p10 t-center">
				    	<li class="mtop5"><span class="f14 mright10 grey">支付费用</span></li>
				    	<li class="paid-num lineheightnormal">￥<span id="orderAmountTotalPrice">${dataValue.payAmount }</span></li>
				    	<li id="payAmountList" class="displaybox f14 lineheightnormal">
				    		<c:forEach items="${dataValue.amountList }" var="item" varStatus="status"> 
					    		<div class="<c:choose> <c:when test="${status.index == 0 }">boxflex01 t-right pright15 borderright cost-of-labor</c:when> <c:when test="${status.index == 1 }">t-center m015 pright15 borderright</c:when> <c:when test="${status.index == 2 }">boxflex01 t-left cost-of-items</c:when>  </c:choose>"> 
					    			<div class="grey">${item.feeName }</div>
					    			<div>￥<span class="cost-num">${item.feeAmount }</span></div>
					    		</div>
				    		</c:forEach>
				    	</li>
				    </ul>
				</section>
			    <div class="item-select-entrance hide">
			    	<a href="../dredge/viewSelfBuyProductList.do?dredgeBillId=${dataValue.id }&dredgeTypeId=${dataValue.dredgeTypeId }&subTypeId=${dataValue.subTypeId }&billType=${dataValue.billType }&canEdit=false">
					    <div class="list-box bgwhite displaybox bordertopgrey">
				            <div class="item-standard-name f16">耗材费<span class="grey f12"></span></div>
				            <div class="boxflex01 box-arrow t-right red">${dataValue.selfBuyCount }件，合计￥<span id="costOfItems">${dataValue.selfBuyAmount }</span></div>
					    </div>
		            </a>
				</div>
				<section class="user-info-title bordertopgrey">
					<span class="disblock p015 f12 grey lineheightnormal">
				    	<span class="list-name">服务类型</span>${dataValue.type }<br/>
				        <span class="list-name">创建时间</span>${dataValue.submitDate }<br/>
				        <span class="list-name">订单号</span>${dataValue.billNo }</span>
				</section>
				<section class="divide-box bordertbgrey"></section>
			    <section class="sectionBox">
			        <ul class="user-menu-list m0">
				    	<li>
					    	<c:if test="${dataValue.ext_isContainCoupon }">
						    	<div class="left mtop5">
						    		<label for="cbox02a" id="allCheckBox" class="item-check-box single-check item-checked"></label >
								</div>
							</c:if>
					    	<div id="viewCouponList" class="prize-list">
						    	<span class="right red">
							    	<c:if test="${dataValue.ext_isContainCoupon}">
							    		<span>￥<span id="couponSelectedVal"></span></span>
							    	</c:if>
							    	<img src="../images/arrow-right.png" />
						    	</span>
						    	<c:set var="totalAmount" value="${dataValue.ext_totalMoney_Fee}" scope="session" />
						    	<c:set var="ext_couponCombiInfo" value="${dataValue.ext_couponCombiInfo}" scope="session" />
						    	<span <c:if test="${not dataValue.ext_isContainCoupon}">class=" mleft15"</c:if>>消费券<%-- ，可使用${fn:length(dataValue.ext_couponCombiInfo) }张 --%>
						    		<input type="hidden" id="percent" value="${dataValue.ext_couponCombiInfo[0].maxDiscountPercent}">
						    		<c:if test="${not dataValue.ext_isContainCoupon}">
							    		<span class="un-use bgred mtop3">无可用</span>
						    		</c:if>
						    	</span>
					    	</div>
				    	</li>
			        </ul>
			    </section>
				<section class="divide-box bordertopgrey"></section>
				<section class="divide-box bggrey h57"></section>
           </div>
        </div>
    </div>
    <ul class="bottom-menu-box displaybox t-left bordertopgrey">
    	<li class="p00 boxflex01 f14 mleft15">实际付款：<span class="red bold f16"><span id="shouldPay" class="actuallyPaidPrice"></span></span><br><span class="grey f12">已优惠：￥<span id="discountPrice">0</span></span></li>
    	<li class="p00 wp30"><input id='confirmPayBtn' class="btn-submit bgred noradius btnSubmit" type="submit" name="submit" value="确认付款"></li>
    </ul>
</section>

<div class="prize-box dsn">
    <header class="sectionBox fantasia-header bordertopgrey">
        <!-- <a id="prizeCancel" class="disblock p010 left w40" href="javascript:void(0)">取消</a> -->
        <div class="header-title">消费券</div>
        <a id="prizeCheck" class="disblock p010 right" href="javascript:void(0)"><span  class="red">确定</span></a>
    </header>
    <section class="divide-box bordertbgrey"></section>
    <section class="sectionBox">
        <ul class="item-price p10">
            <li class="left">订单金额</li>
            <li class="right"><span class="red bold f16">￥<span class="items-total orderAmountPrice">${dataValue.payAmount }</span></span></li>
        </ul>
        <ul class="item-price p510 bordertopgrey">
            <li class="left">人工费金额</li>
            <li class="right"><span class="grey f16">￥<span id="costOfLabor"></span></span></li>
        </ul>
        <ul class="item-price p510 bordertopgrey">
            <li class="left">耗材费金额</li>
            <li class="right"><span class="grey f16">￥<span id="costOfItems01"></span></span></li>
        </ul>
        <ul class="item-price p510">
            <li class="left">使用消费券抵扣</li>
            <li class="right"><span class="red f16">-￥<span class="prize-total"></span></span></li>
        </ul>
    </section> 
    <section class="divide-box bordertopgrey"></section>  
    
    <section class="sectionBox bggrey">
		<c:if test="${empty dataValue.ext_couponCombiInfo }">
			<ul class="p10 bgwhite bordertbgrey">
	            <li class="t-center">无可用消费券</li>
	        </ul>
        </c:if>
		<c:forEach items="${dataValue.ext_couponCombiInfo }" var="coupon">
		<div class="displaybox pos-relative prizeListBox">
            <div class="prize-check new-ticket-check">
            	<div id="${coupon.id }" class="lable4cp item-check-box single-check ${coupon.id == dataValue.ext_couponCombiInfo[0].id ? 'item-checked' :''}" hbAmout="${coupon.discountMoney }" data-value="${coupon.id }" data-useType="${coupon.useType }"></div>
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
	                <li class="record-txt boxflex01 borderleft">
	                <span class="f16">${coupon.couponName }</span>
	                <div class="f12 grey mtop10">${coupon.couponDesc }</div>
	                <div class="f12 grey">有效期至  ${coupon.useEndDate }</div></li>
	            </ul>
	        </div>
        </div>
		</c:forEach>
	</section>
    <section class="divide-box"></section>
</div>
<script src="${resourcePath}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePath}/commonjs/Validform_v5.3.2.js"></script>
<script>
	$(function(){
		
		//处理耗材费多余小数问题
		$('#costOfItems, #costOfItems01').text(($('#costOfItems').text()*1).toFixed(2));
		
		//设置星级
		var spanIndex = ${dataValue.level };
		$('.order-info-address li span:lt( '+spanIndex+' )').addClass('on');
		
		//设置人工费金额
		var costOfLabor = $('#payAmountList').find('.cost-of-labor').find('.cost-num').text();
		$('#costOfLabor').text(costOfLabor);
		
		//判断是否显示耗材入口
		(function hasItems(){
			$.ajax({
				url:"../common/toUrl.do",
				type:"get",
				data:{"detailUrl" : "/dredge/qryProductList.json", page: 1, pageNum: 10, dredgeTypeId: '${dataValue.dredgeTypeId }', subTypeId: '${dataValue.subTypeId }', appType: 3},
				dataType:"json",
				success:function(data){
					//如果商品数量不为0，则显示选择耗材入口
					if(data.dataValue.count !== 0){
						$('.item-select-entrance').removeClass('hide');
					}
					
				}
			});
		}());
	});	
	
	$('#confirmPayBtn').click(function(){
		if(window.confirm('确认要付款吗？')){
			
			$(this).removeClass('bgred').addClass('bggrey01').attr('disabled', 'disabled');
			
			var couponIdList = new Array();
			
			$('.item-checked.lable4cp').each(function(key){
				couponIdList[key] = $(this).attr('data-value'); 
			});
			//console.log(JSON.stringify(couponIdList));
			
			var orderId = $("#orderId").val();
			//if(orderId==null || orderId==""){//没有orderid
			$.ajax({
				type:"get",
				url:"../common/toUrl.do",
				data:{"detailUrl":"/dredge/confirmPayDredgeBill.json", "dredgeBillId":$('#dredgeBillId').val(), "isNeedLogin":"1", couponIdList:JSON.stringify(couponIdList)},
				dataType:"json",
				beforeSend:function(data){
					
				},
				success:function(data){
					if(data.status=="0000"){
						orderId = data.dataValue.orderId;
						$("#orderId").val(orderId);
						//alert("提交订单成功，订单号是：" + orderId);
						if(orderId!=null&&orderId!=""){
							toPay(orderId);
						}
					}
				},
			});
			/* }else{
				//有orderId
				toPay(orderId);
			} */
		};
	});
	
	function toPay(orderId){
		var shouldPayAmt = parseFloat($("#shouldPay").text().substring(1));
	    //alert("shouldPay is: " + shouldPayAmt);
	  
	    var ajaxURL= "../dredge/submitOrderMulti.do";       
	    jQuery.ajax({
            url: ajaxURL,
            cache: false,
		    dataType:"json",
			async:false,
            data:{"shouldPayAmt":shouldPayAmt, "orderId":orderId},
            success:function(data) {
                   var obj = jQuery.parseJSON(data);
            	   if(shouldPayAmt<0.000001){//100%用券支付成功
            		   window.location.href = '../dredge/qryDredgeBillDetail.do?id=' + '${dataValue.id }' + '&billType=' + '${dataValue.billType }' + '&fromPay=' + 'true';
            		   return false;
            	   }
            	   
                   WeixinJSBridge.invoke('getBrandWCPayRequest',{  
	   	                "appId":obj.appId,                 //公众号名称，由商户传入  
	   	                "timeStamp":obj.timeStamp,          //时间戳，自 1970 年以来的秒数  
	   	                "nonceStr":obj.nonceStr,         //随机串  
	   	                "package":obj.package,      //prepay_id=*** 
	   	                "signType":obj.signType,        //微信签名方式:MD5  
	   	                "paySign":obj.paySign,           //微信签名  
	   	                },function(res){      
	   	                    //alert(res.err_msg);  get_brand_wcpay_request:ok
	   	                if(res.err_msg == "get_brand_wcpay_request:ok" ) {  
	   	                	//alert("付款成功，您可继续选购其它商品");
	   	                	window.location.href = '../dredge/qryDredgeBillDetail.do?id=' + '${dataValue.id }' + '&billType=' + '${dataValue.billType }' + '&fromPay=' + 'true';
	   	                }else{  
	   	                	alert("付款失败，您可查看订单，继续支付");
	   	                	//alert("详细错误" + res.err_msg);
	   	                    window.location.href="../dredge/myAppointment.jsp";     
	   	                };
   	            	});  
              },
              error: function(data) {
                  alert("error:"+data.responseText);
                  $('#confirmPayBtn').addClass('bgred').removeClass('bggrey01').removeAttr('disabled');
              }
          });
	}
</script>
<script src="${resourcePath}/commonjs/vouchers.js?v20160811"></script>

</body>
</html>