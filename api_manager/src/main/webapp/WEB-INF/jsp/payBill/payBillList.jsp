<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业缴费管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css"/>
<link rel="stylesheet" type="text/css" href="../css/style.css"/>
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css"/>
</head>

<body>
<div class="info">
    <h2>物业缴费管理 </h2>
    <form name="searchForm" id="searchForm" method="post" action="..${formUrl}">
    <div class="bs-example bgebeb">
	        <table class="info-list" border="0">
	          <tr>
	            <td width="90"><div class="list-name">物业公司：</div></td>
	            <td width="130">
	            	<input type="text" class="input_text w120 pp" name="pcName" value='${param.pcName }'/>
	            </td>
	            
	            <td width="90"><div class="list-name">小区名：</div></td>
	            <td width="130">
	            	<input type="text" class="input_text w120 pp" value="${param.gbName }" name="gbName"/>
	            </td>
	            <td width="90"><div class="list-name">业主姓名：</div></td>
	            <td width="130"><input type="text" class="input_text w120 pp" value="${param.ppName }" name="ppName"/></td>
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
	            <td><div class="list-name">账单月份：</div></td>
	            <td><input type="text" id="begintime" class="input_text w120 pp" value="${param.pbMonth }" title="如：2014-07" name="pbMonth" type="text" onclick="setmonth(this)" /></td>
	            <td><div class="list-name">账单状态：</div></td>
	            <td>
	            	<select class="select_normal w131" name="isPay">
	            			<option value="-1">全部</option>
		                    <option value="1" <c:if test="${param.isPay==1 }"> selected="selected"</c:if> >已缴费</option>
		                    <option value="2" <c:if test="${param.isPay==2 }"> selected="selected"</c:if> >未缴费</option>
		                    <option value="3" <c:if test="${param.isPay==3 }"> selected="selected"</c:if> >部分已缴</option>
		                    <option value="4" <c:if test="${param.isPay==4 }"> selected="selected"</c:if> >已缴费或部分已缴</option>
                    </select>
	            </td>
	            
	            <td><div class="list-name">缴费方式：</div></td>
	            <td>
	            	<select class="select_normal w131" name="paymentWay">
	            			<option value="-1">全部</option>
		            		<option value="1" <c:if test="${param.paymentWay==1 }"> selected="selected"</c:if>>用户在线支付</option>
		            		<option value="2" <c:if test="${param.paymentWay==2 }"> selected="selected"</c:if>>物业公司手工标记</option>
		            		<option value="3" <c:if test="${param.paymentWay==3 }"> selected="selected"</c:if>>代扣卡续费</option>
		            		<option value="4" <c:if test="${param.paymentWay==4 }"> selected="selected"</c:if>>物业宝抵扣</option>
		            		<option value="5" <c:if test="${param.paymentWay==5 }"> selected="selected"</c:if>>银行托收</option>
	            	</select>
	            </td>
	            
	          </tr>
	          <tr>
	            <td><div class="list-name">缴费解放号：</div></td>
	            <td><input type="text" class="input_text w120 pp" value="${param.buyerId }" name="buyerId"/></td>
           		<td><div class="list-name">费用名称：</div></td>
            	<td><input type="text" class="input_text w120 pp" value="${param.billTypeName }" name="billTypeName"></td>
			  	<td><div class="list-name">缴费时间：</div></td>
            	<td colspan="3"><input id="date01" value='${date01 }' name="date01" type="text" class="input_text icon_dt pp"  title="请选择起始时间" /> 至 <input  type="text" class="input_text icon_dt pp" id="date02"  value='${date02 }'  name="date02" title="请选择结束时间"/></td>	   
			  </tr>
	        </table>
    </div>     
    <div class="mtop10">
        <table class="info-list" border="0">		  
		  <tr>
           	<td align="center">
           		<input class="info-btn small-btn w100" type="submit" value=" 查 询"/>&nbsp;&nbsp;&nbsp;&nbsp;
           		<input id="exportPayBill" class="weak-btn small-btn w150" type="button" value="导出查询对账单"/>&nbsp;&nbsp;&nbsp;&nbsp;
           		<!-- <input id="importBill" class="weak-btn small-btn w100" type="button" value="导入物业账单"/> -->
           		<input type="hidden" name="roleId" value="${param.roleId}" />
           	</td>
          </tr>
          
        </table>
    </div>   
    </form> 
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
		<display:column title="<input type='checkbox' id='printDoor' onclick='setRelateCheckBox(this)'/>">
			<input type="checkbox" class="printItem" value="${row.id}" gbId="${row.groupBuildingId}"/>
		</display:column>
		<display:column title="物业公司" property="pcName" sortable="true"/>
		<display:column title="小区名称" property="groupBuildingName" sortable="true"/>
		<display:column title="缴费解放号" >
			<c:if test="${row.buyerId>0 and row.isPay==1 and row.paymentWay!=3}">${row.buyerId }</c:if>
			<c:if test="${row.buyerId==0 and row.isPay==1 and row.paymentWay==3}">${row.sys0UpdUser }</c:if>
			<c:if test="${row.buyerId==0 and row.paymentWay==4}">${row.sys0UpdUser }</c:if>
		</display:column>
		<display:column title="费用名称" property="billTypeName"/>
		<display:column title="账单月份" property="month" />
		<display:column title="楼栋号"  >
			<c:out value="${row.buildingName}" /> 
		</display:column>
		<display:column title="单元"  >
			<c:if test="${not empty row.realRoomUnitName}">
				<c:out value="${row.realRoomUnitName}" /> 
			</c:if>
		</display:column>
		<display:column title="房间号" property="realRoomNum"/>
		<display:column title="账单金额(元)" >
			<c:out value="${row.amount/100.0} "></c:out>
		</display:column>
		<display:column title="抵扣额" >
			<c:out value="${row.deduPrice/100.0 }"></c:out>
		</display:column>
		<display:column title="补贴额" >
			<c:choose>
			  <c:when test="${row.isPay==1&& row.financeStatus == 1}"><c:out value="${row.hbAmount/100.0} "></c:out></c:when>
			  <c:when test="${row.isPay==1&& row.financeStatus == 0}"><c:out value="${(row.amount-row.succPayAmount)/100.0} "></c:out></c:when>
			  <c:when test="${empty row.financeStatus && !empty row.succPayAmount}"><c:out value="${(row.amount-row.succPayAmount)/100.0} "></c:out></c:when>
			  <c:otherwise>0.0</c:otherwise>
			</c:choose>
		</display:column>
		
		<display:column title="业主姓名" property="propertyProprietorName"/>
		<display:column title="缴费方式" >
			<c:if test="${row.paymentWay==1}">用户在线支付</c:if>
			<c:if test="${row.paymentWay==2}">物业公司手工标记</c:if>
			<c:if test="${row.paymentWay==3}">代扣卡续费</c:if>
			<c:if test="${row.paymentWay==4}">物业宝抵扣</c:if>
			<c:if test="${row.paymentWay==5}">银行托收</c:if>
		</display:column>
		
		<display:column title="账单状态" >
		  <c:choose>
		  	<c:when test="${row.isPay==1}">已缴</c:when>
		  	<c:when test="${row.isPay==2 && row.financeStatus!=1}">未缴</c:when>
		  	<c:otherwise>部分已缴</c:otherwise>
		  </c:choose>
		</display:column>
		<display:column title="缴费时间"  maxLength="15">
			<c:if test="${row.isPay==1 || row.financeStatus == 1}"><c:choose><c:when test="${not empty row.payTime}">${row.payTime}</c:when><c:otherwise>${row.sys0UpdTime}</c:otherwise></c:choose>
			</c:if>
		</display:column>
		<display:column title="操作" >
			<c:if test="${row.isPay==2}"><a class="blue markByManual" href="#" data-href = "../payBill/markByManual.html?id=${row.id }" >手动标为已缴</a></c:if>
			<c:if test="${row.isPay==2 && row.financeStatus!=1}"><a class="blue delPayBill" href="#" data-href = "../payBill/delPayBill.html?id=${row.id }" >删除</a></c:if>
			<a class="blue" href="javascript:void(0);" onclick="showBatchPrintDialog(${row.id})">打印</a>
		</display:column>
		<display:column title="账单明细" media="html">
			<a class='blue checkPay' href="../payBill/viewDetail.html?id=${row.id} ">查看明细</a>
		</display:column>
	</display:table>
	<br/><input type="button" class="info-btn save-set-prize-info-btn" value="批量打印账单" onclick="showBatchPrintDialog();"/>
</div>

<input id="nowDate" class="dsn" type="hidden" name="date" value=""/>

<div class="layer-bill dsn" style="height: auto;padding-top:15px;padding-bottom:50px;">
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
     <span class="red dsn" id="uploadTips">账单正在上传中，请稍候…</span>
     </div>
	</form>
</div>
<%-- 打印弹框选择 --%>
<div class="batchPrintDialog dsn" style="height: auto;padding-top:15px;padding-bottom:50px;width: 300px;">
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
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker01.js"></script>
<script type="text/javascript" src="../js/DatePicker.js"></script>
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
	
	$("#exportPayBill").click(function(){
		if($(".empty").length){//表行有空行，即无查询结果
			alert("没有可导出的对账单信息。");
			return false;
		}
		window.location.href="../payBill/exportPayBill.html";
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
		
		$("#uploadTips").show();
	});

	$(".markByManual").click(
			 function() {
				if (window.confirm('您确定要手工标为已缴费？')) {
					var markByManualURL = $(this).attr("data-href");
					var x = $(this);
					$.ajax({//使用ajax请求
						//type : "GET",
						url : markByManualURL,
						async : false,
						success : function(data) {
							if(data.status == "0000"){
								alert("操作提示：" + data.message);
								x.hide();
								$('#nowDate').appendDtpicker();
								var nowSec = new Date().getSeconds();
								x.parent("td").prev("td").html(
										$('#nowDate').val() + ":" + nowSec);
								x.parent("td").prev("td").prev("td").html('已缴');
								x.parent("td").prev("td").prev("td").prev("td")
										.html('物业公司手工标记');
							}else{
								alert(data.message);
							}
						},
						error: function(){
							parent.location.href = "../security/loginPage.html";
							//window.assign.href = "../security/loginPage.html";
						}
					});
					return false;
				} else {//取消操作
					return false;
				}
			}
			); 
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
					$(
							"<option value='" + item.id + "'>" + item.name
									+ "</option>").appendTo($("#gbSelect"));
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
	function showBatchPrintDialog(payBillId){
		if(payBillId){
			$("#printType").val("one");
			$("#printPayBillId").val(payBillId);
			$("#printBtn").val("打印");
			layerPrint = $.layer({
				type: 1,
				shade: [0.4,'#000000'],
				area: ['auto', 'auto'],
				title: false,
				border : [5, 0.3, '#000'],
				page: {dom : '.batchPrintDialog'}
		 	});
		} else {
			$("#printType").val("ones");
			$("#printBtn").val("批量打印");
			var payBillIds = new Array();
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
		var gbId = "${gbId}";
		var pageSize = $(":radio[name='pageSize']:checked").val();
		if($("#printType").val()=="ones"){
			var payBillIds = new Array();
			var gbIds = new Array();
			$(".printItem").each(function(){
				if($(this).is(":checked")){
					payBillIds.push($(this).val());
					gbIds.push($(this).attr("gbId"));
				}
			});
			if("${pageType}"!="zq"){
				gbId = JSON.stringify(gbIds);
			}
			if(payBillIds.length==0){
				alert("请选择要打印的账单！");
			} else {
				window.open("${pageContext.request.contextPath}/payBillPrint/batchPprintByBillId.html?type=${pageType}&gbId="+gbId+"&pageSize="+pageSize+"&payBillIds="+JSON.stringify(payBillIds)+"&feeType=${feeType}");
			}
		} else {
			window.open("${pageContext.request.contextPath}/payBill/print.html?id="+$("#printPayBillId").val()+"&gbId="+gbId+"&pageSize="+pageSize+"&feeType=${feeType}");
		}
	}
</script>
</html>
