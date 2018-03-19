<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报修管理-报修配置-开通业主报修</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
    <h2>开通业主报修 
        <span class="f12 mar-left15">
            <span class="step orange"><span class="f16 bold">Step 1</span>：开通报修</span>
            <span class="step"><span class="grey">-></span> <span class="f16 bold">Step 2</span>：增加报修工人</span>
        </span>
    </h2>
    <form id="inputform" class="inputform" action="../propertyRepair/saveOpenRepairService.html" method="post">
    <div class="distr">
    		<input type="hidden" name="gbId" value="${gbId }"/>
	        <div class="bs-example">
	            <table class="info-list citySelect" border="0">
			        <tr>
			            <td>负责人电话：</td>
		            </tr>
		            <tr>
			            <td><input type="text" class="input_text" maxlength="11" name="serviceMobile" datatype="m" errormsg="请填写正确的手机号！" nullmsg="请填写手机号！"></td>
			        </tr>
		            <tr>
			            <td><span class="f12 grey">解放区app上有产生报修单时，会首先发提醒短信到该负责人手机上</span></td>
			        </tr>
			    </table>
	      	</div>
    </div>
    <div class="padb"><div class="info-btn mtop20 left editRepair"><a href="#" onclick="$('#inputform').submit();">确认开通</a></div></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		dragonfly:true,
	});
});
</script>
</html>
