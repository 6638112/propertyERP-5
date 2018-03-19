<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商家管理-商家店铺管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
</head>

<body>
<div class="info">
    <h2>商家店铺管理</h2>
    <div class="bs-example bgebeb">
    	<form method="post" action="../communitySupplyCompany/search.html">
	        <table class="info-list" border="0">
	          <tr>
	            <td><div align="right">店铺名称：</div></td>
	            <td><input type="text" class="input_text w120 pp" name="csName" value="${param.csName }"></td>
	            <td><div align="right">店铺类别：</div>
	            </td>
	            <td>
	          		<select class="select_normal w131" name="cstId" >
		                    <option value="all">全部</option>
			            <c:forEach var="cst" items="${cstList}" varStatus="status" > 
		                    <option value="${cst.id}" <c:if test="${param.cstId != 'all' and param.cstId eq cst.id}">selected</c:if>>${cst.name}</option>
			            </c:forEach>
	                </select>
	            </td>
	            <td><div align="right">审核状态：</div></td>
	            <td>
					<select name="auditStatus" class="select_normal w131">
	                    <option value="">全部</option>
	                    <option value="1" <c:if test="${param.auditStatus eq 1}">selected</c:if>>待审核</option>
	                    <option value="2" <c:if test="${param.auditStatus eq 2}">selected</c:if>>审核通过</option>
	                    <option value="3" <c:if test="${param.auditStatus eq 3}">selected</c:if>>审核失败</option>
                    </select>
				</td>
				<td></td>
	            <td>
	            	<input class="input-btn w80" type="submit" value="搜索">
	            </td>
	            <td></td>
	            <td></td>
	          </tr>
	        </table>
    	</form>
    </div>
    
    <display:table name="resList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
		<display:column title="店铺名称" sortable="true">
			<a class="blue" href="../communitySupplyCompany/view.html?id=${row.id}&tmpId=${row.tmpId}">${row.supplyName}</a> 
		</display:column>
		<display:column title="店铺类别" property="supplyType" />
		<%-- <display:column title="店铺电话" property="companyPhone" /> --%>
		<display:column title="店铺地址" property="address" />
		<display:column title="店铺状态" >
			<c:if test="${row.auditStatus ==1 }">待审核</c:if>
			<c:if test="${row.auditStatus ==2 }">审核通过</c:if>
			<c:if test="${row.auditStatus ==3 }">审核失败</c:if>
		</display:column>
		<display:column title="操作" >
			<a class="blue" name="view" href="../communitySupplyCompany/view.html?id=${row.id }&tmpId=${row.tmpId}" >查看</a>
			<c:if test="${row.auditStatus !=1 }">
				<a class="blue" name="edit" onclick="return confirm('您确定要编辑吗?');" href="../communitySupplyCompany/initEdit.html?id=${row.id }" >编辑</a>
			</c:if>
		</display:column>
	</display:table>
</div>
</body>
</html>
