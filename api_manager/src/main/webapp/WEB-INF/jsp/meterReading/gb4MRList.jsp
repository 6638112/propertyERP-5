<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>抄表收费设置</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jRating.jquery.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <h2>抄表收费项管理<span class="f12 mleft10">抄表收费流程：1.配置收费项-->2.配置缴费周期-->3.导入抄表数据-->4.系统自动生成账单</span></h2>
    <div class="bs-example bgebeb">
    	<form action="listGb.html" method="post">
        <table class="info-list citySelect" border="0">
          <tr>
            <td width="90">小区名称：</td>
            <td width="180"><input name="gbName" type="text" class="input_text pp w120" value="${param.gbName }"></td>
            <td><input class="input-btn w200" type="submit" value="查询"></td>
          </tr>
        </table>
  		</form>
    </div>
    
    <display:table class="info-list-02 mtop20" cellpadding="0" cellspacing="1"  name="resList" id="row" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize"> 
    	<display:column title="小区名称" property="name" />
    	<display:column title="操作" > 
    		<a class='blue' href="mrFeeItemEdit.html?gbId=${row.id }&gbName=${row.name}">收费配置</a>&nbsp;&nbsp;
    		<a class="blue" href="../groupBuildingBillCycle/billCycleList.html?gbId=${row.id }&feeType=1">缴费周期</a>&nbsp;&nbsp;
    		<a class='blue' href="../payBill/listPeriod.html?pageType=zq&gbId=${row.id }&gbName=${row.name}&pbtIsPropfee=3&paymentWay=-1&feeType=1&cycleType=1">账单查询</a>
    	</display:column>
    </display:table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.cxselect.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/provinceCityBlock.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>

<script>
	function toConfigAdd(dwId){
		//iframe层
		$.layer({
		  type: 2,
		  shadeClose: true,
		  title: false,
		  closeBtn: [0, false],
		  shade: [0.5, '#000'],
		  border: [0],
		  offset: ['20px',''],
		  area: ['650px', ($(window).height() - 450) +'px'],
		  iframe: {src: 'configAdd.html?dwId=' + dwId}
		}); 
	}
</script>
</html>
