<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>神码行动-邀请人配置管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript">
	function setConfig(id,type){
		var reqUrl = "../inviteReward/enableConfig.html";
		if(type=='disable'){
			reqUrl = "../inviteReward/disableConfig.html";
		}
		$.ajax({//使用ajax请求删除数据
			type:"GET",
			url:reqUrl,
			async:true,
			data:{id:id,},
			success:function(data, textStatus){
		        alert("操作提示："+data);
				$(window.parent.document).find("#mainFrame").attr('src', "<%=basePath%>/inviteReward/search.html");
			},
		}); 
	}
</script>
</head>

<body>
<div class="info">
    <h2>邀请人配置管理</h2>
    <div class="bs-example bgebeb">
    	<form method="post" action="../inviteReward/search.html">
	        <table class="info-list" border="0">
	          <tr>
	            <td><div align="right">解放号：</div></td>
	            <td><input type="text" value="${param.inviteUserId }" class="input_text pp w120" name="inviteUserId" /></td>
	            <td><div align="right">邀请人：</div></td>
	            <td><input type="text" value="${param.inviteUserName }" class="input_text pp w120" name="inviteUserName" /></td>
	            <td><div align="right">邀请手机号：</div>
	            </td>
	            <td>
	          		<input type="text" class="input_text pp w120" value="${param.inviteCode }" name="inviteCode" />
	            </td>
	            <!-- <td><div align="right">邀请类别：</div></td>
	            <td>
					<select name="inviteType" class="select_normal w131">
	                    <option value="">全部</option>
	                    <option value="1">解放区</option>
	                    <option value="2">物业</option>
	                    <option value="3">商家</option>
                    </select>
				</td> -->
				<td></td>
	            <td>
	            	<input class="input-btn w80" type="submit" value="搜索">
	            	<input class="input-btn w80" type="button" onclick="$(window.parent.document).find('#mainFrame').attr('src', '../inviteReward/initEdit.html');" value="新增">
	            </td>
	            <td></td>
	            <td></td>
	          </tr>
	        </table>
    	</form>
    </div>
    
    <display:table name="resList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
		<display:column title="邀请人解放号" sortable="true">
			<a class="blue" href="../inviteReward/view.html?id=${row.id}">${row.tInviteUserFId}</a> 
		</display:column>
		<display:column title="邀请人名称">
			<c:if test="${row.inviteUser.nickName!=null}">${row.inviteUser.nickName }</c:if>
			<c:if test="${row.inviteUser.nickName==null}">${row.inviteUser.realName }</c:if>
		</display:column>
		<display:column title="邀请手机号" property="inviteCode" />
		<display:column title="邀请人类型">
			<c:if test="${row.inviteType ==1 }">解放区</c:if>
			<c:if test="${row.inviteType ==2 }">物业</c:if>
			<c:if test="${row.inviteType ==3 }">商家</c:if>
		</display:column>
		<display:column title="当前状态">
			<c:if test="${row.sys0DelState ==0 }">已启用</c:if>
			<c:if test="${row.sys0DelState ==1 }">已禁用</c:if>
		</display:column>
		<display:column title="操作" >
			<a class="blue" name="view" href="../inviteReward/view.html?id=${row.id }" >查看</a>
			<a class="blue" name="edit" href="../inviteReward/initEdit.html?id=${row.id }" >编辑</a>
			<c:if test="${row.sys0DelState ==1 }">
				<a class="blue" name="edit" onclick="return confirm('您确定要启用吗?');" href="javascript:setConfig(${row.id },'enable')" >启用</a>
			</c:if>
			<c:if test="${row.sys0DelState ==0 }">
				<a class="blue" name="edit" onclick="return confirm('您确定要禁用吗?');" href="javascript:setConfig(${row.id },'disable')" >禁用</a>
			</c:if>
		</display:column>
	</display:table>
</div>
</body>
</html>
