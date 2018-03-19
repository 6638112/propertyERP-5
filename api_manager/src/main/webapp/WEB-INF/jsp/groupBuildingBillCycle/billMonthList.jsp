<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业管理-小区缴费管理-月度缴费管理-收费账期管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>

<body>
<div class="info">
    <h2>收费账期管理</h2>
    <div class="bs-example bgebeb">
        <form method="post" action="../groupBuildingBillCycle/billCycleList.html">
        <input type="hidden" name="paytimeType" id="paytimeType" value="1"/>
        <table class="info-list" border="0">
          <tr>
            <td><div class="list-name">小区名称：</div></td>
            <td><input type="text" class="input_text w120 pp" name="groupBuildingName" value="${groupBuildingName }"></td>
            <td><div class="list-name">账单名称：</div></td>
            <td><input type="text" class="input_text w120 pp" name="billName" value="${billName }"></td>
            <td align="right"><input class="info-btn small-btn w150" type="submit" value="查 询"><input id="createNewBill" class="weak-btn small-btn w150 mleft20" type="button" value="新增账期"></td>
          </tr>
        </table>
        </form>
    </div>     
    <display:table name="resList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
		<display:column title="小区名" property="groupBuildingName" sortable="true"/>
		<display:column title="账单名称" property="billName" sortable="true"/>
		<display:column title="账单周期">
			<c:if test="${not empty row.paytimeType and row.paytimeType==1}">${row.billMonth }</c:if>
			<c:if test="${not empty row.paytimeType and row.paytimeType==2}">${row.billMonthStart }至${row.billMonthEnd }</c:if>
		</display:column>
		<display:column title="账单缴费时间">
			${row.billPayStart }至${row.billPayEnd }
		</display:column>
		<display:column title="账期操作" media="html">
			<c:if test="${row.operateStatus != 1}">
				<a class="blue editBill" data-id="${row.id}" data-gbId="${row.tGroupBuildingId}" data-billTypeId="${row.tPayBillTypeId}" href="#">修改</a>
			</c:if>
		</display:column>
		<display:column title="操作" media="html">
			<a class="blue editBill" href="../payBill/search.html?pageType=zq&gbId=${row.tGroupBuildingId}&gbName=${row.groupBuildingName }&pbMonth=${row.billMonthStr }">查看账单详情</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${row.operateStatus != 1}">
				<a class="blue importBillMonth" data-id="${row.id}" data-gbId="${row.tGroupBuildingId}" data-month="${row.billMonthStr }" data-gbName="${row.groupBuildingName }" href="#">导入本期账单</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="blue editBill deleteAllBill" href="#"  data-href="../groupBuildingBillCycle/deleteAllBill.json?gbId=${row.tGroupBuildingId}&billTypeId=${row.tPayBillTypeId}&billTimeCfgId=1&paytimeType=1&id=${row.id}">清除未缴账单</a>
			</c:if>
			<!-- 只有抱朴物业有批量打印 -->
			<%-- <c:if test="${(row.pcId eq 50012) or (row.pmId eq 100198) or (row.pmId eq 100230)}"> --%>
				&nbsp;&nbsp;&nbsp;&nbsp;<a class="blue" href="javascript:batchPrintDialog(${row.tGroupBuildingId}, ${row.id});">批量打印账单</a>
			<%-- </c:if> --%>
		</display:column>
	</display:table>    
</div>
<div id="newBillBox" class="layer-bill dsn" style="width: 480px; height: 260px;">
	<form class="inputform" action="../groupBuildingBillCycle/saveEdit.html">
	    <input type="hidden" name="paytimeType" id="paytimeType" value="1"/>
	    <table class="info-list" border="0">
	      <tr>
	        <td width="90"><div class="list-name">小区：</div></td>
	        <td>
	        	<select id="groupBuiliding" name="gbId" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
	                <option value="">选择小区</option>
	            </select>
	        </td>
	      </tr>
	      <tr>
	        <td><div class="list-name">费用名称：</div></td>
	        <td>
	        	<select class="select_normal" name="billTypeId" id="payBillType" datatype="*" nullmsg="请选择费用类型！">
	                <option value="">选择所配置账单的费用类型</option>
	            </select>
	        </td>
	      </tr>
	      <tr>
	        <td><div class="list-name">账单周期：</div></td>
	        <td>
	        	<input class="input_text date_picker w120" name="billMonth" value="" type="text" onclick="setmonth(this)" readonly="readonly" datatype="*" nullmsg="请选择账单周期！">
	        </td>
	      </tr>
	      <tr>
	        <td><div class="list-name">缴费窗口：</div></td>
	        <td><input type="text" class="input_text icon_dt" name="billPayStart" id="date03" title="请选择起始时间" placeholder="请选择起始时间" datatype="*" nullmsg="请选择缴费窗口！"> 至<input type="text" class="input_text icon_dt" name="billPayEnd" id="date04" title="请选择起始时间" placeholder="请选择起始时间" datatype="*" nullmsg="请选择缴费窗口！"></td>
	      </tr>
	      <tr>
	        <td colspan="2" align="center"><input id="saveNewBillBtn" class="info-btn small-btn w150" type="button" value="保 存"></td>
	      </tr>
	    </table>
    </form>
</div>


<div id="editBillBox" class="layer-bill dsn" style="width: 480px; height: 260px;">
	<form class="inputform" action="../groupBuildingBillCycle/saveEdit.html">
	   	<input type="hidden" name="id" id="billCycleId"/>
	   	<input type="hidden" name="billTypeId" id="billTypeId"/>
	   	<input type="hidden" name="paytimeType" value="1"/>
	   	<input type="hidden" name="gbId" id="gbId"/>
	   	<input type="hidden" name="isHasPayBill" id="isHasPayBill"/>
	    <table class="info-list" border="0">
	      <tr>
	        <td width="90"><div class="list-name">小区：</div></td>
	        <td id="groupBuiliding_edit"></td>
	      </tr>
	      <tr>
	        <td><div class="list-name">费用名称：</div></td>
	        <td id="billName"></td>
	      </tr>
	      <tr>
	        <td><div class="list-name">账单周期：</div></td>
	        <td id="billMonth"></td>
	      </tr>
	      <tr>
	        <td><div class="list-name">缴费窗口：</div></td>
	        <td><input type="text" class="input_text icon_dt pp billPayStart" name="billPayStart" id="date03" title="请选择起始时间" placeholder="请选择起始时间" value="" datatype="*" nullmsg="请选择缴费窗口！"> 至<input type="text" class="input_text icon_dt pp billPayEnd" name="billPayEnd" id="date04" title="请选择起始时间" placeholder="请选择起始时间" value="" datatype="*" nullmsg="请选择缴费窗口！"></td>
	      </tr>
	      <tr>
	        <td colspan="2" align="center"><input id="saveNewBillBtn" class="info-btn small-btn w150" type="button" value="保 存"></td>
	      </tr>
	    </table>
    </form>
</div>
<div class="layer-bill importBillMonthDiv dsn" style="height: auto;padding-top:15px;padding-bottom:50px;">
  <form class="inputform1" enctype="multipart/form-data" action="../payBill/importPayBill02.html" method="post"> 
    <input id="billCycleIdImport" name="billCycleId" type="hidden"/>
    <h2>第一步:下载账单模板</h2>
    <div class="bs-example bgebeb">
	<table class="info-list" border="0">
         <tr>
           <td style="width: 60px;"><div class="list-name">小区</div></td>
           <td colspan="3" class="groupBuildingImport"></td>
         </tr>
         <tr>
           <td><div class="list-name">账单月份</div></td>
           <td class="billMonthImport"></td>
           <td></td>
		   <td><a id="downLoadBtn" class="blue" href="#"><img src="../images/download-icon.jpg" /> 下载账单模版</a></td>
         </tr>
     </table>
     </div>
     <h2>第二步:上传账单数据</h2>
     <div class="bs-example bgebeb">
     <table class="info-list" border="0">
         <tr>
           <td>
		         <input id="excelFileInput" name="excelFile" type="file" style="width: 300px;" title="上传新账单" nullmsg="请选择缴费窗口！"/>
           </td>
           <td><input id="uploadBtn02" class="input-btn w160" type="button" value="上传" /></td>
         </tr>
     </table>
     <span class="red dsn" id="uploadTips">账单正在上传中，请稍候…</span>
     </div>
	</form>
</div>
<div class="batchPrintDialog dsn" style="height: auto;padding-top:15px;padding-bottom:50px;width: 300px;">
	<input id="print_gbId" type="hidden" value=""/>
	<input id="print_gbbcId" type="hidden" value=""/>
	 <h2 style="margin-left: 1em;">选择纸张类型</h2>
     <div class="bs-example bgebeb">
	     <table class="info-list" border="0">
	     	 <tr>
	     	 	<td>
	     	 		<label><input type="radio" name="pageSize" value="A4" checked="checked"/>A4</label>
	     	 	</td>
	     	 </tr>
	     	 <tr>
	     	 	<td>
	     	 		<label><input type="radio" name="pageSize" value="A4_Half"/>复写纸</label>
	     	 	</td>
	     	 </tr>
	         <tr>
	           <td style="text-align: right;">
	           	   <input class="input-btn w160" type="button" value="批量打印" onclick="batchPrint();"/>
	           	   <input class="input-btn w160" type="button" value="取消" onclick="closeBatchPrintDialog();"/>
	           </td>
	         </tr>
	     </table>
     </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/DatePicker.js"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript">
$(function(){
	$('.select2_class').select2();
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		btnSubmit:"#saveNewBillBtn"
	});
    
    $("#uploadBtn02").click(function(){
    	if($("#excelFileInput").val() == "") {
    		alert("请选择excel！");
    		return false;
    	} else {
    		$("#uploadBtn02").attr("disabled",true);
    		$("#uploadBtn02").css({"background":"#DCDCDC","color":"#FFFFFF","border":"0px","cursor":"wait"});
    		$(".inputform1").submit();
    	}
    });

	$(".inputform1").Validform({
		tiptype:3,
		btnSubmit:"#uploadBtn"
	});
	
	//新增账单弹出层
	$('#createNewBill').click(function(){
		$.ajax({
			  url: '../groupBuildingBillCycle/initEdit.html',
			  dataType: 'json',
			  data: {'paytimeType':1},
			  success: function(data){
				  var list = JSON.parse(data).gbList;
				  var strHtml = "<option value='''>选择小区</option>";
				  $.each(list, function(i, item) {
					  strHtml += "<option value='" + item.id + "'>" + item.name + "</option>";
				  });
				  $("#groupBuiliding").html(strHtml);
				  $("#paytimeType").val(JSON.parse(data).paytimeType);
				  $.layer({
						type: 1,
						shade: [0.4,'#000000'],
						area: ['auto', 'auto'],
						title: false,
						border : [5, 0.3, '#000'],
						page: {dom : '#newBillBox'}
				  });
			  }
		});
	});
	
	$("#groupBuiliding").change(function(){
		var gbId = $("#groupBuiliding").val();
		if(gbId==null || gbId=="") {
			return false;
		}
		$.ajax({
			  url: '../groupBuildingBillCycle/selectBillTypeList.html',
			  dataType: 'json',
			  data: {'gbId':gbId,'paytimeType':$("#paytimeType").val()},
			  success: function(data){
				  var strHtml = "<option value='''>选择所配置账单的费用类型</option>";
				  $.each(JSON.parse(data).billTypeList, function(i, item) {
					  strHtml += "<option value='" + item.id + "'>" + item.name+ "</option>";
				  });
				  $("#payBillType").html(strHtml);
			  }
		});
	});
	$("#excelFileInput").change(function(){
		$(this).siblings('input').val('1');
	});
	
	//修改账期弹出层
	$('.editBill').click(function(){
		var id = $(this).attr("data-id"); 
		var gbId = $(this).attr("data-gbId");
		var paytimeType = 1;
		$.ajax({
			  url: '../groupBuildingBillCycle/initEdit.html',
			  dataType: 'json',
			  data: {'paytimeType':paytimeType,"gbId":gbId,"id":id},
			  success: function(data){
				  var gbc = JSON.parse(data).gbc;
				  var isHasPayBill = JSON.parse(data).isHasPayBill;
				  $("#groupBuiliding_edit").html(gbc.groupBuildingName);
				  $("#billName").html(gbc.billName);
				  if(isHasPayBill) {
					  $("#billMonth").html(gbc.billMonthStr+"<input type='hidden' name='billMonth' id='billMonth' value='"+gbc.billMonth+"'/>");
				  } else {
					  $("#billMonth").html("<input class='input_text date_picker w120 pp' name='billMonth' value='"+gbc.billMonth+"' type='text' onclick='setmonth(this)' datatype='*' nullmsg='请选择账单周期！'>");
				  }
				  $(".billPayStart").val(gbc.billPayStart);
				  $(".billPayEnd").val(gbc.billPayEnd);
				  $("#billCycleId").val(gbc.id);
				  $("#gbId").val(gbc.tGroupBuildingId);
				  $("#billTypeId").val(gbc.tPayBillTypeId);
				  $("#paytimeType").val(gbc.paytimeType);
				  $("#isHasPayBill").val(isHasPayBill);
				  $.layer({
						type: 1,
						shade: [0.4,'#000000'],
						area: ['auto', 'auto'],
						title: false,
						border : [5, 0.3, '#000'],
						page: {dom : '#editBillBox'}
				 });
			  }
		});
	});
	
	//全局变量，下载模板使用
	var billCycleId;//账单id
	var groupbuildingId;//小区id
	var groupbuildingName;//小区名称
	var billMonth;//账单月份
	//导入账期弹出层
	$('.importBillMonth').click(function(){
		groupbuildingId = $(this).attr("data-gbId");
		groupbuildingName = $(this).attr("data-gbName");
		billMonth = $(this).attr("data-month");
		billCycleId = $(this).attr("data-id");
		$(".groupBuildingImport").text(groupbuildingName);
		$(".billMonthImport").text(billMonth);
		$("#billCycleIdImport").val(billCycleId);
		$.layer({
			type: 1,
			shade: [0.4,'#000000'],
			area: ['auto', 'auto'],
			title: false,
			border : [5, 0.3, '#000'],
			page: {dom : '.importBillMonthDiv'}
	 	});
	});
	
	$("#downLoadBtn").click(function() {
		if(groupbuildingId==null || groupbuildingId==''){
			alert('您当前没有选中导出小区，不能下载模板!');
			return false;
		}
		location.href=  "../payBill/exportPayBillTemplate02.html?groupbuildingId="+groupbuildingId+"&erportMonth="+billMonth+"&billCycleId="+billCycleId+"&groupbuildingName="+groupbuildingName;
	});
	
	//清除未缴账单
	$('a.deleteAllBill').click(function(){
		$.ajax({
			  url: $(this).attr('data-href'),
			  dataType: 'json',
			  success: function(data,status){
				 alert(data.message);
			  }
		});
	})	
});

var layerPrint;
function batchPrintDialog(gbId, gbbcId){
	$("#print_gbId").val(gbId);
	$("#print_gbbcId").val(gbbcId);
	layerPrint = $.layer({
		type: 1,
		shade: [0.4,'#000000'],
		area: ['auto', 'auto'],
		title: false,
		border : [5, 0.3, '#000'],
		page: {dom : '.batchPrintDialog'}
 	});
}

function closeBatchPrintDialog(){
	layer.close(layerPrint);
}

function batchPrint(){
	var gbId = $.trim($("#print_gbId").val());
	var gbbcId = $.trim($("#print_gbbcId").val());
	var pageSize = $(":radio[name='pageSize']:checked").val();
	window.open("${pageContext.request.contextPath}/payBillPrint/batchPrintByGbId.html?gbId="+gbId+"&gbbcId="+gbbcId+"&pageSize="+pageSize+"&feeType=${feeType}");
}
</script>
</html>
