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
    <h2>物业抵扣收益表</h2>
    <div class="bs-example bgebeb">
    <form id="searchForm" name="form_main" action="<%=basePath%>/finance/profitList.html" method="post">
        <table class="info-list" border="0">
          <tr>
            <td><div align="right">城市：</div></td>
            <td><input class="input_text w120" type="text" name="city" value="${param.city}"></input></td>
            <td><div align="right">小区：</div></td>
            <td><input class="input_text w120" type="text" name="residential" value="${param.residential}" /></td>
            <td><div align="right">楼栋：</div></td>
            <td><input class="input_text w120" type="text" name="building" value="${param.building}" /></td>
            <td><div align="right">单元门牌号：</div></td>
            <td><input class="input_text w120" type="text" name="roomNum" value="${param.roomNum}" /></td>
          </tr>
          <tr>
          	<td><div class="list-name">回款日期：</div></td>
            <td><input class="input_text w120" id="date01" name="startTime" value="${param.startTime}" type="text" readonly="readonly" /> 至 <input class="input_text w120" name="endTime" value="${param.endTime}" id="date02" type="text" readonly="readonly" /></td>
          </tr>
          <tr>
            <td colspan="6" align="center"><input class="info-btn small-btn w100" type="submit" value="查 询" />&nbsp;&nbsp;&nbsp;&nbsp;<!--  <input class="weak-btn small-btn w150" type="button" value="导出查询结果报表"> --></td>
          </tr>
          
          <tr>
          	<td>收益金额总计：<fmt:formatNumber value="${financeSum.profitAll }" pattern="#,##0.##"/></td>
          </tr>
        </table>
     </form>
     </div>
	    <display:table name="profitList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
			<%--
			<display:column title="序号" sortable="true" headerClass="sortable">
			<c:out value="${row_rowNum}"/></display:column> --%>
			<display:column title="城市" property="financeReqEntity.city" sortable="true" />
			<display:column title="小区" property="financeReqEntity.residential" sortable="true"/>
			<display:column title="楼栋" property="financeReqEntity.building" sortable="true"/>
			<display:column title="单元门牌号" property="financeReqEntity.roomNum" sortable="true"/>
			<display:column title="订单号" property="orderNo" sortable="true"></display:column>
			<display:column title="投资金额" property="financeBuyEntity.buyMoney" sortable="true" format="{0, number, 0.00}"/>
			<display:column title="收益金额" property="profit" sortable="true" format="{0, number, 0.00}" />
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
