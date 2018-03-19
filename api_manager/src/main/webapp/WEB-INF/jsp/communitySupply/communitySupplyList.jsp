<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商家管理-小区商家管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
    <h2>小区商家管理</h2>
    <div class="bs-example bgebeb">
    	<form method="post" action="../communitySupply/search.html">
	        <table class="info-list" border="0">
	          <tr>
	            <td><div align="right">小区名称：</div></td>
	            <td><input type="text" class="input_text w120 pp" name="gbName" value="${param.gbName }" ></td>
	            <td><div align="right">商家类别：</div>
	            </td>
	            <td>
	          		<select class="select_normal w131" name="supplyType" >
		                    <option value="all">全部</option>
			            <c:forEach var="cst" items="${cstList}" varStatus="status" > 
		                    <option value="${cst.name}" <c:if test="${param.supplyType eq cst.name}">selected</c:if>>${cst.name}</option>
			            </c:forEach>
	                </select>
	            </td>
	            <td><div align="right">商家名称：</div></td>
	            <td><input type="text" class="input_text w120 pp" name="csName" value="${param.csName}"></td>
	            <td><input class="input-btn w80" type="submit" value="搜索"></td>
	            <td></td>
	          </tr>
	        </table>
    	</form>
    </div>
    
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
		<display:column title="小区名称" property="gbName" sortable="true"/>
		<display:column title="商家类别" property="supplyType" sortable="true"/>
		<display:column title="商家名称" property="name" sortable="true"/>
		<display:column title="商家地址" property="addressDetail" />
		<display:column title="商家描述" property="desc" />
		<display:column title="解放区推荐" >
			<c:if test="${row.pscAuditStatus ==1 }">待审核</c:if>
			<c:if test="${row.pscAuditStatus ==2 }">审核通过</c:if>
			<c:if test="${row.pscAuditStatus ==3 }">审核失败</c:if>
		</display:column>
		<display:column title="是否物业推荐" media="html">
			<c:if test="${row.gbId == row.pscGbId }"><a class="blue alive" href="#">是</a></c:if>
			<c:if test="${row.gbId != row.pscGbId }"><a class="grey asleep" href="#">否</a></c:if>
		</display:column>
		<display:column title="物业推荐" >
			<c:if test="${row.gbId != row.pscGbId }"><a class="blue suggest" name="mark" href="../communitySupply/suggestMark.html?suggestMark=1&csId=${row.id }&pcId=${row.pcId}&gbId=${row.gbId}" >标为推荐</a></c:if>
			<c:if test="${row.gbId == row.pscGbId }"><a class="blue suggest" name="removemark" href="../communitySupply/suggestMark.html?suggestMark=0&csId=${row.id }&pcId=${row.pcId}&gbId=${row.gbId}">取消推荐</a></c:if>
		</display:column>
	</display:table>
	<!-- <div class="padb"><div id="addBusiness" class="info-btn mtop20 left"><a href="#">添加商家</a></div></div> -->
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript">	
$(".suggest").click(function(){
	var mark = $(this).attr("name");
	
	var info = "";
	if(mark=="mark"){
		info = '您确定要标为推荐？';
	}else{
		info= '您确定要取消推荐？';
	}
	
	if(window.confirm(info)){
		var suggestMarkURL = $(this).attr("href");
		var x=$(this);
		$.ajax({//使用ajax请求
			type: "GET",
			url: suggestMarkURL,
			async:false,
			success: function(data, textStatus){
		        alert("操作提示："+data);
		    
		        if(mark=="mark"){		        	
		        	x.attr("href",  suggestMarkURL.replace("suggestMark=1","suggestMark=0"));
		        	x.attr("name",   "removemark");
		        	x.html("取消推荐");
		        	x.parent("td").prev("td").html('<a class="blue alive" href="#">是</a>');
		        }else{
		        	x.attr("href",  suggestMarkURL.replace("suggestMark=0","suggestMark=1"));
		        	x.attr("name",   "mark");
		        	x.html("标为推荐");	
		        	x.parent("td").prev("td").html('<a class="grey asleep" href="#">否</a>');		        	
		        }
		        // textStatus 会返回 success
			},
		});
		return false;
	}else{//取消操作
		return false;
	}
});

</script>
</html>
