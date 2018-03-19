<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<title>借款订单管理</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<style>
			.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
			.w180{width: 170px;}
		</style>
	</head>
	<body>
		<div class="info">
		    <h2 class="mtop20">借款订单管理</h2> 
		    <div class="bs-example bgebeb">
		    	<form id="cashFlowSummaryForm" method="post" action="${pageContext.request.contextPath}/loan/orderManager.html">
			        <table class="info-list" border="0">
			          <tr>
				            <td><div class="list-name">城市：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="city" value="${param.city}"></td>
				            <td><div class="list-name">小区：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="gbName" value="${param.gbName}"></td>
				            <td><div class="list-name">楼栋：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="buildingName" value="${param.buildingName}"></td>
				            <td><div class="list-name">单元门牌号：</div></td>
				            <td colspan="3"><input type="text" class="input_text w240 pp" name="roomNum" value="${param.roomNum}"></td>
			          </tr>
			          <tr>
				            <td><div class="list-name">姓名：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="name" value="${param.name}"></td>
				            <td><div class="list-name">手机号：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="mobile" value="${param.mobile}"></td>
				            <td><div class="list-name">身份证：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="idCard" value="${param.idCard}"></td>
				            <td><div class="list-name">借款时间：</div></td>
				            <td colspan="3">
				            	<input type="text" name="loanDateStart" title="请选择起始时间" value="${param.loanDateStart}" placeholder="请选择起始时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd'});" class="input_text pp w180 icon_dt pp">
				            	至 
				            	<input type="text" name="loanDateEnd" title="请选择结束时间" value="${param.loanDateEnd}" placeholder="请选择结束时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd'});" class="input_text pp w180 icon_dt pp">
				            </td>
			          </tr>
			          <tr>
			          		<td><div class="list-name">借贷平台：</div></td>
				            <td>
				            	<select name=platform class="select_normal">
				                    <option value="">全部</option>
				                    <c:forEach items="${loanThirdList}" var="row">
				                    	<option value="${row.id}" <c:if test="${(not empty param.platform) and (param.platform eq row.id)}">selected</c:if>>${row.name}</option>
				                    </c:forEach>
			                    </select>
				            </td>
				            <td><div class="list-name">产品类型：</div></td>
				            <td>
				            	<select name="productType" class="select_normal">
				                    <option value="">全部</option>
				                    <c:forEach items="${loanProductList}" var="row">
				                    	<option value="${row.id}" <c:if test="${(not empty param.productType) and (param.productType eq row.id)}">selected</c:if>>${row.name}</option>
				                    </c:forEach>
			                    </select>
				            </td>
			          		<td colspan="6">
				            	<input class="input-btn w100" type="submit" value="查询">
				            </td>
			          </tr>
			        </table>
			    </form>
		    </div>  
		    <display:table name="loanBuyLogEntities" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="total" >
	    		<display:column title="订单号" property="orderNo" style="text-align:center;" headerClass="t_center"/>
				<display:column title="城市" property="city" style="text-align:center;" headerClass="t_center"/>
				<display:column title="小区" property="gbName" style="text-align:center;" headerClass="t_center"/>
				<display:column title="楼栋" property="buildingName" style="text-align:center;" headerClass="t_center"/>
				<display:column title="单元门牌号" property="roomNum" style="text-align:center;" headerClass="t_center"/>
				<display:column title="姓名" property="name" style="text-align:center;" headerClass="t_center"/>
				<display:column title="手机号" property="mobile" style="text-align:center;" headerClass="t_center"/>
				<display:column title="身份证" property="idCard" style="text-align:center;" headerClass="t_center"/>
				<display:column title="借贷平台" property="platform" style="text-align:center;" headerClass="t_center"/>
				<display:column title="产品类型" property="productType" style="text-align:center;" headerClass="t_center"/>
				<display:column title="借款时间" property="loanDate" style="text-align:center;" headerClass="t_center"/>
				<display:column title="借款金额" style="text-align:center;" headerClass="t_center">
					<fmt:formatNumber value="${row.loanAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
			</display:table>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
</html>