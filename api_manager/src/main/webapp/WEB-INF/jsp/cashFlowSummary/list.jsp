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
		<title>现金流查询</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<style>
			.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
			.w180{width: 170px;}
		</style>
	</head>
	<body>
		<div class="info">
		    <h2 class="mtop20">现金流查询</h2> 
		    <div class="bs-example bgebeb">
		    	<form id="cashFlowSummaryForm" method="post" action="${pageContext.request.contextPath}/cashFlowSummary/cashFlowSummaryIndex.html">
			        <table class="info-list" border="0">
			          <tr>
				            <td><div class="list-name">所在省：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="provinceName" value="${param.provinceName}"></td>
				            <td><div class="list-name">所在市：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="cityName" value="${param.cityName}"></td>
				            <td><div class="list-name">代理商：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="cpCompanyName" value="${param.cpCompanyName}"></td>
				            <td><div class="list-name">物业公司：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="pcName" value="${param.pcName}"></td>
				            <td><div class="list-name">小区名称：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="gbName" value="${param.gbName}"></td>
			          </tr>
			          <tr>
				            <td><div class="list-name">楼栋号：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="buildingName" value="${param.buildingName}"></td>
				            <td><div class="list-name">单元：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="unit" value="${param.unit}"></td>
				            <td><div class="list-name">房间号：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="roomNum" value="${param.roomNum}"></td>
				            <td><div class="list-name">认证状态：</div></td>
				            <td>
				            	<select name="verifyState" class="select_normal">
				                    <option value="">全部</option>
				                    <option value="1" <c:if test="${(not empty param.verifyState) and (param.verifyState eq 1)}">selected</c:if>>已认证</option>
				                    <option value="0" <c:if test="${(not empty param.verifyState) and (param.verifyState eq 0)}">selected</c:if>>未认证</option>
			                    </select>
				            </td>
				            <td><div class="list-name">业务类型：</div></td>
				            <td>
				            	<select name="billType" class="select_normal">
				                    <option value="">全部</option>
				                    <option value="1" <c:if test="${(not empty param.billType) and (param.billType eq 1)}">selected</c:if>>物业费</option>
				                    <option value="2" <c:if test="${(not empty param.billType) and (param.billType eq 2)}">selected</c:if>>停车费</option>
				                    <option value="3" <c:if test="${(not empty param.billType) and (param.billType eq 3)}">selected</c:if>>其他代收费用</option>
				                    <option value="4" <c:if test="${(not empty param.billType) and (param.billType eq 4)}">selected</c:if>>楼下店</option>
				                    <option value="5" <c:if test="${(not empty param.billType) and (param.billType eq 5)}">selected</c:if>>自营超市</option>
				                    <option value="6" <c:if test="${(not empty param.billType) and (param.billType eq 6)}">selected</c:if>>上门维修</option>
				                    <option value="7" <c:if test="${(not empty param.billType) and (param.billType eq 7)}">selected</c:if>>物业宝</option>
				                    <option value="8" <c:if test="${(not empty param.billType) and (param.billType eq 8)}">selected</c:if>>停车宝</option>
			                    </select>
				            </td>
			          </tr>
			          <tr>
				            <td><div class="list-name">时间：</div></td>
				            <td colspan="3">
				            	<input type="text" name="billStartTime" title="请选择起始时间" value="${param.billStartTime}" placeholder="请选择起始时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            	至 
				            	<input type="text" name="billEndTime" title="请选择结束时间" value="${param.billEndTime}" placeholder="请选择结束时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            </td>
				            <td><div class="list-name">汇总方式：</div></td>
				            <td colspan="5">
				            	<select name="summaryType" class="select_normal">
				                    <option value="">无</option>
				                    <option value="1" <c:if test="${(not empty param.summaryType) and (param.summaryType eq 1)}">selected</c:if>>按小区</option>
				                    <option value="2" <c:if test="${(not empty param.summaryType) and (param.summaryType eq 2)}">selected</c:if>>按门牌</option>
			                    </select>
				            </td>
			          </tr>
			          <tr>
			          		<td><div class="list-name">总金额：</div></td>
			          		<td>
			          			<span class="bold f16 red"><fmt:formatNumber value="${totalAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/></span> 元
				            </td>
			          		<td align="center" colspan="8">
				            	<input class="input-btn w100" type="button" value="查询" onclick="searchList();">
				            	<input class="input-btn w100" type="button" value="导出" onclick="exportList();">
				            </td>
			          </tr>
			        </table>
			    </form>
		    </div>  
		    <display:table name="cashFlowSummarys" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="total" >
		    	<c:choose>
		    		<%-- 按小区 --%>
		    		<c:when test="${(not empty param.summaryType) and (param.summaryType eq 1)}">
						<display:column title="物业公司" property="pcName" style="text-align:left;" headerClass="t_center"/>
						<display:column title="代理商" property="cpCompanyName" style="text-align:left;" headerClass="t_center"/>
						<display:column title="小区名称" property="gbName" style="text-align:left;" headerClass="t_center"/>
						<display:column title="业务类型" style="text-align:left;" headerClass="t_center">
							<c:choose>
								<c:when test="${row.billType eq 1}">物业费</c:when>
								<c:when test="${row.billType eq 2}">停车费</c:when>
								<c:when test="${row.billType eq 3}">其他代收费用</c:when>
								<c:when test="${row.billType eq 4}">楼下店</c:when>
								<c:when test="${row.billType eq 5}">自营超市</c:when>
								<c:when test="${row.billType eq 6}">上门维修</c:when>
								<c:when test="${row.billType eq 7}">物业宝</c:when>
								<c:when test="${row.billType eq 8}">停车宝</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</display:column>
						<display:column title="订单数" property="orderNum" style="text-align:left;" headerClass="t_center"/>
						<display:column title="金额" style="text-align:right;" headerClass="t_center">
							<fmt:formatNumber value="${row.amount/100}" type="currency" pattern="0.00" maxFractionDigits="2"/>
						</display:column>
		    		</c:when>
		    		<%-- 按门牌 --%>
		    		<c:when test="${(not empty param.summaryType) and (param.summaryType eq 2)}">
						<display:column title="小区名称" property="gbName" style="text-align:left;" headerClass="t_center"/>
						<display:column title="楼栋号" property="buildingName" style="text-align:left;" headerClass="t_center"/>
						<display:column title="单元" property="unit" style="text-align:left;" headerClass="t_center"/>
						<display:column title="房间号" property="roomNum" style="text-align:left;" headerClass="t_center"/>
						<display:column title="业务类型" style="text-align:left;" headerClass="t_center">
							<c:choose>
								<c:when test="${row.billType eq 1}">物业费</c:when>
								<c:when test="${row.billType eq 2}">停车费</c:when>
								<c:when test="${row.billType eq 3}">其他代收费用</c:when>
								<c:when test="${row.billType eq 4}">楼下店</c:when>
								<c:when test="${row.billType eq 5}">自营超市</c:when>
								<c:when test="${row.billType eq 6}">上门维修</c:when>
								<c:when test="${row.billType eq 7}">物业宝</c:when>
								<c:when test="${row.billType eq 8}">停车宝</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</display:column>
						<display:column title="订单数" property="orderNum" style="text-align:left;" headerClass="t_center"/>
						<display:column title="金额" style="text-align:right;" headerClass="t_center">
							<fmt:formatNumber value="${row.amount/100}" type="currency" pattern="0.00" maxFractionDigits="2"/>
						</display:column>
		    		</c:when>
		    		<%-- 默认 --%>
		    		<c:otherwise>
			    		<display:column title="所在省" property="provinceName" style="text-align:left;" headerClass="t_center"/>
						<display:column title="所在市" property="cityName" style="text-align:left;" headerClass="t_center"/>
						<display:column title="物业公司" property="pcName" style="text-align:left;" headerClass="t_center"/>
						<display:column title="代理商" property="cpCompanyName" style="text-align:left;" headerClass="t_center"/>
						<display:column title="小区名称" property="gbName" style="text-align:left;" headerClass="t_center"/>
						<display:column title="楼栋号" property="buildingName" style="text-align:left;" headerClass="t_center"/>
						<display:column title="单元" property="unit" style="text-align:left;" headerClass="t_center"/>
						<display:column title="房间号" property="roomNum" style="text-align:left;" headerClass="t_center"/>
						<display:column title="认证状态" style="text-align:left;" headerClass="t_center">
							<c:choose>
								<c:when test="${(not empty row.verifyState) and (row.verifyState eq 5)}">已认证</c:when>
								<c:otherwise>未认证</c:otherwise>
							</c:choose>
						</display:column>
						<display:column title="业务类型" style="text-align:left;" headerClass="t_center">
							<c:choose>
								<c:when test="${row.billType eq 1}">物业费</c:when>
								<c:when test="${row.billType eq 2}">停车费</c:when>
								<c:when test="${row.billType eq 3}">其他代收费用</c:when>
								<c:when test="${row.billType eq 4}">楼下店</c:when>
								<c:when test="${row.billType eq 5}">自营超市</c:when>
								<c:when test="${row.billType eq 6}">上门维修</c:when>
								<c:when test="${row.billType eq 7}">物业宝</c:when>
								<c:when test="${row.billType eq 8}">停车宝</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</display:column>
						<display:column title="金额" style="text-align:right;" headerClass="t_center">
							<fmt:formatNumber value="${row.amount/100}" type="currency" pattern="0.00" maxFractionDigits="2"/>
						</display:column>
						<display:column title="时间" property="billTime" style="text-align:left;" headerClass="t_center"/>
		    		</c:otherwise>
		    	</c:choose>
			</display:table>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		// 搜索
		function searchList(){
			$("#cashFlowSummaryForm").attr("action", "${pageContext.request.contextPath}/cashFlowSummary/cashFlowSummaryIndex.html");
			$("#cashFlowSummaryForm").submit();
		}
		
		// 导出账单
		function exportList(){
			location.href = "${pageContext.request.contextPath}/cashFlowSummary/exportCashFlowSummary.html?"+$("#cashFlowSummaryForm").serialize();
		}
	</script>
</html>