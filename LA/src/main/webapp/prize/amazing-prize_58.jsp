<%@page import="com.cnfantasia.jfq.share.constant.ShareConstant"%>
<%@page import="com.cnfantasia.pub.constant.CookieConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.cnfantasia.server.business.pub.utils.CookieUtil" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>58到家家政服务券</title>
<base href="." target="_blank">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0; user-scalable=no">
<link rel="stylesheet" href="<%=request.getContextPath()%>/prize/css/prize.common.css">

</head>
<body>
<section class="sectionBox lineheight0"><img src="<%=request.getContextPath()%>/prize/images/daojia-quan.jpg" /></section>
<section class="divide-box bordertopgrey borderbottomgrey"></section>
<section class="sectionBox">
    <ul class="use-date f16 disbox">
        <li>有效期</li>
        <li class="t-right">2015.06.30</li>
    </ul>
</section>
<section class="sectionBox">
	<div class="common-title f14 bordertopgrey borderbottomgrey">使用说明</div>
</section>
<section class="sectionBox">
	<div class="common-info f14">点击底部“使用兑换券”直接预定家政服务。</div>
</section>
<section class="sectionBox">
	<div class="common-title f14 bordertopgrey borderbottomgrey">下单指南</div>
</section>
<section class="sectionBox">
	<div class="common-info f14"><span class="bold">第1步：领券。</span><br/>输入手机号码获取现金抵用券；<br/><span class="bold">第2步：下单。</span><br/>点击“立即下单”— 选择城市 — 选择服务地点 — 选择 — 服务时段 — 选择联系电话 — 选择代金券；<br/>下单成功！等待保洁员上门服务。</div>
</section>
<section class="sectionBox">
	<div class="common-title f14 bordertopgrey borderbottomgrey">持券须知</div>
</section>
<section class="sectionBox">
	<div class="common-info f14">本代金券可直接抵扣50元日常保洁服务费，超出部分按30元/小时收取；<br/>一次服务限使用一张；<br/>日常保洁服务时间2小时起，需阿姨自带清洁剂另加5元/小时；<br/>请提前2天预约；本代金券不得兑换现金，不设找零；<br/>代金券不能与其他优惠一起使用。</div>
</section>
<section class="sectionBox">
	<div class="common-title f14 bordertopgrey borderbottomgrey">联系我们</div>
</section>
<section class="sectionBox">
    <ul class="use-date f16 disbox">
        <li>客服电话</li>
        <li class="t-right"><a class="darkblue" href="tel:400-960-2228">400-960-2228</a></li>
    </ul>
    <ul class="use-date f16 disbox user-qq bordertopgrey">
        <li>用户Q群</li>
        <li class="t-right darkblue">384432710</li>
    </ul>
</section>
<section class="divide-box bordertopgrey borderbottomgrey" style="padding-bottom:56px;"></section>
<section class="sectionBox fixed-info">
    <a class="fixed-text bordertopgrey" style="line-height:25px;" href="http://t.cn/R2oYnIb?qq-pf-to=pcqq.c2c" target="_blank">使用兑换券</a>
</section>

<script src="<%=request.getContextPath()%>/prize/js/jquery-1.11.2.min.js"></script>
<script src="<%=request.getContextPath()%>/prize/js/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	});
</script>
<script src="<%=request.getContextPath()%>/prize/js/prize.common.js"></script>
</html>