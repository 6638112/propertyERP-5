<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<title>支付补贴明细表</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<style>
			.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
			.w180{width: 170px;}
		</style>
	</head>
	<body>
		<div class="info">
		    <h2 class="mtop20">支付补贴明细表</h2> 
		    <div class="bs-example bgebeb">
		    	<form id="cashSqpayBtForm" method="post" action="${pageContext.request.contextPath}/cashSqpayBt/cashSqpayBtIndex.html">
			        <table class="info-list" border="0">
			          <tr>
				            <td><div class="list-name">物业对象名称：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="goalName" value="${param.goalName}"></td>
				            <td><div class="list-name">业务类型：</div></td>
				            <td>
				            	<select name="orderType" class="select_normal">
				                    <option value="">全部</option>
				                    <option value="1" <c:if test="${(not empty param.orderType) and (param.orderType eq 1)}">selected</c:if>>购物</option>
				                    <option value="2" <c:if test="${(not empty param.orderType) and (param.orderType eq 2)}">selected</c:if>>物业费</option>
				                    <option value="3" <c:if test="${(not empty param.orderType) and (param.orderType eq 3)}">selected</c:if>>维修</option>
				                    <option value="4" <c:if test="${(not empty param.orderType) and (param.orderType eq 4)}">selected</c:if>>停车费</option>
				                    <option value="5" <c:if test="${(not empty param.orderType) and (param.orderType eq 5)}">selected</c:if>>其他代收费</option>
				                    <option value="6" <c:if test="${(not empty param.orderType) and (param.orderType eq 6)}">selected</c:if>>物业代扣卡</option>
			                    </select>
				            </td>
				            <td><div class="list-name">支付时间：</div></td>
				            <td>
				            	<input type="text" name="payTimeStart" title="请选择起始时间" value="${param.payTimeStart}" placeholder="请选择起始时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            	至 
				            	<input type="text" name="payTimeEnd" title="请选择结束时间" value="${param.payTimeEnd}" placeholder="请选择结束时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            </td>
				            <td><div class="list-name">流水号：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="orderNo" value="${param.orderNo}"></td>
			          </tr>
			          <tr>
			          		<td><div class="list-name">补贴金额：</div></td>
			          		<td>
			          			<span class="bold f16 red"><fmt:formatNumber value="${totalAmountBt/100}" type="currency" pattern="0.00" maxFractionDigits="2"/></span> 元
				            </td>
			          		<td align="center" colspan="6">
				            	<input class="input-btn w100" type="button" value="查询" onclick="searchList();">
				            	<input class="input-btn w100" type="button" value="清空" onclick="clearSearch();">
				            	<input class="input-btn w100" type="button" value="导出" onclick="exportList();">
				            </td>
			          </tr>
			        </table>
			    </form>
		    </div>  
		    <display:table name="cashSqpayBtDtos" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="total" >
				<display:column title="业务对象名称" property="goalName" style="text-align:left;" headerClass="t_center"/>
				<display:column title="业务类型" style="text-align:left;" headerClass="t_center">
					<c:choose>
						<c:when test="${row.orderType eq 1}">购物</c:when>
						<c:when test="${row.orderType eq 2}">物业费</c:when>
						<c:when test="${row.orderType eq 3}">维修</c:when>
						<c:when test="${row.orderType eq 4}">停车费</c:when>
						<c:when test="${row.orderType eq 5}">其他代收费</c:when>
						<c:when test="${row.orderType eq 6}">物业代扣卡</c:when>
					</c:choose>
				</display:column>
				<display:column title="双乾实付金额" style="text-align:right;" headerClass="t_center">
					<fmt:formatNumber value="${row.amount/100}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
				<display:column title="补贴金额" style="text-align:right;" headerClass="t_center">
					<fmt:formatNumber value="${row.amountBt/100}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
				<display:column title="支付时间" property="payTime" style="text-align:left;" headerClass="t_center"/>
				<display:column title="流水号" property="orderNo" style="text-align:left;" headerClass="t_center"/>
			</display:table>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		// 搜索
		function searchList(){
			$("#cashSqpayBtForm").attr("action", "${pageContext.request.contextPath}/cashSqpayBt/cashSqpayBtIndex.html");
			$("#cashSqpayBtForm").submit();
		}
		
		// 导出账单
		function exportList(){
			location.href = "${pageContext.request.contextPath}/cashSqpayBt/exportCashSqpayBts.html?"+$("#cashSqpayBtForm").serialize();
		}
		
		// 清空查询条件
		function clearSearch(){
			$(":text,select").val("");
		}
	</script>
</html>