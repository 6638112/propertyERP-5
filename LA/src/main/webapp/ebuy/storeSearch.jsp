<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="../error/404.jsp" %>
<%@page import="java.util.Calendar"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	String storeId = request.getParameter("storeId");
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="cleartype" content="on">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">
<title>搜索店内商品</title>
<link type="text/css" rel="stylesheet" href="../css/common.css?v20170914">
</head>
<body class="pos-relative heightp100">
<!-- 搜索框 -->
<div class="sectionBox">
	<ul class="displaybox ptb10 bgwhite">
		<li id="goBackBtn" class="p10 f14">取消</li>
		<li class="boxflex01">
			<input id="storeSearch" class="border-left-radius border-right-radius0 noborder bggrey02 wp100 ptb8 indent10 f14" type="text" placeholder="搜索店内商品" style="height: 33px;" />
		</li>
		<li class="btn-search f14">搜索</li>
	</ul>
</div>
<div class="sectionBox pos-relative search-list" style="padding: 0 0 20px 0; z-index: 1; overflow: scroll; position: fixed; top: 54px; bottom: 0;">
   	<ul class="displaybox boxalignend pos-relative mtop10 pt10 store-item dsn">
   		<li class="store-item-img radius4 overhidden" prdtId="1">
   			<a class="item-link" data-itemid="121895" href="#">
   				<img class="bgloading bgsize110" data-original="../images/prev_img.jpg" src="../images/pixel.gif">
   			</a>
   		</li>
   		<li class="boxflex01 f16 m010 mb5 lineheight110">
   			<a class="item-link" data-itemid="121895" href="#" target="_blank">
    			<div class="store-item-title">
     				<div class="store-item-name lineheight140 word-break1">越南红肉火龙果500g</div>
    				<div class="store-item-desc mtop2 f12 grey word-break1">爽口甘甜 清凉一夏</div>
   				</div>
    			<div class="red"><span class="f12">优选价 </span></div>
    			<div class="red">￥<span class="store-item-price">29.90</span> <span class="line-through f12 grey">￥<span class="market-price">39.90</span></span></div>
    		</a>
    		<div class="store-supplier grey f12 dsn">来自<a class="supplier-name mleft10 grey" href="javascript:void(0)">解放区平台</a></div>
   		</li>
   	</ul>
	<div class="p010 item-list-box">
	
	</div>
</div>
<div class="sectionBox loading grey mtop15 hide"><img src="../images/loading01.gif" /> 加载中…</div>
<section class="pop-tips dsn">
	<div class="pop-tips-text">请选择</div>
</section>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.lazyload.js"></script>
<script src="../js/method.common.js"></script>
<script src="../js/store.search.js?v20171108"></script>
</body>
<script type="text/javascript">
	var cnzz_s_tag = document.createElement('script');
	cnzz_s_tag.type = 'text/javascript';
	cnzz_s_tag.async = true;
	cnzz_s_tag.charset = 'utf-8';
	cnzz_s_tag.src = 'https://s11.cnzz.com/z_stat.php?id=1261123817&async=1';
	var root_s = document.getElementsByTagName('script')[0];
	setTimeout(function(){
		root_s.parentNode.insertBefore(cnzz_s_tag, root_s);
	},4000);
</script>
</html>