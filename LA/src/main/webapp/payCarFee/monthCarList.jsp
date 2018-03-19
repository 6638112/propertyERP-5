<%-- 
    Document   : 新增车辆信息成功
    Created on : 2016-3-16, 13:20:24
    Author     : Liyl lyl010991@126.com
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<link rel="dns-prefetch" href="//jiefangqu.com">
		<title>停车缴费</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopping.common.css">
	</head>
	<body class="bggrey">
		<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100">
			<form class="inputform">
				<section class="sectionBox">
					<%-- <div class="lineheight0"><a href="${pageContext.request.contextPath}/appDownload/index.jsp"><img src="${pageContext.request.contextPath}/images/banner.png" /></a></div> --%>

					<%--<div class="lineheight0"><a href="${adUrl}"><img src="${pageContext.request.contextPath}/images/wash_car.png" /></a></div>--%>
					<div class="lineheight0"><a class="get-ad-click" data-title="轻应用月卡车缴费顶部广告"  href="https://www.xhqb.com/bt/btsfpp.html?appChannel=zhw_jiefangqu07"><img src="../images/zhihuangwang.png" /></a></div>

					<section class="divide-box"></section>
			        <ul id="carTab" class="displaybox bgwhite t-center ptb10 f14">
			            <li class="boxflex01 borderright"><a class="black on" href="javascript:void(0)">月卡缴费</a></li>
			            <li class="boxflex01"><a class="black" href="${pageContext.request.contextPath}/temporaryParkingFee/goFeePage.html">临停缴费</a></li>
			        </ul>
			        
			    	<c:forEach items="${carFees}" var="carFeeList" varStatus="i">
			    		<c:choose>
						   <c:when test="${i.index==0}">  
						       <section class="divide-box bordertbgrey"></section>
						   </c:when>
						   <c:otherwise> 
						       <section class="divide-box borderbottomgrey"></section>
						   </c:otherwise>
						</c:choose>
						
			    		<c:forEach items="${carFeeList.value}" var="carFee" varStatus="k">
						    <c:if test="${k.index==0}">  
							    <div class="list-box borderbottomgrey">
							        <div class="item-standard-name f14 grey">
							        	<span class="left">${carFeeList.key}</span>
							        	<span class="right"><a href="${pageContext.request.contextPath}/payCarFee/queryCarFeeHistory.do?groupBuildingId=${carFee.groupBuildingId}">往期账单</a></span>
							        </div>
							    </div>
							    <div class="cash-out-order-list-box borderbottomgrey">
									<ul class="cash-out-order-list mleft15">				
										<li class="f16 icon-portcar-bill"><span class="left">停车费账单</span><span class="right"></span></li>
			    		    </c:if>
							<c:choose>
					    		<c:when test="${k.index<fn:length(carFeeList.value)-1}"> 
					    			<li class="property-bill-list mtop10 borderbottomgrey">
					    		</c:when>
								<c:otherwise> 
									<li class="property-bill-list mtop10">
			    				</c:otherwise>
							</c:choose>	
							<c:choose>
							   <c:when test="${carFee.validdays<=3}">  
										<div class="f14 displaybox height28">
											<span class="boxflex01 red f20">￥<span class="singleOrderPrice">${carFee.fee}</span></span>
											<span class="boxflex01 t-right"><a href="${pageContext.request.contextPath}/cart/queryCarFeeDetail.do?groupBuilding=${carFee.groupbuildingName}&carNum=${carFee.platenumber}&carId=${carFee.id}&validDate=${carFee.expiredate}&cardname=${carFee.cardname}"><input class="btn-pay-property btn-next" type="button" value="续费" /></a></span>
										</div>
										<div class="grey f14">${carFee.platenumber}  ${carFee.cardname}
											<c:choose>
											   <c:when test="${carFee.validdays>0}">  
											       <span class="red">${carFee.validdays}天</span>后到期
											   </c:when>
											   <c:when test="${carFee.validdays==0}">  
											       <span class="red">今天</span>到期
											   </c:when>
											   <c:otherwise> 
											                    已过期<span class="red">${-carFee.validdays}天</span>
											   </c:otherwise>
											</c:choose>
										</div>
									</li>
							   </c:when>
							   <c:otherwise> 
										<div class="f14 displaybox height28">
											<span class="boxflex01 f20">￥<span class="singleOrderPrice">${carFee.fee}</span></span>
											<span class="boxflex01 t-right"><a href="${pageContext.request.contextPath}/cart/queryCarFeeDetail.do?groupBuilding=${carFee.groupbuildingName}&carNum=${carFee.platenumber}&carId=${carFee.id}&validDate=${carFee.expiredate}&cardname=${carFee.cardname}">查看详情</a></span>
										</div>
										<div class="grey f14">${carFee.platenumber}  ${carFee.cardname}
											<c:choose>
											   <c:when test="${carFee.validdays>0}">  
											       <span class="red">${carFee.validdays}天</span>后到期
											   </c:when>
											   <c:when test="${carFee.validdays==0}">  
											       <span class="red">今天</span>到期
											   </c:when>
											   <c:otherwise> 
											                    已过期<span class="red">${-carFee.validdays}天</span>
											   </c:otherwise>
											</c:choose>
										</div>
									</li>
							   </c:otherwise>
							</c:choose>		
							<c:if test="${k.last}">
									</ul>
							    </div>
							</c:if>
			    		</c:forEach>
			    	</c:forEach>
			    	
			    	<c:if test="${fn:length(carFees) > 0}">
					    <section class="divide-box pb48 bggrey"></section>
					    <ul class="bottom-menu-box displaybox t-center height40 bordertopgrey box-shadow-top">
				        	<li class="p00 boxflex01"><a class="pleft25 f16 icon-car red" href="${pageContext.request.contextPath}/payCarFee/newCar.do">新增车辆</a></li>
					    </ul>
				    </c:if>
				</section>
				<c:if test="${fn:length(carFees)==0}">
					<section class="sectionBox mtop5 bggrey">
						<section class="m10 bgwhite ptb10 radius2 box-shadow">
							<ul class="displaybox">
								<li class="m010"><img class="disblock" style="width: 36px;" src="../images/icon_car.png" /></li>
								<li class="boxflex01 f14">我的车辆信息</li>
							</ul>
							<ul class="t-center grey mtop5">
								<li>点击按钮添加车辆</li>
								<li>即可查看您在管理处登记的车辆信息</li>
							</ul>
							<section class="sectionBox wp100 ptb10 border-radius-bottom">
							    <div class="wp72 mtop5">
							    	<input class="btn-submit btnSubmit btn-next height40 box-shadow-red bgred noborder white" onclick="location.href='${pageContext.request.contextPath}/payCarFee/newCar.do'" type="button" value="添加车辆">
							    </div>
							</section>
						</section>
					</section>
				</c:if>
			</form>
		</section>
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/fastclick.js"></script>
		<script src="${pageContext.request.contextPath}/js/ad.clicks.js"></script>
		<script>
			$(function(){
				new FastClick(document.body);
			});
		</script>
	</body>
</html>