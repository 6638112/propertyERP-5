<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="com.cnfantasia.server.ms.pub.session.UserContext" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-物业代扣卡购买列表</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css"/>
</head>

<body>
<div class="info">
    <h2>物业缴费卡管理</h2>
    <div class="bs-example bgebeb">
    	<form method="post" action="../propertyCard/listCardBuyRecord.html">
    		<input name="search" value="1" type="hidden"/>
	        <table class="info-list" border="0">
	          <tr>
	            <td><div align="right">购买解放号：</div></td>
	            <td><input type="text" value="${param.huaId }" class="input_text pp w120" name="huaId" /></td>
	            <td><div align="right">购买时间：</div></td>
	            <td colspan="3"><input id="date01" value='${param.date01 }' name="date01" type="text" class="input_text icon_dt pp"  title="请选择起始时间" /> 至 <input  type="text" class="input_text icon_dt pp" id="date02"  value='${param.date02 }'  name="date02" title="请选择结束时间"/></td>
	           <%--  <td><div align="right">已转粮票金额：</div></td>
	            <td colspan="3">
	            	<select name="pcToEnvelopeMoney" class="select_normal">
	            		<option value="">全部</option>
	            		<option value="greater0" ${(param.pcToEnvelopeMoney eq 'greater0')?'selected':''}>>0</option>
	            		<option value="equal0" ${(param.pcToEnvelopeMoney eq 'equal0')?'selected':''}>=0</option>
	            	</select>
	            </td> --%>
	            <td><div align="right">代扣小区：</div></td>
	            <td>
	          		<input type="text" class="input_text pp w120" value="${param.gbName }" name="gbName" />
	            </td>
	            
				<td></td>
	            <td>
	            	<input class="input-btn w80" type="submit" value="搜索" />
	            	<!-- <input class="input-btn w120" type="button" value="代扣卡转粮票一览表" onclick="goPcToEnvelopeHistory('')"/> -->
	            </td>
	            <td></td>
	            <td></td>
	          </tr>
	        </table>
    	</form>
    </div>
    
    <display:table name="resList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="resultSize" >
		<display:column title="购买解放号" property='tUserFId' />
		<display:column title="用户门牌小区" style="max-width:160px;">
			<c:forEach var="roomInfo" items="${row.userRoomInfoList }">
				${roomInfo };
			</c:forEach>
		</display:column>
		<display:column title="累计购买金额">
			<fmt:formatNumber type="number"  pattern="0.00" value="${row.cardAmount/100 }" maxFractionDigits="2"/>
		</display:column>
		<display:column title="累计优惠金额" ><fmt:formatNumber type="number"  pattern="0.00" value="${row.discountAmt/100 }" maxFractionDigits="2"/></display:column>
		<display:column title="累计实付金额">
			<fmt:formatNumber type="number"  pattern="0.00" value="${row.realPayAmt/100 }" maxFractionDigits="2"/>
		</display:column>
		<%-- <display:column title="已转粮票金额">
			<a href="javascript:goPcToEnvelopeHistory(${row.tUserFId})" class="blue">
				<fmt:formatNumber type="number"  pattern="0.00" value="${row.totalRedEnvelopeMoney/100 }" maxFractionDigits="2"/>
			</a>
		</display:column> --%>
		<display:column title="剩余金额" ><fmt:formatNumber type="number"  pattern="0.00" value="${row.balanceAmt/100 }" maxFractionDigits="2"/></display:column>
		<display:column title="接受短信号码">
			${row.notifyPhone}
		</display:column>
		
		<display:column title="最后购买时间">${fn:substring(row.buyTime, 0, 19)}</display:column>
		<display:column title="代扣小区"  style="max-width:260px;">
			<c:if test="${row.gbName!=null}">
				[${row.gbName }]
			</c:if>
		</display:column>
		<display:column title="操作" media="html">
			<a class='blue checkPay' href="../propertyCard/cardBuyRecordDetail.html?userId=${row.tUserFId }">查看</a>
		</display:column>
	</display:table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker01.js"></script>
<script type="text/javascript" src="../js/DatePicker.js"></script>
<!-- <script>
	function goPcToEnvelopeHistory(userId){
		location = "${pageContext.request.contextPath}/payRedEnvelope/pcToEnvelopeHistory.html?userId="+userId;
	}
</script> -->
</html>
