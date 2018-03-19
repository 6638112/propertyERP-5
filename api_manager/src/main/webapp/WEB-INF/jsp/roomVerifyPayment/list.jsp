<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<title>门牌验证缴费情况</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/select2.css">
		<style>
			.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
			.w180{width: 170px;}
		</style>
	</head>
	<body>
		<div class="info">
		    <h2 class="mtop20">门牌验证缴费情况查询表</h2> 
		    <div class="bs-example bgebeb">
		    	<form id="doorListForm" method="post" action="${pageContext.request.contextPath}/door/doorVerifyAndPaymentIndex.html">
			        <table class="info-list" border="0">
			          <tr>
				            <td><div class="list-name">所在省：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="province" value="${param.province}"></td>
				            <td><div class="list-name">所在市：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="city" value="${param.city}"></td>
				            <td><div class="list-name">代理商：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="cpCompanyName" value="${param.cpCompanyName}"></td>
				            <td><div class="list-name">物业公司：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="pcName" value="${param.pcName}"></td>
				            <td><div class="list-name">小区名称：</div></td>
						  	<td>
							  <select id="groupBuiliding" name="gbName" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
								  <option value="">选择小区</option>
							  </select>
						  	</td>
			          </tr>
			          <tr>
				            <td><div class="list-name">楼栋号：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="buildingName" value="${param.buildingName}"></td>
				            <td><div class="list-name">单元：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="unit" value="${param.unit}"></td>
				            <td><div class="list-name">房间号：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="roomNum" value="${param.roomNum}"></td>
				            <td><div class="list-name">注册状态：</div></td>
				            <td>
				            	<select name="registerState" class="select_normal">
				                    <option value="">全部</option>
				                    <option value="1" <c:if test="${(not empty param.registerState) and (param.registerState eq 1)}">selected</c:if>>已注册</option>
				                    <option value="0" <c:if test="${(not empty param.registerState) and (param.registerState eq 0)}">selected</c:if>>未注册</option>
			                    </select>
				            </td>
				            <td><div class="list-name">认证状态：</div></td>
				            <td>
				            	<select name="verifyState" class="select_normal">
				                    <option value="">全部</option>
				                    <option value="1" <c:if test="${(not empty param.verifyState) and (param.verifyState eq 1)}">selected</c:if>>已认证</option>
				                    <option value="0" <c:if test="${(not empty param.verifyState) and (param.verifyState eq 0)}">selected</c:if>>未认证</option>
			                    </select>
				            </td>
			          </tr>
			          <tr>
			                <td><div class="list-name">缴费状态：</div></td>
				            <td>
				            	<select name="payState" class="select_normal">
				                    <option value="">全部</option>
				                    <option value="1" <c:if test="${(not empty param.payState) and (param.payState eq 1)}">selected</c:if>>已缴费</option>
				                    <option value="0" <c:if test="${(not empty param.payState) and (param.payState eq 0)}">selected</c:if>>未缴费</option>
			                    </select>
				            </td>
				            <td><div class="list-name">认证时间：</div></td>
				            <td colspan="3">
				            	<input type="text" name="verifyStartTime" title="请选择起始时间" value="${param.verifyStartTime}" placeholder="请选择起始时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            	至 
				            	<input type="text" name="verifyEndTime" title="请选择结束时间" value="${param.verifyEndTime}" placeholder="请选择结束时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            </td>
				            <td><div class="list-name">缴费时间：</div></td>
				            <td colspan="3">
				            	<input type="text" name="payStartTime" title="请选择起始时间" value="${param.payStartTime}" placeholder="请选择起始时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            	至 
				            	<input type="text" name="payEndTime" title="请选择结束时间" value="${param.payEndTime}" placeholder="请选择结束时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            </td>
			          </tr>
			          <tr>
			          		<td align="center" colspan="10">
				            	<input class="input-btn w100" type="button" value="查询" onclick="searchList();">
				            	<input class="input-btn w100" type="button" value="导出" onclick="exportList();">
				            </td>
			          </tr>
			        </table>
			    </form>
		    </div>  
		    
		    <display:table name="doorVerifyAndPayments" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="total" >
				<display:column style="text-align:center;" headerClass="t_center" title="所在省" property="province"/>
				<display:column style="text-align:center;" headerClass="t_center" title="所在市" property="city"/>
				<display:column style="text-align:center;" headerClass="t_center" title="物业公司" property="pcName"/>
				<display:column style="text-align:center;" headerClass="t_center" title="代理商" property="cpCompanyName"/>
				<display:column style="text-align:center;" headerClass="t_center" title="小区名称" property="gbName"/>
				<display:column style="text-align:center;" headerClass="t_center" title="楼栋号" property="buildingName"/>
				<display:column style="text-align:center;" headerClass="t_center" title="单元" property="unit"/>
				<display:column style="text-align:center;" headerClass="t_center" title="房间号" property="roomNum"/>
				<display:column style="text-align:center;" headerClass="t_center" title="注册状态">
					<c:choose>
						<c:when test="${row.registerState eq 1}">已注册</c:when>
						<c:otherwise>未注册</c:otherwise>
					</c:choose>
				</display:column>
				<display:column style="text-align:center;" headerClass="t_center" title="认证状态">
					<c:choose>
						<c:when test="${(not empty row.verifyState) and (row.verifyState eq 1)}">已认证</c:when>
						<c:otherwise>未认证</c:otherwise>
					</c:choose>
				</display:column>
				<display:column style="text-align:center;" headerClass="t_center" title="认证时间" property="verifyTime"/>
				<display:column style="text-align:center;" headerClass="t_center" title="缴费状态">
					<c:choose>
						<c:when test="${row.payState eq 1}">已缴费</c:when>
						<c:otherwise>未缴费</c:otherwise>
					</c:choose>
				</display:column>
				<display:column style="text-align:center;" headerClass="t_center" title="缴费时间" property="payTime"/>
			</display:table>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/select2.js"></script>
	<script type="text/javascript">
		// 搜索
		function searchList(){
			$("#doorListForm").attr("action", "${pageContext.request.contextPath}/door/doorVerifyAndPaymentIndex.html");
			$("#doorListForm").submit();
		}
		
		// 导出账单
		function exportList(){
			location.href = "${pageContext.request.contextPath}/door/exportDoorVerifyAndPayment.html?"+$("#doorListForm").serialize();
		}

		$(function(){
			var gbName = '${param.gbName}';
			$.ajax({
				url: '${pageContext.request.contextPath}/groupBuilding/getGroupBuildings.json',
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