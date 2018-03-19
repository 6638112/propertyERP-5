<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>订单详情</title>
<link rel="stylesheet" href="css/recommendaward.css">
</head>
<body class="bggrey">
<section class="sectionBox bordertopgrey">
	<ul class="p20 t-center">
    	<li class="mtop10"><img class="wp15 rotateZoomIn animated1s" src="images/icon-gathering-done.png" /></li>
    	<li class="mtop10"><span class="f20 red">推荐成功</span></li>
    	<li class="mtop10 f20"><span class="f30">${param.totalAmt }</span> 元</li>
    	<li class="mtop10 grey f14">订单金额<span class="mleft10">￥ ${param.billAmt }</span></li>
    </ul>
</section>
<section class="divide-box bgwhite user-info-title recommend-details-info borderbottomgrey">
	<span class="disblock f14 black bordertopgrey">
    	<span class="list-name">实际费用</span><span class="right">￥${param.billAmt }</span><br/>
        <span class="list-name">服务类型</span><span class="right">${param.dredgeType }</span><br/>
        <span class="list-name">交易时间</span><span class="right">${param.payTime}</span><br/>
    </span>
	<span class="disblock f14 black bordertopgrey">
    	<span class="list-name">服务小区</span><span class="right">${param.billAddress}</span><br/>
        <span class="list-name">预约时间</span><span class="right">${param.submitDate}</span><br/>
        <span class="list-name">预约手机</span><span class="right">${param.userMobile}</span><br/>
        <span class="list-name">推荐人手机</span><span class="right">${param.referrerMobile}</span>
    </span>
</section>

</body>
</html>