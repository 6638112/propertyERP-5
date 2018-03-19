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

<title>解放区电影券</title>
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
	1、此兑换码在全国926家影院通用，详情点击“查看支持影院”；<br/>
	2、此兑换码限兑换2D电影票，看3D电影需根据各影院定价政策进行补差价；<br/>
	3、此兑换码可直接在影院柜台兑换纸质影票；<br>
	4、使用此码，登录“卖座网”官方网站，即可进行座位预定。
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
<section class="sectionBox fixed-info">
    <a class="fixed-text bordertopgrey" href="http://api.jiefangqu.com:8080/API/htmlPages/cinemaSearch/cinemaSearch.htm" target="_blank">查看支持影院</a>
</section>

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