<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>代扣卡转粮票一览表</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<style>
			.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
			.w180{width: 170px;}
		</style>
	</head>
	<body>
		<div class="info">
		    <h2 class="mtop20">代扣卡转粮票一览表</h2>
		    <div class="bs-example bgebeb">
		    	<form id="cashFlowSummaryForm" method="post" action="${pageContext.request.contextPath}/payRedEnvelope/pcToEnvelopeHistory.html">
			        <table class="info-list" border="0">
			          <tr>
				            <td><div class="list-name">解放号：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="userId" value="${param.userId}"></td>
				            <td><div class="list-name">转入时间：</div></td>
				            <td colspan="3">
				            	<input type="text" name="inTimeStart" title="请选择起始时间" value="${param.inTimeStart}" placeholder="请选择起始时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            	至 
				            	<input type="text" name="inIimeEnd" title="请选择结束时间" value="${param.inIimeEnd}" placeholder="请选择结束时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            </td>
				            <td><div class="list-name">累计转入金额（元）：</div></td>
				            <td>
				            	<span class="bold f16 red">
				            		<fmt:formatNumber value="${totalMoney/100}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				            	</span>
				            </td>
				            <td>
				           	    <input class="input-btn w100" type="submit" value="搜索">
				            </td>
			          </tr>
			        </table>
			    </form>
		    </div>  
		    <display:table name="redEnvelopeHistory" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="total" >
				<display:column title="解放号" property="userId" style="text-align:center;" headerClass="t_center"/>
				<display:column title="转入金额（元）" style="text-align:center;" headerClass="t_center">
					<fmt:formatNumber value="${row.amountTotal/100}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
				<display:column title="转入时间" property="sys0AddTime" style="text-align:center;" headerClass="t_center"/>
			</display:table>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
</html>