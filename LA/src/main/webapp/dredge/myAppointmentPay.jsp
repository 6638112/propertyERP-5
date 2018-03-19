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
<title>订单支付</title>
<link rel="dns-prefetch" href="//jiefangqu.com">
<link rel="stylesheet" href="../dredge/css/shopping.common.css?v201600923">
</head>

<body class="bggrey">
<section id="tabBox" class="tabBox pos-relative check-pay-box bgwhite">
	<input id="dredgeBillId" type="hidden" value="${dataValue.id }" >
	<input id="orderId" type="hidden" value="${dataValue.orderId }" >
    <div class="bd my-order" id="tabBox-bd">
        <div class="myOrderList bgwhite">
           <div class="my-order-item">
				<section class="sectionBox addressInfo black paid-info">
					<ul class="p10 t-center">
				    	<li class="mtop5"><span class="f14 mright10 grey">待支付金额（元）</span></li>
				    	<li class="paid-num lineheightnormal h57"><span id="orderShouldPrice"></span></li>
				    	<li class="f14 lineheightnormal default-price<c:if test="${not dataValue.ext_isContainCoupon}"> hide</c:if>"><span id="orderAmountTotalPrice">${dataValue.payAmount }</span></li>
				    	<li class="f14 lineheightnormal red<c:if test="${not dataValue.ext_isContainCoupon}"> hide</c:if>"><span id="orderAmountTotalPrice">使用消费券优惠  <span id="discountPrice">0</span> 元</span></li>
				    	<li class="mtop15">
					    	<c:if test="${dataValue.billType != 5}">
					    		<input id='confirmPayBtn' class="details-common-btn btn-bgred" type="submit" name="submit" value="确认并支付">
				    		</c:if>
					    	<c:if test="${dataValue.billType == 5}">
					    		<input id='confirmPayBtn' class="details-common-btn btn-bgred" type="submit" name="submit" value="确认支付">
				    		</c:if>
				    	</li>
				    </ul>
				</section>
				<section class="sectionBox">
					<div style="margin: 10px;">
						<ul class="user-menu-list box-shadow m0">
					    	<li>
						    	<c:if test="${dataValue.ext_isContainCoupon }">
							    	<div class="left mtop5">
							    		<label for="cbox02a" id="allCheckBox" class="item-check-box single-check item-checked"></label >
									</div>
								</c:if>
						    	<div id="viewCouponList" class="prize-list f14">
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
					</div>
			    </section>
			    
				<c:set var="hasItemsList" value="${(not empty dataValue.dredgeProductSpecList) or (not empty dataValue.selfBuyProductList)}"/>
				<c:if test="${hasItemsList or (not empty dataValue.amountList)}">
					<section class="user-info-title bordertopgrey mtop10">
						<span class="disblock p015 f14 lineheightnormal">
							<span class="list-name grey">服务详情</span><br/>
					    	<table class="service_detail mtop5">
								<c:forEach items="${dataValue.dredgeProductSpecList }" var="item"> 
						        	<tr>
										<td class="td1">${item.specName}</td>
										<td class="td2">
											￥<fmt:formatNumber value="${(item.specPrice+0.00000000001)/100.0 + 0.000001}" type="currency" pattern="0.00" maxFractionDigits="2"/>
											<c:if test="${not empty item.specUnit}">
												/${item.specUnit}
											</c:if>
											<c:if test="${not empty item.specNum}">
												 ×${item.specNum}
											</c:if>
										</td>
									</tr>
						        </c:forEach>
						        <c:forEach items="${dataValue.selfBuyProductList }" var="item"> 
						        	<tr>
										<td class="td1">${item.name}</td>
										<td class="td2">￥ ${item.price} x${item.quantity}</td>
									</tr>
						        </c:forEach>
							</table>
							<!-- 旧订单，有人工费，显示 -->
							<c:if test="${dataValue.billType != 5}">
						    	<table class="service_detail mtop10 bordertopgrey ptop5">
							        <c:forEach items="${dataValue.amountList }" var="item"> 
										<c:if test="${item.feeAmount > 0}">
								        	<tr>
												<td class="td1 fee-name">${item.feeName}</td>
												<td class="td2">￥ <span class="fee-amount">${item.feeAmount }</span></td>
											</tr>
										</c:if>
							        </c:forEach>
								</table>
							</c:if>
							<!-- 先付款订单，没有人工费，所以不显示。实际付款金额即为人工费，所以此处隐藏人工费 -->
							<c:if test="${dataValue.billType == 5}">
						    	<table class="service_detail mtop10 bordertopgrey ptop5 hide">
							        <c:forEach items="${dataValue.amountList }" var="item"> 
										<c:if test="${item.feeAmount > 0}">
								        	<tr>
												<td class="td1 fee-name">${item.feeName}</td>
												<td class="td2">￥ <span class="fee-amount">${item.feeAmount }</span></td>
											</tr>
										</c:if>
							        </c:forEach>
								</table>
							</c:if>
					    </span>
					</section>
				</c:if>
				
				<section class="user-info-title bordertopgrey">
					<span class="disblock p015 f14 lineheightnormal">
						<span class="list-name grey">订单详情</span><br/>
						<table class="other mtop5 grey">
							<tr>
								<td class="td1">预约地址</td>
								<td class="td2 black">${dataValue.address }</td>
							</tr>
							<tr>
								<td class="td1">预约时间</td>
								<td class="td2 black">${dataValue.expectDate }</td>
							</tr>
							<tr>
								<td class="td1">联系信息</td>
								<td class="td2 black">${dataValue.userPhone} ${dataValue.linkName}</td>
							</tr>
							<tr>
								<td class="td1">订单备注</td>
								<td class="td2 black">${dataValue.content }</td>
							</tr>
							<c:if test="${not empty dataValue.picInfo}">
								<tr>
									<td class="td1">&nbsp;</td>
									<td class="td2">
										 <c:forEach items="${dataValue.picInfo }" var="item"> 
								        	<img class="w80 height80" src="${item}?x-oss-process=image/resize,m_fill,w_160,h_160/format,jpg/interlace,1/quality,q_80"/>
								        </c:forEach>
									</td>
								</tr>
							</c:if>
						</table>
				    </span>
				</section>
           </div>
        </div>
    </div>
    <!-- <ul class="bottom-menu-box displaybox t-left bordertopgrey">
    	<li class="p00 boxflex01 f14 mleft15">实际付款：<span class="red bold f16"><span id="shouldPay" class="actuallyPaidPrice"></span></span><br><span class="grey f12">已优惠：￥<span id="discountPrice">0</span></span></li>
    	<li class="p00 wp30"><input id='confirmPayBtn' class="btn-submit bgred noradius btnSubmit" type="submit" name="submit" value="确认付款"></li>
    </ul> -->
</section>

<div class="prize-box dsn">
    <header class="sectionBox fantasia-header">
        <!-- <a id="prizeCancel" class="disblock p010 left w40" href="javascript:void(0)">取消</a> -->
        <div class="header-title">消费券</div>
        <a id="prizeCheck" class="disblock p010 right" href="javascript:void(0)"><span  class="red">确定</span></a>
    </header>
    <section class="divide-box bordertbgrey"></section>
    <section class="sectionBox f14">
        <ul class="item-price p10">
            <li class="left">订单金额</li>
            <li class="right"><span class="red bold f16">￥<span class="items-total orderAmountPrice">${dataValue.payAmount }</span></span></li>
        </ul>
		<!-- 旧订单，有人工费，显示 -->
		<c:if test="${dataValue.billType != 5}">
	        <ul class="item-price p10 bordertopgrey">
	            <li class="left">人工费金额</li>
	            <li class="right"><span class="grey f16">￥<span id="costOfLabor"></span></span></li>
	        </ul>
	        <ul class="item-price p10 bordertbgrey">
	            <li class="left">耗材费金额</li>
	            <li class="right"><span class="grey f16">￥<span id="costOfItems"></span></span></li>
	        </ul>
	        <ul class="item-price p10 bordertopgrey">
	            <li class="left">其它费金额</li>
	            <li class="right"><span class="grey f16">￥<span id="costOfOthers"></span></span></li>
	        </ul>
        </c:if>
		<!-- 先付款订单，没有人工费，所以不显示。实际付款金额即为人工费，所以此处隐藏人工费 -->
		<c:if test="${dataValue.billType == 5}">
	        <ul class="item-price p10 bordertopgrey">
	            <li class="left">人工费金额</li>
	            <li class="right"><span class="grey f16">￥<span id="costOfLabor"></span></span></li>
	        </ul>
	        <ul class="item-price p10 bordertopgrey">
	            <li class="left">耗材费金额</li>
	            <li class="right"><span class="grey f16">￥<span id="costOfItems"></span></span></li>
	        </ul>
	        <ul class="item-price p10 bordertopgrey">
	            <li class="left">其它费金额</li>
	            <li class="right"><span class="grey f16">￥<span id="costOfOthers"></span></span></li>
	        </ul>
        </c:if>
        <ul class="item-price p10 bordertopgrey">
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
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/Validform_v5.3.2.js"></script>
<script>
	$(function(){
		
		//设置星级
		var spanIndex = ${dataValue.level};
		$('.order-info-address li span:lt( '+spanIndex+' )').addClass('on');
		
		//设置金额
		var costOfLabor;	//人工费
		var costOfItems;	//耗材费
		var costOfOthers;	//其它费
		$('.fee-name').each(function(index){
			if($(this).text() === '人工费'){
				costOfLabor = $('.fee-amount').eq(index).text();
			}
			if($(this).text() === '耗材费'){
				costOfItems = $('.fee-amount').eq(index).text();
			}
			if($(this).text() === '其它费'){
				costOfOthers = $('.fee-amount').eq(index).text();
			}
		});
		if(costOfLabor === undefined){
			$('#costOfLabor').parents('.item-price').addClass('hide');
		}else{
			$('#costOfLabor').text(costOfLabor);
		}
		if(costOfItems === undefined){
			$('#costOfItems').parents('.item-price').addClass('hide');
		}else{
			$('#costOfItems').text(costOfItems);
		}
		if(costOfOthers === undefined){
			$('#costOfOthers').parents('.item-price').addClass('hide');
		}else{
			$('#costOfOthers').text(costOfOthers);
		}
		
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
		var shouldPayAmt = parseFloat($("#orderShouldPrice").text());
	  
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
    				   //修改历史记录，完成后，后退回到我的订单首页
    				   history.replaceState({}, '我的订单', '../dredge/myAppointment.do');
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
	   					    //修改历史记录，完成后，后退回到我的订单首页
	   					    //这一行必须在进入微信支付后执行，否则会导致android支付失败，因为修改了支付授权目录，不是./cart开头
	   					    history.replaceState({}, '我的订单', '../dredge/myAppointment.do');
	   	                	//alert("付款成功，您可继续选购其它商品");
	   	                	window.location.href = '../dredge/qryDredgeBillDetail.do?id=' + '${dataValue.id }' + '&billType=' + '${dataValue.billType }' + '&fromPay=' + 'true';
	   	                }else{  
	   	                	alert("付款失败，您可查看订单，继续支付");
	   	                	//alert("详细错误" + res.err_msg);
	   	                    window.location.href="../dredge/myAppointment.do";     
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
<script src="../dredge/js/vouchers.js?v20170719"></script>
</body>
</html>