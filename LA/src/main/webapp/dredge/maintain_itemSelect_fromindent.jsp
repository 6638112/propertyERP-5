<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
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

<title>解放区上门服务预约</title>
<link rel="stylesheet" href="css/shopping.common.css" type="text/css">
</head>

<body class="pos-relative bggrey">
<form class="inputform" action="myAppointment.html">
	<div class="shop-part03">    
	    <section class="sectionBox item-details-info pos-relative">
	        <div class="my-order-item">
				<section class="divide-box bordertopgrey"></section>
	        	<div class="single-item-wrap overhidden hide">
	                <div class="displaybox order-info-box p00 bordertopgrey overhidden">
	                    <div class="item-check-box single-check"></div >
	                    <div class="item-info-img"><img src="images/1ba.jpg"></div>
		                <ul class="order-info-address boxflex01">
		                    <a class="disblock item-url" href="#">
		                        <li>
		                        	<div class="single-item-name">飞利浦节能灯（11瓦）</div>
		                        	<div class="mtop5">￥<span class="single-item-price">13.50</span></div>
		                        </li>
		                    </a>
	                    </ul>
	                	<div class="displaybox mright10 mleft10"><div class="btn-num btnReduce">-</div><input id="itemNum01" class="input-normal w40 itemNum" type="text" name="itemSelectNum" value="1" maxlength="3" /><div class="btn-num btnAdd">+</div></div>
	                </div>
					<section class="divide-box bordertopgrey"></section>
                </div>
           </div>
		    <div class="pbfooter bggrey bordertopgrey"></div>
	        <ul class="bottom-menu-box displaybox t-center">
	            <li class="p00 boxflex01"><input id="itemCancelBtn" class="btn-submit btn-next noradius bordertbgrey red" type="button" name="button" value="取消"></li>
	            <li class="p00 boxflex01 borderleft"><input id="itemHasSelectedBtn" class="btn-submit btn-next noradius bordertbgrey bgred white" type="button" name="button" value="选好了"></li>
	        </ul>
	    </section>
	</div>

</form>
<div class="sectionBox loading grey bordertopgrey mtop15 hide"><img src="images/loading01.gif" /> 加载中…</div>

<script src="js/jquery-1.11.2.min.js"></script>
<script src="js/Validform_v5.3.2.js"></script>
<script src="js/shoppingcart.common.js"></script>
<script src="js/getitem.pages.js"></script>
<script>
	$(function(){

		//增加耗材，选好了按钮
		$('#itemHasSelectedBtn').click(function(){
			submitItemSelected("saveSelfBuyProduct.json", "add");
		})
		
		//增加耗材，取消按钮
		$('#itemCancelBtn').click(function(){
			location.href = '../dredge/viewSelfBuyProductList.do?' + getOrderInfo();
		})
		
	});

	
</script>
</body>
</html>