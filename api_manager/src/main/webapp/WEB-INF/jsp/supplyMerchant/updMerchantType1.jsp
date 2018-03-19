<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电商-供应商列表-供应商编辑</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>

<body>
<div class="info">
    <form class="inputform" action="<%=basePath%>/supplyMerchant/type1Update.html" method="post">
        <h2>供应商编辑</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">基本属性</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 供应商属性：</div></td>
            <td>自营</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 供应商名称：</div></td>
			  <input type="hidden" name="ebuySupplyMerchant.id" value="${ebuySupplyMerchant.id}">
            <td><input type="text" class="input_text pp" name="ebuySupplyMerchant.name" value="${ebuySupplyMerchant.name}" maxlength="20" datatype="*" placeholder="请填写供应商名称" nullmsg="请填写供应商名称！"></td>
          </tr>
			<tr>
				<td><div class="list-name"><span class="red">*</span> 供应商地址：</div></td>
				<td><input type="text" name="ebuySupplyMerchant.address" value="${ebuySupplyMerchant.address}" class="input_text" maxlength="40" datatype="*" placeholder="请填写供应商地址" nullmsg="请填写供应商地址！"></td>
			</tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 联系人：</div></td>
            <td><input type="text" class="input_text pp" name="ebuySupplyMerchant.linkName" value="${ebuySupplyMerchant.linkName}" maxlength="10" datatype="*" placeholder="请填写联系人" nullmsg="请填写联系人！"></td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 手机号码：</div></td>
            <td><input type="text" class="input_text pp" name="ebuySupplyMerchant.tel" value="${ebuySupplyMerchant.tel}" maxlength="11" datatype="m" placeholder="请填写手机号码" nullmsg="请填写手机号码！"></td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 配送范围：</div></td>
            <td>
                <select class="select_normal addressAreaSelect" name="ebuySupplyMerchant.type" datatype="*" nullmsg="请选择用户范围！">
                    <option value="">选择发券范围</option>
                    <option value="1" <c:if test="${ebuySupplyMerchant.type == 1}">selected</c:if>>全国范围</option>
                    <option value="4" <c:if test="${ebuySupplyMerchant.type == 4}">selected</c:if>>城市</option>
                </select>
            </td>
          </tr>
          <tr class="search-input <c:if test="${ebuySupplyMerchant.type == 1}">dsn</c:if>">
            <td></td>
            <td>
            	<input type="text" maxlength="8" class="input_text w120" placeholder="请输入关键字" />
				<input class="input-btn" type="button" value="搜索">
            </td>
          </tr>
          <tr class="city-con <c:if test="${ebuySupplyMerchant.type == 1}">dsn</c:if>">
            <td><div class="list-name">搜索结果：</div></td>
            <td>
            	<ul class="address-list search-box">
            	</ul>
            </td>
          </tr>
          <tr class="city-con <c:if test="${ebuySupplyMerchant.type == 1}">dsn</c:if>">
            <td><div class="list-name">已选城市：</div></td>
            <td>
            	<ul class="address-list selected-box">
					<li class="posrelative address-selected dsn"><span class="address-name"></span><div class="icon-delete"></div></li>
					<c:forEach var="city" items="${cityList}">
						<li class="posrelative address-selected address-selected-3" data-class="address-selected-3"><span class="address-name">${city.name}</span><div class="icon-delete"></div></li>
						<input type="hidden" name="serveCityIds" value="${city.id}">
					</c:forEach>
            	</ul>
            	<input type="hidden" class="select-input input_text w80 dsn" value="${fn:length(cityList)}" datatype="*" nullmsg="请选择城市！" />
            </td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 运费收取方式：</div></td>
            <td>
                <select class="select_normal select-charging" name="deliveryType" datatype="*" nullmsg="请选择运费收取方式！" >
                    <option value="">请选择</option>
                    <option value="1" <c:if test="${!hasDelivFee}">selected</c:if>>免邮费</option>
                    <option value="2" <c:if test="${hasDelivFee}">selected</c:if>>有条件包邮</option>
                </select>
            </td>
          </tr>
          <tr class="swap-info swap-val-2 <c:if test="${!hasDelivFee}">dsn</c:if>">
            <td></td>
            <td>不满 <input type="text" maxlength="8" class="input_text w80 pp" name="deliveryFeeFreeStart" value="<c:if test="${hasDelivFee}">${leastOrderAmt}</c:if>" ignore="ignore" datatype="numberFixTwo" nullmsg="请填写发券条件！" /> 元收
				<input type="text" maxlength="8" class="input_text w80 pp" name="deliveryFee" value="<c:if test="${hasDelivFee}">${delivFee}</c:if>" ignore="ignore" datatype="numberFixTwo" nullmsg="请填写包邮条件！" /> 元</td>
          </tr>
        </table>
        
        <table class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">结算信息（非必填）</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">结算方式：</div></td>
            <td>
                <select class="select_normal select-charging" name="ebuySupplyMerchant.revenueType">
                    <option value="">请选择</option>
                    <option value="1" <c:if test="${ebuySupplyMerchant.revenueType == 1}">selected</c:if>>购销</option>
                    <option value="2" <c:if test="${ebuySupplyMerchant.revenueType == 2}">selected</c:if>>抽佣</option>
                </select>
            </td>
          </tr>
          <tr class="<c:if test="${ebuySupplyMerchant.revenueType == 1}">dsn</c:if> swap-info swap-val-2">
            <td><div class="list-name">佣金比例：</div></td>
            <td><input type="text" maxlength="5" name="ebuySupplyMerchant.revenueRate" value="${revenueRate}" class="input_text w80" ignore="ignore" datatype="numberFixTwoSmallThanHundred" nullmsg="请填写佣金比例！" /> %</td>
          </tr>
          <tr class="<c:if test="${ebuySupplyMerchant.revenueType == 2}">dsn</c:if> swap-info swap-val-1">
            <td><div class="list-name">结算运费：</div></td>
            <td>不满 <input type="text" name="settleDeliveryFeeFreeStart" value="${leastSettleOrderAmt}" maxlength="8" class="input_text w80" ignore="ignore" datatype="numberFixTwo" nullmsg="请填写包邮条件！" /> 元收
				<input type="text" name="settleDeliveryFee" value="${settleDelivFee}" maxlength="8" class="input_text w80" ignore="ignore" datatype="numberFixTwo" nullmsg="请填写包邮条件！" /> 元</td>
          </tr>
          <tr>
            <td><div class="list-name">收款账户：</div></td>
			  <input type="hidden" name="ebuySupplyMerchantBankAccount.id" value="${bankAccount.id}">
            <td><input type="text" name="ebuySupplyMerchantBankAccount.accountName" value="${bankAccount.accountName}" class="input_text" maxlength="20" ignore="ignore" datatype="*" placeholder="请填写收款账户" nullmsg="请填写收款账户！"></td>
          </tr>
          <tr>
            <td><div class="list-name">收款账号：</div></td>
            <td><input type="text" name="ebuySupplyMerchantBankAccount.accountBank" value="${bankAccount.accountBank}" class="input_text" maxlength="22" ignore="ignore" datatype="bankCardNumber" placeholder="请填写收款账号" nullmsg="请填写收款账号！"></td>
          </tr>
          <tr>
            <td><div class="list-name">开户银行：</div></td>
            <td><input type="text" name="ebuySupplyMerchantBankAccount.bankName" value="${bankAccount.bankName}" class="input_text" maxlength="20" ignore="ignore" datatype="*" placeholder="请填写供开户银行" nullmsg="请填写供开户银行！"></td>
          </tr>
			<tr>
				<td><div class="list-name">开户支行：</div></td>
				<td><input type="text" name="ebuySupplyMerchantBankAccount.bankBranch" value="${bankAccount.bankBranch}" class="input_text" maxlength="20" ignore="ignore" datatype="*" placeholder="请填写供开户银行" nullmsg="请填写供开户银行！"></td>
			</tr>
          <tr>
            <td><div class="list-name">手机号码：</div></td>
            <td><input type="text" name="ebuySupplyMerchantBankAccount.linkTel" value="${bankAccount.linkTel}" class="input_text" maxlength="11" ignore="ignore" datatype="m" placeholder="请填写手机号码" nullmsg="请填写手机号码！"></td>
          </tr>
        </table>
        <input id="sumSupplier" class="info-btn save-set-prize-info-btn" type="submit" value="保存" />
        <div class="h30"></div>
    </form>
</div>

</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js?v20160726"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript">
(function($){
    //表单验证
	$(".inputform").Validform({
		btnSubmit:"#sumSupplier",
		tiptype:3,
		ignoreHidden: true,
		beforeSubmit: function (curform) {
			$(".inputform").attr('onsubmit', 'return false;');
		},
		callback: function (data) {
			$(".inputform").ajaxSubmit(function (data) {
				try {
					data = eval(data);
				} catch (e) {
					data = eval("(" + data + ")");
				}
				if (data.status == '0000') {
					alert("供应商修改成功！");
					window.location.href = "<%=basePath%>/supplyMerchant/supplyMerchantList2.html";
				} else {
					alert(data.message);
					$(".inputform").Validform().resetStatus();
				}
			});
		}
	});
	//运费收取方式
	selectOptionChange('.select-charging');
	
})(jQuery);
</script>
<script type="text/javascript">
$(function(){
	//选择用户范围
	$('.addressAreaSelect').change(function(){
		var curVal = $(this).val();
		if(curVal == '' || curVal == 1){
			$('.search-input').hide();
			$('.city-con,.search-input').hide();
			$('.city-con').find('input').attr('ignore', 'ignore');
			selectCountNum = 0;
		}else if(curVal == 4){
			$('.city-con,.search-input').show();
			$('.city-con').find('input').attr('ignore', '');
			//获取已选城市个数
			var curNum = $('.address-list.selected-box:visible li:visible').length;
			if( curNum == ''){
				selectCountNum = 0;
			}else{
				selectCountNum = curNum;
			}
			
		}
		//重置页面高度
		window.parent.TuneHeight();
	});
	
	//选择城市
	var $addressSelectedLi = $('.address-selected.dsn');
	var addressSelectedNum = 1;
	var selectCountNum = ${fn:length(cityList)};

	$(document).on("click", '.address-list.search-box li:visible', function(){ 
		var $this = $(this);
		//选择
		if(!$this.hasClass('on')){
			var addressSelectedClass = 'address-selected-';
			var $addressSelectedLiClone = $addressSelectedLi.clone(true);
			var thisName = $this.find('.address-name').text();
			var id = $this.find('span').attr('data-itemid');
			
			//防止重复选择
			var areaSelectedNum = 0;
			$('.address-list.selected-box li:visible').each(function(){
				var thisAreaName = $(this).find('.address-name').text();
				if(thisAreaName === thisName){
					areaSelectedNum += 1;
				}
			})
			if(areaSelectedNum > 0){
				alert('该城市已选择！');
				return false;
			}
			
			addressSelectedNum += 1;
			selectCountNum += 1;
			//已选城市，校验通过
			if(selectCountNum > 0){
				$('.address-list:visible').siblings('.select-input').val(selectCountNum);
				$(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input'));
			}
			
			addressSelectedClass = addressSelectedClass + addressSelectedNum;
			
			
			$this.addClass('on ' + addressSelectedClass).attr('data-class',addressSelectedClass);
			$addressSelectedLiClone.find('.address-name').text(thisName);
			$addressSelectedLiClone.append('<input type="hidden" name="serveCityIds" value="'+id+'">');
			$addressSelectedLiClone.removeClass('dsn').addClass(addressSelectedClass).attr('data-class',addressSelectedClass).appendTo('.selected-box:visible');
		//反选
		}else{
			var thisUnSelectedClass = $this.attr('data-class');
			$this.removeClass();
			$('.selected-box').find('.' + thisUnSelectedClass).remove();
			selectCountNum -= 1;
			//没有选择城市，校验不通过
			if(selectCountNum == 0){
				$('.address-list:visible').siblings('.select-input').val('');
				$(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input'));
			}
		}
		//重置页面高度
		window.parent.TuneHeight();
	})
	
	//删除城市
	$(document).on("click", '.address-list.selected-box li .icon-delete', function(){ 
		var $this = $(this);
		var thisDeleteClass = $this.parent('li').attr('data-class');
		if($('.' + thisDeleteClass)){
			$('.' + thisDeleteClass).removeClass('on');
		}
		$this.parent('li').remove();
		
		selectCountNum -= 1;
		//没有选择城市，校验不通过
		if(selectCountNum == 0){
			$('.address-list:visible').siblings('.select-input').val('');
			$(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input'));
		}
		//重置页面高度
		window.parent.TuneHeight();
	});
	
	
	var $searchList = $('.search-list-con.dsn');
	//搜索内容
	function ajaxSearch(url,objBox){
		$.getJSON(url, function(data){
			
			var $objBox = $(objBox);
			var searchList = '';
			
			var $searchListClone = $searchList.clone(true);
			$.each(data, function (i, aaList) {
				//城市搜索，不带详细地址
				if(aaList.name.indexOf(currentVal) > -1){
					searchList += '<li><span class="address-name" data-itemid="'+aaList.id+'">' + aaList.name + '</span></li>';
				}
			});
			if(searchList == ''){
				searchList = '<span>' + '没有找到相关内容' + '</span>';	
			}
			$objBox.html(searchList);
			//重置页面高度
			window.parent.TuneHeight();
		});		
	}
	
	//开始搜索
	$('.input-btn').click(function(){

		var thisSearchBox = $('.search-box:visible');
		currentVal = $.trim($('.search-input input.input_text').val());
		var url = '<%=basePath%>/addressCity/getAddressCityIdByName.json?name=' + currentVal;
		if(!currentVal == ''){
			ajaxSearch(url,thisSearchBox);
		}
	});
});

</script>
</html>
