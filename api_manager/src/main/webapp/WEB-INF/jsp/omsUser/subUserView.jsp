<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限-子账号管理-查看账号</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
    <h2>账号配置</h2>
    <div class="distr">
        <div class="bs-example">
        	<input type="hidden" name="subUserId" value="${omsUserBean.id }" />
            <div class="add-user">
                <table border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td align="right">账号名称：</td>
                    <td><input name="userName" maxlength="15" type="text" class="input_text pp" value="${omsUserBean.realName }" datatype="s2-10" nullmsg="请填写账号名称！" errormsg="账号名称请录入2到10个字符！"></td>
                  </tr>
                  <tr>
                    <td align="right">联系方式：</td>
                    <td><input name="telPhone" type="text" class="input_text pp" value="${omsUserBean.telPhone }"></td>
                  </tr>
                  <tr>
                    <td align="right">账号：</td>
                    <td><input readonly="readonly" maxlength="15" type="text" class="input_text pp" value="${omsUserBean.userAccount }" datatype="EnglishOrNumber" nullmsg="请填写账号！" errormsg="账号由字符或数字组成！"></td>
                  </tr>
                </table>
            </div>
            <br/>
           
 			<h2>角色分配</h2>
            <display:table name="omsRoles" id="row" class="mars info-list-02 mtop20" >
				<display:column title="选择">
					<!-- 请注意：sys0DelState字段被借用来表示是否被选中过 -->
					<input type="checkbox" value="${row.id }" name="roleId" <c:if test="${row.sys0DelState ==1}">checked="checked"</c:if> />
				</display:column>
				<display:column title="角色名称" property="name" sortable="true"/>
				<display:column title="角色描述" property="desc"></display:column>				
			</display:table>

      	</div>
    </div>
    <input onclick="history.back()" class="info-btn" type="button" value="返回" /> 
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
</html>