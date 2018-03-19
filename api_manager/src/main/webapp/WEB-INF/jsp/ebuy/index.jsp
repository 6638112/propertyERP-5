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
<link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/metro/easyui.css?v20150306">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/icon.css">
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
<div class="info">
    <h2>订单管理</h2>
    <div class="bs-example bgebeb">
    	<form id="searchForm" name="form_main" action="<%=basePath%>/order/index.html" method=post>
	        <table class="info-list" border="0">
	          <tr>
	            <td><div align="right">订单号：</div></td>
	            <td><input type="text" id="orderNo" name="orderNo" value="<%=request.getParameter("orderNo")==null?"":request.getParameter("orderNo") %>" class="input_text w120"></td>
	            <td><div class="list-name">买家花号：</div></td>
	            <td><input type="text" id="huaId" name="huaId" value="<%=request.getParameter("huaId")==null?"":request.getParameter("huaId") %>" class="input_text w120"></td>
	            <td><div class="list-name">联系人：</div></td>
	            <td><input type="text" id="realName" name="realName" value="<%=request.getParameter("realName")==null?"":request.getParameter("realName") %>" class="input_text w120"></td>
	            <td><div class="list-name">联系方式：</div></td>
	            <td><input type="text" id="mobile" name="mobile" value="<%=request.getParameter("mobile")==null?"":request.getParameter("mobile") %>" class="input_text w120"></td>
	          </tr>
	          <tr>
	            <td><div class="list-name">商品名称：</div></td>
	            <td><input type="text" id="productName" name="productName" value="<%=request.getParameter("productName")==null?"":request.getParameter("productName") %>" class="input_text w120"></td>
	            <td><div class="list-name">商品类别：</div></td>
	            <td><input type="text" id="typeName" name="typeName" value="<%=request.getParameter("typeName")==null?"":request.getParameter("typeName") %>" class="input_text w120"></td>
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
		                    <option value="" >请选择..</option>
			       			<option value="1" <%if(request.getParameter("payMethod")!=null&&"1".equals(request.getParameter("payMethod"))){%>selected="selected"<%} %>>微信支付</option>
			       			<option value="2" <%if(request.getParameter("payMethod")!=null&&"2".equals(request.getParameter("payMethod"))){%>selected="selected"<%} %>>支付宝</option>
	                    </select>
	            </td>
	          </tr>
	          <tr>
	            <td><div class="list-name">订单状态：</div></td>
	            <td>
	                    <select  id="orderStatus" name="orderStatus" class="select_normal w131">
		                    <option value="">请选择..</option>
			       			<option value="1" <%if(request.getParameter("orderStatus")!=null&&"1".equals(request.getParameter("orderStatus"))){%>selected="selected"<%} %>>待付款</option>
			       			<option value="2" <%if(request.getParameter("orderStatus")!=null&&"2".equals(request.getParameter("orderStatus"))){%>selected="selected"<%} %>>已取消</option>
			       			<option value="3" <%if(request.getParameter("orderStatus")!=null&&"3".equals(request.getParameter("orderStatus"))){%>selected="selected"<%} %>>待发货</option>
			       			<option value="4" <%if(request.getParameter("orderStatus")!=null&&"4".equals(request.getParameter("orderStatus"))){%>selected="selected"<%} %>>待收货</option>
			       			<option value="5" <%if(request.getParameter("orderStatus")!=null&&"5".equals(request.getParameter("orderStatus"))){%>selected="selected"<%} %>>待评价</option>
			       			<option value="6" <%if(request.getParameter("orderStatus")!=null&&"6".equals(request.getParameter("orderStatus"))){%>selected="selected"<%} %>>已完成</option>
			       			<option value="99" <%if(request.getParameter("orderStatus")!=null&&"99".equals(request.getParameter("orderStatus"))){%>selected="selected"<%} %>>已删除</option>
	                    </select>
	            </td>
	            <td><div class="list-name">支付状态：</div></td>
	            <td>
	                    <select id="payStatus" name="payStatus" class="select_normal w131">
		                    <option value="">请选择..</option>
						 	<option value="1" <%if(request.getParameter("payStatus")!=null&&"1".equals(request.getParameter("payStatus"))){%>selected="selected"<%} %>>未支付</option>
			       			<option value="2" <%if(request.getParameter("payStatus")!=null&&"2".equals(request.getParameter("payStatus"))){%>selected="selected"<%} %>>支付成功</option>
			       			<option value="3" <%if(request.getParameter("payStatus")!=null&&"3".equals(request.getParameter("payStatus"))){%>selected="selected"<%} %>>支付失败</option>
	                    </select>
	            </td>
	            <td><div class="list-name">订单日期：</div></td>
	            <td colspan="3"><input type="text" class="input_text icon_dt" id="date01" name="startTime" title="请选择起始时间"  value="<%=request.getParameter("startTime")==null?"":request.getParameter("startTime") %>"> 至 <input type="text" class="input_text icon_dt" id="date02" name="endTime" title="请选择结束时间"  value="<%=request.getParameter("endTime")==null?"":request.getParameter("endTime") %>"></td>
	            
	          </tr>
	        </table>
	  		<div class="mtop10 t_center btn-search">
	  			<input class="input-btn w200" id="searchButtom" type="submit"  value="搜索"/>
	  			
	  			<input class="input-btn w200 mar-left15" type="button" onclick="exp();" value="导出订单明细"/>
	  		</div>
  		</form>
    </div>
    <div id="order222" class="easyui-dialog" style="width: 600px; height: 300px; padding: 10px 20px; " closed="true"></div>
    <!-- 订单列表 -->
    <display:table name="resJsonMessage.rows" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" >
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
		            closed: true
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
