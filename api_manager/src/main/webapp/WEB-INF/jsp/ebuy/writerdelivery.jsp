<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>录入物流单号</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/jquery.simple-dtpicker.css">

<%@ include file="../common/static.jsp"%>
</head>
<body>
	
		<div class="ftitle">录入订单编号：${orderNo} 的物流信息</div>
		<form id="fm" method="post" novalidate enctype="multipart/form-data">
			<div class="fitem">
				<label>物流名称:</label> <input name="logisticsName" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>物流单号:</label> <input name="logisticseCode" class="easyui-validatebox" required="true">
			</div>
			<input type="hidden" name="id" id="id" value="${id}"/>
			<input type="hidden" name="orderNo" id="orderNo" value="${orderNo}"/>
		</form>
		
		<div class="ftitle"></div>
		<div align="center">
			<button id="subbtn" class="easyui-linkbutton" iconCls="icon-ok">提交</button>
			<!-- <button id="cancelbtn" class="easyui-linkbutton" iconCls="icon-cancel">取消</button> -->
		</div>
		
	<script type="text/javascript">
		$("#subbtn").bind("click",function(){ 			
			$('#fm').form('submit', {
				url : '<%=basePath%>/order/doWriteDelivery.html?rand='+Math.random(),
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(data) {
					var data = eval('(' + data + ')');
					if (data.message) {
						$.messager.alert('友情提示',data.message,data.result,function(){
							if(data.result != 'error'){
								$('#order').dialog('close');
								$('#dg').datagrid('reload');
							}
						});
					}
				}
			});
		});
		
		$("#cancelbtn").bind("click",function(){
			$('#order').dialog('close');
		});
		
	</script>
	
</body>
</html>
