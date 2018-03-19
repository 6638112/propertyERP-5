
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-房号管理-编辑</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
<script type="text/javascript">
	$(function(){
		 //表单验证
		$(".inputform").Validform({
			tiptype:3
		});
	});
	function submitValidate(){
		$(".inputform").submit();
	}
	
</script>
</head>

<body>
<div class="info">
	<form class="inputform" method="post" action="../buildingRoom/saveRoom.html">
		<input type="hidden" name="roomId" value="${entity.id }" />
        <h2>房号管理-编辑</h2>
	    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
	      <tr>
	        <td align="right"><span class="red">*</span>所属小区：</td>
	        <td>
	        	<input type="text" class="input_text pp" value="${entity.groupbuildingName }" style="background-color: #dad8d6;" readonly="readonly" />
	        </td>
	      </tr>
	      <tr>
	        <td align="right"><span class="red">*</span> 楼栋名：</td>
	        <td>
	        	<input type="hidden" name="buildingId" value="${entity.tBuildingFId }" />
	        	<input type="text" class="input_text pp" name="buildingName" value="${entity.buildingName }" style="background-color: #dad8d6;" readonly="readonly" />
	         </td>
	      </tr>
	      <tr>
	        <td align="right">单元号：</td>
	        <td><input type="text" class="input_text pp" name="unitName" value="${entity.unitName }" />(格式如:X单元)</td>
	      </tr>
	      <tr>
	        <td align="right"><span class="red">*</span> 房号：</td>
	        <td><input type="text" class="input_text pp" name="rommNum" value="${entity.num }" datatype="*1-10" nullmsg="请填写房号！" errormsg="房号由1到10位字符组成！" /></td>
	      </tr>
	      <tr>
	        <td align="right">户名：</td>
	        <td>
	        	<input type="hidden" name="proprietorId" value="${entity.proprietor.id }" />
	        	<input type="text" class="input_text pp" name="proprietorName" value="${entity.proprietor.name }" />
	        </td>
	      </tr>
	      <%-- <tr>
	        <td align="right"><span class="red">*</span> 住户类型：</td>
	        <td>
	        	<input type="hidden" name="proprietorId" value="${entity.proprietor.id }" />
	        	<input type="text" class="input_text pp" name="proprietorName" value="${entity.proprietor.name }" datatype="*2-10" nullmsg="请填写户名！" errormsg="户名由2到10位字符组成！" />
	        </td>
	      </tr> --%>
	      <tr>
	        <td align="right"> 联系电话：</td>
	        <td>
	        	<input type="text" class="input_text pp" name="proprietorTel" value="${entity.proprietor.phone }" />
	        </td>
	      </tr>
	      <tr>
	        <td align="right"> 身份证：</td>
	        <td>
	        	<input type="text" class="input_text pp" name="proprietorIdentifyNo" value="${entity.proprietor.identifyNo }" />
	        </td>
	      </tr>
	      <%-- <tr>
	        <td align="right"><span class="red">*</span> 备注：</td>
	        <td>
	        	<input type="text" class="input_text pp" name="roomDesc" value="${entity.proprietor.phone }" />
	        </td>
	      </tr> --%>
	    </table>
	    <div class="padb">
	    	<input class="info-btn" type="button" onclick="submitValidate()" value="保 存" />
	    	<input type="button" class="info-btn" onclick="history.back();" value="返回" />
	    </div>
    </form>
</div>
</body>
</html>
