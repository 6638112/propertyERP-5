
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" deferredSyntaxAllowedAsLiteral="true" %>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>神码行动-邀请人配置详情</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
	<form class="inputform">
		<input type="hidden" name="supplyId" value="${entity.id }" />
        <h2>邀请人配置信息</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right">解放号:</td>
            <td>${entity.tInviteUserFId }</td>
          </tr>
          <tr>
            <td align="right">邀请人:</td>
            <td>
            	<c:if test="${entity.inviteUser.nickName!=null}">${entity.inviteUser.nickName }</c:if>
				<c:if test="${entity.inviteUser.nickName==null}">${entity.inviteUser.realName }</c:if>
            </td>
          </tr>
          <tr>
            <td width="120" align="right">邀请手机号：</td>
            <td>${entity.inviteCode}</td>
          </tr>
           <tr>
            <td align="right" rowspan="2">邀请人类型：</td>
            <td>
            	<c:if test="${entity.inviteType ==1 }">解放区</c:if>
				<c:if test="${entity.inviteType ==2 }">物业</c:if>
				<c:if test="${entity.inviteType ==3 }">商家</c:if>
            </td>
          </tr>
	    </table>
	    <br/>
	    <h2>邀请注册新人信息</h2>
        <display:table name="registerList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="registerSize">
        	<display:column title="解放号">${row.tRegisterUserFId}</display:column>
        	<display:column title="昵称">${row.registerNickName}</display:column>
        	<display:column title="真实名称">${row.registerRealName}</display:column>
        	<display:column title="注册手机">${row.registerMobile}</display:column>
        	<display:column title="性别">
        		<c:if test="${row.registerSex =='1' }">女</c:if>
        		<c:if test="${row.registerSex =='0' }">男</c:if>
        	</display:column>
        	<display:column title="是否已登录">
        		<c:if test="${row.registerIslogin ==1 }">已登录</c:if>
        		<c:if test="${row.registerIslogin ==0 }">未登录</c:if>
        	</display:column>
        	<display:column title="注册时间">${row.registerTime}</display:column>
        </display:table>
        <div class="padb mtop10">
        	<input id="sumShopList" class="info-btn" type="button" value="返 回" onclick="history.back();"/>
        </div>
    </form>
</div>
</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
</html>
