<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>物业管理-小区缴费管理-临时收费项-缴费周期</title>
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
	<link rel="stylesheet" type="text/css" href="../css/select2.css">
	<style>
		.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 155px 3px;}
		.w180{width: 175px;}
	</style>
</head>
<body>
<div class="info">
    <h2>临时收费账单配置</h2>
    <div class="bs-example bgebeb">
        <form method="post" action="../groupBuildingBillCycle/tmpBillList.html">
        <table class="info-list" border="0">
          <tr>
            <td><div class="list-name">小区名称：</div></td>
            <td><input type="text" class="input_text w120 pp" name="groupBuildingName" value="${groupBuildingName }"></td>
            <td><div class="list-name">账单名称：</div></td>
            <td><input type="text" class="input_text w120 pp" name="billName" value="${billName }"></td>
            <td><div class="list-name">账单周期：</div></td>
            <td colspan="2">
            	<input class="input_text date_picker w120 ept" name="billCycleStart" value="${param.billCycleStart}" type="text" title="请选择起始时间" placeholder="请选择起始时间" onclick="setmonth(this)" readonly="readonly" datatype="*" nullmsg="请请选择起始时间！">
            	至 
            	<input class="input_text date_picker w120 ept" name="billCycleEnd" value="${param.billCycleEnd}" type="text" title="请选择结束时间" placeholder="请选择结束时间" onclick="setmonth(this)" readonly="readonly" datatype="*" nullmsg="请选择结束时间！">
            </td>
            <td><div class="list-name">缴费时间：</div></td>
            <td colspan="2">
            	<input type="text" name="payTimeStart" title="请选择起始时间" value="${param.payTimeStart}" placeholder="请选择起始时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd'});" class="input_text pp w180 icon_dt pp">
            	至 
            	<input type="text" name="payTimeEnd" title="请选择结束时间" value="${param.payTimeEnd}" placeholder="请选择结束时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd'});" class="input_text pp w180 icon_dt pp">
            </td>
          </tr>
          <tr>
            <td colspan="10"><input class="info-btn small-btn w150" type="submit" value="查 询"></td>
          </tr>
        </table>
        </form>
    </div>     
    <display:table name="resList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
		<display:column title="小区名" property="groupBuildingName" sortable="true"/>
		<display:column title="账单名称" sortable="true">
			${row.billName}（临时收费子项）
		</display:column>
		<display:column title="账单周期">
			<c:if test="${not empty row.paytimeType and row.paytimeType==1}">${row.billMonth }</c:if>
			<c:if test="${not empty row.paytimeType and row.paytimeType==2}">${row.billMonthStart }至${row.billMonthEnd }</c:if>
		</display:column>
		<display:column title="缴费时间">
			${row.billPayStart }至${row.billPayEnd }
		</display:column>
		<display:column title="操作" media="html">
			<a class="blue editBill" href="../payBill/listPeriod.html?pageType=zq&gbId=${row.tGroupBuildingId}&gbName=${row.groupBuildingName }&billMonthStart=${row.billMonthStartStr }&billMonthEnd=${row.billMonthEndStr }&billTypeName=${row.billName}&feeType=3">账单查询</a>
		</display:column>
	</display:table>    
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
</html>