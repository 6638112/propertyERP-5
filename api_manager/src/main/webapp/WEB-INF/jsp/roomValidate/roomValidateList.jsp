<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
<style>
    .record_num{color:red;}
</style>
</head>

<body>
<div class="info">
    <h2>门牌审核管理</h2>
    <div class="bs-example bgebeb">
    	<form action="../roomValidate/search.html">
	        <table class="info-list" border="0">
	          <tr>
	            <td><div align="right">状态：</div></td>
	            <td>
	            	<select class="select_normal w131" name="rvStatus">
	                    <option value="0" <c:if test="${param.rvStatus==0 }"> selected="selected"</c:if> >所有</option>
	                    <option value="2" <c:if test="${param.rvStatus==2 or empty param.rvStatus}"> selected="selected"</c:if>>待审核</option>
	                    <option value="3" <c:if test="${param.rvStatus==3 }"> selected="selected"</c:if>>审核失败</option>
	                    <option value="4" <c:if test="${param.rvStatus==4 }"> selected="selected"</c:if>>审核通过</option>
	                </select>
	            </td>
	            <td><div class="list-name">提交时间：</div></td>
	            <td><input type="text" class="input_text icon_dt" id="date01" name="startTime" title="请选择起始时间" value="${param.startTime }"/>至 <input type="text" class="input_text icon_dt" id="date02" value="${param.endTime }" name="endTime" title="请选择结束时间"/></td>
	            <td><div class="list-name">小区：</div></td>
            	<td><input type="text" class="input_text w80" name="groupBuildingName" value="${param.groupBuildingName }" /></td>
	            <td><div class="list-name">用户ID：</div></td>
	            <td><input type="text" class="input_text w80" name="uId" value="${param.uId }"/></td>
	            <td></td>
	            <td><input class="input-btn w80" type="submit" value="查询"/></td>
	          </tr>
	        </table>
        </form>
    </div>    
    
    <display:table name="resList" id="row" class="info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
		<display:column title="用户ID" property="uId" class="${(row.recordNum >= 4)?'record_num':''}"/>
		<display:column title="小区" property="gbDesc" class="${(row.recordNum >= 4)?'record_num':''}"/>
		<display:column title="楼栋" property="bName" class="${(row.recordNum >= 4)?'record_num':''}"/>
		<display:column title="单元" property="unitName" class="${(row.recordNum >= 4)?'record_num':''}"/>
		<display:column title="房号" property="numTail" class="${(row.recordNum >= 4)?'record_num':''}"/>
		<display:column title="提交时间" property="rvSubmitTime" class="${(row.recordNum >= 4)?'record_num':''}"/>
		<display:column title="状态" class="${(row.recordNum >= 4)?'record_num':''}">
			<c:choose>
				<c:when test="${row.rvStatus==2 }">待审核</c:when>
				<c:when test="${row.rvStatus==3 }">审核失败</c:when>
				<c:when test="${row.rvStatus==4 }">审核通过</c:when>
				<c:otherwise>其它</c:otherwise>
			</c:choose>
		</display:column>
		<display:column title="操作" >
			<c:if test="${row.rvStatus<=2}">
				<a class="blue editOwnerInfo" href="../roomValidate/audit.html?id=${row.rvId }">审核</a>
			</c:if>
			<c:if test="${row.rvStatus>2}">
				<a class="blue editOwnerInfo" href="../roomValidate/view.html?id=${row.rvId }">查看</a>
				<a class="blue editOwnerInfo" href="../roomValidate/audit.html?id=${row.rvId }">修改</a>
			</c:if>
		</display:column>
	</display:table>
</div>
</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
</html>
