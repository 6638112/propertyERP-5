<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>地址管理</title>
<link rel="stylesheet" href="../css/address.common.css?20170918">
</head>

<body class="bggrey">
<section class="sectionBox bggrey pb48">
    <c:forEach items="${addresResponse.dataValue.list}" var="address">
	    <section class="divide-box bordertbgrey"></section>
	    <div class="list-box bgwhite displaybox ptb5">
	        <div data-id="${address.id }" class="item-standard-name f16 boxflex01 <c:if test="${address.isDefault==1 }">list-icon list-icon-check</c:if> ">${address.userName } 
	        	<span class="mleft10">${address.userPhone }</span>
	        	<div class="f14 grey mtop5"><!-- ${address.addressArea } -->${address.addressDetail }</div>
	        </div>
	        <div class="w50 f16 t-right lineheight65"><a class="disblock red" href="../address/updDeliveryAddress.do?id=${address.id }">修改</a></div>
	    </div>
	</c:forEach>
    
    <section class="divide-box bordertopgrey"></section>
    <ul class="bottom-menu-box displaybox t-center">
    	<li class="p00 boxflex01">
	    	<a class="disblock" href="../address/addAddressInfo.do">
	    		<input class="btn-submit bgred noradius noborder btnSubmit" type="submit" name="submit" value="新增地址" />
	    	</a>
    	</li>
    </ul>
</section>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="${resourcePathHttps}/commonjs/Validform_v5.3.2.js"></script>
<script>
$(function(){ 
	new FastClick(document.body);	
	//切换地址
	$('.item-standard-name').click(function(){
		$('.item-standard-name').removeClass('list-icon list-icon-check');
		$(this).addClass('list-icon list-icon-check');
		$.get("../address/setDefaultDeliveryAddress.do", {id:$(this).attr("data-id")}, function(data){
			if(data.status == "0000"){
				$.Showmsg("修改默认收货地址成功");
				var fromWhere = '${sessionScope.fromWhere}';
				var fromWhereParam = '${sessionScope.fromWhereParam}';
				
				setTimeout(function(){
					//如果是从确认订单那里修改收货地址，保存后跳回  确认订单 界面，否则跳回收货地址列表界面  wenfq 2015-04-09
					if ("checkPay" === fromWhere ){
						// 跳到“/cart/checkProdctInfo.do”不能用“forward”，否则只会会失败，因为“forward”之后url没变，支付授权目录不一致
						window.location.href = "../cart/checkProdctInfo.do"; 
					}else if ("buyRightNow" === fromWhere) {
						window.location.href = "../cart/buyRightNow.do?" + fromWhereParam; 
					}else if ("limitbuy" === fromWhere) {
						window.location.href = "../cart/limitBuyDetail.do?" + fromWhereParam; 
					} else {
						window.location.href = "../address/qryDeliveryAddressList.do"; 
					}
				}, 2000);
			};
		});
	});
	
})
</script>

</body>
</html>