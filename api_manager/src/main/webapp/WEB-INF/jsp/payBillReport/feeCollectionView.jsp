<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>收费情况汇总报表</title>
	<link rel="stylesheet" type="text/css" href="../css/common.css"/>
	<link rel="stylesheet" type="text/css" href="../css/style.css"/>
	<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css"/>
	<link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>

<body class="pos-relative">
<div class="info">
	<h2>收费情况汇总报表</h2>
	<form name="searchForm" id="searchForm" method="post" action="../payBillReport/feeCollectionView.html">
		<input type="hidden" name="pmList" id="pmList" value="${pmListStr}"/>
		<input type="hidden" name="gbList" id="gbList" value="${gbListStr}"/>
		<input type="hidden" name="buildingList" id="buildingList" value="${buildingListStr}"/>
		<input type="hidden" name="isSelect" value="1"/>
		<div class="bs-example bgebeb">
			<table class="info-list" border="0">
				<tr>
					<td><div class="list-name">查询月份：</div></td>
					<td><input type="text" id="begintime" class="input_text w120 pp" value="${billMonth}" title="如：2014-07" name="billMonth" type="text" onclick="setmonth(this)" readonly="readonly"/></td>
					<td width=""><div class="list-name">管理处：</div></td>
					<td id="propertyManagementTd">
						<select id="propertyManagement" name="propertyManagementId" class="select_normal select2_class" datatype="*" nullmsg="请选择管理处！">
							<option value="">选择管理处</option>
							<c:forEach var="item" items="${pmList}">
								<option value="${item.id}" <c:if test="${item.id == propertyManagementId}">selected="selected"</c:if>>${item.name}</option>
							</c:forEach>
						</select>
					</td>
					<td width=""><div class="list-name">小区：</div></td>
					<td id="groupBuilidingTd">
						<select id="groupBuiliding" name="gbId" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
							<option value="">选择小区</option>
							<c:forEach var="item" items="${gbList}">
								<option value="${item.id}" <c:if test="${item.id == gbId}">selected="selected"</c:if>>${item.name}</option>
							</c:forEach>
						</select>
					</td>
					<td width=""><div class="list-name">楼栋：</div></td>
					<td id="builidingTd">
						<select id="builiding" name="buildingId" class="select_normal select2_class" datatype="*" nullmsg="请选择楼栋！">
							<option value="">选择楼栋</option>
							<c:forEach var="item" items="${buildingList}">
								<option value="${item.id}" <c:if test="${item.id == buildingId}">selected="selected"</c:if>>${item.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		</div>
		<div class="mtop10">
			<table class="info-list" border="0">
				<tr>
					<td align="center">
						<input class="info-btn small-btn w100" type="submit" value=" 查 询"/>
					</td>
				</tr>

			</table>
		</div>
	</form>
	<table class="mars info-list-02 mtop20">
		<thead>
		<tr class="title" style="text-align: center;">
			<td rowspan="2">物业公司</td>
			<td rowspan="2">管理处</td>
			<td rowspan="2">小区名称</td>
			<td rowspan="2">费用类型</td>
			<td rowspan="2">本期应收</td>
			<td rowspan="2">往期欠费</td>
			<td rowspan="2">本期应收合计</td>
			<td rowspan="2">往期欠费合计</td>
			<td colspan="3">本期已收合计</td>
			<td rowspan="2">本期待收合计</td>
			<td rowspan="2">本期收缴率</td>
			<td rowspan="2">本期欠费率</td>
		</tr>
		<tr class="title" style="text-align: center;">
			<td>解放区缴费</td>
			<td>银行托收</td>
			<td>现金缴费</td>
		</tr>
		</thead>
		<tbody>
		<c:set var="succAmt" value="0"/>
		<c:set var="lastUnpaidAmt" value="0"/>
		<c:set var="succTotalAmt" value="0"/>
		<c:set var="lastUnpaidTotalAmt" value="0"/>
		<c:set var="needPayTotalAmt" value="0"/>
		<c:set var="jfqPayAmt" value="0"/>
		<c:set var="bankPayAmt" value="0"/>
		<c:set var="cashPayAmt" value="0"/>
		<c:forEach items="${resList }" var="feeCollectionViewEntity" varStatus="pcStatus">
			<c:if test="${fn:length(feeCollectionViewEntity.propertyManagementReportEntityList) == 0}">
				<tr>
					<td>${feeCollectionViewEntity.propertyCompanyName}</td>
				</tr>
			</c:if>
			<c:if test="${fn:length(feeCollectionViewEntity.propertyManagementReportEntityList) > 0 }">
				<c:forEach items="${feeCollectionViewEntity.propertyManagementReportEntityList }" var="pmEntity" varStatus="pmStatus">
					<c:if test="${fn:length(pmEntity.groupBuildingReportEntityList) == 0 }">
						<tr>
							<c:if test="${pmStatus.first}">
								<td rowspan="${feeCollectionViewEntity.pmCount}">${feeCollectionViewEntity.propertyCompanyName}</td>
							</c:if>
							<td rowspan="${pmEntity.gbCount}">${pmEntity.propertyManagementName}</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
						</tr>
					</c:if>
					<c:if test="${fn:length(pmEntity.groupBuildingReportEntityList) > 0 }">
						<c:forEach items="${pmEntity.groupBuildingReportEntityList }" var="gbEntity" varStatus="gbStatus">
							<c:if test="${fn:length(gbEntity.feeReportEntityList) == 0 }">
								<tr>
									<c:if test="${pmStatus.first and gbStatus.first}">
										<td rowspan="${feeCollectionViewEntity.pmCount}">${feeCollectionViewEntity.propertyCompanyName}</td>
									</c:if>
									<c:if test="${gbStatus.first}">
										<td rowspan="${pmEntity.gbCount}">${pmEntity.propertyManagementName}</td>
									</c:if>
									<c:if test="${pfStatus.first}">
										<td rowspan="${gbEntity.pfCount}">
											<a href="javascript:void(0);" class="blue" onclick="goPayBillList('${gbEntity.gbName}', '${billMonth}', -1, -1);">${gbEntity.gbName}</a>
										</td>
									</c:if>
									<td></td>
									<td></td>
									<td></td>
									<c:if test="${pfStatus.first}">
										<td align="right" rowspan="${gbEntity.pfCount}">
											<fmt:formatNumber value="${(gbEntity.succAmt+0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
											<c:set var="succAmt" value="${succAmt + (gbEntity.succAmt+0.00000000001)/100.0}"/>
										</td>
										<td align="right" rowspan="${gbEntity.pfCount}">
											<c:if test="${gbEntity.isCollectArrears == 2}">
												<c:if test="${gbEntity.lastUnpaidAmt > 0}">
													<a href="javascript:void(0);" class="blue" onclick="goPayBillListUnpaid('${gbEntity.gbName}', '${billMonth}', -1, 2);">
														<fmt:formatNumber value="${(gbEntity.lastUnpaidAmt+0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
													</a>
												</c:if>
												<c:if test="${gbEntity.lastUnpaidAmt == 0}">
													<fmt:formatNumber value="${(gbEntity.lastUnpaidAmt+0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
												</c:if>
												<c:set var="lastUnpaidAmt" value="${lastUnpaidAmt +  (gbEntity.lastUnpaidAmt+0.00000000001)/100.0}"/>
											</c:if>
										</td>
										<td align="right" rowspan="${gbEntity.pfCount}">
											<a href="javascript:void(0);" class="blue" onclick="goPayBillList('${gbEntity.gbName}', '${billMonth}', -2, 4);">
												<fmt:formatNumber value="${(gbEntity.jfqPayAmt+0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
												<c:set var="jfqPayAmt" value="${jfqPayAmt + (gbEntity.jfqPayAmt+0.00000000001)/100.0 }"/>
											</a>
										</td>
										<td align="right" rowspan="${gbEntity.pfCount}">
											<a href="javascript:void(0);" class="blue" onclick="goPayBillList('${gbEntity.gbName}', '${billMonth}', 5, 1);">
												<fmt:formatNumber value="${(gbEntity.bankPayAmt+0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
												<c:set var="bankPayAmt" value="${bankPayAmt + (gbEntity.bankPayAmt+0.00000000001)/100.0 }"/>
											</a>
										</td>
										<td align="right" rowspan="${gbEntity.pfCount}">
											<a href="javascript:void(0); " class="blue" onclick="goPayBillList('${gbEntity.gbName}', '${billMonth}', 2, 1);">
												<fmt:formatNumber value="${(gbEntity.cashPayAmt+0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
												<c:set var="cashPayAmt" value="${cashPayAmt + (gbEntity.cashPayAmt+0.00000000001)/100.0 }"/>
											</a>
										</td>
										<td align="right" rowspan="${gbEntity.pfCount}">
											<a href="javascript:void(0); " class="blue" onclick="goPayBillList('${gbEntity.gbName}', '${billMonth}', -1, 5);">
												<fmt:formatNumber value="${(gbEntity.needPayAmt + gbEntity.lastUnpaidAmt +0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
												<c:set var="needPayTotalAmt" value="${needPayTotalAmt +  (gbEntity.needPayAmt+0.00000000001)/100.0}"/>
											</a>
										</td>
										<td align="right" rowspan="${gbEntity.pfCount}">
											<c:if test="${gbEntity.collectionRate >= 0}">
												<fmt:formatNumber value="${gbEntity.collectionRate }" type="currency" pattern="0.00"/>%
											</c:if>
										</td>
										<td align="right" rowspan="${gbEntity.pfCount}">
											<c:if test="${gbEntity.unpaidRate >= 0}">
												<fmt:formatNumber value="${gbEntity.unpaidRate }" type="currency" pattern="0.00"/>%
											</c:if>
										</td>
									</c:if>
								</tr>
							</c:if>
							<c:if test="${fn:length(gbEntity.feeReportEntityList) > 0 }">
								<c:forEach items="${gbEntity.feeReportEntityList}" var="pfEntity" varStatus="pfStatus">
									<tr>
										<c:if test="${pmStatus.first and gbStatus.first and pfStatus.first}">
											<td rowspan="${feeCollectionViewEntity.pmCount}">${feeCollectionViewEntity.propertyCompanyName}</td>
										</c:if>
										<c:if test="${gbStatus.first and pfStatus.first}">
											<td rowspan="${pmEntity.gbCount}">${pmEntity.propertyManagementName}</td>
										</c:if>
										<c:if test="${pfStatus.first}">
											<td rowspan="${gbEntity.pfCount}">
												<a href="javascript:void(0);" class="blue" onclick="goPayBillList('${gbEntity.gbName}', '${pfEntity.code}', '${billMonth}', -1, -1);">${gbEntity.gbName}</a>
											</td>
										</c:if>
										<td>${pfEntity.thisName}</td>
										<td align="right">
											<fmt:formatNumber value="${(pfEntity.totalAmt+0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
											<c:set var="succTotalAmt" value="${succTotalAmt + (pfEntity.totalAmt+0.00000000001)/100.0 }"/>
										</td>
										<td align="right">
											<c:if test="${gbEntity.isCollectArrears == 2}">
												<fmt:formatNumber value="${(pfEntity.unpaidAmt+0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
											</c:if>
										</td>
										<c:if test="${pfStatus.first}">
											<td align="right" rowspan="${gbEntity.pfCount}">
												<fmt:formatNumber value="${(gbEntity.succAmt+0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
												<c:set var="succAmt" value="${succAmt + (gbEntity.succAmt+0.00000000001)/100.0}"/>
											</td>
											<td align="right" rowspan="${gbEntity.pfCount}">
												<c:if test="${gbEntity.isCollectArrears == 2}">
													<c:if test="${gbEntity.lastUnpaidAmt > 0}">
														<a href="javascript:void(0);" class="blue" onclick="goPayBillListUnpaid('${gbEntity.gbName}', '${pfEntity.code}', '${billMonth}', -1, 2);">
															<fmt:formatNumber value="${(gbEntity.lastUnpaidAmt+0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
														</a>
													</c:if>
													<c:if test="${gbEntity.lastUnpaidAmt == 0}">
														<fmt:formatNumber value="${(gbEntity.lastUnpaidAmt+0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
													</c:if>
													<c:set var="lastUnpaidAmt" value="${lastUnpaidAmt +  (gbEntity.lastUnpaidAmt+0.00000000001)/100.0}"/>
												</c:if>
											</td>
											<td align="right" rowspan="${gbEntity.pfCount}">
												<a href="javascript:void(0);" class="blue" onclick="goPayBillList('${gbEntity.gbName}', '${pfEntity.code}', '${billMonth}', -2, 4);">
													<fmt:formatNumber value="${(gbEntity.jfqPayAmt+0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
													<c:set var="jfqPayAmt" value="${jfqPayAmt + (gbEntity.jfqPayAmt+0.00000000001)/100.0 }"/>
												</a>
											</td>
											<td align="right" rowspan="${gbEntity.pfCount}">
												<a href="javascript:void(0);" class="blue" onclick="goPayBillList('${gbEntity.gbName}', '${pfEntity.code}', '${billMonth}', 5, 1);">
													<fmt:formatNumber value="${(gbEntity.bankPayAmt+0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
													<c:set var="bankPayAmt" value="${bankPayAmt + (gbEntity.bankPayAmt+0.00000000001)/100.0 }"/>
												</a>
											</td>
											<td align="right" rowspan="${gbEntity.pfCount}">
												<a href="javascript:void(0); " class="blue" onclick="goPayBillList('${gbEntity.gbName}', '${pfEntity.code}', '${billMonth}', 2, 1);">
													<fmt:formatNumber value="${(gbEntity.cashPayAmt+0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
													<c:set var="cashPayAmt" value="${cashPayAmt + (gbEntity.cashPayAmt+0.00000000001)/100.0 }"/>
												</a>
											</td>
											<td align="right" rowspan="${gbEntity.pfCount}">
												<a href="javascript:void(0); " class="blue" onclick="goPayBillList('${gbEntity.gbName}', '${pfEntity.code}', '${billMonth}', -1, 5);">
													<fmt:formatNumber value="${(gbEntity.needPayAmt + gbEntity.lastUnpaidAmt +0.00000000001)/100.0 }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
													<c:set var="needPayTotalAmt" value="${needPayTotalAmt +  (gbEntity.needPayAmt+0.00000000001)/100.0}"/>
												</a>
											</td>
											<td align="right" rowspan="${gbEntity.pfCount}">
												<c:if test="${gbEntity.collectionRate >= 0}">
													<fmt:formatNumber value="${gbEntity.collectionRate }" type="currency" pattern="0.00"/>%
												</c:if>
											</td>
											<td align="right" rowspan="${gbEntity.pfCount}">
												<c:if test="${gbEntity.unpaidRate >= 0}">
													<fmt:formatNumber value="${gbEntity.unpaidRate }" type="currency" pattern="0.00"/>%
												</c:if>
											</td>
										</c:if>
									</tr>
								</c:forEach>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			</c:if>
		</c:forEach>
		<tr>
			<td align="right" colspan="4">合计</td>
			<td align="right"><fmt:formatNumber value="${succAmt }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/></td>
			<td align="right"><fmt:formatNumber value="${lastUnpaidAmt }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/></td>
			<td align="right"><fmt:formatNumber value="${succTotalAmt }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/></td>
			<td align="right"><fmt:formatNumber value="${lastUnpaidAmt }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/></td>
			<td align="right"><fmt:formatNumber value="${jfqPayAmt }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/></td>
			<td align="right"><fmt:formatNumber value="${bankPayAmt }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/></td>
			<td align="right"><fmt:formatNumber value="${cashPayAmt }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/></td>
			<td align="right"><fmt:formatNumber value="${needPayTotalAmt + lastUnpaidAmt }" type="currency" pattern="#,##0.00" maxFractionDigits="2"/></td>
			<td align="right"></td>
			<td align="right"></td>
		</tr>
		</tbody>
	</table>
	<%--<c:import url="/common/page.jsp" />--%>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker01.js"></script>
<script type="text/javascript" src="../js/DatePicker.js"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript" >
	$(function() {
		$('.select2_class').select2();
		var pmclickeNum = '${isSelect}';
		$(document).on('click', "#propertyManagementTd .select2", function(){
			if(pmclickeNum == 0) {
				var pmList = [];
				$.ajax({
					url: '<%=basePath%>/payBillReport/getPmList.json',
					dataType: 'json',
					success: function (data) {
						var list = JSON.parse(data);
						var strHtml = "<option value=''>选择管理处</option>";
						$.each(list, function (i, item) {
							strHtml += "<option value='" + item.id + "'>" + item.name + "</option>";
							pmList.push("{'id':'"+item.id+"','name':'"+item.name+"'}");
						});
						$("#propertyManagement").html(strHtml);
						$('.select2_class').select2();
						pmclickeNum = 1;
						addSelectHideValue(pmList, 'pmList');
						getGbList(null);
						getBuilding(null);
					}
				});
			}
		});
		$("#propertyManagementTd .select2").click();

		function getGbList(pmId) {
			var gbList = [];
			$.ajax({
				url: '<%=basePath%>/payBillReport/getGbList.json?pmId='+pmId,
				dataType: 'json',
				success: function (data) {
					var list = JSON.parse(data);
					var strHtml = "<option value=''>选择小区</option>";
					$.each(list, function (i, item) {
						strHtml += "<option value='" + item.id + "'>" + item.name + "</option>";
						gbList.push("{'id':'"+item.id+"','name':'"+item.name+"'}");
					});
					$("#groupBuiliding").html(strHtml);
					$('.select2_class').select2();
					addSelectHideValue(gbList, "gbList");
				}
			});
		}

		function getBuilding(gbId) {
			var bList = [];
			$.ajax({
				url: '<%=basePath%>/payBillReport/getBuildingList.json?gbId='+gbId,
				dataType: 'json',
				success: function (data) {
					var list = JSON.parse(data);
					var strHtml = "<option value=''>选择楼栋</option>";
					$.each(list, function (i, item) {
						strHtml += "<option value='" + item.id + "'>" + item.name + "</option>";
						bList.push("{'id':'"+item.id+"','name':'"+item.name+"'}");
					});
					$("#builiding").html(strHtml);
					$('.select2_class').select2();
					addSelectHideValue(bList, "buildingList");
				}
			});
		}

		$("#propertyManagement").change(function () {
			$("#groupBuiliding").html("");
			var strHtml = "<option value=''>选择小区</option>";
			$("#groupBuiliding").html(strHtml);
			getGbList($(this).val());
		});

		$("#groupBuiliding").change(function () {
			$("#builiding").html("");
			var strHtml = "<option value=''>选择楼栋</option>";
			$("#builiding").html(strHtml);
			getBuilding($(this).val());
		});
	});

	function goPayBillList(gbName, code, billMonthStart, paymentWay, isPay) {
	    if(isPay!=undefined && isPay!=null && code=="car"){
	    	goCarPayList(gbName, paymentWay, isPay);
	    } else {
	        var obj = $("#builiding").find("option:selected");
			var bName = "";
			if(obj.val() != "") {
				bName = $("#builiding").find("option:selected").text();
			}
			var isUnpaid = 0;
			window.location.href = "<%=basePath%>/payBill/listPeriod.html?gbName="+gbName+"&bName="+bName+"&billMonthStart="+billMonthStart+"&isReport=1&paymentWay="+paymentWay+"&isUnpaid="+isUnpaid+"&isPay="+isPay;
	    }
	}
	
	function goCarPayList(gbName, paymentWay, isPay) {
		if(paymentWay==-2){
		    paymentWay = 3;
		} else if(paymentWay==2){
		    paymentWay = 1;
		} else if(paymentWay==-1){
		    paymentWay = "";
		} else {
		    paymentWay = -1;
		}
		
		if(isPay==5){
		    paymentWay = -1;
		}
		
		var url = "<%=basePath%>/cloudkey/paysearch.html?parking="+gbName+"&payType="+paymentWay;
		var billMonthStart = $.trim($("#begintime").val());
		if(billMonthStart!=""){
		    var startTime = billMonthStart+"-01 00:00";
		    
		    var year = startTime.substring(0, 4);
		    var month = startTime.substring(5, 7);
		    var day = new Date(year,month,0);     
			var endTime = billMonthStart + '-' + day.getDate()+" 23:59:59";
		    
		    url = url+"&startTime="+startTime+"&endTime="+endTime;
		}
		window.location.href = url;
	}

	function goPayBillListUnpaid(gbName, code, billMonthStart, paymentWay, isPay) {
	    if(isPay!=undefined && isPay!=null && code=="car"){
	    	goCarPayList(gbName, paymentWay, isPay);
	    } else {
		    var obj = $("#builiding").find("option:selected");
			var bName = "";
			if(obj.val() != "") {
				bName = $("#builiding").find("option:selected").text();
			}
			window.location.href = "<%=basePath%>/payBill/listPeriod.html?gbName="+gbName+"&bName="+bName+"&billMonthStart="+billMonthStart+"&paymentWay="+paymentWay+"&isUnpaid=1&isReport=1&isPay="+isPay;
	    }
	}

	/**
	 * 将select的值隐藏在input中
	 */
	function addSelectHideValue(objList, objId) {
		$("#"+objId+"").val();
		$("#"+objId+"").val("["+objList+"]");
	}
</script>
</html>
