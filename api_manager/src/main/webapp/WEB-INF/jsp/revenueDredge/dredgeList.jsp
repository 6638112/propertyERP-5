<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上门维修收益明细</title>
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
	location.href = "<%=basePath%>/revenueDredge/exportExcel.html";
		<%--
	$("#searchForm").attr("action", "<%=basePath%>/revenueDredge/exportExcel.html");
	$("#searchForm").submit();
	var applyId = $("#applyId").val();
	var roleId = $("#roleId").val();
	var supplyName = $("#supplyName").val();
	var groupBuildingName = $("#groupBuildingName").val();
	var payTmStart = $("#date01").val();
	var payTmEnd = $("#date02").val();
	var tkStatus = $("#tkStatus").val();
	var profitObject = $("#profitObject").val();
	
	location.href="<%=basePath%>/revenueEbuy/ebuySearch.html?" + "applyId=" + applyId + "&supplyName=" + supplyName + "&groupBuildingName=" + groupBuildingName + "&payTmStart=" + payTmStart
					+ "&payTmEnd=" + payTmEnd + "&tkStatus=" + tkStatus;
	--%>
}

function onSubmit() {
	$("#searchForm").attr("action", "<%=basePath%>/revenueDredge/dredgeList.html");
	$("#searchForm").submit();
}


</script>
</head>

<body>
<div class="info">
    <h2>上门维修收益明细</h2>
    <div class="bs-example bgebeb">
    <form id="searchForm" name="form_main" action="<%=basePath%>/revenueDredge/dredgeList.html" method="post">
    	<input type="hidden" id="applyId" name="applyId" value="${param.applyId}"></input>
    	<input type="hidden" id="roleId" name="roleId" value="${param.roleId}" />
    	<c:choose>
    		<c:when test="${roleType == 0}">
    			<table class="info-list" border="0">
		          <tr>
		            <td width="7%"><div align="right">物业公司/代理商：</div></td>
		            <td width="13%"><input class="input_text w120 pp" type="text" id="wyOrAgentName" name="wyOrAgentName" value="${param.wyOrAgentName}"></input></td>
		            <td width="7%"><div align="right">小区名称：</div></td>
				  	<td width="13%">
					  <select id="groupBuiliding" name="gbName" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
						  <option value="">选择小区</option>
					  </select>
				  	</td>
		            <td width="7%"><div align="right">推荐人手机号：</div></td>
		            <td width="13%"><input class="input_text w120 pp" type="text" id="recommenderName" name="recommenderName" value="${param.recommenderName}" /></td>
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
		            <td width="7%"><div align="right">收益对象：</div></td>
		            <td width="13%">
		            	<select  id="profitObject" name="profitObject" class="select_normal w131">
		                    <option value="0">全部</option>
			       			<option value="1" <c:if test="${param.profitObject == 1}">selected="selected"</c:if> >师傅</option>
			       			<option value="2" <c:if test="${param.profitObject == 2}">selected="selected"</c:if> >物业</option>
			       			<option value="3" <c:if test="${param.profitObject == 3}">selected="selected"</c:if> >代理</option>
			       			<option value="4" <c:if test="${param.profitObject == 4}">selected="selected"</c:if> >平台</option>
			       			<option value="5" <c:if test="${param.profitObject == 5}">selected="selected"</c:if> >推荐人</option>
		                </select>
		            </td>
		          </tr>
	        	</table>
    		</c:when>
    		<c:otherwise>
    			<table class="info-list" border="0">
		          <tr>
		            <td width="7%"><div align="right">物业公司/代理商：</div></td>
		            <td width="13%"><input class="input_text w120 pp" type="text" id="wyOrAgentName" name="wyOrAgentName" value="${param.wyOrAgentName}"></input></td>
		            <td width="7%"><div align="right">小区名称：</div></td>
		            <td width="13%"><input class="input_text w120 pp" type="text" id="gbName" name="gbName" value="${param.gbName}" /></td>
		            <td width="7%"><div align="right">支付时间：</div></td>
		            <td width="28%">
		            	<input type="text" class="input_text pp icon_dt" id="date01" name="payTmStart" title="请选择起始时间"  value="${param.payTmStart }"> 至 
		            	<input type="text" class="input_text pp icon_dt" id="date02" name="payTmEnd" title="请选择结束时间"  value="${param.payTmEnd }">
		            </td>
		           	<td width="5%"><div align="right">结算状态：</div></td>
		            <td width="25%">
		            	<select  id="tkStatus" name="tkStatus" class="select_normal w131">
		                    <option value="">全部</option>
			       			<option value="1" <c:if test="${param.tkStatus == 1}">selected="selected"</c:if> >未结算</option>
			       			<option value="2" <c:if test="${param.tkStatus == 2}">selected="selected"</c:if> >申请中</option>
			       			<option value="3" <c:if test="${param.tkStatus == 3}">selected="selected"</c:if> >已结算</option>
		                </select>
		            </td>
		          </tr>
		        </table>
    		</c:otherwise>
    	</c:choose>
        
        
        <table class="info-list" border="0" style="color: red;">
          <tr>
   			<c:choose>
		  	  <c:when test="${roleType == 1}">
		  	  	<td align="left" width="10%">订单总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalOrder }" pattern="#,##0.00"/></td>
		  	  	<td align="left" width="15%">收益总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountDiscount }" pattern="#,##0.00"/></td>
		  	  	<td width="45%">&nbsp;</td>
		  	  </c:when>
		  	  <c:when test="${roleType == 2}">
		  	  	<td align="left" width="10%">订单总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalOrder }" pattern="#,##0.00"/></td>
		  	  	<td align="left" width="15%">收益总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountReal }" pattern="#,##0.00"/></td>
		  	  	<td width="45%">&nbsp;</td>
		  	  </c:when>
		  	  <c:otherwise>
		  	  	<td align="left" width="10%">订单总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalOrder }" pattern="#,##0.00"/></td>
		  	  	<c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 1}">
			  	  	<td align="left" width="10%">优惠总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalOrderDiscount }" pattern="#,##0.00"/></td>
			  	  	<td align="left" width="10%">实付总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalOrderReal }" pattern="#,##0.00"/></td>
		  	  	</c:if>
		  	  	<c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 2}">
		  	  		<td align="left" width="14%">收益总额（物业）：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountDiscount }" pattern="#,##0.00"/></td>
		  	  	</c:if>
		  	  	<c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 3}">
		  	  		<td align="left" width="14%">收益总额（代理）：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountReal }" pattern="#,##0.00"/></td>
		  	  	</c:if>
		  	  	<c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 1}">
		  	  		<td align="left" width="14%">收益总额（师傅）：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmount }" pattern="#,##0.00"/></td>
		  	  	</c:if>
		  	  	<c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 4}">
		  	  		<td align="left" width="14%">收益总额（平台）：<fmt:formatNumber value="${ebuyRevenueTotal.totalPlatform }" pattern="#,##0.00"/></td>
		  	  	</c:if>
		  	  	<c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 5}">
		  	  		<td align="left" width="14%">收益总额（推荐人）：<fmt:formatNumber value="${ebuyRevenueTotal.totalRecommender }" pattern="#,##0.00"/></td>
		  	  	</c:if>
		  	  	<c:if test="${!empty param.profitObject && param.profitObject != 0 }"><td width="30%">&nbsp;</td></c:if>
		  	  </c:otherwise>
		  	</c:choose>
          </tr>
          <tr>
         	  	<td colspan="10" align="right"><input class="info-btn small-btn w100" type="button" onclick="onSubmit();" value="查 询" />&nbsp;&nbsp;&nbsp;&nbsp;
           		<input class="info-btn small-btn w100" type="button" onclick="exp();" value="导 出" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <%--
          	<td colspan="10">
          	  <table  class="info-list" border="0" cellpadding="0">
          	  	<td width="65%"></td>
          	  	<td align="right" width="25%"><input class="info-btn small-btn w100" type="button" onclick="onSubmit();" value="查 询" />&nbsp;&nbsp;&nbsp;&nbsp;
            		<input class="info-btn small-btn w100" type="button" onclick="exp();" value="导 出" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
	          	<td width="15%"></td>
          	  </table>
          	</td>
          	 --%>
          </tr>
        </table>
     </form>
     </div>
	    <display:table name="ebuyRevenueSignalAmountList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
			<display:column title="物业公司" property="revenueSignalAmountEbuy.pcName" sortable="true" />
			<display:column title="代理商" property="revenueSignalAmountEbuy.agentName" sortable="true"/>
			<display:column title="小区名称" property="revenueSignalAmountEbuy.groupBuildingName" sortable="true"/>
			<%-- <display:column title="预约解放号" property="revenueSignalAmountEbuy.huaId" sortable="true"/> --%>
			<c:if test="${roleType != 2 and roleType != 3 and roleType != 13}">
				<display:column title="订单号" property="orderNo" sortable="true" />
			</c:if>
			<display:column title="预约服务" property="revenueSignalAmountEbuy.dredgeServiceName" sortable="true"/>
			<display:column title="费用类型" property="goalDetailTypeStr" sortable="true"/>
			<display:column title="费用金额" sortable="true" style="text-align: right;">
				<fmt:formatNumber value="${row.srcMoney }" pattern="#,##0.00"/>
			</display:column>
			<c:if test="${roleType == 0 }">
			<display:column title="推荐人手机号" property="recommenderName" sortable="true"/>
			</c:if>
			<%-- <display:column title="订单金额" sortable="true" style="text-align: right;">
				<fmt:formatNumber value="${row.srcMoney }" pattern="#,##0.00"/>
			</display:column> --%>
			<c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 1}">
				<display:column title="优惠金额" sortable="true" style="text-align: right;">
					<fmt:formatNumber value="${row.revenueSignalAmountEbuy.amountDiscount }" pattern="#,##0.00"/>
				</display:column>
				<display:column title="实付金额" sortable="true" style="text-align: right;">
					<fmt:formatNumber value="${row.revenueSignalAmountEbuy.amountOrderReal }" pattern="#,##0.00"/>
				</display:column>
			</c:if>
			<display:column title="支付渠道" sortable="true">
				<c:choose>
					<c:when test="${row.revenueSignalAmountEbuy.payMethod == 1 }">微信支付</c:when>
					<c:when test="${row.revenueSignalAmountEbuy.payMethod == 2 }">支付宝</c:when>
					<c:when test="${row.revenueSignalAmountEbuy.payMethod == 3 }">银联支付</c:when>
					<c:when test="${row.revenueSignalAmountEbuy.payMethod == 4 }">纯粮票支付</c:when>
					<c:when test="${row.revenueSignalAmountEbuy.payMethod == 5 }">纯积分支付</c:when>
					<c:when test="${row.revenueSignalAmountEbuy.payMethod == 6 }">微信轻应用支付</c:when>
					<c:when test="${row.revenueSignalAmountEbuy.payMethod == 7 }">纯优惠券支付</c:when>
					<c:when test="${row.revenueSignalAmountEbuy.payMethod == 9 }">双乾支付</c:when>
				</c:choose>
			</display:column>
			<display:column title="流水号" property="payFlowNo" sortable="true" />
			<display:column title="支付时间" property="payTm" sortable="true" format="{0, date, yyyy-MM-dd HH:mm}" />
			
			<c:choose>
			  <c:when test="${roleType == 1}">
			    <display:column title="收益金额<span style='color:red'>（物业）</span>" sortable="true" style="text-align: right;">
			    	<fmt:formatNumber value="${row.amountWy }" pattern="#,##0.00"/>
			    </display:column>
			    <display:column title="结算状态" property="tkStatusWyStr" sortable="true" />
			  </c:when>
			  <c:when test="${roleType == 2}">
			    <display:column title="收益金额<span style='color:red'>（代理）</span>" sortable="true" style="text-align: right;">
			    	<fmt:formatNumber value="${row.amountAgent }" pattern="#,##0.00"/>
			    </display:column>
			    <display:column title="结算状态" property="tkStatusAgentStr" sortable="true" />
			  </c:when>
			  <c:otherwise>
				  <c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 2}">
				    <display:column title="收益金额</br><span style='color:red'>（物业）</span>" sortable="true" style="text-align: right;">
				    	<fmt:formatNumber value="${row.amountWy }" pattern="#,##0.00"/>
				    </display:column>
				  </c:if>
				    
				  <c:if test="${param.profitObject == 2}">
				    <display:column title="结算状态" property="tkStatusWyStr" sortable="true" />
				  </c:if> 
				  <c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 3}">
				    <display:column title="收益金额</br><span style='color:red'>（代理）</span>" sortable="true" style="text-align: right;">
				    	<fmt:formatNumber value="${row.amountAgent }" pattern="#,##0.00"/>
				    </display:column>
				  </c:if> 
				  <c:if test="${param.profitObject == 3}">
				    <display:column title="结算状态" property="tkStatusAgentStr" sortable="true" />
				  </c:if>
				  <c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 1}">  
				    <display:column title="收益金额</br><span style='color:red'>（师傅）</span>" sortable="true" style="text-align:right;">
				    	<fmt:formatNumber value="${row.amountRepair }" pattern="#,##0.00"/>
				    </display:column>
				  </c:if>
				  <c:if test="${param.profitObject == 1}">
				    <display:column title="结算状态" property="tkStatusRepairStr" sortable="true" />
				  </c:if>
				  <c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 4}">
				    <display:column title="收益金额</br><span style='color:red'>（平台）</span>" sortable="true" style="text-align:right;">
				    	<fmt:formatNumber value="${row.amountPlatform }" pattern="#,##0.00"/>
				    </display:column>
				  </c:if>
				  <c:if test="${param.profitObject == 4}">
				    <display:column title="结算状态" property="tkStatusPlatformStr" sortable="true" />
				  </c:if>
				  <c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 5}">   
				    <display:column title="收益金额</br><span style='color:red'>（推荐人）</span>" sortable="true" style="text-align:right;">
				    	<fmt:formatNumber value="${row.amountRecommender }" pattern="#,##0.00"/>
				    </display:column>
				  </c:if>
				  <c:if test="${param.profitObject == 5}">
				    <display:column title="结算状态" property="tkStatusRecommenderStr" sortable="true" />
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
		if($("#tkStatus").val() != '' && $("#profitObject").val() == 0) {
			 $("#profitObject").val("1");
		}
	});
	if($("#tkStatus").val() != '' && $("#profitObject").val() == 0) {
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
