<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单管理</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/metro/easyui.css?v20150306">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/icon.css">
<script type="text/javascript">

</script>
</head>

<body>
<div class="info">
    <h2>订单管理</h2>
    <div class="bs-example bgebeb">
    	<form id="searchForm" name="form_main" action="<%=basePath%>/order/index.html" method=get>
	        <table class="info-list" border="0">
	          <tr>
	          	<td><div class="list-name">解放号：</div></td>
	            <td><input type="text" id="huaId" name="huaId" value="<%=request.getParameter("huaId")==null?"":request.getParameter("huaId") %>" class="input_text pp w120"></td>
	            <td><div class="list-name">联系人：</div></td>
	            <td><input type="text" id="realName" name="realName" value="<%=request.getParameter("realName")==null?"":request.getParameter("realName") %>" class="input_text pp w120"></td>
	            <td><div class="list-name">支付状态：</div></td>
	            <td>
	            	<select  id="payStatus" name="payStatus" class="select_normal w131">
	                    <option value="">请选择..</option>
		       			<option value="1" <c:if test="${param.payStatus == 1}">selected="selected"</c:if> >未支付</option>
		       			<option value="2" <c:if test="${param.payStatus == 2}">selected="selected"</c:if> >已支付</option>
                    </select>
	            </td>
	            <td><div class="list-name">运单状态：</div></td>
	            <td>
	                    <select  id="orderStatus" name="orderStatus" class="select_normal w131">
		                    <option value="">请选择..</option>
			       			<option value="3" <%if(request.getParameter("orderStatus")!=null&&"3".equals(request.getParameter("orderStatus"))){%>selected="selected"<%} %>>待发货</option>
			       			<option value="4" <%if(request.getParameter("orderStatus")!=null&&"4".equals(request.getParameter("orderStatus"))){%>selected="selected"<%} %>>待收货</option>
			       		<%-- 	<option value="5" <%if(request.getParameter("orderStatus")!=null&&"5".equals(request.getParameter("orderStatus"))){%>selected="selected"<%} %>>待评价</option> --%>
			       			<option value="6" <%if(request.getParameter("orderStatus")!=null&&"6".equals(request.getParameter("orderStatus"))){%>selected="selected"<%} %>>交易完成</option>
			       			<option value="7" <%if(request.getParameter("orderStatus")!=null&&"7".equals(request.getParameter("orderStatus"))){%>selected="selected"<%} %>>已退款</option>
			       			<option value="8" <%if(request.getParameter("orderStatus")!=null&&"8".equals(request.getParameter("orderStatus"))){%>selected="selected"<%} %>>退款中</option>
	                    </select>
	            </td>
	          </tr>
	          <tr>
	          	<td><div align="right">订单号：</div></td>
	            <td><input type="text" id="orderNo" name="orderNo" value="<%=request.getParameter("orderNo")==null?"":request.getParameter("orderNo") %>" class="input_text pp w120"></td>
	            <%--
	            <td><div class="list-name">商品类别：</div></td>
	            <td><input type="text" id="typeName" name="typeName" value="<%=request.getParameter("typeName")==null?"":request.getParameter("typeName") %>" class="input_text pp w120"></td>
	             --%>
	            <c:if test="${sm ne 'invisible'}">
		            <td><div class="list-name">供应商名称：</div></td>
		            <td>
		            	<select id="tSupplyMerchantFId" name="tSupplyMerchantFId" class="select_normal">
	                        <c:if test="${!isSupply || fn:length(supplyMerchantList) > 1}"><option value="">全部</option></c:if>
	                        <c:forEach var="supplyMerchant" items="${supplyMerchantList}" >
		                        <c:if test="${supplyMerchant.name !=null && supplyMerchant.name != ''}">
									<option value="${supplyMerchant.id}" <c:if test="${supplyMerchant.id==param.tSupplyMerchantFId}">selected</c:if> >${supplyMerchant.name}</option>
								</c:if>
							</c:forEach>
	                    </select>
		            	<%--<input type="text" id="supplyName" name="supplyName" value="${param.supplyName == null ? "" : param.supplyName}" class="input_text pp w120"> --%>
		           	</td>
	           	</c:if>
	           	<td><div class="list-name">有无退款：</div></td>
	            <td>
	            	<select  id="refundStatus" name="refundStatus" class="select_normal w131">
	                    <option value="">请选择..</option>
		       			<option value="1" <c:if test="${param.refundStatus == 1}">selected="selected"</c:if> >有退款</option>
		       			<option value="2" <c:if test="${param.refundStatus == 2}">selected="selected"</c:if> >无退款</option>
                    </select>
	            </td>
	           	<td><div class="list-name">订单日期：</div></td>
	            <td colspan="3">
	            	<input type="text" class="input_text pp icon_dt" id="date01" name="startTime" title="请选择起始时间"  value="<%=request.getParameter("startTime")==null?"":request.getParameter("startTime") %>" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm'});"> 至 
	            	<input type="text" class="input_text pp icon_dt" id="date02" name="endTime" title="请选择结束时间"  value="<%=request.getParameter("endTime")==null?"":request.getParameter("endTime") %>" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm'});">
	            </td>
	          </tr>
	          <tr>
	          	<td><div class="list-name">物流单号：</div></td>
	            <td><input type="text" id="expressNo" name="expressNo" value="${param.expressNo}" class="input_text pp w120"></td>
	            <td><div class="list-name">结算状态：</div></td>
	            <td>
	            	<select  id="tkStatus" name="tkStatus" class="select_normal w131">
	                    <option value="">请选择..</option>
		       			<option value="1" <c:if test="${param.tkStatus == 1}">selected="selected"</c:if> >未结算</option>
		       			<option value="2" <c:if test="${param.tkStatus == 2}">selected="selected"</c:if> >结算中</option>
		       			<option value="3" <c:if test="${param.tkStatus == 3}">selected="selected"</c:if> >已结算</option>
                    </select>
	            </td>
	            <td><div class="list-name">支付时间：</div></td>
	            <td>
	            	<input type="text" 
	            		id="payTimeStart" 
	            		name="payTimeStart" 
	            		value="<%=request.getParameter("payTimeStart")==null?"":request.getParameter("payTimeStart") %>" 
	            		onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" 
	            		class="input_text pp w150">至
	            	<input type="text" 
	            		id="payTimeEnd" 
	            		name="payTimeEnd" 
	            		value="<%=request.getParameter("payTimeEnd")==null?"":request.getParameter("payTimeEnd") %>" 
	            		onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" 
	            		class="input_text pp w150">
	            </td>
	          </tr>
	          <tr>
	          	<td colspan="8">
	            	<div class="mtop10 t_center btn-search">
			  			<input class="input-btn w200" id="searchButtom" type="submit"  value="搜索"/>
			  			<input class="input-btn w200 mar-left15" type="button" onclick="exp();" value="导出订单明细"/>
			  		</div>
	            </td>
	          </tr>
	        </table>
  		</form>
    </div>
    <div id="order222" class="easyui-dialog" style="width: 600px; height: 300px; padding: 10px 20px; " closed="true"></div>
    <!-- 订单列表 -->
    <display:table name="resList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
		<%--
		<display:column title="序号" sortable="true" headerClass="sortable">
		<c:out value="${row_rowNum}"/></display:column> --%>
		<display:column title="下单时间" property="buyTime" sortable="true" format="{0, date, yyyy-MM-dd HH:mm:ss}" style="width: 70px;"/>
		<display:column title="订单编号" sortable="true">
			<a class="blue editShopInfo" href="../order/viewOrderDetail.html?delieveOrderId=${row.delieveOrderId}&orderNo=${row.orderNo}">${row.orderNo }</a>
		</display:column>
		<display:column title="解放号" property="huaId" sortable="true"/>
		<display:column title="联系人" property="realName" sortable="true" style="width: 45px;"/>
		<display:column title="供应商" property="supplyName" sortable="true" style="width: 90px;"/>
		<c:if test="${!isSupply}">
		  <display:column title="小区名称" property="groupBuildingName" sortable="true" />
		</c:if>
		<display:column title="运单状态" sortable="true" style="width: 50px;" >
			<div id="status_${row.delieveOrderId}">
				<c:choose>
				  <c:when test="${row.status == 3 && row.deliveryStatus <= 1 && refundStatus != 1 && refundStatus != 2}">待发货</c:when>
				  <c:when test="${row.refundStatus == 2 }">已退款</c:when>
				  <c:when test="${row.refundStatus == 1}">退款中</c:when>
				  <c:when test="${row.deliveryStatus == 2 }">待收货</c:when>
				  <c:when test="${row.deliveryStatus == 3}">交易完成</c:when>
				  <c:otherwise>已取消</c:otherwise>
				</c:choose>
			</div>
		</display:column>
		<%--
		<display:column title="支付方式" property="payMethodStr" sortable="true"/>
		--%>
		<display:column title="支付状态" property="payStatusStr" sortable="true" style="width: 50px;"/>
		<display:column title="支付时间" property="payTime" sortable="true" format="{0, date, yyyy-MM-dd HH:mm:ss}" style="width: 70px;" />
		<display:column title="活动" property="opNames" sortable="true" />
		<%--
		<display:column title="商品金额(元)" sortable="true">${row.amount/100}</display:column>
		<display:column title="运费(元)" sortable="true">${row.deliveryFee/100}</display:column>
		 --%>
		<display:column title="<div class='red'>运单金额(元)</div>" sortable="true"><div class="red">${(row.amount+row.deliveryFee)/100}</div></display:column>
		<c:if test="${!isSupply}">
			<display:column title="订单总额" sortable="true">${(row.orderAmount + row.couponAmount)/100}</display:column>
			<display:column title="使用优惠" sortable="true">${row.deliveryOrderCoupon/100}</display:column>
			<display:column title="实际支付" sortable="true">${row.deliveryOrderAmount/100}</display:column>
		</c:if>
		<display:column title="配送方式" sortable="true">
			<c:if test="${row.userDeliveryType == 2}">自提</c:if>
			<c:if test="${row.userDeliveryType != 2}">
				<c:choose>
					<c:when test="${row.deliveryMethodType == 1}">快递</c:when>
					<c:when test="${row.deliveryMethodType == 2}">自提</c:when>
					<c:when test="${row.deliveryMethodType == 3}">送货上门</c:when>
				</c:choose>
			</c:if>
		</display:column>
		<c:choose>
			<c:when test="${row.status == 3 || row.status == 4 }">
				<display:column title="物流公司" sortable="true">
					<select id="expressId_${row.delieveOrderId}" name="expressId">
						<option value="">请选择</option>
						<c:forEach var="express" items="${expressList}">
							<option value="${express.id}" <c:if test="${express.id==row.expressId}">selected</c:if> >${express.name}</option>
						</c:forEach>
					</select>
				</display:column>
				<display:column title="物流单号" sortable="true" style="width:157px;">
					<input type="text" id="expressNo_${row.delieveOrderId}" name="expressNo" maxlength="20" value="${row.expressNo}" class="input_text pp w100"><input class="input-btn" style="width:25px;" id="saveButtom" onclick="saveExpress(${row.id}, ${row.delieveOrderId})"; value="保存"/>
				</display:column>
			</c:when>
			<c:otherwise>
				<display:column title="物流公司" sortable="true">${row.expressName}</display:column>
				<display:column title="物流单号" sortable="true" style="width:157px;">${row.expressNo}</display:column>
			</c:otherwise>
		</c:choose>
		<display:column title="运费" sortable="true" style="width:87px;">
			<input type="text" id="deliFee_${row.delieveOrderId}" name="deliFee" maxlength="20" <c:if test="${not empty row.deliverySettleFee && row.deliverySettleFee != 0}">value="${row.deliverySettleFee/100}"</c:if>
			<c:if test="${row.deliverySettleFee == 0}">value="0"</c:if>
			class="input_text pp w30"><input class="input-btn" style="width:25px;" id="saveButtom2" onclick="saveFee(${row.id}, ${row.delieveOrderId})"; value="保存"/>
		</display:column>
		<display:column title="有无退款" sortable="true">
			<c:choose>
			  <c:when test="${row.refundStatus == 2}"><a href="../refundOrder/refundDetail.html?refundOrderId=${row.refundOrderId}"><div class="blue">有退款</div></a></c:when>
			  <c:otherwise></c:otherwise>
			</c:choose>
		</display:column>
		<display:column title='推送结果'  style="width: 30px;">
			<c:if test="${empty row.orderPushRecorder.isPush_success}">未推送</c:if>
			<c:if test="${row.orderPushRecorder.isPush_success==1 }">推送成功</c:if>
			<c:if test="${row.orderPushRecorder.isPush_success==0 }">推送失败<br> <%--输出失败原因 --%>
			${fn:substring(row.orderPushRecorder.push_result, 28, fn:length(row.orderPushRecorder.push_result)-2) }</c:if>
		</display:column>
		
		<display:column title="操作" media="html">
			<c:if test="${row.orderPushRecorder.isPush_success==0 || (empty row.orderPushRecorder.isPush_success)}">
				<c:if test="${ fn:contains(row.supplyName,'海吉星')}">
					<a class="blue pushAgain" ajaxHref="../order/pushAgain.html?delieveOrderId=${row.delieveOrderId}&orderId=${row.id}" href='#'>再推一次</a>
				</c:if>
			</c:if>
		</display:column>
	</display:table>
	
</div>
	<form id="reportfm" action="<%=basePath%>/order/orderExport.html" method="post">
		<input type="hidden" id="orderNo1" name="orderNo"/>
		<input type="hidden" id="huaId1" name="huaId"/>
		<input type="hidden" id="realName1" name="realName"/>
		<%--<input type="hidden" id="mobile1" name="mobile"/> --%>
		<input type="hidden" id="expressIds1" name="expressIds"/>
		<input type="hidden" id="productName1" name="productName"/>
		<input type="hidden" id="typeName1" name="typeName"/>
		<input type="hidden" id="supplyName1" name="supplyName"/>
		<input type="hidden" id="payMethod1" name="payMethod"/>
		<input type="hidden" id="orderStatus1" name="orderStatus"/>
		<input type="hidden" id="payStatus1" name="payStatus"/>
		<input type="hidden" id="date011" name="startTime"/>
		<input type="hidden" id="date021" name="endTime"/>
		<input type="hidden" id="tSupplyMerchantFId1" name="tSupplyMerchantFId"/>
		<input type="hidden" id="tkStatus1" name="tkStatus"/>
		<input type="hidden" id="refundStatus1" name="refundStatus"/>
		<input type="hidden" id="payTimeStart1" name="payTimeStart"/>
		<input type="hidden" id="payTimeEnd1" name="payTimeEnd"/>
	</form>
	
</body>
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<%-- <script type="text/javascript" src="<%=basePath%>/js/jquery.simple-dtpicker.js"></script> --%>
<script type="text/javascript" src="<%=basePath%>/js/jquery.common.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/datePicker/WdatePicker.js"></script>

<script type="text/javascript">
		function writeDelivery(delieveOrderId,orderNo) {
			if (delieveOrderId) {
				$("#order222").dialog({
		            title: '录入物流单号',
		            href: '<%=basePath%>/order/writeDelivery.html?delieveOrderId='+delieveOrderId+'&orderNo='+orderNo+'&rand='+Math.random(),
		            iconCls: 'icon-edit',
		            width: 400,
		            height: 250,
		            modal: true,
		            closed: true
		        }).dialog('open');
			}
		}
		
		function exp(){
			$('#orderNo1').val($('#orderNo').val());
			$('#huaId1').val($('#huaId').val());
			$('#realName1').val($('#realName').val());
			<%--$('#mobile1').val($('#mobile').val());--%>
			$('#expressIds1').val($('#expressIds').val());
			$('#productName1').val($('#productName').val());
			$('#typeName1').val($('#typeName').val());
			$('#supplyName1').val($('#supplyName').val());
			$('#payMethod1').val($('#payMethod').val());
			$('#orderStatus1').val($('#orderStatus').val());
			$('#payStatus1').val($('#payStatus').val());
			$('#date011').val($('#date01').val());
			$('#date021').val($('#date02').val());
			$('#tSupplyMerchantFId1').val($('#tSupplyMerchantFId').val());
			$('#tkStatus1').val($('#tkStatus').val());
			$('#refundStatus1').val($('#refundStatus').val());
			$('#payTimeStart1').val($('#payTimeStart').val());
			$('#payTimeEnd1').val($('#payTimeEnd').val());
			
			document.getElementById("reportfm").submit();
		}
		
		function saveExpress(orderId, delieveOrderId) {
			var expressId = $("#expressId_" + delieveOrderId).val();
			var expressNo = $("#expressNo_" + delieveOrderId).val();
			if(expressId == "" || trim(expressNo) == "") {
				$.messager.alert('操作提示', "物流公司和物流单号都不能空!", "info");
				return false;
			}
			
			$.post("<%=basePath%>/order/saveExpress.html", {
				orderId : orderId,
				delieveOrderId : delieveOrderId,
				expressId : expressId,
				expressNo : expressNo
			}, function(data) {
				if (data.message) {
					$.messager.alert('操作提示',data.message,data.status);
				}
				if(data.status == 'info') {
					$('#status_' + delieveOrderId).html("待收货");
				};
			});
		}
		
		var regex = /^([1-9]\d{0,3}|0)(\.\d{1,2})?$/;
		function saveFee(orderId, delieveOrderId) {
			var deliFee = $("#deliFee_" + delieveOrderId).val();
			deliFee = trim(deliFee);
			if(deliFee == "") {
				$.messager.alert('操作提示', "保存时，运费不能为空!", "info");
				return false;
			}
			if(!regex.test(deliFee)) {
				$.messager.alert('操作提示', "请输入小于1000的数字，最多保留两位小数!", "info");
				return false;
			}
			
			$.post("<%=basePath%>/order/saveFee.html", {
				orderId : orderId,
				delieveOrderId : delieveOrderId,
				deliFee : deliFee
			}, function(data) {
				if (data.message) {
					$.messager.alert('操作提示',data.message,data.status);
				}
			});
		}
		
		function trim(str)
		{
		     return str.replace(/(^\s*)|(\s*$)/g, '');
		}
		
		//再次推送订单
		$("a.pushAgain").click(function(){
			var isConfirm = window.confirm('确认要再次推送吗？');
			if(!isConfirm) 
				return;
			
			var $this = $(this);
			$.get($this.attr('ajaxHref'), function(data,status){
				if(data=='推送成功'){
					$this.parent().prev().html('推送成功');
					$this.parent().html('');
				}else{
					$this.parent().prev().html('推送失败<br>' + data);
				}
			});
		});
	</script>
</html>
