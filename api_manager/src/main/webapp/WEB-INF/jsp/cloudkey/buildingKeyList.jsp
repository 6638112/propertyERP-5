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
<link rel="stylesheet" type="text/css" href="../css/picbox.css" />
</head>

<body>
<div class="info">
    <h2>门禁开锁审核管理</h2>
    <div class="bs-example bgebeb">
    	<form action="../cloudkey/search.html">
	        <table class="info-list" border="0">
	          <tr>
	          	<td align="right">状态：</td>
	          	<td>
	            	<select class="select_normal w131" name="status">
	                    <option value="-1">所有</option>
	                    <option value="0" <c:if test="${param.status==0 }">selected="selected"</c:if> >待审核</option>
	                    <option value="1" <c:if test="${param.status==1 }">selected="selected"</c:if>>已审核</option>
	                    <option value="2" <c:if test="${param.status==2 }">selected="selected"</c:if>>审核失败</option>
	                </select>
				</td>
	          	<td align="right">提交时间：</td>
	          	<td colspan="2"><input id="date01" name='startTime' type="text" class="input_text icon_dt pp" value="${param.startTime }"  title="请选择起始时间" > 至 
	          	<input  type="text" class="input_text icon_dt pp" id="date02"  name="endTime" value="${param.endTime }" title="请选择结束时间"></td>
	           	<td><div align="right">楼栋：</div></td>
				<td><input class="input_text pp" type="text" name="buildingname" value="${param.buildingname }"></td>
				<td><div align="right">单元：</div></td>
				<td><input class="input_text pp" type="text" name="unitName" value="${param.unitName }"></td>
				<td><div align="right">解放号：</div></td>
				<td><input class="input_text pp" type="text" name="huaId" value="${param.huaId }"></td>
	            <td><input class="input-btn w80" type="submit" value="查询"/></td>
	          </tr>
	        </table>
        </form>
    </div>    

    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
		<display:column title="用户解放号" property="huaId"/>
		<display:column title="小区" property="gbName"/>
		<display:column title="楼栋" property="buildingname"/>	
		<display:column title="单元" property="unitName" />
		<display:column title="房间号" property="roomNum"/>
		<display:column title="提交时间" property="sys0AddTime"/>
		<display:column title="状态">  
			<c:choose>
				<c:when test="${row.status ==0}">
					待审核
				</c:when>
				<c:when test="${row.status ==1}">
					已审核
				</c:when>
				<c:when test="${row.status ==2}">
					审核失败
				</c:when>
			</c:choose>
		</display:column>
		<display:column title="操作">
			<c:choose>
				<c:when test="${row.status ==0}"><div class="red">
					<a href="../cloudkey/buildingKeyDetail.html?auditUserId=${row.id}"><div class="blue">审核</div></a></div>
				</c:when>
				<c:when test="${row.status ==1}">
					<div class="red"><a href="../cloudkey/buildingKeyDetail.html?auditUserId=${row.id}"><div class="blue">查看</div></a></div>
				</c:when>
				<c:when test="${row.status ==2}"><div class="red">
					<a href="../cloudkey/buildingKeyDetail.html?auditUserId=${row.id}"><div class="blue">查看</div></a></div>
				</c:when>
			</c:choose>
		</display:column>	
	</display:table>
</div>
</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker01.js"></script>
<script type="text/javascript" src="../js/picbox.js?v20150215"></script>
<script type="text/javascript">	

</script>
</html>
