<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>店铺设置</title>
<link rel="stylesheet" href="../css/merchant/shopping.common.css">
</head>

<body class="bggrey">
<section id="wrapBox" class="sectionBox pos-relative bggrey minheight100" data-ownerAuditStatus="${merchantSupply.storeAuditStatus }">
	<c:if test="${merchantSupply.storeAuditStatus==2 }">
		<section class="sectionBox item-status-desc bgred item-details-info">${merchantSupply.storeAuditDesc }</section>
	</c:if>
	
	<section class="tabBox pos-relative">
	    <a href="settingInfo.html">
	        <ul class="displaybox setting-head-box">
	            <li class="setting-head pos-relative">
	                <div class="setting-head-img-box"><img class="setting-head-img wh50" src="${merchantSupply.storePicStr}" /></div>
	               <c:if test="${merchantSupply.storeAuditStatus==1 }"><img class="checkDone-icon" src="../images/checkDone-icon.png" /></c:if>
	            </li>
	            <li class="setting-text boxflex01">
	            	<span class="f16">${merchantSupply.name}
		            	<c:choose>
		            		<c:when test="${merchantSupply.storeAuditStatus==0 }">
		            			<span class="bgorange f12 mleft10 check-status">待审核</span>
		            		</c:when>
		            		<c:when test="${merchantSupply.storeAuditStatus==2 }"><span class="bgorange f12 mleft10 check-status">审核失败</span></c:when>
		            	</c:choose>
	            	</span><br>
	            	<span class="f14">${merchantSupply.address}</span></li>
	            <li class="box-arrow"></li>
	        </ul>
	    </a>
	    <section class="divide-box bordertbgrey"></section>
	    <div class="list-box bgwhite">
	        <a class="displaybox" href="settingCheck.html">
	            <div class="item-standard-name f16 boxflex01 list-icon list-icon01">店主认证</div>
	            <div class="boxflex01 box-arrow t-right grey">
	            	<c:if test="${merchantSupply.ownerAuditStatus ==1}">已认证</c:if>
	            	<c:if test="${merchantSupply.ownerAuditStatus ==0}">待认证</c:if>
	            	<c:if test="${merchantSupply.ownerAuditStatus ==2}">未通过</c:if>
	            </div>
	        </a>
	    </div>
	    <section class="divide-box bordertbgrey"></section>
	    <div class="list-box bgwhite">
	        <a class="displaybox" href="settingServer.html">
	            <div class="item-standard-name f16 boxflex01 list-icon list-icon02">客户服务</div>
	            <div class="boxflex01 box-arrow t-right grey"></div>
	        </a>
	    </div>
	    <section class="divide-box bordertbgrey"></section>
	    <div class="list-box bgwhite">
	        <a class="displaybox" href="editFreight.html">
	            <div class="item-standard-name f16 boxflex01 list-icon list-icon04">运费设置</div>
	            <div class="boxflex01 box-arrow t-right grey"></div>
	        </a>
	    </div>
		<c:if test="${openCarCoupon == 1}">
			<div class="list-box bgwhite">
				<a class="displaybox" href="goToCarCoupon.html">
					<div class="item-standard-name f16 boxflex01 list-icon list-icon05">停车优惠券</div>
					<div class="boxflex01 box-arrow t-right grey"></div>
				</a>
			</div>
		</c:if>

	    <section class="divide-box bordertbgrey"></section>
	    <div class="list-box bgwhite">
	        <a class="displaybox" href="toPage.html?page=changePassword1&type=3">
	            <div class="item-standard-name f16 boxflex01 list-icon list-icon03">修改密码</div>
	            <div class="boxflex01 box-arrow t-right grey"></div>
	        </a>
	    </div>
	    <section class="divide-box bordertbgrey"></section>
	    <div class="list-box bgwhite borderbottomgrey t-center">
	        <a id="logoutBtn" class="height36 f16 disblock" href="logout.html?initPage=false">退出登录</a>
	    </div>
	    <section class="divide-box height75"></section>
	    
	    <ul class="bottom-menu-box displaybox t-center bordertopgrey">
	    	<ul class="bottom-menu-box displaybox t-center bordertopgrey">
	    	<li><a href="myOrder.html"><span class="menu-icon01"></span>我的订单</a></li>
	    	<li><a href="itemManage.html"><span class="menu-icon02"></span>商品管理</a></li>
	    	<!--<li><a href="#"><span class="menu-icon03"></span>账务中心</a></li>-->
	    	<li class="on"><a href="setting.html"><span class="menu-icon04"></span>店铺设置</a></li>
	        <li class="boxflex01"><a href="settleCenter.html"><span class="menu-icon03"></span>结算中心</a></li>
	    </ul>
	    </ul>
	</section>
	<section id="popBox" class="sectionBox wrap-bg hide"></section>
</section>

<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	})
</script>
<script src="../js/merchant/getTipsBox.js"></script>
</body>
</html>