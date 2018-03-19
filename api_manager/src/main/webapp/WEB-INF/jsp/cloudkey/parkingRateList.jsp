<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="../css/picbox.css" />
</head>

<body>
<div class="info">
    <h2>停车费一览表</h2>
    <div class="bs-example bgebeb">
    	<form action="../cloudkey/paysearch.html" id="searchForm">
	        <table class="info-list" border="0">
	          <tr>
	          	<td align="right">物业公司：</td>
	          	<td>
		          	<c:choose>
			          	<c:when test="${pcName != null}">
			          		<input id="pcName" name="pcName" type="text" value="${pcName}" disabled="true" readOnly="true" class="input_text w400">
			          	</c:when>
			          	<c:otherwise>
			          		<input id="pcName" name="pcName" type="text" class="input_text w400 pp" value="${param.pcName}">
			          	</c:otherwise>
		          	</c:choose>
	          	</td>
	          	<td align="right">停车场：</td>
	          	<td>
			        <input id="gbName" name="parking" type="text" class="input_text w400 pp" value="${param.parking}">
	          	</td>
	          	<td align="right">缴费时间：</td>
	          	<td colspan="2">
	          		<input id="date01" name=startTime type="text" class="input_text icon_dt pp"  title="请选择起始时间" value="${param.startTime}"> 
	          		至 
	          		<input  type="text" class="input_text icon_dt pp" id="date02"  name="endTime" title="请选择结束时间" value="${param.endTime}">
	          	</td>
	          </tr>
	          <tr>
	          	<td align="right">车辆类型：</td>
	          	<td>
	            	<select id="carType" class="select_normal w131" name="carType">
	                    <option value="">全部</option>
	                    <option value="1" <c:if test="${(not empty param.carType) and (param.carType eq 1)}">selected</c:if>>次缴车</option>
	                    <option value="2" <c:if test="${(not empty param.carType) and (param.carType eq 2)}">selected</c:if>>月卡车</option>
	                    <option value="3" <c:if test="${(not empty param.carType) and (param.carType eq 3)}">selected</c:if>>年卡车</option>
	                </select>
				</td>
	          	<td align="right">小区：</td>
	          	<td>
			        <input id="gbName" name="gbName" type="text" class="input_text w400 pp" value="${param.gbName}">
	          	</td>
				<td align="right">支付渠道：</td>
	          	<td colspan="2">
	            	<select id="payBigType" class="select_normal w131" onchange="setPayBigType()">
	                    <option value=""  <c:if test="${empty param.payType and param.payType ne 0}">selected</c:if>>所有</option>
                    	<option value="3" <c:if test="${(not empty param.payType) and ((param.payType eq 0) or (param.payType eq 2) or (param.payType eq 3))}">selected</c:if>>解放区支付</option>
                    	<option value="1" <c:if test="${(not empty param.payType) and (param.payType eq 1)}">selected</c:if>>线下支付</option>
	                </select>
	                <select id="paySmallType" class="select_normal w131" onchange="setPaySmallType()">
	                	<option tag="1" value=""  <c:if test="${empty param.payType}">selected</c:if>>所有</option>
                    	<option tag="2" value="3" <c:if test="${(not empty param.payType) and (param.payType eq 3)}">selected</c:if>>所有</option>
                    	<option tag="3" value="0" <c:if test="${(not empty param.payType) and (param.payType eq 0)}">selected</c:if>>线上支付</option>
                    	<option tag="4" value="2" <c:if test="${(not empty param.payType) and (param.payType eq 2)}">selected</c:if>>停车宝抵扣</option>
                    	<option tag="5" value="1" <c:if test="${(not empty param.payType) and (param.payType eq 1)}">selected</c:if>>现金支付</option>
	                </select>
	                <input type="hidden" id="payType" name="payType" value=""/>
				</td>
			 </tr>
	          <tr>
	          	<td align="right">车牌：</td>
	          	<td>
			        <input id="carNum" name="carNum" type="text" class="input_text w400 pp" value="${param.carNum}">
	          	</td>
	          	<td align="right">总额：</td>
	          	<td colspan="3">
	          	    <span class="bold f16 red"><fmt:formatNumber value="${totalMoney/100}" type="currency" pattern="0.00" maxFractionDigits="2"/></span>元
	          	</td>
	          	<td>
	          		<input class="input-btn w80" type="submit" value="查询"/>&nbsp;&nbsp;
	          		<input class="input-btn w80" type="button" onclick="exp();" value="导出"/>
	          	</td>
	          </tr>
	        </table>
        </form>
    </div>    

    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
		<display:column title="物业公司" property="pcName"/>
		<display:column title="停车场" property="parking"/>
		<display:column title="小区" property="gbName"/>
		<display:column title="楼栋" property="buildingname"/>
		<display:column title="单元" property="unitName"/>
		<display:column title="房间号" property="roomNum"/>
		<display:column title="车牌号" property="carNum"/>
		<display:column title="用户名">${row.userName}</display:column>
		<display:column title="手机号">${row.phoneNum}</display:column>
		<display:column title="缴费时间">${row.payTime}</display:column>
		<display:column title="车辆类型">
			<c:if test="${row.carType ==1}">
				次缴车
			</c:if>
			<c:if test="${row.carType ==2}">
				月卡车
			</c:if>
			<c:if test="${row.carType ==3}">
				年卡车
			</c:if>
		</display:column>
		<display:column title="缴费时长">${row.payNum}个月</display:column>
		<display:column title="缴费金额（元）" style="text-align:right;">
			<fmt:formatNumber value="${row.payMoney/100}" type="currency" pattern="0.00" maxFractionDigits="2"/>
		</display:column>
		<display:column title="实缴（元）" style="text-align:right;">
			<fmt:formatNumber value="${(row.payMoney-row.couponAmount-row.wyCouponAmount)/100}" type="currency" pattern="0.00" maxFractionDigits="2"/>
		</display:column>
		<display:column title="平台补贴（元）" style="text-align:right;">
			<fmt:formatNumber value="${row.couponAmount/100}" type="currency" pattern="0.00" maxFractionDigits="2"/>
		</display:column>
		<display:column title="物业补贴（元）" style="text-align:right;">
			<fmt:formatNumber value="${row.wyCouponAmount/100}" type="currency" pattern="0.00" maxFractionDigits="2"/>
		</display:column>
		<display:column title="支付渠道">
			<c:choose>
				<c:when test="${(not empty row.payType) and (row.payType==0)}">线上支付</c:when>
				<c:when test="${(not empty row.payType) and (row.payType==1)}">现金支付</c:when>
				<c:when test="${(not empty row.payType) and (row.payType==2)}">停车宝抵扣</c:when>
			</c:choose>
		</display:column>
		<display:column title="支付详情" property="payMethod"/>
		<display:column title="是否需要发票">
			<c:if test="${(not empty row.needBill) and (row.needBill eq 1)}">需要</c:if>
		</display:column>
		<display:column title="支付流水号" property="payFlown"/>
	</display:table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker01.js"></script>
<script type="text/javascript" src="../js/picbox.js?v20150215"></script>
<script type="text/javascript">	
function exp(){
	location.href = "${pageContext.request.contextPath}/cloudkey/exportpayList.html?"+$("#searchForm").serialize();
}

function showpayTypeOption(bigType){
	if(bigType==""){
		$("#paySmallType option:eq(1)").hide();
		$("#paySmallType option:not(:eq(1))").show();
	} else if(bigType=="1"){
		$("#paySmallType option[tag=5]").show();
		$("#paySmallType option[tag!=5]").hide();
	} else if(bigType=="3"){
		$("#paySmallType option[tag=1],#paySmallType option[tag=5]").hide();
		$("#paySmallType option[tag=2],#paySmallType option[tag=3],#paySmallType option[tag=4]").show();
	}
}

function setPayBigType(){
	var thz = $("#payBigType").val();
	if(thz==""){
		$("#paySmallType").val("");
		$("#payType").val("");
	} else if(thz=="1"){
		$("#paySmallType").val("1");
		$("#payType").val("1");
		$("#paySmallType option[tag!=5]").css("display","none");
	} else if(thz=="3"){
		$("#paySmallType").val("3");
		$("#payType").val("3");
	}
	showpayTypeOption(thz);
}

function setPaySmallType(){
	var thz = $("#paySmallType").val();
	if(thz==""){
		$("#payBigType").val("");
		$("#payType").val("");
	} else if(thz=="0"){
		$("#payBigType").val("3");
		$("#payType").val("0");
	} else if(thz=="1"){
		$("#payBigType").val("1");
		$("#payType").val("1");
	} else if(thz=="2"){
		$("#payBigType").val("3");
		$("#payType").val("2");
	} else if(thz=="3"){
		$("#payBigType").val("3");
		$("#payType").val("3");
	}
	showpayTypeOption($("#payBigType").val());
}
$(function(){
	setPaySmallType();
});
</script>
</html>