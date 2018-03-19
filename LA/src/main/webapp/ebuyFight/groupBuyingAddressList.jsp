<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no" />
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>选择自提点</title>
<link rel="stylesheet" href="../ebuyFight/css/groupbuying.css">
</head>

<body class="bggrey heightp100">
<section class="sectionBox pos-relative minheight100 bggrey pb48 heightp100 overhidden">
    <section class="divide-box bordertbgrey"></section>
    <c:forEach var="zitiDian" items="${zitiList}" varStatus="status">
        <div class="p010 bgwhite displaybox">
	        <div class="icon-address-list"></div>
	        <div class="item-standard-name f16 mleft10 borderbottomgrey boxflex01 ptb20 <c:if test="${status.index == 0}">list-address-check</c:if>">
	           	<input class="ziTiId" type="hidden" name="ziTiId" value="${zitiDian.id}">
	        	<span>${zitiDian.name}</span>
	        	<div class="f14 grey mtop10">${zitiDian.delivAddress}</div>
	        </div>
    	</div>
    </c:forEach>

    <section class="divide-box bordertopgrey"></section>
	<section class="pbfooter"></section>
    <ul class="bottom-menu-box displaybox t-center">
    	<li class="p00 boxflex01"><a ><input class="btn-submit bgred noradius noborder btnSubmit height48 lineheight48" onclick="ziTiList();" type="button" name="submit" value="确定" /></a></li>
    </ul>
    
   	<div class="tips-box tips-done bounceInDown animated1s lineheight140">
		请您先选择合适的自提点才可为您<br>提供对应的商品哦
	</div>
</section>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>

<script>
$(function(){
	new FastClick(document.body);
	//根据localStorage数字设置选中地址，关闭浏览器后,可根据本地存储获取并设置已选项
	if(localStorage.groupBuyingAddressHashNum && typeof(localStorage["zitiDianName"]) == "undefined"){
		var addressNum = localStorage.groupBuyingAddressHashNum;
		$('.item-standard-name').removeClass('list-address-check');
		$(".p010.bgwhite.displaybox").eq(addressNum-1).find('.item-standard-name').addClass('list-address-check');
	}
	if(typeof(localStorage["zitiDianName"]) != "undefined"){
		$('.item-standard-name').removeClass('list-address-check')
								.find(":contains('"+localStorage['zitiDianName']+"')")
								.parent()
								.addClass('list-address-check');
	}
	
	$('.item-standard-name').click(function(){
		$('.item-standard-name').removeClass('list-address-check');
		$(this).addClass('list-address-check');
		//将选中地址的序号加入localStorage
		var num = $(this).parents(".p010.bgwhite.displaybox").index();
		
        localStorage['groupBuyingAddressHashNum'] = num;
	})
	
	setTimeout(function(){
		$('.tips-done').fadeOut(300);
		setTimeout(function(){
			$('.sectionBox.pos-relative').removeClass('heightp100 overhidden');
		},300);
	},2400);
})
function ziTiList(){
	var ziTiId = $('.list-address-check .ziTiId').val();
	location.href="../laGroupPurchase/productList.do?ziTiId="+ziTiId;
}

</script>

</body>
</html>