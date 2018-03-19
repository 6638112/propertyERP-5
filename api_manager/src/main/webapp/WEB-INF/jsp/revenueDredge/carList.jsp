<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>停车费代收明细</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/metro/easyui.css?v20150306">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/icon.css">
<style type="text/css">
.text-right{text-align: right;}
</style>
<script type="text/javascript">
function exp(){
	location.href = "<%=basePath%>/revenueCar/exportExcel.html?"+$("#searchForm").serialize();
}

function onSubmit() {
	$("#searchForm").attr("action", "<%=basePath%>/revenueCar/carList.html");
	$("#searchForm").submit();
}
</script>
</head>

<body>
<div class="info">
    <h2>停车费代收明细</h2>
    <div class="bs-example bgebeb">
    <form id="searchForm" name="form_main" action="<%=basePath%>/revenueCar/carList.html" method="post">
    	<input type="hidden" id="applyId" name="applyId" value="${param.applyId}"></input>
    	<input type="hidden" id="roleId" name="roleId" value="${param.roleId}" />
        <table class="info-list" border="0">
          <tr>
            <td><div align="right">物业公司：</div></td>
            <td><input class="input_text w120 pp" type="text" id="wyName" name="wyName" value="${param.wyName}"></input></td>
            <td><div align="right">缴费时间：</div></td>
            <td>
            	<input type="text" class="input_text pp icon_dt" id="date01" name="goalRevTimeStart" title="请选择起始时间"  value="${param.goalRevTimeStart }"> 至 
            	<input type="text" class="input_text pp icon_dt" id="date02" name="goalRevTimeEnd" title="请选择结束时间"  value="${param.goalRevTimeEnd }">
            </td>
            <td><div align="right">车费类型：</div></td>
            <td>
            	<select id="carType" name="carType" class="select_normal w131">
                    <option value="">全部</option>
	       			<option value="1" <c:if test="${param.carType == 1}">selected="selected"</c:if> >月卡车</option>
	       			<option value="2" <c:if test="${param.carType == 2}">selected="selected"</c:if> >年卡车</option>
	       			<option value="3" <c:if test="${param.carType == 3}">selected="selected"</c:if> >次缴车</option>
                </select>
            </td>
           	<td><div align="right">结算状态：</div></td>
            <td>
            	<select id="tkStatus" name="tkStatus" class="select_normal w131">
                    <option value="">全部</option>
	       			<option value="1" <c:if test="${param.tkStatus == 1}">selected="selected"</c:if> >未结算</option>
	       			<option value="2" <c:if test="${param.tkStatus == 2}">selected="selected"</c:if> >申请中</option>
	       			<option value="3" <c:if test="${param.tkStatus == 3}">selected="selected"</c:if> >已结算</option>
                </select>
            </td>
          </tr>
          <tr>
          	<td><div align="right">收益项目：</div></td>
            <td>
            	<select id="goalType" name="goalType" class="select_normal w131">
	       			<option value="6" <c:if test="${param.goalType eq 6}">selected="selected"</c:if> >停车费实收</option>
	       			<option value="17" <c:if test="${param.goalType eq 17}">selected="selected"</c:if> >停车费补贴</option>
                </select>
            </td>
            <td><div align="right">车牌号：</div></td>
            <td><input class="input_text w120 pp" type="text" id="carNum" name="carNum" value="${param.carNum}"></input></td>
          </tr>
        </table>
        <table class="info-list" border="0" style="color: red;">
          <tr>
	  	  	<td align="left" colspan="3">
	  	  		<span>账单金额：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmount}" pattern="#,##0.00"/></span>
	  	  		<span style="margin-left:2em;">实缴金额：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountReal}" pattern="#,##0.00"/></span>
	  	  		<span style="margin-left:2em;">补贴金额：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountDiscount}" pattern="#,##0.00"/></span>
	  	  	</td>
            <td align="left" colspan="5"><input class="info-btn small-btn w100" type="button" onclick="onSubmit();" value="查 询" />&nbsp;&nbsp;&nbsp;&nbsp;
            	<input class="info-btn small-btn w100" type="button" onclick="exp();" value="导 出" />
            </td>
          </tr>
        </table>
     </form>
     </div>
	    <display:table name="carRevenueSignalAmountList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
			<display:column title="物业公司" property="roleName" sortable="true" />
			<display:column title="小区名称" property="gbName" sortable="true"/>
			<!--<display:column title="楼栋" property="building" sortable="true"/>
			<display:column title="单元" property="unitName" sortable="true"/>
			<display:column title="房间号" property="room" sortable="true"/>-->
			<display:column title="车牌号" property="carNum" sortable="true"/>
			<display:column title="缴费时间" property="payTime" sortable="true" format="{0, date, yyyy-MM-dd HH:mm}" ></display:column>
			<display:column title="车辆类型" sortable="true">
				<c:choose>
				  <c:when test="${row.payNum == 12 }">年卡车</c:when>
				  <c:when test="${row.payNum == 0 }">次缴车</c:when>
				  <c:otherwise>月卡车</c:otherwise>
				</c:choose>
			</display:column>
			<display:column title="收益项目" sortable="true">
				<c:if test="${row.goalType eq 6}">停车费实收</c:if>
				<c:if test="${row.goalType eq 17}">停车费补贴</c:if>
			</display:column>
			<display:column title="缴费时长" sortable="true">
				<c:if test="${row.payNum != 0}">${row.payNum }个月</c:if>
			</display:column>
			<display:column title="缴费区间" sortable="true">
				<c:if test="${(not empty row.payStartDate) and (not empty row.payEndDate)}">
					${row.payStartDate}至${row.payEndDate}
				</c:if>
			</display:column>
			<display:column title="是否需要发票" sortable="true">
				<c:if test="${(not empty row.needBill) and (row.needBill eq 1)}">
					需要
				</c:if>
			</display:column>
			<display:column title="账单金额" sortable="true" style="text-align: right;">
				<fmt:formatNumber value="${row.amount }" pattern="#,##0.00"/>
			</display:column>
			<display:column title="实缴金额" sortable="true" style="text-align: right;">
				<fmt:formatNumber value="${row.amountUsrReal }" pattern="#,##0.00"/>
			</display:column>
			<display:column title="平台补贴金额" sortable="true" style="text-align: right;">
				<fmt:formatNumber value="${row.amountPtbt }" pattern="#,##0.00"/>
			</display:column>
			<display:column title="支付方式" sortable="true">
				<c:if test="${row.payMethod==1 }">微信支付<br/></c:if>
                <c:if test="${row.payMethod==2 }">支付宝钱包支付<br/></c:if>
                <c:if test="${row.payMethod==3 }">银联支付<br/></c:if>
                <c:if test="${row.payMethod==4 }">纯粮票支付<br/></c:if>
                <c:if test="${row.payMethod==5 }">纯积分支付<br/></c:if>
                <c:if test="${row.payMethod==6 }">微信轻应用支付<br/></c:if>
                <c:if test="${row.payMethod==7 }">纯消费券支付<br/></c:if>
                <c:if test="${row.payMethod==9 }">银行支付<br/></c:if>
			</display:column>
			<display:column title="支付流水" property="payFlowNo" sortable="true" />
		    <display:column title="结算状态" property="tkStatusStr" sortable="true" />
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
