<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>周期账单列表
</title>
<link rel="stylesheet" type="text/css" href="../css/common.css"/>
<link rel="stylesheet" type="text/css" href="../css/style.css"/>
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css"/>
<link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>

<body>
<div class="info">
    <h2>物业缴费管理</h2>
    <form name="searchForm" id="searchForm" method="post" action="../payBill/listPeriod.html">
		<%--v349大家共用账单查询方法--%>
		<input type="hidden" value="${param.cycleType}" name="cycleType"/>
		<input type="hidden" value="${param.gbId}" name="gbId"/>
		<input type="hidden" value="${param.feeType}" name="feeType"/>
		<input type="hidden" value="${param.cycleId}" name="cycleId"/>
		<%-- 从【抄表收费项-账单详情】进入需要该条件 --%>
		<input type="hidden" value="${param.pbtIsPropfee}" name="pbtIsPropfee"/>
    <div class="bs-example bgebeb" style="padding-right:100px">
        <table class="info-list" border="0">
          <tr>
            <td width="90"><div class="list-name">物业公司：</div></td>
            <td width="130">
            	<input type="text" class="input_text w120 pp" name="pcName" value='${param.pcName }'>
            </td>
            <td width="90"><div class="list-name">小区名：</div></td>
            <td width="130">
				<%--<select name="gbName" class="select_normal select2_class groupBuiliding_select" datatype="*" nullmsg="请选择小区！">
					<option value="">选择小区</option>
					<c:forEach items="${gbList}" var="gb">
						<option value="${gb.name}" <c:if test="${param.gbName == gb.name }">selected="selected"</c:if>>${gb.name}</option>
					</c:forEach>
				</select>--%>
				<select id="groupBuiliding_select" name="gbName" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
					<option value="">选择小区</option>
				</select>
            </td>
           
            <td width="90"><div class="list-name">业主姓名：</div></td>
            <td width="130"><input type="text" class="input_text w120 pp" value="${param.ppName }" name="ppName"></td>
            
          </tr>
          
          <tr>
            <td><div class="list-name">楼栋：</div></td>
            <td>
	            <input type="text" class="input_text w120 pp" name="bName" value='${param.bName }'/>
            </td>
            
            <td><div class="list-name">单元：</div></td>
            <td>
            	<input type="text" class="input_text w120 pp" value="${param.unitName }" name="unitName"/>
            </td>
            <td><div class="list-name">房号：</div></td>
            <td><input type="text" class="input_text w120 pp" value="${param.roomNum }" name="roomNum"/></td>
          </tr>
			<tr>
				<td width="90"></td>
				<td colspan="5"></td>
			</tr>
			<tr class="border-top01">
				<td width="90"></td>
				<td colspan="5"></td>
			</tr>
          <tr>
            <td><div class="list-name">费用名称：</div></td>
            <td><input type="text" class="input_text w120 pp" value="${param.billTypeName }" name="billTypeName"></td>
		  <td width="90"><div class="list-name">缴费解放号：</div></td>
		  <td><input type="text" class="input_text w120 pp" value="${param.buyerId }" name="buyerId"></td>
		  <td width="90"><div class="list-name">缴费时间：</div></td>
		  <td width="130"><input id="date01" value='${date01 }' name="date01" type="text" class="input_text icon_dt pp"  title="请选择起始时间" >
			  至 <input  type="text" class="input_text icon_dt pp" id="date02"  value='${date02 }'  name="date02" title="请选择结束时间"></td>
          </tr>

          <tr>
          	<td width="90"><div class="list-name">账单状态：</div></td>
            <td width="130"><select class="select_normal w131" name="isPay">
                    <option value="-1">全部</option>
                    <option value="1" <c:if test="${param.isPay==1 }"> selected="selected"</c:if> >已缴费</option>
                    <option value="2" <c:if test="${param.isPay==2 }"> selected="selected"</c:if> >未缴费</option>
					<option value="3" <c:if test="${param.isPay==3 }"> selected="selected"</c:if> >部分已缴</option>
					<option value="4" <c:if test="${param.isPay==4 || isPay == 4 }"> selected="selected"</c:if> >已缴费或部分已缴</option>
					<option value="5" <c:if test="${param.isPay==5 }"> selected="selected"</c:if> >未缴费或部分已缴</option>
                    </select>
            </td>
            
            <td width="90"><div class="list-name">缴费方式：</div></td>
            <td width="130">
            	<select class="select_normal w131" name="paymentWay">
            		<option value="-1">全部</option>
					<option value="-2" <c:if test="${param.paymentWay==-2 }"> selected="selected"</c:if>>解放区支付</option>
					<option value="1" <c:if test="${param.paymentWay==1 }"> selected="selected"</c:if>>&nbsp;&nbsp;--用户在线支付</option>
					<option value="3" <c:if test="${param.paymentWay==3 }"> selected="selected"</c:if>>&nbsp;&nbsp;--代扣卡续费</option>
					<option value="4" <c:if test="${param.paymentWay==4 }"> selected="selected"</c:if>>&nbsp;&nbsp;--物业宝抵扣</option>
					<option value="2" <c:if test="${param.paymentWay==2 }"> selected="selected"</c:if>>现金缴费</option>
					<option value="5" <c:if test="${param.paymentWay==5 }"> selected="selected"</c:if>>银行托收</option>
            	</select>
            </td>
			  <td><div class="list-name">账单周期：</div></td>
			  <td>
				  <input type="text" id="begintime" class="input_text w120 " <c:choose><c:when test="${empty billMonthStart}">value="${param.billMonthStart }"</c:when><c:otherwise>value="${billMonthStart }"</c:otherwise></c:choose>  title="如：2014-07" name="billMonthStart" type="text" onclick="setmonth(this)" readonly="readonly" />
				  至
				  <input type="text" id="begintime" class="input_text w120 " value="${param.billMonthEnd }" title="如：2014-07" name="billMonthEnd" type="text" onclick="setmonth(this)" readonly="readonly"/>
			  </td>
          </tr>

        </table>
    </div>   
    <div class="mtop10">
        <table class="info-list" border="0">		  
		  <tr>
           	<td align="center">
           	<input class="info-btn small-btn w100" type="submit" value=" 查 询" id="queryBtn">&nbsp;&nbsp;&nbsp;&nbsp;
           	<input id="exportPayBill" class="weak-btn small-btn w150" type="button" value="导出查询对账单">&nbsp;&nbsp;&nbsp;&nbsp;
           	<!-- <input id="importBill" class="weak-btn small-btn w100" type="button" value="导入物业账单"> -->
           	</td>
          </tr>
          
        </table>
    </div>   
    </form>
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
		<c:if test="${feeType ==null || feeType == ''}">
			<display:column title="<input type='checkbox' id='printDoor' onclick='setRelateCheckBox(this)'/>">
				<c:if test="${empty row.tGroupBuildingBillCycleConfigFId}">
					<input type="checkbox" class="printItem" value="${row.id}" gbId="${row.groupBuildingId}"/>
				</c:if>
			</display:column>
		</c:if>
		<display:column title="物业公司" property="pcName" sortable="true"/>
		<display:column title="小区名称" property="groupBuildingName" sortable="true" class="gbName"/>
		<display:column title="缴费解放号" >
			<c:if test="${row.buyerId>0 and row.isPay==1}">${row.buyerId }</c:if>
			<c:if test="${row.buyerId==0 and row.isPay==1 and row.paymentWay==3 }">${row.sys0UpdUser }</c:if>
		</display:column>
		<display:column title="费用名称" property="billTypeName" class="billName"/>
		<%-- <display:column title="周期缴费方式">
			<c:if test="${row.paytimeType eq 2 and row.cycleType ne 2}">固定周期</c:if>
			<c:if test="${row.cycleType eq 2}">选择性周期</c:if>
		</display:column> --%>
		<display:column title="账单开始月份" property="billMonthStart" class="startMonth"/>
		<display:column title="账单截止月份" property="billMonthEnd" />
		<display:column title="缴费时长" property="billMonthSize" />
		<display:column title="楼栋号" class="buildingName">
			<c:out value="${row.buildingName}"/> 
		</display:column>
		<display:column title="单元" class="unitName">
			<c:if test="${not empty row.realRoomUnitName}">
				<c:out value="${row.realRoomUnitName}" /> 
			</c:if>
		</display:column>
		<display:column title="房间号" property="realRoomNum" class="roomName"/>
		<display:column title="账单金额(元)" style="text-align: right">
			<fmt:formatNumber value="${(row.totalSuccAmount+0.00000000001)/100.0 + 0.000001}" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
		</display:column>
		<display:column title="本期应收" class="amount" style="text-align: right">
			<c:choose>
				<c:when test="${not empty feeType}">
					<fmt:formatNumber value="${(row.amountBigDecimal+0.00000000001)/100.0 + 0.000001}" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
				</c:when>
				<c:otherwise>
					<fmt:formatNumber value="${(row.amount+0.00000000001)/100.0 + 0.000001}" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
				</c:otherwise>
			</c:choose>
		</display:column>
		<display:column title="往月欠费" style="text-align: right">
			<c:if test="${row.isUnpaidFee}">
				<fmt:formatNumber value="${(row.lastUnpaids+0.00000000001)/100.0 + 0.000001}" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
			</c:if>
		</display:column>
		<c:if test="${feeType ==null || feeType == ''}">
			<display:column title="抵扣额" style="text-align: right">
				<fmt:formatNumber value="${(row.deduPrice+0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
			</display:column>
			<display:column title="补贴额" style="text-align: right">
				<c:choose>
					<c:when test="${row.isPay==1&& row.financeStatus == 1 && row.paymentWay!=2}">
						<fmt:formatNumber value="${(row.amount-row.deduPrice - row.succPayAmount+0.00000000001)/100.0}" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
					</c:when>
					<c:when test="${row.isPay==1&& row.financeStatus == 0}">
						<fmt:formatNumber value="${(row.amount-row.succPayAmount+0.00000000001)/100.0}" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
					</c:when>
					<c:when test="${empty row.financeStatus && !empty row.succPayAmount}">
						<fmt:formatNumber value="${(row.amount-row.succPayAmount+0.00000000001)/100.0}" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
					</c:when>
					<c:otherwise>0.00</c:otherwise>
				</c:choose>
			</display:column>
		</c:if>

		<display:column title="业主姓名" property="propertyProprietorName"/>
		<display:column title="缴费方式" >
			<c:if test="${row.paymentWay==1}">用户在线支付</c:if>
			<c:if test="${row.paymentWay==2}">现金缴费</c:if>
			<c:if test="${row.paymentWay==3}">代扣卡续费</c:if>
			<c:if test="${row.paymentWay==4}">物业宝抵扣</c:if>
			<c:if test="${row.paymentWay==5}">银行托收</c:if>
		</display:column>
		<display:column title="账单状态" >
			<c:if test="${row.isPay==1}">已缴</c:if>
			<c:if test="${row.isPay==2}">
			 <c:choose>
				 <c:when test="${row.financeStatus == 1}">部分已缴</c:when>
				 <c:otherwise>未缴</c:otherwise>
			 </c:choose>
			</c:if>
		</display:column>
		<display:column title="缴费时间"  maxLength="15">
			<c:if test="${row.isPay==1 }"><c:choose><c:when test="${not empty row.payTime }">${row.payTime }</c:when><c:otherwise>${row.sys0UpdTime }</c:otherwise></c:choose></c:if>
		</display:column>
		<c:if test="${feeType ==null || feeType == ''}"><%-- （王冲）总帐单才打印 --%>
			<display:column title="操作" >
				<c:if test="${row.isPay==2 && row.financeStatus != 1}">
					<a class="blue editPayBillAmount" href="../payBill/goToEditPayBillAmount.html?id=${row.id }&gbName=${row.groupBuildingName}&billName=${row.billTypeName}" >金额修改</a>
				</c:if>
				<c:if test="${row.isPay==2}">
					<%--物业宝部分抵扣--不进行手工标记V509-2017-06-14 21：26--%>
					<c:choose>
						<c:when test="${row.financeStatus == 1}"></c:when>
						<c:otherwise><a class="blue markByManual" href="javascript:void(0)" onclick="showMarkDialog(${row.id}, this)">手动标为已缴</a></c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${row.isPay==2 && row.financeStatus != 1}"><a class="blue delPayBill" href="#" data-href = "../payBill/delPayBill.html?id=${row.id }" >删除</a></c:if>
				<c:if test="${empty row.tGroupBuildingBillCycleConfigFId}">
					<a class="blue" href="javascript:void(0);" onclick="showBatchPrintDialog(${row.id}, ${row.groupBuildingId})">打印</a>
				</c:if>
			</display:column>
		</c:if>
		<display:column title="账单明细" media="html">
			<a class='blue checkPay' href="../payBill/viewDetail.html?id=${row.id}&feeType=${feeType}">查看明细</a>
		</display:column>
	</display:table>
	<c:if test="${feeType ==null || feeType == ''}">
		<br/><input type="button" class="info-btn save-set-prize-info-btn" value="批量打印账单" onclick="showBatchPrintDialog();"/>
	</c:if>
</div>

<%-- 打印弹框选择 --%>
<div class="batchPrintDialog dsn" style="height: auto;padding-top:15px;padding-bottom:50px;width: 300px;">
	 <input id="printPayBillId" type="hidden" value=""/>
	 <input id="printType" type="hidden" value=""/>
	 <input id="printGbId" type="hidden" value=""/>
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
<div class="batchPrintDialog02 dsn" style="height: auto;padding-top:15px;padding-bottom:50px;width: 300px;">
	 <input id="printPayBillId" type="hidden" value=""/>
	 <input id="printType" type="hidden" value=""/>
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
	           	   <input class="input-btn w160" id="printBtn" type="button" value="批量打印" onclick="batchPrint();"/>
	           	   <input class="input-btn w160" type="button" value="取消" onclick="closeBatchPrintDialog();"/>
	           </td>
	         </tr>
	     </table>
     </div>
</div>

<div class="markDiv dsn" style="height: auto;padding-top:15px;padding-bottom:50px;width: 400px;">
	 <input id="payBillId" type="hidden" value=""/>
	 <input id="gbName" type="hidden" value=""/>
	 <input id="buildingName" type="hidden" value=""/>
	 <input id="unitName" type="hidden" value=""/>
	 <input id="roomName" type="hidden" value=""/>
	 <input id="startMonth" type="hidden" value=""/>
	 <input id="billName" type="hidden" value=""/>
	 <input id="amount" type="hidden" value=""/>
	 <h2 style="margin-left: 1em;">现金缴费-手动标记</h2>
     <div class="bs-example bgebeb">
	     <table class="info-list" border="0">
	     	 <tr>
	     	 	<td class="address"></td>
	     	 	<td style="width:100px;text-align: right;" class="wuyeFeeTitle">本月物业费：</td>
	     	 	<td class="wuyeFee" style="width:50px;"></td>
	     	 </tr>
	     	 <tr>
	     	 	<td colspan="3">确定该用户已缴费？</td>
	     	 </tr>
	     	 <tr>
	     	 	<td colspan="3">手机号：<input type="text" id="mobile" class="input_text w240 pp" maxlength="11"/></td>
	     	 </tr>
	     	 <tr><td colspan="3" style="color:red;">（填写手机号后，标记完成会自动给用户发送费用缴清短信）</td></tr>
	         <tr>
	           <td style="text-align: right;">
	           	   <input class="input-btn w160" type="button" value="确认" onclick="mark();"/>
	           	   <input class="input-btn w160" type="button" value="取消" onclick="closeMarkDialog();"/>
	           </td>
	         </tr>
	     </table>
     </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/DatePicker.js?v1"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker01.js"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript" >
	$(function() {
		$('.select2_class').select2();

		var gbName = '${param.gbName}';
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
				$("#groupBuiliding_select").html(strHtml);
			}
		});
	});
	var markDialog = null;
	var hrefMark = null;   //手动标记a的标签
	function showMarkDialog(payBillId, o){
	    hrefMark = $(o);
		var trObj = $(o).parent().parent();
		
		var gbName = $.trim(trObj.find(".gbName").text());
		var address = gbName;
		var buildingName = $.trim(trObj.find(".buildingName").text());
		if(buildingName!=""){
			address += buildingName+"栋";
		}
		var unitName = $.trim(trObj.find(".unitName").text());
		if(unitName!=""){
			address += unitName+"单元";
		}
		var roomName = $.trim(trObj.find(".roomName").text());
		address += roomName;
		$(".markDiv .address").html(address);
		$(".markDiv .wuyeFee").html($.trim(trObj.find(".amount").text()));
		$(".markDiv .wuyeFeeTitle").html("本月"+$.trim(trObj.find(".billName").text())+"：");
		
		$(".markDiv #payBillId").val(payBillId);
		$(".markDiv #gbName").val(gbName);
		$(".markDiv #buildingName").val(buildingName);
		$(".markDiv #unitName").val(unitName);
		$(".markDiv #roomName").val(roomName);
		$(".markDiv #startMonth").val($.trim(trObj.find(".startMonth").text()));
		$(".markDiv #billName").val($.trim(trObj.find(".billName").text()));
		$(".markDiv #amount").val($.trim(trObj.find(".amount").text()));
		$("#mobile").val("");
		
		markDialog = $.layer({
			type: 1,
			shade: [0.4,'#000000'],
			area: ['auto', 'auto'],
			title: false,
			border : [5, 0.3, '#000'],
			page: {dom : '.markDiv'}
	 	});
	}
	
	function mark(){
		var mobile = $.trim($("#mobile").val());
		if(mobile!="" && !/^1[0-9]{10}$/.test(mobile)){
			alert("手机号码格式不对！");
		} else {
			if (window.confirm('您确定要手工标为已缴费？')) {
				var payBillId = $(".markDiv #payBillId").val();
				var gbName = $(".markDiv #gbName").val();
				var buildingName = $(".markDiv #buildingName").val();
				var unitName = $(".markDiv #unitName").val();
				var roomName = $(".markDiv #roomName").val();
				var startMonth = $(".markDiv #startMonth").val();
				var billName = $(".markDiv #billName").val();
				var amount = $(".markDiv #amount").val();

				$.post("../payBill/markByManual.html", {"id":payBillId, "mobile":mobile, "gbName":gbName, "buildingName":buildingName, "unitName":unitName,
					"roomName":roomName, "startMonth":startMonth, "billName":billName, "amount":amount}, function(data){
					if(data.status == "0000"){
						closeMarkDialog();
						alert("操作提示：" + data.message);

						//不要用$("#queryBtn").click();，该方法会触发重新查询，改用JS修改当前行的某些显示即可
                        hrefMark.hide();
                        hrefMark.siblings("a.editPayBillAmount").hide();
                        hrefMark.siblings("a.delPayBill").hide();
                        hrefMark.parent("td").prev("td").html(getNowFormatDate());
                        hrefMark.parent("td").prev("td").prev("td").html('已缴');
                        hrefMark.parent("td").prev("td").prev("td").prev("td").html('现金缴费');
					}else{
                        alert(data.message);
					}
				});
			}
		}
	}
	
	function closeMarkDialog(){
		layer.close(markDialog);
	}

	$("#gbSelect").change(function(){
		var gbId = $("#gbSelect").val();
		
		$.ajax({//选择市，更新区
			  url: "../payBill/qryPayBillTypeListByGbId.html",
			  dataType:"json",
			  data:"gbId="+gbId,
			  success: function(data){
				$("#pbTypeSelect").empty();
			    $.each(JSON.parse(data), function(i, item) {
			    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#pbTypeSelect"));
			    });
			  }
		});
	});

	//下载模板
	$("#downLoadBtn").click(function() {
		var groupbuildingId = $("#gbSelect").val();
		var groupbuildingName = $("#gbSelect option:selected").text();
		
		var payBillTypeName = $("#pbTypeSelect option:selected").text();
		
		if(groupbuildingId==null || groupbuildingId==''){
			alert('您当前没有选中导出小区，不能下载模板!');
			return false;
		}
		if(payBillTypeName==null || payBillTypeName==''){
			alert('当前小区没有配置账单类型，不能下载模板!');
			return false;
		}
		
		location.href=  "../payBill/exportPayBillPeriodTemplate.html?groupbuildingId=" 
				+ groupbuildingId
				+"&groupbuildingName="+groupbuildingName
				+"&payBillTypeName="+payBillTypeName;
	});
	
	$("#exportPayBill").click(function(){
		if($(".empty").length){//表行有空行，即无查询结果
			alert("没有可导出的对账单信息。");
			return false;
		}
		window.location.href="../payBill/exportPayBillPeriod.html?feeType=${feeType}";
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
		
		$("#uploadTips").show();
	});

	$(".delPayBill").click(
			 function() {
				if (window.confirm('您确定要删除该条记录？')) {
					var markByManualURL = $(this).attr("data-href");
					var x = $(this);
					$.ajax({//使用ajax请求
						//type : "GET",
						url : markByManualURL,
						async : false,
						success : function(data) {
							if(data.status == "0000"){
								alert("操作提示：" + data.message);
								$("#searchForm").submit();							
							}else{
								alert("操作提示：" + data.message);
							}
						},
						error: function(){
							alert("操作提示：" + data.message);
						}
					});
					return false;
				} else {//取消操作
					return false;
				}
			}
		); 
	
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
					$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#gbSelect"));
				});
			}
		});
	}
	
	// checkbox全选 
	function setRelateCheckBox(){
		var isOpen = $("#printDoor").is(":checked");
		$(".printItem").each(function(){
			$(this).prop("checked", isOpen);
		});
	}
	
	var layerPrint;
	function showBatchPrintDialog(payBillId, gbId){
		if(payBillId){
			$("#printType").val("one");
			$("#printGbId").val(gbId);
			$("#printPayBillId").val(payBillId);
			$("[id=printBtn]").val("打印");
			layerPrint = $.layer({
				type: 1,
				shade: [0.4,'#000000'],
				area: ['auto', 'auto'],
				title: false,
				border : [5, 0.3, '#000'],
				page: {dom : '.batchPrintDialog02'}
		 	});
			
		} else {
			$("#printType").val("ones");
			$("[id=printBtn]").val("批量打印");
			var payBillIds = [];
			$(".printItem").each(function(){
				if($(this).is(":checked")){
					payBillIds.push($(this).val());
				}
			});
			if(payBillIds.length==0){
				alert("请选择要打印的账单！");
			} else {
				layerPrint = $.layer({
					type: 1,
					shade: [0.4,'#000000'],
					area: ['auto', 'auto'],
					title: false,
					border : [5, 0.3, '#000'],
					page: {dom : '.batchPrintDialog'}
			 	});
			}
		}
	}
	
	function closeBatchPrintDialog(){
		layer.close(layerPrint);
	}
	
	function batchPrint(){
		var gbId = null;
		var pageSize = $(":radio[name='pageSize']:checked").val();
		var pageDivision = $("#pageDivision").val();
		if($("#printType").val()=="ones"){
			var payBillIds = [];
			var gbIds = [];
			$(".printItem").each(function(){
				if($(this).is(":checked")){
					payBillIds.push($(this).val());
					gbIds.push($(this).attr("gbId"));
				}
			});
			if("${pageType}"!="zq"){
				gbId = JSON.stringify(gbIds);
			} else {
				gbId = ["${gbId}"];
			}
			if(payBillIds.length==0){
				alert("请选择要打印的账单！");
			} else {
				window.open("${pageContext.request.contextPath}/payBillPrint/batchPprintByBillId.html?type=${pageType}&gbId="+gbId+"&pageSize="+pageSize+"&payBillIds="+JSON.stringify(payBillIds)+"&feeType=${feeType}&pageDivision="+pageDivision);
			}
		} else {
			window.open("${pageContext.request.contextPath}/payBill/print.html?id="+$("#printPayBillId").val()+"&gbId="+$('#printGbId').val()+"&pageSize="+pageSize+"&feeType=${feeType}&pageDivision="+pageDivision);
		}
	}
</script>
</html>
