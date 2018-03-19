<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业缴费管理-账单明细</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <h2>账单明细</h2>
    <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
      <tr>
        <td width="90"><div align="right" class="bold">房间号：</div></td>
        <td>${payBill.realRoomNum} </td>
        
        <c:choose>
        	<c:when test="${payBill.paytimeType==1}">
		        <td width="90"><div align="right" class="bold">物业缴费月份：</div></td>
		        <td>${payBill.month}</td>
        	</c:when>
        	<c:when test="${payBill.paytimeType==2}">
				<c:choose>
					<c:when test="${payBill.cycleType ne 2}">
						<td width=""><div align="right" class="bold">账单缴费周期：</div></td>
						<td>${payBill.billMonthStart} ~ ${payBill.billMonthEnd}</td>
						<td width=""><div align="right" class="bold">账单缴费窗口：</div></td>
						<td>${payBill.payDayStart} ~ ${payBill.payDayEnd}</td>
					</c:when>
					<c:otherwise>
						<td width=""><div align="right" class="bold">账单缴费周期：</div></td>
						<td>${payBill.billMonthSize}个月</td>
					</c:otherwise>
				</c:choose>
        	</c:when>
        </c:choose>
        
        <td width="90"><div align="right" class="bold">账单状态：</div></td>
        <td>
        	<c:choose>
			  	<c:when test="${payBill.isPay==1}"> <a class="blue alive" href="#"> 已缴 </a> </c:when>
			  	<c:when test="${payBill.isPay==2 && payBill.financeStatus!=1}"><a class="grey asleep" href="#"> 未缴 </a></c:when>
			  	<c:otherwise><a class="grey asleep" href="#"> 部分已缴 </a></c:otherwise>
		  </c:choose>
        </td>
        <td width="90"><div align="right" class="bold">业主姓名：</div></td>
        <td>${payBill.propertyProprietorName}</td>
        <%-- <td width="90"><div align="right" class="bold">合同号：</div></td>
        <td>${payBill.contractNum}</td> --%>
      </tr>
    </table>
	<table class="info-list-01" border="0" style="width:50%;margin-top:10px;text-align: center;">
		<tr class="tittle">
			<td width=""><div align="center" class="bold">费用总计</div></td>
			<td width=""><div align="center" class="bold">本月应缴合计</div></td>
			<td width=""><div align="center" class="bold">欠费合计</div></td>
			<td width=""><div align="center" class="bold">滞纳金金额</div></td>
		</tr>
		<tr>
			<td width="">${totalAmounts }</td>
			<td width="">${totalSuccAmount }</td>
			<td width="">
				<c:choose>
					<c:when test="${totalUnpaid > 0}">
						<a class="blue" href="<%=basePath%>/payBill/getUnpaidList.html?payBillId=${payBill.id}">${totalUnpaid}</a>
					</c:when>
					<c:otherwise>
						0.00
					</c:otherwise>
				</c:choose>
			</td>
			<td width="">${lateFeeTotal }</td>
		</tr>
	</table>
    <%--固定--%>
	<c:set var="fixedTotalPrice" value="0"/>
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
    	<tr>
			<td>${item.name}</td>
			<td><fmt:formatNumber value="${item.totalPrice*item.billMonthSize/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/></td>
			<c:set var="fixedTotalPrice" value="${item.totalPrice*item.billMonthSize/100 + 0.0000001 + fixedTotalPrice}"/>
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
    			<tr>
    				<td>应缴合计</td>
    				<td><fmt:formatNumber value="${fixedTotalPrice }" type="currency" pattern="0.##" maxFractionDigits="2"/></td>
    				<td></td>
    				<td></td>
    			</tr>
    		</table>
    	</c:if>
    </c:forEach>
    <%--抄表--%>
    <c:forEach items="${billMrDetails}" var="billMrDetail" varStatus="k">
    	<c:if test="${k.first}">
    		<table class="mars info-list-02 mtop20">
    			<tr class="title"><td colspan="7"><div align="left">抄表费用</div></td></tr>
    			<tr>
    				<td>费用名称</td>
    				<td>计费表名称</td>
    				<td>金额</td>
    				<td>用量</td>
    				<td>单价</td>
    				<td>上期读数</td>
    				<td>本期读数</td>
    			</tr>
    	</c:if>
    	<tr>
			<td>${billMrDetail.name}</td>
			<td>${billMrDetail.mrName}</td>
			<td><fmt:formatNumber value="${billMrDetail.totalPrice + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/></td>
			<td>${billMrDetail.priceUnitValue}</td>
			<td>
				<c:choose>
					<c:when test="${not empty billMrDetail.signalPriceStr}">
						${billMrDetail.signalPriceStr}
					</c:when>
					<c:otherwise>${billMrDetail.signalPrice}</c:otherwise>
				</c:choose>
			</td>
			<td>
				<c:if test="${not empty billMrDetail.startValue}">
					${billMrDetail.startValue}
				</c:if>
			</td>
			<td>
				<c:if test="${not empty billMrDetail.endValue}">
					${billMrDetail.endValue}
				</c:if>
			</td>
		</tr>
    	<c:if test="${k.last}">
    		<%--<c:if test="${(not empty payBill.lastUnpaid) and (payBill.lastUnpaid gt 0)}">
    			<tr>
    				<td>往月欠费</td>&lt;%&ndash;+ 0.0000001保证四舍五入，因为fmt使用的是"4舍6入5奇偶"的算法&ndash;%&gt;
    				<td><fmt:formatNumber value="${payBill.lastUnpaid/100  + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/></td>
    				<td></td>
    				<td></td>
    				<td></td>
    				<td></td>
    			</tr>
    		</c:if>--%>
    			<tr>
    				<td>应缴合计</td>
    				<td><fmt:formatNumber value="${mrTotal - (payBill.lastUnpaid/100  + 0.0000001) + 0.0000001}" type="currency" pattern="0.##" maxFractionDigits="2"/></td>
    				<td></td>
    				<td></td>
    				<td></td>
    				<td></td>
    			</tr>
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
    	<tr>
			<td>${item.name}</td>
			<td><fmt:formatNumber value="${item.totalPrice/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/></td>
			<td>
				<c:choose>
					<c:when test="${(not empty item.priceUnitValue) and (item.priceUnitValue ne 0)}">
						<fmt:formatNumber value="${item.priceUnitValue/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/>
					</c:when>
					<c:otherwise>&nbsp;</c:otherwise>
				</c:choose>
			</td>
			<td>
				<c:choose>
					<c:when test="${(not empty item.signalPrice) and (item.signalPrice ne 0)}">
						<fmt:formatNumber value="${item.signalPrice/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/>
					</c:when>
					<c:otherwise>&nbsp;</c:otherwise>
				</c:choose>
			</td>
		</tr>
    	<c:if test="${k.last}">
    			<tr>
    				<td>应缴合计</td>
    				<td><fmt:formatNumber value="${tmpdTotal/100 + 0.0000001}" type="currency" pattern="0.##" maxFractionDigits="2"/></td>
    				<td></td>
    				<td></td>
    			</tr>
    		</table>
    	</c:if>
    </c:forEach>
	<%--选择周期--%>
	<c:forEach items="${alterFeeDetails}" var="item" varStatus="g">
		<c:if test="${g.first}">
			<table class="mars info-list-02 mtop20">
			<tr class="title"><td colspan="4"><div align="left">选择周期费用</div></td></tr>
			<tr>
				<td>费用名称</td>
				<td>金额</td>
				<td>建筑面积/用量</td>
				<td>单价</td>
			</tr>
		</c:if>
		<tr>
			<td>${item.name}</td>
			<td><fmt:formatNumber value="${item.totalPrice/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/></td>
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
		<c:if test="${g.last}">
			<tr>
				<td>应缴合计</td>
				<td><fmt:formatNumber value="${alterFeeDetailTotal/100 + 0.0000001}" type="currency" pattern="0.##" maxFractionDigits="2"/></td>
				<td></td>
				<td></td>
			</tr>
			</table>
		</c:if>
	</c:forEach>

	<%--第三方极致--%>
	<c:forEach items="${thrdFeeDetails}" var="item" varStatus="y">
		<c:if test="${y.first}">
			<table class="mars info-list-02 mtop20">
			<tr class="title"><td colspan="4"><div align="left">费用明细</div></td></tr>
			<tr>
				<td>费用名称</td>
				<td>金额</td>
				<td>建筑面积/用量</td>
				<td>单价</td>
			</tr>
		</c:if>
		<tr>
			<td>${item.name}</td>
			<td><fmt:formatNumber value="${item.totalPrice/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/></td>
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
		<c:if test="${g.last}">
			<tr>
				<td>应缴合计</td>
				<td><fmt:formatNumber value="${thrdTotal/100 + 0.0000001}" type="currency" pattern="0.##" maxFractionDigits="2"/></td>
				<td></td>
				<td></td>
			</tr>
			</table>
		</c:if>
	</c:forEach>

	<%--滞纳金--%>
	<c:forEach items="${lateFeeDetails}" var="item" varStatus="g">
		<c:if test="${g.first}">
			<table class="mars info-list-02 mtop20">
			<tr class="title"><td colspan="4"><div align="left">滞纳金</div></td></tr>
			<tr>
				<td>费用名称</td>
				<td>金额</td>
				<td>建筑面积/用量</td>
				<td>单价</td>
			</tr>
		</c:if>
		<tr>
			<td>${item.name}</td>
			<td><fmt:formatNumber value="${item.totalPrice/100 + 0.0000001}" type="currency" pattern="0.######" maxFractionDigits="6"/></td>
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
		<c:if test="${g.last}">
			<tr>
				<td>应缴合计</td>
				<td><fmt:formatNumber value="${lateFeeTotal}" type="currency" pattern="0.##" maxFractionDigits="2"/></td>
				<td></td>
				<td></td>
			</tr>
			</table>
		</c:if>
	</c:forEach>
    
    <c:if test="${feeType == null || feeType == 4}">
		<table class="mars">
		  <thead>
		  <tr class="title">
			<th colspan="2"><div align="left">实际结算合计</div></th>
		  </tr>
		  </thead>
		  <tr>
			<td width="300">用户实际缴费</td>
				<c:choose>
				  <c:when test="${payBill.paymentWay==2 }"><td>${(payBill.succPayAmount - payBill.amountFinance)/100.0} 元</td></c:when>
				  <c:otherwise><td>${payBill.succPayAmount/100.0} 元</td></c:otherwise>
				</c:choose>
		  </tr>
		  <tr class="total">
			<td>解放区补贴</td>
			<td>
				<c:choose>
				  <c:when test="${payBill.isPay==1&& payBill.financeStatus == 1  && payBill.paymentWay!=2}"><c:out value="${(payBill.amount-payBill.deduPrice - payBill.succPayAmount)/100.0}"></c:out></c:when>
				  <c:when test="${payBill.isPay==1&& payBill.financeStatus == 0}"><c:out value="${(payBill.amount-payBill.succPayAmount)/100.0} "></c:out></c:when>
				  <c:when test="${empty payBill.financeStatus && !empty payBill.succPayAmount}"><c:out value="${(payBill.amount-payBill.succPayAmount)/100.0} "></c:out></c:when>
				  <c:otherwise>0.0</c:otherwise>
				</c:choose>元
			</td>
		  </tr>
		  <tr class="total">
			<td>物业宝抵扣</td>
			<td>
				${payBill.amountFinance/100.0 }元
			</td>
		  </tr>
		  <tr>
			<td>实际结算合计</td>
			<td>${payBill.amount/100.0} 元</td>
		  </tr>
		</table>
	</c:if>
    <table class="mars">
      <thead>
      <tr class="title">
        <th colspan="4"><div align="left">操作记录</div></th>
      </tr>
      </thead>
      <tr>
		<td width="300">操作名称</td>
        <td width="300">操作者</td>
        <td>操作时间</td>
        <td>解放号</td>
      </tr>
      <c:if test="${payBill.paymentWay==3 }">
	      <tr>
			<td>代扣卡续费</td>
			<td>系统自动划扣</td>
			<td>${payBill.payTime }</td>
			<td>${payBill.sys0UpdUser }</td>
	      </tr>
      </c:if>
      <c:if test="${payBill.paymentWay==2 }">
	      <tr>
			<td>修改账单状态</td>
			<td>${payBill.updateUserName }</td>
			<td>${payBill.sys0UpdTime }</td>
			<td>${payBill.sys0UpdUser }</td>
	      </tr>
      </c:if>
	  <c:if test="${payBill.paymentWay==4 }">
		<tr>
			<td>物业宝抵扣</td>
			<td>${payBill.updateUserName }</td>
			<td>${payBill.payTime }</td>
			<td>${payBill.sys0UpdUser }</td>
		</tr>
	  </c:if>
      <c:if test="${payBill.paymentWay==1 }">
	      <c:forEach var="item" items="${payBillWithPayRecored}">
	      	<c:if test="${item.payStatus==2}">
		      <tr>
				<td>用户缴费</td>
				<td>${item.payUserName}</td>
				<td>${item.payTime}</td>
				<td>${item.buyerId}</td>
		      </tr>
	      	</c:if>
      	  </c:forEach>
		  <c:if test="${payBill.hbAmount == payBill.amount}">
			  <tr>
				  <td>用户缴费</td>
				  <td>${payBill.payUserName }</td>
				  <td>${payBill.payTime }</td>
				  <td>${payBill.buyerId }</td>
			  </tr>
		  </c:if>
      </c:if>
    </table>
    <table class="mars">
      <thead>
      <tr class="title">
        <th colspan="5"><div align="left">支付记录</div></th>
      </tr>
      </thead>
      <tr>
		<td width="300">支付方式</td>
		<td width="300">支付人</td>
        <td width="300">支付结果</td>
        <td width="300">支付时间</td>
        <td>支付流水号</td>
      </tr>
      <c:if test="${payBill.paymentWay==1 }">
      	  <c:forEach var="item" items="${payBillWithPayRecored}">
		      <tr>
				<td>
					<c:choose>
						<c:when test="${item.payMethod==1}">微信支付</c:when>
						<c:when test="${item.payMethod==2}">支付宝支付</c:when>
						<c:when test="${item.payMethod==3}">银联支付</c:when>
						<c:when test="${item.payMethod==9}">双乾支付</c:when>
					</c:choose>
				</td>
				<td>${item.userName}</td>
				<td>
					<c:choose>
						<c:when test="${item.payStatus==1}">未支付</c:when>
						<c:when test="${item.payStatus==2}">支付成功</c:when>
						<c:otherwise>支付失败</c:otherwise>
					</c:choose>
				</td>
		        <td>${item.payTime}</td>
				<td>${item.flowNo}</td>
		      </tr>
      	  </c:forEach>
      </c:if>
    </table>
	<!-- <div class="padb"><div id="backPay" class="info-btn mtop20 left"><a href="#" onclick="javascript:history.go(-2);">返 回</a></div></div> -->
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
</html>
