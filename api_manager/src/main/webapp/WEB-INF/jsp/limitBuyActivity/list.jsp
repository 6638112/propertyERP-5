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
		<title>限时抢购列表</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
		<style>
			.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
			.w180{width: 170px;}
		</style>
	</head>
	<body>
		<div class="info">
		    <h2 class="mtop20">限时抢购列表</h2> 
		    <div class="bs-example bgebeb">
		    	<form method="post" action="${pageContext.request.contextPath}/limitBuyActivity/index.html">
			        <table class="info-list" border="0">
			            <tr>
				            <td><div class="list-name">商品id：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="productId" value="${param.productId}"></td>
				            <td><div class="list-name">开始时间：</div></td>
				            <td>
				            	<input type="text" name="startTimeStart" title="请选择起始时间" value="${param.startTimeStart}" placeholder="请选择起始时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            	至
				            	<input type="text" name="startTimeEnd" title="请选择截止时间" value="${param.startTimeEnd}" placeholder="请选择截止时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            </td>
				            <td><div class="list-name">状态：</div></td>
				            <td>
				            	<select name="status" class="select_normal">
				                    <option value="">全部</option>
				                    <option value="1" <c:if test="${(not empty param.status) and (param.status eq 1)}">selected</c:if>>进行中</option>
				                    <option value="2" <c:if test="${(not empty param.status) and (param.status eq 2)}">selected</c:if>>未开始</option>
				                    <option value="3" <c:if test="${(not empty param.status) and (param.status eq 3)}">selected</c:if>>已结束</option>
			                    </select>
				            </td>
				        </tr>
				        <tr>
				        	<td><div class="list-name">商品名称：</div></td>
				            <td><input type="text" class="input_text w240 pp" name="productName" value="${param.productName}"></td>
				            <td><div class="list-name">结束时间：</div></td>
				            <td>
				            	<input type="text" name="endTimeStart" title="请选择起始时间" value="${param.endTimeStart}" placeholder="请选择起始时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            	至
				            	<input type="text" name="endTimeEnd" title="请选择截止时间" value="${param.endTimeEnd}" placeholder="请选择截止时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" class="input_text pp w180 icon_dt pp">
				            </td>
				            <td><input class="input-btn w100" type="submit" value="查询"></td>
			          </tr>
			        </table>
			    </form>
		    </div>  
		    <display:table name="labs" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="total" >
		    	<display:column title="标题" property="title" style="text-align:left;" headerClass="t_center"/>
				<display:column title="商品名称" property="productName" style="text-align:left;" headerClass="t_center"/>
				<display:column title="商品id" property="productId" style="text-align:left;" headerClass="t_center"/>
				<display:column title="供应商" property="merchantName" style="text-align:left;" headerClass="t_center"/>
				<display:column title="原价" style="text-align:right;" headerClass="t_center">
					<fmt:formatNumber value="${row.originalPrice}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
				<display:column title="抢购价"  style="text-align:right;" headerClass="t_center">
					<fmt:formatNumber value="${row.robPrice}" type="currency" pattern="0.00" maxFractionDigits="2"/>
				</display:column>
				<display:column title="促销库存" style="text-align:right;" property="leftCount"/>
				<display:column title="每人限购" style="text-align:right;" >
					<c:choose>
						<c:when test="${row.maxCanBuy>-1 }"> ${row.maxCanBuy }</c:when>
						<c:otherwise>不限购</c:otherwise>
					</c:choose>
				</display:column>
				
				<display:column title="开始时间" property="startTime" style="text-align:left;" headerClass="t_center"/>
				<display:column title="结束时间" property="endTime" style="text-align:left;" headerClass="t_center"/>
				
				<display:column title="状态" style="text-align:left;" headerClass="t_center">
					<c:choose>
						<c:when test="${(row.startStatus eq 1) and (row.endStatus eq 1)}">进行中</c:when>
						<c:when test="${row.startStatus eq 0}">未开始</c:when>
						<c:when test="${row.endStatus eq 0}">已结束</c:when>
					</c:choose>
				</display:column>
				<display:column style="text-align:center;" headerClass="t_center" title="操作">
					<a class="blue" href="${pageContext.request.contextPath}/limitBuyActivity/detailPage.html?lbaId=${row.lbaId}&pageType=query">查看</a>
					<c:if test="${row.startStatus eq 0}">
						<a class="blue" href="${pageContext.request.contextPath}/limitBuyActivity/detailPage.html?lbaId=${row.lbaId}&pageType=update">编辑</a>
					</c:if>
					<c:if test="${(row.startStatus eq 1) and (row.endStatus eq 1)}">
						<a class="blue" href="javascript:finishLimitBuyActivity(${row.lbaId}, '${row.title}')">结束</a>
					</c:if>
					<c:if test="${(row.startStatus eq 1) and (row.endStatus eq 1)}">
						<c:if test="${row.positionType eq 1}">
							<a class="blue" href="javascript:resetPosition('${row.lbaId}', 2)">放入首页</a>
						</c:if>
						<c:if test="${row.positionType eq 2}">
							<a class="blue" href="javascript:resetPosition('${row.lbaId}', 1)">从首页移除</a>
						</c:if>
					</c:if>
				</display:column>
			</display:table>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		// 导出账单
		function exportList(){
			location.href = "${pageContext.request.contextPath}/cashSqpayBt/exportCashSqpayBts.html?"+$("#cashSqpayBtForm").serialize();
		}

		function resetPosition(limitBuyId, positionType) {
			$.post("${pageContext.request.contextPath}/limitBuyActivity/resetPosition.html",
					{"limitBuyId":limitBuyId, "positionType":positionType },
					function(data){
				if(data.status == "0000") {
					alert("操作成功");
					window.location.reload();
				} else {
					alert(data.message);
				}
			});
		}
		function finishLimitBuyActivity(lbaId, title){
			if(confirm("您确定要结束【"+title+"】吗？")){
				$.post("${pageContext.request.contextPath}/limitBuyActivity/finishLimitBuyActivity.html", {"lbaId":lbaId}, function(data){
					alert(data.message);
					if(data.status == "<%=CommConstants.ResponseStatus.SUCCESS%>") {
						location = location;
					}
				});
			}
		}
	</script>
</html>