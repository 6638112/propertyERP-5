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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>结算记录</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css?v20160817">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.simple-dtpicker.css">
		<style>
			.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
			.w180{width: 170px;}
		</style>
	</head>
	<body>
		<div class="info">
		    <h2 class="mtop20">
		    	<c:choose>
		    		<c:when test="${isAdmin}">结算管理</c:when>
		    		<c:otherwise>结算记录</c:otherwise>
		    	</c:choose>
		    </h2> 
		    <div class="bs-example bgebeb">
		    	<form method="post" action="${pageContext.request.contextPath}/ebuyProductSettle/revenueListIndex.html">
			        <table class="info-list" border="0">
			          <tr>
			          
			            <c:if test="${isAdmin}">
				            <td><div class="list-name">供应商：</div></td>
				            <td>
					            <select id="supplyMerchantId" name="supplyMerchantId" class="select_normal">
			                      <option value="">全部</option>
			                      <c:forEach var="supplyMerchant" items="${supplyMerchants}" >
									  <option value="${supplyMerchant.id}" <c:if test="${supplyMerchant.id eq param.supplyMerchantId}">selected</c:if> >${supplyMerchant.name}</option>
								  </c:forEach>
			                    </select>
				            </td>
			            </c:if>
			            <td><div class="list-name">提交时间：</div></td>
			            <td>
			            	<input type="text" id="applyTimeStart" name="applyTimeStart" title="请选择起始时间" value="${param.applyTimeStart}" placeholder="请选择起始时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
			            	至 
			            	<input type="text" id="applyTimeEnd" name="applyTimeEnd" title="请选择结束时间" value="${param.applyTimeEnd}" placeholder="请选择结束时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
			            </td>
			            <td><div class="list-name">结算状态：</div></td>
			            <td>
			            	<select class="select_normal" name="settleStatus">
			                    <option value="">全部</option>
			                    <option value="1" <c:if test="${param.settleStatus eq 1}">selected</c:if>>待审核</option>
			                    <option value="2" <c:if test="${param.settleStatus eq 2}">selected</c:if>>审核不通过</option>
			                    <option value="3" <c:if test="${param.settleStatus eq 3}">selected</c:if>>待付款</option>
			                    <option value="4" <c:if test="${param.settleStatus eq 4}">selected</c:if>>已付款</option>
			                </select>
			            </td>
			          </tr>
						<c:if test="${isAdmin}">
							<tr>
								<td><div class="list-name">合作方式：</div></td>
								<td>
									<select class="select_normal" name="revenueType">
										<option value="">全部</option>
										<option value="1" <c:if test="${param.revenueType eq 1}">selected</c:if>>购销模式</option>
										<option value="2" <c:if test="${param.revenueType eq 2}">selected</c:if>>抽佣模式</option>
									</select>
								</td>
							</tr>
						</c:if>
			          <tr>
			            <td colspan="4" class="t_center">
			            	<input class="input-btn w200" type="submit" value="搜索">
			            	<c:if test="${isAdmin}">
			            		<input class="input-btn w100" type="button" value="导出" onclick="exportList();">
			            	</c:if>
			            </td>
			          </tr>
			        </table>
		        </form>
		    </div>  
		    
		    <display:table name="revenueApplys" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="total" >
		    	<c:if test="${isAdmin}"><display:column title="供应商" property="merchant" style="text-align:left;" headerClass="t_center"/></c:if>
				<c:if test="${isAdmin}">
					<display:column title="合作模式" style="text-align:left;" headerClass="t_center">
						<c:if test="${row.revenueType == 1}">购销</c:if>
						<c:if test="${row.revenueType == 2}">抽佣</c:if>
					</display:column>
				</c:if>
				<display:column title="提交时间" property="applyTime" style="text-align:left;" headerClass="t_center"/>
				<display:column title="审核时间" property="auditTime" style="text-align:left;" headerClass="t_center"/>
				<display:column title="支付时间" property="tkSuccTime" style="text-align:left;" headerClass="t_center"/>
				<display:column title="结算金额（元）" style="text-align:right;" headerClass="t_center">
					<fmt:formatNumber value="${row.totalAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
				<display:column title="结算账户" property="accountName" style="text-align:left;" headerClass="t_center"/>
				<display:column title="备注" property="applyNote" maxLength="20" headerClass="t_center"/>
				<display:column title="结算状态" style="text-align:left;" headerClass="t_center">
					<c:choose>
						<c:when test="${row.auditStatus eq 3}">
							<c:choose>
								<c:when test="${row.tkStatus eq 3}">已付款</c:when>
								<c:otherwise>待付款</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${row.auditStatus eq 1}">待审核</c:when>
								<c:otherwise><span style="color:red;">审核不通过</span></c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="操作" style="text-align:center;" headerClass="t_center">
					<a class="blue viewAccountListDetails" name="view" href="${pageContext.request.contextPath}/ebuyProductSettle/revenueListDetail.html?revenueApplyId=${row.id}">详情</a>
					<c:if test="${isAdmin and (row.auditStatus eq 1)}">
						<a class="blue viewAccountListDetails" name="view" href="${pageContext.request.contextPath}/ebuyProductSettle/revenueHandlerDetail.html?revenueApplyId=${row.id}">审核</a>
					</c:if>
				</display:column>
			</display:table>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript">
		function exportList(){
			var supplyMerchantId = $.trim($("#supplyMerchantId").val());
			var applyTimeStart = $.trim($("#applyTimeStart").val());
			var applyTimeEnd = $.trim($("#applyTimeEnd").val());
			var settleStatus = $.trim($("#settleStatus").val());
			location.href = "${pageContext.request.contextPath}/ebuyProductSettle/exportRevenueApplyListForAdmin.html?supplyMerchantId="
					+supplyMerchantId+"&applyTimeStart="+applyTimeStart+"&applyTimeEnd="+applyTimeEnd+"&settleStatus="+settleStatus;
		}
	</script>
</html>