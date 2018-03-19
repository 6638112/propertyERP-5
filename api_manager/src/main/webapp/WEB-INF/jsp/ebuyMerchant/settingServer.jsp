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

<title>客户服务</title>
<link rel="stylesheet" href="../css/merchant/shopping.common.css">
</head>

<body class="bggrey">
<header class="sectionBox fantasia-header order-top-bg">
	<a class="disblock mleft15 left" href="setting.html"><img class="back-arrow" src="../images/merchant/back-arrow.png" /></a>
    <div class="header-title">客户服务</div>
</header>
<section class="sectionBox">
    <section class="divide-box bordertbgrey"></section>
    <div class="list-box bgwhite">
        <div class="displaybox">
            <div class="item-standard-name height36 f16 boxflex01">客服电话</div>
            <div class="boxflex01 f16 t-right grey"><a class="green" href="tel:${merchantSupply.tel}">${merchantSupply.tel}</a></div>
        </div>
    </div>
    <section class="divide-box bordertbgrey"></section>
    <ul class="register-list borderbottomgrey">
        <li class="borderbottomgrey">
            <div class="displaybox">
                <div class="item-standard-name height36 f16 boxflex01">营业时间</div>
                <div class="boxflex01 f16 t-right grey">${merchantSupply.startTime}-${merchantSupply.endTime}</div>
            </div>
        </li>
        <%--
        <li>
            <div class="displaybox">
                <div class="item-standard-name height36 f16 boxflex01">营业周期</div>
                <div class="boxflex01 f16 t-right grey">${merchantSupply.tel}</div>
            </div>
        </li>
         --%>
    </ul>

    <section class="divide-box pb56"></section>
    <ul class="bottom-menu-box displaybox t-center">
        <li class="btn-submit btn-next noradius bordertbgrey btnSubmit p00" style="line-height:58px"><a class="red" href="toPage.html?page=settingServerEdit" >修改</a></li>
    </ul>

</section>
<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script>
$(function(){
	new FastClick(document.body);
})
</script>


</body>
</html>