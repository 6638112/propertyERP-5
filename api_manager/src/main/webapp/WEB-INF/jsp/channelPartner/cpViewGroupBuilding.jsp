<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>合伙人管理-合伙人资料-查看管辖小区</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
	<form class="inputform">
        <h2>查看管辖小区</h2>
        
        <display:table name="resList" id="row" class="info-list-02 mtop20"	>
				<display:column title="小区名称" property="name" />
				<display:column title="省" property="apName" />
				<display:column title="市" property="acName" />
				<display:column title="区" property="abName" />
				<display:column title="详细地址" property="addressDesc"/>
				<display:column title="审核状态" >
					<c:choose>
						<c:when test="${(empty row.auditstatus) or (row.auditstatus==0) }">待审核</c:when>
						<c:when test="${row.auditstatus==1}">审核通过</c:when>
						<c:when test="${row.auditstatus==2}">审核失败</c:when>
					</c:choose>
				</display:column>
				<display:column title="是否已签约" property="signStatus"/>
				 <% if(UserContext.getCurrUser().getIsadmin()==0){ //管理员不需要操作按钮 %>
        			<display:column title="操作" >
						<c:if test="${(empty row.auditstatus) or (row.auditstatus==0) or (row.auditstatus==2) }">
							<a href="../channelPartner/initGroupBuildingRegisterEdit.html?gbrId=${row.id}&cprId=${row.tChannelPartnerRecommendFId}" class='blue' >编辑</a>
							<a href='#' class='blue removeGbr' id='${row.id }'>删除</a>
						</c:if>
					</display:column>
       	 		<%} %>
		</display:table>
        
        <div class="padb mtop10"><input class="info-btn coperView" type="button" onclick="window.location.href='../channelPartner/cpCompanyView.html'" value="返回" />&nbsp;&nbsp;
        <% if(UserContext.getCurrUser().getIsadmin()==0){ //管理员不需要新增按钮 %>
        		<input id="createCoper" class="info-btn" type="button" value="新增小区" onclick="window.location.href='../channelPartner/initGroupBuildingSupplement.html?&id=${cprId}'" />
        <%} %>
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript">
	$(function(){
		$('a.removeGbr').click(function(){
			if(window.confirm("确定要删除吗？")){
				var $a = $(this);
				$.get(
						'../channelPartner/deleteGbrById.html?id='+$a.attr('id'), 
						function(data,status){
							if(data==='删除成功'){
								alert('删除成功');
								$a.parents('tr').remove();
							}else{
								alert('删除失败');
							}
				}); 
			}
		});	
	});
</script>
</html>
