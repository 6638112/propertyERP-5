<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账单金额修改</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
    <h2>账单金额修改</h2>
    <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
      	<tr>
			<td>小区</td>
			<td>${gbName}</td>
		</tr>
		<tr>
        	<td>房间号</td>
        	<td>${payBill.realRoomNum} </td>
        </tr>
		<tr>
			<td>业主姓名</td>
			<td>${payBill.propertyProprietorName}</td>
		</tr>
		<tr>
			<td>账单名称</td>
			<td>${billName}</td>
		</tr>
		<tr>
        <c:choose>
        	<c:when test="${payBill.paytimeType==1}">
		        <td>账单周期</td>
		        <td>${payBill.month}</td>
        	</c:when>
        	<c:when test="${payBill.paytimeType==2}">
				<c:choose>
					<c:when test="${payBill.cycleType ne 2}">
						<td width="">账单周期</td>
						<td>${payBill.billMonthStart} ~ ${payBill.billMonthEnd}</td>
					</c:when>
					<c:otherwise>
						<td width="">账单周期</td>
						<td>${payBill.billMonthSize}个月</td>
					</c:otherwise>
				</c:choose>
        	</c:when>
        </c:choose>
        </tr>
		<tr>
			<td width="">缴费时间</td>
			<td>
				<c:if test="${not empty payBill.payDayStart and not empty payBill.payDayEnd}">
					${payBill.payDayStart} ~ ${payBill.payDayEnd}
				</c:if>
			</td>
		</tr>
    </table>
    <%--固定--%>
    <c:forEach items="${fixedPfds}" var="item" varStatus="k">
    	<c:if test="${k.first}">
    		<table class="mars info-list-02 mtop20">
    			<tr class="title"><td colspan="4"><div align="left">固定费用</div></td></tr>
    			<tr>
    				<td>费用名称</td>
    				<td>金额</td>
    				<td>建筑面积/用量</td>
    				<td>单价</td>
    			</tr>
    	</c:if>
    	<tr class="fixedPfds">
			<input type="hidden" name="fixedPfdId" value="${item.id }"/>
			<td>${item.name}</td>
			<td>
				<input type="number" class="input_text w120 pp" name="fixedUnpaid" data-name="${item.name}" value="<fmt:formatNumber value="${item.totalPrice/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/>"/>
				<%--<c:choose>
					<c:when test="${item.type == 8}">
						<input type="number" class="input_text w120 pp" name="fixedUnpaid" data-name="${item.name}" value="<fmt:formatNumber value="${item.totalPrice/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/>"/>
					</c:when>
					<c:otherwise>
						<fmt:formatNumber value="${item.totalPrice/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/>
						<input type="hidden" class="input_text w120 pp" name="fixedUnpaid" value="<fmt:formatNumber value="${item.totalPrice/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/>"/>
					</c:otherwise>
				</c:choose>--%>
			</td>
			<td>
				<c:if test="${not empty item.priceUnitValue}">
					<fmt:formatNumber value="${item.priceUnitValue/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty item.signalPrice}">
					<fmt:formatNumber value="${item.signalPrice/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/>
				</c:if>
			</td>
		</tr>
    	<c:if test="${k.last}">
    		</table>
    	</c:if>
    </c:forEach>
    <%--抄表--%>
    <c:forEach items="${billMrDetails}" var="billMrDetail" varStatus="k">
    	<c:if test="${k.first}">
    		<table class="mars info-list-02 mtop20">
    			<tr class="title"><td colspan="6"><div align="left">抄表费用</div></td></tr>
    			<tr>
    				<td>费用名称</td>
    				<td>金额</td>
    				<td>用量</td>
    				<td>单价</td>
    				<td>上期读数</td>
    				<td>本期读数</td>
    			</tr>
    	</c:if>
    	<tr class="mrPfds">
			<input type="hidden" name="mrPfdId" value="${billMrDetail.propertyFeeDetailId }"/>
			<td>${billMrDetail.name}</td>
			<td>
				<input type="number" class="input_text w120 pp" name="billMrUnpaid" data-name="${billMrDetail.name}" value="<fmt:formatNumber value="${billMrDetail.totalPrice + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/>"/>
			</td>
			<td>${billMrDetail.priceUnitValue}</td>
			<td>${billMrDetail.signalPrice}</td>
			<td>
				<c:if test="${not empty billMrDetail.startValue}">
					<input type="number" class="input_text w120 pp" name="billMrStartValue" data-name="${billMrDetail.name}" value="${billMrDetail.startValue}"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty billMrDetail.endValue}">
					<input type="number" class="input_text w120 pp" name="billMrEndValue" data-name="${billMrDetail.name}" value="${billMrDetail.endValue}"/>
				</c:if>
			</td>
		</tr>
    	<c:if test="${k.last}">
    		</table>
    	</c:if>
    </c:forEach>
    <%--临时--%>
    <c:forEach items="${tmpPfds}" var="item" varStatus="k">
    	<c:if test="${k.first}">
    		<table class="mars info-list-02 mtop20">
    			<tr class="title"><td colspan="4"><div align="left">临时费用</div></td></tr>
    			<tr>
    				<td>费用名称</td>
    				<td>金额</td>
    				<td>建筑面积/用量</td>
    				<td>单价</td>
    			</tr>
    	</c:if>
    	<tr class="tempPfds">
			<input type="hidden" name="tempPfdId" value="${item.id }"/>
			<td>${item.name}</td>
			<td>
				<input type="number" class="input_text w120 pp" name="tmpAmount" data-name="${item.name}" value="<fmt:formatNumber value="${item.totalPrice/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/>"/>
			</td>
			<td>
				<c:if test="${not empty item.priceUnitValue}">
					<input type="number" class="input_text w120 pp" name="tmpPriceUnitValue" data-name="${item.name}" value="<fmt:formatNumber value="${item.priceUnitValue/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/>"/>
				</c:if>
			</td>
			<td>
				<c:if test="${not empty item.signalPrice}">
					<input type="number" class="input_text w120 pp" name="tmpSignalPrice" data-name="${item.name}" value="<fmt:formatNumber value="${item.signalPrice/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/>"/>
				</c:if>
			</td>
		</tr>
    	<c:if test="${k.last}">
    		</table>
    	</c:if>
    </c:forEach>
	<div class="mtop20">
		<input id="savePayBillAmount" class="weak-btn small-btn w100 mleft20" type="button" value="保存">
	</div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" >
	$("#savePayBillAmount").click(function () {
		var isSubmit = true;
		var msg = "";
		$(".info").find("input[type='number']").each(function () {
			var val = $(this).val();
			if(0 > val) {
				isSubmit = false;
				msg = $(this).attr("data-name")+": 必须为大于等于零的数字！";
			}
		});
		if(!isSubmit) {
			alert(msg);
			return false;
		}

		//固定收费
		var fixedArr = [];
		$(".info").find(".fixedPfds").each(function (i, e) {
			var fixedPfdId = $(this).find("input[name='fixedPfdId']").val();
			var fixedUnpaid = changeEmptyData($(this).find("input[name='fixedUnpaid']").val());
			fixedArr[i] = [fixedPfdId,fixedUnpaid];
		});
		//临时收费
		var tmpArr = [];
		$(".info").find(".tempPfds").each(function (i, e) {
			var tmpAmount = changeEmptyData($(this).find("input[name='tmpAmount']").val());
			var tmpId = $(this).find("input[name='tempPfdId']").val();
			var tmpPriceUnitValue = changeEmptyData($(this).find("input[name='tmpPriceUnitValue']").val());
			var tmpSignalPrice = changeEmptyData($(this).find("input[name='tmpSignalPrice']").val());
			tmpArr[i] = [tmpId,tmpAmount,tmpPriceUnitValue,tmpSignalPrice];
		});
		//抄表收费
		var mrArr = [];
		$(".info").find(".mrPfds").each(function (i, e) {
			var mrPfdId = $(this).find("input[name='mrPfdId']").val();
			var billMrUnpaid = changeEmptyData($(this).find("input[name='billMrUnpaid']").val());
			//字符串数值比较有问题，乘以1将其转换成数值
			var billMrStartValue = changeEmptyData($(this).find("input[name='billMrStartValue']").val()) * 1;
			var billMrEndValue = changeEmptyData($(this).find("input[name='billMrEndValue']").val()) * 1;
			if(billMrStartValue > billMrEndValue) {
				msg = $(this).find("input[name='billMrStartValue']").attr("data-name")+":本期读数不能小于上期读数";
				isSubmit = false;
			}
			mrArr[i] = [mrPfdId,billMrUnpaid,billMrStartValue,billMrEndValue];
		});
		if(!isSubmit) {
			alert(msg);
			return false;
		}
		$.post("<%=basePath%>/payBill/savePayBillAmount.html", {fixed:JSON.stringify(fixedArr), tmp:JSON.stringify(tmpArr), mr:JSON.stringify(mrArr), payBillId:${payBill.id}}, function (data) {
			alert(data.message);
			window.history.back(-1);
			//window.location.href = "<%=basePath%>/payBill/listPeriod.html?pageType=zd";
		});
	});
	
	$(".billMrValueChange").change(function () {

	});

	function changeEmptyData(val) {
		if(val == null || val == "" || val == "undefined") {
			val = 0;
		}
		return val;
	}
</script>
</html>
