<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<title>托收记录</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" type="text/css" href="../css/select2.css">
		<style>
			.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
			.w180{width: 170px;}
			#importForm label{font-weight: bold;}
		</style>
	</head>
	<body>
		<div class="info">
		    <h2 class="mtop20">托收记录</h2> 
		    <div class="bs-example bgebeb">
		    	<form id="bcHistoryForm" method="post" action="${pageContext.request.contextPath}/bankCollection/bcHistory.html">
			        <table class="info-list" border="0">
			          <tr>
				            <td width="90"><div class="list-name">编号：</div></td>
				            <td width="260"><input type="text" class="input_text w240 pp" name="no" value="${param.no}"></td>
						    <td><div class="list-name">小区名称：</div></td>
						    <td>
							  <select id="groupBuiliding" name="gbName" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
								  <option value="">选择小区</option>
							  </select>
						    </td>
				            <td><div class="list-name">托收银行：</div></td>
				            <td>
			                    <select name="bankOrg" class="select_normal">
			                    	<option value="">全部</option>
									<c:forEach items="${bcFinanceOrgList}" var="row">
										<option value="${row.id}" <c:if test="${row.id eq param.bankOrg}"> selected</c:if>>${row.orgName}</option>
									</c:forEach>
								</select>
				            </td>
			          </tr>
			          <tr>
				            <td><div class="list-name">账单名称：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="billName" value="${param.billName}"></td>
				            <td><div class="list-name">账单周期：</div></td>
				            <td>
								<c:choose>
									<c:when test="${not empty billMonthStart}">
										<input type="text" name="billMonthStart" title="请选择起始时间" value="${billMonthStart}" placeholder="请选择起始时间" onclick="setmonth(this)" class="input_text w120 pp">
									</c:when>
									<c:otherwise>
										<input type="text" name="billMonthStart" title="请选择起始时间" value="${param.billMonthStart}" placeholder="请选择起始时间" onclick="setmonth(this)" class="input_text w120 pp">
									</c:otherwise>
								</c:choose>
					           	 至
					            <input type="text" id="billMonthEnd" class="input_text w120 pp" value="${param.billMonthEnd}" title="如：2014-07" name="billMonthEnd" type="text" onclick="setmonth(this)" readonly="readonly"/>
				            </td>
				            <td></td>
				            <td></td>
			          </tr>
			          <tr>
				            <td colspan="6" style="text-align: center;">
				            	<input class="info-btn small-btn w100" type="submit" value="查询"/>
				            </td>
			          </tr>
			        </table>
			    </form>
		    </div>  
		    <display:table name="bcHistorys" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="total" >
	    		<display:column title="编号" property="no" style="text-align:center;" headerClass="t_center"/>
				<display:column title="托收范围" style="text-align:center;" headerClass="t_center">
					<c:choose>
						<c:when test="${row.collectionRange eq 1}">小区</c:when>
						<c:otherwise>业主</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="所含小区" property="gbNames" style="text-align:center;" headerClass="t_center"/>
				<display:column title="托收银行" property="bankName" style="text-align:center;" headerClass="t_center"/>
				<display:column title="账单周期" style="text-align:center;" headerClass="t_center">
					${row.billMonthStart}&nbsp;至&nbsp;${row.billMonthEnd}
				</display:column>
				<display:column title="账单名称" property="billName" style="text-align:center;" headerClass="t_center"/>
				<display:column title="操作" style="text-align:center;" headerClass="t_center">
					<c:if test="${row.rebackStatus ne 1}">
						<a href="javascript:void(0)" onclick="downOutBC(${row.bgbcId},'${row.billMonthStart}', '${row.billMonthEnd}', this)" class="blue">下载出盘文件</a>
						|
						<a href="javascript:void(0)" class="blue" onclick="showImportTmpBillDialog(${row.bgbcId}, '${row.fileFormat}', this)">回盘</a>
						<c:if test="${(not empty row.detailFileUrl) or (not empty row.sumFileUrl)}">
							|
						</c:if>
					</c:if>
					<c:if test="${(not empty row.detailFileUrl) or (not empty row.sumFileUrl)}">
						<a href="${pageContext.request.contextPath}/bankCollection/printOfferDetail.html?bgbcId=${row.bgbcId}&pcbciId=${row.pcbciId}" class="blue" target="_blank">打印出盘明细</a>
					</c:if>
				</display:column>
				<display:column title="查看回盘结果" style="text-align:center;" headerClass="t_center">
					<a href="${pageContext.request.contextPath}/bankCollection/viewBc.html?bgbcIds=${row.bgbcIds}" class="blue">查看</a>
				</display:column>
				<display:column title="确认" style="text-align:center;" headerClass="t_center">
					<c:if test="${row.rebackStatus ne 1}">
						<a href="javascript:confirmBc(${row.bgbcId}, ${row.pcbciId})" class="blue">确认回盘完成</a>
					</c:if>
				</display:column>
			</display:table>
		</div>
		<div class="importInBCDialog dsn" style="height: auto;padding-top:15px;padding-bottom:50px;width: 600px;">
			 <h2 style="margin-left: 1em;">上传回盘文件</h2>
		     <div class="bs-example bgebeb">
		         <form id="importForm" action="${pageContext.request.contextPath}/bankCollection/importInBC.html" method="post" enctype="multipart/form-data">
			     	 <input type="hidden" id="bgbcId" name="bgbcId" value=""/>
			     	 <input type="hidden" id="fileFormat" name="fileFormat" value=""/>
				     <table class="info-list" border="0">
				     	 <tr>
				     	 	<td>
				     	 		<div class="list-name">编号：</div>
				     	 	</td>
				     	 	<td class="no">
				     	 	</td>
				     	 </tr>
				     	 <tr>
				     	 	<td>
				     	 		<div class="list-name">所含小区：</div>
				     	 	</td>
				     	 	<td class="gbName">
				     	 	</td>
				     	 </tr>
				     	 <tr>
				     	 	<td>
				     	 		<div class="list-name">账单周期：</div>
				     	 	</td>
				     	 	<td class="billTime">
				     	 	</td>
				     	 </tr>
				     	 <tr>
				     	 	<td>
				     	 		<div class="list-name">账单名称：</div>
				     	 	</td>
				     	 	<td class="billName">
				     	 	</td>
				     	 </tr>
				     	 <tr>
				            <td>
				     	 		<div class="list-name" id="fileFormatTip">选择.zip、.xls、.xlsx等格式文件：</div>
				     	 	</td>
				     	 	<td colspan="2">
								<input type="file" id="bankFile" name="bankFile" onchange="checkFile()"/>
				     	 	</td>
				         </tr>
				         <tr>
				           <td style="text-align: right;" colspan="2">
				           	   <input class="input-btn small-btn w160" id="importBtn" type="button" value="上传" onclick="importInBC();"/>
				           </td>
				         </tr>
				     </table>
				     <span class="red dsn" id="uploadTips">数据正在上传中，请稍候…</span>
			     </form>
		     </div>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/DatePicker.js?v1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simple-dtpicker01.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/layer.min.js"></script>
	<script type="text/javascript" src="../js/select2.js"></script>
	<script type="text/javascript">
		function downOutBC(bgbcId, billMonthStart, billMonthEnd, obj){
			var $this = $(obj).parents("tr");
			var gbNames = $this.find("td").eq(2).text();
			var bankName = $this.find("td").eq(3).text();
			var cycleTimes = $this.find("td").eq(4).text();
			var payBillTypeName = $this.find("td").eq(5).text();
			
			if(gbNames.lastIndexOf(", ") == (gbNames.length -2)) //末尾有逗号和空格，要去掉
				gbNames = gbNames.substring(0, gbNames.lastIndexOf(", "));
			
			if(billMonthEnd == billMonthStart)
				location.href = "${pageContext.request.contextPath}/bankCollection/downOutBC.html?bgbcId="+bgbcId + "&allInfo=" +(gbNames+billMonthStart+payBillTypeName+ "-" +bankName);
			else
				location.href = "${pageContext.request.contextPath}/bankCollection/downOutBC.html?bgbcId="+bgbcId + "&allInfo=" +(gbNames+billMonthStart + "-" + billMonthEnd +payBillTypeName+ "-" +bankName);
		}
		
		var bcDialog;
		function showImportTmpBillDialog(bgbcId, fileFormat, o){
			$("#bgbcId").val(bgbcId);
			$(".importInBCDialog .no, .importInBCDialog .gbName, .importInBCDialog .billTime, .importInBCDialog .billName").html("");
			
			var trObj = $(o).parent().parent().find("td");
			$(".importInBCDialog .no").html($(trObj[0]).text());
			$(".importInBCDialog .gbName").html($(trObj[2]).text());
			$(".importInBCDialog .billTime").html($(trObj[4]).text());
			$(".importInBCDialog .billName").html($(trObj[5]).text());
			
			$("#fileFormatTip").html("选择"+fileFormat+"格式文件：");
			$("#fileFormat").val(fileFormat);
			bcDialog = $.layer({
				type: 1,
				shade: [0.4,'#000000'],
				area: ['auto', 'auto'],
				title: false,
				border : [5, 0.3, '#000'],
				page: {dom : '.importInBCDialog'}
		 	});
		}
		
		function checkFile(){
			var fileFormat = $("#fileFormat").val();
			if($("#bankFile").val().indexOf(fileFormat)==-1){
				alert("请选择"+fileFormat+"格式文件！");
				$("#bankFile").val("");
				return false;
			}
		}

		function importInBC() {
			var fileFormat = $("#fileFormat").val();
			if($("#bankFile").val().indexOf(fileFormat)==-1){
				alert("请选择"+fileFormat+"格式文件！");
				return false;
			} else {
				var layermsg = null;
				layermsg = layer.msg('正在导入，请稍候', {
					icon: 16,
					time: 0,
					shade: 0.5
				});
				$("#importBtn").attr("disabled", true);
				$("#importBtn").css({
					"background": "#DCDCDC",
					"color": "#FFFFFF",
					"border": "0px",
					"cursor": "wait"
				});
				$("#importForm").submit();
			}
		}
		function confirmBc(bgbcId, pcbciId){
			if(confirm("您确认回盘已完成吗？")){
				$.post("${pageContext.request.contextPath}/bankCollection/confirmBc.html", {"bgbcId":bgbcId, "pcbciId":pcbciId}, function(data){
					alert(data.message);
					if(data.status == "<%=CommConstants.ResponseStatus.SUCCESS%>") {
						location = location;
					}
				});
			}
		}

		$(function(){
			var gbName = '${param.gbName}';
			$('.select2_class').select2();

			$.ajax({
				url: '${pageContext.request.contextPath}/groupBuilding/getGroupBuildings.json',
				dataType: 'json',
				success: function (data) {
					var list = JSON.parse(data);
					var strHtml = "<option value=''>选择小区&nbsp;&nbsp;&nbsp;</option>";
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
				}
			});
		});
	</script>
</html>