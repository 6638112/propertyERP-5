<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>打印出盘明细</title>
		<style>
			body{font-family:microsof_yahei;}
			.print_container{text-align: center;padding: 20px;}
			.print_div{margin:0 auto;}
			.title{margin:0 auto;width: 80%;border-bottom: 4px solid black;padding-bottom:5px;}
			.td_left{text-align: left;}
			.td_right{text-align: right;}
			.td_center{text-align: center;}
			table{width: 100%;}
			.title_table{margin-top: 2em;margin-bottom: 5px;}
			.content_table, .content_table td{border: 1px solid black;border-spacing: 0px;}
			.content_table td{padding:5px 3px 5px 3px;}
			.foot_table{padding: 5px 0px 5px 0px;border-spacing: 0px;}
		</style>
	</head>
	<body onload="printDetail()">
		<c:forEach items="${data}" var="row">
			<c:if test="${row.pageStart}">
			<div class="print_container" style="page-break-after: always;">
				<div class="print_div">
					<h1>${gbNames}</h1>
					<h2 class="title">银行出盘明细表</h2>
					<table class="title_table">
						<tr>
							<td class="td_left">划款方案名称：${title}</td>
							<td class="td_right">出盘时间：${now}</td>
						</tr>
					</table>
					<table class="content_table">
						<tr>
							<td class="td_center">资源代码</td>
							<td class="td_center">客户名称</td>
							<td class="td_center">银行账号</td>
							<td class="td_center">出盘金额</td>
							<td class="td_center">划款本金</td>
							<td class="td_center">划款滞纳金</td>
						</tr>
			</c:if>
						<tr>
							<td class="td_left">
								<c:choose>
									<c:when test="${empty row.bcPrintDetail.roomNo}">&nbsp;</c:when>
									<c:otherwise>${row.bcPrintDetail.roomNo}</c:otherwise>
								</c:choose>
							</td>
							<td class="td_left">
								<c:choose>
										<c:when test="${empty row.bcPrintDetail.ppName}">&nbsp;</c:when>
										<c:otherwise>${row.bcPrintDetail.ppName}</c:otherwise>
									</c:choose>
								</td>
							<td class="td_left">
								<c:choose>
									<c:when test="${empty row.bcPrintDetail.bankAccount}">&nbsp;</c:when>
									<c:otherwise>${row.bcPrintDetail.bankAccount}</c:otherwise>
								</c:choose>
							</td>
							<td class="td_right">
								<fmt:formatNumber value="${row.bcPrintDetail.outAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/>
							</td>
							<td class="td_right">
								<fmt:formatNumber value="${row.bcPrintDetail.dealAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/>
							</td>
							<td class="td_right">
								<fmt:formatNumber value="${row.bcPrintDetail.dealLateAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/>
							</td>
						</tr>
			<c:if test="${row.pageEnd}">
					</table>
					<table class="foot_table">
						<tr>
							<td colspan="2" class="td_center">
								<span>制表：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								<span>复核：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</td>
						</tr>
						<tr>
							<td class="td_left">打印日期：${fn:substring(now, 0, 10)}</td>
							<td class="td_right">页码：第${row.pageNum}页/总共${row.totalPageNum}页</td>
						</tr>
					</table>
				</div>
			</div>
			</c:if>
		</c:forEach>
	</body>
</html>