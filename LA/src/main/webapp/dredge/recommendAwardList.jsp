<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>推荐奖励</title>
<link rel="stylesheet" href="css/recommendaward.css?v20160325">
</head>

<body <c:if test="${ fn:length(dataValue.canWithdrawList) > 0 }">  class="bggrey" </c:if> >
<section id="wrapBox" class="sectionBox pos-relative minheight100 <c:if test="${ fn:length(dataValue.canWithdrawList) > 0 }"> bggrey </c:if> ">
	<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
		<section class="sectionBox">
			<ul class="bgredgradual white recommend-list">
				<li class="mtop5 f14 opacity75">可提款奖励(元)</li>
				<li class="myAmountPrice">${dataValue.canWithdrawAmt }</li>
				<li class="bordertopgrey opacity30 m0"></li>
				<li class="last f14 opacity75 overhidden">
					<c:choose>
						<c:when test="${empty dataValue.bankCard }">
							<a id="bankCardInfo" class="white displaybox" href="bindingBankCard.do">
								<div class="boxflex01">银行卡：<span id="bankCardNum">未绑定</span></div>
								<div class="t-right box-arrow-white">立即绑定</div>
							</a>
						</c:when>
						<c:otherwise>
							<a class="white displaybox" href="qryBankCardList.do">
								<div class="boxflex01">银行卡：${dataValue.bankCard }</div>
								<div class="t-right box-arrow-white"></div>
							</a>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		    <div class="list-box bggrey borderbottomgrey">
		        <div class="item-standard-name f14 grey">推荐奖励明细</div>
		    </div>
		    
		    <c:choose>
		    	<c:when test="${empty sessionScope.regist3rdResponse.mobile }"><!-- 无手机号状态，显示内容 -->
					<ul class="p20 t-center overhidden">
						<li class="mtop5"><img class="wp15" src="images/nophone-img.png" /></li>
						<li class="mtop5 f16 grey">绑定手机查看我的推荐奖励</li>
						<li class="mtop30 f18"><a class="disblock wp50 red margin_auto" href="bindingPhoneNum_to_personalCenter.jsp">立即绑定 ></a></li>
					</ul>
		    	</c:when>
		    	<c:otherwise><!-- 有手机号状态 -->
		    		<c:choose>
		    	<c:when test="${fn:length(dataValue.canWithdrawList)==0 }">
				    <!-- 无订单状态，显示内容 -->
					<ul id="noOrderListBox" class="p20 t-center">
						<li class="mtop5"><img class="wp20" src="images/icon-RMB.png" /></li>
						<li class="mtop5 f16 grey">您还没有可以提款<br>的奖励明细</li>
						<li class="mtop15 f14"><a class="red" href="recommendDesc.jsp">如何获得奖励？</a></li>
					</ul>
		    	</c:when> 
		    	<c:otherwise>
				    <!--有订单状态，显示内容-->
				    <div class="cash-out-order-list-box borderbottomgrey" data-next="true">
				    	<form class="inputform" action="applyWithdraw.do">
					    	<c:forEach items="${dataValue.canWithdrawList}" var="item" >
					    		<input type="hidden" name="dredgeBillId" value="${item.dredgeBillId }"> 
							    <a class="cash-out-order-item" href="payListDetails.jsp?dredgeType=${item.dredgeType }&totalAmt=${item.totalAmt }&billAmt=${item.billAmt }&payTime=${item.payTime}&submitDate=${item.submitDate}&billAddress=${item.billAddress}&userMobile=${item.userMobile}&referrerMobile=${item.referrerMobile}">
									<ul class="cash-out-order-list mleft15 borderbottomgrey">
										<li class="mtop5 f16 icon-order-list"><span class="left dredgeType">${item.dredgeType }</span><span class="right red f20">￥<span class="singleOrderPrice subsidyAmt">${item.totalAmt }</span></span></li>
										<li class="f14 grey"><span class="left billAddress">${item.billAddress }</span><span class="right">金额:￥<span class="billAmt">${item.billAmt }</span></span></li>
										<li class="f14 grey">${item.submitDate }</li>
									</ul>
								</a>
					    	</c:forEach>
				    	</form>
				    </div>
		    	</c:otherwise>
		    </c:choose>
		    	</c:otherwise>
		    </c:choose>
		    
		    <section class="divide-box pb56 bggrey <c:if test="${empty sessionScope.regist3rdResponse.mobile or fn:length(dataValue.canWithdrawList)== 0 }">bgwhite</c:if>  "></section>
		    <ul class="bottom-menu-box displaybox t-center">
		        <li class="p00 boxflex01">
		        	<a class="pos-relative" href="viewWithdrawList.do?page=1&pageNum=10">
		        		<input class="btn-submit btn-next noradius bordertbgrey black" type="button" name="button" value="提款记录">
		        		<!-- <span class="tip-nums cash-out-dot"></span> -->
		        	</a>
		        </li>
		        <li class="p00 boxflex01 borderleft">
		        <input class="btn-submit btn-next noradius bordertbgrey btnSubmit 
		        	<c:if test="${fn:length(dataValue.canWithdrawList)>0 }">red 
			        	<c:choose>
							<c:when test="${empty dataValue.bankCard }">nobankCardBtn</c:when>
							<c:otherwise>cashOutBtn</c:otherwise>
						</c:choose>
		        	</c:if>"
		        	 
					type="button" name="button" value="提款">
				</li>
		    </ul>
		
		</section>
	</section>
	
	
	<section class="sectionBox wrap-bg pop-box02 dsn">
		<div class="tips-box">
			<div class="t-center ptb20 borderbottomgrey">
				<div class="marb15 f18">提款信息确认</div>
				<div class="wp90 t-left margin_auto">金额：<span class="bankCardPerson">${dataValue.canWithdrawAmt }元</span></div>
				<div class="wp90 t-left margin_auto">卡号：<span class="bankCardNum">${dataValue.bankCard }</span></div>
			</div>
			<ul class="displaybox">
				<li class="boxflex01 ptb10 t-center back-btn">取消</li>
				<li class="boxflex01 ptb10 t-center red borderleft pay-check-btn"><a class="disblock red" href="javascript:void(0)">确认提款</a></li>
			</ul>	
		</div>
	</section>
	
	<section class="sectionBox wrap-bg pop-box03 dsn">
		<form class="inputform">
			<div class="tips-box">
				<div class="t-center ptb20 borderbottomgrey">
					<img class="wp20" src="images/icon-bankcard01.png" alt="" />
					<div class="f14">提款前需要绑定一张<br>您本人的银行卡</div>
				</div>
				<ul class="displaybox">
					<li class="boxflex01 ptb10 t-center back-btn f16">稍后绑定</li>
					<li class="boxflex01 ptb10 t-center red borderleft pay-check-btn f16"><a class="disblock red" href="bindingBankCard.do">立即绑定</a></li>
				</ul>	
			</div>
		</form>
	</section>
	
</section>

<script src="js/jquery-1.11.2.min.js"></script>
<script src="js/Validform_v5.3.2.js"></script>

<script>
$(function(){
	$('.cashOutBtn').click(function(){
		$('#wrapBox').addClass('heightp100');
		$('.pop-box02').removeClass('dsn');
	});
	
	$('.nobankCardBtn').click(function(){
		$('#wrapBox').addClass('heightp100');
		$('.pop-box03').removeClass('dsn');
	});
	
	$(".pay-check-btn").click(function(){
		//跳转
		window.location.href = 'applyWithdraw.do';
	});
	
	$('.back-btn').click(function(){
		$('#wrapBox').removeClass('heightp100');
		$(this).parents('.wrap-bg').addClass('dsn');
	});
	
});
</script>
</body>
</html>