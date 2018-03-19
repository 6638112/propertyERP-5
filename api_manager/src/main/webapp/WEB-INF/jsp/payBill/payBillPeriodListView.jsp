<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>收费一览表
</title>
<link rel="stylesheet" type="text/css" href="../css/common.css"/>
<link rel="stylesheet" type="text/css" href="../css/style.css"/>
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css"/>
<link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>

<body>
<div class="info">
    <h2>收费一览表</h2>
    <form name="searchForm" id="searchForm" method="post" action="../payBill/listPeriodPayBillView.html">
		<%--v349大家共用账单查询方法--%>
		<input type="hidden" value="${param.cycleType}" name="cycleType"/>
		<input type="hidden" value="${param.gbId}" name="gbId"/>
		<input type="hidden" value="${param.feeType}" name="feeType"/>
		<%-- 从【抄表收费项-账单详情】进入需要该条件 --%>
		<input type="hidden" value="${param.pbtIsPropfee}" name="pbtIsPropfee"/>
    <div class="bs-example bgebeb" style="padding-right:100px">
        <table class="info-list" border="0">
          <tr>
            <td width="90"><div class="list-name">物业公司：</div></td>
            <td width="130">
            	<input type="text" class="input_text w120 pp" name="pcName" value='${param.pcName }'>
            </td>
            <td width="90"><div class="list-name">小区名：</div></td>
            <td width="130">
				<select name="gbName" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
					<option value="">选择小区</option>
					<c:forEach items="${gbList}" var="gb">
						<option value="${gb.name}" <c:if test="${param.gbName == gb.name }">selected="selected"</c:if>>${gb.name}</option>
					</c:forEach>
				</select>
            </td>
           
            <td width="90"><div class="list-name">业主姓名：</div></td>
            <td width="130"><input type="text" class="input_text w120 pp" value="${param.ppName }" name="ppName"></td>
            
          </tr>
          
          <tr>
            <td><div class="list-name">楼栋：</div></td>
            <td>
	            <input type="text" class="input_text w120 pp" name="bName" value='${param.bName }'/>
            </td>
            
            <td><div class="list-name">单元：</div></td>
            <td>
            	<input type="text" class="input_text w120 pp" value="${param.unitName }" name="unitName"/>
            </td>
            <td><div class="list-name">房号：</div></td>
            <td><input type="text" class="input_text w120 pp" value="${param.roomNum }" name="roomNum"/></td>
          </tr>
			<tr>
				<td width="90"></td>
				<td colspan="5"></td>
			</tr>
			<tr class="border-top01">
				<td width="90"></td>
				<td colspan="5"></td>
			</tr>
          <tr>
            <td><div class="list-name">费用名称：</div></td>
            <td><input type="text" class="input_text w120 pp" value="${param.billTypeName }" name="billTypeName"></td>
		  <td width="90"><div class="list-name">缴费解放号：</div></td>
		  <td><input type="text" class="input_text w120 pp" value="${param.buyerId }" name="buyerId"></td>
		  <td width="90"><div class="list-name">缴费时间：</div></td>
		  <td width="130"><input id="date01" value='${date01 }' readonly="readonly" name="date01" type="text" class="input_text icon_dt pp"  title="请选择起始时间" >
			  至 <input  type="text" class="input_text icon_dt pp" id="date02" readonly="readonly"  value='${date02 }'  name="date02" title="请选择结束时间"></td>
          </tr>

          <tr>
          	<td width="90"><div class="list-name">账单状态：</div></td>
            <td width="130"><select class="select_normal w131" name="isPay">
                    <option value="4">全部</option>
                    <option value="1" <c:if test="${param.isPay==1 }"> selected="selected"</c:if> >已缴费</option>
                    <%--<option value="2" <c:if test="${param.isPay==2 }"> selected="selected"</c:if> >未缴费</option>--%>
					<option value="3" <c:if test="${param.isPay==3 }"> selected="selected"</c:if> >部分已缴</option>
					<%--<option value="4" <c:if test="${param.isPay==4 || isPay == 4 }"> selected="selected"</c:if> >已缴费或部分已缴</option>--%>
                    </select>
            </td>
            
            <td width="90"><div class="list-name">缴费方式：</div></td>
            <td width="130">
            	<select class="select_normal w131" name="paymentWay">
					<option value="-1">全部</option>
					<option value="-2" <c:if test="${param.paymentWay==-2 }"> selected="selected"</c:if>>解放区支付</option>
					<option value="1" <c:if test="${param.paymentWay==1 }"> selected="selected"</c:if>>&nbsp;&nbsp;--用户在线支付</option>
					<option value="3" <c:if test="${param.paymentWay==3 }"> selected="selected"</c:if>>&nbsp;&nbsp;--代扣卡续费</option>
					<option value="4" <c:if test="${param.paymentWay==4 }"> selected="selected"</c:if>>&nbsp;&nbsp;--物业宝抵扣</option>
					<option value="2" <c:if test="${param.paymentWay==2 }"> selected="selected"</c:if>>现金缴费</option>
					<option value="5" <c:if test="${param.paymentWay==5 }"> selected="selected"</c:if>>银行托收</option>
            	</select>
            </td>
			  <td><div class="list-name">账单周期：</div></td>
			  <td>
				  <input type="text" id="begintime" class="input_text w120 " readonly="readonly" <c:choose><c:when test="${empty billMonthStart}">value="${param.billMonthStart }"</c:when><c:otherwise>value="${billMonthStart }"</c:otherwise></c:choose>  title="如：2014-07" name="billMonthStart" type="text" onclick="setmonth(this)" readonly="readonly" />
				  至
				  <input type="text" id="begintime" class="input_text w120 " readonly="readonly" value="${param.billMonthEnd }" title="如：2014-07" name="billMonthEnd" type="text" onclick="setmonth(this)" readonly="readonly"/>
			  </td>
          </tr>

        </table>
    </div>   
    <div class="mtop10">
        <table class="info-list" border="0">		  
		  <tr>
           	<td align="center">
           	<input class="info-btn small-btn w100" type="submit" value=" 查 询" id="queryBtn">&nbsp;&nbsp;&nbsp;&nbsp;
           	<input id="exportPayBill" class="weak-btn small-btn w150" type="button" value="导出查询对账单">&nbsp;&nbsp;&nbsp;&nbsp;
           	<!-- <input id="importBill" class="weak-btn small-btn w100" type="button" value="导入物业账单"> -->
           	</td>
          </tr>
          
        </table>
    </div>   
    </form>
	<h2 style="color: red">收费总额：${totalAmount}&nbsp;&nbsp;用户支付：${userPay}&nbsp;&nbsp;补贴总额：${perAmount}&nbsp;&nbsp;物业宝抵扣总额：${deduAmount}&nbsp;&nbsp;</h2>
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
		<display:column title="物业公司" property="pcName" sortable="true"/>
		<display:column title="小区名称" property="groupBuildingName" sortable="true" class="gbName"/>
		<display:column title="缴费解放号" >
			<c:if test="${row.buyerId>0 and row.isPay==1}">${row.buyerId }</c:if>
			<c:if test="${row.buyerId==0 and row.isPay==1 and row.paymentWay==3 }">${row.sys0UpdUser }</c:if>
		</display:column>
		<display:column title="费用名称" property="billTypeName" class="billName"/>
		<display:column title="账单开始月份" property="billMonthStart" class="startMonth"/>
		<display:column title="账单截止月份" property="billMonthEnd" />
		<display:column title="缴费时长" property="billMonthSize" />
		<display:column title="楼栋号" class="buildingName">
			<c:out value="${row.buildingName}"/> 
		</display:column>
		<display:column title="单元" class="unitName">
			<c:if test="${not empty row.realRoomUnitName}">
				<c:out value="${row.realRoomUnitName}" /> 
			</c:if>
		</display:column>
		<display:column title="房间号" property="realRoomNum" class="roomName"/>
		<display:column style="text-align:right;" title="账单金额(元)" class="amount t_right">
			<c:choose>
				<c:when test="${not empty feeType}">
					<fmt:formatNumber value="${row.amountBigDecimal/100 + 0.000001}" type="currency" pattern="#,##0.0" maxFractionDigits="2"/>
				</c:when>
				<c:otherwise>
					<fmt:formatNumber value="${row.amount/100 + 0.000001}" type="currency" pattern="#,##0.0" maxFractionDigits="2"/>
				</c:otherwise>
			</c:choose>
		</display:column>
		<c:if test="${feeType ==null || feeType == ''}">
			<display:column title="抵扣额" style="text-align:right;">
				<fmt:formatNumber value="${row.deduPrice/100 + 0.000001}" type="currency" pattern="#,##0.0" maxFractionDigits="2"/>
			</display:column>
			<display:column title="补贴额" style="text-align:right;">
				<c:choose>
					<c:when test="${row.isPay==1&& row.financeStatus == 1}">
						<fmt:formatNumber value="${(row.amount-row.deduPrice - row.succPayAmount)/100 + 0.000001}" type="currency" pattern="#,##0.0" maxFractionDigits="2"/>
					</c:when>
					<c:when test="${row.isPay==1&& row.financeStatus == 0}">
						<fmt:formatNumber value="${(row.amount-row.succPayAmount)/100 + 0.000001}" type="currency" pattern="#,##0.0" maxFractionDigits="2"/>
					</c:when>
					<c:when test="${empty row.financeStatus && !empty row.succPayAmount}">
						<fmt:formatNumber value="${(row.amount-row.succPayAmount)/100 + 0.000001}" type="currency" pattern="#,##0.0" maxFractionDigits="2"/>
					</c:when>
					<c:otherwise>0.0</c:otherwise>
				</c:choose>
			</display:column>
		</c:if>

		<display:column title="业主姓名" property="propertyProprietorName"/>
		<display:column title="缴费方式" >
			<c:if test="${row.paymentWay==1}">用户在线支付</c:if>
			<c:if test="${row.paymentWay==2}">现金缴费</c:if>
			<c:if test="${row.paymentWay==3}">代扣卡续费</c:if>
			<c:if test="${row.paymentWay==4}">物业宝抵扣</c:if>
			<c:if test="${row.paymentWay==5}">银行托收</c:if>
		</display:column>
		<display:column title="账单状态" >
			<c:if test="${row.isPay==1}">已缴</c:if>
			<c:if test="${row.isPay==2}">
			 <c:choose>
				 <c:when test="${row.financeStatus == 1}">部分已缴</c:when>
				 <c:otherwise>未缴</c:otherwise>
			 </c:choose>
			</c:if>
		</display:column>
		<display:column title="缴费时间"  maxLength="15">
			<c:if test="${row.isPay==1 }"><c:choose><c:when test="${not empty row.payTime }">${row.payTime }</c:when><c:otherwise>${row.sys0UpdTime }</c:otherwise></c:choose></c:if>
		</display:column>
	</display:table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript" src="../js/DatePicker.js?v1"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker01.js"></script>
<script type="text/javascript" >
	$(function() {
		$('.select2_class').select2();
	});

	$("#exportPayBill").click(function(){
		if($(".empty").length){//表行有空行，即无查询结果
			alert("没有可导出的对账单信息。");
			return false;
		}
		window.location.href="../payBill/exportPayBillPeriod.html?feeType=${feeType}";
	});
</script>
</html>
