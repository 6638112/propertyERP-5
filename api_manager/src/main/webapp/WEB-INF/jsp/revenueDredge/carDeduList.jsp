<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>停车宝抵扣明细</title>
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
	location.href = "<%=basePath%>/revenueCarDedu/exportExcel.html";
}

function onSubmit() {
	$("#searchForm").attr("action", "<%=basePath%>/revenueCarDedu/carDeduList.html");
	$("#searchForm").submit();
}
</script>
</head>

<body>
<div class="info">
    <h2>停车宝抵扣明细</h2>
    <div class="bs-example bgebeb">
    <form id="searchForm" name="form_main" action="<%=basePath%>/revenueCarDedu/carDeduList.html" method="post">
    	<input type="hidden" id="applyId" name="applyId" value="${param.applyId}"></input>
    	<input type="hidden" id="roleId" name="roleId" value="${param.roleId}" />
        <table class="info-list" border="0">
          <tr>
            <td width="7%"><div align="right">物业公司：</div></td>
            <td width="13%"><input class="input_text w120 pp" type="text" id="wyName" name="wyName" value="${param.wyName}"></input></td>
            <td width="7%"><div align="right">小区：</div></td>
		  	<td width="13%">
			  <select id="groupBuiliding" name="gbName" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
				  <option value="">选择小区</option>
			  </select>
		  	</td>
            <td width="7%"><div align="right">抵扣月份：</div></td>
            <td width="28%">
            	<input type="text" id="begintime" class="input_text w120" value="${param.monthStart }" title="如：2014-07" name="monthStart" type="text" onclick="setmonth(this)" readonly="readonly"/>
            	至<input type="text" id="endtime" class="input_text w120" value="${param.monthEnd }" title="如：2014-07" name="monthEnd" type="text" onclick="setmonth(this)" readonly="readonly"/>
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
        <table class="info-list" border="0" style="color: red;">
          <tr>
	  	  	<td align="left" width="15%">收益总额：<fmt:formatNumber value="${ebuyRevenueTotal.totalAmount }" pattern="#,##0.00"/></td>
	  	  	<td width="45%">&nbsp;</td>
	        
            <td align="left" width="25%"><input class="info-btn small-btn w100" type="button" onclick="onSubmit();" value="查 询" />&nbsp;&nbsp;&nbsp;&nbsp;
            	<input class="info-btn small-btn w100" type="button" onclick="exp();" value="导 出" />&nbsp;&nbsp;&nbsp;&nbsp; </td>
          	<td></td>
          </tr>
        </table>
     </form>
     </div>
	    <display:table name="carDeduRevenueSignalAmountList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
			<display:column title="物业公司" property="roleName" sortable="true" />
			<display:column title="小区名称" property="gbName" sortable="true"/>
			<display:column title="车牌号" property="carNum" sortable="true"/>
			<display:column title="订单号" property="orderNo" sortable="true"/>
			<display:column title="抵扣金额" sortable="true" style="text-align: right;">
				<fmt:formatNumber value="${row.amount }" pattern="#,##0.00"/>
			</display:column>
			<display:column title="抵扣月份" property="goalRevTimeStr" sortable="true" ></display:column>
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
