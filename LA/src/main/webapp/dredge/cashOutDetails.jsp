<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>提款详情</title>
<link rel="stylesheet" href="css/recommendaward.css">
</head>

<body class="bggrey">
<section class="sectionBox">
	<section class="sectionBox bordertopgrey">
		<ul class="p20 t-center">
	    	<li class="mtop10">
	    		<img class="wp15 rotateZoomIn animated1s" src="images/icon-gathering-done.png" />
	    	</li>
	    	<li class="mtop10"><span class="f20 red">申请成功</span></li>
	    	<li class="mtop10 f20">${dataValue.billAmt }元</li>
	    </ul>
	</section>
	<section class="divide-box bgwhite user-info-title borderbottomgrey pb0">
		<span class="disblock bordertopgrey cash-out-info-text f12 black">
	    	<span class="list-name">订单合计</span><span class="right">${dataValue.billCount }笔</span><br/>
	        <span class="list-name">费用合计</span><span class="right">￥${dataValue.billAmt }</span><br/>
	        <span class="list-name">提款时间</span><span class="right">${dataValue.submitTime }</span><br/>
	        <span class="list-name">提款银行</span><span class="right">${dataValue.bankName }</span><br/>
	        <span class="list-name">提款帐号</span><span class="right">${dataValue.bankCardNumber }</span><br/>
	        <span class="list-name">提款状态</span><span class="right">${dataValue.tkStatus }</span>
	    </span>
	</section>

    <ul class="bottom-menu-box displaybox t-center">
        <li class="p00 boxflex01"><a href="personalCenter.do"><input class="btn-submit f14 blue bggrey" type="button" name="button" value="返回个人中心 >"></a></li>
    </ul>
</section>
</body>
</html>