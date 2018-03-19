<%@page import="com.cnfantasia.server.api.payment.constant.EbuyDict"%>
<%@page import="com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager" %>
<%@page import="com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", -10);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>活动管理</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.simple-dtpicker.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/clearbox.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-ui-1.11.4.custom/jquery-ui.min.css">
</head>

<body>
<div class="info">
	<h2>活动列表</h2>
	<div class="bs-example bgebeb">
		<form id="searchForm" name="form_main" action="${pageContext.request.contextPath}/flashDealActivityCfg/flashDealActivityList.html" method="post">
			<table class="info-list" border="0">
				<tr>
					<td><div align="right">商品ID：</div></td>
					<td><input name="tEbuyProductFId" value="${param.tEbuyProductFId}" type="text" class="input_text w200 pp"></td>
					<td><div class="list-name">结束时间：</div></td>
					<td><input type="text" class="input_text icon_dt pp" id="date02" name="activityEndTime" title="请选择结束时间" value="${param.activityEndTime}"></td>
					<td><div class="list-name">状态：</div></td>
					<td>
						<select id="status" name="status" class="select_normal">
							<option value="">全部</option>
							<option value="0" <c:if test="${(not empty param.status) and (param.status eq 0)}">selected</c:if>>已结束</option>
							<option value="1" <c:if test="${param.status eq 1}">selected</c:if>>进行中</option>
							<option value="2" <c:if test="${param.status eq 2}">selected</c:if>>未开始</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><div align="right">商品名称：</div></td>
					<td><input name="productName" value="${param.productName}" type="text" class="input_text w200 pp"></td>
					<td><div align="right">自提点：</div></td>
					<td><input name="merchantName" value="${param.merchantName}"  type="text" class="input_text w200 pp"></td>
					<%--<td><div class="list-name">排序方式：</div></td>
					<td>
						<select id="status" name="status02" class="select_normal">
							<option value="">全部</option>
							<option value="0" <c:if test="${(not empty param.status) and (param.status eq 0)}">selected</c:if>>已上架</option>
							<option value="1" <c:if test="${param.status eq 1}">selected</c:if>>已下架</option>
							<option value="2" <c:if test="${param.status eq 2}">selected</c:if>>草稿</option>
							<option value="3" <c:if test="${param.status eq 3}">selected</c:if>>待审核</option>
							<option value="4" <c:if test="${param.status eq 4}">selected</c:if>>审核不通过</option>
						</select>
					</td>--%>
				</tr>
				<tr>
					<td colspan="6" class="t_center"><input class="input-btn w200" type="submit" value="搜索"></td>
				</tr>
			</table>
		</form>
	</div>
	<display:table name="resList" id="row" class="info-list-02 mtop40" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
		<display:column title="活动名称" property="title"/>
		<display:column title="商品ID" property="tEbuyProductFId"/>
		<display:column title="商品名称" property="productName"/>
		<display:column title="自提点" property="merchantName"/>
		<display:column title="份数" property="prizeCount"/>
		<display:column title="开始时间" property="activityStartTime"/>
		<display:column title="结束时间" property="activityEndTime"/>
		<display:column title="状态">
			<c:choose>
				<c:when test="${row.startStatus eq 1}">
					未开始
				</c:when>
				<c:when test="${row.startStatus eq 0 && row.endStatus eq 0}">
					进行中
				</c:when>
				<c:otherwise>已结束</c:otherwise>
			</c:choose>
		</display:column>
		<display:column title="添加人" property="addMan" />
		<display:column title="修改人" property="updateMan" />
		<display:column title="操作"  >
			<a class="blue editShopInfo" href="../flashDealActivityCfg/flashDealActivityCfgDetail.html?flashDealActivityId=${row.id}&pageType=detail">查看</a>
			<c:if test="${row.startStatus eq 1}">
				<a class="blue editShopInfo" href="../flashDealActivityCfg/flashDealActivityCfgDetail.html?flashDealActivityId=${row.id}&pageType=edit">编辑</a>
			</c:if>
		</display:column>
	</display:table>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js?v20150506"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/clearbox.js?v20150215"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
<script type="text/javascript">
	function deleteApp(id){
		steveConfirm("你确认要删除此商品吗?", function(){
			$.post("${pageContext.request.contextPath}/ebuyProduct/deleteProduct.html", {
				productId : id
			}, function(data) {
				if(data.errcode == null) {
					$("#searchForm").submit();
				} else if (data.message) {
					$.messager.alert('操作提示',data.message,data.status);
				}
			});
		});
	}
</script>
</html>
