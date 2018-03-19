<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.cnfantasia.server.api.plotproperty.constant.FinanceConfig"%>
<%@ include file="/head.jsp"%>
<%
	String financeCar= FinanceConfig.FinanceType.FINANCE_CAR;
    boolean isFinanceCar = financeCar.equals(request.getAttribute("financeType"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业宝购买明细表</title>
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
    <h2>理财产品购买明细表</h2>
    <div class="bs-example bgebeb">
    <form id="searchForm" name="form_main" action="<%=basePath%>/finance/buyList.html" method="post">
        <table class="info-list" border="0">
          <tr>
            <td width="10%"><div align="right">城市：</div></td>
            <td width="15%"><input class="input_text w120" type="text" name="city" value="${param.city}"></input></td>
            <td width="10%"><div align="right">小区：</div></td>
            <td width="15%"><input class="input_text w120" type="text" name="residential" value="${param.residential}" /></td>
            <td width="10%"><div align="right">楼栋：</div></td>
            <td width="15%"><input class="input_text w120" type="text" name="building" value="${param.building}" /></td>
            <td width="10%"><div align="right">单元门牌号：</div></td>
            <td width="15%"><input class="input_text w120" type="text" name="roomNum" value="${param.roomNum}" /></td>
          </tr>
          <tr>
          	<td><div class="list-name">抵扣月份：</div></td>
            <td>
             <input class="input_text w120" value="${param.deductionTime}" id="begintime" name="deductionTime" type="text" onclick="setmonth(this)" />
            </td>
            <td><div class="list-name">类型：</div></td>
            <td>
            	<select id="financeType" name="financeType" class="select_normal w131" onchange="document.getElementById('searchForm').submit();">
            		<option value="<%=FinanceConfig.FinanceType.FINANCE_PLOTPROPERTY%>">物业宝</option>
            		<option value="<%=FinanceConfig.FinanceType.FINANCE_CAR%>" <%if(isFinanceCar){%>selected<%}%>>停车宝</option>
            	</select>
            </td>
          </tr>
          <tr>
          	<%if(!isFinanceCar){%>
          	<td colspan="2">返还物业费金额总计：<fmt:formatNumber value="${financeSum.retWyMoney }" pattern="#,#00.00"/></td>
			<%}%>
			<td align="right">返还佣金金额总计：</td><td align="left"><fmt:formatNumber value="${financeSum.retProfitMoney }" pattern="#,#00.00"/></td>
			<%if(!isFinanceCar){%>
			<td align="right">返还金额总计：</td><td align="left"><fmt:formatNumber value="${financeSum.retWyMoney + financeSum.retProfitMoney }" pattern="#,#00.00"/></td>
			<%}%>
          	<td></td>
            <td align="left"><input class="info-btn small-btn w100" type="submit" value="查 询" />&nbsp;&nbsp;&nbsp;&nbsp;<!--  <input class="weak-btn small-btn w150" type="button" value="导出查询结果报表"> --></td>
          </tr>
        </table>
     </form>
     </div>
	    <display:table name="buyList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
			<%--
			<display:column title="序号" sortable="true" headerClass="sortable">
			<c:out value="${row_rowNum}"/></display:column> --%>
			<display:column title="城市" property="financeReqEntity.city" sortable="true" />
			<display:column title="小区" property="financeReqEntity.residential" sortable="true"/>
			<display:column title="楼栋" property="financeReqEntity.building" sortable="true"/>
			<display:column title="单元门牌号" property="financeReqEntity.roomNum" sortable="true"/>
			<display:column title="订单号" property="orderNo" sortable="true"></display:column>
			<display:column title="姓名" property="buyPerson" sortable="true"></display:column>
			<display:column title="手机号" property="buyMobile" sortable="true"></display:column>
			<display:column title="身份证号" property="buyIdNo" sortable="true"></display:column>
			<display:column title="投资金额" property="buyMoney" sortable="true" format="{0, number, 0.00}"/>
			<%if(!isFinanceCar){%>
			<display:column title="每月返还物业费金额" property="financeReqEntity.propertyFees" sortable="true" format="{0, number, 0.00}" />
			<%}%>
			<display:column title="每月返还佣金金额" sortable="true">
			<%if(isFinanceCar){%>
				<fmt:formatNumber value="${row.returnMoney / row.deductionCount}" pattern="#,##0.00"/>
			<%}else{%>
				<fmt:formatNumber value="${row.returnMoney / 12}" pattern="#,##0.00"/>
			<%}%>
			</display:column>
			<display:column title="抵扣周期（起始）" property="deductionBeginTime" sortable="true" format="{0, date, yyyy-MM}" />
			<display:column title="抵扣周期（截止）" property="deductionEndTime" sortable="true" format="{0, date, yyyy-MM}" />
		</display:table>
          
    
</div>
</body>

<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.common.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/DatePicker.js"></script>
</html>
