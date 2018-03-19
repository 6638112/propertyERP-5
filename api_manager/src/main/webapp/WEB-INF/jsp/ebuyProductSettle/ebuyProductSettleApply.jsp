<%@page import="com.cnfantasia.server.ms.ebuyProductSettle.constants.PageType"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%
	response.setHeader("Pragma","No-cache");    
	response.setHeader("Cache-Control","no-cache");    
	response.setDateHeader("Expires", -10);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>申请结算</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<style>
			.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
			.w180{width: 170px;}
		</style>
	</head>
	<body>
		<div class="info">
			<form id="applyListForm" method="post"
				  action="${pageContext.request.contextPath}/ebuyProductSettle/revenueApplyListIndex.html">
				<div class="overflow">
					<h2>结算总览</h2>
					<table>
						<tr>
							<td>
								<select class="select_normal Validform_error" name="supplyMerchantId" id="supplyMerchantId" onchange="changeMerchant()">
									<c:forEach items="${merchants}" var="merchant">
										<option value="${merchant.id}" <c:if test="${(merchant.id eq merchantId) or (merchant.id eq param.merchantId)}">selected</c:if> >${merchant.name}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<div class="f12">可结算金额：<span class="bold f16 red"><fmt:formatNumber
										value="${totalAmount}" type="currency" pattern="0.00"
										maxFractionDigits="2"/></span> 元
									<input id="importBill" class="info-btn small-btn w100 mtop3 mleft10" type="button"
										   value=" 申请结算">
								</div>
							</td>
						</tr>
					</table>
				</div>
				<h2 class="mtop20">结算明细</h2>
				<div class="bs-example bgebeb">

					<input type="hidden" id="pageType" name="pageType" value="<%=PageType.APPLY_LIST%>"/>
					<table class="info-list" border="0">
						<tr>
							<td>
								<div class="list-name">订单号：</div>
							</td>
							<td><input type="text" class="input_text w240 pp" id="orderNo" name="orderNo"
									   value="${param.orderNo}"></td>
							<td>
								<div class="list-name">选择日期：</div>
							</td>
							<td>
								<input type="text" id="payTimeStart" name="payTimeStart" title="请选择起始时间"
									   value="${param.payTimeStart}" placeholder="请选择起始时间"
									   onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});"
									   class="input_text pp w180 icon_dt pp">
								至
								<input type="text" id="payTimeEnd" name="payTimeEnd" title="请选择结束时间"
									   value="${param.payTimeEnd}" placeholder="请选择结束时间"
									   onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});"
									   class="input_text pp w180 icon_dt pp">
							<td align="left">
								<input class="input-btn w100" type="button" value="搜索" onclick="searchApplyList();">
								<input class="input-btn w100" type="button" value="导出账单" onclick="exportApplyList();">
							</td>
						</tr>
						<tr>
						</tr>
					</table>

				</div>
			</form>
		    <display:table name="applyList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="applyTotal" >
				<display:column title="订单号" property="orderNo" style="text-align:left;" headerClass="t_center"/>
				<display:column title="支付时间" property="payTime" style="text-align:left;" headerClass="t_center"/>
				<display:column title="商品总额（元）" style="text-align:right;" headerClass="t_center">
					<fmt:formatNumber value="${row.productAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
				<display:column title="运费（元）" style="text-align:right;" headerClass="t_center">
					<fmt:formatNumber value="${row.deliveryFee}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
				<display:column title="退款金额（元）" style="text-align:right;" headerClass="t_center">
					<fmt:formatNumber value="${row.refundFee}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
				<display:column title="订单总金额（元）" style="text-align:right;" headerClass="t_center">
					<fmt:formatNumber value="${row.orderTotalAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
				<display:column title="订单结算金额（元）" style="text-align:right;" headerClass="t_center">
					<fmt:formatNumber value="${row.revenueAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
				<c:if test="${currMerchant.revenueType == 2}">
				<display:column title="订单结算总额（元）" style="text-align:right;" headerClass="t_center">
					<fmt:formatNumber value="${row.revenueAmount + row.deliveryFee}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
				</c:if>
				<display:column title="操作" style="text-align:left;" headerClass="t_center">
					<a class="blue" name="view" href="${pageContext.request.contextPath}/order/viewOrderDetail.html?delieveOrderId=${row.delieveOrderId}&orderNo=${row.orderNo}">详情</a>
				</display:column>
			</display:table>
		</div>
		<div class="layer-bill dsn" style="width:450px; height: 300px;">
		    <div class="bold f16 borderb">结算信息</div>
		    <form class="inputform" action="${pageContext.request.contextPath}/ebuyProductSettle/applyRevenue.html">
			    <table class="mtop10" border="0">
					<input type="hidden" id="applyMerchantId" name="supplyMerchantId">
			        <tr>
			        	<td width="72"><div class="list-name">结算金额：</div></td>
			        	<td><span class="bold f16 red"><fmt:formatNumber value="${totalAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/></span> 元</td>
			        </tr>
			        <tr>
			        	<td><div class="list-name">收款账户：</div></td>
			        	<td>${applyDialogInfo.accountName}</td>
			        </tr>
			        <tr>
			        	<td><div class="list-name">收款账号：</div></td>
			        	<td>${applyDialogInfo.accountBank}</td>
			        </tr>
			        <tr>
			        	<td><div class="list-name">开户银行：</div></td>
			        	<td>${applyDialogInfo.bankName}${applyDialogInfo.bankBranch}</td>
			        </tr>
			        <tr>
			        	<td><div class="list-name">联系方式：</div></td>
			        	<td><input type="text" class="input_text" name="mobile" maxlength="11" ignore="ignore" datatype="m" placeholder="请填写手机号码"></td>
			        </tr>
			        <tr>
			        	<td><div class="list-name">备注：</div></td>
			        	<td><input type="text" class="input_text" name="note" maxlength="200" ignore="ignore" datatype="*"></td>
			        </tr>
			    </table>
			    <div class="mtop20 t_center">
				    <input id="submitAccountBtn" class="info-btn small-btn w100" type="submit" value="提交">
			        <input class="input-btn w100 classify-cancel-btn" type="button" value="取消" />
		        </div>
		    </form>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/layer.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	(function($){
	    //表单验证
		$(".inputform").Validform({
			btnSubmit:"#submitAccountBtn", 
			tiptype:3,
			beforeSubmit: function (curform) {
				$("#applyMerchantId").val($("#supplyMerchantId  option:selected").val());
			},
			callback:function(data){
				
			}
		});
	    
		$('.classify-cancel-btn').click(function(){
			layer.closeAll();
		});
	})(jQuery);
	
	// 搜索
	function searchApplyList(){
		$("#applyListForm").attr("action", "${pageContext.request.contextPath}/ebuyProductSettle/revenueApplyListIndex.html");
		$("#applyListForm").submit();
	}
	function changeMerchant() {
		var merchantId = jQuery("#supplyMerchantId  option:selected").val();
		window.location.href = '${pageContext.request.contextPath}/ebuyProductSettle/revenueApplyListIndex.html?supplyMerchantId=' + merchantId;
	}
	// 导出账单
	function exportApplyList(){
		var pageType= $("#pageType").val();
		var orderNo = $.trim($("#orderNo").val());
		var payTimeStart = $.trim($("#payTimeStart").val());
		var payTimeEnd = $.trim($("#payTimeEnd").val());
		var merchantId = jQuery("#supplyMerchantId  option:selected").val();
		location.href = "${pageContext.request.contextPath}/ebuyProductSettle/exportRevenueApplyList.html?pageType="
				+pageType+"&orderNo="+orderNo+"&payTimeStart="+payTimeStart+"&payTimeEnd="+payTimeEnd+"&supplyMerchantId=" + merchantId;
	}
	</script>
</html>