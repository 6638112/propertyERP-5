<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账单查询</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/metro/easyui.css?v20150306">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/icon.css">
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/revenue/layer.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js?v2"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>

<script type="text/javascript">
function freshCurrPage(){
	$("#searchForm").submit();//刷新页面
}
function toConfigAdd(){
	layer.open({
	    type: 2,  
	    shadeClose: false,  //true点击遮罩关闭层
	    title: false,  
	    closeBtn: [0, true],  
	    shade: [0.2, '#000'],  
	    border: [0],  
	    offset: ['15px',''], // top、left 
	    area: ['50%', '65%'], //宽高
		content:'addAcountPage.html',
		end:function(){
			freshCurrPage();
	    }
	});
}

function printNull() {
	window.open("<%=basePath%>/payBill/print.html");
}
</script>
</head>

<body>
<div class="info">
    <h2>账单查询</h2>
    <div class="bs-example bgebeb">
    <form id="searchForm" name="form_main" action="<%=basePath%>/payBill/acountList.html" method="post">
        <table class="info-list" border="0">
          <tr>
          	<td width="5%"><div align="right">小区：</div></td>
            <td width="15%"><input class="input_text w120 pp" type="text" name="groupBuild" value="${param.groupBuild}"></input></td>
            <td width="10%"><div align="right">楼栋：</div></td>
            <td width="15%"><input class="input_text w120 pp" type="text" name="building" value="${param.building}" /></td>
            <td width="10%"><div align="right">单元号：</div></td>
            <td width="15%"><input class="input_text w120 pp" type="text" name="unitName" value="${param.unitName}" /></td>
            <td width="10%"><div align="right">房号：</div></td>
            <td width="20%"><input class="input_text w120 pp" type="text" name="roomNum" value="${param.roomNum}" /></td>
          </tr>
           <tr>
          	<td width="5%"><div align="right">账单名称：</div></td>
            <td width="15%"><input class="input_text w120 pp" type="text" name="accountName" value="${param.accountName}"></input></td>
            <td width="10%"><div align="right">账单月份：</div></td>
            <td width="15%"><input class="input_text w120 pp" type="text" name="accountMonth" value="${param.accountMonth}" /></td>
          </tr>
        </table>
        <table class="info-list" border="0">
          <tr>
            <td width="70%" align="right"></td>
            <td align="30%">
	            <input class="info-btn small-btn w100" type="submit" value="查询账单"  />
	            <input class="info-btn small-btn w100" type="button" value="录入账单" onclick="toConfigAdd();" />
	            <input class="info-btn small-btn w100" type="button" value="打印空白账单" onclick="printNull();" />
            </td>
          </tr>
        </table>
     </form>
     </div>
	    <display:table name="feePrintList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
			<display:column title="小区" property="realRoomEntity.buildingEntity.groupBuildingEntity.name" sortable="true"/>
			<display:column title="楼栋" property="realRoomEntity.buildingEntity.name" sortable="true"/>
			<display:column title="单元号" property="realRoomEntity.unitName" sortable="true"></display:column>
			<display:column title="房号" property="realRoomEntity.numTail" sortable="true"></display:column>
			<display:column title="账单名称" property="accountName" sortable="true"></display:column>
			<display:column title="账单月份" property="accountMonth" sortable="true"></display:column>
			<display:column title="缴费金额" property="account" sortable="true"></display:column>
			<display:column title="缴费时间" property="payTm" sortable="true" format="{0, date, yyyy-MM-dd HH:mm}" />
			<display:column title="操作" >
			<a class="blue" target="_blank" href="<%=basePath%>/payBill/print.html?feePrintId=${row.id}" >打印</a>
		</display:column>
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
