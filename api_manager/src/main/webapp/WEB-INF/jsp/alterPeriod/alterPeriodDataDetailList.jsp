<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="com.cnfantasia.server.ms.pub.session.UserContext" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择周期数据详情</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
	<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <h2>${gbName}-收费项基础数据管理</h2>
    <div class="bs-example bgebeb">
    	<form method="post" action="<%=basePath%>/alterPeriod/listDataDetail.html">
	        <table class="info-list" border="0">
	          <tr>
				  <input type="hidden" name="gbId" value="${param.gbId}">
	            <td><div align="right">楼栋：</div></td>
	            <td><input type="text" value="${param.bName }" class="input_text pp w120" name="bName" /></td>
	            <td><div align="right">单元：</div>
	            </td>
	            <td>
	          		<input type="text" class="input_text pp w120" value="${param.unitName }" name="unitName" />
	            </td>
	            <td><div align="right">房号：</div>
	            </td>
	            <td>
	          		<input type="text" class="input_text pp w120" value="${param.roomNumTail }" name="roomNumTail" />
	            </td>
	            
				<td></td>
	            <td>
	            	<input class="input-btn w80" type="submit" value="搜索" />
	            	<input class="input-btn w80" type="button" onclick="showImport(this);" value="导入数据" />
	            	<input class="input-btn w80" type="button" onclick="clearAll('${param.gbId}');" value="清除数据" />
	            </td>
	            <td></td>
	            <td></td>
	          </tr>
	        </table>
    	</form>
    </div>

    <display:table name="resList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="total" >

		<display:column title="小区名称" property="gbName"/>
		<display:column title="楼栋" property="bName" />
		<display:column title="单元" property="unitName" />
		<display:column title="房号" property="roomNumTail" />

		<c:set var="totalFee" value="0"/>
		<c:forEach items="${feeItems}" var="feeItem">
			<display:column title="${feeItem.name}">
				<c:set var="hasThis" value="0"/>
				<c:forEach items="${row.hasFeeItemList}" var="hasFeeItem">
					<c:if test="${hasFeeItem.tAlterPeriodFeeItemId == feeItem.id}">
						<input type="hidden" value="${feeItem.id}" name="${row.alterPeriodDataId}_feeItemId">
						<input type="text" id="${row.alterPeriodDataId}_${feeItem.id}" class="input_text w50 pp" value="${hasFeeItem.amount div 100}" datatype="numberFixTwo">
						<c:set value="${totalFee + hasFeeItem.amount}" var="totalFee"/>
						<c:set var="hasThis" value="1"/>
					</c:if>
				</c:forEach>
				<c:if test="${hasThis == 0}">
					<input type="hidden" value="${feeItem.id}" name="${row.alterPeriodDataId}_feeItemId">
					<input type="text" id="${row.alterPeriodDataId}_${feeItem.id}" class="input_text w50 pp" datatype="numberFixTwo">
				</c:if>
			</display:column>
		</c:forEach>
		<display:column title="合计">${totalFee div 100}<input type="hidden" value="${row.realRoomId}" id="${row.alterPeriodDataId}_realRoomId"></display:column>
		<display:column title="物业费起始时间">
			<input type="text" onclick="setmonth(this)" id="begintime" value="${fn:substring(row.billMonthStart, 0, 7)}" class="${row.alterPeriodDataId}_billMonthStart pp input_text icon_dt" id="date01" placeholder="请选择起始时间" datatype="*" nullmsg="请选择起始时间！" readonly="readonly"/>
		</display:column>
		<display:column title="滞纳金起算时间">
			<input type="text" id="date04" value="${fn:substring(row.latefeeStart, 0, 10)}" class="${row.alterPeriodDataId}_latefeeStart pp input_text icon_dt" id="date02" readonly="readonly"/>
		</display:column>
		<display:column title="滞纳金金额" property="latefeeAmount" />
		<display:column title="操作" >
			<input class="input-btn w45 import-layer-bill-btn pp" onclick="savePeriodData(${row.alterPeriodDataId})" value="保存" type="button"/>
			<input class="input-btn w90 import-layer-bill-btn pp" onclick="resetLatefeeAmount(${row.alterPeriodDataId})" value="滞纳金归零" type="button"/>
		</display:column>
	</display:table>
</div>

<div class="layer-bill dsn">
   <a id="downLoadBtn" class="blue" href="javascript:exportExcelModel();"><img src="../images/download-icon.jpg" /> 第1步：下载数据导入模版</a>
   <br />
   <form id="inputFileForm" class="mtop10" enctype="multipart/form-data" action="../alterPeriod/importDataDetail.html" method="post">
   	 <input type="hidden" name="gbId" value="${param.gbId }" />
   	 <input type="hidden" id="gbName" name="groupBuildingName" value="" />
     <span class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第2步：请选择要上传的文件</span>
     <br/> 
     <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="file" id="excelFileInput" name="excelFile" size="50" title="上传楼栋" /></div>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="input-btn w120 mtop20" type="button" id="uploadBtn02" onclick="submitValidate();" value="上传" />
   </form>
</div>

</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js?v20160419"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js?v20160419"></script>
<script type="text/javascript" src="../js/DatePicker.js?v20160419"></script>
<script type="text/javascript">
//导入账单弹出层
	function showImport(ths){
		var $this = $(ths);
		$('#gbId').val($this.attr('gbId'));
		$('#gbName').val($this.attr('gbName'));
		
		$.layer({
			type: 1,
			shade: [0.4,'#000000'],
			area: ['auto', 'auto'],
			title: false,
	    	border : [5, 0.3, '#000'],
			page: {dom : '.layer-bill'}
		});
	}
	function submitValidate(){
		var fullFileName = $("#excelFileInput").val(); //选中文件的全名，包含路径
		var d = fullFileName.length - ".xls".length;
		if(fullFileName == ""){
			alert("请先选中一个上传文件");
			return false;
		}else if(fullFileName.indexOf(".xls") !=d){ 
			alert("上传的必须是后缀名为xls的Excel文件，请先下载模板进行编辑后再上传");
			return false;			
		}
		$("#uploadBtn02").attr("disabled",true);
		$("#uploadBtn02").css({"background":"#DCDCDC","color":"#FFFFFF","border":"0px","cursor":"wait"});
		$("#inputFileForm").submit();
	}
	
	function exportExcelModel(ths){
		location.href= "../alterPeriod/exportTemplate.html?gbId=" + ${param.gbId};
	}
//重置滞纳金
function resetLatefeeAmount(id) {
	$.ajax({
		url: 'resetLatefeeAmount.json',
		data: {
			"id": id
		},
		type: 'post',
		cache: false,
		dataType: 'json',
		success: function (data) {
			if (data.status == '0000') {
				alert('重置滞纳金成功');
				window.location.reload();
			} else {
				//console.log(JSON.stringify(data));
				//$("#processMsg").html(data.message);
				alert(data.message);
			}
		},
		error: function () {
		}
	});
}

	//保存修改的选择账期
	function savePeriodData(dataId) {
		var reg = /^[0-9]+(\.[0-9]{0,2})?$/;
		var billMonthStart = $('.'+ dataId + '_billMonthStart').val();
		var latefeeStart = $('.'+ dataId + '_latefeeStart').val();
		var realRoomId = $('#' + dataId + "_realRoomId").val();

		var tmp = dataId + "_feeItemId";
		var data = {};
		var hasItems = [];
		var isFeeRight = true;
		$('input[name='+tmp+']').each(function(index,item){
			var itemId = $(item).val();
			hasItems.push(itemId);
			var feeValue = $('#' + dataId + '_' + itemId).val();
			if (reg.test(feeValue)){
				data[itemId] = feeValue;
			} else {
				isFeeRight = false;
			}
		});

		if (!isFeeRight) {
			alert("请填写正确费用！");
			return;
		}
		data['alterPeriodDataId'] = dataId;
		data['billMonthStart'] = billMonthStart;
		data['realRoomId'] = realRoomId;
		data['latefeeStart'] = latefeeStart;
		data['feeItems'] = hasItems;
		$.ajax({
			url: 'updRoomAlterPeriod.json',
			data: data,
			type: 'post',
			cache: false,
			dataType: 'json',
			success: function (data) {
				if (data.status == '0000') {
					alert('修改成功');
					window.location.reload();
				} else {
					alert(data.message);
				}
			}
		});

	}

	/**
	 * 清除小区数据
	 */
	function clearAll(gbId) {
		if(confirm("确认清除？")) {
			$.ajax({
				type: "POST",
				url: '${pageContext.request.contextPath}/propertyPayConfig/deleteAllDetail.json',
				data: {gbId: gbId},
				dataType: 'json',
				success: function (data, status) {
					alert(data.message);
					document.location.reload();
				}
			});
		}
	}
</script>

</html>
