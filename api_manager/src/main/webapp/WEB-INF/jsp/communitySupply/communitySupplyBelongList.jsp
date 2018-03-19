<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="../error/404.jsp"%>
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
<title>店铺管理-认领店铺列表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="info">
    <h2>店铺列表</h2>
    <div class="bs-example bgebeb">
    	<form action="${pageContext.request.contextPath}/communitySupply/searchBelong.html">
	        <table class="info-list citySelect" border="0">
	          <tr>
	            <td><div class="list-name">店铺名称：</div></td>
	            <td><input name="csName" type="text" class="input_text w120 pp" value="${param.csName}"></td>
	            <td align="right">店铺类别：</td>
	            <td><select name="cstId" class="select_normal w131">
	                    <option value="">全部</option>
	            		<c:forEach items="${cstList}" var="cst"> 
		                    <option value="${cst.id }" <c:if test="${(not empty param.cstId) and (param.cstId eq cst.id)}"> selected</c:if>>${cst.name }</option>
	            		</c:forEach>
	                    </select></td>
	            <td><div class="list-name">审核状态：</div></td>
	            <td>
                    <select name="auditStatus" class="select_normal w131">
	                    <option value="">全部</option>
	                    <option value="1" <c:if test="${(not empty param.auditStatus) and (param.auditStatus eq 1)}"> selected</c:if>>待审核</option>
	                    <option value="2" <c:if test="${(not empty param.auditStatus) and (param.auditStatus eq 2)}"> selected</c:if>>审核通过</option>
	                    <option value="3" <c:if test="${(not empty param.auditStatus) and (param.auditStatus eq 3)}"> selected</c:if>>审核失败</option>
                    </select>
	            </td>
	          </tr>
	        </table>
  		<div class="mtop10 t_center"><input class="input-btn w200" type="submit" value="搜索"></div>
    	</form>
    </div>
    
    <display:table name="resList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="10" requestURI="" partialList="true" size="resultSize">
		<display:column title="店铺名称" >${row.communitySupply.name}	</display:column>
		<display:column title="店铺地址">${row.communitySupply.addressDetail}</display:column>
		<display:column title="类别">${row.communitySupplyType.name}</display:column>
		<display:column title="认领时间" property="sys0AddTime" />
		<display:column title="审核状态" >
			<c:choose>
				<c:when test="${row.auditStatus==1 }">待审核</c:when>
				<c:when test="${row.auditStatus==2 }">审核通过</c:when>
				<c:when test="${row.auditStatus==3 }">审核失败</c:when>
			</c:choose>
		</display:column>
		<display:column title="操作" >
			<a class="blue editShopInfo" href="${pageContext.request.contextPath}/communitySupply/viewBelongDetail.html?id=${row.id }">查看</a>
			<c:if test="${row.auditStatus==1 }">
		        <a class="blue checkShopInfo auditHref" href="${pageContext.request.contextPath}/communitySupply/auditCSBelongView.html?id=${row.id }">审核</a>
			</c:if>
		</display:column>
	</display:table>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
</html>
