<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<link rel="dns-prefetch" href="//jiefangqu.com">
		<title>临时车缴费</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopping.common.css">
	</head>
	<body class="bggrey">
		<div id="mainPart">
			<section class="sectionBox borderbottomgrey ptb15">
				<ul class="p20 t-center">
			    	<li class="grey f14">账单金额</li>
			    	<c:choose>
		    			<c:when test="${(not empty feeInfo.carTmpIsOpen) and (feeInfo.carTmpIsOpen ne 0)}">
			    			<li class="mtop20 f40 pb10">￥
			    				<span class="discountRealFee dsn"><fmt:formatNumber value="${feeInfo.fee-feeInfo.couponAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/></span>
			    				<span class="realFee dsn"><fmt:formatNumber value="${feeInfo.fee-feeInfo.couponAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/></span>
			    			</li>
		    				<li class="mtop10 f16 line-through">￥
		    					<span class="totalFee"><fmt:formatNumber value="${feeInfo.fee}" type="currency" pattern="0.00" maxFractionDigits="2"/></span>
		    				</li>
		    			</c:when>
		    			<c:otherwise>
		    				<li class="mtop20 f40 pb10">￥
		    					<span class="discountRealFee dsn"><fmt:formatNumber value="${feeInfo.fee}" type="currency" pattern="0.00" maxFractionDigits="2"/></span>
		    					<span class="realFee dsn"><fmt:formatNumber value="${feeInfo.fee}" type="currency" pattern="0.00" maxFractionDigits="2"/></span>
		    				</li>
		    				<li id="totalFeeBox" class="mtop10 f16 line-through dsn">￥
		    					<span class="totalFee"><fmt:formatNumber value="${feeInfo.fee}" type="currency" pattern="0.00" maxFractionDigits="2"/></span>
		    				</li>
		    			</c:otherwise>
		    		</c:choose>
			    </ul>
			</section>
			<section class="divide-box bgwhite user-info-title recommend-details-info borderbottomgrey">
				<span class="disblock f14 black">
					<c:if test="${(not empty feeInfo.carTmpIsOpen) and (feeInfo.carTmpIsOpen ne 0)}">
	    				<span class="list-name red">优惠</span><span class="right red">随机立减￥<span><fmt:formatNumber value="${feeInfo.couponAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/></span></span><br/>
	    			</c:if>
			    	<span class="list-name">车牌号</span><span class="right">${feeInfo.plate}</span><br/>
			        <span class="list-name">停车场信息</span><span class="right">${feeInfo.gbName}</span><br/>
			        <span class="list-name">进场时间</span><span class="right">${feeInfo.inDate}</span><br/>
			    	<span class="list-name">停车时长</span><span class="right parkingTime">${feeInfo.parkingTime}</span><br/>
			    	<span class="list-name">已缴费用</span><span class="right prepaymentamount">
			    		￥<fmt:formatNumber value="${feeInfo.prepaymentamount}" type="currency" pattern="0.00" maxFractionDigits="2"/>
			    	</span>
			    </span>
			</section>
			
			<section class="sectionBox bggrey coupon-box dsn">
				<ul id="noCouponsBox" class="p10 bgwhite bordertbgrey dsn">
		            <li class="t-center">无可用消费券</li>
		        </ul>
				<div class="displaybox pos-relative prizeListBox">
		            <div class="prize-check new-ticket-check">
		            	<input name="cpId" type="radio" hidden="hidden" value="" hbAmout="" />
						<label class="item-check-box single-check item-checked"></label >
		            </div>
		            <div class="record-list-bg bgred boxflex01">
			            <ul class="displaybox record-list p10 mleft5 noborderleft">
			                <li class="new-ticket-num">
			               		<span class="f16 coupon-name"></span>
				                <div class="f12 grey">有效期至  <span class="coupon-useEndDate"></span></div>
			                </li>
			            </ul>
			        </div>
		        </div>
			</section>
			
			<section class="sectionBox ptb10 border-radius-bottom bggrey">
			    <div class="m010 mtop10" id="btnDiv"><input class="btn-submit btnSubmit btn-next bgred noborder white btn01 dsn" id="payBtn" onclick="payParkingFee()" type="button" value="确认缴费" /></div>
				<div class="m010 mtop10"><input class="btn-submit btnSubmit btn-next bgdarkblue noborder white btn02" type="button" value="请稍候" /></div>
				<div class="f14 mtop10 t-center">请在缴费后15分钟内出场</div>
			</section>
		</div>
	</body>
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/fastclick.js"></script>
	<script src="${resourcePath}/commonjs/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/js/getCouponList.js?v01"></script>
	<script>
		$(function(){
			new FastClick(document.body);
			if("${feeInfo.fee}"=="0" || "${feeInfo.fee}"=="0.0" || "${feeInfo.fee}"=="0.00"){
				$("#btnDiv").hide();
			}
			
			//监听WeixinJSBridge对象
			var listenWinxin = setInterval(function(){
				if(typeof WeixinJSBridge != 'undefined'){
					$(".btn02").addClass('dsn');
					$(".btn01").removeClass('dsn');
					clearInterval(listenWinxin);
				}
			},100)
		});
		
		function payParkingFee(){
			var x;
			if($('.item-check-box').is(':visible') && $('.item-check-box').hasClass('item-checked')){
				if(!confirm('是否要使用消费券支付？')){
					return;
				}
				x = {"carId":"${feeInfo.carId}", "carNum":"${feeInfo.plate}", "ucId": $('[name=cpId]').val()};
			} else {
				x = {"carId":"${feeInfo.carId}", "carNum":"${feeInfo.plate}"};
			}
			$("#payBtn").attr("disabled", "disabled");
			jQuery.ajax({
	            url: "${pageContext.request.contextPath}/temporaryParkingFee/payParkingFee.html",//ajax调用微信统一接口获取prepayId
	            cache: false,
			    dataType:"json",
				async:false,
	            data:x,
	            success:function(data) {
	                   var obj = jQuery.parseJSON(data);
	                   if(obj.status !=undefined && "0000"!=obj.status){//提交订单失败
	                	   $("#payBtn").removeAttr("disabled");
	                	   alert(obj.message);
	                   	   return;
	                   }
	                   if(obj.free){
	                	   window.location.href = "${pageContext.request.contextPath}/temporaryParkingFee/queryParkingFee.html?carNum=${feeInfo.plate}&orderId="+obj.orderId;
	                   } else {
	                	   try{
			                   WeixinJSBridge.invoke('getBrandWCPayRequest',{  
				   	                "appId":obj.appId,          // 公众号名称，由商户传入  
				   	                "timeStamp":obj.timeStamp,  // 时间戳，自 1970 年以来的秒数  
				   	                "nonceStr":obj.nonceStr,    // 随机串  
				   	                "package":obj.package,      // prepay_id=*** 
				   	                "signType":obj.signType,    // 微信签名方式:MD5  
				   	                "paySign":obj.paySign       // 微信签名  
			   	               }, function(res){     
			   	            	   $("#payBtn").removeAttr("disabled");
			   	                   if(res.err_msg == "get_brand_wcpay_request:ok") {
			   	                	   localStorage.lastPay = 'tempCar';
			   	                	   window.location.href = "${pageContext.request.contextPath}/temporaryParkingFee/queryParkingFee.html?carNum=${feeInfo.plate}&orderId="+obj.orderId;
			   	                   } else if(res.err_msg == "get_brand_wcpay_request:cancel"){
			   	                   	   $("#payBtn").removeAttr("disabled");
			   	                	   alert("支付已取消！");
			   	                   } else {
			   	                	   $("#payBtn").removeAttr("disabled");
			   	                	   alert("支付失败！");
			   	                   }
			   	              }); 
		                  } catch (err) {
							  $("#payBtn").removeAttr("disabled");
							  alert(err.message);
						  }
	                   }
	            },
	            error: function(data) {
	            	$("#payBtn").removeAttr("disabled");
	                alert("付款失败，请稍后再试");
	            }
	        });
		}
		
		/*计算停车时长*/
		var parkFreshId = setInterval(getParkingTime, 1000*60*5);// 5分钟刷新一次
		function getParkingTime(){
	        $.post("${pageContext.request.contextPath}/temporaryParkingFee/qryFee.html", {"carNum":"${feeInfo.plate}"}, function(data){
	        	if (data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>' ) {
	        		var parkingInfo = data.dataValue;
	        		if(parkingInfo.carTmpIsOpen!=0 && parkingInfo.carTmpIsOpen!='' && parkingInfo.carTmpIsOpen !='undefined'){
	        			$(".realFee").html(getFormatMoney(parkingInfo.fee-parkingInfo.couponAmount));
	        			$(".totalFee").html(getFormatMoney(parkingInfo.fee));
	        		} else {
	        			$(".realFee").html(getFormatMoney(parkingInfo.fee));
	        		}
	        		if(parkingInfo.prepaymentamount){
	        			$(".prepaymentamount").html("￥"+getFormatMoney(parkingInfo.prepaymentamount));
	        		} else {
	        			$(".prepaymentamount").html("￥0.00");
	        		}
	        		$(".parkingTime").html(parkingInfo.parkingTime);
	        		if(parkingInfo.fee<=0){
	    				$("#btnDiv").hide();
	    			} else {
	    				$("#btnDiv").show();
	    			}
	        	} else {
	        		window.clearInterval(parkFreshId);
	        		alert(data.message);
	        	}
	        });
		}
		
		function getFormatMoney(m){
			return m.toFixed(2);
		}
	</script>
</html>
