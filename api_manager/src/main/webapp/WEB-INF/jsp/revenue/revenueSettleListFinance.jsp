<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>//" />
	<title>结算中心</title>
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
	<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css"/>
	<style type="text/css">
		.text-right{text-align: right;}
	</style>
	<script type="text/javascript">
	function exp(){
		location.href = "${pageContext.request.contextPath}/revenueSettle/exportExcel.html?"+$("#searchForm").serialize();
	}
	
	function onSubmit() {
		$("#searchForm").attr("action", "<%=basePath%>/revenueSettle/listFinance.html");
		$("#searchForm").submit();
	}
	</script>
</head>
<body>
<div class="info"> 
    <h2>结算中心</h2>
    <div class="bs-example bgebeb">
    <form id="searchForm" action="<%=basePath%>/revenueSettle/listFinance.html">
        <table class="info-list" border="0">
          <tr>
           	<td><div class="list-name">发起时间：</div></td>
            <td colspan="3"><input id="date01" value='${param.date01 }' name="date01" type="text" class="input_text icon_dt"  title="请选择起始时间" /> 至 <input  type="text" class="input_text icon_dt" id="date02"  value='${param.date02 }'  name="date02" title="请选择结束时间"/></td>	   
			<td><div align="list-name">提款单号：</div></td>
            <td><input class="input_text w120 pp" type="text" name="tkNo" value="${param.tkNo }"/></td>
			<td><div align="list-name">申请对象名称：</div></td>
            <td><input class="input_text w120 pp" type="text" name="roleName" value="${param.roleName}"/></td>
            <td><div class="list-name">申请对象类型：</div></td>
            <td>
            	<select class="select_normal w131" id="roleType" name="roleType">
            		<option value="">全部</option>
            		<option value="2" <c:if test="${param.roleType==2 or param.roleType==13}"> selected="selected"</c:if>>物业</option>
            		<option value="3" <c:if test="${param.roleType==3 }"> selected="selected"</c:if>>代理</option>
            		<option value="5" <c:if test="${param.roleType==5 or param.roleType==12}"> selected="selected"</c:if>>店铺</option>
            		<option value="6" <c:if test="${param.roleType==6 }"> selected="selected"</c:if>>师傅</option>
            		<option value="7" <c:if test="${param.roleType==7 }"> selected="selected"</c:if>>推荐人</option>
				 	<option value="15" <c:if test="${param.roleType==15 }"> selected="selected"</c:if>>生活缴费运营</option>
            		<%-- <option value="13" <c:if test="${param.roleType==13 }"> selected="selected"</c:if>>物业管理处</option> --%>
            	</select>
            </td>
            <td><div class="list-name">结算项目：</div></td>
            <td>
            	<select class="select_normal w131" id="goalType" name="goalType" onchange="changeGoalType()">
            		<option value="">全部</option>
            		<option value="1" <c:if test="${param.goalType==1}"> selected="selected"</c:if>>认证门牌</option>
            		<option value="2" <c:if test="${param.goalType==2}"> selected="selected"</c:if>>维修收益</option>
            		<option value="3" <c:if test="${param.goalType==3}"> selected="selected"</c:if>>物业宝佣金</option>
            		<option value="4" <c:if test="${param.goalType==4}"> selected="selected"</c:if>>超市收益</option>
            		<option value="18" <c:if test="${param.goalType==18}"> selected="selected"</c:if>>生活缴费代收</option>
            		<option value="5" <c:if test="${param.goalType==5}"> selected="selected"</c:if>>物业费实收</option>
            		<option value="6" <c:if test="${param.goalType==6}"> selected="selected"</c:if>>停车费实收</option>
            		<option value="7" <c:if test="${param.goalType==7}"> selected="selected"</c:if>>其他代收费用</option>
            		<option value="8" <c:if test="${param.goalType==8}"> selected="selected"</c:if>>物业宝抵扣</option>
            		<option value="9" <c:if test="${param.goalType==9}"> selected="selected"</c:if>>停车宝佣金</option>
            		<option value="10" <c:if test="${param.goalType==10}"> selected="selected"</c:if>>停车宝抵扣</option>
            		<option value="15" <c:if test="${param.goalType==15}"> selected="selected"</c:if>>物业费补贴</option>
            		<option value="16" <c:if test="${param.goalType==16}"> selected="selected"</c:if>>供应商结算</option>
            		<option value="17" <c:if test="${param.goalType==17}"> selected="selected"</c:if>>停车费补贴</option>
            		<option value="100" <c:if test="${param.goalType==100}"> selected="selected"</c:if>>所有补贴</option>
            	</select>
            </td>
           </tr>
           <tr>
            <td><div class="list-name">结算状态：</div></td>
            <td>
            	<select class="select_normal w131" name="tkStatus">
            		<option value="-1">全部</option>
            		<option value="2" <c:if test="${param.tkStatus==2 }"> selected="selected"</c:if>>申请中</option>
            		<option value="3" <c:if test="${param.tkStatus==3 }"> selected="selected"</c:if>>已转账</option>
            	</select>
            </td>
           </tr>
          <tr>
            <td><div class="list-name">结算金额总计：</div></td>
         	<td>
         		<span class="bold f16 red"><fmt:formatNumber value="${totalAmountAll}" type="currency" pattern="0.00" maxFractionDigits="2"/></span> 元
            </td>
            <td colspan="4" align="center">
            	<input class="info-btn small-btn w100" type="button" value="查 询" onclick="onSubmit();" />&nbsp;&nbsp;&nbsp;&nbsp;
            	<input class="info-btn small-btn w100" type="button" onclick="exp();" value="导 出" />
            </td>
          </tr>
        </table>
     </form>
    </div>      
    
    <table class="mars info-list-02 mtop20">
		<thead><tr class="title">
			<td><input type="checkbox" id="theadCheck" /></td>
			<td>提款单号</td>
			<td>申请发起时间</td>
			<td>申请对象名称</td>
			<td>申请对象类型</td>
			<!-- 
			<td>手机号</td>
			<td>联系人</td>
			-->
			<td>结算项目</td>
			<td>结算金额</td>
			<td>平台补贴额</td>
			<td>用户实缴</td>
			<%-- <td>账户名称</td>
			<td>银行卡号</td>
			<td>开卡支行</td>
			<td>开户银行</td> --%>
			<td>关联单号</td>
			<td>结算</td>
			<td>操作</td>
		</tr></thead>
		<tbody>
		<c:forEach items="${resList }" var="row">
		<tr>
			<td>
				<input type="checkbox" name="applyIdList" value="${row.id}" 
						goalType="${row.goalType}" 
						easBillNumbers="${row.easBillNumbers}"
						miniRoleId="${row.miniRoleId}"
						miniRoleType="${row.miniRoleType}"
						applyNo="${row.applyNo}"/>
				<%-- <input type="hidden" name="batchParam" value="${row.goalType}_${row.id}_${row.miniRoleId}_${row.miniRoleType}_${row.applyNo}"/> --%>
			</td>
			<td>${row.applyNo}</td>
			<td>${row.applyTime}</td>
			<td>${row.roleName}</td>
			<td>
			<c:choose>
				<c:when test="${row.miniRoleType == 1}">平台</c:when>
				<c:when test="${row.miniRoleType == 2 or row.miniRoleType == 13}">物业</c:when>
				<c:when test="${row.miniRoleType == 3}">代理</c:when>
				<c:when test="${row.miniRoleType == 4}">财务</c:when>
				<c:when test="${row.miniRoleType == 5 or row.miniRoleType == 12}">店铺</c:when>
				<c:when test="${row.miniRoleType == 6}">师傅</c:when>
				<c:when test="${row.miniRoleType == 7}">推荐人</c:when>
				<c:when test="${row.miniRoleType == 15}">生活缴费运营</c:when>
				<%-- <c:when test="${row.miniRoleType == 13}">物业管理处</c:when> --%>
				<c:otherwise>未定义</c:otherwise>
			</c:choose>
			</td>
			<!-- 
			<td>${row.bPhone}</td>
			<td>${row.bConnectName}</td>
			 -->
			<td>
			<c:choose>
				<c:when test="${row.goalType == 1}">认证门牌</c:when>
				<c:when test="${row.goalType == 2}">维修收益</c:when>
				<c:when test="${row.goalType == 3}">物业宝佣金</c:when>
				<c:when test="${row.goalType == 4}">超市收益</c:when>
				<c:when test="${row.goalType == 5}">物业费实收</c:when>
				<c:when test="${row.goalType == 6}">停车费实收</c:when>
				<c:when test="${row.goalType == 7}">其他代收费用</c:when>
				<c:when test="${row.goalType == 8}">物业宝抵扣</c:when>
				<c:when test="${row.goalType == 9}">停车宝佣金</c:when>
				<c:when test="${row.goalType == 10}">停车宝抵扣</c:when>
				<c:when test="${row.goalType == 15}">物业费补贴</c:when>
				<c:when test="${row.goalType == 16}">供应商结算</c:when>
				<c:when test="${row.goalType == 17}">停车费补贴</c:when>
				<c:when test="${row.goalType == 18}">生活缴费代收</c:when>
				<c:otherwise>未定义</c:otherwise>
			</c:choose>
			</td>
			<td id="${row.id}_totalAmount" style="text-align: right;">
				<fmt:formatNumber value="${row.totalAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/>
			</td>
			<%-- <c:choose>
				<c:when test="${(row.miniRoleType eq 6) and (not empty row.dredgeAmountPtbt) and (not empty row.dredgeAmountUsrReal)}">
					<td style="text-align: right;">
						<fmt:formatNumber value="${row.dredgeAmountPtbt}" type="currency" pattern="0.00" maxFractionDigits="2"/>
					</td>
					<td style="text-align: right;">
						<fmt:formatNumber value="${row.dredgeAmountUsrReal}" type="currency" pattern="0.00" maxFractionDigits="2"/>
					</td>
				</c:when>
				<c:otherwise> --%>
					<td style="text-align: right;">
						<fmt:formatNumber value="${row.amountPtbt}" type="currency" pattern="0.00" maxFractionDigits="2"/>
					</td>
					<td style="text-align: right;">
						<fmt:formatNumber value="${row.amountUsrReal}" type="currency" pattern="0.00" maxFractionDigits="2"/>
					</td>
				<%-- </c:otherwise>
			</c:choose> --%>
			<%--
			<td>${row.accountName }</td>
			<td>${row.bBankNo}</td>
			<td>${row.bankBranch }</td>
			<td>${row.bBankName}</td>
			 --%>
			<td>${row.easBillNumbers}</td>
			<td>
			<c:choose>
				<c:when test="${row.tkStatus == 2}">申请中</c:when>
				<c:when test="${row.tkStatus == 3}">已转账</c:when>
				<c:otherwise>未定义</c:otherwise>
			</c:choose>
			</td>
			<td>
				<c:choose>
					<c:when test="${row.goalType == 5}">
					<a class="blue" href="<%=basePath%>/payBill/searchRevenue.html?roleId=${row.miniRoleId}&roleType=${row.miniRoleType}&projectType=${row.goalType}&rev_pcName=${row.roleName}&isPay=1&paymentWay=1&applyId=${row.id}">明细</a>
					</c:when>
					<c:when test="${row.goalType == 15}">
					<a class="blue" href="<%=basePath%>/payBill/searchRevenue.html?roleId=${row.miniRoleId}&roleType=${row.miniRoleType}&projectType=${row.goalType}&rev_pcName=${row.roleName}&isPay=1&paymentWay=1&applyId=${row.id}">明细</a>
					</c:when>
					<c:when test="${row.goalType == 7}">
					<a class="blue" href="<%=basePath%>/payBill/searchRevenue.html?roleId=${row.miniRoleId}&roleType=${row.miniRoleType}&projectType=${row.goalType}&rev_pcName=${row.roleName}&isPay=1&paymentWay=1&applyId=${row.id}">明细</a>
					</c:when>
					<c:when test="${row.goalType == 4}">
					<a class="blue" href="<%=basePath%>/revenueEbuy/ebuyList.html?roleId=${row.miniRoleId}&isPay=1&applyId=${row.id}">明细</a>
					</c:when>
					<c:when test="${row.goalType == 2}">
					<a class="blue" href="<%=basePath%>/revenueDredge/dredgeList.html?roleId=${row.miniRoleId}&roleType=${row.miniRoleType}&projectType=${row.goalType}&rev_pcName=${row.roleName}&isPay=1&paymentWay=1&applyId=${row.id}">明细</a>
					</c:when>
					<c:when test="${row.goalType == 3}">
					<a class="blue" href="<%=basePath%>/revenueFinance/financeList.html?roleId=${row.miniRoleId}&roleType=${row.miniRoleType}&projectType=${row.goalType}&rev_pcName=${row.roleName}&isPay=1&paymentWay=1&applyId=${row.id}">明细</a>
					</c:when>
					<c:when test="${row.goalType == 9}">
					<a class="blue" href="<%=basePath%>/revenueFinance/carFinanceList.html?roleId=${row.miniRoleId}&roleType=${row.miniRoleType}&projectType=${row.goalType}&rev_pcName=${row.roleName}&isPay=1&paymentWay=1&applyId=${row.id}">明细</a>
					</c:when>
					<c:when test="${row.goalType == 6 or row.goalType == 17}">
					<a class="blue" href="<%=basePath%>/revenueCar/carList.html?roleId=${row.miniRoleId}&roleType=${row.miniRoleType}&goalType=${row.goalType}&rev_pcName=${row.roleName}&isPay=1&paymentWay=1&applyId=${row.id}">明细</a>
					</c:when>
					<c:when test="${row.goalType == 8}">
					<a class="blue" href="<%=basePath%>/revenueFinanceDedu/financeDeduList.html?roleId=${row.miniRoleId}&roleType=${row.miniRoleType}&projectType=${row.goalType}&rev_pcName=${row.roleName}&isPay=1&paymentWay=1&applyId=${row.id}">明细</a>
					</c:when>
					<c:when test="${row.goalType == 10}">
					<a class="blue" href="<%=basePath%>/revenueCarDedu/carDeduList.html?roleId=${row.miniRoleId}&roleType=${row.miniRoleType}&projectType=${row.goalType}&rev_pcName=${row.roleName}&isPay=1&paymentWay=1&applyId=${row.id}">明细</a>
					</c:when>
					<c:when test="${row.goalType == 16}">
					<a class="blue" href="<%=basePath%>/ebuyProductSettle/revenueListDetail.html?revenueApplyId=${row.id}&visibleType=${row.visibleType}&revApplyFinanceId=${row.revApplyFinanceId}">明细</a>
					</c:when>
					<c:when test="${row.goalType == 18}">
					<a class="blue" href="<%=basePath%>/livingPay/qryLivingPayRevenueList.html?revenueApplyId=${row.id}">明细</a>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
				&nbsp;&nbsp;
				<c:choose>
					<c:when test="${row.tkStatus == 3}">已结算</c:when>
					<c:otherwise><a class="blue" onclick="doRevenueConfirm(${row.id},this, '${row.goalType}')" href="javascript:void(0)">结算</a></c:otherwise>
				</c:choose>
				&nbsp;&nbsp;
				<c:if test="${(empty row.easBillNumbers) and (row.tkStatus eq 2)}">
					<a class="blue" href="javascript:void(0);" onclick="pushBill2EAS(${row.id}, ${row.goalType}, ${row.miniRoleType})">生成单据</a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
	<script type="text/javascript" src="js/revenue/layer.js"></script>
	<script type="text/javascript" src="js/jquery.common.js"></script>
	<script type="text/javascript" src="js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	<script type="text/javascript" src="js/revenue/moment.js"></script>
	<c:import url="/common/page.jsp" />
	<br/>
	<input type="button" id="batchApply" class="info-btn save-set-prize-info-btn" value="批量结算"/>&nbsp;&nbsp;
	<input type="button" id="batchExport" class="info-btn save-set-prize-info-btn" value="批量导出明细"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tool.js"></script>
</div>
<script type="text/javascript">
(function($){
	function showGatherMoney(){
		var totalMoney = 0;
		$("[name=applyIdList]").each(function(index,data){
			if($(data).is(':checked')){
				totalMoney+=parseFloat($("#"+$(data).val()+"_totalAmount").html());
			}
		});
		$("#batchApply").val("批量结算("+totalMoney.toFixed(2)+"元)");
	}
	
	$(document).on('click','[id=theadCheck]',function (){
		var checked= $('[id=theadCheck]').is(':checked');
		$("[name=applyIdList]").prop("checked",checked);
		showGatherMoney();
	});
	
	$(document).on('click','[name=applyIdList]',function (){
		showGatherMoney();
	});
	
	$(document).on('click','[id=batchApply]',function (){
		var idList="", goalType="";
		$("[name=applyIdList]").each(function(index,data){
			if($(data).is(':checked')){
				idList+=$(data).val()+"|";
				goalType += $(data).attr("goalType")+"|";
			}
		});
		doRevenueConfirm(idList,null, goalType);
	});
	
	var isAlreadyClick = true;//防止多次点击批量导出按钮，造成服务器压力。目的是为了减缓用户请求操作。
	$(document).on('click','[id=batchExport]',function (){
		var batchParam = new Array();
		$("[name=applyIdList]:checked").each(function(){
			var goalType = $(this).attr("goalType");
			var applyId = $(this).val();
			var miniRoleId = $(this).attr("miniRoleId");
			var miniRoleType = $(this).attr("miniRoleType");
			var applyNo = $(this).attr("applyNo");
			var easBillNumbers = $.trim($(this).attr("easBillNumbers"));
			
			batchParam.push({"goalType":goalType, 
							 "applyId":applyId, 
							 "miniRoleId":miniRoleId, 
							 "miniRoleType":miniRoleType, 
							 "applyNo":applyNo, 
							 "easBillNumbers":easBillNumbers});
		});
		if(batchParam.length==0) {
			alert("请选择需要导出的内容！");
			return false;
		}
		if(!isAlreadyClick) {
			if(confirm("文件已存在，是否重新下载")){
				isAlreadyClick = true;
			} else {
				isAlreadyClick = false;
				return false;
			}
		}
		if(isAlreadyClick) {//如果已经导出excel，则不进行重复操作
			isAlreadyClick = false;
			location.href = "<%=basePath%>/revenueBatchExport/exportExcel.html?batchParam="+JSON.stringify(batchParam);
		}
	});
	
	//导出一次后，只要点击checkbox就把isAlreadyClick置为true，用户可以继续导出操作
	$(document).on('click','[name=applyIdList]',function(){
		isAlreadyClick = true;
	});
})(jQuery);

function freshCurrPage(){
	$("#searchForm").submit();//刷新页面
}

function doRevenueConfirm(dataId,tdTmp,goalType){
	//询问框
	layer.confirm('确认结算？', {
	    btn: ['确认','取消'] //按钮
	},function(){//确认
		layer.close();
		$.ajax({
			type:"post",
			url:"revenueAmountJson/confirmRevApply.json",
			data:{'applyId': dataId, "goalType":goalType},
			dataType:"TEXT",
			beforeSend:function(data){
			},
			success:function(data){
				try {
					data = eval(data);
				} catch (e) {
					data = eval("("+data+")");
				}
				var msg = '操作成功';
				try {
					if (data.status == '0000') {
						//layer.msg('删除成功', {icon: 1});
					} else {
						msg = data.message;
						//layer.msg(data.message, {icon: 6});
					}
				} catch (e) {
					msg = data.message;
					//layer.msg(data.message, {icon: 6});
				}
				layer.alert(msg, {closeBtn: 0}, function(){
					freshCurrPage();
				});
				return;
			}
		});
		
	}, function(){});
};

// 当结算项目为所有补贴时，申请对象类型默认为全部
function changeGoalType(){
	var goalType = $.trim($("#goalType").val());
	if(goalType==100){
		$("#roleType").val("");
	}
}


function pushBill2EAS(applyId, goalType, miniRoleType){
	$.cxhd.loading();
	$.post("${pageContext.request.contextPath}/revenueSettle/pushBill2EAS.html", {"applyId":applyId, "goalType":goalType, "miniRoleType":miniRoleType}, function(data){
		$.cxhd.hide();
		if(data.dataValue.code=="0000"){
			alert("操作成功");
		} else {
			alert(data.dataValue.message);
		}
		location=location;
	});
}
</script>
</body>
</html>
