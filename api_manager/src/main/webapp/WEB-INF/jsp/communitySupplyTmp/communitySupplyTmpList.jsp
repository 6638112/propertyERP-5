<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商家管理-小区商家黄页管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
    <h2>小区商家管理</h2>
    <div class="bs-example bgebeb">
    	<form method="post" action="../communitySupplyTmp/searchTmp.html">
	        <table class="info-list" border="0">
	          <tr>
	            <td><div align="right">商家名称：</div></td>
	            <td><input type="text" class="input_text w120" name="csName" value="${param.csName }"></td>
	            <td><div align="right">商家类别：</div>
	            </td>
	            <td>
	          		<select class="select_normal w131" name="supplyType" >
		                    <option value="all">全部</option>
			            <c:forEach var="cst" items="${cstList}" varStatus="status" > 
		                    <option value="${cst.name}" <c:if test="${param.supplyType eq cst.name}">selected</c:if> >${cst.name}</option>
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
	            	<input class="input-btn w80" onclick="addSupplyTmp();" type="button" value="新增商家">
	            </td>
	            <td></td>
	            <td></td>
	          </tr>
	        </table>
    	</form>
    </div>
    
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
		<display:column title="商家类别" property="supplyType" sortable="true"/>
		<display:column title="商家名称" property="supplyName" sortable="true"/>
		<display:column title="商家地址" property="addressDetail" />
		<%-- <display:column title="联系人" property="companyName" />
		<display:column title="联系电话" property="companyPhone" /> --%>
		<display:column title="审核状态" >
			<c:if test="${row.auditStatus ==1 }">待审核</c:if>
			<c:if test="${row.auditStatus ==2 }">审核通过</c:if>
			<c:if test="${row.auditStatus ==3 }">审核失败</c:if>
		</display:column>
		<display:column title="操作" >
			<a class="blue" name="view" href="../communitySupplyTmp/viewTmpDetail.html?id=${row.id }" >查看</a>
			<c:if test="${row.auditStatus !=2 }">
				<!-- <a class="blue suggest" name="remove" href="../communitySupplyTmp/delTmpDetail.html?id=${row.id }">删除</a> -->
				<a class="blue deleteNotice" href="javaScript:deleteTmp(${row.id})">删除</a> 
			</c:if>
		</display:column>
	</display:table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript">	
function addSupplyTmp(){
	var href = "<%=basePath%>/communitySupplyTmp/initSupplyTmp.html";
	$(window.parent.document).find("#mainFrame").attr("src", href);
}

function deleteTmp(idValue){
	if(window.confirm('您确定要删除该记录吗？')){
		$.ajax({//使用ajax请求删除数据
			type: "GET",
			url: "../communitySupplyTmp/delTmpDetail.html",
			async:true,
			data: {id:idValue,},
			success: function(data, textStatus){
		        alert("操作提示："+data);
				$(window.parent.document).find("#mainFrame").attr('src', "<%=basePath%>/communitySupplyTmp/searchTmp.html");
			},
		}); 
		//再刷新iframe
	}else{
		return false;
	}
}
</script>
</html>
