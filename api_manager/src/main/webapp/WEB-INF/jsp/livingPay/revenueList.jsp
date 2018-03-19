<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>生活缴费收益明细</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<style>
			.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
			.w180{width: 170px;}
		</style>
	</head>
	<body>
		<div class="info">
		    <h2 class="mtop20">结算中心</h2>
		    <div class="bs-example bgebeb">
		    	<form action="qryLivingPayRevenueList.html">
					<input name="revenueApplyId" type="hidden" value="${param.revenueApplyId}"/>
			        <table class="info-list" border="0">
			            <tr>
				            <td><div class="list-name">收费项目：</div></td>
				            <td>
								<select name="itemId" class="select_normal">
									<option value="-1">全部</option>
									<option value="1" <c:if test="${(not empty param.itemId) and (param.itemId eq 1)}">selected</c:if>>水费</option>
									<option value="2" <c:if test="${(not empty param.itemId) and (param.itemId eq 2)}">selected</c:if>>电费</option>
									<option value="3" <c:if test="${(not empty param.itemId) and (param.itemId eq 3)}">selected</c:if>>煤气费</option>
									<option value="4" <c:if test="${(not empty param.itemId) and (param.itemId eq 4)}">selected</c:if>>手机</option>
									<option value="5" <c:if test="${(not empty param.itemId) and (param.itemId eq 5)}">selected</c:if>>固话</option>
									<option value="6" <c:if test="${(not empty param.itemId) and (param.itemId eq 5)}">selected</c:if>>宽带</option>
								</select>
							</td>
							<td><div class="list-name">户号：</div></td>
							<td><input type="text" class="input_text w240" name="chargeObject" value="${param.chargeObject}"></td>
							<td><div class="list-name">联系方式：</div></td>
							<td><input type="text" class="input_text w240" name="linkTel" value="${param.linkTel}"></td>
						</tr>
						<tr>
				            <td><div class="list-name">缴费时间：</div></td>
				            <td>
				            	<input type="text" name="payTimeStart" title="请选择起始时间" value="${param.payTimeStart}" placeholder="请选择起始时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            	至
				            	<input type="text" name="payTimeEnd" title="请选择截止时间" value="${param.payTimeEnd}" placeholder="请选择截止时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            </td>
				            <td><div class="list-name">状态：</div></td>
				            <td>
				            	<select name="status" class="select_normal">
				                    <option value="">全部</option>
				                    <option value="0" <c:if test="${(not empty param.status) and (param.status eq 0)}">selected</c:if>>未充值</option>
				                    <option value="1" <c:if test="${(not empty param.status) and (param.status eq 1)}">selected</c:if>>已充值</option>
			                    </select>
				            </td>

							<td><div class="list-name">结算状态：</div></td>
							<td>
								<select name="settleStatus" class="select_normal">
									<option value="">全部</option>
									<option value="1" <c:if test="${(not empty param.settleStatus) and (param.settleStatus eq 1)}">selected</c:if>>未提款</option>
									<option value="2" <c:if test="${(not empty param.settleStatus) and (param.settleStatus eq 2)}">selected</c:if>>申请中</option>
									<option value="3" <c:if test="${(not empty param.settleStatus) and (param.settleStatus eq 3)}">selected</c:if>>已结算</option>
								</select>
							</td>
			          </tr>
						<tr>
							<td colspan="6" align="center">
								<input class="input-btn w100" type="submit" value="查询">
								<c:if test="${empty param.revenueApplyId}">
									<input id="applyRevenueBtn" class="input-btn w100 mleft10" type="button" value="提款">
								</c:if>
								<input id="exportExcelBtn" onclick="exportList();" class="input-btn w100 mleft10" type="button" value="导出">
							</td>
						</tr>
			        </table>
			    </form>
		    </div>  
		    <display:table name="resList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="total" >
		    	<display:column title="收费项目" property="typeName" headerClass="t_center"/>
				<display:column title="户号" property="chargeObject" style="text-align:left;" headerClass="t_center"/>
				<display:column title="地址" property="address" style="text-align:left;" headerClass="t_center"/>
				<display:column title="金额" style="text-align:right;" headerClass="t_center">
					<fmt:formatNumber value="${row.amount}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
				<display:column title="联系方式" property="linkTel" style="text-align:left;" headerClass="t_center"/>
				<display:column title="缴费时间" property="payTime" style="text-align:left;" headerClass="t_center"/>
				<display:column title="充值状态" property="statusStr" style="text-align:left;" headerClass="t_center"/>
				<display:column title="用户可用余额"  style="text-align:right;" headerClass="t_center">
					<fmt:formatNumber value="${row.amtBalance}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
				<display:column title="结算状态" style="text-align:right;" property="revenueStatusStr"/>
				<display:column title="操作">
					<c:if test="${row.statusStr eq '未充值'}">
						<a class="blue confirmCharge" data-id="${row.id}">确认充值</a>
					</c:if>
				</display:column>
			</display:table>
		</div>

		<div class="layer-bill confirmChargeDiv dsn" style="height: auto;padding-top:15px;padding-bottom:50px;">
				<div class="bs-example bgebeb">
					户号：<span id="r_chargeObject"></span><br>
					地址：<span id="r_address"></span><br>
					金额：<span id="r_amount"></span><br>

					<div class="mtop10">
						您确定该用户已经充值成功？<br>
						<span class="red">*用户可用余额：</span><input class="input_text" type="number" id="r_confirmRecordInput" datatype="numberFixTwo"><br>
						<input class="input-btn w100 confirmChargeBtn mtop10" type="submit" value="确定">
					</div>
				</div>
		</div>

	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/layer.min.js"></script>

	<script type="text/javascript">

		// 导出账单
		function exportList(){
			location.href = "exportExcel.html";
		}


		$(".confirmCharge").click(function () {
		    $this = $(this);
            var tdList = $this.parents("tr").children();
            $("#r_chargeObject").html(tdList.eq(1).html());
            $("#r_address").html(tdList.eq(2).html());
            $("#r_amount").html(tdList.eq(3).html());
            $(".confirmChargeBtn").attr("data-id", $this.attr("data-id"));
            $.layer({
                type: 1,
                shade: [0.4,'#000000'],
                area: ['auto', 'auto'],
                title: false,
                border : [5, 0.3, '#000'],
                page: {dom : '.confirmChargeDiv'}
            });
        });

		$(".confirmChargeBtn").click(function () {
		    var id= $(this).attr("data-id");
            $.ajax({
                type:"post",
                url:"confirmCharge.json",
                data:{"id": id, "amtBalance":$("#r_confirmRecordInput").val(), },
                dataType:"json",
                beforeSend:function(data){
                    var numberFixTwo = /^[0-9]+(\.[0-9]{0,2})?$/;
                    if($("#r_confirmRecordInput").val() == ""){
                        alert("请输入充值后的金额！");
                        return false;
                    }
                    if(!numberFixTwo.test($("#r_confirmRecordInput").val())){
                        alert("请填写数字，可带两位小数！");
                        return false;
					}
                },
                success:function(data){
                    if(data.status == '0000'){
						window.location.reload(true);
                    }
                }
            });
        });

		$("#applyRevenueBtn").click(function(){
		    var confirmApply = window.confirm("确认要提现所有充值成功的记录吗？");
		    if(!confirmApply)
		        return ;

            $.ajax({
                type:"post",
                url:"applyRevenue.json",
                data:{},
                dataType:"json",
                success:function(data){
                    if(data.status == '0000'){
                        alert(data.message);
                        window.location.reload(true);
                    }else{
                        alert("提款失败");
					}
                }
            });
		})
	</script>
</html>