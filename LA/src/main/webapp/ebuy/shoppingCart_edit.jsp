<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">

<title>购物车编辑</title>
<link rel="stylesheet" href="../css/common.css?v20170907">
</head>

<body class="bggrey">
<form id="removeForm" action="../cart/remove2BuyCarBatch.do" method="post">
	<c:forEach items="${jsonResponse.dataValue.list }" var="item" >
		<section class="divide-box borderbottomgrey"></section>
		<section class="sectionBox shoppingPage">
		   <h3 class="item-sort">
		   		<input id="cbox01${item.name}" name="checkbox" type="checkbox" hidden="hidden" />
		   		<label for="cbox01${item.name}" class="item-check-box all-check"></label >${item.name }
		   		 <a class="disblock p010 right" href="../cart/qryBuyCar.do"><span  class="red">完成</span></a>
		   </h3>
		   	  <c:forEach items="${item.productList }" var="product" varStatus="subStatus">
				   <div class="info ptb20">
				        <div class="tbl">
				        	<span class="tbl-cell align-top">
					        	<input id="${product.id }${product.name }" name="productCheckBox" type="checkbox" hidden="hidden" value="${product.id }" />
					        	<label for="${product.id }${product.name }" class="item-check-box single-check"></label >
				        	</span>
				            <span class="tbl-cell inline-table item-img-small">
					            <a href="../product/productDetail.do?ptId=${product.id}">
					            	<img src="${product.picBase }<c:choose><c:when test="${fn:contains(product.picBase, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_110,h_110,limit_0/format,jpg/interlace,1/quality,q_90" />
					            </a>
				            </span>
				            <span class="item-info w500 tbl-cell">
				                <div class="disbox">
					                <div id="${product.id }" productSpecId="${product.productSpecId}" class="btn-num btnReduce border-left-radius" style="" onclick="subQty(this);">-</div>
					                <input class="input-normal w50 itemNum border-black" type="text" value="${product.productQty }" readonly="readonly" />
					                <div id="${product.id }" productSpecId="${product.productSpecId}" class="btn-num btnAdd border-right-radius"  onclick="addQty(this);">+</div>
				                </div>
				                <div class="standards-selected grey"><a href="../product/productDetail.do?ptId=${product.id}">${product.name } </a></div>
				            </span>
				            <span class="price-num tbl-cell">
				                <span class="disblock f14 red mtop10 t-right">￥<span class="singleItemPrice">${product.priceDiscount}</span></span>
				            </span>
				        </div>
				    </div>
			  </c:forEach>
			  
		    <ul class="item-price">
		    	<li class="left">共<span class="subTotalNum"> ${jsonResponse.dataValue.ext_totalCount }</span> 件商品</li>
		        <li class="right">总计：<span class="red bold f16">￥<span class="subTotalPrice">${jsonResponse.dataValue.ext_totalMoney }</span></span></li>
		    </ul>
		</section> 
	</c:forEach>
</form>
<section class="divide-box bordertop pbfooter"></section>

<!-- <div class="exchang-fixed ptb10">
    <div class="item-all-check"><input id="allCheck" name="checkbox" type="checkbox" hidden="hidden" /><label for="allCheck" class="item-check-box"></label > 全选</div>
    <div class="btn-pay-fixed">
        <div class="btn-pay btn-delete"><a id="btnRomoveProduct" class="bgred" href="javascript:void(0);" onclick="removeProduct();">删除</a></div>
    </div>
</div> -->

<section class="sectionBox">
	<div class="exchang-fixed displaybox">
	    <div class="item-all-check bggradient boxflex01"><input id="allCheck" name="checkbox" type="checkbox" hidden="hidden" /><label for="allCheck" class="item-check-box"></label > 全选</div>
	    <div class="btn-pay-fixed">
	        <div class="btn-pay btn-delete"><a id="btnRomoveProduct" class="bgred" href="javascript:void(0);" onclick="removeProduct();">删除</a></div>
	    </div>
	</div>
</section>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script> 
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	});
</script>
<script src="${resourcePathHttps}/commonjs/TouchSlide.1.1.js"></script>
<script src="../js/ebuy.common.js"></script>
<script src="${resourcePathHttps}/commonjs/ajax_common.js"></script>
<script type="text/javascript">
	//添加数量
	function addQty(ths) {
		var ptId = ths.id;
		var postData = "ptId="+ptId+"&productQty=1" + "&specId=" + $(ths).attr("productSpecId");
		
		$.ajax({
			type:"post",
			url:"../cart/add2BuyCar.do",
			data:{"ptId": ptId,"productQty":1, "specId":$(ths).attr("productSpecId")},
			dataType:"json",
			success:function(data){
				if(data.status != '0000'){
					alert(data.message); 
					
					//添加购物车失败，购买数量减1
					var num = $(ths).siblings(".itemNum").val();
					$(ths).siblings(".itemNum").val(num-1);
					
					ShoppingCartEditGetCount();//金额总计也要还原
				}
			},  
            error: function(){  
            	alert('网络不给力，请稍后重试'); 
            } 
		});
	}

	//减少数量
	function subQty(ths) {
		var ptId = ths.id;
		var postData = "ptId="+ ptId + "&productQty=-1" + "&specId=" + $(ths).attr("productSpecId");//-1表示减少
		ajaxPost("../cart/add2BuyCar.do", postData);
	}
	
	//移除商品
	function removeProduct(){
		if($('input[type=checkbox]:checked').length==0){
			alert("请选择要删除的商品");
			return false;
		}
		$("#removeForm").submit();
	}
</script>
</body>
</html>