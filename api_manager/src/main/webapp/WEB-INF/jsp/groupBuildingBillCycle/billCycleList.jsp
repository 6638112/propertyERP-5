<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="../error/404.jsp"%>
<%@page import="com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager" %>
<%@page import="com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", -10);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>物业管理-小区缴费管理-月度缴费管理-收费账期管理</title>
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="stylesheet" type="text/css" href="../css/style.css">
	<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
	<link rel="stylesheet" type="text/css" href="../css/select2.css">
	<style>
		.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 155px 3px;}
		.w180{width: 175px;}
	</style>
</head>
<body>
<div class="info">
    <h2>
		<c:if test="${feeType == null || feeType == ''}">
			收费账单配置
		</c:if>
		<c:if test="${feeType != null}">
			收费周期配置
		</c:if>
	</h2>
    <div class="bs-example bgebeb">
        <form method="post" class="query_form" action="../groupBuildingBillCycle/billCycleList.html">
			<input type="hidden" name="feeType" value="${feeType}"/>
        <table class="info-list" border="0">
          <tr>
		  	<td width=""><div class="list-name">小区名称：</div></td>
		  	<td>
			  <select id="groupBuiliding_select" name="gbId" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
				  <option value="">选择小区</option>
			  </select>
		  	</td>

            <td><div class="list-name">账单名称：</div></td>
            <td><input type="text" class="input_text w120 pp" name="billName" value="${billName }"></td>
            <td><div class="list-name">账单周期：</div></td>
            <td colspan="2">
				<c:choose>
					<c:when test="${not empty billCycleStart}">
						<input class="input_text date_picker w120 ept" name="billCycleStart" value="${billCycleStart}" type="text" title="请选择起始时间" placeholder="请选择起始时间" onclick="setmonth(this)" readonly="readonly" datatype="*" nullmsg="请请选择起始时间！">
					</c:when>
					<c:otherwise>
						<input class="input_text date_picker w120 ept" name="billCycleStart" value="${param.billCycleStart}" type="text" title="请选择起始时间" placeholder="请选择起始时间" onclick="setmonth(this)" readonly="readonly" datatype="*" nullmsg="请请选择起始时间！">
					</c:otherwise>
				</c:choose>
            	至
            	<input class="input_text date_picker w120 ept" name="billCycleEnd" value="${param.billCycleEnd}" type="text" title="请选择结束时间" placeholder="请选择结束时间" onclick="setmonth(this)" readonly="readonly" datatype="*" nullmsg="请选择结束时间！">
            </td>
            <td><div class="list-name">缴费时间：</div></td>
            <td colspan="2">
            	<input type="text" name="payTimeStart" title="请选择起始时间" value="${param.payTimeStart}" placeholder="请选择起始时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd'});" class="input_text pp w180 icon_dt pp">
            	至 
            	<input type="text" name="payTimeEnd" title="请选择结束时间" value="${param.payTimeEnd}" placeholder="请选择结束时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd'});" class="input_text pp w180 icon_dt pp">
            </td>
          </tr>
          <tr>
            <td colspan="10"><input class="info-btn small-btn w150" type="submit" value="查 询">
				<c:if test="${feeType == null}">
					<input id="createNewBill" class="weak-btn small-btn w150 mleft20" type="button" value="新增账期">
				</c:if>
            </td>
          </tr>
        </table>
        </form>
    </div>     
    <display:table name="resList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
		<display:column title="小区名" property="groupBuildingName" sortable="true"/>
		<display:column title="账单名称" sortable="true">
			<c:choose>
				<c:when test="${feeType == null}">
					<c:out value="${row.billName}"></c:out>
				</c:when>
				<c:otherwise>
					<c:out value="${row.billName}"></c:out>
					<c:if test="${feeType == 1}">
						（抄表收费子项）
					</c:if>
					<c:if test="${feeType == 2}">
						（常规收费子项）
					</c:if>
					<c:if test="${feeType == 3}">
						（临时收费子项）
					</c:if>
				</c:otherwise>
			</c:choose>
		</display:column>

		<display:column title="账单周期">
			${row.billMonthStart }至${row.billMonthEnd }
		</display:column>
		<display:column title="缴费时间">
			${row.billPayStart }至${row.billPayEnd }
		</display:column>
		<c:if test="${feeType == null}">
			<display:column title="账期操作" media="html">
				<c:if test="${row.chargingMode == 1}">
					<c:choose>
						<c:when test="${row.operateStatus == 0}">
							<a class="blue editBill" data-id="${row.id}" data-gbId="${row.tGroupBuildingId}" data-billTypeId="${row.tPayBillTypeId}" href="#">修改</a>
						</c:when>
						<c:otherwise>
							<a class="blue lockCycleData" href="#">修改</a>
						</c:otherwise>
					</c:choose>
				</c:if>
			</display:column>
			<display:column title="账单导入" media="html">
				<c:if test="${row.chargingMode == 1}">
					<c:choose>
						<c:when test="${row.operateStatus == 0}">
							<c:forEach items="${row.feeTypes}" var="feeType">
								<c:if test="${feeType == '1'}"><a class="blue" href="javascript:showImportMrDialog(${row.tGroupBuildingId}, ${row.id}, '${row.groupBuildingName}','${row.gbbcCfgId}');">导入抄表收费数据</a>&nbsp;&nbsp;</c:if>
								<c:if test="${feeType == '2'}"><a class="blue synchroFixedData" href="#" data-href="../groupBuildingBillCycle/synchroFixedData.json?cycleId=${row.id}&gbId=${row.tGroupBuildingId}">同步常规收费数据</a>&nbsp;&nbsp;</c:if>
								<c:if test="${feeType == '3'}"><a class="blue" href="javascript:showImportTmpBillDialog(${row.tGroupBuildingId}, ${row.id}, '${row.groupBuildingName}','${row.gbbcCfgId}');">导入临时收费项数据</a>&nbsp;&nbsp;</c:if>
							</c:forEach>
							<c:if test="${not empty row.feeTypes}">
								<a class="blue createPayBill" href="#" data-href="../groupBuildingBillCycle/createPayBill.json?cycleId=${row.id}&gbId=${row.tGroupBuildingId}">生成账单</a>
							</c:if>
						</c:when>
						<c:otherwise>
							<a class="blue lockCycleData" href="#">后台正在处理数据，请稍后刷新重试！</a>
						</c:otherwise>
					</c:choose>
				</c:if>
			</display:column>
		</c:if>
		<display:column title="操作" media="html">
			<c:choose>
				<%--可以进行账期操作--%>
				<c:when test="${row.operateStatus == null or row.operateStatus == 0}">
					<c:choose>
						<c:when test="${feeType == null}">
							<a class="blue editBill" href="../payBill/listPeriod.html?pageType=zq&gbId=${row.tGroupBuildingId}&gbName=${row.groupBuildingName }&billMonthStart=${row.billMonthStartStr }&billMonthEnd=${row.billMonthEndStr }&billTypeName=${fn:replace(fn:replace(fn:replace(fn:replace(row.billName , '+', '%2B'), ' ', '%20'), '#', '%23'), '=', '%3D')}&cycleId=${row.id}">查看账单详情</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="blue editBill deleteAllBill" href="#" data-href="../groupBuildingBillCycle/deleteAllBill.json?gbId=${row.tGroupBuildingId}&billTypeId=${row.tPayBillTypeId}&billTimeCfgId=${row.tPayBillTimeCfgId}&paytimeType=2&id=${row.id}">清除未缴账单</a>
							&nbsp;&nbsp;&nbsp;&nbsp;<a class="blue" href="javascript:batchPrintDialog(${row.tGroupBuildingId}, ${row.id});">批量打印账单</a>
						</c:when>
						<c:otherwise>
							<a class="blue editBill" href="../payBill/listPeriod.html?pageType=zq&gbId=${row.tGroupBuildingId}&gbName=${row.groupBuildingName }&billMonthStart=${row.billMonthStartStr }&billMonthEnd=${row.billMonthEndStr }&billTypeName=${row.billName}&feeType=${feeType}&cycleId=${row.id}">账单查询</a>
						</c:otherwise>
					</c:choose>
					<c:if test="${row.hasPayBillNum == 0}"><%--没有已缴账单才可以删除--%>
						&nbsp;&nbsp;&nbsp;&nbsp;<a class="blue deleteGroupBuildingCycle" href="#" data-desc="${row.delDesc }" data-href="../groupBuildingBillCycle/deleteGroupBuildingCycle.json?gbId=${row.tGroupBuildingId}&billTypeId=${row.tPayBillTypeId}&billTimeCfgId=${row.tPayBillTimeCfgId}&id=${row.id}">删除</a>
					</c:if>
				</c:when>
				<%--账期数据锁定--%>
				<c:otherwise>
					<c:choose>
						<c:when test="${feeType == null}">
							<a class="blue lockCycleData" href="#">查看账单详情</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="blue lockCycleData" href="#">清除未缴账单</a>
							&nbsp;&nbsp;&nbsp;&nbsp;<a class="blue lockCycleData" href="#">批量打印账单</a>
						</c:when>
						<c:otherwise>
							<a class="blue lockCycleData" href="#">账单查询</a>
						</c:otherwise>
					</c:choose>
					<c:if test="${row.hasPayBillNum == 0}">
						&nbsp;&nbsp;&nbsp;&nbsp;<a class="blue lockCycleData" href="#">删除</a>
					</c:if>
				</c:otherwise>
			</c:choose>
		</display:column>
	</display:table>    
</div>
<div id="newBillBox" class="layer-bill dsn" style="width: 480px; height: 260px;">
	<form class="inputform" action="../groupBuildingBillCycle/saveEdit.html" method="post">
	    <table class="info-list" border="0">
	      <tr>
	        <td width="90"><div class="list-name">小区：</div></td>
	        <td>
	        	<select id="groupBuiliding" name="gbId" class="select_normal select2_class groupBuildingAdd" datatype="*" nullmsg="请选择小区！">
	                <option value="">选择小区</option>
	            </select>
	        </td>
	      </tr>
	      <tr>
	        <td><div class="list-name">账单名称：</div></td>
	        <td><input type="text" name="billName" value="" class="input_text ept billNameAdd"  maxlength="20" datatype="*1-20" nullmsg="请完善费用名称" errormsg="费用名称为1-20个字符"/></td>
	      </tr>
	      <tr>
	        <td><div class="list-name">账单周期：</div></td>
	        <td><input class="input_text date_picker w120 ept" name="billMonthStart" value="" type="text" onclick="setmonth(this)" readonly="readonly" datatype="*" nullmsg="请选择账单周期！">
	        	至<input class="input_text date_picker w120 ept" name="billMonthEnd" value="" type="text" onclick="setmonth(this)" readonly="readonly" datatype="*" nullmsg="请选择账单周期！">
	        </td>
	      </tr>
	      <tr>
	        <td><div class="list-name">缴费窗口：</div></td>
	        <td><input type="text" class="input_text icon_dt billPayStart ept" name="billPayStart" readonly="readonly" id="date03" title="请选择起始时间" placeholder="请选择起始时间" datatype="*" nullmsg="请选择缴费窗口！"> 至<input type="text" readonly="readonly" class="input_text icon_dt billPayEnd ept" name="billPayEnd" id="date04" title="请选择起始时间" placeholder="请选择起始时间" datatype="*" nullmsg="请选择缴费窗口！"></td>
	      </tr>
		  <tr>
			  <td><div class="list-name">包含费用项：</div></td>
			  <td id="addFeeType">
			  </td>
		  </tr>
	      <tr>
	        <td colspan="2" align="center"><input id="saveNewBillBtn" class="info-btn small-btn w150" type="button" value="保 存"></td>
	      </tr>
	    </table>
    </form>
</div>

<div id="editBillBox" class="layer-bill dsn" style="width: 480px; height: 260px;">
	<form class="inputform02" action="../groupBuildingBillCycle/saveEdit.html" method="post">
	   	<input type="hidden" name="id" id="billCycleId"/>
	   	<input type="hidden" name="payBillTypeId" id="payBillTypeId"/>
	   	<input type="hidden" name="payBillTimeCfgId" id="payBillTimeCfgId"/>
	   	<input type="hidden" name="gbId" id="gbId"/>
	   	<input type="hidden" name="isHasPayBill" id="isHasPayBill"/>
	    <table class="info-list" border="0">
	      <tr>
	        <td width="90"><div class="list-name">小区：</div></td>
	        <td id="groupBuiliding_edit"></td>
	      </tr>
	      <tr id="billNameEdit">
	        <td><div class="list-name">费用名称：</div></td>
	        <td id="billNameTd">
			</td>
	      </tr>
		<tr id="billmonthreadonle">
			<td><div class="list-name">账单周期：</div></td>
			<td id="billMonth">
				<input type='hidden' name='billMonthStartEdit' value=''/>
				<input type='hidden' name='billMonthEndEdit' value=''/>
			</td>
		</tr>
	      <tr>
	        <td><div class="list-name">缴费窗口：</div></td>
	        <td id="billPayDay"><input type="text" class="input_text icon_dt billPayStart" readonly="readonly" name="billPayStart" id="date03" title="请选择起始时间" placeholder="请选择起始时间" readonly="readonly" value="" datatype="*" nullmsg="请选择缴费窗口！">
				至<input type="text" readonly="readonly" class="input_text icon_dt billPayEnd" name="billPayEnd" id="date04" title="请选择起始时间" placeholder="请选择起始时间" value="" datatype="*" nullmsg="请选择缴费窗口！"></td>
	      </tr>
			<tr>
				<td><div class="list-name">包含费用项：</div></td>
				<td id="editFeeType">
				</td>
			</tr>
	      <tr>
	        <td colspan="2" align="center"><input id="saveNewBillBtn02" class="info-btn small-btn w150" type="button" value="保 存"></td>
	      </tr>
	    </table>
    </form>
</div>
<%-- 抄表 --%>
<div class="layer-bill importMrDiv dsn" style="height: auto;padding-top:15px;padding-bottom:50px;">
  <form enctype="multipart/form-data" id="mrForm" class="inputform1" action="../groupBuildingBillCycle/importMrData.html" method="post"> 
    <input id="billCycleIdImport" name="billCycleId" type="hidden"/>
    <input id="importGbId" name="importGbId" type="hidden"/>
    <input id="gbbcCfgIdImport" name="gbbcCfgIdImport" type="hidden"/>
    <input id="importGbName" name="importGbName" type="hidden"/>
    <input name="isBillCycle" type="hidden" value="1"/>
	<input class="currentPageInput" name="currentPage" type="hidden"/> <%--记录当前页 --%>
    <h2>第一步:下载抄表模板</h2>
    <div class="bs-example bgebeb">
	<table class="info-list" border="0">
         <tr>
           <td style="width: 60px;"><div class="list-name">小区：</div></td>
           <td class="groupBuildingImport"></td>
         </tr>
         <tr>
           <td><div class="list-name">下载：</div></td>
		   <td><a id="downMrBtn" class="blue" href="javascript:void(0)"><img src="../images/download-icon.jpg" />下载抄表模版</a></td>
         </tr>
     </table>
     </div>
     <h2>第二步:上传抄表数据</h2>
     <div class="bs-example bgebeb">
     <table class="info-list" border="0">
         <tr>
           <td>
		       <input id="mrExcelFileInput" name="excelFile" type="file" style="width: 300px;" title="上传新账单" />
		       <input class="img-upload-val dsn" type="text" value="" datatype="*" nullmsg="请选择excel！">
           </td>
           <td><input id="uploadMrBtn" class="input-btn w160" type="button" value="上传" /></td>
         </tr>
     </table>
     <span class="red dsn" id="uploadTips">数据正在上传中，请稍候…</span>
     </div>
	</form>
</div>

<%-- 临时收费 --%>
<div class="layer-bill importTmpBillDiv dsn" style="height: auto;padding-top:15px;padding-bottom:50px;">
  <form enctype="multipart/form-data" id="tmpBillForm" class="inputform1" action="../groupBuildingBillCycle/importTmpBillData.json" method="post"> 
    <input id="gbIdForTmpBill" name="gbIdForTmpBill" type="hidden"/>
    <input id="gbbcIdForTmpBill" name="gbbcIdForTmpBill" type="hidden"/>
    <input id="gbbcCfgIdForTmpBill" name="gbbcCfgIdForTmpBill" type="hidden"/>
    <input id="gbNameForTmpBill" name="gbNameForTmpBill" type="hidden"/>
    <input class="currentPageInput" name="currentPage" type="hidden"/> <%--记录当前页 --%>
    <h2>第一步:下载临时收费模板</h2>
    <div class="bs-example bgebeb">
	<table class="info-list" border="0">
         <tr>
           <td style="width: 60px;"><div class="list-name">小区：</div></td>
           <td class="groupBuildingImport"></td>
         </tr>
         <tr>
           <td><div class="list-name">下载：</div></td>
		   <td><a id="downTmpBillBtn" class="blue" href="javascript:void(0)"><img src="../images/download-icon.jpg" />下载临时收费模版</a></td>
         </tr>
     </table>
     </div>
     <h2>第二步:上传临时收费数据</h2>
     <div class="bs-example bgebeb">
     <table class="info-list" border="0">
         <tr>
           <td>
		       <input id="tmpBillExcelFileInput" name="excelFile" type="file" style="width: 300px;" title="上传新账单" />
		       <input class="img-upload-val dsn" type="text" value="" datatype="*" nullmsg="请选择excel！">
           </td>
           <td><input id="uploadTmpBillBtn" class="input-btn w160" type="button" value="上传" /></td>
         </tr>
     </table>
     <span class="red dsn" id="uploadTips">数据正在上传中，请稍候…</span>
     </div>
	</form>
</div>

<div class="batchPrintDialog dsn" style="height: auto;padding-top:15px;padding-bottom:50px;width: 300px;">
	 <input id="printPayBillId" type="hidden" value=""/>
	 <input id="print_gbId" type="hidden" value=""/>
	 <input id="print_gbbcId" type="hidden" value=""/>
	 <h2 style="margin-left: 1em;">选择纸张类型</h2>
     <div class="bs-example bgebeb">
	     <table class="info-list" border="0">
	     	 <tr>
	     	 	<td>
	     	 		<label><input type="radio" name="pageSize" value="A4" checked="checked"/>A4</label>
					<select class="select_normal w131" name="pageDivision" id="pageDivision">
						<option value="1"selected="selected">一张</option>
						<option value="2">两张</option>
						<option value="3">三张</option>
					</select>
	     	 	</td>
	     	 </tr>
	     	 <tr>
	     	 	<td>
	     	 		<label><input type="radio" name="pageSize" value="A4_Half"/>复写纸</label>
	     	 	</td>
	     	 </tr>
	         <tr>
	           <td style="text-align: right;">
	           	   <input class="input-btn w160" id="printBtn" type="button" value="批量打印" onclick="batchPrint();"/>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(function(){
	$('.select2_class').select2();
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		btnSubmit:"#saveNewBillBtn",
		beforeCheck:function(curform){
			if($(".groupBuildingAdd").val() == null || "" == $(".groupBuildingAdd").val()) {
				alert("请选择小区！");
				return false;
			}

			var id;
			if(curform.find("#editFeeType").length>0){
				id = "editFeeType";
			} else if(curform.find("#addFeeType").length>0){
				id = "addFeeType";
			}
			var feeTypes = $("#"+id).find(":checkbox");
			if(feeTypes.length==0){
				alert("该小区没有配置收费项！请到“正式小区参数”下配置收费项！");
				return false;
			} else if($("#"+id).find(":checked").length==0){
				alert("请选择“费用项”！");
				return false;
			}

		}
	});

	$("#saveNewBillBtn02").click(function() {
		var isSub = true;
		var isHasBox = $("#editFeeType").find("input[type='checkbox']").length;
		if(isHasBox > 0) {
			var isHasChecked = false;
			$("#editFeeType").find("input[type='checkbox']").each(function () {
				if($(this).is(":checked")) {
					isHasChecked = true;
				}
			});
			if(!isHasChecked) {
				alert("请选择'包含费用项'");
				isSub = false;
				return false;
			}
		}

		var billName = $("#billNameTd").find("input[type='text']");
		if (billName.length > 0 && !billName.val()) {
			alert("账单名称不能为空！");
			isSub = false;
			return false;
		}

		var billMonth02 = $("#billMonth").find("input[type='text']");
		if (billMonth02.length > 0) {
			billMonth02.each(function () {
				if(!$(this).val()) {
					alert("缴费周期不能为空！");
					isSub = false;
					return false;
				}
			});
		}

		var billPayDay = $("#billPayDay").find("input[type='text']");
		if (billPayDay.length > 0) {
			billPayDay.each(function () {
				if(!$(this).val()) {
					alert("缴费窗口不能为空！");
					isSub = false;
					return false;
				}
			});
		}

		if(isSub) {
			$(".inputform02").submit();
		}

	});

	 $("#uploadMrBtn").click(function(){
	    	if($("#mrExcelFileInput").val() == "") {
	    		alert("请选择excel！");
	    		return false;
	    	} else {
	    		$("#uploadMrBtn").attr("disabled",true);
	    		$("#uploadMrBtn").css({"background":"#DCDCDC","color":"#FFFFFF","border":"0px","cursor":"wait"});
				//增加菊花
				var layermsg = null;
				layermsg = layer.msg('正在导入，请稍候', {
					icon: 16,
					time: 0,	//指定time为0，默认该消息不关闭；若2秒后，再弹出layer.alert('加载完成', 1);该消息同时关闭。
					shade: 0.5
				});
	    		$("#mrForm").submit();
	    	}
	  });
	 
	 $("#uploadTmpBillBtn").click(function(){
	    	if($("#tmpBillExcelFileInput").val() == "") {
	    		alert("请选择excel！");
	    		return false;
	    	} else {
				//增加菊花
				var layermsg = null;
				layermsg = layer.msg('正在导入，请稍候', {
					icon: 16,
					time: 0,	//指定time为0，默认该消息不关闭；若2秒后，再弹出layer.alert('加载完成', 1);该消息同时关闭。
					shade: 0.5
				});
	    		$("#uploadTmpBillBtn").attr("disabled",true);
	    		$("#uploadTmpBillBtn").css({"background":"#DCDCDC","color":"#FFFFFF","border":"0px","cursor":"wait"});
	    		$("#tmpBillForm").submit();
	    	}
	  });
    
	//新增账单弹出层
	$('#createNewBill').click(function(){
		$.ajax({
			  url: '../groupBuildingBillCycle/initEdit.html',
			  dataType: 'json',
			  success: function(data){
				  var list = JSON.parse(data).gbList;
				  var strHtml = "<option value='''>选择小区</option>";
				  $.each(list, function(i, item) {
					  strHtml += "<option data-fixed-id='" + item.fixedFee + "' data-meter-id='" + item.meterFee + "' data-temp-id='" + item.tempFee + "' value='" + item.id + "'>" + item.name + "</option>";
				  });
				  $("#groupBuiliding").html(strHtml);
				  $("#paytimeType").val(JSON.parse(data).paytimeType);
				  $("#addFeeType").html("");
				  //清除原来的内容
				  $(".ept").val("");
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
	
	$("#groupBuiliding").change(function(e){
		var fixed = $(this).find("option:selected").attr("data-fixed-id");
		var meter = $(this).find("option:selected").attr("data-meter-id");
		var temp = $(this).find("option:selected").attr("data-temp-id");
		var html = feeTypeHtml(fixed, meter, temp);
		$("#addFeeType").html(html);

		//清除原来的内容
		$(".ept").val("");
	});

	function feeTypeHtml(fixed, meter, temp) {
		var html = "";
		if(fixed == 1) {
			html += "<label><input name='feeType' type='checkbox' value='2'>常规收费项&nbsp;&nbsp;</label>";
		}
		if(meter == 1) {
			html += "<label><input name='feeType' type='checkbox' value='1'>抄表收费项&nbsp;&nbsp;</label>";
		}
		if(temp == 1) {
			html += "<label><input name='feeType' type='checkbox' value='3'>临时收费项&nbsp;&nbsp;</label>";
		}
		if(html == "") {
			html = "<a style='color:red' href='../groupBuilding/list4admin.html'>请到“正式小区参数”下配置收费项</a>";
		}
		return html;
	}

	//修改账期弹出层
	$('.editBill').click(function(){
		var id = $(this).attr("data-id");
		var gbId = $(this).attr("data-gbId");
		//var paytimeType = 2;
		$.ajax({
			  url: '../groupBuildingBillCycle/initEdit.html',
			  dataType: 'json',
			  data: {"gbId":gbId,"id":id},
			  success: function(data){
				  var gbc = JSON.parse(data).gbc;
				  var isHasPayBill = JSON.parse(data).isHasPayBill;
				  $("#groupBuiliding_edit").html(gbc.groupBuildingName);
				  var feeType = gbc.feeType+'';
				  var htmlStr = "";
				  if(isHasPayBill) {
					  $("#billMonth").html(gbc.billMonthStartStr+"至"+gbc.billMonthEndStr+
							  "<input type='hidden' name='billMonthStartEdit' value='"+gbc.billMonthStart+"'/>"+
							  "<input type='hidden' name='billMonthEndEdit' value='"+gbc.billMonthEnd+"'/>"
					  );
					  $("#billNameTd").html(gbc.billName);
				  } else {
					  $("#billNameTd").html("<input type='text' class='input_text pp' name='billName' value='"+gbc.billName+"' maxlength='20' datatype='*1-20' nullmsg='请完善费用名称' errormsg='费用名称为1-20个字符'/>");
					  $("#billMonth").html(
							  "<input class='input_text date_picker w120 pp' name='billMonthStartEdit' value='"+gbc.billMonthStart+"' readonly='readonly' type='text' onclick='setmonth(this)' datatype='*' nullmsg='请选择账单周期！'>"+
							  "至<input class='input_text date_picker w120 pp' name='billMonthEndEdit' value='"+gbc.billMonthEnd+"' readonly='readonly' type='text' onclick='setmonth(this)' datatype='*' nullmsg='请选择账单周期！'>"
					  );
				  }
				  if(gbc.fixedFeeStatus != null && gbc.fixedFeeStatus == 1) {
					  if(feeType.indexOf("2") >= 0) {
						  htmlStr += "<label><input name='feeType' type='checkbox' checked='checked' value='2'>常规收费项&nbsp;&nbsp;</label>";
					  } else {
						  htmlStr += "<label><input name='feeType' type='checkbox' value='2'>常规收费项&nbsp;&nbsp;</label>";
					  }
				  }
				  if(gbc.meterFeeStatus != null && gbc.meterFeeStatus == 1) {
					  if(feeType.indexOf("1") >= 0) {
						  htmlStr += "<label><input name='feeType' type='checkbox' checked='checked' value='1'>抄表收费项&nbsp;&nbsp;</label>";
					  } else {
						  htmlStr += "<label><input name='feeType' type='checkbox' value='1'>抄表收费项&nbsp;&nbsp;</label>";
					  }
				  }
				  if(gbc.tempFeeStatus != null && gbc.tempFeeStatus == 1) {
					  if(feeType.indexOf("3") >= 0) {
						  htmlStr += "<label><input name='feeType' type='checkbox' checked='checked' value='3'>临时收费项&nbsp;&nbsp;</label>";
					  } else {
						  htmlStr += "<label><input name='feeType' type='checkbox' value='3'>临时收费项&nbsp;&nbsp;</label>";
					  }
				  }
				  if(feeType == "") {
					  htmlStr = "<a style='color:red' href='../groupBuilding/list4admin.html'>请到“正式小区参数”下配置收费项</a>";
				  }

				  $("#editFeeType").html(htmlStr);

				  $(".billPayStart").val(gbc.billPayStart);
				  $(".billPayEnd").val(gbc.billPayEnd);
				  $("#billCycleId").val(gbc.id);
				  $("#gbId").val(gbc.tGroupBuildingId);
				  $("#payBillTypeId").val(gbc.tPayBillTypeId);
				  $("#payBillTimeCfgId").val(gbc.tPayBillTimeCfgId);
				  $("#billMonthStartEdit").val(gbc.billMonthStart);
				  $("#billMonthEndEdit").val(gbc.billMonthEnd);
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
	
	$("#downMrBtn").click(function() {
		location.href=  "${pageContext.request.contextPath}/groupBuildingBillCycle/exportMRTemplate.json?gbbcCfgId="+$("#gbbcCfgIdImport").val()+"&cycleId="+$("#billCycleIdImport").val()+"&gbId="+$("#importGbId").val();
	});
	
	$("#downTmpBillBtn").click(function() {
		location.href=  "${pageContext.request.contextPath}/groupBuildingBillCycle/exportTmpBillTemplate.json?gbbcCfgId="+$("#gbbcCfgIdForTmpBill").val()+"&cycleId="+$("#gbbcIdForTmpBill").val()+"&gbId="+$("#gbIdForTmpBill").val();
	});
	
	//清除未缴账单
	$('a.deleteAllBill').click(function(){
		if(confirm("是否清除所有未缴账单？")){
			$.ajax({
				  url: $(this).attr('data-href'),
				  dataType: 'json',
				  success: function(data,status){
					 alert(data.message);
				  }
			});
		}
	});
	
	//删除账期
	$(".deleteGroupBuildingCycle").click(function () {
		if(confirm("确认删除"+$(this).attr("data-desc")+"？")){
			$.ajax({
				url: $(this).attr('data-href'),
				dataType: 'json',
				success: function(data,status){
					alert(data.message);
					document.location.reload();
				}
			});
		}
	});

	//固定周期数据同步
	$(".synchroFixedData").click(function(){
		//增加菊花
		var layermsg = null;
		layermsg = layer.msg('正在同步，请稍候', {
			icon: 16,
			time: 0,	//指定time为0，默认该消息不关闭；若2秒后，再弹出layer.alert('加载完成', 1);该消息同时关闭。
			shade: 0.5
		});
		$.ajax({
			url: $(this).attr('data-href'),
			dataType: 'json',
			success: function(data, status){
				alert(data.message);
				window.location.reload();
			}
		});
	});

	//生成账单
	$(".createPayBill").click(function(){
		//增加菊花
		var layermsg = null;
		layermsg = layer.msg('正在生成，请稍候', {
			icon: 16,
			time: 0,	//指定time为0，默认该消息不关闭；若2秒后，再弹出layer.alert('加载完成', 1);该消息同时关闭。
			shade: 0.5
		});
		$.ajax({
			url: $(this).attr('data-href'),
			dataType: 'json',
			success: function(data, status){
				alert(data.message);
				window.location.reload();
			}
		});
	});

	var gbId = '${gbId}';
	$.ajax({
		url: '${pageContext.request.contextPath}/groupBuilding/getGroupBuildings.json',
		dataType: 'json',
		success: function (data) {
			var list = JSON.parse(data);
			var strHtml = "<option value=''>选择小区&nbsp;&nbsp;&nbsp;</option>";
			$.each(list, function (i, item) {
				var str = "";
				if(gbId == item.id) {
					str = "<option value='" + item.id + "' selected='selected'>" + item.name + "</option>";
				} else {
					str = "<option value='" + item.id + "'>" + item.name + "</option>";
				}
				strHtml += str;
			});
			$("#groupBuiliding_select").html(strHtml);
		}
	});

	/*数据锁定，提示。同时刷新页面*/
	$(".lockCycleData").click(function () {
		alert("正在处理数据，请稍后！");
		$(".query_form").submit();
	});
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
	var pageDivision = $("#pageDivision").val();
	var url = "${pageContext.request.contextPath}/payBillPrint/batchPrintByGbId.html?gbId="+gbId+"&gbbcId="+gbbcId+"&pageSize="+pageSize+"&feeType=${feeType}&pageDivision="+pageDivision;
	window.open(url);
}

//导入账期弹出层
function showImportMrDialog(gbId, gbbcId, gbName, gbbcCfgId){
	$("#importGbId").val(gbId);
	$("#importGbName").val(gbName);
	$(".importMrDiv .groupBuildingImport").html(gbName);
	$("#billCycleIdImport").val(gbbcId);
	$("#gbbcCfgIdImport").val(gbbcCfgId);
	$.layer({
		type: 1,
		shade: [0.4,'#000000'],
		area: ['auto', 'auto'],
		title: false,
		border : [5, 0.3, '#000'],
		page: {dom : '.importMrDiv'}
 	});
}

function showImportTmpBillDialog(gbId, gbbcId, gbName, gbbcCfgId){
	$("#gbIdForTmpBill").val(gbId);
	$("#gbbcIdForTmpBill").val(gbbcId);
	if(!gbbcCfgId) {
		$("#gbbcCfgIdForTmpBill").val("");
	} else {
		$("#gbbcCfgIdForTmpBill").val(gbbcCfgId);
	}
	$("#gbNameForTmpBill").val(gbName);
	$(".importTmpBillDiv .groupBuildingImport").html(gbName);
	$.layer({
		type: 1,
		shade: [0.4,'#000000'],
		area: ['auto', 'auto'],
		title: false,
		border : [5, 0.3, '#000'],
		page: {dom : '.importTmpBillDiv'}
 	});
}

function URLencode(sStr)
{
	return escape(sStr).replace(/\+/g, '%2B').replace(/\"/g,'%22').replace(/\'/g, '%27').replace(/\//g,'%2F');
}

	$(".currentPageInput").val(getUrlParam("d-16544-p")); //记录当前页
</script>
</html>
