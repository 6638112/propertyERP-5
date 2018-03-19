<%@page import="com.cnfantasia.server.ms.ebuyProductSettle.constants.PageType"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>结算详情</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	</head>
	<body>
		<div class="info">
		    <h2 class="mtop20">
		    	结算详情 
		    	<input class="info-btn small-btn w100 mtop3 mleft10" type="button" value="导出账单详情" onclick="exportDetailList();"/>
		    </h2> 
		    <div class="bs-example bgebeb mtop10">
		        <table class="info-list" border="0">
		          <tr>
		            <td width="50%"><div class="f14 bold borderb">结算信息</div></td>
		            <td><div class="f14 bold borderb">审核结果</div></td>
		          </tr>
		          <tr>
		            <td>
		            	<span class="red bold">结算金额：<fmt:formatNumber value="${revenueApply.totalAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/>元</span><br>
		            	申请时间：${revenueApply.applyTime}<br>
		            	收款账户：${revenueApply.accountName}<br>
		            	收款账号：${revenueApply.bBankNo}<br>
		            	开户银行：${revenueApply.bBankName}${revenueApply.bankBranch}<br>
		            	联系方式：${revenueApply.bPhone}<br>
		            	备注：${revenueApply.applyNote}
		            </td>
		            <td>
		            	结算状态：
							<c:choose>
								<c:when test="${revenueApply.tkStatus eq 1}">未提款</c:when>
								<c:when test="${revenueApply.tkStatus eq 2}">待付款</c:when>
								<c:when test="${revenueApply.tkStatus eq 3}">已付款</c:when>
							</c:choose>
		            	<br>
		            	审核时间：${revenueApply.auditTime}<br>
		            	审核结果：
		            		<c:choose>
								<c:when test="${revenueApply.auditStatus eq 1}">待审核</c:when>
								<c:when test="${revenueApply.auditStatus eq 2}"><span style="color:red;">审核不通过</span></c:when>
								<c:when test="${revenueApply.auditStatus eq 3}">审核通过</c:when>
							</c:choose>
		            	<br>
		            	审核说明：${revenueApply.handlerNote}<br>
		            	支付时间：${revenueApply.tkSuccTime}
		            </td>
		          </tr>
		        </table>
		    </div>  
		    <h3 class="f14 mtop10">结算清单</h3>
		    <display:table name="applyList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="applyTotal" >
				<display:column title="订单号" property="orderNo" style="text-align:left;" headerClass="t_center"/>
				<c:if test="${isAdmin}">
					<display:column title="供应商" property="merchant" style="text-align:left;" headerClass="t_center"/>
				</c:if>
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
				<c:if test="${isAdmin}">
					<display:column title="实际支付" style="text-align:right;" headerClass="t_center">
						<fmt:formatNumber value="${row.realPay}" type="currency" pattern="0.00" maxFractionDigits="2"/>
					</display:column>
				</c:if>
				<display:column title="操作" style="text-align:left;" headerClass="t_center">
					<a class="blue" name="view" href="${pageContext.request.contextPath}/order/viewOrderDetail.html?delieveOrderId=${row.delieveOrderId}&orderNo=${row.orderNo}">详情</a>
				</display:column>
			</display:table>
		    <div class="padb"><input class="info-btn" type="button" onclick="history.back()" value="返 回" /></div>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script>
		function exportDetailList(){
			<c:choose>
				<c:when test="${!isAdmin}">
					location.href = "${pageContext.request.contextPath}/ebuyProductSettle/exportRevenueApplyList.html?pageType=<%=PageType.DETAIL%>&revenueApplyId=${revenueApply.id}&revApplyFinanceId=${revApplyFinanceId}&visibleType=${visibleType}";
				</c:when>
				<c:otherwise>
					location.href = "${pageContext.request.contextPath}/ebuyProductSettle/exportRevenueDetailAdmin.html?revenueApplyId=${revenueApply.id}&revApplyFinanceId=${revApplyFinanceId}&visibleType=${visibleType}";
				</c:otherwise>
			</c:choose>
		}
	</script>
</html>
