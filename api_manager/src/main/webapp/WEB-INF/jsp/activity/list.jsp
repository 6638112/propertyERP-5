<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>活动列表</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
	</head>
	<body>
		<div class="info">
		    <h2>活动列表</h2>
		    <display:table name="activities" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="total" >
				<display:column style="text-align:center;" headerClass="t_center" title="标题" property="tittle"/>
				<display:column style="text-align:center;" headerClass="t_center" title="优先级">
					<input id="order" name="order" value="${row.order}" maxlength="10" style="color:#FF0000;" class="input_text pp w250"/>
					<input type="button" class="input-btn" value="保存" onclick="saveOrder(this, ${row.id}, '${row.tittle}')"/>
				</display:column>
				<display:column style="text-align:center;" headerClass="t_center" title="状态">
					<div align="center">
						<c:choose>
							<c:when test="${row.sys0DelState eq 1}">
								<a class="blue" href="javascript:void(0);" onclick="updateState(${row.id}, 0, '${row.tittle}')">启用</a>
								<span class="grey">|</span> 
								<span class="black">停用</span> 
							</c:when>
							<c:otherwise>
								<span class="black">启用</span> 
								<span class="grey">|</span> 
								<a class="blue" href="javascript:void(0);" onclick="updateState(${row.id}, 1, '${row.tittle}')">停用</a>
							</c:otherwise>
						</c:choose>
					</div>
				</display:column>
				<display:column style="text-align:center;" headerClass="t_center" title="操作">
					<a class="blue" href="${pageContext.request.contextPath}/activity/detailPage.html?id=${row.id}&pageType=update">编辑</a>
					<a class="blue" href="${pageContext.request.contextPath}/activity/detailPage.html?id=${row.id}&pageType=query">查看</a>
				</display:column>
			</display:table>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript">
		// 更新优先级
		function saveOrder(o, id, title){
			var order = $.trim($(o).parent().find("#order").val());
			if(order==""){
				alert("优先级不能为空！");
			} else {
				var reg = new RegExp("^[1-9][0-9]*$");
				if(!reg.test(order)){  
			        alert("请输入正整数!");  
			    } else if(confirm("您确定要更新【"+title+"】的优先级吗？")){
					$.post("${pageContext.request.contextPath}/activity/updateOrder.html", {"id":id, "order":order}, function(data){
						alert(data.message);
						if(data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>') {
							location = location;
						}
					});
				}
			}
		}
		
		// 启用、停用
		function updateState(id, state, tittle){
			var msg;
			if(state==0){
				msg = "您确定要启用【"+tittle+"】吗？";
			} else {
				msg = "您确定要停用【"+tittle+"】吗？";
			}
			if(confirm(msg)){
				$.post("${pageContext.request.contextPath}/activity/updateState.html", {"id":id, "state":state}, function(data){
					alert(data.message);
					if(data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>') {
						location = location;
					}
				});
			}
		}
	</script>
</html>