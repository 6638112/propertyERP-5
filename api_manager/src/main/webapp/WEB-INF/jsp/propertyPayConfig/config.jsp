<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	response.setHeader("Pragma","No-cache");    
	response.setHeader("Cache-Control","no-cache");    
	response.setDateHeader("Expires", -10);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>物业-常规收费管理-收费配置</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	</head>
	<body>
		<div class="info">
			<form class="inputform" action="${pageContext.request.contextPath}/propertyPayConfig/saveLateMoneyConfig.html" method="post">
				<input type="hidden" id="gbId" name="gbId" value="${gbId}"/>
				<input type="hidden" id="alterPeriodFeeItems" name="alterPeriodFeeItems" value=""/>
			    <h2>${gbName}-收费项配置<span style="color:red;">（*最多只能配置10项）</span></h2>
			    <table id="sortTable" class="info-list-02" border="0" cellpadding="0" cellspacing="1">
				    <thead>
					    <tr class="title">
					        <th width="50%">名称</th>
					        <th width="20%">是否计算滞纳金</th>
					        <th width="20%">操作</th>
					    </tr>
				    </thead>
				    <tr class="new-sort1-info-tr dsn">
				        <td><input type="text" id="name" class="input_text pp" maxlength="20" value="" placeholder="请输入收费项名称"/></td>
				        <td><input type="checkbox"></td>
				        <td><a class="blue deleteItem" href="#">删除</a></td>
				    </tr>
				    <c:forEach items="${alterPeriodFeeItems}" var="row">
					    <tr class="new-sort1-info-tr">
					        <td class="">
					        	<input type="hidden" id="id" value="${row.id}"/>
					        	<input type="text" id="name" class="input_text pp" maxlength="20" value="${row.name}" placeholder="请输入收费项名称"/>
					        </td>
					        <td class=""><input type="checkbox" <c:if test="${row.latefeeStatus eq 1}"> checked="checked"</c:if>/></td>
							<td><a class="blue deleteItem" href="" data-href="${pageContext.request.contextPath}/propertyPayConfig/deleteItem.json" data-id="${row.id}">删除</a></td>
					    </tr>
				    </c:forEach>
			    </table>
				<div>
					<input class="input-btn w200 mtop10 add-sort1-btn" type="button" value="新增收费项">
				</div>
				<table class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
					<tr>
						<td>账单名称</td>
						<td><input type="text" id="billName" name="billName" class="input_text pp" maxlength="20" value="${billName}" placeholder="请输入账单名称"/></td>
					</tr>
					<tr>
						<td>缴费可选月数</td>
						<td><input type="hidden" id="periodMonth" name="periodMonth"/><%--全部保存的时候使用1,2,3...--%>
							<c:forEach items="${months}" var="months" varStatus="status">
								<input id="pk${status.index}" type="checkbox" style="cursor: pointer;" class="periodMonths mtop3 <c:if test="${status.index != 0}">mleft20</c:if>"
									   <c:if test="${status.index == 0}">datatype="*" nullmsg="请配置选择周期缴费总基数！"</c:if>
									   name="periodMonths" value="${months}"
								<c:forEach items="${alterMonths}" var="alterMonths">
									   <c:if test="${months == alterMonths}">checked="checked"</c:if>
								</c:forEach>
								> <label style="cursor: pointer;" for="pk${status.index}">${months}个月</label>
							</c:forEach>
						</td>
					</tr>
				</table>
			    <div>
			    	<input class="info-btn w200 mtop10 small-btn save-sort-btn" type="button" value="保存">
			    </div>

				<h2 class="mtop20">收费金额</h2>
				<div>
					<input class="input-btn w200 mtop10 add-sort1-btn" id="dataManager" type="button" value="数据管理">
				</div>

			    <h2 class="mtop40">${gbName}-滞纳金设置</h2>
			    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
				    <tr>
				        <td width="120">滞纳金收取</td>
				        <td>
				        	<input id="hasOverdueFine" type="radio" class="mtop3" name="latefeeStatus" value="1" datatype="*" nullmsg="请选择是否收取滞纳金！" <c:if test="${(not empty alterPeriodCfg) and (not empty alterPeriodCfg.latefeeStatus) and (alterPeriodCfg.latefeeStatus eq 1)}"> checked="checked"</c:if>> <label for="hasOverdueFine">是</label>
					    	<input id="noOverdueFine" type="radio" class="mtop3 mleft20" name="latefeeStatus" value="2" <c:if test="${(empty alterPeriodCfg) or (empty alterPeriodCfg.latefeeStatus) or (alterPeriodCfg.latefeeStatus ne 1)}"> checked="checked"</c:if>> <label for="noOverdueFine">否</label>
				        </td>
				    </tr>
				    <tr class="is-overdue-fine">
				        <td>滞纳金利率</td>
				        <td><input type="text" class="input_text w150 pp" name="latefeeRate" value="${(alterPeriodCfg.latefeeRate)}" maxlength="8" placeholder="请输入滞纳金利率" datatype="numberFixFourSmallThanHundred" nullmsg="请输入滞纳金利率！" nullmsg="滞纳金利率为小数！"> %</td>
				    </tr>
			    </table>
			    <table class="info-list-02 mtop20 is-overdue-fine" border="0" cellpadding="0" cellspacing="1">
				    <thead>
					    <tr class="title">
					        <th width="30%">计算元素</th>
					        <th>滞纳金计算公式</th>
					    </tr>
				    </thead>
				    <tr class="nobg" style="vertical-align: top;">
				        <td>
				        	<ul class="calculate-user-el">
				        		<c:forEach var="item" items="${calExpElements}">   
				        			<c:if test="${item.value.visible}">
				        			    <li><a href="javascript:void(0)" data-mark="${item.key}">${item.value.name}</a></li>
				        			</c:if>
								</c:forEach>  
				        	</ul>
				        </td>
				        <td>
				        	<div id="calculateExp" class="textareas txt02">${expShow}</div>
				        	<ul class="calculate-system-el mtop10">
				        		<li><a class="calc-operator" href="javascript:void(0)">+</a></li>
				        		<li><a class="calc-operator" href="javascript:void(0)">-</a></li>
				        		<li><a class="calc-operator" href="javascript:void(0)">*</a></li>
				        		<li><a class="calc-operator" href="javascript:void(0)">/</a></li>
				        		<li><a class="calc-bracket-left" href="javascript:void(0)">(</a></li>
				        		<li><a class="calc-bracket-right" href="javascript:void(0)">)</a></li>
				        		<li class="w50"><a id="calcDeleteBtn" href="javascript:void(0)">←</a></li>
				        	</ul>
				        	<ul class="calculate-system-el mtop10">
				        		<li><a class="calc-num" href="javascript:void(0)">1</a></li>
				        		<li><a class="calc-num" href="javascript:void(0)">2</a></li>
				        		<li><a class="calc-num" href="javascript:void(0)">3</a></li>
				        		<li><a class="calc-num" href="javascript:void(0)">4</a></li>
				        		<li><a class="calc-num" href="javascript:void(0)">5</a></li>
				        		<li><a class="calc-num" href="javascript:void(0)">6</a></li>
				        		<li><a class="calc-num" href="javascript:void(0)">7</a></li>
				        		<li><a class="calc-num" href="javascript:void(0)">8</a></li>
				        		<li><a class="calc-num" href="javascript:void(0)">9</a></li>
				        		<li><a class="calc-num" href="javascript:void(0)">0</a></li>
				        	</ul>
				        	<input id="calculateExpInput" type="hidden" name="calculateExpression" value="" />
				        	<div id="calculateExpHidden" class="dsn">${expHidden}</div>
				        </td>
				    </tr>
			    </table>
			    <div class="padb"><input id="savePlotInfoChild" class="info-btn" type="submit" value="全部保存"/></div>
		    </form>
		</div>
	</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
	if("${(empty alterPeriodCfg) or (empty alterPeriodCfg.latefeeStatus) or (alterPeriodCfg.latefeeStatus ne 1)}"=="true"){
		$(".is-overdue-fine").hide();
	}
	
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		btnSubmit:"#savePlotInfoChild",
		ignoreHidden: true,
		postonce:true,
		ajaxPost:true,
		beforeSubmit: function(){
			$("#savePlotInfoChild").attr("disabled", "disabled");
			var alterPeriodFeeItems = [];
			var isValid = true;
			$(".new-sort1-info-tr:visible").each(function(){
				var $thisInput = $(this).find("#name");
				if($thisInput.val() === ""){
					alert('收费项配置信息不能为空！！！');
					isValid = false;
					return false;
				}
				var alterPeriodFeeItem = {};
				alterPeriodFeeItem["name"] = $.trim($thisInput.val());
				var check = $(this).find('input[type=checkbox]').is(':checked');
				if(check){
					alterPeriodFeeItem["latefeeStatus"] = 1;
				} else {
					alterPeriodFeeItem["latefeeStatus"] = 2;
				}
				alterPeriodFeeItem["id"]=$.trim($(this).find("#id").val());
				alterPeriodFeeItems.push(alterPeriodFeeItem);
			});
			if(alterPeriodFeeItems.length>10){
				alert('收费项最多只能配置10个！！！');
				isValid = false;
			}
			if($("#billName").val()=="") {
				alert('账单名称不能为空！！');
				isValid = false;
			}
			
			$("#alterPeriodFeeItems").val(JSON.stringify(alterPeriodFeeItems));

			$("#periodMonth").val(getPeriodMonths());

			convertCalculateExp();
			$("#savePlotInfoChild").removeAttr("disabled");
			return isValid && checkBlacket();
		},
		callback: function(data){
			if(data.status=="0000"){
				$("#savePlotInfoChild").attr("disabled", "disabled");
				alert(data.message);
				location = "${pageContext.request.contextPath}/propertyPayConfig/config.html?gbId=${gbId}";
			} else {
				alert(data.message);
			}
		}
	});

	//跳转到数据详情
	$("#dataManager").click(function () {
		window.location.href ="${pageContext.request.contextPath}/alterPeriod/listDataDetail.html?gbId=${gbId}";
	});

	//编辑、保存……奇葩的需求、奇葩的交互，不要问为什么
	$('.btn-edit').click(function(){
		var $this = $(this);
		var $sibTd = $this.parents('td').siblings('td');
		
		$sibTd.find('input[type=text]').removeClass('input_noborder').addClass('input_text').removeAttr('readonly');
	});
	$('.save-sort-btn').click(function(){
		var alterPeriodFeeItems = [];
		var isValid = true;
		$(".new-sort1-info-tr:visible").each(function(){
			var $thisInput = $(this).find("#name");
			if($thisInput.val() === ""){
				isValid = false;
				return false;
			}
			var alterPeriodFeeItem = {};
			alterPeriodFeeItem["name"] = $.trim($thisInput.val());
			var check = $(this).find('input[type=checkbox]').is(':checked');
			if(check){
				alterPeriodFeeItem["latefeeStatus"] = 1;
			} else {
				alterPeriodFeeItem["latefeeStatus"] = 2;
			}
			alterPeriodFeeItem["id"]=$.trim($(this).find("#id").val());
			alterPeriodFeeItems.push(alterPeriodFeeItem);
		});
		
		if(!isValid){
			alert('收费项配置信息不能为空！！！');
			return;
		}
		
		if(alterPeriodFeeItems.length>10){
			alert('收费项最多只能配置10个！！！');
			return;
		}

		var billName = $("#billName").val();
		if(billName == '' || billName == null) {
			alert('账单名称不能为空！！');
			return;
		}

		$.post("${pageContext.request.contextPath}/propertyPayConfig/savePayConfig.html", {"gbId":$.trim($("#gbId").val()), "alterPeriodFeeItems":JSON.stringify(alterPeriodFeeItems),"billName":$.trim(billName),"periodMonths":getPeriodMonths()}, function(data){
			if(data.status=="0000"){
				alert(data.message);
				location = "${pageContext.request.contextPath}/propertyPayConfig/config.html?gbId=${gbId}";
			} else {
				alert(data.message);
			}
		});
	});

	//优化缴费月份数为逗号分隔的字符串
	function getPeriodMonths() {
		var periodMonths = '';
		$(".periodMonths").each(function () {
			if($(this).prop("checked") == true) {
				var month = $(this).val();
				if(month != '') {
					periodMonths = periodMonths + month + ',';
				}
			}
		});
		if(periodMonths.length>1) {
			periodMonths = periodMonths.substring(0,periodMonths.length - 1);
		}
		return periodMonths;
	}

	//显示隐藏滞纳金
	$("#hasOverdueFine").click(function(){
		$(".is-overdue-fine").show();
		window.parent.TuneHeight();
	});
	$("#noOverdueFine").click(function(){
		$(".is-overdue-fine").hide();
		window.parent.TuneHeight();
	});
	
	//滞纳金公式
	//添加左侧中文元素
	addElementsFn('.calculate-user-el li a', testChinese);
	
	//添加计算符号
	addElementsFn('.calc-operator', testOperator);
	
	//添加括号
	addElementsFn('.calc-bracket-left', testLeftBracket);
	
	//添加括号
	addElementsFn('.calc-bracket-right', testRightBracket);
	
	//添加括号
	addElementsFn('.calc-num', testCalcNum);
	
	$("#calcDeleteBtn").click(function(){
		deleteCalcEl();
	});
	
	//删除计算元素
	function deleteCalcEl(){
		$('#calculateExp, #calculateExpHidden').find('span:last-child').remove();
	}
	//添加计算元素
	function addElementsFn(obj, testFn){
		$(obj).click(function(){
			var thisText = $(this).text();
			var lastText = $('#calculateExp').find('span:last-child').text();
			var thisMark = $(this).attr('data-mark') || '';
			
			if(testFn(lastText)){
				addCalculateEl(thisText);
				jointCalculateExp(thisText, thisMark);
			}
		});
	}
	//添加计算元素
	function addCalculateEl(text){
		$('#calculateExp').append('<span>' + text + '</span>');
	}
	//添加计算公式
	function jointCalculateExp(text, mark){
		if(mark !== ''){
			$('#calculateExpHidden').append('<span>' + mark + '</span>');
		}else{
			$('#calculateExpHidden').append('<span>' + text + '</span>');
		}
	}
	//转换计算公式
	function convertCalculateExp(){
		var expString = $('#calculateExpHidden').html().replace(/(<span>)|(<\/span>)/gi,'');
		$("#calculateExpInput").val(expString);
	}
	
	//校验添加左侧中文元素
	function testChinese(string){
		var reg = /[\u4E00-\u9FA5\uf900-\ufa2d]+|\d+|\)/;
		if(reg.test(string)){
			alert('此类元素不能连续输入');
			return false;
		}else{
			return true;
		}
	}
	//校验添加计算符号
	function testOperator(string){
		var reg = /[\+\-\*\/]$/;
		var regBracket = /\($/;
		if(string === undefined || string === '' || regBracket.test(string)){
			alert('请先添加计算元素');
			return false;
		}else{
			if(reg.test(string)){
				deleteCalcEl()
			}
			return true;
		}
	}
	//校验添加左括号
	function testLeftBracket(string){
		var reg = /[\)]|[\u4E00-\u9FA5\uf900-\ufa2d]+|\d+/;
		return !reg.test(string);
	}
	//获取括号数量
	function getBlacketNum(){
		var blacketNum = {};
		
		blacketNum.allString = $('#calculateExp').html().replace(/(<span>)|(<\/span>)/g,'');
		blacketNum.leftBracketArray = blacketNum.allString.match(/\(/g) || [];
		blacketNum.rightBracketArray = blacketNum.allString.match(/\)/g) || [];
		blacketNum.leftBracketNum = blacketNum.leftBracketArray.length;
		blacketNum.rightBracketNum = blacketNum.rightBracketArray.length;
		
		return blacketNum;
	}
	//校验添加左括号
	function testRightBracket(string){	//|((\d|[\u4E00-\u9FA5\uf900-\ufa2d]+)[\+\-\*\/](\d|[\u4E00-\u9FA5\uf900-\ufa2d]+))
		var reg = /[\(]$|[\+\-\*\/]$/;
		var lastTextPrev = $('#calculateExp span').eq($('#calculateExp span').length - 2).text();
		var blacketNum = getBlacketNum();
		
		if(string === undefined || string === ''){
			return false;
		}
		if(blacketNum.leftBracketNum === blacketNum.rightBracketNum){
			return false;
		}else if(blacketNum.leftBracketNum > blacketNum.rightBracketNum){
			if(lastTextPrev === '('){
				return false;
			}else{
				return !reg.test(string);
			}
		}
	}
	//校验添加数字
	function testCalcNum(string){
		var reg = /[\)]|[\u4E00-\u9FA5\uf900-\ufa2d]+/;
		if(reg.test(string)){
			return false;
		}else{
			return true;
		}
	}
	//提交时校验括号格式
	function checkBlacket(){
		var blacketNum = getBlacketNum();
		if(blacketNum.leftBracketNum > blacketNum.rightBracketNum){
			alert('括号格式有误，请检查公式');
			return false;
		}
	}
	
	window.parent.TuneHeight();

	//删除费用
	$(".deleteItem").click(function () {
		if($(this).attr("data-id") == null || $(this).attr("data-id") == "") {
			if(confirm("确认删除？")){
				$(this).parent("td").parent("tr").remove();
			}
			return;
		}
		if(confirm("确认删除？")){
			$.ajax({
				type: "POST",
				url: $(this).attr('data-href'),
				data: {id:$(this).attr("data-id"),gbId:$("#gbId").val()},
				dataType: 'json',
				success: function(data,status){
					alert(data.message);
					document.location.reload();
				}
			});
		}
	});
});
</script>
</html>
