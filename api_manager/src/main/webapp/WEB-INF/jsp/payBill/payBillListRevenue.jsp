<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
<c:choose>
	<c:when test="${param.projectType == 5}">物业费实收</c:when>
	<c:when test="${param.projectType == 15}">物业费补贴</c:when>
	<c:when test="${param.projectType == 7}">其他代收费用</c:when>
	<c:otherwise>物业代收费管理</c:otherwise>
</c:choose>
</title>
<link rel="stylesheet" type="text/css" href="../css/common.css"/>
<link rel="stylesheet" type="text/css" href="../css/style.css"/>
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css"/>
<link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>

<body>
<div class="info">
    <h2>
    	<c:choose>
    		<c:when test="${param.projectType == 5}">物业费实收</c:when>
    		<c:when test="${param.projectType == 15}">物业费补贴</c:when>
    		<c:when test="${param.projectType == 7}">其他代收费用</c:when>
    		<c:otherwise>物业代收费管理</c:otherwise>
    	</c:choose>
    </h2>
    <div class="bs-example bgebeb">
    	<form name="searchForm" method="post" action="..${formUrl}">
	        <table class="info-list" border="0">
	          <tr>
	            <td width="90"><div class="list-name">物业公司：</div></td>
	            <td width="130">
	            	<c:choose>
	            		<c:when test="${qryAll == true}">
		            		<input type="text" class="input_text w120 pp" name="pcName" value='${param.pcName }'/>
	            		</c:when>
	            		<c:otherwise>
		            		<input type="text" class="input_text w120 pp" name="rev_pcName" value='${param.rev_pcName }' readonly="readonly"/>
			            	<input type="hidden" class="input_text w120" name="applyId" value='${param.applyId }'/>
			            	<input type="hidden" class="input_text w120" name="projectType" value='${param.projectType }'/>
	            		</c:otherwise>
	            	</c:choose>
	            </td>
	            
	            <c:if test="${qryAll == true}">
            	<td width="90"><div class="list-name">收益项目：</div></td>
	            <td width="130">
	            	<select class="select_normal w131" name="projectType">
	            		<!-- <option value="">全部</option> -->
            			<option value="5"<c:if test="${param.projectType==5 or projectType==5}"> selected="selected"</c:if> >物业费实收</option>
            			<option value="15"<c:if test="${param.projectType==15 or projectType==15}"> selected="selected"</c:if> >物业费补贴</option>
            			<option value="7"<c:if test="${param.projectType==7 or projectType==7}"> selected="selected"</c:if> >其他代收费用</option>
                    </select>
	            </td>
	            </c:if>
	            
	            <td width="90"><div class="list-name">小区名：</div></td>
			  	<td>
				  <select id="groupBuiliding" name="gbName" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
					  <option value="">选择小区</option>
				  </select>
			  	</td>
	            <td width="90"><div class="list-name">楼栋：</div></td>
	            <td width="130">
	            	<input type="text" class="input_text w120 pp" value="${param.bName }" name="bName"/>
	            </td>
	            <td width="90"><div class="list-name">单元：</div></td>
	            <td width="130">
	            	<input type="text" class="input_text w120 pp" value="${param.unitName }" name="unitName"/>
	            </td>
	            <td width="90"><div class="list-name">房号：</div></td>
	            <td width="130">
	            	<input type="text" class="input_text w120 pp" value="${param.roomNum }" name="roomNum"/>
	            </td>
	            <td width="90"></td>
	          </tr>
	          <tr>
	            <td width="90"><div class="list-name">业主姓名：</div></td>
	            <td width="120"><input type="text" class="input_text w120 pp" value="${param.ppName }" name="ppName"/></td>
	            <td width="90"><div class="list-name">账单月份：</div></td>
	            <td width="120">
	            	<c:choose>
						<c:when test="${not empty pbMonth}">
			            	<input type="text" id="begintime" class="input_text w120" value="${pbMonth }" title="如：2014-07" name="pbMonth" type="text" onclick="setmonth(this)" readonly="readonly"/>
						</c:when>
						<c:otherwise>
			            	<input type="text" id="begintime" class="input_text w120" value="${param.pbMonth }" title="如：2014-07" name="pbMonth" type="text" onclick="setmonth(this)" readonly="readonly"/>
						</c:otherwise>
					</c:choose>
	            </td>
	            <input name="isPay" value="1" type="hidden"/>
            	<%-- <option value="1" <c:if test="${param.isPay==1 }"> selected="selected"</c:if> >已缴费</option> --%>
	            <td width="90"><div class="list-name">费用名称：</div></td>
	            <td width="120">
	            <c:choose>
					<c:when test="${param.projectType == 5 || param.projectType == 15}">物业费</c:when>
					<c:otherwise><input type="text" class="input_text w120 pp" value="${param.billTypeName }" name="billTypeName"/></c:otherwise>
				</c:choose>
	            </td>
	            <input name="paymentWay" value="1" type="hidden"/>
         		<%-- <option value="1" <c:if test="${param.paymentWay==1 || paymentWay==1}"> selected="selected"</c:if>>在解放区缴费</option> --%>
           		
           		<td width="90"><div class="list-name">结算状态：</div></td>
	            <td width="150">
	            	<select class="select_normal w131" name="tkStatus_wy">
	            		<option value="">全部</option>
	            		<option value="1" <c:if test="${param.tkStatus_wy==1 }"> selected="selected"</c:if>>未结算</option>
	            		<option value="2" <c:if test="${param.tkStatus_wy==2 }"> selected="selected"</c:if>>申请中</option>
	            		<option value="3" <c:if test="${param.tkStatus_wy==3 }"> selected="selected"</c:if>>已结算</option>
	            	</select>
	            </td>
			  	<td width="90"><div class="list-name">缴费时间：</div></td>
            	<td colspan="3">
					<input id="date01" value='${param.date01 }' name="date01" type="text" class="input_text icon_dt pp"  title="请选择起始时间" />
					至 <input  type="text" class="input_text icon_dt" id="date02"  value='${param.date02 }'  name="date02" title="请选择结束时间"/></td>
	          </tr>
			  <tr>
			  	<td colspan="5" align="center">
	            	<span>
	            	账单总额：<fmt:formatNumber value="${totalData.amountTotal/100.0}" pattern="#,##0.00"/>元&nbsp;&nbsp;&nbsp;&nbsp;
	            	折扣总额：<fmt:formatNumber value="${(totalData.amountTotal-totalData.succPayAmountTotal - totalData.deduPriceTotal)/100.0}" pattern="#,##0.00"/>&nbsp;&nbsp;&nbsp;&nbsp;
	            	实付总额：<fmt:formatNumber value="${(totalData.succPayAmountTotal)/100.0}" pattern="#,##0.00"/>&nbsp;&nbsp;&nbsp;&nbsp;
	            	物业宝抵扣总额：<fmt:formatNumber value="${(totalData.deduPriceTotal)/100.0}" pattern="#,##0.00"/> 
	            	</span>
			  	</td>
            	<td colspan="7" align="center">
            	<input class="info-btn small-btn w100" type="submit" value=" 查 询"/>&nbsp;&nbsp;&nbsp;&nbsp;
           		<input id="exportPayBillRevenue" class="weak-btn small-btn w150" type="button" value="导出查询对账单"/>&nbsp;&nbsp;&nbsp;&nbsp;
            	<input type="hidden" name="roleId" value="${param.roleId}" />
            	</td>
	          </tr>
	        </table>
        </form>
    </div>   
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
		<display:column title="物业公司" property="pcName" sortable="true"/>
		<display:column title="小区名称" property="groupBuildingName" sortable="true"/>
		<%-- <display:column title="缴费解放号" >
			<c:if test="${row.buyerId>0 and row.isPay==1 and row.paymentWay!=3}">${row.buyerId }</c:if>
			<c:if test="${row.buyerId==0 and row.isPay==1 and row.paymentWay==3 }">${row.sys0UpdUser }</c:if>
		</display:column> --%>
		<display:column title="费用名称" property="billTypeName"/>
		<display:column title="账单月份">
		<c:choose>
		<c:when test="${!empty row.month}"><c:out value="${row.month}"/></c:when>
		<c:otherwise><c:out value="${row.billMonthStart}"/><br /><c:out value="${row.billMonthEnd}"/></c:otherwise>
		</c:choose>
		</display:column>
		
		<display:column title="楼栋号"  >
			<c:out value="${row.buildingName}" /> 
		</display:column>
		<display:column title="单元"  >
			<c:if test="${not empty row.realRoomUnitName}">
				<c:out value="${row.realRoomUnitName}" /> 
			</c:if>
		</display:column>
		<display:column title="房间号" property="realRoomNum"/>
		<display:column title="账单金额(元)" style="text-align: right">
			<fmt:formatNumber value="${row.amount/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
		</display:column>
		<display:column title="抵扣额" style="text-align: right">
			<fmt:formatNumber value="${row.deduPrice/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
		</display:column>
		<display:column title="实缴额" style="text-align: right">
			<fmt:formatNumber value="${row.succPayAmount/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
		</display:column>
		<display:column title="补贴额" style="text-align: right">
			<c:choose>
			  <c:when test="${row.isPay==1&& row.financeStatus == 1}">
				  <fmt:formatNumber value="${(row.amount-row.deduPrice - row.succPayAmount)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
			  </c:when>
			  <c:when test="${row.isPay==1&& row.financeStatus == 0}">
				  <fmt:formatNumber value="${(row.amount-row.succPayAmount)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
			  </c:when>
			  <c:when test="${empty row.financeStatus && !empty row.succPayAmount}">
				  <fmt:formatNumber value="${(row.amount-row.succPayAmount)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
			  </c:when>
			  <c:otherwise>0.00</c:otherwise>
			</c:choose>
		</display:column>
		
		<display:column title="业主姓名" property="propertyProprietorName"/>
		
		<%-- <display:column title="账单状态" >
			<c:if test="${row.isPay==1}">已缴</c:if>
			<c:if test="${row.isPay==2}">未缴</c:if>
		</display:column> --%>
		<display:column title="缴费时间"  maxLength="15">
			<c:if test="${row.isPay==1 }">${row.payTime }</c:if>
		</display:column>
		<display:column title="支付渠道">
			
			<c:choose>
				<c:when test="${row.payMethod==1}">微信支付</c:when>
				<c:when test="${row.payMethod==2}">支付宝</c:when>
				<c:when test="${row.payMethod==3}">银联支付</c:when>
				<c:when test="${row.payMethod==4}">纯粮票支付</c:when>
				<c:when test="${row.payMethod==5}">纯积分支付</c:when>
				<c:when test="${row.payMethod==6}">微信轻应用支付</c:when>
				<c:when test="${row.payMethod==7}">纯优惠券支付</c:when>
				<c:when test="${row.payMethod==8}">纯折扣支付</c:when>
				<c:when test="${row.payMethod==9}">双乾支付</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${row.paymentWay == 3 }">代扣卡续费</c:when>
						<c:when test="${row.paymentWay == 4 }">物业宝抵扣</c:when> 
						<c:otherwise>${row.payMethod}</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</display:column>
		<%-- <display:column title="支付流水号" property="payFlowNo"/> --%>
		<display:column title="结算状态">
			<c:choose>
				<c:when test="${row.tkStatus_wy==-1}">未处理</c:when>
				<c:when test="${row.tkStatus_wy==1}">未结算</c:when>
				<c:when test="${row.tkStatus_wy==2}">申请中</c:when>
				<c:when test="${row.tkStatus_wy==3}">已结算</c:when>
				<c:otherwise>${row.tkStatus_wy}</c:otherwise>
			</c:choose>
		</display:column>
		<display:column title="账单明细" media="html">
			<a class='blue checkPay' href="../payBill/viewDetail.html?id=${row.id} ">查看明细</a>
		</display:column>
	</display:table>
</div>

<input id="nowDate" class="dsn" type="hidden" name="date" value=""/>

<div class="layer-bill dsn" style="height: auto;padding-top:15px;padding-bottom:20px;">
  <form enctype="multipart/form-data" action="../payBill/importPayBill.html" method="post"> 
    <h2>第一步:下载账单模板</h2>
    <div class="bs-example bgebeb">
	<table class="info-list" border="0">
         <tr>
           <td style="width: 60px;"><div class="list-name">小区</div></td>
           <td colspan="3">
           	<%-- <select style="width: 140px;" class="select_normal" onchange="onChangeGB(this);">
		    	<option value="">选择管理处</option>
		    	<c:forEach items="${managements }" var="mgt" >
		    		<option value="${mgt.id}">${mgt.name}</option>
		    	</c:forEach>
		    </select>
           </td>
           <td style="width: 40px;"><div class="list-name">小区</div></td>
           <td> --%>
           	<select style="width: 300px;" id="gbSelect" class="select_normal">
		    	<option value="-">选择小区</option>
		    	<c:forEach items="${gbList }" var="gb" >
		    		<option value="${gb.id}">${gb.name}</option>
		    	</c:forEach>
		    </select>
           </td>
         </tr>
         <tr>
           <td><div class="list-name">账单月份</div></td>
           <td>
           	<select style="width: 140px;" id="erportMonth" class="select_normal w140">
		    	<c:forEach var="month" begin="1" end="12">
		    		<option value="${month }">${month }月</option>
		    	</c:forEach>
		    </select>
           </td>
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
		         <input id="excelFileInput" name="excelFile" type="file" style="width: 300px;" title="上传新账单" />
           </td>
           <td><input id="uploadBtn" class="input-btn w160" type="submit" value="上传" /></td>
         </tr>
     </table>
     </div>
	</form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker01.js"></script>
<script type="text/javascript" src="../js/DatePicker.js"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript" >
	$("#downLoadBtn").click(function() {
		var groupbuildingId = $("#gbSelect").val();
		var groupbuildingName = $("#gbSelect option:selected").text();
		var erportMonth = $("#erportMonth").val();
		if(groupbuildingId==null || groupbuildingId==''){
			alert('您当前没有选中导出小区，不能下载模板!');
			return false;
		}
		location.href=  "../payBill/exportPayBillTemplate.html?groupbuildingId="+groupbuildingId+"&erportMonth="+erportMonth+"&groupbuildingName="+groupbuildingName;
	});
	
	$("#exportPayBillRevenue").click(function(){
		if($(".empty").length){//表行有空行，即无查询结果
			alert("没有可导出的对账单信息。");
			return false;
		}
		window.location.href="../payBill/exportPayBillRevenue.html?isRevenue=true";
	});
	

	//上传文件校验
	$("#uploadBtn").click(function() {
		var fullFileName = $("#excelFileInput").val(); //选中文件的全名，包含路径
		var d = fullFileName.length - ".xls".length;
		if (fullFileName == "") {
			alert("请先选中一个上传文件");
			return false;
		} else if (d > 0 && fullFileName.lastIndexOf(".xls") != d) {
			alert("上传的必须是后缀名为xls的Excel文件，请先下载模板进行编辑后再上传");
			return false;
		}
	});

	$(".markByManual").click(
			function() {
				if (window.confirm('您确定要手工标为已缴费？')) {
					var markByManualURL = $(this).attr("href");
					var x = $(this);
					$.ajax({//使用ajax请求
						type : "GET",
						url : markByManualURL,
						async : false,
						success : function(data, textStatus) {
							alert("操作提示：" + data);
							x.hide();
							$('#nowDate').appendDtpicker();
							var nowSec = new Date().getSeconds();
							x.parent("td").prev("td").html(
									$('#nowDate').val() + ":" + nowSec);
							x.parent("td").prev("td").prev("td").html('已缴');
							x.parent("td").prev("td").prev("td").prev("td")
									.html('物业公司手工标记');
						},
					});
					return false;
				} else {//取消操作
					return false;
				}
			});
	function onChangeGB(mgt) {
		document.getElementById("gbSelect").innerHTML = "";//清空之前的选项
		jQuery.ajax({
			url : "../payBill/getGroupbuildingByMgtId.html",
			cache : false,
			dataType : "json",
			async : false,
			data : "mgtId=" + mgt.value,
			success : function(data) {
				$.each(JSON.parse(data), function(i, item) {
					$(
							"<option value='" + item.id + "'>" + item.name
									+ "</option>").appendTo($("#gbSelect"));
				});
			}
		});
	}

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
