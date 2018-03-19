<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>理财宝收益明细</title>
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
	location.href = "../revenueFinance/exportExcel.html";
}

function onSubmit() {
	$("#searchForm").attr("action", "../revenueFinance/carFinanceList.html");
	$("#searchForm").submit();
}
</script>
</head>

<body>
<div class="info">
    <h2>停车宝收益明细</h2>
	
    <div class="bs-example bgebeb">
    <form id="searchForm" name="form_main" method="post">
    	<input type="hidden" id="applyId" name="applyId" value="${param.applyId}"></input>
    	<input type="hidden" id="roleId" name="roleId" value="${param.roleId}" />
        <table class="info-list" border="0">
          <tr>
            <td><div align="right">物业公司/代理商：</div></td>
            <td><input class="input_text w120 pp" type="text" id="wyOrAgentName" name="wyOrAgentName" value="${param.wyOrAgentName}"></input></td>
            <td><div align="right">小区名称：</div></td>
		  	<td>
			  <select id="groupBuiliding" name="gbName" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
				  <option value="">选择小区</option>
			  </select>
		  	</td>
            <td><div align="right">回款时间：</div></td>
            <td>
            	<input type="text" class="input_text pp icon_dt" id="date01" name="goalRevTimeStart" title="请选择起始时间"  value="${param.goalRevTimeStart }"> 至 
            	<input type="text" class="input_text pp icon_dt" id="date02" name="goalRevTimeEnd" title="请选择结束时间"  value="${param.goalRevTimeEnd }">
            </td>
           	<td><div align="right">结算状态：</div></td>
            <td>
            	<select  id="tkStatus" name="tkStatus" class="select_normal w131">
                    <option value="">全部</option>
	       			<option value="1" <c:if test="${param.tkStatus == 1}">selected="selected"</c:if> >未结算</option>
	       			<option value="2" <c:if test="${param.tkStatus == 2}">selected="selected"</c:if> >申请中</option>
	       			<option value="3" <c:if test="${param.tkStatus == 3}">selected="selected"</c:if> >已结算</option>
                </select>
            </td>
            <c:if test="${roleType == 0}">
	            <td><div align="right">收益对象：</div></td>
			    <td>
	            	<select  id="profitObject" name="profitObject" class="select_normal w131">
	                    <option value="0">全部</option>
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
	  	  	<td align="left">投资总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalOrder }" pattern="#,##0.00"/></td>

  			<c:choose>
		  	  <c:when test="${roleType == 1}">
		  	  	<td align="left">收益总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountDiscount }" pattern="#,##0.00"/></td>
		  	  	<td >&nbsp;</td>
		  	  </c:when>
		  	  <c:when test="${roleType == 2}">
		  	  	<td align="left">收益总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountReal }" pattern="#,##0.00"/></td>
		  	  	<td>&nbsp;</td>
		  	  </c:when>
		  	  <c:otherwise>
		  	  	<%-- <td align="left" width="15%">收益总额（物业）：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountDiscount }" pattern="#,##0.00"/></td>
		  	  	<td width="22%">收益总额（代理）：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountReal }" pattern="#,##0.00"/></td>
		  	  	<td width="23%">收益总额（平台）：<fmt:formatNumber value="${ebuyRevenueTotal.totalPlatform }" pattern="#,##0.00"/></td> --%>
		  	  	
		  	  	<c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 2}">
		  	  		<td align="left">收益总额（物业）：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountDiscount }" pattern="#,##0.00"/></td>
		  	  	</c:if>
		  	  	<c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 3}">
		  	  		<td align="left">收益总额（代理）：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmountReal }" pattern="#,##0.00"/></td>
		  	  	</c:if>
		  	  	<c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 4}">
		  	  		<td align="left">收益总额（平台）：<fmt:formatNumber value="${ebuyRevenueTotal.totalPlatform }" pattern="#,##0.00"/></td>
		  	  	</c:if>
		  	  </c:otherwise>
		  	</c:choose>
          </tr>
        </table>
     </form>
     </div>
        <div class="t_center mtop10"><input class="info-btn small-btn w100" type="button" onclick="onSubmit();" value="查 询" />&nbsp;&nbsp;&nbsp;&nbsp;
            	<input class="info-btn small-btn w100" type="button" onclick="exp();" value="导 出" />&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
	    <display:table name="financeRevenueSignalAmountList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
			<display:column title="物业公司" property="financeBuyEntity.propertyCompanyName" sortable="true" />
			<display:column title="代理商" property="financeBuyEntity.channelPartnerName" sortable="true"/>
			<display:column title="城市" property="financeBuyEntity.financeReqEntity.city" sortable="true"/>
			<display:column title="小区" property="financeBuyEntity.financeReqEntity.residential" sortable="true"/>
			<display:column title="车牌号" property="financeBuyEntity.carNum" sortable="true"/>
			<display:column title="订单号" property="financeBuyEntity.orderNo" sortable="true"></display:column>
			<display:column title="投资金额" sortable="true" style="text-align: right;">
				<fmt:formatNumber value="${row.financeBuyEntity.buyMoney }" pattern="#,##0.00"/>
			</display:column>
			<display:column title="回款时间" property="goalRevTimeDate" sortable="true" format="{0, date, yyyy-MM-dd HH:mm}" />
			<c:choose>
			  <c:when test="${roleType == 1}">
			    <display:column title="收益金额（物业）" sortable="true" style="text-align: right;" >
			    	<fmt:formatNumber value="${row.amountWy }" pattern="#,##0.00"/>
			    </display:column>
			    <display:column title="结算状态" property="tkStatusWyStr" sortable="true" />
			  </c:when>
			  <c:when test="${roleType == 2}">
			    <display:column title="收益金额（代理）" sortable="true" style="text-align: right;" >
			    	<fmt:formatNumber value="${row.amountAgent }" pattern="#,##0.00"/>
			    </display:column>
			    <display:column title="结算状态" property="tkStatusAgentStr" sortable="true" />
			  </c:when>
			  <c:otherwise>
			    <%-- <display:column title="收益金额<span style='color:red'>（物业）</span>" sortable="true" style="text-align: right;">
			    	<fmt:formatNumber value="${row.amountWy }" pattern="#,##0.00"/>
			    </display:column>
			    <display:column title="结算状态" property="tkStatusWyStr" sortable="true" />
			    <display:column title="收益金额<span style='color:red'>（代理）</span>" sortable="true" style="text-align: right;">
			    	<fmt:formatNumber value="${row.amountAgent }" pattern="#,##0.00"/>
			    </display:column>
			    <display:column title="结算状态" property="tkStatusAgentStr" sortable="true" />
			    <display:column title="收益金额<span style='color:red'>（平台）</span>" sortable="true" style="text-align: right;">
			    	<fmt:formatNumber value="${row.amountPlatform }" pattern="#,##0.00"/>
			    </display:column> --%>
			    
			    
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
				  <c:if test="${empty param.profitObject || param.profitObject == 0 || param.profitObject == 4}">
				    <display:column title="收益金额</br><span style='color:red'>（平台）</span>" sortable="true" style="text-align:right;">
				    	<fmt:formatNumber value="${row.amountPlatform }" pattern="#,##0.00"/>
				    </display:column>
				  </c:if>
				  <c:if test="${param.profitObject == 4}">
				    <display:column title="结算状态" property="tkStatusPlatformStr" sortable="true" />
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
<script type="text/javascript" >
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
