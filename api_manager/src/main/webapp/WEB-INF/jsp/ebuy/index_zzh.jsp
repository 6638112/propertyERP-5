<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单管理</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/jquery.simple-dtpicker.css">
<script type="text/javascript">
	var Common = {
		currentFormatter: function (value, rec, index) {
	    	if(value != null && value !=''){
	    		return value/100;
	    	}else {
	    		return value;
	    	}
	    },
	    payMethodFormatter: function (value, rec, index) {
	    	if(value == '1'){
	    		return '微信支付';
	    	}else if(value == '2'){
	    		return '支付宝';
	    	}else {
	    		return '其他方式';
	    	}
	    },
	    orderStatusFormatter: function (value, rec, index) {
	    	if(value == '1'){
	    		return '待付款';
	    	}else if(value == '2'){
	    		return '已取消';
	    	}else if(value == '3'){
	    		return '待发货';
	    	}else if(value == '4'){
	    		return '待收货';
	    	}else if(value == '5'){
	    		return '待评价';
	    	}else if(value == '6'){
	    		return '已完成';
	    	}else if(value == '99'){
	    		return '已删除';
	    	}
	    },
	    payStatusFormatter: function (value, rec, index) {
	    	if(value == '1'){
	    		return '未支付';
	    	}else if(value == '2'){
	    		return '支付成功';
	    	}else if(value == '3'){
	    		return '支付失败';
	    	}
	    }
	};

</script>
</head>

<body>
<<<<<<< .mine
<div class="info">
    <h2>订单管理</h2>
    <div class="bs-example bgebeb">
    	<form id="searchForm" name="form_main" action="<%=basePath%>/order/index.html" method=post>
	        <table class="info-list" border="0">
	          <tr>
	            <td width="95" style="min-width:95px;"><div align="right">订单号：</div></td>
	            <td width="120"><input type="text" id="orderNo" name="orderNo" class="input_text w200"></td>
	            <td width="95" style="min-width:95px;"><div class="list-name">买家花号：</div></td>
	            <td width="120"><input type="text" id="huaId" name="huaId" class="input_text w120"></td>
	            <td width="95" style="min-width:95px;"><div class="list-name">联系人：</div></td>
	            <td width="120"><input type="text" id="realName" name="realName" class="input_text w120"></td>
	            <td width="95" style="min-width:95px;"><div class="list-name">联系方式：</div></td>
	            <td width="120"><input type="text" id="mobile" name="mobile" class="input_text w120"></td>
	          </tr>
	          <tr>
	            <td><div class="list-name">商品名称：</div></td>
	            <td><input type="text" id="productName" name="productName" class="input_text w200"></td>
	            <td><div class="list-name">商品类别：</div></td>
	            <td><input type="text" id="typeName" name="typeName" class="input_text w120"></td>
	            <!-- <td>
	                    <select class="select_normal w131">
	                    <option>全部</option>
	                    <option>供港蔬菜</option>
	                    <option>休闲食品</option>
	                    </select>
	            </td> -->
	            <td><div class="list-name">供应商名称：</div></td>
	            <td><input type="text" id="supplyName" name="supplyName" value="${merchantName}" <c:if test="${merchantName != ''}">readonly="readonly"</c:if> class="input_text w120"></td>
	            <!-- <td>
	                    <select class="select_normal w131">
	                    <option>全部</option>
	                    <option>布吉农批市场</option>
	                    <option>南山农批市场南山农批市场</option>
	                    </select>
	            </td> -->
	            <td><div class="list-name">支付方式：</div></td>
	            <td>
	                    <select id="payMethod" name="payMethod" class="select_normal w131">
		                    <option value="">请选择..</option>
			       			<option value="1">微信支付</option>
			       			<option value="2">支付宝</option>
	                    </select>
	            </td>
	          </tr>
	          <tr>
	            <td><div class="list-name">订单状态：</div></td>
	            <td>
	                    <select  id="orderStatus" name="orderStatus" class="select_normal w131">
		                    <option value="">请选择..</option>
			       			<option value="1">待付款</option>
			       			<option value="2">已取消</option>
			       			<option value="3">待发货</option>
			       			<option value="4">待收货</option>
			       			<option value="5">待评价</option>
			       			<option value="6">已完成</option>
			       			<option value="99">已删除</option>
	                    </select>
	            </td>
	            <td><div class="list-name">支付状态：</div></td>
	            <td>
	                    <select id="payStatus" name="payStatus" class="select_normal w131">
		                    <option value="">请选择..</option>
						 	<option value="1">未支付</option>
			       			<option value="2">支付成功</option>
			       			<option value="3">支付失败</option>
	                    </select>
	            </td>
	            <td><div class="list-name">订单日期：</div></td>
	            <td colspan="3"><input type="text" class="input_text icon_dt" id="date01" name="startTime" title="请选择起始时间" value=""> 至 <input type="text" class="input_text icon_dt" id="date02" name="endTime" title="请选择结束时间" value=""></td>
	            
	          </tr>
	        </table>
	  		<div class="mtop10 t_center btn-search">
	  			<input class="input-btn w200" id="searchButtom" type="submit"  value="搜索">
	  			<input class="input-btn w200" type="button" onclick="exp();" value="导出订单明细">
	  		</div>
  		</form>
    </div>
    <div id="order222" class="easyui-dialog" style="width: 600px; height: 300px; padding: 10px 20px" closed="true"></div>
    <!-- 订单列表 -->
    <display:table name="resJsonMessage.rows" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" >
		<display:column title="序号" sortable="true" headerClass="sortable">
		<c:out value="${row_rowNum}"/></display:column>
		<display:column title="订单编号" property="orderNo" sortable="true"/>
		<display:column title="买家花号" property="huaId" sortable="true"/>
		<display:column title="买家姓名" property="realName" sortable="true"/>
		<display:column title="订单日期" property="buyTime" sortable="true"/>
		<display:column title="订单总金额(元)" property="amount" sortable="true"/>
		<display:column title="实际支付(元)" property="factAmount" sortable="true"/>
		<display:column title="支付方式" property="payMethod" sortable="true"/>
		<display:column title="订单状态" property="status" sortable="true"/>
		<display:column title="支付状态" property="payStatus" sortable="true"/>
		
		
		<display:column title="操作" media="html">
			<!-- <div class="list-name"><a class="blue" href="#">查看</a></div> -->
			<%-- <div align="left"><a class="blue" href="<%=basePath%>/order/writeDelivery.html?id=${row.id}&orderNo=${row.orderNo}&rand="+Math.random()>录入物流</a></div> --%>
			
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="writeDelivery('${row.id}','${row.orderNo}')">录入物流单号</a>
			
			<%-- <a class="blue" href="<%=basePath%>/notice/noticeEdit.html?id=${row.id}">修改</a> &nbsp;&nbsp;&nbsp;&nbsp;
			<a class="deleteNotice" href="#" name="${row.id}" >删除</a>  --%>
		</display:column>
	</display:table>
</div>
=======
	<table id="dg" title="订单列表" fit="true" toolbar="#toolbar" pagination="true" rownumbers="true" pageSize="30"
		fitColumns="true" singleSelect="true" loadMsg="正在查询...">
		<thead>
			<tr>
				<th field="id" width="60">id</th>
				<th field="orderNo" width="150">订单编号</th>
				<th field="huaId" width="100">买家花号</th>
				<th field="realName" width="80">买家姓名</th>
				<th field="buyTime" width="100">订单日期</th>
				<th field="amount" width="80" formatter="Common.currentFormatter">订单总金额(元)</th>
				<th field="factAmount" width="80" formatter="Common.currentFormatter">实际支付(元)</th>
				<th field="payMethod" width="80" formatter="Common.payMethodFormatter">支付方式</th>
				<th field="status" width="80" formatter="Common.orderStatusFormatter">订单状态</th>
				<th field="payStatus" width="80" formatter="Common.payStatusFormatter">支付状态</th>
			</tr>
		</thead>
	</table>

	<div id="toolbar">
		<span>订单编号:</span><input type="text" id="orderNo" name="orderNo"/>
		<span>买家花号:</span><input type="text" id="huaId" name="huaId"/>
		<span>联系人:</span><input type="text" id="realName" name="realName"/>
		<span>联系方式:</span><input type="text" id="mobile" name="mobile"/><br/>
		<span>商品名称:</span><input type="text" id="productName" name="productName"/>
		<span>商品类别:</span><input type="text" id="typeName" name="typeName"/>
		<span>供应商:</span><input type="text" id="supplyName" name="supplyName" value="${merchantName}" <c:if test="${merchantName != ''}">readonly="readonly"</c:if>/>
		<span>支付方式:</span>
			<select class="easyui-combobox" id="payMethod" name="payMethod" style="width:150px;">
				<option value="">请选择..</option>
	       		<option value="1">微信支付</option>
	       		<option value="2">支付宝</option>
	        </select>
		<br/>
		<span>订单状态:</span>
			 <select class="easyui-combobox" id="orderStatus" name="orderStatus" style="width:150px;">
			 		 <option value="">请选择..</option>
	       			 <option value="1">待付款</option>
	       			 <option value="2">已取消</option>
	       			 <option value="3">待发货</option>
	       			 <option value="4">待收货</option>
	       			 <option value="5">待评价</option>
	       			 <option value="6">已完成</option>
	       			 <option value="99">已删除</option>
	        </select>
		<span>支付状态:</span>
			 <select class="easyui-combobox" id="payStatus" name="payStatus" style="width:150px;">
			 	 <option value="">请选择..</option>
			 	 <option value="1">未支付</option>
       			 <option value="2">支付成功</option>
       			 <option value="3">支付失败</option>
        	</select>
		<br/>
		<span>订单日期:</span><input type="text" id="startTime" name="startTime" class="easyui-datebox"/>至<input type="text" id="endTime" name="endTime" class="easyui-datebox"/>
	    <a href="javascript:void(0);" onclick="findData();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
	    <br/>
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="writeDelivery()">录入物流单号</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="exp()">导出订单明细</a>
	</div>
	
	<div id="order" class="easyui-dialog" style="width: 600px; height: 700px; padding: 10px 20px" closed="true">
	</div>
>>>>>>> .r1492
	<form id="reportfm" action="<%=basePath%>/order/orderExport.html" method="post">
		<input type="hidden" id="orderNo1" name="orderNo"/>
		<input type="hidden" id="huaId1" name="huaId"/>
		<input type="hidden" id="realName1" name="realName"/>
		<input type="hidden" id="mobile1" name="mobile"/>
		<input type="hidden" id="productName1" name="productName"/>
		<input type="hidden" id="typeName1" name="typeName"/>
		<input type="hidden" id="supplyName1" name="supplyName"/>
		<input type="hidden" id="payMethod1" name="payMethod"/>
		<input type="hidden" id="orderStatus1" name="orderStatus"/>
		<input type="hidden" id="payStatus1" name="payStatus"/>
		<input type="hidden" id="startTime1" name="startTime"/>
		<input type="hidden" id="endTime1" name="endTime"/>
	</form>
	
</body>
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.common.js"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/icon.css">
<script type="text/javascript" src="<%=basePath%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
		function writeDelivery(rowId,orderNo) {
			if (rowId) {
				$("#order222").dialog({
		            title: '录入物流单号',
		            href: '<%=basePath%>/order/writeDelivery.html?id='+rowId+'&orderNo='+orderNo+'&rand='+Math.random(),
		            iconCls: 'icon-edit',
		            width: 400,
		            height: 250,
		            modal: true,
		            closed: false
		        }).dialog('open');
			}
		}
		
		function exp(){
			$('#orderNo1').val($('#orderNo').val());
			$('#huaId1').val($('#huaId').val());
			$('#realName1').val($('#realName').val());
			$('#mobile1').val($('#mobile').val());
			$('#productName1').val($('#productName').val());
			$('#typeName1').val($('#typeName').val());
			$('#supplyName1').val($('#supplyName').val());
			$('#payMethod1').val($('#payMethod').val());
			$('#orderStatus1').val($('#orderStatus').val());
			$('#payStatus1').val($('#payStatus').val());
			$('#startTime1').val($('#startTime').val());
			$('#endTime1').val($('#endTime').val());
			
			/* $('#payMethod1').val($('#payMethod').combobox('getValue'));
			$('#orderStatus1').val($('#orderStatus').combobox('getValue'));
			$('#payStatus1').val($('#payStatus').combobox('getValue'));
			$('#startTime1').val($('#startTime').datebox('getValue'));
			$('#endTime1').val($('#endTime').datebox('getValue')); */
			document.getElementById("reportfm").submit();
		}
		
	</script>
</html>
