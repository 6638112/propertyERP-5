<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>解放区租车券</title>
<link rel="stylesheet" href="../css/prize.common.css">
</head>

<body>
<section class="sectionBox">
	<ul class="jfq-quan">
    	<li class="quan-money">￥<%=request.getParameter("hbAmount").substring(0, request.getParameter("hbAmount").indexOf(".")) %></li>
        <li class="quan-date">有效期至：
          <%
            String expiryTimeLong = request.getParameter("expiryTimeLong");
			Date expiryTime = new java.util.Date(Long.parseLong(expiryTimeLong));
			out.write(new SimpleDateFormat("yyyy-MM-dd").format(expiryTime)); 
		  %>
        </li>
    </ul>
</section>
<section class="sectionBox">
	<div class="common-info f14 bordertopgrey " align="center">兑换码：<span class='red'><%=request.getParameter("exchCode") %></span></div>
</section>
<section class="sectionBox">
	<div class="common-title f14 bordertopgrey borderbottomgrey">使用说明</div>
</section>
<section class="sectionBox">
	<div class="common-info f14">
	1.本券只限凹凸共享租车新用户使用（首次预订用户）；<br>
	2.本券价值200元（含保险），可抵租车时200元租金及保险费用；<br>
	3.每位用户仅限使用一次，不找零，不兑现，不可与其他优惠券叠加使用；<br>
	4.服务城市：上海，北京，广州，深圳，杭州，南京等（具体城市以APP中显示为准）；<br>
	5.体验券充值激活后3个月内使用有效，过期作废；<br>
	6.本活动最终解释权归凹凸共享租车所有。客服电话：400-668-0202<br>
	使用流程：<br>
	1．下载凹凸共享租车APP，完成注册；<br>
	2．上传身份证与驾照，完成认证<br>
	3．进入“我是租客”-“钱包”-“充值”- 输入体验码完成充值 – 获得优惠<br>
	适用地区<br>
	上海，北京，广州，深圳，杭州，南京，重庆，海口，青岛，成都，西安，三亚
	</div>
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
<!-- <section class="sectionBox fixed-info">
    <a class="fixed-text bordertopgrey" href="../product/index.do" target="_blank">使用消费券购物</a>
</section> -->

<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	})
</script>
<script src="../js/prize.common.js?0706"></script>
</body>
</html>