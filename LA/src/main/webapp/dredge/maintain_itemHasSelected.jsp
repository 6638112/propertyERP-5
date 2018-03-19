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
			    <div class="list-box bgwhite displaybox bordertopgrey p510 order-to-pay">
		            <div class="item-standard-name f14 boxflex01">已选择耗材</div>
		            <div class="t-right boxflex01">
		            	<a id="itemHasSelectedBtn" class="red ptb10" href="#">返回</a>
		            </div>
			    </div>
			    <c:if test="${empty dataValue.list}">
				    <div class="list-box displaybox bordertopgrey bggrey ptb20">
			            <div class="item-standard-name f14 boxflex01 t-center">没有选择耗材</div>
				    </div>
			    </c:if>	
			    
			    <c:forEach items="${dataValue.list}" var="item">
			    	<section class="divide-box bordertopgrey"></section>
                    <a class="disblock" href="../product/productDetail.do?readonly=true&ptId=${item.id}">
		                <div class="displaybox order-info-box p00 bordertopgrey">
		                    <div class="item-info-img mleft10"><img src="${item.pic }"></div>
			                <ul class="order-info-address boxflex01">
		                        <li>
		                        	<div class="icon-person master-info-name">${item.name }</div>
		                        </li>
		                    </ul>
			                <ul class="order-info-address t-right mright10">
		                        <%-- <li>
		                        	<div class="icon-person master-info-name">￥${item.price}</div>
		                        	<div class="icon-person master-info-name grey f12">x${item.quantity}</div>
		                        </li> --%>
		                        <li class="right mleft10">￥<span class="subPrice">${item.price * item.quantity}</span></li>
		                        <li class="right">
		                        	<div class="icon-person master-info-name grey f12">￥${item.price} x${item.quantity}</div>
		                        </li>
		                    </ul>
		                </div>
                    </a>
			    </c:forEach>
			    <c:if test="${!empty dataValue.list}">
					<section class="divide-box bordertopgrey"></section>
	                <div class="displaybox order-info-box p00 bordertopgrey">
		                <ul class="order-info-address boxflex01 mleft10">
	                        <li>
	                        	<div class="icon-person master-info-name">总金额</div>
	                        </li>
	                    </ul>
		                <ul class="order-info-address boxflex01 t-right mright10">
	                        <li>
	                        	<div class="icon-person master-info-name">￥<span id="totalItemPrice"></span></div>
	                        </li>
	                    </ul>
	                </div>
                </c:if>
           </div>
		    <div class="pbfooter bggrey bordertopgrey"></div>

			<c:if test="${dataValue.canEdit}">
				<ul class="bottom-menu-box displaybox t-center order-to-pay">
					<li class="p00 boxflex01">
						<a id="moreItemsBtn" class="displaybox" href="#">
							<input class="btn-submit btn-next noradius bordertbgrey red" type="button" name="button" value="选择更多耗材">
						</a>
					</li>
					<li class="p00 boxflex01 borderleft">
						<a id="editItemsBtn" class="displaybox" href="#">
							<input class="btn-submit btn-next noradius bordertbgrey red" type="button" name="button" value="编辑">
						</a>
					</li>
				</ul>
			</c:if>
	    </section>
	</div>

</form>

<script src="js/jquery-1.11.2.min.js"></script>
<script src="js/shoppingcart.common.js"></script>
<script>
$(function(){

	//计算商品总金额
	totalPriceCount();
	
	function totalPriceCount(){
		var totalItemPrice = 0;
		$(".order-info-box").each(function(){
			var subPrice = $(this).find('.subPrice').text();
			
			if(subPrice != undefined && subPrice != ''){
				totalItemPrice += subPrice*1;
			}
		})
		$("#totalItemPrice").text(totalItemPrice.toFixed(2));
	}
	
	//回到订单链接
	document.getElementById("itemHasSelectedBtn").setAttribute('href', '../dredge/qryDredgeBillDetail.do?billType=${billType}&id=' + getUrlParam('dredgeBillId'));
	
	if(${dataValue.canEdit}){
		//设置更多耗材链接
		document.getElementById("moreItemsBtn").setAttribute('href', '../dredge/maintain_itemSelect_fromindent.jsp?' + getOrderInfo());
		document.getElementById("editItemsBtn").setAttribute('href', '../dredge/viewSelfBuyProductList.do?operType=edit&' + getOrderInfo());
	}
	
	if(location.search.indexOf('canEdit') > -1){
		$('.order-to-pay').remove();
	}
});
</script>
</body>
</html>