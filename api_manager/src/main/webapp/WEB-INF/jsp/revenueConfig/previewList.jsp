<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>//" />
	<title>物业管理-配置概要列表</title>
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
	<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css?v=1"/>
</head>

<body>
<div class="info">
    <h2>配置概要列表</h2>
    <div class="bs-example bgebeb">
    	<form id="searchForm"  action="<%=basePath%>/revenueConfig/previewList.html" method="post">
	        <table class="info-list" border="0">
	          <tr>
	            <td><div class="list-name">物业公司名称：</div></td>
	            <td><input type="text" class="input_text w200 pp" name="companyName" value="${param.companyName}"/></td>
	            <td colspan="6"  class="t_center"><input class="input-btn w200" type="submit" value="搜索"/></td>
	          </tr>
	        </table>
        </form>
    </div>

	<table class="mars info-list-02 mtop20">
		<thead><tr class="title">
			<td>物业公司名称</td>
			<td>代理名称</td>
			<td>收益项目</td>
			<td>状态</td>
			<td>规则生效时间（开始）</td>
			<td>操作</td>
		</tr></thead>
		<tbody>
		<c:forEach items="${resList }" var="row">
			<c:if test="${fn:length(row.projectInfoList)==0}">
			<tr ondblclick="toConfigList('${row.companyId}','${row.companyName}','${prj.projectType}');">
				<td>${row.companyName}</td>
				<td>${row.agentName}</td>
				<td></td>
				<td></td>
				<td></td>
				<td><a class="blue"  onclick="toConfigList('${row.companyId}','${row.companyName}','${prj.projectType}');" href="javascript:void(0);">配置规则</a></td>
			</tr>
			</c:if>
			<c:if test="${fn:length(row.projectInfoList)>0 }">
			<c:forEach items="${row.projectInfoList }" var="prj" varStatus="prjStatus">
				<tr ondblclick="toConfigList('${row.companyId}','${row.companyName}','${prj.projectType}');">
					<c:if test="${prjStatus.first}">
						<td rowspan="${fn:length(row.projectInfoList)}">${row.companyName}</td>
						<td rowspan="${fn:length(row.projectInfoList)}">${row.agentName}</td>
					</c:if>
					<td>
						<c:choose>
							<c:when test="${prj.projectType==7}">其他代收费用</c:when>
							<c:when test="${prj.projectType==15}">物业费补贴</c:when>
							<c:when test="${prj.projectType==5}">物业费实收</c:when>
							<c:when test="${prj.projectType==1}">认证门牌</c:when>
							<c:when test="${prj.projectType==2}">服务类订单</c:when>
							<c:when test="${prj.projectType==3}">物业宝佣金</c:when>
							<c:when test="${prj.projectType==9}">停车宝佣金</c:when>
							<c:when test="${prj.projectType==4}">超市收益</c:when>
							<c:otherwise>未知</c:otherwise>
						</c:choose>
					</td>
					<td>已配置(${prj.configCount}项)</td>
					<td>${fn:substring(prj.startTime, 0, 10)}</td>
					<c:if test="${prjStatus.first}">
						<td rowspan="${fn:length(row.projectInfoList)}"><a class="blue"   href="javascript:toConfigList('${row.companyId}','${row.companyName}','');">配置规则</a></td>
						<!-- onclick="toConfigList('${row.companyId}','${row.companyName}','');" -->
					</c:if>
				</tr>
			</c:forEach>
			</c:if>
		</c:forEach>
		</tbody>
		<!-- <tr>
			<td rowspan="4">深圳先进物业有限公司</td>
			<td rowspan="4">丰明科技</td>
			
			<td>认证门牌</td>
			<td>已配置</td>
			<td>2015-10-22</td>
			<td><a class="blue" onclick="" href="javascript:void(0)">查看详情</a></td>
		</tr>
		<tr>
			<td>服务类订单</td>
			<td>未配置</td>
			<td>-</td>
			<td><a class="blue" onclick="" href="javascript:void(0)">查看详情</a></td>
		</tr> -->
	</table>
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
	<script type="text/javascript" src="js/revenue/layer.js"></script>
	<script type="text/javascript" src="js/jquery.common.js"></script>
	<script type="text/javascript" src="js/revenue/revenueConfig.js?v=2"></script>
	<c:import url="/common/page.jsp" />
</div>

<script type="text/javascript">
function toConfigList(companyId,companyName,projectType){
	layer.open({
		type: 2,  
	    shadeClose: true,  //点击遮罩关闭层
	    title: false,  
	    closeBtn: [0, true],
	    shade: [0.5, '#000'],
	    border: [0],  
	    offset: ['25px',''], // top、left 
	    area: ['95%', '85%'], //宽高
	    content: 'revenueConfig/configList.html?companyId='+companyId+'&companyName='+companyName+'&projectType='+projectType+'' //iframe的url
	});
}
</script>

</body>
</html>
