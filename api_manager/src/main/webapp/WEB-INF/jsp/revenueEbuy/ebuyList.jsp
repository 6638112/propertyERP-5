<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>周边店铺交易明细</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/metro/easyui.css?v20150306">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/select2.css">
<style type="text/css">
	.text-right{text-align: right;}
</style>
<script type="text/javascript">
function exp(){
	location.href = "<%=basePath%>/revenueEbuy/ebuySearch.html";
}
<%--
function exp(){
	var applyId = $("#applyId").val();
	var roleId = $("#roleId").val();
	var supplyName = $("#supplyName").val();
	var groupBuildingName = $("#groupBuildingName").val();
	var payTmStart = $("#date01").val();
	var payTmEnd = $("#date02").val();
	var tkStatus = $("#tkStatus").val();
	
	location.href="<%=basePath%>/revenueEbuy/ebuySearch.html?" + "applyId=" + applyId + "&roleId=" + roleId + "&supplyName=" + supplyName + "&groupBuildingName=" + groupBuildingName + "&payTmStart=" + payTmStart
					+ "&payTmEnd=" + payTmEnd + "&tkStatus=" + tkStatus;
}
--%>
</script>
</head>

<body>
<div class="info">
    <h2>店铺收益明细</h2>
    <div class="bs-example bgebeb">
    <form id="searchForm" name="form_main" action="<%=basePath%>/revenueEbuy/ebuyList.html" method="post">
    	<input type="hidden" id="applyId" name="applyId" value="${param.applyId}"></input>
    	<input type="hidden" id=roleId name="roleId" value="${param.roleId}"></input>
        <table class="info-list" border="0">
          <tr>
          	<td width="7%"><div align="right">物业公司/代理商：</div></td>
		    <td width="13%"><input class="input_text w120 pp" type="text" id="wyOrAgentName" name="wyOrAgentName" value="${param.wyOrAgentName}"></input></td>
            <td width="7%"><div align="right">店铺名称：</div></td>
            <td width="13%"><input class="input_text w120 pp" type="text" id="supplyName" name="supplyName" value="${param.supplyName}"></input></td>
            <td width="7%"><div align="right">小区名称：</div></td>
            <td width="13%">
            	<%-- <input class="input_text w120 pp" type="text" id="groupBuildingName" name="groupBuildingName" value="${param.groupBuildingName}" /> --%>
            	<select id="groupBuiliding" name="gbName" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
						  <option value="">选择小区</option>
				</select>
            </td>
            <td width="7%"><div align="right">支付时间：</div></td>
            <td width="28%">
            	<input type="text" class="input_text pp icon_dt" id="date01" name="payTmStart" title="请选择起始时间"  value="${param.payTmStart }"> 至 
            	<input type="text" class="input_text pp icon_dt" id="date02" name="payTmEnd" title="请选择结束时间"  value="${param.payTmEnd }">
            </td>
          </tr>
          <tr>
          	<td width="7%"><div align="right">结算状态：</div></td>
            <td width="13%">
            	<select  id="tkStatus" name="tkStatus" class="select_normal w131">
                    <option value="">全部</option>
	       			<option value="1" <c:if test="${param.tkStatus == 1}">selected="selected"</c:if> >未结算</option>
	       			<option value="2" <c:if test="${param.tkStatus == 2}">selected="selected"</c:if> >申请中</option>
	       			<option value="3" <c:if test="${param.tkStatus == 3}">selected="selected"</c:if> >已结算</option>
                </select>
            </td>
            <c:if test="${roleType == 1}" >
	            <td width="7%"><div align="right">收益对象：</div></td>
	            <td width="13%">
	            	<select  id="profitObject" name="profitObject" class="select_normal w131">
	                    <option value="0">全部</option>
		       			<option value="1" <c:if test="${param.profitObject == 1}">selected="selected"</c:if> >店铺</option>
		       			<option value="2" <c:if test="${param.profitObject == 2}">selected="selected"</c:if> >物业</option>
		       			<option value="3" <c:if test="${param.profitObject == 3}">selected="selected"</c:if> >代理</option>
		       			<option value="4" <c:if test="${param.profitObject == 4}">selected="selected"</c:if> >平台</option>
	                </select>
	            </td>
            </c:if>
          </tr>
        </table>
        <table class="info-list" border="0" style="color: red;">
          <tr>
          	<%--
          	<c:choose>
          		<c:when test="${isSupply == true }">
          			<td align="right" width="7%">结算总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmount }" pattern="#,##0.00"/></td>
          			<td width="40%">&nbsp;</td>
          		</c:when>
          		<c:otherwise>
          			<td align="right" width="7%">优惠总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountDiscount }" pattern="#,##0.00"/></td>
			        <td align="right" width="20%">实付总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountReal }" pattern="#,##0.00"/></td>
		          	<td align="right" width="20%">结算总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmount }" pattern="#,##0.00"/></td>
          		</c:otherwise>
          	</c:choose>
          	 --%>
          	<c:if test="${roleType == 1 && (empty param.profitObject || param.profitObject == 0 || param.profitObject == 1)}">
          		<td align="right" width="7%">优惠总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalOrderDiscount }" pattern="#,##0.00"/></td>
			    <td align="right" width="7%">实付总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalOrderReal }" pattern="#,##0.00"/></td>
          	</c:if>
          	<c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 1 }">
          		<td align="right" width="8%">退款总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountReal }" pattern="#,##0.00"/></td>
          	</c:if>
          	<td align="right" width="7%">运单总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountDiscount }" pattern="#,##0.00"/></td>
          	<td align="right" width="13%">平台使用费总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalPlatformUse }" pattern="#,##0.00"/></td>
          	<c:choose>
	          	<c:when test="${roleType == 2}">
	          		<td align="right" width="10%">结算总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalWy }" pattern="#,##0.00"/></td>
	          		<td width="18%">&nbsp;</td>
	          	</c:when>
	          	<c:when test="${roleType == 3}">
	          		<td align="right" width="10%">结算总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalAgent }" pattern="#,##0.00"/></td>
	          		<td width="18%">&nbsp;</td>
	          	</c:when>
	          	<c:when test="${roleType == 5}">
	          		<td align="right" width="10%">结算总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalSupply }" pattern="#,##0.00"/></td>
	          		<td width="18%">&nbsp;</td>
	          	</c:when>
	          	<c:otherwise>
	          	  <c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 1}">
	          		<td align="right" width="10%">收益金额（店铺）：<fmt:formatNumber value="${ebuyRevenueTotal.totalSupply }" pattern="#,##0.00"/></td>
	          	  </c:if>
	          	  <c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 2}">
	          		<td align="right" width="10%">收益金额（物业）：<fmt:formatNumber value="${ebuyRevenueTotal.totalWy }" pattern="#,##0.00"/></td>
	          	  </c:if>
	          	  <c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 3}">
	          		<td align="right" width="10%">收益金额（代理）：<fmt:formatNumber value="${ebuyRevenueTotal.totalAgent }" pattern="#,##0.00"/></td>
	          	  </c:if>
	          	  <c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 4}">
	          		<td align="right" width="10%">收益金额（平台）：<fmt:formatNumber value="${ebuyRevenueTotal.totalPlatform }" pattern="#,##0.00"/></td>
	          	  </c:if>
	          		<td width="8%">&nbsp;</td>
	          	</c:otherwise>
          	</c:choose>
	      </tr>
	      <tr>
            <td align="right" colspan="10"><input class="info-btn small-btn w100" type="submit" value="查 询" />&nbsp;&nbsp;&nbsp;&nbsp;
            	<input class="info-btn small-btn w100" type="button" onclick="exp();" value="导 出" />&nbsp;&nbsp;&nbsp;&nbsp;<!--  <input class="weak-btn small-btn w150" type="button" value="导出查询结果报表"> -->
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
          </tr>
        </table>
     </form>
     </div>
	    <display:table name="ebuyRevenueSignalAmountList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
			<c:if test="${roleType != 5}">
				<display:column title="物业公司" property="revenueSignalAmountEbuy.pcName" sortable="true" />
				<display:column title="代理商" property="revenueSignalAmountEbuy.agentName" sortable="true" />
			</c:if>
			<display:column title="店铺名称" property="supplyName" sortable="true" />
			<%--
			<display:column title="解放号" property="revenueSignalAmountEbuy.huaId" sortable="true"/>
			 --%>
			<display:column title="小区名称" property="revenueSignalAmountEbuy.groupBuildingName" sortable="true"/>
			<display:column title="订单号" sortable="true">
				<c:choose>
					<c:when test="${roleType ==2 || roleType == 3 }">${row.orderNo}</c:when>
					<c:otherwise><a class="blue editShopInfo" href="../order/viewOrderDetail.html?delieveOrderId=${row.goalId}&orderNo=${row.orderNo}">${row.orderNo}</a></c:otherwise>
				</c:choose>
			</display:column>
			<%-- <display:column title="平台补贴金额" sortable="true">0</display:column> --%>
			<c:if test="${roleType == 1 }">
				<display:column title="优惠金额" sortable="true" style="text-align: right;">
					<fmt:formatNumber value="${row.revenueSignalAmountEbuy.amountDiscount }" pattern="#,##0.00"/>
				</display:column>
				<display:column title="运单实付" sortable="true" style="text-align: right;">
					<fmt:formatNumber value="${row.revenueSignalAmountEbuy.amountReal }" pattern="#,##0.00"/>
				</display:column>
			</c:if>
			<display:column title="运单总额" sortable="true" style="text-align: right;">
				<fmt:formatNumber value="${row.revenueSignalAmountEbuy.amountTotal }" pattern="#,##0.00"/>
			</display:column>
			<%-- 
			<c:if test="${isSupply != true }">
				<display:column title="订单实付" sortable="true" style="text-align: right;">
					<fmt:formatNumber value="${row.revenueSignalAmountEbuy.amountOrderReal }" pattern="#,##0.00"/>
				</display:column>
			</c:if>
			 --%>
			<%--<display:column title="支付渠道" property="revenueSignalAmountEbuy.huaId" sortable="true" format="{0, number, 0.##}"/> 
			<display:column title="流水号" property="revenueSignalAmountEbuy.huaId" sortable="true" format="{0, number, 0.##}" /> --%>
			<%--
			<display:column title="支付渠道" sortable="true">
				<c:choose>
					<c:when test="${row.revenueSignalAmountEbuy.payMethod == 1 }">微信支付</c:when>
					<c:when test="${row.revenueSignalAmountEbuy.payMethod == 2 }">支付宝</c:when>
					<c:when test="${row.revenueSignalAmountEbuy.payMethod == 3 }">银联支付</c:when>
					<c:when test="${row.revenueSignalAmountEbuy.payMethod == 4 }">纯粮票支付</c:when>
					<c:when test="${row.revenueSignalAmountEbuy.payMethod == 5 }">纯积分支付</c:when>
					<c:when test="${row.revenueSignalAmountEbuy.payMethod == 6 }">微信轻应用支付</c:when>
					<c:when test="${row.revenueSignalAmountEbuy.payMethod == 7 }">纯优惠券支付</c:when>
				</c:choose>
			</display:column>
			
			<display:column title="流水号" property="revenueSignalAmountEbuy.flowNo" sortable="true" />
			 --%>
			<display:column title="支付时间" property="payTm" sortable="true" format="{0, date, yyyy-MM-dd HH:mm}" />
			<display:column title="退款金额" sortable="true" style="text-align: right;">
				<fmt:formatNumber value="${row.revenueSignalAmountEbuy.amountRefund }" pattern="#,##0.00"/>
			</display:column>
			<display:column title="平台收入" sortable="true" style="text-align: right;">
				<fmt:formatNumber value="${row.revenueSignalAmountEbuy.amoutDeduct }" pattern="#,##0.00"/>
			</display:column>
			<c:choose>
				<c:when test="${roleType == 2}">
					<display:column title="收益金额" sortable="true" style="text-align: right;">
						<fmt:formatNumber value="${row.amountWy }" pattern="#,##0.00"/>
					</display:column>
					<display:column title="结算状态" property="tkStatusWyStr" sortable="true" />
				</c:when>
				<c:when test="${roleType == 3}">
					<display:column title="收益金额" sortable="true" style="text-align: right;">
						<fmt:formatNumber value="${row.amountAgent }" pattern="#,##0.00"/>
					</display:column>
					<display:column title="结算状态" property="tkStatusAgentStr" sortable="true" />
				</c:when>
				<c:when test="${roleType == 5}">
					<display:column title="结算金额" sortable="true" style="text-align: right;">
						<fmt:formatNumber value="${row.amountSupply }" pattern="#,##0.00"/>
					</display:column>
					<display:column title="结算状态" property="tkStatusSupplyStr" sortable="true" />
				</c:when>
				<c:otherwise>
				  <c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 1}">
					<display:column title="结算金额<span style='color:red'>(店铺)</span>" sortable="true" style="text-align: right;">
						<fmt:formatNumber value="${row.amountSupply }" pattern="#,##0.00"/>
					</display:column>
					<c:if test="${param.profitObject == 1 }">
						<display:column title="结算状态" property="tkStatusSupplyStr" sortable="true" />
					</c:if>
				  </c:if>
				  <c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 2}">
					<display:column title="收益金额<span style='color:red'>(物业)</span>" sortable="true" style="text-align: right;">
						<fmt:formatNumber value="${row.amountWy }" pattern="#,##0.00"/>
					</display:column>
					<c:if test="${param.profitObject == 2 }">
						<display:column title="结算状态" property="tkStatusWyStr" sortable="true" />
					</c:if>
				  </c:if>
				  <c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 3}">
					<display:column title="收益金额<span style='color:red'>(代理)</span>" sortable="true" style="text-align: right;">
						<fmt:formatNumber value="${row.amountAgent }" pattern="#,##0.00"/>
					</display:column>
					<c:if test="${param.profitObject == 3 }">
						<display:column title="结算状态" property="tkStatusAgentStr" sortable="true" />
					</c:if>
				  </c:if>
				  <c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 4}">
					<display:column title="收益金额<span style='color:red'>(平台)</span>" sortable="true" style="text-align: right;">
						<fmt:formatNumber value="${row.amountPlatform }" pattern="#,##0.00"/>
					</display:column>
				  </c:if>
				</c:otherwise>
			</c:choose>
		</display:table>
          
    
</div>
</body>

<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.common.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/DatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/select2.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#tkStatus").change(function(){
		if($("#tkStatus").val() != '') {
			 $("#profitObject").val("1");
		}
	});
	if($("#tkStatus").val() != '') {
		 $("#profitObject").val("1");
	}
});


$(function(){
	var gbName = '${param.gbName}';
	$.ajax({
		url: '<%=basePath%>/groupBuilding/getGroupBuildings.json',
		dataType: 'json',
		success: function (data) {
			var list = JSON.parse(data);
			var strHtml = "<option value=''>选择小区</option>";
			$.each(list, function (i, item) {
				var str = "";
				if(gbName == item.name) {
					str = "<option value='" + item.name + "' selected='selected'>" + item.name + "</option>";
				} else {
					str = "<option value='" + item.name + "'>" + item.name + "</option>";
				}
				strHtml += str;
			});
			$("#groupBuiliding").html(strHtml);
			$('.select2_class').select2();
		}
	});
});
</script>
</html>
