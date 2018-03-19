
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-小区管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
	$(function(){
		//表单验证
		$(".inputform").Validform({
			tiptype:3
		});
	});
	function submitValidate(){
		//如果居委会和街道办电话有录入，则需要验证电话格式
		var isPhone=/^(([0-9]{3,4}-)?[0-9]{7,8})|(((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|14[012356789][0-9]{8}|15[012356789][0-9]{8}|17[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7}))$/;
		if($("#street_tel").val()){
			if(isPhone.test($("#street_tel").val())==false){
		        alert('街道办电话号码格式不正确！');
		        return false;
		    }
		}
		if($("#neighbor_tel").val()){
			if(isPhone.test($("#neighbor_tel").val())==false){
		        alert('居委会电话号码格式不正确！');
		        return false;
		    }
		}
		
		//验证小区是否在当前的省市区地区存在
		if(checkGroupbuilding() == true){
			$(".inputform").submit();
		}
			
	}
	
	function checkGroupbuilding(){
		var flag = true;
		if(!$("#groupbuilding_id").val()){
			var gbName = $("#groupbuilding_name").val();
			var blockId = $('#block').val();
			$.ajax({//使用ajax请求
				type:"GET",
				url:"../groupBuilding/validateGBIsExists.html",
				async:false,
				data:{gbName:gbName,blockId:blockId,},
				success:function(data, textStatus){
			        if(data=='NO'){
			        	alert('您当前录入的小区已经存在，不能再次重复录入!');
			        	flag = false;
			        }else if(data=='OK'){
			        	flag = true;
			        }else{
			        	$("#groupbuilding_id").val(data);
			        	flag = true;
			        }
				},
			}); 
		}
		return flag;
	}
	//省市切换
	function onSelectChange(obj,toSelId){
		setSelect(obj.value,toSelId);
	}
	//省市切换详情逻辑处理
	function setSelect(fromSelVal,toSelId){
		document.getElementById(toSelId).innerHTML="";//清空之前的选项
		if(toSelId === "city"){//选择省，更新市
			jQuery.ajax({
				  url: "../propertyCompany/getCityList.html",
				  cache: false,
				  dataType:"json",
				  async:false,
				  data:"apId="+fromSelVal,
				  success: function(data){
				    $.each(JSON.parse(data), function(i, item) {
				    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
				    });
				  }
				});
			$("#city").val(1);
			$("#block").empty();
		}else {
			jQuery.ajax({//选择市，更新区
				  url: "../propertyCompany/getBlockList.html",
				  cache: false,
				  dataType:"json",
				  data:"acId="+fromSelVal,
				  success: function(data){
				    $.each(JSON.parse(data), function(i, item) {
				    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
				    });
				  }
				});
		}	
	}
</script>
</head>

<body>
<div class="info">
	<form class="inputform" method="post" action="../groupBuilding/saveEdit.html">
		<input type="hidden" id="groupbuilding_id" name="gbId" value="${entity.id }" />
		<input type="hidden" name="companyId" value="${entity.tPropertyCompanyFId }" />
        <h2>小区管理</h2>
	    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
	      <tr>
	        <td width="120" align="right"><span class="red">*</span> 小区名称：</td>
	        <td><input type="text" class="input_text pp" id="groupbuilding_name" name="gbName" value="${entity.name }" datatype="*2-20" nullmsg="请填写小区名称！" errormsg="小区名称至少2个字！"/></td>
	      </tr>
	      <tr>
	        <td align="right">小区所属物业：</td>
	        <td>${entity.propertyCompanyName }</td>
	      </tr>
	      <tr>
	        <td align="right"><span class="red">*</span> 小区所在地：</td>
	        <td>
	        	<select id="province" onchange="onSelectChange(this,'city');" class="province select_normal" data-first-title="选择省" title="选择省" datatype="n" errormsg="请选择省！" nullmsg="请选择省！">
                	<option value="-">选择省</option>
                	<c:forEach items="${pcbList}" var="pcb" >
                		<option value="${pcb.id}" <c:if test="${entity.addressProvinceName==pcb.name }">selected="selected"</c:if>>${pcb.name}</option>
                	</c:forEach>
                </select> 
                <select id="city" onchange="onSelectChange(this,'block');"  class="city select_normal" data-first-title="选择市" datatype="n" errormsg="请选择市！" nullmsg="请选择市！">
                	<option value="-">选择市</option>
                	<c:if test="${entity.addressCityName!=null }"><option value="0" selected="selected">${entity.addressCityName}</option></c:if>
                </select> 
                <select id="block" name="blockId" class="area select_normal" data-first-title="选择区" datatype="n" errormsg="请选择区！" nullmsg="请选择区！">
                	<option value="-">选择区</option>
                	<c:if test="${entity.addressBlockName!=null }"><option value="${entity.tBlockFId}" selected="selected">${entity.addressBlockName}</option></c:if>
                </select>
	         </td>
	      </tr>
	      <tr>
	        <td align="right"><span class="red">*</span> 详细地址：</td>
	        <td><input type="text" class="input_text pp" name="addressDesc" value="${entity.addressDesc }" datatype="*2-20" nullmsg="请填写详细地址！" errormsg="详细地址由2到20位字符组成！" /></td>
	      </tr>
	      <tr>
	        <td align="right">管理处：</td>
	        <td>
	        	<c:if test="${isMgt==1 }">
	        		<input type="hidden" name="managementId" value="${entity.tPropertyManagementFId }" />
	        		${entity.propertyManagementName }
	        	</c:if>
	        	<c:if test="${1==isPc }">
	        	<select name="managementId" class="province select_normal" data-first-title="选择管理处" title="选择管理处">
                	<option value="">选择管理处</option>
                	<c:forEach items="${managements}" var="mgt" >
                		<option value="${mgt.id}" <c:if test="${entity.tPropertyManagementFId==mgt.id }">selected="selected"</c:if>>${mgt.name}</option>
                	</c:forEach>
                </select> 
                </c:if>
	        </td>
	      </tr>
	      <tr>
	        <td align="right">小区所属街道办：</td>
	        <td><input type="text" class="input_text pp" name="streetName" value="${entity.streetName }" /></td>
	      </tr>
	      <tr>
	        <td align="right">街道办电话：</td>
	        <td><input type="text" class="input_text pp" id="street_tel" name="streetTel" value="${entity.streetTel }" /></td>
	      </tr>
	      <tr>
	        <td align="right">小区所在居委会：</td>
	        <td><input type="text" class="input_text pp" name="neighborName" value="${entity.neighborName }"/></td>
	      </tr>
	      <tr>
	        <td align="right">居委会电话：</td>
	        <td><input type="text" class="input_text pp" id="neighbor_tel" name="neighborTel" value="${entity.neighborTel }" /></td>
	      </tr>
	      <c:if test="${entity.id!=null }">
	      <tr>
	        <td align="right">缴费周期：</td>
	        <td>每月${entity.payPeriodStart }日 至 ${entity.payPeriodEnd }日</td>
	      </tr>
	      </c:if>
	    </table>
	    <div class="padb"><input class="info-btn" type="button" onclick="submitValidate()" value="保 存" /></div>
    </form>
</div>
</body>
</html>
