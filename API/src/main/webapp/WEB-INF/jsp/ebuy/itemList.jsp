<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cnfantasia.server.api.pub.header.HeaderConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="HandheldFriendly" content="True">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">
<title>解放区超市</title>
<link rel="stylesheet" href="../css/item-list.css?20170215">
</head>
<body>
<section class="sectionBox relative_box">
    <section class="tabBox pos-relative">
        <div class="bd mtop10" id="tabBox-bd">
            <div class="con" data-page="1" data-next="true">
	            <li class="boxflex01 item-single dsn">
	            	<div class="item-list-small">
	                    <a class="single-item-btn" data-id="106369" href="javascript:void(0)"><div class="item-list-img"><img class="scrollLoading imgloading" data-url="images/beefxxx.jpg" src="../images/pixel.gif" /></div><p class="item-name">澳洲Perfit中西式套餐（MINI款）</p></a>
	                    <div class="red f16 bold mtop5 m010">￥<span class="priceOnShelf">198.00</span></div>
	                    <div class="mtop5 m010">
	                        <div class="market-price-small left">￥<span class="priceMarket">368.00</span></div>
	                        <div class="right join-btn join-shoppingCart-btn" data-id="106369"><img src="../images/shoppingcart-green.png" /></div>
	                    </div>
	                </div>
	            </li>
	            <ul class="item-list displaybox newCreate dsn"></ul>
                <div id="itemListBox" class="p010"></div>
            </div>
        </div>
    </section>
    <section class="divide-box footpadding"></section>
    <footer class="absolute_foot">
    	<p>Copyright <span>&copy;</span> 2014 深圳前海邻里乐科技服务有限公司 <br />All rights reserved.</p>
    </footer>
</section>
<div class="sectionBox loading grey bggrey hide" style="width:auto;"><img style="display:initial" src="../images/loading01.gif" /> 加载中…</div>
<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/fastclick.js"></script>
<script src="../js/Validform_v5.3.2.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	});
</script>
<script src="../js/jquery.scrollLoading.js"></script>
<script src="../js/item-list.js?v20170213"></script>
</body>
</html>