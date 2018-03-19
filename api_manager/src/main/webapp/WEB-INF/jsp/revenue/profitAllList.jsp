<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业抵扣收益表</title>
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
    <h2>物业抵扣收益回款表</h2>
    <div class="bs-example bgebeb">
    <form id="searchForm" name="form_main" action="<%=basePath%>/finance/profitAllList.html" method="post">
        <table class="info-list" border="0">
          <tr>
          	<td width="5%"><div align="right">代理商：</div></td>
            <td width="15%"><input class="input_text w120 pp" type="text" name="agentName" value="${param.agentName}"></input></td>
            <td width="10%"><div align="right">物业公司：</div></td>
            <td width="15%"><input class="input_text w120 pp" type="text" name="wyName" value="${param.wyName}" /></td>
            <td width="10%"><div align="right">城市：</div></td>
            <td width="15%"><input class="input_text w120 pp" type="text" name="city" value="${param.city}"></input></td>
            <td width="10%"><div align="right">小区：</div></td>
            <td width="20%"><input class="input_text w120 pp" type="text" name="residential" value="${param.residential}" /></td>
          </tr>
          <tr>
          	<td><div align="right">楼栋：</div></td>
            <td><input class="input_text w120 pp" type="text" name="building" value="${param.building}" /></td>
            <td><div align="right">单元门牌号：</div></td>
            <td><input class="input_text w120 pp" type="text" name="roomNum" value="${param.roomNum}" /></td>
          	<td><div class="list-name">回款日期：</div></td>
            <td colspan="2"><input class="input_text w120 pp" id="date01" name="startTime" value="${param.startTime}" type="text" readonly="readonly" /> 至 <input class="input_text w120 pp" name="endTime" value="${param.endTime}" id="date02" type="text" readonly="readonly" /></td>
          </tr>
          <tr>
          	<td colspan="2">物业回款金额总计：<fmt:formatNumber value="${financeSum.wyProfit }" pattern="#,##0.##"/></td>
          	<td style="text-align: right;">代理回款金额总计：</td>
          	<td><fmt:formatNumber value="${financeSum.agentProfit }" pattern="#,##0.##"/></td>
          	<td></td>
          	<td align="left"><input class="info-btn small-btn w100" type="submit" value="查 询" />&nbsp;&nbsp;&nbsp;&nbsp;<!--  <input class="weak-btn small-btn w150" type="button" value="导出查询结果报表"> --></td>
          </tr>
        </table>
     </form>
     </div>
	    <display:table name="profitList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
			<%--
			<display:column title="序号" sortable="true" headerClass="sortable">
			<c:out value="${row_rowNum}"/></display:column> --%>
			<display:column title="代理商" sortable="true" >
			  <c:choose>
				  <c:when test="${empty row.agentName}"><div style="text-align: center">-</div></c:when>
				  <c:otherwise>${row.agentName}</c:otherwise>
			  </c:choose>
			 </display:column>
			<display:column title="物业公司" property="wyName" sortable="true" />
			<display:column title="城市" property="financeReqEntity.city" sortable="true" />
			<display:column title="小区" property="financeReqEntity.residential" sortable="true"/>
			<display:column title="楼栋" property="financeReqEntity.building" sortable="true"/>
			<display:column title="单元门牌号" property="financeReqEntity.roomNum" sortable="true"/>
			<display:column title="订单号" property="orderNo" sortable="true"></display:column>
			<display:column title="投资金额" property="financeBuyEntity.buyMoney" sortable="true" format="{0, number, 0.##}"/>
			<display:column title="回款金额（物业）" property="profitWy" sortable="true" format="{0, number, 0.##}" />
			<display:column title="回款金额（代理）" property="profitAgent" sortable="true" format="{0, number, 0.##}" />
			<display:column title="回款日期" property="profitTm" sortable="true" format="{0, date, yyyy-MM-dd}" />
		</display:table>
          
    
</div>
</body>

<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.common.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/locale/easyui-lang-zh_CN.js"></script>
</html>
