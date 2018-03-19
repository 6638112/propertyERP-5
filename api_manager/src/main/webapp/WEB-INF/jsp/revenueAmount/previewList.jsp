<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page import="com.cnfantasia.server.ms.revenue.constant.RevenueDict"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>//" />
	<title>物业管理-收益总额概要列表</title>
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
	<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css?v=1"/>
<style type="text/css">
.text-right{text-align: right;}
</style>
</head>

<body>
<div class="info">
    <h2>收益总额概要列表</h2>
    <c:if test="${role == 2}">
    <form id="searchForm"  action="<%=basePath%>${formUrl}" method="post"></form>
    </c:if>
    <c:if test="${role != 2}">
    <div class="bs-example bgebeb">
    	<form id="searchForm"  action="<%=basePath%>${formUrl}" method="post">
    		
    		<table class="info-list" border="0">
	          <tr>
	            <td><div class="list-name"><c:choose><c:when test="${role == 5}">店铺名称</c:when><c:otherwise>对象名称：</c:otherwise></c:choose></div></td>
	            <td><input type="text" class="input_text w200 pp" name="searchName" value="${param.searchName}"/></td>
	            <td colspan="6"  class="t_center"><input class="input-btn w200" type="submit" value="搜索"/></td>
	          </tr>
	        </table>
        </form>
    </div>
    </c:if>

	<table class="mars info-list-02 mtop20">
		<thead><tr class="title">
			<td>对象类别</td>
			<td>对象名称</td>
			<td>收益项目</td>
			<td>已结算金额</td>
			<td>未结算金额</td>
			<td>在途资金金额</td>
			<td>总金额</td>
			<td>操作</td>
			<c:if test="${role == 4 || role == 1}">
			<td>历史结算</td>
			</c:if>
		</tr></thead>
		<tbody>
		<c:forEach items="${resList }" var="row">
			<c:if test="${fn:length(row.amountInfoList)==0}">
			<tr>
				<td>
					<c:choose>
						<c:when test="${row.roleType==1}">系统管理员</c:when>
						<c:when test="${row.roleType==2}">物业公司</c:when>
						<c:when test="${row.roleType==3}">代理商</c:when>
						<c:when test="${row.roleType==4}">财务人员</c:when>
						<c:when test="${row.roleType==5}">楼下店铺</c:when>
						<c:when test="${row.roleType==6}">维修师傅</c:when>
						<c:when test="${row.roleType==7}">推荐人</c:when>
						<c:when test="${row.roleType==13}">物业管理处</c:when>
						<c:otherwise>未定义</c:otherwise>
					</c:choose>
				</td>
				<td>${row.roleName}</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			</c:if>
			<c:if test="${fn:length(row.amountInfoList)>0 }">
			<c:forEach items="${row.amountInfoList }" var="prj" varStatus="prjStatus">
				<tr>
					<c:if test="${prjStatus.first}">
						<td rowspan="${fn:length(row.amountInfoList)}">
							<c:choose>
								<c:when test="${row.roleType==1}">系统管理员</c:when>
								<c:when test="${row.roleType==2}">物业公司</c:when>
								<c:when test="${row.roleType==3}">代理商</c:when>
								<c:when test="${row.roleType==4}">财务人员</c:when>
								<c:when test="${row.roleType==5}">楼下店铺</c:when>
								<c:when test="${row.roleType==6}">维修师傅</c:when>
								<c:when test="${row.roleType==7}">推荐人</c:when>
								<c:when test="${row.roleType==13}">物业管理处</c:when>
								<c:otherwise>未定义</c:otherwise>
							</c:choose>
						</td>
						<td rowspan="${fn:length(row.amountInfoList)}">${row.roleName}</td>
					</c:if>
					<td>
						<c:choose>
							<c:when test="${prj.projectType==7}">其他代收费用</c:when>
							<c:when test="${prj.projectType==15}">物业费补贴</c:when>
							<c:when test="${prj.projectType==5}">物业费实收</c:when>
							<c:when test="${prj.projectType==1}">认证门牌</c:when>
							<c:when test="${prj.projectType==2}">维修师傅</c:when>
							<c:when test="${prj.projectType==3}">物业宝佣金</c:when>
							<c:when test="${prj.projectType==8}">物业宝抵扣</c:when>
							<c:when test="${prj.projectType==9}">停车宝佣金</c:when>
							<c:when test="${prj.projectType==10}">停车宝抵扣</c:when>
							<c:when test="${prj.projectType==4}">超市收益</c:when>
							<c:when test="${prj.projectType==6}">停车费实收</c:when>
							<c:when test="${prj.projectType==17}">停车费平台补贴</c:when>
							<c:otherwise>未知</c:otherwise>
						</c:choose>
					</td>
					<c:choose>
						<c:when test="${prj.projectType==5 || prj.projectType==15 || prj.projectType==7}">
						<td><div class="text-right"><a class="blue" href="javascript:toDetailList('${row.roleId}','${row.roleType}','${prj.projectType}','<c:if test="${row.roleType==2}">${row.roleName}</c:if>','3');"><fmt:formatNumber value="${prj.setedAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toDetailList('${row.roleId}','${row.roleType}','${prj.projectType}','<c:if test="${row.roleType==2}">${row.roleName}</c:if>','1');"><fmt:formatNumber value="${prj.toSetAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toDetailList('${row.roleId}','${row.roleType}','${prj.projectType}','<c:if test="${row.roleType==2}">${row.roleName}</c:if>','2');"><fmt:formatNumber value="${prj.settingAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						</c:when>
						<c:when test="${prj.projectType==4}">
						<td><div class="text-right"><a class="blue" href="javascript:toEbuyDetailList('${row.roleId}','3');"><fmt:formatNumber value="${prj.setedAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toEbuyDetailList('${row.roleId}','1');"><fmt:formatNumber value="${prj.toSetAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toEbuyDetailList('${row.roleId}','2');"><fmt:formatNumber value="${prj.settingAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						</c:when>
						<c:when test="${prj.projectType==2}">
						<td><div class="text-right"><a class="blue" href="javascript:toDredgeDetailList('${row.roleId}','3');"><fmt:formatNumber value="${prj.setedAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toDredgeDetailList('${row.roleId}','1');"><fmt:formatNumber value="${prj.toSetAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toDredgeDetailList('${row.roleId}','2');"><fmt:formatNumber value="${prj.settingAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						</c:when>
						<c:when test="${prj.projectType==3}">
						<td><div class="text-right"><a class="blue" href="javascript:toFinanceDetailList('${row.roleId}','3', ${prj.projectType});"><fmt:formatNumber value="${prj.setedAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toFinanceDetailList('${row.roleId}','1', ${prj.projectType});"><fmt:formatNumber value="${prj.toSetAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toFinanceDetailList('${row.roleId}','2', ${prj.projectType});"><fmt:formatNumber value="${prj.settingAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						</c:when>
						<c:when test="${prj.projectType==9}">
						<td><div class="text-right"><a class="blue" href="javascript:toCarFinanceDetailList('${row.roleId}','3',${prj.projectType});"><fmt:formatNumber value="${prj.setedAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toCarFinanceDetailList('${row.roleId}','1', ${prj.projectType});"><fmt:formatNumber value="${prj.toSetAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toCarFinanceDetailList('${row.roleId}','2',${prj.projectType});"><fmt:formatNumber value="${prj.settingAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						</c:when>
						<c:when test="${(prj.projectType eq 6) or (prj.projectType eq 17)}">
						<td><div class="text-right"><a class="blue" href="javascript:toCarDetailList('${row.roleId}','3','${prj.projectType}');"><fmt:formatNumber value="${prj.setedAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toCarDetailList('${row.roleId}','1','${prj.projectType}');"><fmt:formatNumber value="${prj.toSetAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toCarDetailList('${row.roleId}','2','${prj.projectType}');"><fmt:formatNumber value="${prj.settingAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						</c:when>
						<c:when test="${prj.projectType==8}">
						<td><div class="text-right"><a class="blue" href="javascript:toFinanceDeduList('${row.roleId}','3');"><fmt:formatNumber value="${prj.setedAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toFinanceDeduList('${row.roleId}','1');"><fmt:formatNumber value="${prj.toSetAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toFinanceDeduList('${row.roleId}','2');"><fmt:formatNumber value="${prj.settingAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						</c:when>
						<c:when test="${prj.projectType==10}">
						<td><div class="text-right"><a class="blue" href="javascript:toCarDeduList('${row.roleId}','3');"><fmt:formatNumber value="${prj.setedAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toCarDeduList('${row.roleId}','1');"><fmt:formatNumber value="${prj.toSetAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						<td><div class="text-right"><a class="blue" href="javascript:toCarDeduList('${row.roleId}','2');"><fmt:formatNumber value="${prj.settingAmount+0.000001}" pattern="#,##0.00"/></a></div></td>
						</c:when>
						<c:otherwise>
						<td><div class="text-right"><fmt:formatNumber value="${prj.setedAmount+0.000001}" pattern="#,##0.00"/></div></td>
						<td><div class="text-right"><fmt:formatNumber value="${prj.toSetAmount+0.000001}" pattern="#,##0.00"/></div></td>
						<td><div class="text-right"><fmt:formatNumber value="${prj.settingAmount+0.000001}" pattern="#,##0.00"/></div></td>
						</c:otherwise>
					</c:choose>
					<td><div class="text-right">
						<c:choose>
						<c:when test="${prj.projectType==4}">
						<a class="blue" href="javascript:toEbuyDetailList('${row.roleId}', '');"><fmt:formatNumber value="${prj.totalAmount+0.000001}" pattern="#,##0.00"/></a>
						</c:when>
						<c:when test="${prj.projectType==2}">
						<a class="blue" href="javascript:toDredgeDetailList('${row.roleId}', '');"><fmt:formatNumber value="${prj.totalAmount+0.000001}" pattern="#,##0.00"/></a>
						</c:when>
						<c:when test="${prj.projectType==3}">
						<a class="blue" href="javascript:toFinanceDetailList('${row.roleId}', '');"><fmt:formatNumber value="${prj.totalAmount+0.000001}" pattern="#,##0.00"/></a>
						</c:when>
						<c:when test="${prj.projectType==9}">
						<a class="blue" href="javascript:toCarFinanceDetailList('${row.roleId}', '');"><fmt:formatNumber value="${prj.totalAmount+0.000001}" pattern="#,##0.00"/></a>
						</c:when>
						<c:when test="${prj.projectType==5||prj.projectType==15 || prj.projectType==7}">
						<a class="blue" href="javascript:toDetailList('${row.roleId}','${row.roleType}','${prj.projectType}','<c:if test="${row.roleType==2}">${row.roleName}</c:if>','');"><fmt:formatNumber value="${prj.totalAmount+0.000001}" pattern="#,##0.00"/></a>
						</c:when>
						<c:when test="${prj.projectType==6||prj.projectType==17}">
						<a class="blue" href="javascript:toCarDetailList('${row.roleId}', '','${prj.projectType}');"><fmt:formatNumber value="${prj.totalAmount+0.000001}" pattern="#,##0.00"/></a>
						</c:when>
						<c:when test="${prj.projectType==8}">
						<a class="blue" href="javascript:toFinanceDeduList('${row.roleId}', '');"><fmt:formatNumber value="${prj.totalAmount+0.000001}" pattern="#,##0.00"/></a>
						</c:when>
						<c:when test="${prj.projectType==10}">
						<a class="blue" href="javascript:toCarDeduList('${row.roleId}', '');"><fmt:formatNumber value="${prj.totalAmount+0.000001}" pattern="#,##0.00"/></a>
						</c:when>
						<c:otherwise><fmt:formatNumber value="${prj.totalAmount+0.000001}" pattern="#,##0.00"/></c:otherwise>
						</c:choose>
					</div></td>
					<td>
					<%-- role即roleType，1和4是管理员和财务 --%>
					<c:if test="${prj.toSetAmount>=0.01 && role!=1 && role!=4 
					&& prj.projectType!=15 && prj.projectType!=2 && prj.projectType!=3 
					&& prj.projectType!=9 && prj.projectType!=8 && prj.projectType!=10 && prj.projectType!=17
					 && !(prj.projectType==4&&(role==2||role==13||role ==3))}">  <%-- projectType==4超市收益，物业、管理处、代理都不能提现 --%>
						<a class="blue" onclick="applyMoney('${row.roleId}','${row.roleType}','${prj.projectType}');">申请提款</a>
					</c:if>
					</td>
					<c:if test="${role == 4 || role == 1}">
					<c:if test="${prjStatus.first}">
					<td rowspan="${fn:length(row.amountInfoList)}">
						<c:if test="${prj.projectType==5 || prj.projectType==15}"><!-- prj.toSetAmount>0 && role==4 &&  -->
						<a class="blue" onclick="markHistoryClick('${row.roleId}','${row.roleName}');">标记物业账单</a>
						</c:if>
					</td>
					</c:if>
					</c:if>
				</tr>
			</c:forEach>
			</c:if>
		</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
	<script type="text/javascript" src="js/revenue/layer.js"></script>
	<script type="text/javascript" src="js/jquery.common.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
</div>

<script type="text/javascript">
// 物业、代理，如果银行卡信息为空，则一天提醒一次
<c:if test="${not empty bank_msg}">
	$(function(){
		if($.cookie('bank_alert')==undefined){
			$.cookie('bank_alert', '1', { expires: 1, path: '/'});// 一天提醒一次
			if(confirm("${bank_msg}")){
				location.href="${pageContext.request.contextPath}${bank_url}";
			}
		}
	});
</c:if>

function freshCurrPage(){
	$("#searchForm").submit();//刷新页面
}

function applyMoney(roleId,roleType,projectType){
	// 物业、代理==>检查银行卡信息是否为空
	if(roleType=="<%=RevenueDict.MiniRoleType.PropertyCompany%>" || roleType=="<%=RevenueDict.MiniRoleType.PropertyAgent%>"){
		$.post("${pageContext.request.contextPath}/revenueAmount/checkBankInfo.html", {"miniRoleId":roleId, "miniRoleType":roleType}, function(data){
			if(data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>'){
				applyConfirm(roleId,roleType,projectType);
			} else {
				alert(data.message);
			}
		});
	} else {
		applyConfirm(roleId,roleType,projectType);
	}
}

function applyConfirm(roleId,roleType,projectType){
	layer.open({
		type: 2,  
	    shadeClose: true,  //点击遮罩关闭层
	    title: false,  
	    closeBtn: [0, true],
	    shade: [0.5, '#000'],
	    border: [0],  
	    offset: ['',''], // top、left 
	    area: ['500px', '215px'], //宽高
	    content: 'revenueAmount/toApplyRevenue.html?roleId='+roleId+'&roleType='+roleType+'&projectType='+projectType+'', //iframe的url
	    end:function(){
	    	window.setTimeout(freshCurrPage,700);
	    }
	});
}

function markHistoryClick(companyId,companyName){
	layer.open({
		type: 2,  
	    shadeClose: true,  //点击遮罩关闭层
	    title: false,  
	    closeBtn: [0, true],
	    shade: [0.5, '#000'],
	    border: [0],  
	    offset: ['',''], // top、left 
	    area: ['550px', '280px'], //宽高
	    content: '<%=basePath%>/revenueAmount/toMarkPage.html?companyId='+companyId+'&companyName='+companyName,
	    end:function(){
	    	window.setTimeout(freshCurrPage,700);
	    }
	});
}


function toDetailList(roleId,roleType,projectType,pcName,tkStatus_wy){
	window.location.href='<%=basePath%>/payBill/searchRevenue.html?roleId='+roleId+'&roleType='+roleType+'&projectType='+projectType+'&rev_pcName='+pcName+'&isPay=1&paymentWay=1'+'&tkStatus_wy='+tkStatus_wy;
}

function toEbuyDetailList(roleId, tkStatus){
	window.location.href='<%=basePath%>/revenueEbuy/ebuyList.html?roleId='+roleId+'&isPay=1' + '&tkStatus=' + tkStatus;
}

function toDredgeDetailList(roleId, tkStatus){
	window.location.href='<%=basePath%>/revenueDredge/dredgeList.html?roleId='+roleId+'&isPay=1' + '&tkStatus=' + tkStatus;
}

function toFinanceDetailList(roleId, tkStatus, projectType){
	window.location.href='<%=basePath%>/revenueFinance/financeList.html?roleId='+roleId+'&isPay=1' + '&tkStatus=' + tkStatus + '&projectType=' + projectType ;
}

function toCarFinanceDetailList(roleId, tkStatus, projectType){
	window.location.href='<%=basePath%>/revenueFinance/carFinanceList.html?roleId='+roleId+'&isPay=1' + '&tkStatus=' + tkStatus + '&projectType=' + projectType ;
}

function toFinanceDeduList(roleId, tkStatus){
	window.location.href='<%=basePath%>/revenueFinanceDedu/financeDeduList.html?roleId='+roleId+'&isPay=1' + '&tkStatus=' + tkStatus;
}
function toCarDeduList(roleId, tkStatus){
	window.location.href='<%=basePath%>/revenueCarDedu/carDeduList.html?roleId='+roleId+'&isPay=1' + '&tkStatus=' + tkStatus;
}

function toCarDetailList(roleId, tkStatus, goalType){
	window.location.href='<%=basePath%>/revenueCar/carList.html?roleId='+roleId+'&isPay=1' + '&tkStatus=' + tkStatus+'&goalType='+goalType;
}
</script>

</body>
</html>
