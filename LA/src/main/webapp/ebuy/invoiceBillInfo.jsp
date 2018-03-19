
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

<title>发票信息</title>
<link rel="stylesheet" href="../css/common.css">
</head>

<body class="bggrey">
<section class="divide-box user-info-title borderbottom" style="padding-top:55px;"><span class="p010 f14 grey">发票抬头</span></section>
<form id="inputForm">
	<input name="invoiceId" type="hidden" value="${jsonResponse.dataValue.id }" /> 
	<section class="sectionBox borderbottom">
		<ul class="user-menu-list">
			<li>
				<span class="right f14 btn-edit"><img class="address-info" src="../images/icon-edit.png" /></span>
				<input class="input-text black wp80" type="text" name="companyName" placeholder="可填写个人或单位名称" readonly value="${jsonResponse.dataValue.companyName }" />
			</li>
	    </ul>
	</section>
	<section class="divide-box user-info-title borderbottom"><span class="p010 f14 grey">发票内容</span></section>
	<section class="sectionBox borderbottom">
		<ul class="user-menu-list">
			<li>
				<span class="right f14 red btn-edit"><img class="address-info" src="../images/icon-edit.png" /></span>
				<input class="input-text wp80" type="text" name="productTypeName" placeholder="请填写发票内容" readonly value="${jsonResponse.dataValue.productTypeName }"  />
			</li>
	    	<!-- <li><input id="cradio01" class="hide" name="radio" type="radio" /><label for="cradio01" class="radio-check-box wp100 radio-checked">电脑配件</label></li>
	    	<li><input id="cradio02" class="hide" name="radio" type="radio" /><label for="cradio02" class="radio-check-box wp100">耗材</label></li>
	    	<li><input id="cradio03" class="hide" name="radio" type="radio" /><label for="cradio03" class="radio-check-box wp100">办公用品（附购物清单）</label></li>
	    	<li><input id="cradio04" class="hide" name="radio" type="radio" /><label for="cradio04" class="radio-check-box wp100">明细</label></li> -->
	    </ul>
	</section>
</form>

<section class="divide-box user-info-title"><span class="p010 f12 grey">*发票的金额不包含粮票或者折扣券抵扣的数目</span></section>
<script src="${resourcePathHttps}/commonjs/zepto.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	})
</script>
<script src="../js/ebuy.common.js"></script>
<script>
	(function ($) {
		//选择发票抬头
		/* $('.bill-check').click(function(){
			var billCheckLength = $('.bill-check').length;
			var billCheckedLength = $('.bill-check[readonly]').length;
			if(billCheckLength == billCheckedLength){
				$(this).addClass('bill-checked').parents('ul').siblings('ul').find('.bill-check').removeClass('bill-checked');
			}
		}); */
		
		//修改发票抬头
		$('.btn-edit').click(function(){
			if($(this).do() == '确定'){
				$(this).next('input').removeClass('grey').addClass('black').prop('readonly',true);
				$(this).do('<img class="address-info" src="../images/icon-edit.png" />');
				
				$.ajax({
					url:"../invoice/updateInvoic.do",
					async:false,
					data:$("#inputForm").serialize(),
					success:function(data){
						alert(data);
						window.history.back;
						window.location.reload();
					},
					error:function(data){
						alert("更新发票信息失败");
					}
				});
			}else{
				$(this).next('input').removeClass('black').addClass('grey').prop('readonly',false).focus();
				$(this).do('确定');
			}
		});
		
		//选择发票内容
		var $allCheck = $('.user-menu-list').find('input[type=radio]');
		$allCheck.change(function(){
			if($(this).is(":checked")){
				$(this).next("label").addClass("radio-checked");
				$(this).parent('li').siblings().find('label').removeClass("radio-checked");
			}
		});
	})(Zepto); 
</script>

</body>	
</html>