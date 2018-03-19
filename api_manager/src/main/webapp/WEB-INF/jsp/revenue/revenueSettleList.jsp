<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>结算情况查询</title>
<link rel="stylesheet" type="text/css" href="../css/common.css"/>
<link rel="stylesheet" type="text/css" href="../css/style.css"/>
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css"/>
<style type="text/css">
	.text-right{text-align: right;}
</style>
</head>

<body>
<div class="info"> 
    <h2>结算情况查询</h2>
    <div class="bs-example bgebeb">
    <form action="..${formUrl}">
        <table class="info-list" border="0">
          <tr>
           	<td width="90"><div class="list-name">发起时间：</div></td>
            <td colspan="3"><input id="date01" value='${param.date01 }' name="date01" type="text" class="input_text icon_dt"  title="请选择起始时间" /> 至 <input  type="text" class="input_text icon_dt" id="date02"  value='${param.date02 }'  name="date02" title="请选择结束时间"/></td>	   
			<td><div align="right">提款单号：</div></td>
            <td><input class="input_text w120" type="text" name="tkNo" value="${param.tkNo }"/></td>
            <td width="90"><div class="list-name">提款状态：</div></td>
	            <td width="130">
	            	<select class="select_normal w131" name="tkStatus">
	            		<option value="-1">全部</option>
	            		<option value="2" <c:if test="${param.tkStatus==2 }"> selected="selected"</c:if>>申请中</option>
	            		<option value="3" <c:if test="${param.tkStatus==3 }"> selected="selected"</c:if>>已转账</option>
	            	</select>
	            </td>
            </tr>
          <tr>
            <td colspan="6" align="center"><input class="info-btn small-btn w100" type="submit" value="查 询" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
          </tr>
        </table>
     </form>
    </div>      
    
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
		<display:column title="提款单号" property="tkNo" sortable="true"/>
		<display:column title="申请发起时间" property="applyTime" sortable="true"/>
		<display:column title="提款总金额" style="text-align: right;">
			<fmt:formatNumber value="${row.totalAmount }" pattern="#,##0.00"/>
		</display:column>
		<display:column title="收益项目">
			<c:choose>
				<c:when test="${row.goalType == 1}">认证门牌</c:when>
				<c:when test="${row.goalType == 2}">服务类订单</c:when>
				<c:when test="${row.goalType == 3}">物业宝佣金</c:when>
				<c:when test="${row.goalType == 9}">停车宝佣金</c:when>
				<c:when test="${row.goalType == 4}">超市收益</c:when>
				<c:when test="${row.goalType == 5}">物业费实收</c:when>
				<c:when test="${row.goalType == 6}">停车费实收</c:when>
				<c:when test="${row.goalType == 15}">物业费补贴</c:when>
				<c:when test="${row.goalType == 7}">其他代收费用</c:when>
				<c:when test="${row.goalType == 8}">物业宝抵扣</c:when>
				<c:when test="${row.goalType == 10}">停车宝抵扣</c:when>
				<c:when test="${row.goalType == 17}">停车费补贴</c:when>
				<c:otherwise>未定义</c:otherwise>
			</c:choose>
		</display:column>
		<display:column title="状态">
			<c:if test="${row.tkStatus == 2 }">申请中</c:if>
			<c:if test="${row.tkStatus == 3 }">已转账</c:if>
		</display:column>
		<display:column title="操作">
		  <c:choose>
			<c:when test="${row.goalType==5 || row.goalType==7 || row.goalType==15}"><a class="blue" href="javascript:toDetailList(${row.applyId}, ${row.goalType });">明细</a></c:when>
			<c:when test="${row.goalType==2}"><a class="blue" href="javascript:toDredgeDetailList(${row.applyId});">明细</a></c:when>
			<c:when test="${row.goalType==3}"><a class="blue" href="javascript:toFinanceDetailList(${row.applyId});">明细</a></c:when>
			<c:when test="${row.goalType==9}"><a class="blue" href="javascript:toCarFinanceDetailList(${row.applyId});">明细</a></c:when>
			<c:when test="${row.goalType==8}"><a class="blue" href="javascript:toFinanceDeduList(${row.applyId});">明细</a></c:when>
			<c:when test="${row.goalType==10}"><a class="blue" href="javascript:toCarDeduList(${row.applyId});">明细</a></c:when>
			<c:when test="${row.goalType==4}"><a class="blue" href="javascript:toEbuyDetailList(${row.applyId});">明细</a></c:when>
			<c:when test="${row.goalType==6 or row.goalType==17}"><a class="blue" href="javascript:toCarDetailList(${row.applyId}, ${row.goalType});">明细</a></c:when>
		  </c:choose>
		</display:column>
	</display:table>
</div>
</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/DatePicker.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>

<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript">
function toDetailList(applyId, goalType){
	window.location.href="<%=basePath%>/payBill/searchRevenue.html?applyId="+applyId + '&projectType=' + goalType;
}

function toEbuyDetailList(applyId){
	window.location.href='<%=basePath%>/revenueEbuy/ebuyList.html?applyId='+applyId;
}

function toDredgeDetailList(applyId){
	window.location.href='<%=basePath%>/revenueDredge/dredgeList.html?applyId='+applyId;
}

function toFinanceDetailList(applyId){
	window.location.href='<%=basePath%>/revenueFinance/financeList.html?applyId='+applyId;
}

function toCarFinanceDetailList(applyId){
	window.location.href='<%=basePath%>/revenueFinance/carFinanceList.html?applyId='+applyId;
}

function toCarDetailList(applyId, goalType){
	window.location.href='<%=basePath%>/revenueCar/carList.html?applyId='+applyId+'&goalType='+goalType;
}

function toFinanceDeduList(applyId){
	window.location.href='<%=basePath%>/revenueFinanceDedu/financeDeduList.html?applyId='+applyId;
}

function toCarDeduList(applyId){
	window.location.href='<%=basePath%>/revenueCarDedu/carDeduList.html?applyId='+applyId;
}
</script>
</html>
