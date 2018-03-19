
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>申请高级合作模式</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/jRating.jquery.css" />
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
    <h2>合作模式申请审核</h2>
    <div class="bs-example bgebeb">
    	<form method="post" action="../propertyCompany/applyAuditList.html">
	        <table class="info-list" border="0">
	          <tr>
	            <td><div align="right">物业公司：</div></td>
	            <td><input type="text" value="${param.propertyCompanyName }" class="input_text pp w120" name="propertyCompanyName" /></td>
	            <!-- <td><div align="right">合作方式</div></td>
	            <td>
	            	<select class="select_normal w100">
	                    <option>全部</option>
	                    <option>基础合作</option>
	                    <option>高级合作</option>
	                    <option>全面合作</option>
                    </select>
				</td> -->
	            <td><div align="right">审核状态：</div>
	            </td>
	            <td>
	          		<select class="select_normal w100" name="auditStatus">
	                    <option value="">全部</option>
	                    <option value="2" <c:if test="${param.auditStatus==2 }"> selected="selected"</c:if>>待审核</option>
	                    <option value="1" <c:if test="${param.auditStatus==1 }"> selected="selected"</c:if>>审核通过</option>
	                    <option value="3" <c:if test="${param.auditStatus==3 }"> selected="selected"</c:if>>审核失败</option>
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
    <display:table name="resList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
		<display:column title="物业公司名称" sortable="true">
			<a class="blue" href="../propertyCompany/initApplyAudit.html?id=${row.id}">${row.name}</a> 
		</display:column>
		<display:column title="申请合作方式">
			<c:choose>
            	<c:when test="${row.isAllCooperation!=null}">
            		<span class="left mtop2">全面合作</span>
            	</c:when>
            	<c:when test="${row.isHighCooperation!=null}"><span class="left mtop2">高级合作</span></c:when>
            	<c:otherwise>
			　　       <span class="left mtop2">基础合作</span>
			　　</c:otherwise>
			</c:choose> 
		</display:column>
		<display:column title="提交时间">
			<c:choose>
            	<c:when test="${row.isAllCooperation!=null}">
            		<span class="left mtop2">${row.allCooperationTime}</span>
            	</c:when>
            	<c:when test="${row.isHighCooperation!=null}"><span class="left mtop2">${row.highCooperationTime}</span></c:when>
            	<c:otherwise>
			　　       <span class="left mtop2">${row.lightCooperationTime}</span>
			　　</c:otherwise>
			</c:choose> 
		</display:column>
		<display:column title="状态">
			<c:choose>
            	<c:when test="${row.isAllCooperation!=null}">
            		<c:if test="${row.isAllCooperation!=null && row.isAllCooperation==1}">
            			<span class="left mtop2">审核通过</span>
            		</c:if>
            		<c:if test="${row.isAllCooperation!=null && row.isAllCooperation==2}">
            			<span class="left mtop2">待审核</span>
            		</c:if>
            		<c:if test="${row.isAllCooperation!=null && row.isAllCooperation==3}">
            			<span class="left mtop2">审核失败</span>
            		</c:if>
            	</c:when>
            	<c:when test="${row.isHighCooperation!=null}">
            		<c:if test="${row.isHighCooperation!=null && row.isHighCooperation==1}">
            			<span class="left mtop2">审核通过</span>
            		</c:if>
            		<c:if test="${row.isHighCooperation!=null && row.isHighCooperation==2}">
            			<span class="left mtop2">待审核</span>
            		</c:if>
            		<c:if test="${row.isHighCooperation!=null && row.isHighCooperation==3}">
            			<span class="left mtop2">审核失败</span>
            		</c:if>
            	</c:when>
            	<c:otherwise>
			　　       <span class="left mtop2">审核通过</span>
			　　</c:otherwise>
			</c:choose> 
		</display:column>
		<display:column title="操作" >
			<a class="blue" name="view" href="../propertyCompany/initApplyAudit.html?id=${row.id }&opType=0" >查看</a>
			<c:if test="${row.isAllCooperation!=null && row.isAllCooperation==2}">
				<a class="blue" name="superAudit" href="../propertyCompany/initApplyAudit.html?id=${row.id}&opType=1" >审核</a>
			</c:if>
			<c:if test="${row.isHighCooperation!=null && row.isHighCooperation==2}">
				<a class="blue" name="allAudit" href="../propertyCompany/initApplyAudit.html?id=${row.id}&opType=1" >审核</a>
			</c:if>
		</display:column>
	</display:table>  
</div>
</body>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript">
(function($){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		dragonfly:true,
		ignoreHidden:true
	});
})(jQuery);
</script>
</html>
