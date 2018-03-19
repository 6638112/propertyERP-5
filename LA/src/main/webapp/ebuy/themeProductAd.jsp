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
	<link rel="stylesheet" href="../css/common.css">
	<link rel="stylesheet" href="../css/item-list.css">
</head>
<body>
<section class="sectionBox relative_box">
	<section class="tabBox pos-relative">
		<div class="shopping-cart-index bggreen"><a href="${pageContext.request.contextPath}/cart/qryBuyCar.do"><div id="cartNum" class="item-num bgwhite">${productCount.productCount}</div><img src="${pageContext.request.contextPath}/images/shoppingcart-white-big.png" /></a></div>
		<div class="bd mtop10" id="tabBox-bd">
			<div class="con">
				<div class="p010">
					<c:forEach var="product" items="${json.dataValue.products }" varStatus="status">
						<c:if test="${status.index % 2 == 0}">
							<ul class="item-list displaybox">
						</c:if>
						<li class="boxflex01">
							<div class="item-list-small">
								<a class="single-item-btn" data-id="${product.id}" prdtId="${product.id}" href="${pageContext.request.contextPath}/product/productDetail.do?ptId=${product.id }"><div class="item-list-img"><img class="scrollLoading imgloading" data-url="${product.picBase}" src="${product.picBase}" /></div><p class="item-name">${product.name}</p></a>
								<div class="red f16 bold mtop5 m010">${product.priceOnShelf}</div>
								<div class="mtop5 m010">
									<div class="market-price-small left">${product.price}</div>
									<div class="right join-btn join-shoppingCart-btn" data-id="${product.id}" leftCount="${product.leftCount}"><img src="../images/shoppingcart-green.png" /></div>
								</div>
							</div>
						</li>
						<c:if test="${status.index % 2 == 1}">
							</ul>
						</c:if>
						<c:if test="${status.last && status.index % 2 == 0}">
							<li class="boxflex01"></li></ul>
						</c:if>
					</c:forEach>

				</div>
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
<script src="${resourcePathHttps}/commonjs/Validform_v5.3.2.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.flying.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.scrollLoading.js"></script>
<script src="../js/jquery.index.main.js"></script>
<script src="../js/item-list.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	});
</script>
</body>
</html>