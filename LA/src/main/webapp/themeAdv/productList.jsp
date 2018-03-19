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
	<title></title>
	<link rel="stylesheet" href="../css/item-list.css">
</head>
<body>
<section class="sectionBox relative_box">
	<section class="tabBox pos-relative">
		<div id="shoppingCart" class="shopping-cart-index dsn">
			<a href="javascript:void(0)">
				<div id="cartNum" class="item-num bgwhite dsn"></div>
				<img src="../images/shoppingcart-white-big.png" />
			</a>
		</div>
		<div class="bd" id="tabBox-bd">
			<div class="con">
				<div class="showAd">
					<img id="advPic" style=" background: #ebebf3;" src="">
				</div>
				<div class="p10" id="probox">
				</div>

				<ul class="item-list displaybox dsn" id="productUl">
					<li class="boxflex01 pro-item01 dsn">
						<div class="item-list-small">
							<a class="single-item-btn" href="javascript:void(0)">
								<div class="item-list-img">
									<img class="bgloading" src="../images/pixel.gif" style=" max-height: 249px; background: #ebebf3;"/>
								</div>
								<p class="item-name"></p>
							</a>
							<div class="red item-priceOnShelf f16 bold mtop5 m010"></div>
							<div class="mtop5 m010">
								<div class="market-price-small left"></div>
								<div class="right join-btn join-shoppingCart-btn">
									<img src="../images/shoppingcart-green.png" />
								</div>
							</div>
						</div>
					</li>
					<li class="boxflex01 pro-item02 dsn">
						<div class="item-list-small">
							<a class="single-item-btn" href="javascript:void(0)">
								<div class="item-list-img">
									<img class="bgloading" src="../images/pixel.gif" style=" background: #ebebf3;"/>
								</div>
								<p class="item-name"></p>
							</a>
							<div class="red item-priceOnShelf f16 bold mtop5 m010"></div>
							<div class="mtop5 m010">
								<div class="market-price-small left"></div>
								<div class="right join-btn join-shoppingCart-btn">
									<img src="../images/shoppingcart-green.png" />
								</div>
							</div>
						</div>
					</li>
					<li class="boxflex01 emptyli dsn"></li>
				</ul>
			</div>
		</div>
	</section>
	<section class="divide-box footpadding"></section>
	<footer class="absolute_foot">
		<p>Copyright <span>&copy;</span> 2014 深圳前海邻里乐科技服务有限公司 <br />All rights reserved.</p>
	</footer>
</section>
<div class="sectionBox loading grey bggrey hide" style="width:auto;"><img style="display:initial" src="../images/loading01.gif" /> 加载中…</div>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="${resourcePathHttps}/commonjs/requestAnimationFrame.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.flying.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.lazyload.js"></script>
<script src="../js/itemlist.js?20170920"></script>
<script src="js/productlist.js?20170920"></script>
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
</body>
</html>