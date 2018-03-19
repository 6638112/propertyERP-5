<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>渠道新增小区审核</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>

<body>
<div class="info">
    <h2>小区审核</h2>
    <div class="bs-example bgebeb">
    	<form method="post" action="../groupBuilding/searchCPAudit.html">
	        <table class="info-list" border="0">
	          <tr>
	            <td><div align="right">物业公司：</div></td>
	            <td><input type="text" value="${param.pcName }" class="input_text pp w120" name="pcName" /></td>
	            <td><div align="right">审核状态：</div></td>
	            <td>
					<select name="auditStatus" class="select_normal w131">
	                    <option value="">全部</option>
	                    <option value="0" <c:if test="${param.auditStatus==0 }"> selected="selected"</c:if>>待审核</option>
	                    <option value="1" <c:if test="${param.auditStatus==1 }"> selected="selected"</c:if>>审核通过</option>
	                    <option value="2" <c:if test="${param.auditStatus==2 }"> selected="selected"</c:if>>审核失败</option>
                    </select>
				</td>
	            <td><div align="right">小区名称：</div>
	            </td>
	            <td>
	          		<input type="text" class="input_text pp w120" value="${param.gbName }" name="gbName" />
	            </td>
	            <td >
	            	<input class="input-btn w80" type="submit" value="搜索" />
	            </td>
	           </tr>
	        </table>
    	</form>
    </div>
    
    <display:table name="resList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="resultSize" >
		<display:column title="小区名称" sortable="true">
			<a class="blue" href="../groupBuilding/view4CP.html?id=${row.id}">${row.name}</a> 
		</display:column>
		<display:column title="所在地">
			${row.addressProvinceName}-${row.addressCityName}-${row.addressBlockName}
		</display:column>
		<display:column title="详细地址" property="addressDesc" />
		<display:column title="所属物业公司">
			${row.propertyCompanyName}
		</display:column>
		<display:column title="审核状态">
			<c:if test="${row.checkStatus ==1 }">审核通过</c:if>
			<c:if test="${row.checkStatus ==2 }">审核失败</c:if>
			<c:if test="${row.checkStatus==0 or (empty row.checkStatus)}">待审核</c:if>
		</display:column>
		<display:column title="操作" >
			<c:if test="${row.checkStatus==0 or (empty row.checkStatus)}"><a class="blue audit" name="view" href="../groupBuilding/initAudit4CP.html?id=${row.id }">审核</a></c:if>
 		</display:column>
	</display:table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript">
</script>
</html>
