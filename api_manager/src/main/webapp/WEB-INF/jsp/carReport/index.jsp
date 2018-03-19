<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%
	response.setHeader("Pragma","No-cache");    
	response.setHeader("Cache-Control","no-cache");    
	response.setDateHeader("Expires", -10);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>停车费汇总报表</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.simple-dtpicker.css"/>
		<style type="text/css">
			.reportTable thead tr td{text-align: center;}
			.L{text-align: left;} 
			.R{text-align: right;} 
			.C{text-align: center;}
			.totalPayChannel{font-size: 14px;font-weight: bold;}
			.totalAllPayChannel{font-size: 14px;font-weight: bold;color:red;}
		</style>
	</head>
	<body>
		<div class="info">
			<h2>停车费汇总报表</h2>
			<form name="searchForm" id="searchForm" method="post" action="${pageContext.request.contextPath}/carReport/index.html">
				<div class="bs-example bgebeb">
					<table class="info-list" border="0">
						<tr>
							<td><div class="list-name">查询月份：</div></td>
							<td><input type="text" class="input_text w120 pp" value="${param.month}" title="如：2014-07" name="month" type="text" onclick="setmonth(this)" readonly="readonly"/></td>
							<td width=""><div class="list-name">管理处：</div></td>
							<td>
								<select name="pmId" class="select_normal select2_class">
									<option value="">选择管理处</option>
									<c:forEach var="item" items="${pmList}">
										<option value="${item.id}" <c:if test="${item.id == param.pmId}">selected="selected"</c:if>>${item.name}</option>
									</c:forEach>
								</select>
							</td>
							<td width=""><div class="list-name">停车场：</div></td>
							<td>
								<select name="plotId" class="select_normal select2_class">
									<option value="">选择小区</option>
									<c:forEach var="item" items="${gbList}">
										<option value="${item.id}" <c:if test="${item.id == param.plotId}">selected="selected"</c:if>>${item.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td><div class="list-name">是否显示占比：</div></td>
							<td>
								<label style="margin-right:1em;"><input type="radio" value="true" name="percentVisible" onclick="setPercentVisible(true)" <c:if test="${percentVisible}">checked</c:if>/>是</label>
								<label><input type="radio" value="false" name="percentVisible" onclick="setPercentVisible(false)" <c:if test="${not percentVisible}">checked</c:if>/>否</label>
							</td>
							<td><input class="info-btn small-btn w100" type="submit" value=" 查 询"/></td>
						</tr>
					</table>
				</div>
			</form>
			<table class="mars info-list-02 mtop20 reportTable">
				<thead>
					<tr class="title" style="text-align: center;">
						<td rowspan="2">管理处</td>
						<td rowspan="2">停车场</td>
						<td rowspan="2">收费项目</td>
						<td colspan="2">支付渠道</td>
						<td rowspan="2">合计</td>
					</tr>
					<tr class="title" style="text-align: center;">
						<td rowspan="2">解放区缴费</td>
						<td rowspan="2">现金缴费</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${carReportDTOList}" var="carReportDTO">
						<tr>
							<td class="L" rowspan="2">${carReportDTO.pmName}</td>
							<td class="L" rowspan="2">${carReportDTO.plotName}</td>
							<td class="L">月卡费用</td>
							<td class="R">
								<fmt:formatNumber value="${carReportDTO.monthPayChannel.jfqFee.fee}" type="currency" pattern="#,##0.00"/>
								<c:if test="${percentVisible and carReportDTO.monthPayChannel.jfqFee.percentVisible}">
									<span class="percentSpan">（<fmt:formatNumber value="${carReportDTO.monthPayChannel.jfqFee.percent}" type="currency" pattern="#,##0.00"/>%）</span>
								</c:if>
							</td>
							<td class="R">
							    <fmt:formatNumber value="${carReportDTO.monthPayChannel.cash.fee}" type="currency" pattern="#,##0.00"/>
							    <c:if test="${percentVisible and carReportDTO.monthPayChannel.cash.percentVisible}">
									<span class="percentSpan">（<fmt:formatNumber value="${carReportDTO.monthPayChannel.cash.percent}" type="currency" pattern="#,##0.00"/>%）</span>
								</c:if>
							</td>
							<td class="R">
							    <fmt:formatNumber value="${carReportDTO.monthPayChannel.totalFee}" type="currency" pattern="#,##0.00"/>
							</td>
						</tr>
						<tr>
							<td class="L">临时停车费用</td>
							<td class="R">
							    <fmt:formatNumber value="${carReportDTO.tempPayChannel.jfqFee.fee}" type="currency" pattern="#,##0.00"/>
							    <c:if test="${percentVisible and carReportDTO.tempPayChannel.jfqFee.percentVisible}">
									<span class="percentSpan">（<fmt:formatNumber value="${carReportDTO.tempPayChannel.jfqFee.percent}" type="currency" pattern="#,##0.00"/>%）</span>
								</c:if>
							</td>
							<td class="R">
							    <fmt:formatNumber value="${carReportDTO.tempPayChannel.cash.fee}" type="currency" pattern="#,##0.00"/>
							    <c:if test="${percentVisible and carReportDTO.tempPayChannel.cash.percentVisible}">
									<span class="percentSpan">（<fmt:formatNumber value="${carReportDTO.tempPayChannel.cash.percent}" type="currency" pattern="#,##0.00"/>%）</span>
								</c:if>
							</td>
							<td class="R">
							    <fmt:formatNumber value="${carReportDTO.tempPayChannel.totalFee}" type="currency" pattern="#,##0.00"/>
							</td>
						</tr>
						<tr class="totalPayChannel">
							<td class="C" colspan="3">小计</td>
							<td class="R">
							    <fmt:formatNumber value="${carReportDTO.totalPayChannel.jfqFee.fee}" type="currency" pattern="#,##0.00"/>
							    <c:if test="${percentVisible and carReportDTO.totalPayChannel.jfqFee.percentVisible}">
									<span class="percentSpan">（<fmt:formatNumber value="${carReportDTO.totalPayChannel.jfqFee.percent}" type="currency" pattern="#,##0.00"/>%）</span>
								</c:if>
							</td>
							<td class="R">
							    <fmt:formatNumber value="${carReportDTO.totalPayChannel.cash.fee}" type="currency" pattern="#,##0.00"/>
							    <c:if test="${percentVisible and carReportDTO.totalPayChannel.cash.percentVisible}">
									<span class="percentSpan">（<fmt:formatNumber value="${carReportDTO.totalPayChannel.cash.percent}" type="currency" pattern="#,##0.00"/>%）</span>
								</c:if>
							</td>
							<td class="R">
							    <fmt:formatNumber value="${carReportDTO.totalPayChannel.totalFee}" type="currency" pattern="#,##0.00"/>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${not empty totalAllPayChannel}">
						<tr class="totalAllPayChannel">
							<td class="C" colspan="3">合计</td>
							<td class="R">
							    <fmt:formatNumber value="${totalAllPayChannel.jfqFee.fee}" type="currency" pattern="#,##0.00"/>
							    <c:if test="${percentVisible and totalAllPayChannel.jfqFee.percentVisible}">
									<span class="percentSpan">（<fmt:formatNumber value="${totalAllPayChannel.jfqFee.percent}" type="currency" pattern="#,##0.00"/>%）</span>
								</c:if>
							</td>
							<td class="R">
							    <fmt:formatNumber value="${totalAllPayChannel.cash.fee}" type="currency" pattern="#,##0.00"/>
							    <c:if test="${percentVisible and totalAllPayChannel.cash.percentVisible}">
									<span class="percentSpan">（<fmt:formatNumber value="${totalAllPayChannel.cash.percent}" type="currency" pattern="#,##0.00"/>%）</span>
								</c:if>
							</td>
							<td class="R">
							    <fmt:formatNumber value="${totalAllPayChannel.totalFee}" type="currency" pattern="#,##0.00"/>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simple-dtpicker01.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/DatePicker.js"></script>
<script>
	function setPercentVisible(visible){
		if(visible){
			$(".percentSpan").show();
		} else {
			$(".percentSpan").hide();
		}
	}
	
	$(function(){
		$(".reportTable tr").unbind("click");
	});
</script>
</html>