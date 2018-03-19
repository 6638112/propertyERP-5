<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>//" target="_blank"/>
	<title>录入账单</title>
	<link rel="stylesheet" type="text/css" href="../css/common.css" />
	<link rel="stylesheet" type="text/css" href="../css/style.css?v1" />
	<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css"/>
	<style type="text/css">
		html{
			height: auto;
		}
		
		.info-btn {
			margin-top:60px;
		}
	</style>
</head>

<body>
<form class="revenueEditForm1" action="" enctype="multipart/form-data" method="post">
	<div class="info" id="step1">
        <h2>选择计费房号</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1" style="margin-bottom:10px;">
        	<tr>
        		<td width="15%">小区：</td>
        		<td width="85%">
	        		<select id="groupBuilding" onchange="onSelectChange('building');" class="groupBuilding select_normal" data-first-title="选择省小区" title="选择小区"
	            		 datatype="*" nullmsg="请选择小区！">
						<c:forEach items="${groupBuildingList}" var="gb"> 
		                    <option value="${gb.id }">${gb.name }</option>
	            		</c:forEach>
					</select>
        		</td>
        	</tr>
        	<tr>
        		<td>楼栋：</td>
        		<td>
        			<select id="building" onchange="onSelectChange('unitNum');" class="building select_normal" data-first-title="选择省楼栋" title="选择楼栋"
	            		 datatype="*" nullmsg="请选择楼栋！">
					</select>
        		</td>
        	</tr>
        	<tr id="unitNumTr">
        		<td>单元号：</td>
        		<td>
        			<select id="unitNum" onchange="onSelectChange('roomNum');" class="unitNum select_normal" data-first-title="选择省单元号" title="选择单元号"
	            		 datatype="*" nullmsg="请选择单元号！">
						<option value=""></option>
						<c:forEach items="${groupBuildingList}" var="gb"> 
		                    <option value="${gb.id }">${gb.name }</option>
	            		</c:forEach>
					</select>
        		</td>
        	</tr>
        	<tr>
        		<td>房间号：</td>
        		<td>
        			<select id="roomNum" name="roomNum" class="roomNum select_normal" data-first-title="选择省房间号" title="选择房间号"
	            		 datatype="*" nullmsg="请选择房间号！">
						<option value=""></option>
						<c:forEach items="${groupBuildingList}" var="gb"> 
		                    <option value="${gb.id }">${gb.name }</option>
	            		</c:forEach>
					</select>
        		</td>
        	</tr>
        </table>
        <input class="info-btn bnLastStep" type="button" value=" 取消 " onclick="bnLastStep1();" />
        <input id="sumGoods0" class="info-btn" type="button" value=" 确定 " onclick="nextStep();" />
	</div>
</form>

<form class="revenueEditForm2" action="<%=basePath%>/payBill/addAcount.html" enctype="multipart/form-data" method="post">
	<div class="info mtop40" id="step2" style="display:none; padding:0 0 30px;">
        <h2>录入账单页面</h2>
        <input type="hidden" id="addr" name="addr"/>
        <input type="hidden" id="realRoomId" name="realRoomId"/>
        <table class="info-list-02 mtop30" border="0" cellpadding="0" cellspacing="1" style="margin-bottom:10px;">
        	<tr>
        		<td width="15%">客户名称：</td>
        		<td width="85%" class="addrTd">
        			<%-- <input type="text" size="40" style="height:25px" id="addr" name="addr" size="50px" readonly="true"/>--%>
        		</td>
        	</tr>
        	<tr>
        		<td><span class="red">*</span> 账单名称：</td>
        		<td>
        			<input class="input_text pp " type="text" size="40" style="height:25px" name="accountName" placeholder="请填写账单名称" maxlength="30" datatype="*2-30" nullmsg="请填写账单名称" errormsg="账单名称为4-30个字符" />
        		</td>
        	</tr>
        	<tr>
        		<td><span class="red">*</span> 账单月份：</td>
        		<td>
        			<input class="input_text pp " type="text" size="40" style="height:25px" name="accountMonth" maxlength="30" placeholder="如：2016年2月" datatype="*" nullmsg="请填写账单月份" />
        		</td>
        	</tr>
        	<tr>
        		<td><span class="red">*</span> 缴费时间：</td>
        		<td>
        			<input id="date01" title="请选择缴费时间" class="input_text pp" type="text" size="40" style="height:25px" name="payTime" value="请选择缴费时间" maxlength="30" datatype="*" nullmsg="请选择缴费时间" />
        		</td>
        	</tr>
        	<tr>
        		<td><span class="red">*</span> 账单明细：</td>
        		<td>
        			<table id="detailFeeTable" class="mars info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
					<thead>
					<tr>
					<th>收费项目名称</th>
					<th>金额(元)</th>
					<th>操作</th></tr></thead>
					
					<tr class="templatTR dsn">
						<td class=""><input class="input_text w200 pp " type="text" size="40" style="height:25px" name="detailFeeName" maxlength="10" placeholder="如：水费" datatype="*2-10" nullmsg="请填写收费项目" errormsg="收费项目为2-10个字符" /></td>
						<td class=""><input class="input_text w120 pp "  type="text" size="40" style="height:25px" name="detailFeeAmount" maxlength="30" datatype="numberFixTwo & numberAndDecimals" nullmsg="请填写金额" errormsg="金额为数字，可带两位小数" /></td>
						<td class=""><input class="input-btn w80"  type="button" value="删除此栏" onclick="removeTR(this);" ></td>
					</tr>

					<tr>
						<td class=""><input class="input_text w200 pp " type="text" size="40" style="height:25px" name="detailFeeName" maxlength="10" placeholder="如：水费" datatype="*2-10" nullmsg="请填写收费项目" errormsg="收费项目为2-10个字符" /></td>
						<td class=""><input class="input_text w120 pp "  type="text" size="40" style="height:25px" name="detailFeeAmount" maxlength="10" datatype="numberFixTwo & numberAndDecimals" nullmsg="请填写金额" errormsg="金额为数字，可带两位小数" /></td>
						<td class="" width="100"></td>
					</tr>
					
					</table>
					<br /> 
					<input id="addDetailFeeBtn" class="input-btn w80" type="button" value="增加一栏">
        		</td>
        	</tr>
        </table>
        <input class="info-btn bnLastStep" type="button" value=" 返回 " onclick="bnLastStep2();" />
        <input id="sumGoods2" class="info-btn" type="submit" value="提交" />
	</div>
</form>



<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js?v4"></script>
<script type="text/javascript" src="js/revenue/layer.js?v2"></script>
<script type="text/javascript" src="js/jquery.common.js?v2015091802"></script>
<script type="text/javascript" src="js/Validform_v5.3.2.js?v2"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/revenue/revenueConfig.js?v=12"></script>
<script type="text/javascript" src="js/revenue/moment.js"></script>

<script type="text/javascript">

$("#addDetailFeeBtn").click(function(){
	var $tr = $(".templatTR");
	$("#detailFeeTable").append($tr.clone().show().removeClass("templatTR dsn"));
});

function removeTR(ths){
	var $this = $(ths);
	$this.parent('td').parent('tr').remove();
};

(function($){
	onSelectChange('building');
})(jQuery);

function onSelectChange(toSelId){
	setSelect(toSelId);
}

function setSelect(toSelId){
	if(toSelId === "building"){//选择省，更新市
		jQuery.ajax({
			  url: "payBill/getSelectBuildingList.html",
			  cache: false,
			  dataType:"json",
			  async:false,
			  data:"id=" + $("#groupBuilding").val(),
			  success: function(data){
				document.getElementById('building').innerHTML="";//清空之前的选项
				document.getElementById('unitNum').innerHTML="";//清空之前的选项
				document.getElementById('roomNum').innerHTML="";//清空之前的选项
			    $.each(JSON.parse(data), function(i, item) {
			    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
			    });
			    setSelect('unitNum');
			  }
			});
	} else if(toSelId === "unitNum") {
		 jQuery.ajax({
			  url: "payBill/getSelectUnitNameList.html",
			  cache: false,
			  dataType:"json",
			  async:false,
			  data:"id=" + $("#building").val(),
			  success: function(data){
				 document.getElementById('unitNum').innerHTML="";//清空之前的选项
				 document.getElementById('roomNum').innerHTML="";//清空之前的选项
				 if(data !='[null]') {
					 $("#unitNumTr").show();
					 $.each(JSON.parse(data), function(i, item) {
					    	$("<option value='" + item.unitName + "'>" + item.unitName + "</option>").appendTo($("#"+toSelId));
					    });
				 } else {
					 $("#unitNumTr").hide();
				 }
			     setSelect('roomNum');
			  }
			});
	} else if(toSelId === "roomNum") {
			 jQuery.ajax({
				  url: "payBill/getSelectRoomNumList.html",
				  cache: false,
				  dataType:"json",
				  async:false,
				  data:"buildingId=" + $("#building").val() + "&unitName=" + $("#unitNum").val(),
				  success: function(data){
					document.getElementById('roomNum').innerHTML="";//清空之前的选项
				    $.each(JSON.parse(data), function(i, item) {
				    	$("<option value='" + item.id + "'>" + item.roomNum + "</option>").appendTo($("#"+toSelId));
				    });
				  }
			 });
	}
}

function nextStep() {
	$.post("<%=basePath%>/payBill/getAddr.html", {
		realRoomId : $("#roomNum").val()
	}, function(data) {
		//alert(JSON.stringify(data));
		if (data.message) {
			alert(data.message);
			//$.messager.alert('操作提示',data.message,data.status);
		}
		if(data.status == 'info') {
			$("#step1").hide();
			$("#step2").show();
			$("#addr").val(data.dataValue.addr);
			$(".addrTd").html(data.dataValue.addr);
			$("#realRoomId").val($("#roomNum").val());
		};
	});
}

function bnLastStep1() {
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	parent.layer.close(index);
}

function bnLastStep2() {
	$("#step2").hide();
	$("#step1").show();
}
	
function alertInfo(info,callBack){
	callBack();
	/* var f = callBack();
	layer.alert(info, {
	    shade: 0.1,
	    closeBtn: 0
	},f); */
	alert(info);
}

(function($){
    //表单验证
	$(".revenueEditForm1").Validform({
		btnSubmit:"#sumGoods1",
		tiptype:4,
		ajaxPost:true,
		beforeSubmit:function(curform){
			$(".revenueEditForm1").attr('onsubmit','return false;');
		},
		callback:function(data){
			//$(".revenueEditForm").ajaxSubmit(function(data) {
				try {
					data = eval(data);
				} catch (e) {
					data = eval("("+data+")");
				}
				if (data.status == '0000') {
					//parent.layer.msg('操作成功', {shade: 0.3});
					alertInfo('操作成功',function fn1(){
						//closeWin("aaa");
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						parent.layer.close(index);
					});
				} else {
					alertInfo(data.message,function fn2(){
						$(".revenueEditForm1").Validform().resetStatus();
					});
				}
				//});
			//return false;
		}
	});
    
	 //表单验证
	$(".revenueEditForm2").Validform({
		btnSubmit:"#sumGoods2",
		tiptype:3,
		ignoreHidden:true,
		//dragonfly:true,
		ajaxPost:true,
		beforeSubmit:function(curform){
			$(".revenueEditForm2").attr('onsubmit','return false;');
		},
		callback:function(data){
			//$(".revenueEditForm").ajaxSubmit(function(data) {
				try {
					data = eval(data);
				} catch (e) {
					data = eval("("+data+")");
				}
				if (data.status == '0000') {
					//parent.layer.msg('操作成功', {shade: 0.3});
					alertInfo('操作成功',function fn1(){
						//closeWin("aaa");
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						parent.layer.close(index);
					});
				} else {
					alertInfo(data.message,function fn2(){
						$(".revenueEditForm2").Validform().resetStatus();
					});
				}
				//});
			//return false;
		}
	});
})(jQuery);


</script>

</body>
</html>

