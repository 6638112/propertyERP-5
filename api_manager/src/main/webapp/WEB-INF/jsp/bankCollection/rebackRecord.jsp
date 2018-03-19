<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<title>回盘记录</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<style>
			.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
			.w180{width: 170px;}
			.red{color:red;}
		</style>
	</head>
	<body>
		<div class="info">
		    <h2 class="mtop20">回盘记录</h2> 
		    <div class="bs-example bgebeb">
		    	<form id="bcHistoryForm" method="post" action="${pageContext.request.contextPath}/bankCollection/viewBc.html">
		    		<input type="hidden" name="bgbcIds" value="${bgbcIds}">
			        <table class="info-list" border="0">
			          <tr>
				            <td><div class="list-name">小区：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="gbName" value="${param.gbName}"></td>
				            <td><div class="list-name">楼栋：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="buildingName" value="${param.buildingName}"></td>
				            <td><div class="list-name">单元：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="unitName" value="${param.unitName}"></td>
				            <td><div class="list-name">房号：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="roomName" value="${param.roomName}"></td>
				            <td><div class="list-name">业主姓名：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="ppName" value="${param.ppName}"></td>
			          </tr>
			          <tr>
				            <td><div class="list-name">回盘信息：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="rebackContent" value="${param.rebackContent}"></td>
				            <td><div class="list-name">银行托收结果：</div></td>
				            <td>
			                    <select name="bankResult" class="select_normal">
			                    	<option value="">全部</option>
									<option value="1" <c:if test="${param.bankResult eq 1}"> selected</c:if>>成功</option>
							        <option value="2" <c:if test="${param.bankResult eq 2}"> selected</c:if>>失败</option>
								</select>
				            </td>
				            <td><div class="list-name">回盘时间：</div></td>
				            <td>
				           		<input type="text" name="rebackStartTime" title="请选择起始时间" value="${param.rebackStartTime}" placeholder="请选择起始时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            	至 
				            	<input type="text" name="rebackEndTime" title="请选择结束时间" value="${param.rebackEndTime}" placeholder="请选择结束时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            </td>
				            <td><div class="list-name">回盘结果：</div></td>
				            <td>
			                    <select name="rtStatus" class="select_normal">
			                    	<option value="">全部</option>
									<option value="1" <c:if test="${param.rtStatus eq 1}"> selected</c:if>>回盘成功</option>
							        <option value="2" <c:if test="${param.rtStatus eq 2}"> selected</c:if>>未找到账单</option>
							        <option value="3" <c:if test="${param.rtStatus eq 3}"> selected</c:if>>余额不足</option>
							        <option value="4" <c:if test="${param.rtStatus eq 4}"> selected</c:if>>金额不匹配</option>
							        <option value="5" <c:if test="${param.rtStatus eq 5}"> selected</c:if>>重复回盘</option>
								</select>
				            </td>
				            <td colspan="4" style="text-align: left;">
				            	<input class="info-btn small-btn w100" type="submit" value="查询"/>
				            </td>
			          </tr>
			        </table>
			    </form>
		    </div>  
		    <display:table name="rebackRecords" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="total" >
	    		<display:column title="小区" property="gbName" style="text-align:center;" headerClass="t_center"/>
	    		<display:column title="楼栋" property="buildingName" style="text-align:center;" headerClass="t_center"/>
	    		<display:column title="单元" property="unitName" style="text-align:center;" headerClass="t_center"/>
	    		<display:column title="房号" property="roomName" style="text-align:center;" headerClass="t_center"/>
	    		<display:column title="业主姓名" property="ppName" style="text-align:center;" headerClass="t_center"/>
	    		<display:column title="回盘信息" property="rebackContent" style="text-align:center;" headerClass="t_center"/>
	    		<display:column title="银行托收结果" style="text-align:center;" headerClass="t_center">
	    			<c:choose>
	    				<c:when test="${(row.rtStatus eq 1) or (row.rtStatus eq 5)}">成功</c:when>
	    				<c:otherwise><span class="red">失败</span></c:otherwise>
	    			</c:choose>
				</display:column>
	    		<display:column title="回盘时间" property="rebackTime" style="text-align:center;" headerClass="t_center"/>
				<display:column title="结果" style="text-align:center;" headerClass="t_center">
					<c:choose>
						<c:when test="${row.rtStatus eq 1}">回盘成功</c:when>
						<c:when test="${row.rtStatus eq 2}"><span class="red">未找到账单</span></c:when>
						<c:when test="${row.rtStatus eq 3}"><span class="red">余额不足</span></c:when>
						<c:when test="${row.rtStatus eq 4}"><span class="red">金额不匹配</span></c:when>
						<c:when test="${row.rtStatus eq 5}">重复回盘</c:when>
					</c:choose>
				</display:column>
			</display:table>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
</html>