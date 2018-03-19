<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退款管理</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/metro/easyui.css?v20150306">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/icon.css">
<script type="text/javascript">

</script>
</head>

<body>
<div class="info">
    <h2>退款管理</h2>
    <div class="bs-example bgebeb">
    	<form id="searchForm" name="form_main" action="<%=basePath%>/refundOrder/search.html" method="post">
	        <table class="info-list" border="0">
	          <tr>
   				<td><div class="list-name">申请时间：</div></td>
	            <td colspan="3"><input type="text" class="input_text pp icon_dt" id="date01" name="createTime_START" title="请选择起始时间"  value="<%=request.getParameter("createTime_START")==null?"":request.getParameter("createTime_START") %>"> 至 <input type="text" class="input_text pp icon_dt" id="date02" name="createTime_END" title="请选择结束时间"  value="<%=request.getParameter("createTime_END")==null?"":request.getParameter("createTime_END") %>"></td>	
	          	<td><div class="list-name">订单号：</div></td>
	            <td><input type="text" id="orderNo" name="orderNo" value="<%=request.getParameter("orderNo")==null?"":request.getParameter("orderNo") %>" class="input_text pp w120"></td>
	          	<td><div class="list-name">解放号：</div></td>
	            <td><input type="text" id="buyerId" name="buyerId" value="<%=request.getParameter("buyerId")==null?"":request.getParameter("buyerId") %>" class="input_text pp w120"></td>
	          </tr>
	          <tr>
	            <td><div class="list-name">退款时间：</div></td>
	            <td colspan="3"><input type="text" class="input_text pp icon_dt" id="date01" name="sys0UpdTime_START" title="请选择起始时间"  value="<%=request.getParameter("sys0UpdTime_START")==null?"":request.getParameter("sys0UpdTime_START") %>"> 至 <input type="text" class="input_text pp icon_dt" id="date02" name="sys0UpdTime_END" title="请选择结束时间"  value="<%=request.getParameter("sys0UpdTime_END")==null?"":request.getParameter("sys0UpdTime_END") %>"></td>	
	             
	            <td><div class="list-name">退款账户：</div></td>
	            <td>
	            	<select id="refundNo" name="refundNo" class="select_normal w131">
	            		<option value="" >全部..</option>
	            		<option value="1" <%if(request.getParameter("refundNo")!=null&&"1".equals(request.getParameter("refundNo"))){%>selected="selected"<%} %>>微信..</option>
	            		<option value="2" <%if(request.getParameter("refundNo")!=null&&"2".equals(request.getParameter("refundNo"))){%>selected="selected"<%} %>>支付宝..</option>
	            		<option value="3" <%if(request.getParameter("refundNo")!=null&&"3".equals(request.getParameter("refundNo"))){%>selected="selected"<%} %>>银联..</option>
	            		<option value="4" <%if(request.getParameter("refundNo")!=null&&"4".equals(request.getParameter("refundNo"))){%>selected="selected"<%} %>>粮票..</option>
	            		<option value="9" <%if(request.getParameter("refundNo")!=null&&"9".equals(request.getParameter("refundNo"))){%>selected="selected"<%} %>>双乾支付..</option>	            		
	            	</select>
	            </td>
	                        
	            <td><div class="list-name">退款状态：</div></td>
	            <td>
	            	<select id="refundStatus" name="refundStatus" class="select_normal w131">
	            		<option value="" >请选择..</option>
	            		<option value="1" <%if(request.getParameter("refundStatus")!=null&&"1".equals(request.getParameter("refundStatus"))){%>selected="selected"<%} %>>退款处理中..</option>
	            		<option value="2" <%if(request.getParameter("refundStatus")!=null&&"2".equals(request.getParameter("refundStatus"))){%>selected="selected"<%} %>>退款成功..</option>
	            		<option value="3" <%if(request.getParameter("refundStatus")!=null&&"3".equals(request.getParameter("refundStatus"))){%>selected="selected"<%} %>>退款不通过..</option>
	            	</select>
	            </td>
	          </tr>
	        </table>
	  		<div class="mtop10 t_center btn-search">
	  			<input class="input-btn w200" id="searchButtom" type="submit"  value="搜索"/>
	  			<input class="input-btn w200 mar-left15" type="button" onclick="exp();" value="导出退款明细"/>
	  		</div>
  		</form>
    </div>
    <div id="order222" class="easyui-dialog" style="width: 600px; height: 300px; padding: 10px 20px; " closed="true"></div>
    <display:table name="resList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
		<display:column title="申请时间" >${row.createTime}</display:column>
		<display:column title="退款时间" >${row.sys0UpdTime}</display:column>
		<display:column title="订单编号">
			${row.orderNo}
		</display:column>
		<display:column title="支付流水号" property="payFlowNo"/>
		<display:column title="运单状态">
			<c:choose>
				<c:when test="${row.deliveryOrder.status == 0}"><div class="red">未启动</div></c:when>
				<c:when test="${row.deliveryOrder.status == 1}"><div class="red">待发货</div></c:when>
				<c:when test="${row.deliveryOrder.status == 2}"><div class="red">待收货</div></c:when>
				<c:when test="${row.deliveryOrder.status == 3}"><div class="red">确认收货</div></c:when>
			</c:choose>
		</display:column>
		<display:column title="供应商">${row.ebuySupplyMerchant.name}</display:column>	
		<display:column title="解放号" property="buyerId"/>
		<display:column title="退款粮票(元)">${row.refundRedEnvelope}</display:column>
		<display:column title="退款现金(元)">${row.refundMoney}</display:column>
		<display:column title="退款账户">
			<c:choose>
				<c:when test="${row.payStatus ==1}"><div class="red">微信</div></c:when>
				<c:when test="${row.payStatus ==2}"><div class="red">支付宝</div></c:when>
				<c:when test="${row.payStatus ==3}"><div class="red">银联</div></c:when>
				<c:when test="${row.payStatus ==4}"><div class="red">粮票</div></c:when>
				<c:when test="${row.payStatus ==9}"><div class="red">双乾支付</div></c:when>
			</c:choose>		
		</display:column>
		<display:column title="退款方式">
			<c:choose>
				<c:when test="${row.status ==1}"><div class="red">部分退款</div></c:when>
				<c:when test="${row.status ==2}"><div class="red">全额退款</div></c:when>
			</c:choose>
		</display:column>
		<display:column title="申请状态">
			<c:choose>
				<c:when test="${row.refundStatus ==1}"><div class="red">退款处理中</div></c:when>
				<c:when test="${row.refundStatus ==2}"><div class="red">退款成功</div></c:when>
				<c:when test="${row.refundStatus ==3}"><div class="red">退款失败</div></c:when>
			</c:choose>
		</display:column>
		<display:column title="操作人">
			<c:choose>
				<c:when test="${not empty row.updateMan}">${row.updateMan}</c:when>
				<c:otherwise>${row.addMan}</c:otherwise>
			</c:choose>
		</display:column>
		<display:column title="操作">
			<c:choose>
				<c:when test="${row.refundStatus ==1}"><div class="red">
					<a href="../refundOrder/refundDetail.html?refundOrderId=${row.id}"><div class="blue">审核</div></a></div>
				</c:when>
				<c:when test="${row.refundStatus ==2}">
					<div class="red"><a href="../refundOrder/refundDetail.html?refundOrderId=${row.id}"><div class="blue">查看</div></a></div>
				</c:when>
				<c:when test="${row.refundStatus ==3}"><div class="red">
					<a href="../refundOrder/refundDetail.html?refundOrderId=${row.id}"><div class="blue">查看</div></a></div>
				</c:when>
			</c:choose>
		</display:column>		
	</display:table>
	
</div>
<form id="reportfm" action="<%=basePath%>/refundOrder/exportRefund.html" method="post">
		<input type="hidden" id="orderNo1" name="orderNo1"/>
		<input type="hidden" id="buyerId1" name="buyerId1"/>
		<input type="hidden" id="refundNo1" name="refundNo1"/>
		<input type="hidden" id="createTime_START1" name="createTime_START1"/>
		<input type="hidden" id="createTime_END1" name="createTime_END1"/>
		<input type="hidden" id="sys0UpdTime_END1" name="sys0UpdTime_END1"/>
		<input type="hidden" id="sys0UpdTime_START1" name="sys0UpdTime_START1"/>
		<input type="hidden" id="refundStatus1" name="refundStatus1"/>
</form>
</body>
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.common.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
function exp(){
	$("#orderNo1").val($("#orderNo").val());
	$("#buyerId1").val($("#buyerId").val());
	$("#refundNo1").val($("#refundNo").val());
	$("#createTime_START1").val($("input[name='createTime_START']").val());
	$("#createTime_END1").val($("input[name='createTime_END']").val());
	$("#sys0UpdTime_START1").val($("input[name='sys0UpdTime_START']").val());
	$("#sys0UpdTime_END1").val($("input[name='sys0UpdTime_END']").val());
	$("#refundStatus1").val($("#refundStatus").val());
	
	document.getElementById("reportfm").submit();
}
</script>
</html>
