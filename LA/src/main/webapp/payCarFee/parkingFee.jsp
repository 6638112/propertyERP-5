<%-- 
    Document   : 确认缴费
    Created on : 2016-3-16, 13:20:24
    Author     : Liyl lyl010991@126.com
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<link rel="dns-prefetch" href="//jiefangqu.com">
		<title>停车费账单</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopping.common.css">
		<style type="text/css">
			.bottomline{border-bottom: 1px solid #F1F0F2;}
		</style>
	</head>
	<body class="bggrey">
		<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100">
			<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
				<section class="sectionBox">
					<section class="divide-box10 bordertbgrey"></section>
					<ul class="register-list">
						<li>
							<div class="displaybox">
								<div class="item-standard-name height36 f16 boxflex01 carBill-icon01 pleft30">停放地址</div>
								<div class="boxflex01 f16 lineheight140 t-right">${groupBuilding}</div>
							</div>
						</li>
					</ul>
					<section class="divide-box10 bordertbgrey"></section>
					<ul class="register-list">
						<li class="borderbottomgrey">
							<div class="displaybox">
								<div class="item-standard-name height36 f16 boxflex01 carBill-icon02 pleft30">车辆信息</div>
								<div class="boxflex01 f16 lineheight140 t-right">${carNum}</div>
							</div>
						</li>
						<li>
							<div class="displaybox">
								<div class="item-standard-name height36 f16 boxflex01 pleft30">到期时间</div>
								<div class="boxflex01 f16 lineheight140 t-right">${validDate}</div>
							</div>
						</li>
					</ul>
					<c:if test="${isCanPay == 1 }">
						<section class="divide-box10 bordertbgrey"></section>
						<ul class="register-list">
							<li class="borderbottomgrey">
								<div class="displaybox">
									<div class="item-standard-name height36 f16 boxflex01 carBill-icon03 pleft30">本次缴费时长</div>
									<div class="boxflex01 f16 lineheight140">
										<select class="input-text wp100 list-arrow-down directionRtl pright20" id="payNum" onchange="setFee();">
											<c:forEach items="${carFeeTypeList}" var="item">
												<option value="${item.num}" fee="${item.fee}">${item.typeName}</option>
											</c:forEach>
											<%--<option value="1" fee="${fee*1}">1个月</option>
											<option value="3" fee="${fee*3}">3个月</option>
											<option value="6" fee="${fee*6}">6个月</option>
											<option value="12" fee="${fee*12}" <c:if test="${cardname eq '年卡'}">selected</c:if>>12个月</option>--%>
										</select>
									</div>
								</div>
							</li>
							<li>
								<div class="displaybox">
									<div class="item-standard-name height36 f16 boxflex01 pleft30">费用</div>
									<div class="boxflex01 f20 lineheight140 t-right red" id="totalFee">￥${fee*1}</div>
								</div>
							</li>
						</ul>
						<section class="sectionBox wp100 ptb10 border-radius-bottom bggrey">
							<div class="m010 grey">缴费后实时累计天数</div>
						</section>
						<c:if test="${needBillIsOpen}">
							<ul id="needBillBtn" class="register-list bordertbgrey">
								<li class="displaybox">
									<input id="isNeedBill" class="pos-absolute wp100 height36 opacity0" type="checkbox" name="checkbox" />
									<div class="icon-check"></div>
									<div class="item-standard-name height36 f16 boxflex01">需要发票</div>
								</li>
							</ul>
							<section class="divide-box10 bggrey"></section>
						</c:if>
						<section class="sectionBox wp100 border-radius-bottom bggrey <c:if test="${!needBillIsOpen}">mtop5"</c:if>">
						<div class="m010"><input class="btn-submit btnSubmit btn-next bgred noborder white" type="button" value="确认缴费" onclick="payCarNumFee()"/></div>
					</c:if>
				</section>
				<section class="divide-box10 bggrey"></section>
			</section>
		</section>
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/fastclick.js"></script>
		<script>
			$(function(){
				new FastClick(document.body);
				
				$('#needBillBtn').click(function(){
					$(this).find('.icon-check').toggleClass('on');
				});
			});
			
			function payCarNumFee(){
				var payNum = $.trim($("#payNum").val());
				var isNeedBill = 0;
				if($("#isNeedBill").length>0 && $("#isNeedBill").is(":checked")){
					isNeedBill = 1;
				}
				jQuery.ajax({
		            url: "${pageContext.request.contextPath}/payCarFee/payCarFee.do",//ajax调用微信统一接口获取prepayId
		            cache: false,
				    dataType:"json",
					async:false,
		            data:{"carId":"${carId}", "payNum":payNum, "needBill":isNeedBill},
		            success:function(data) {
		                   var obj = jQuery.parseJSON(data);
		                   if(obj.status !=undefined && "0000"!=obj.status){//提交订单失败
		                	   alert(obj.message);
		                   	   return;
		                   }
		                   WeixinJSBridge.invoke('getBrandWCPayRequest',{  
			   	                "appId":obj.appId,          // 公众号名称，由商户传入  
			   	                "timeStamp":obj.timeStamp,  // 时间戳，自 1970 年以来的秒数  
			   	                "nonceStr":obj.nonceStr,    // 随机串  
			   	                "package":obj.package,      // prepay_id=*** 
			   	                "signType":obj.signType,    // 微信签名方式:MD5  
			   	                "paySign":obj.paySign       // 微信签名  
		   	               }, function(res){     
		   	                   if(res.err_msg == "get_brand_wcpay_request:ok") {
		   	                	   localStorage.lastPay = 'monthCar';
		   	                	   window.location.href = "${pageContext.request.contextPath}/payCarFee/queryPayCarFeeResult.do?orderId=" + obj.orderId+"&cardname="+cardname;
		   	                   } else if(res.err_msg == "get_brand_wcpay_request:cancel"){
		   	                	   alert("支付已取消！");
		   	                   } else {
		   	                	   alert("支付失败！");
		   	                   }
		   	            }); 
		            },
		            error: function(data) {
		                alert("付款失败，请稍后再试");
		            }
		        });
			}
			
			function setFee(){
				var totalFee = $("#payNum").find("option:selected").attr("fee");
				$("#totalFee").html("￥"+totalFee);
			}
		</script>
	</body>
</html>