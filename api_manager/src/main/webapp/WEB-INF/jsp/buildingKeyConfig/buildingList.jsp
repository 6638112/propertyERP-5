<%-- 
    Document   : 门禁认证选项配置管理
    Created on : 2016-3-16, 13:20:24
    Author     : Liyl lyl010991@126.com
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="com.cnfantasia.server.ms.pub.session.UserContext" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>物业-门禁认证选项配置管理</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
	</head>
	<body>
		<div class="info">
		    <h2>门禁认证选项配置管理</h2>
		    <div class="bs-example bgebeb">
		    	<form method="post" action="${pageContext.request.contextPath}/groupBuilding/searchKeyConfig.html">
			        <table class="info-list" border="0">
			          <tr>
			            <td><div align="right">管理处：</div></td>
			            <td><input type="text" value="${param.pmName}" class="input_text pp w120" name="pmName" /></td>
			            <td><div align="right">小区名称：</div>
			            </td>
			            <td>
			          		<input type="text" class="input_text pp w120" value="${param.gbName }" name="gbName" />
			            </td>
			            <td><div align="right">审核状态：</div></td>
			            <td>
							<select name="auditStatus" class="select_normal w131">
			                    <option value="-1" >全部</option>
			                    <option value="0" <c:if test="${param.auditStatus==0 }"> selected="selected"</c:if>>待审核</option>
			                    <option value="1" <c:if test="${param.auditStatus==1 }"> selected="selected"</c:if>>审核通过</option>
			                    <option value="2" <c:if test="${param.auditStatus==2 }"> selected="selected"</c:if>>审核失败</option>
		                    </select>
						</td>
						<td></td>
			            <td>
			            	<input class="input-btn w80" type="submit" value="搜索" />
			            </td>
			            <td></td>
			            <td></td>
			          </tr>
			        </table>
		    	</form>
		    </div>
		    <display:table name="resList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="resultSize" >
				<display:column style="text-align:center;" headerClass="t_center" title="小区名称" sortable="true">
					<a class="blue" href="${pageContext.request.contextPath}/groupBuilding/view.html?id=${row.id}">${row.name}</a> 
				</display:column>
				<display:column style="text-align:center;" headerClass="t_center" title="所在地">
					${row.addressProvinceName}-${row.addressCityName}-${row.addressBlockName}
				</display:column>
				<display:column style="text-align:center;" headerClass="t_center" title="详细地址" property="addressDesc" />
				<display:column style="text-align:center;" headerClass="t_center" title="所属管理处">
					${row.propertyManagementName}
				</display:column>
				<display:column style="text-align:center;" headerClass="t_center" title="所属物业公司">
					${row.propertyCompanyName}
				</display:column>
				<display:column style="text-align:center;" headerClass="t_center" title="审核状态">
					<c:if test="${row.checkStatus ==1 }">审核通过</c:if>
					<c:if test="${row.checkStatus ==2 }">审核失败</c:if>
					<c:if test="${row.checkStatus ==0 }">待审核</c:if>
					<c:if test="${row.checkStatus ==9 }">无须审核</c:if>
				</display:column>
				<display:column style="text-align:center;" headerClass="t_center" title="操作" >
					<a class="blue" name="view" href="${pageContext.request.contextPath}/buildingKeyConfig/keyConfigIndex.html?groupBuildingId=${row.id}&gbName=${row.name}">配置</a>
				</display:column>
			</display:table>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
</html>
