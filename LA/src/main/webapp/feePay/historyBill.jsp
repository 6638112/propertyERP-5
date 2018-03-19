<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="cleartype" content="on">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<link rel="dns-prefetch" href="//jiefangqu.com">

		<title>缴费记录</title>
		<link rel="stylesheet" href="../css/feepay.css">
	</head>

	<body class="pos-relative bggrey">
		<div id="wrapBox" class="sectionBox bggrey pos-relative minheight100vh-minu-footer mtop10" v-cloak>
			<div class="ptb10 t-center grey" v-if="hasNoBills">暂无缴费记录</div>
			<div class="displaybox bgwhite m010 box-shadow-simple p10 boxalign-start" :class="index !== 0 ? 'mtop10':''" v-for="(item, index) in dataValue">
				<div class="mright10">
					<img class="w40" :src="item.picUrl" >
				</div>
				 <div class="boxflex01 f14 grey">
				 	<ul class="displaybox">
				 		<li class="boxflex01">
				 			<div>
				 				<span class="bold black f16">{{item.typeName}}</span> 
				 				<span class="fee-status radius2 mleft5 mtop3 completed" v-if="item.status === 1">充值完成</span>
				 				<span class="fee-status radius2 mleft5 mtop3 grey" v-if="item.status !== 1">正在充值</span>
			 				</div>
				 			<div class="f12">时间：{{item.payTime}}</div>
				 		</li>
				 		<li class="f24 red">￥{{item.amount.toFixed(2)}}</li>
				 	</ul>
				 	<ul class="displaybox mtop10" v-if="item.amtBalance !== null && item.amtBalance !== undefined">
				 		<li class="w60 justify height24">可用余额</li>
				 		<li>：</li>
				 		<li class="boxflex01 t-right">￥{{item.amtBalance.toFixed(2)}}</li>
				 	</ul>
					 <ul class="displaybox" :class="(item.amtBalance === null || item.amtBalance === undefined) ? 'mtop10':''">
				 		<li class="w60 justify height24" v-if="item.typeName === '手机充值'">手机号</li>
				 		<li class="w60 justify height24" v-if="item.typeName === '固话充值'">固话号码</li>
				 		<li class="w60 justify height24" v-if="item.typeName !== '手机充值' && item.typeName !== '固话充值'">户号</li>
				 		<li>：</li>
				 		<li class="boxflex01 t-right">{{item.changeObject}}</li>
				 	</ul>
				 	<ul class="displaybox boxalign-start" v-if="item.typeName !== '手机充值' && item.typeName !== '固话充值'">
				 		<li class="w60 justify height24">地址</li>
				 		<li>：</li>
				 		<li class="boxflex01 t-right mleft5">{{item.address}}</li>
				 	</ul>
				 </div>
			</div>
			<div class="service_call bggrey t-center"><a class="blue" href="tel:13217328487">联系客服</a></div>
		</div>

		<script src="${resourcePathHttps}/commonjs/vue.2.3.0.min.js"></script>
		<script src="${resourcePathHttps}/commonjs/axios.0.16.1.min.js"></script>
		<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
		<script src="../js/method.common.js"></script>
		<script src="../js/vue.feebill.js?20171116"></script>
	</body>

</html>