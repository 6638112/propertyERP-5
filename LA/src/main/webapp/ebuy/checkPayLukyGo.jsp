<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<% 
	String jfqsource = request.getParameter("jfqsource");//用request得到 
	String limitbuyId = request.getParameter("id");
	String buyNum = request.getParameter("buyNum");
%>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">

<title>确认订单</title>
<link rel="stylesheet" href="../css/common.css?20170807">
<style type="text/css">
[v-cloak] {
  display: none;
}
</style>
</head>

<body class="heightp100 bggrey">
<form id="toPayForm" class="heightp100" action="../order/submitOrderMulti.do" method="post">
<input type="hidden" name="isSelfGet" value="0" />
<input type="hidden" name="couponId" value="" />
<div class="check-pay-box heightp100">
	<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100" v-cloak>
		<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
			<section class="sectionBox addressInfo f14">
		        <div class="m1510 displaybox">
		    		<div><img class="left mtop5 mright10" style="width: 14px;" src="../images/icon-address-map.png" /></div>
			        <ul class="boxflex01">
				        <!-- 促销商品收货地址 -->
		            	<li class="f16">
			            	<img class="right mtop20 mleft10" src="../images/arrow-right-white.png" />
			            	<span class="mright10 user-name">请完善收货人姓名</span> 
			            	<span class="user-phone">{{actDetail.mobile }}</span>
		            	</li>
		            	<li>自提点地址：{{actDetail.merchantName }}</li>
			        </ul>
		        </div>
			</section>
			<section class="divide-box borderbottomgrey borderbottomgrey"></section>
			<section class="sectionBox payList">
			   <div class="pay-list-info mleft10">
			       <div class="info ptb10">
			            <div class="tbl">
			                <span class="item-img-small01 inline-table"><img :src="actDetail.activityPicList[0] + '?x-oss-process=image/resize,m_fill,w_110,h_110,limit_0/format,jpg/interlace,1/quality,q_90'" /></span>
			                <span class="item-info tbl-cell">
			                    <div class="f14">{{actDetail.activityTitle}}</div>
			                    <div class="message"></div>
			                </span>
			                <span class="price-num tbl-cell">
			                	<span class="f14">￥<span class="limitBuyPrice">{{(actDetail.productPrice/100).toFixed(2) }}</span></span><br/><span class="grey">x<span class="item-qty">1</span></span>
			                </span>
			                <input type="hidden" name="productIdQty" />
			            </div>
			        </div>
			        
			        <ul class="item-price item-sub-total">
			            <li class="left">合计</li>
			            <li class="right"><span class="red bold f16">￥<span class="merchant-price">{{(actDetail.productPrice/100).toFixed(2) }}</span></span></li>
			        </ul>
			    </div>
			</section>
		    <section class="sectionBox">
			    <div class="exchang-fixed displaybox">
			        <div class="boxflex01 bggradient p010">
			        	<span class="f12">实付：</span><span class="red bold f16">￥</span>
				        <span class="red bold f16" id="shouldPay">{{(actDetail.productPrice/100).toFixed(2) }}</span>
			        </div>
			        <div class="btn-check-pay bgred">
		        		<a id="btnCheckPay" class="white disblock" href="javascript:void(0)" onclick="toPay();">确认付款</a>
			        </div>
			    </div>
		    </section>
		</section>
	</section>
	
	<section id="userInfoBox" class="sectionBox dsn">
	    <section class="divide-box borderbottomgrey"></section>
	    <ul class="register-list borderbottomgrey">
	        <li class="borderbottomgrey">
	            <div class="displaybox">
	                <div class="item-standard-name height36 f16 w50">收货人</div>
	                <div class="boxflex01 f16 t-right grey"><input id="userName" name="userName" class="input-text wp100 t-right" type="text" placeholder="收货人" value="" maxlength="100" datatype="*" nullmsg="请填写收货人！" /></div>
	            </div>
	        </li>
	        <li>
	            <div class="displaybox">
	                <div class="item-standard-name height36 f16 w50">联系方式</div>
	                <div class="boxflex01 f16 t-right grey"><input id="userPhone" class="input-text wp100 t-right"  name="userPhone" type="text" placeholder="手机号码" value="" maxlength="11" datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码格式！" /></div>
	            </div>
	        </li>
	    </ul>
	    <section class="divide-box"></section>
		<section class="sectionBox bggrey">
		    <div class="m010 mtop15"><input class="btn-submit btnSubmit btn-next bgred white" type="button" value="保存" /></div>
		</section>
	</section>
</div>

</form>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script> 
<script src="${resourcePathHttps}/commonjs/vue.2.3.0.min.js"></script>
<script src="${resourcePathHttps}/commonjs/axios.0.16.1.min.js"></script>
<script src="${resourcePathHttps}/commonjs/Validform_v5.3.2.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="../js/method.common.js"></script>
<script src="../js/vue.lukygo.detail.js"></script>
</body>
</html>