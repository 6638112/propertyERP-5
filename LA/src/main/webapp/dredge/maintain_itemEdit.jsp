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
	<div class="shop-part03">    
	    <section class="sectionBox item-details-info pos-relative">
	        <div class="my-order-item bggrey">
			    <div class="list-box bgwhite displaybox bordertopgrey p510">
		            <div class="item-standard-name f14 boxflex01">编辑耗材</div>
		            <div class="t-right boxflex01">
		            	<a id="editItemDoneBtn" class="red ptb10" href="javascript:void(0)">确定</a>
		            </div>
			    </div>
			    <div id="noItemSelected" class="list-box displaybox bordertopgrey ptb20 hide">
		            <div class="item-standard-name f14 boxflex01 t-center">没有选择耗材</div>
			    </div>
			    <c:if test="${empty dataValue.list}">
				    <div class="list-box displaybox bordertopgrey ptb20">
			            <div class="item-standard-name f14 boxflex01 t-center">没有选择耗材</div>
				    </div>
			    </c:if>
			    <c:forEach items="${dataValue.list}" var="item">
	                <div class="single-item-box">
						<section class="divide-box bordertopgrey"></section>
		                <div class="displaybox order-info-box p00 bordertopgrey bgwhite" data-itemid="${item.id}">
		                    <div class="item-check-box single-check item-checked"></div>
		                    <div class="item-info-img"><img src="${item.pic }"></div>
			                <ul class="order-info-address boxflex01">
			                    <a class="disblock" href="../product/productDetail.do?readonly=true&ptId=${item.id}">
			                        <li>
			                        	<div class="icon-person master-info-name">${item.name }</div>
			                        	<div class="icon-person master-info-name mtop5">￥${item.price}</div>
			                        </li>
			                    </a>
		                    </ul>
		                	<div class="displaybox mright10 mleft10"><div class="btn-num btnReduce">-</div><input class="input-normal w40 itemNum" type="text" name="itemSelectNum" value="${item.quantity}" maxlength="3" /><div class="btn-num btnAdd">+</div></div>
		                </div>
	                </div>
			    </c:forEach>
           </div>
		    <div class="pbfooter bggrey bordertopgrey"></div>
	        <ul class="bottom-menu-box displaybox t-center">
	        	<li class="item-all-check boxflex01 bordertopgrey lh58">
	        		<div id="allCheck" class="item-check-box total-check disinline left item-checked"></div> 
	        		<div class="left">全选</div>
	        	</li>
	            <li id="itemDeleteBtn" class="item-delete-dtn">
	        		<input class="btn-submit btn-next noradius white bgred" type="button" name="button" value="删除">
	            </li>
			
	        </ul>
	    </section>
	</div>

<script src="js/jquery-1.11.2.min.js"></script>
<script src="js/Validform_v5.3.2.js"></script>
<script src="js/shoppingcart.common.js"></script>
<script>
	$(function(){
		
		//点完成按钮，提交已选商品信息
		$('#editItemDoneBtn').click(function(){
			var itemLength = $('.item-check-box.single-check').length;
			if(itemLength === 0){
				location.href = '../dredge/viewSelfBuyProductList.do?' + getOrderInfo();
			}else{
				submitItemSelected("saveSelfBuyProduct.json", "update");
			}
		});
	});
</script>
</body>
</html>