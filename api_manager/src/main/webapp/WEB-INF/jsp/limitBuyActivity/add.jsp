<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发起限时购</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.simple-dtpicker.css">
	<style>
		.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
		.w180{width: 170px;}
	</style>
</head>
<body>
<div class="info">
    <form class="inputform" action="${pageContext.request.contextPath}/limitBuyActivity/addLimitBuyActivity.html" method="post">
    	<input type="hidden" name="id" value="${lba.productId}"/>
    	<input type="hidden" name="originalPrice" value="${lba.originalPrice}"/>
        <h2>发起限时购</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">商品信息</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">供应商：</div></td>
            <td>${lba.merchantName}</td>
          </tr>
          <tr>
            <td><div class="list-name">商品ID：</div></td>
            <td>${lba.productId}</td>
          </tr>
          <tr>
            <td><div class="list-name">货架分类：</div></td>
            <td>${lba.shelfType}</td>
          </tr>
          <tr>
            <td><div class="list-name">商品名称：</div></td>
            <td>${lba.productName}</td>
          </tr>
          <tr>
            <td><div class="list-name">商品售价：</div></td>
            <td><fmt:formatNumber value="${lba.originalPrice}" type="currency" pattern="0.00" maxFractionDigits="2"/>元</td>
          </tr>
        </table>
        
        <table class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">抢购信息</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 开始时间：</div></td>
            <td><input type="text" name="startTime" class="input_text pp icon_dt endlessTime w180" id="date05" placeholder="请选择开始时间" datatype="dateTime" nullmsg="请选择开始时间！" value="" ></td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 结束时间：</div></td>
            <td><input type="text" name="endTime" class="input_text pp icon_dt endlessTime w180" id="date06" placeholder="请选择结束时间" datatype="dateTime" nullmsg="请选择结束时间！" value="" ></td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 标题：</div></td>
            <td><input type="text" name="title" class="input_text pp w300" maxlength="30" datatype="*" nullmsg="请完善标题" value="" /></td>
          </tr> 
          <tr>
            <td><div class="list-name"><span class="red">*</span> 抢购价：</div></td>
            <td><input type="text" name="robPrice" class="input_text pp w120" maxlength="8" datatype="numberFixTwoGt0" nullmsg="请完抢购价" value=""/> 元</td>
          </tr> 
          <tr>
            <td><div class="list-name"><span class="red">*</span> 每人购买数量限制：</div></td>
            <td><input id="numNoLimit" type="radio" checked="checked" name="numLimit" datatype="*" nullmsg="请选择是否限制数量" /> 不限制 <input id="numLimited" class="mleft20" type="radio" name="numLimit" datatype="*" nullmsg="请选择是否限制数量" /> 限制数量 <input id="limitInput" class="input_text pp w120 mleft20" type="text" name="limitNumber" placeholder="请输入限制数量" ignore="ignore" readonly="readonly" datatype="fei0zhengzhengshu" nullmsg="请输入限制数量！" errormsg="请填写大于0的整数！" maxlength="6" /></td>
          </tr> 
          <tr>
            <td><div class="list-name"><span class="red">*</span> 促销库存：</div></td>
            <td><input id="leftCountInput" class="input_text pp w120" type="text" name="leftCount" placeholder="请输入促销库存" value="${lba.leftCount}" datatype="fei0zhengzhengshu" nullmsg="请输入促销库存！" errormsg="请填写大于0的整数！" maxlength="6" /></td>
          </tr>
        </table>
        
        <input id="sumTicket" class="info-btn save-set-prize-info-btn" type="submit" value="发布" />
        <input class="info-btn mar-left15 info-btn01 mtop0" onclick="history.back()" type="button" value="取消">
        <div class="h30"></div>
    </form>
</div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
(function($){
    //表单验证
	$(".inputform").Validform({
		btnSubmit:"#sumTicket", 
		tiptype:3,
		beforeCheck:function(curform){
			var date05 = $.trim($("#date05").val());
			var date06 = $.trim($("#date06").val());
			var startTime = new Date(date05.replace("-", "/").replace("-", "/"));
			var endTime = new Date(date06.replace("-", "/").replace("-", "/"));
			if(startTime >= endTime)
			{
			    alert("开始时间必须小于结束时间！");
			    location = "#date05";
			    return false;
			}
		},
		callback:function(data){
			
		}
	});

	
	//选择是否限制购买数量
	$('[name=numLimit]').change(function(event){
		var $thisCheck = $(this);
		var thisType = $thisCheck.attr('id');
		
		if(thisType === 'numLimited'){
			$('#limitInput').removeAttr('ignore').removeAttr('readonly');
		}else{
			$('#limitInput').val('').attr('ignore', 'ignore').attr('readonly', 'readonly');
			
			var inputformLayer = $(".inputform").Validform();
			if(!inputformLayer.check()){
				inputformLayer.submitForm();
			}
		}
	});
	
	//限购数量/促销库存不可超过商品库存，限购数量不可超过促销库存
	$('#leftCountInput, #limitInput').keyup(function(){
		var thisText = $(this).parent().siblings('.item-standard-name').text();
		if($('#limitInput').val() !== '' && $('#leftCountInput').val() !== '' && $('#limitInput').val()*1 > $('#leftCountInput').val()*1){
			alert('限购数量不可超过促销库存');
			$(this).val('');
		}

		if($(this).val() !== '' && $(this).val()*1 > '${lba.leftCount}'){
			alert(thisText + '不可超过商品库存');
			$(this).val('${lba.leftCount}');
		}
	});
})(jQuery);
</script>
</html>


