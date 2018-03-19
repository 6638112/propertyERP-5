<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报修管理-报修配置</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>

<body>
<div class="info">
    <h2>业主报修配置 <span class="f12 red"></span></h2>
	<div class="bs-example bgebeb">
		<form action="<%=basePath%>/propertyRepair/listRepairConfig.html" method="post">
			<table class="info-list" border="0">
				<tr>
					<td><div class="list-name">管理处名称：</div></td>
					<td><input type="text" name="pmName" value="${param.pmName}" class="input_text w120 pp"></td>
					<td><div class="list-name">小区：</div></td>
					<td>
						<select id="groupBuiliding" name="gbName" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
							<option value="">选择小区</option>
						</select>
					</td>
					<td><div class="list-name">状态：</div></td>
					<td>
						<select class="select_normal" name="openStatus">
							<option value="" <c:if test="${empty param.openStatus}"> selected="selected"</c:if>>全部</option>
							<option value="1" <c:if test="${param.openStatus==1 }"> selected="selected"</c:if>>已开通</option>
							<option value="0" <c:if test="${not empty param.openStatus and param.openStatus==0 }"> selected="selected"</c:if>>未开通</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="6" class="t_center"><input class="input-btn w200" type="submit" value="搜索"></td>
				</tr>
			</table>
		</form>
	</div>
    <div>
    <display:table name="resList" id="row" class="info-list-02" cellspacing="1" cellpadding="0" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
				<display:column title="管理处名称" property="pmName" />
				<display:column title="小区" property="gbName" />
				<%-- <display:column title="支持门牌数" property="supportCount" /> 商务部说暂不开放 --%>
				<display:column title="状态" >
					<c:choose>	
						<c:when test="${row.openStatus }">
							<span class="blue alive">已开通</span>
						</c:when>
						<c:otherwise>
							<span class="grey asleep">未开通</span>
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="操作">
					<c:choose>	
						<c:when test="${row.openStatus}">
							<div align="left"><a class="blue closeRepairBtn" href="../propertyRepair/closeRepairService.html?gbId=${row.gbId}">关闭业主报修</a></div>
						</c:when>
						<c:otherwise>
							<div align="left"><a class="blue openRepair" href="../propertyRepair/openRepairService.html?gbId=${row.gbId}">开通业主报修</a></div>
						</c:otherwise>
					</c:choose>
				</display:column>
	</display:table>
    </div>
    
    <table class="info-list-02 mtop40" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td><strong>温馨提示：</strong><span>如需开通，请点击“<strong>开通业主报修</strong>”功能。</td>
        </tr>
        <tr>
          <td>开通业主报修功能后，业主便可在“解放区”APP上提交报修工单。</td>
        </tr>
    </table>
    <h2 class="mtop20">报修管理常规流程：</h3>
    <table class="info-list-02" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td><strong>第 <span class="step-num">1</span> 步：</strong> 处理报修的人员管理，请点击 <input class="input-btn mtop3 manageRepair" type="button" value="维修工人管理" onclick="window.location.href='../propertyRepair/listRepairer.html'"></td>
        </tr>
        <tr>
          <td><strong>第 <span class="step-num">2</span> 步：</strong> 报修单管理，查看用户在APP上提交的报修单，分配报修单到处理人 <input class="input-btn mtop3 checkRepair" type="button" value="工单管理" onclick="window.location.href='../propertyRepair/listRepair.html'"></td>
        </tr>
    </table>
	
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript" >
	$(function(){
		var gbName = '${param.gbName}';
		$.ajax({
			url: '<%=basePath%>/groupBuilding/getGroupBuildings.json',
			dataType: 'json',
			success: function (data) {
				var list = JSON.parse(data);
				var strHtml = "<option value=''>选择小区</option>";
				$.each(list, function (i, item) {
					var str = "";
					if(gbName == item.name) {
						str = "<option value='" + item.name + "' selected='selected'>" + item.name + "</option>";
					} else {
						str = "<option value='" + item.name + "'>" + item.name + "</option>";
					}
					strHtml += str;
				});
				$("#groupBuiliding").html(strHtml);
				$('.select2_class').select2();
			}
		});
	});
</script>
</html>
