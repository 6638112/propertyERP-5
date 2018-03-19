<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>账单打印模版</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <style type="text/css">.red{color:red;}</style>
</head>
<body>
<div class="info">
    <h2>账单打印模版</h2>
    <div class="bs-example bgebeb">
    	<form method="get" action="${pageContext.request.contextPath}/payBill/printConfig.html">
	        <table class="info-list" border="0">
	          <tr>
	            <td><div class="list-name">小区名：</div></td>
	            <td>
	            	<input type="text" class="input_text w240 pp" name="gbName" value="${param.gbName}">
	            </td>
	            <td><div class="list-name">配置状态：</div></td>
	            <td>
	            	<select name="isConfig" class="select_normal">
	            		<option value="">全部</option>
	            		<option value="1" <c:if test="${param.isConfig eq 1}"> selected</c:if>>已配置</option>
	            		<option value="2" <c:if test="${param.isConfig eq 2}"> selected</c:if>>未配置</option>
	            	</select>
	            </td>
	            <td>
	            	<input class="input-btn w100" type="submit" value="查询">
	            </td>
	          </tr>
	        </table>
	    </form>
    </div>  
    <display:table name="printConfigList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
        <display:column title="小区">
            ${row.name}
        </display:column>
        <display:column title="是否已配置模板">
        	<c:choose>
        		<c:when test="${row.config}">是</c:when>
        		<c:otherwise><span class="red">否</span></c:otherwise>
        	</c:choose>
        </display:column>
        <display:column title="操作">
            <a class="blue viewTicket" href="<%=basePath%>/payBill/editPrintConfig.html?gbId=${row.id}">编辑模版</a>
        </display:column>
    </display:table>
</div>
</body>
</html>