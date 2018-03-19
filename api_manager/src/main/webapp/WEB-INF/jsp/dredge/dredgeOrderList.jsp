<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>维修单列表</title>
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="stylesheet" type="text/css" href="../css/style.css?v20161128">
	<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
	<link rel="stylesheet" type="text/css" href="../styles/displaytag-css/alternative.css">
	<link rel="stylesheet" type="text/css" href="../css/select2.css">
	<style>
		.searchDiv{cursor: pointer;}
	</style>
</head>
<body>
<div class="info posrelative" style="padding-bottom: 150px">
	<h2>维修单列表</h2>
	<div class="bs-example bgebeb">
		<form action="<%=basePath%>/dredge/dredgeOrderList.html" method="post" id="searchForm">
			<table class="info-list" border="0">
				<tr>
					<td><div class="list-name">下单解放号：</div></td>
					<td><input type="text" id="tUserFId" name="tUserFId" value="${param.tUserFId}" class="input_text w120 pp"></td>
					<td><div class="list-name">手机号：</div></td>
					<td><input type="text" name="userTel" value="${param.userTel}" class="input_text w120 pp"></td>
					<td><div class="list-name">维修类型：</div></td>
					<td><input type="text" name="dredgeType" value="${param.dredgeType}" class="input_text w120 pp"></td>
					<%--
					<td><div class="list-name">内容描述：</div></td>
					<td><input type="text" name="content" value="${param.content}" class="input_text w120 pp"></td>
					--%>
					<td><div class="list-name">师傅手机号：</div></td>
					<td><input type="text" name="workerMobile" value="${param.workerMobile}" class="input_text w120 pp"></td>
					<td><div class="list-name">下单时间：</div></td>
					<td><input type="text" value='${param.sys0AddTime_START }' name="sys0AddTime_START" class="input_text icon_dt" id="date01" title="请选择起始时间" value="请选择起始时间"> 至
						<input type="text" value='${param.sys0AddTime_END }' name="sys0AddTime_END" class="input_text icon_dt" id="date02" title="请选择结束时间" value="请选择结束时间">
					</td>
				</tr>
				<tr>
					<td><div class="list-name">预约时间：</div></td>
					<td>
						<input type="text" value='${param.expectdate }' name="expectdate" class="input_text icon_dt" id="date01" title="请选择起始时间" value="请选择起始时间">
					</td>
					<td><div class="list-name">联系电话：</div></td>
					<td><input type="text" name="tel" value="${param.tel}" class="input_text w120 pp"></td>
					<td><div class="list-name">城市：</div></td>
					<td><input type="text" name="cityName" value="${param.cityName}" class="input_text w120 pp"></td>
					<td><div class="list-name">区县：</div></td>
					<td><input type="text" name="blockName" value="${param.blockName}" class="input_text w120 pp"></td>
					<td><div class="list-name">支付时间：</div></td>
					<td><input type="text" value='${param.payTime_START }' name="payTime_START" class="input_text icon_dt" id="date01" title="请选择起始时间" value="请选择起始时间"> 至
						<input type="text" value='${param.payTime_END }' name="payTime_END" class="input_text icon_dt" id="date02" title="请选择结束时间" value="请选择结束时间">
					</td>
				</tr>
				<tr>
					<td><div class="list-name">维修地址：</div></td>
					<td><input type="text" name="address" value="${param.address}" class="input_text w120 pp"></td>
					<td><div class="list-name">师傅解放号：</div></td>
					<td><input type="text" id="tWorkerFId" name="tWorkerFId" value="${param.tWorkerFId}" class="input_text w120 pp"></td>
					<td><div class="list-name">师傅姓名：</div></td>
					<td><input type="text" name="workerName" value="${param.workerName}" class="input_text w120 pp"></td>
					<td><div class="list-name">订单状态：</div></td>
					<td>
						<select class="select_normal" name="combineStatus">
							<option value="">全部</option>
							<option value="1" <c:if test="${param.combineStatus=='1' }"> selected="selected"</c:if>>待付款</option>
							<option value="2" <c:if test="${param.combineStatus=='2' }"> selected="selected"</c:if>>待分配</option>
							<option value="3" <c:if test="${param.combineStatus=='3' }"> selected="selected"</c:if>>待服务</option>
							<option value="4" <c:if test="${param.combineStatus=='4' }"> selected="selected"</c:if>>已服务</option>
							<option value="5" <c:if test="${param.combineStatus=='5' }"> selected="selected"</c:if>>已完成</option>
							<option value="6" <c:if test="${param.combineStatus=='6' }"> selected="selected"</c:if>>已取消</option>
							<option value="7" <c:if test="${param.combineStatus=='7' }"> selected="selected"</c:if>>退款中</option>
							<option value="8" <c:if test="${param.combineStatus=='8' }"> selected="selected"</c:if>>已退款</option>
						</select>
					</td>
					<td><div class="list-name">订单号：</div></td>
					<td><input type="text" name="billNo" value="${param.billNo}" class="input_text w120 pp"></td>
				</tr>
				<tr>
					<td><div class="list-name">支付解放号：</div></td>
					<td><input type="text" id="buyerId" name="buyerId" value="${param.buyerId}" class="input_text w120 pp"></td>
					<td><div class="list-name">维修供应商：</div></td>
					<td><input type="text" id="serviceSupplier" name="serviceSupplier" value="${param.serviceSupplier}" class="input_text w120 pp"></td>
				</tr>
				<tr>
					<td colspan="10" class="t_center">
						<input class="input-btn w200" type="submit" value="搜索">&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="exportDrdegeBill" class="weak-btn small-btn w150" type="button" value="导出">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI=""  partialList="true" size="resultSize">
		<%--<display:column title="解放号" property="tUserFId"/> --%>
		<display:column title="订单号" property="billNo"/>
		<display:column title="维修类型">
			${row.dredgeType}<c:if test="${empty row.dredgeType2nd }">-${row.dredgeType2nd }</c:if>
		</display:column>
		<display:column title="服务供应商" property="serviceSupplier"/>
		<display:column title="商品名称" property="dredgeProductName" class="order-dredgeProductName"/>
		<display:column title="订单总额" class="order-amount">
			<c:choose>
				<c:when test="${not empty row.totalAmount}">${row.totalAmount/100.0}</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</display:column>
		<display:column title="下单时间" property="sys0AddTime"/>
		<display:column title="支付时间" property="payTime"/>
		<%--<display:column title="预约时间" property="expectdate" sortable="true"/> --%>
		<display:column title="预计上门时间" property="expectWorkTime"/>
		<display:column title="城市" property="cityName"/>
		<display:column title="区县" property="blockName"/>
		<display:column title="维修地址" property="address"/>
		<display:column title="问题描述" style="max-width: 150px;">
			<c:choose>
			 <c:when test="${fn:length(row.content) <= '12'}">${row.content }</c:when>
			 <c:otherwise>${fn:substring(row.content, 0, 12)}...</c:otherwise>
			</c:choose>
		</display:column>
		<display:column title="下单解放号">
			<div onclick="searchByUser(this)" inputId="tUserFId" class="searchDiv">${row.tUserFId}</div>
		</display:column>
		<display:column title="下单手机号" property="userTel"/>
		<display:column title="支付解放号">
			<div onclick="searchByUser(this)" inputId="buyerId" class="searchDiv">${row.buyerId}</div>
		</display:column>
		<display:column title="联系人" property="linkName"/>
		<display:column title="联系电话" property="tel"/>
		<display:column title="师傅解放号">
			<div onclick="searchByUser(this)" inputId="tWorkerFId" class="searchDiv">${row.tWorkerFId}</div>
		</display:column>
		<display:column title="师傅姓名" property="workerName"/>
		
		<%--
		<display:column title="内容描述" property="content"/>
		<display:column title="现场照片" media="html">
			<c:choose>
				<c:when test="${not empty row.picUrl}">
					<a class="blue" target="_blank" href="<%=basePath%>/dredge/getScenePhotos.json?id=${row.id}">查看照片</a>
				</c:when>
				 <c:otherwise>
             		 暂无照片
      			 </c:otherwise>
      			 
			</c:choose>
		</display:column>
		--%>
		
		<%--
		<display:column title="人工费">
			<c:choose>
				<c:when test="${not empty row.dredgeBillAmountDetailList[0].payAmount}">${row.dredgeBillAmountDetailList[0].payAmount/100.0}</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</display:column>
		<display:column title="其他费">
			<c:choose>
				<c:when test="${not empty row.dredgeBillAmountDetailList[1].payAmount}">${row.dredgeBillAmountDetailList[1].payAmount/100.0}</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</display:column>
		--%>
		<%--
		<display:column title="师傅手机" property="workerMobile"/>
		 --%>
		<%--
		<display:column title="星级">
			<c:choose>
				<c:when test="${not empty row.commentLevel}">${row.commentLevel}</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</display:column>
		--%>
		<display:column title="流程记录">
			<c:choose>
				<c:when test="${row.status==2 or row.status==3 or row.status==4 or row.status==5 or row.status==7 or row.status==8}"> 	
					<c:choose>
						<c:when test="${row.processRecordCount>0 }">
							<a class="blue" target="_blank" href="<%=basePath%>/dredge/viewProcessRecord.json?id=${row.id}">查看流程照片</a>
						</c:when>
						<c:otherwise>
		 					 暂无流程记录
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</display:column>
		
		<display:column title="订单状态" class="order-status">
			<c:choose>
				<c:when test="${row.combineStatus==1}">
					<c:if test="${row.billType == 5}">
						<span title="已下单/未支付/未分配">待付款</span>
					</c:if>
				</c:when>
				<c:when test="${row.combineStatus==2}">
					<c:if test="${row.billType == 5}">
						<span title="已下单/已支付/未分配">待分配</span>
					</c:if>
					<c:if test="${row.billType != 5}">
						<span title="已下单/未支付/未分配">待分配</span>
					</c:if>
				</c:when>
				<c:when test="${row.combineStatus==3}">
					<c:if test="${row.billType == 5}">
						<span title="已下单/已支付/已分配">待服务</span>
					</c:if>
					<c:if test="${row.billType != 5}">
						<span title="已下单/未支付/已分配">待服务</span>
					</c:if>
				</c:when>
				<c:when test="${row.combineStatus==4}">
					<c:if test="${row.billType == 5}">
						<span title="已下单/已支付/已服务">已服务</span>
					</c:if>
					<c:if test="${row.billType != 5}">
						<span title="已下单/未支付/已服务">已服务</span>
					</c:if>
				</c:when>
				<c:when test="${row.combineStatus==5}">
					<c:if test="${row.billType == 5}">
						<span title="已完成/已支付/已服务">已完成</span>
					</c:if>
					<c:if test="${row.billType != 5}">
						<span title="已完成/已支付/已服务">已完成</span>
					</c:if>
				</c:when>
				<c:when test="${row.combineStatus==6}">
					<c:if test="${row.billType == 5}">
						<span title="已取消/未支付/未分配">已取消</span>
					</c:if>
					<c:if test="${row.billType != 5}">
						<span title="已取消/未支付">已取消</span>
					</c:if>
				</c:when>
				<c:when test="${row.combineStatus==7}">
					<c:if test="${row.billType == 5}">
						<span title="退款中">退款中</span>
					</c:if>
				</c:when>
				<c:when test="${row.combineStatus==8}">
					<c:if test="${row.billType == 5}">
						<span title="已退款">已退款</span>
					</c:if>
					<c:if test="${row.billType != 5}">
						<span title="已退款">已退款</span>
					</c:if>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</display:column>
		<display:column title="操作" media="html">
			<a class="blue viewTicket" href="<%=basePath%>/dredge/viewDetail.html?dredgeId=${row.id}">查看</a>
			<a class="blue trackBtn" data="${row.id}" href="javascript:void(0)">跟踪</a>
			<c:choose>
				<c:when test="${row.combineStatus==2}">
					<c:if test="${row.billType != 5}">
						<a class="blue" href="javascript:void(0)" onclick="cancelDredgeBill(this,${row.id})">取消订单</a>
					</c:if>
					<c:if test="${row.billType == 5}">
						<a class="blue refund-apply-btn" data-id="${row.id}" data-billType="${row.billType }" href="javascript:void(0)">申请退款</a>
					</c:if>
					<a class="blue sendBillBtn" data-id="${row.id}" data-billType="${row.billType }" href="javascript:void(0)">派单</a>
				</c:when>
				<c:when test="${row.combineStatus==3}">
					<c:if test="${row.billType == 5}">
						<a class="blue refund-apply-btn" data-id="${row.id}" data-billType="${row.billType }" href="javascript:void(0)">申请退款</a>
					</c:if>
					<c:if test="${row.billType != 5}">
						<a class="blue" href="javascript:void(0)" onclick="cancelDredgeBill(this,${row.id})">取消订单</a>
					</c:if>
					<a class="blue check-done-btn" data-id="${row.id}" data-billType="${row.billType }" href="javascript:void(0)">确认完成</a>
					<a class="blue resend-bill-btn" data-id="${row.id}" data-billType="${row.billType }" href="javascript:void(0)">订单改派</a>
					<%--<c:choose>
						<c:when test="${not empty row.serviceSupplierId}">
							<a class="blue" onclick="view3rdStatus(this);" dbId="${row.id }" href="javascript:void(0)">查看状态</a>
						</c:when>
					</c:choose>--%>
				</c:when>
			</c:choose>
			<%--<c:if test="${row.status == 1 || row.status == 2}"><a class="blue editStatuBtn" data="${row.id}" href="javascript:void(0)">修改状态</a></c:if>--%>
		</display:column>
	</display:table>
</div>
<div class="layer-classify sendbill-info-box dsn">
	<form class="inputform" action="assignBillToWorker.json" method="post" >
		<input type="hidden" class="sendBill_dredgebillid" name="dredgeBillId" value=""/>
		<input type="hidden" class="sendBill_dredgeBillBillType" name="dredgeBillBillType" value=""/>
		<table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td width="90">师傅选择</td>
				<td width="250">
					<select id="selectBox01" name="workerId" class="select2_class select_normal" datatype="*" nullmsg="请选择师傅">

					</select>
				</td>
			</tr>
			<tr>
				<td width="90">日期选择</td>
				<td width="250">
					<input type="text" value="" name="expectWorkTime" class="input_text icon_dt" id="date01" title="请选择起始时间">
				</td>
			</tr>
		</table>
		<div class="mtop20 t_center"><input class="info-btn small-btn w80 sendBillCheckBtn" type="button" value="确定"></div>
	</form>
</div>

<div class="layer-classify track-info-box dsn">
	<form class="inputform01" action="../dredge/updateDredgeBillProgress.json" method="post">
		<input type="hidden" class="progress_dredgebillid" name="dredgeBillId" value=""/>
		<h3>跟进情况</h3>
		<div class="mright10 mtop10" style="height: 160px;">
			<textarea class="textareas txt02" name="rateProgress" cols="" rows="5" datatype="*" nullmsg="请填写跟进情况" ></textarea>
			<div>还可输入<span class="leftNum">100</span>字</div>
		</div>
		<div class="t_center"><input class="info-btn small-btn w80 trackInfoBtn" type="button" value="提交"></div>
	</form>
</div>

<div class="layer-classify check-order-box dsn" style="min-width: 400px; padding-bottom: 80px;">
	<h3 class="t_center">确认订单完成</h3>
	<ul class="mright10 mtop10">
		<li><span>商品名称：</span> <span class="item-name" id="finish-dredgeProductName"></span></li>
		<li class="bill-type-5 dsn"><span>订单总额：</span> <span class="order-total-price"></span> 元</li>
		<li class="bill-type-not-5 dsn">
			<select id="feeChange" class="select_normal">
				<option value="1">仅人工费</option>
				<option value="2">人工费+其他费</option>
			</select>
		</li>
		<li class="mtop10 bill-type-not-5 dsn"><span>人工费：</span> <input class="input_text w120 pp" type="text" name="laborAmount" /> 元</li>
		<li class="mtop10 swap-val swap-val-2 dsn"><span>其他费：</span> <input class="input_text w120 pp" type="text" name="materialAmount" /> 元</li>
	</ul>
    <div class="mtop20 t_center">
	    <input class="info-btn small-btn w80 check-order-btn" type="button" value="确认完成">
	    <input class="input-btn w80 mar-left15 cancel-box-btn" type="button" value="取消" />
    </div>
</div>

<div class="layer-classify cancel-order-box dsn" style="min-width: 400px">
	<h3 class="t_center">确认取消订单</h3>
	<ul class="mright10 mtop10">
		<li><span>商品名称：</span> <span class="item-name" id="cancel-dredgeProductName"></span></li>
		<li><span>订单总额：</span> <span class="order-total-price"></span> 元</li>
	</ul>
    <div class="mtop20 t_center">
	    <input class="info-btn small-btn w80 cancel-order-btn" type="button" value="确认取消">
    </div>
</div>

<div class="layer-bill edit-status-box dsn" method="post">
	<form class="inputform02" action="../dredge/editDredgeBillStatus.json">
		<input type="hidden" class="editDredgeBillStatus_dredgebillid" name="dredgeBillId" value=""/>
		<table class="info-list-02 mtop30" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td width="90">订单状态</td>
				<td>
					<select class="select_normal" name="status" datatype="*" nullmsg="请选择订单状态">
						<option value="">选择订单状态</option>
						<option value="4">用户已取消</option>
						<option value="1">可接单</option>
					</select>
				</td>
			</tr>
		</table>
		<div class="mtop20 t_center"><input class="info-btn small-btn w80 editStatuCheckBtn" type="button" value="提交"></div>
	</form>
</div>
<div class="pop-box-trackinfo p010 dsn">
    <table id="trackInfoTable" border="0" cellpadding="0" cellspacing="1">
      <tr class="nobg">
        <td>2016-11-23</td>
        <td>周三三</td>
        <td>商品正在出库商品正在出库商品</td>
      </tr>
      <tr class="nobg">
        <td>2016-11-23</td>
        <td>周三三</td>
        <td>商品正在出库</td>
      </tr>
    </table>
</div>

<div class="layer-classify refund-apply-box dsn" style="padding-bottom: 60px;">
    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
      <tr class="title">
        <td>订单号</td>
        <td>商品名称</td>
        <td>订单总额</td>
        <td>支付方式</td>
        <td>支付时间</td>
        <td>优惠券抵扣</td>
        <td>粮票抵扣</td>
        <td>用户实付</td>
      </tr>
      <tr>
        <td class="order-no"></td>
        <td class="item-name"></td>
        <td class="order-total"></td>
        <td class="pay-type"></td>
        <td class="pay-time"></td>
        <td class="coupon-reduce"></td>
        <td class="ticket-reduce"></td>
        <td class="user-pay"></td>
      </tr>
    </table>
    <table class="info-list mtop10" border="0">
        <tr>
            <td width="100" align="right">退款方式：</td>
            <td colspan="3">
				<input type="hidden" name="refundType" value="1">
				全额退款
            </td>
        </tr>
        <tr class="swap-info">
            <td width="30%" align="right">实付金额退款：</td>
            <td width="120">
				<input class="input_text w80 pp" type="hidden" id="refundAmountDecimalAdd" name="refundAmountDecimalAdd" />
				<span id="refundAmountDecimalAddSpan"></span>
			</td>
            <td align="right">粮票退款：</td>
            <td>
				<input class="input_text w80 pp" id="refundCouponAmountDecimalAdd" type="hidden" name="refundCouponAmountDecimalAdd" />
				<span id="refundCouponAmountDecimalAddSpan"></span>
			</td>
        </tr>
        <tr>
            <td align="right">退款原因：</td>
            <td colspan="3" class="refund-reason"><input class="input_text w120 pp" type="text" name="refundReason" /></td>
        </tr>
        <tr>
            <td align="right">备注：</td>
            <td colspan="3">
				<textarea class="textareas" style="width:100%; height: 50px;" name="note" cols="" rows="3"></textarea>
			</td>
        </tr>
    </table>
    <div class="mtop20 t_center">
	    <input class="info-btn small-btn w80 refund-check-btn" type="button" value="确认退款">
    </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/jquery.form.js?v=5"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js?v20161128"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript">

	$("#searchBtn").click(function(){//查找
		$("#searchForm").attr("action","<%=basePath%>/dredge/dredgeOrderList.html");
		$("#searchForm").submit();
	});
	
	//查看供应商订单的状态	
	function view3rdStatus(ths){
		var $this = $(ths);

		$.ajax({//使用ajax请求
			//type : "GET",
			url : "querySfdjOrderStatus.json",
			data: {dredgeBillId:$this.attr('dbId')},
			async : false,
			dataType:"json",
			success : function(data) {
				var resultObj = JSON.parse(data);
				alert("订单状态：" + resultObj.msg);
			}
			});
		}

	$(function(){
		//表单验证
		$(".inputform").Validform({
			tiptype:3,
			btnSubmit: '.sendBillCheckBtn',
			dragonfly:true,
			ajaxPost:false,
			ignoreHidden: true,
			beforeSubmit:function(curform){
				if($(".inputform").find("input[name='otherFee']").val() !== '' && $(".inputform").find("input[name='laborFee']").val() === '') {
					$(".inputform").find("input[name='laborFee']").siblings('.Validform_checktip').addClass('Validform_wrong').text('请填写人工费');
					return false;
				}
				$(".inputform").attr('onsubmit','return false;');
			},
			callback:function(data){
				//console.log(data);
				$(".inputform").ajaxSubmit(function(data) {
					console.log(data);
					try {
						data = eval("("+data+")");
					} catch (e) {
						try {
							data = eval(data);
						} catch (e2) {}
					}
					if (data.status == '0000') {
						alert('操作成功');
						location.reload();
					} else {
						alert("推送失败，原因：" + data.message);
					}
				});
			}
		});
		$(".inputform01").Validform({
			tiptype:3,
			btnSubmit: '.trackInfoBtn',
			dragonfly:true,
			ajaxPost:false,
			ignoreHidden: true,
			postonce:false,
			beforeSubmit:function(curform){
				$(".inputform01").attr('onsubmit','return false;');
			},
			callback:function(data){
				$(".inputform01").ajaxSubmit(function(data) {
					data = data.replace("<PRE>","");
					data = data.replace("</PRE>","");
					try {
						data = eval("("+data+")");
					} catch (e) {
						try {
							data = eval(data);
						} catch (e2) {}
					}
					if (data.status == '0000') {
						alert('操作成功');
						location.reload();
					} else {
						alert(data.message);
						location.reload();
					}
				});
			}
		});
		$(".inputform02").Validform({
			tiptype:3,
			btnSubmit: '.editStatuCheckBtn',
			dragonfly:true,
			ajaxPost:false,
			ignoreHidden: true,
			postonce:false,
			beforeSubmit:function(curform){
				$(".inputform02").attr('onsubmit','return false;');
			},
			callback:function(data){
				$(".inputform02").ajaxSubmit(function(data) {
					data = data.replace("<PRE>","");
					data = data.replace("</PRE>","");
					try {
						data = eval("("+data+")");
					} catch (e) {
						try {
							data = eval(data);
						} catch (e2) {}
					}
					if (data.status == '0000') {
						alert('操作成功');
						location.reload();
					} else {
						alert(data.message);
						location.reload();
					}
				});
			}
		});

		$('.select2_class').select2();

		$("#selectBox01").on('change', function(){
			console.log('触发二级菜单');
		});

		//获取师傅列表
		function getMastersList(){
			$.ajax({
				url:"getDredgeWorkerList.json",
				dataType:"json",
				success:function(data){
					var mastersList = '<option value="">选择师傅</option>';
					var list = data.dataValue;
					$.each(list, function(i) {
						mastersList += '<option value="'+list[i].id+'">' + list[i].value + '</option>';
					});
					$("#selectBox01").html(mastersList);
				}
			});
		}
		
		//派单弹出层
		$('.sendBillBtn, .resend-bill-btn').click(function(){
			$('.inputform').Validform().resetForm();
			$('.Validform_checktip').text('');
			
			var billType = $(this).attr("data-billType");
			if(billType == 5) //服务前付款
				$(".tr_fee").hide();
			else
				$(".tr_fee").show();
			
			$.layer({
				type: 1,
				shade: [0.4,'#000000'],
				area: ['auto', 'auto'],
				title: false,
				border : [5, 0.3, '#000'],
				page: {dom : '.layer-classify.sendbill-info-box'}
			});
			getMastersList();
			$(".sendBill_dredgebillid").val($(this).attr("data-id"));
		});
		
		//确认完成弹出层
		var curBillType;
		var curBillId;
		$('.check-done-btn').click(function(){
			curBillType = $(this).attr('data-billtype');
			curBillId = $(this).attr('data-id');
			$('.order-total-price').text( $(this).parents('tr').find('.order-amount').text() );
			$('#finish-dredgeProductName').text($(this).parents('tr').find('.order-dredgeProductName').text());
			
			//服务前的不用显示人工费这两个字段，要显示订单总额字段
			if(curBillType === '5'){
				$('.bill-type-5').removeClass('dsn');
			}else{
				$('.bill-type-not-5').removeClass('dsn');
			}

			$.layer({
				type: 1,
				shade: [0.4,'#000000'],
				area: ['auto', 'auto'],
				title: false,
				border : [5, 0.3, '#000'],
				page: {dom : '.layer-classify.check-order-box'}
			});
		});
		//确认完成
		$('.check-order-btn').click(function(){
			var dataParams = {'dredgeBillId': curBillId };
			if($('[name=laborAmount]').is(':visible')){
				var laborAmount = $.trim( $('[name=laborAmount]').val() );
				var materialAmount = $.trim( $('[name=materialAmount]').val() );
				var numberFixTwo = new RegExp("^[0-9]+(\.[0-9]{0,2})?$");
				
				if(laborAmount === ''){
					alert('请完善信息');
					return;
				}
				if($('#feeChange').val() === '1'){
					if(!numberFixTwo.test(laborAmount)){
						alert('请填写数字，可带两位小数');
						return;
					}
				}else{
					if(!numberFixTwo.test(laborAmount) || !numberFixTwo.test(materialAmount)){
						alert('请填写数字，可带两位小数');
						return;
					}
				}
				
				dataParams = {'dredgeBillId': curBillId, 'laborAmount': laborAmount, 'materialAmount': materialAmount};
			}
			$.ajax({
	            type : "post",
	            url : "confirmFinish.html", 
	            data: dataParams,
	            async : false,
	            dataType:"json",
	            beforeSend: function(){
	            	$.Showmsg('请稍候');
	            },
	            success : function(data) {
	                alert("确认成功");
	                window.location.reload();
	            }
	        });
		});
		//确认完成弹出层取消
		$('.cancel-box-btn').click(function(){
			layer.closeAll();
		});

		//跟踪弹出层
		$('.trackBtn').click(function(){
			$.layer({
				type: 1,
				shade: [0.4,'#000000'],
				area: ['auto', 'auto'],
				title: false,
				border : [5, 0.3, '#000'],
				page: {dom : '.layer-classify.track-info-box'}
			});
			$(".progress_dredgebillid").val($(this).attr("data"));
		});

		//修改状态弹出层
		$('.editStatuBtn').click(function(){
			$.layer({
				type: 1,
				shade: [0.4,'#000000'],
				area: ['auto', 'auto'],
				title: false,
				border : [5, 0.3, '#000'],
				page: {dom : '.layer-bill.edit-status-box'}
			});
			$(".editDredgeBillStatus_dredgebillid").val($(this).attr("data"));
		});
	});
</script>
<script type="text/javascript">
	$(function(){
		$('.trackBtn').mouseover(function(){
			var $this = $(this);
			$this.addClass('posrelative');
			var $popBoxTrackinfo = $('.pop-box-trackinfo');
			
			getTrackInfoData($this, '/dredge/qryOrderFollowRecord.json');
			
			$popBoxTrackinfo.appendTo($this).toggle();
		});
		$('.trackBtn').mouseout(function(){
			$('.pop-box-trackinfo').hide();
		});
		//获取数据
		function getTrackInfoData(obj, url){
			$.ajax({
				url:'/api_manager'+url,
				dataType:"json",
				data: {id: $(obj).attr('data')},
				success:function(data){
					var tracklist = '';
					if(data.dataValue == ''){
						tracklist += '<tr class="nobg"><td>暂无跟踪信息</td>';
					}else{
						$.each(data.dataValue, function(i,dataList) {
								tracklist += '<tr class="nobg"><td>'+dataList.followTime+'</td>'+'<td>'+dataList.followUser+'</td>'+'<td>'+dataList.content+'</td></tr>';
						});
					}
					$('#trackInfoTable').html(tracklist);
				}
			});
		}

		$("#exportDrdegeBill").click(function(){
			if($(".empty").length){//表行有空行，即无查询结果
				alert("没有可导出的信息。");
				return false;
			}
			window.location.href="../dredge/exportDredgeOrders.json";
		});
		
		//订单列表变色
		$('.order-status').each(function(){
			var curText = $(this).text();
			if(curText.indexOf('待付款') > -1){
				$(this).parent('tr').css('background', '#fee8eb').addClass('nobg');
			}
			if(curText.indexOf('可接单') > -1){
				$(this).parent('tr').css('background', '#e8feec').addClass('nobg');
			}
		});
		
	});
	
	function searchByUser(o){
		var v = $.trim($(o).html());
		var id = $(o).attr("inputId");
		$("#"+id).val(v);
		$("#searchForm").submit();
	}

	function cancelDredgeBill(obj, dredgeBillId) {
		curBillId = dredgeBillId;
		$('.order-total-price').text( $(obj).parents('tr').find('.order-amount').text() );
        $('#cancel-dredgeProductName').text($(obj).parents('tr').find('.order-dredgeProductName').text());
	    $.layer({
			type: 1,
			shade: [0.4,'#000000'],
			area: ['auto', 'auto'],
			title: false,
			border : [5, 0.3, '#000'],
			page: {dom : '.layer-classify.cancel-order-box'}
		});
    }
	
	$('.cancel-order-btn').click(function(){
        $.ajax({//使用ajax请求
            type : "POST",
            url : "cancel.html",
            data: {dredgeBillId: curBillId},
            async : false,
            dataType:"json",
            success : function(data) {
                alert("取消成功");
                window.location.reload();
            }
        });
	})
	
	selectOptionChange('#refundType');
	var refundId;
	//申请退款弹窗
	$('.refund-apply-btn').click(function(){

		refundId = $(this).attr('data-id');
		$(this).addClass('cur-pop-btn');
		//获取对应订单信息
		$.ajax({
			type: 'get',
			url: '../dredge/applyRefundInfo.json',
			data: {'dredgeBillId': refundId},
			success: function(data){
				if(data.status === '0000'){
					//审核订单
					getRefundInfo('.refund-apply-box', data.dataValue.detail);

					$.layer({
						type: 1,
						shade: [0.4,'#000000'],
						area: ['auto', 'auto'],
						title: false,
						border : [5, 0.3, '#000'],
						page: {dom : '.refund-apply-box'}
					});
				}
			}
		});

	});

	//提交退款申请
	$('.refund-check-btn').click(function(){
		$.ajax({
			type: 'post',
			url: '../dredgeRefund/addRefund.html',
			data: {'dredgeBillFId': refundId, 'refundType': $('[name=refundType]').val(), 'refundAmountDecimalAdd': $('[name=refundAmountDecimalAdd]').val(), 'refundCouponAmountDecimalAdd': $('[name=refundCouponAmountDecimalAdd]').val(), 'refundReason': $('[name=refundReason]').val(), 'note': $('[name=note]').val()},
			success: function(data){
				if(data.status === '0000'){
					alert('操作成功！');
					window.location.reload();
				}else{
					alert(data.message);
				}
			}
		});
	});
	//费用选择
	if($('#feeChange').length){
		selectChangePlus('#feeChange', '.swap-val');
	}


function getRefundInfo(obj, data){
	$(obj).find('.order-no').text(data.billNo);
	$(obj).find('.item-name').text(data.dredgeProductName);
	$(obj).find('.order-total').text((data.billTotalAmount/100).toFixed(2));
	$(obj).find('.pay-type').text(data.payMethodDesc);
	$(obj).find('.pay-time').text(data.payTime);
	$(obj).find('.coupon-reduce').text((data.couponDiscountMoney/100).toFixed(2));
	$(obj).find('.ticket-reduce').text(((data.orderCouponAmount - data.couponDiscountMoney)/100).toFixed(2));
	$(obj).find('.user-pay').text((data.orderAmount/100).toFixed(2));
    $(obj).find('#refundAmountDecimalAddSpan').text((data.orderAmount/100).toFixed(2) + '元');
    $(obj).find('#refundAmountDecimalAdd').val((data.orderAmount/100).toFixed(2));
    $(obj).find('#refundCouponAmountDecimalAddSpan').text(((data.orderCouponAmount - data.couponDiscountMoney)/100).toFixed(2) + '元');
    $(obj).find('#refundCouponAmountDecimalAdd').val(((data.orderCouponAmount - data.couponDiscountMoney)/100).toFixed(2));

}
</script>
</html>
