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
    <title>停车券发送</title>
    <link rel="stylesheet" href="../css/merchant/shopping.common.css">
</head>

<body class="bggrey">
<section id="wrapBox" class="sectionBox pos-relative bggrey minheight100">
    <div class="overhidden t-center">
        <div class="f16" style="margin-top: 11%;margin-bottom: 4%;color: red;">微信扫一扫、领取停车券</div>
        <div id="carCoupon"></div>
        <div style="margin-top: 4%;margin-bottom: 4%;">
            停车券剩余数量<span class="f14" style="margin-left: 2%;color: red;"><b>${cpNum }</b></span>
        </div>
    </div>
    <div class="m010">
        <p>停车券类型：<span style="color: red;">${name}</span></p>
        <p>适用小区：<span style="color: red;">园景园名苑、万丰园、湾厦福园</span></p>
        <p>适用说明：扫码关注解放区公众号之后即时到账，以上小区出车扫码缴费时可直接抵扣。</p>
    </div>
</section>

<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/merchant/jquery.qrcode.min.js"></script>
<script>
    var url = "${url}";
    $(function(){
        console.log("==="+url);
        if(url != "") {
            jQuery('#carCoupon').qrcode({width:300,height:300,correctLevel:0,text:url});
        }
    })
</script>
</body>
</html>