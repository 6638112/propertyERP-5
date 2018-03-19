<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限-用户管理-新增帐号</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
    <h2>新增帐号</h2>
        <form class="inputform" name="saveForm" action="<%=basePath%>/omsUser/save.html" method="post">
    <div class="distr">
        <div class="bs-example">
        	<input type="hidden" name="id" value="${omsUserBean.id }" />
            <div class="add-user">
                <table border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td align="right"><Span class="red">*</Span> 帐号：</td>
                    <td><input name="userAccount" maxlength="15" type="text" class="input_text pp" value="${omsUserBean.userAccount }" datatype="*4-16" nullmsg="请填写帐号！" errormsg="帐号由4到16位字符组成！"></td>
                  </tr>
                 <%--  <tr>
                    <td align="right"><Span class="red">*</Span> 密码：</td>
                    <td><input name="password" type="password" class="input_text pp" value="${omsUserBean.password }" datatype="s6-20" nullmsg="请输入密码！" errormsg="密码至少由6位字符组成！"></td>
                  </tr>
                  <tr>
                    <td align="right"><Span class="red">*</Span> 再次输入密码：</td>
                    <td><input name="repassword" type="password" class="input_text pp" value="${omsUserBean.password }" datatype="s6-20" recheck="password" nullmsg="请再次输入密码！" errormsg="您两次输入的账号密码不一致！"></td>
                  </tr> --%>
                  <tr>
                    <td align="right">姓名：</td>
                    <td><input name="realName" type="text" class="input_text pp" value="${omsUserBean.realName }"></td>
                  </tr>
                  <tr>
                    <td align="right">联系方式：</td>
                    <td><input type="text" class="input_text pp" value=""></td>
                  </tr>
                  <tr>
                    <td align="right">邮箱：</td>
                    <td><input type="text" class="input_text pp" value=""></td>
                  </tr>
                  <tr>
                    <td align="right">账号描述：</td>
                    <td><input type="text" class="input_text pp" value=""></td>
                  </tr>
                  <tr>
                    <td align="right">是否后台管理账号：</td>
                    <td>
                  		<input type="checkbox" name="isadmin" class="input_text pp"
	                    	<c:if test="${omsUserBean.isadmin ==1 }">
	                    	 	checked="checked" 
	                    	</c:if> />
                    </td>
                  </tr>
                  <tr>
                    <td align="right">邀请码：</td>
                    <td><input type="text" class="input_text pp" name='inviteCode' value="${omsUserBean.inviteCode }"></td>
                  </tr>
                </table>
            </div>
            <br/>
            <h2>账号角色</h2>
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
    <input id="sumOmsUser" class="info-btn" type="submit" value="提 交"/> 
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		beforeCheck:function(){
			//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
			//这里明确return false的话表单将不会提交;	
			return validateUserName();
		},
		beforeSubmit:function(curform){
			//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
			//这里明确return false的话表单将不会提交;	
			if(validateUserName() && (!validateRoleId())) {
				alert("请选择账号角色");
			}
			return validateRoleId();
		},
	});
});

function validateRoleId() {
	var isChecked = false;
	$('input[name=roleId]').each(function(){
		if($(this).is(':checked')) {
			isChecked = true;
		}
	});
	return isChecked;
}

//验证用户是否存在
function validateUserName() {
	ths = $('input[name=userAccount]');
	// 1获取文本框的内容
	var userName = ths.val();
	var id = $('input[name=id]').val();
	
	// 2将获取到的内容发送给服务器的servlet
	//使用jquery的XMLHTTPRequest对象get请求的封装
	var isExistUser = false;
	$.ajax({
	    type:"POST",   
		dataType:"text",
		async:false,
	    url:"../omsUser/validUserName.html", //发送给服务器的url
	    data:"omsUserName="+userName+"&id="+id, //发送给服务器的参数
	    success:function(data, textStatus){ 
	        if(data==="已经存在该账号，请重新输入"){
	      	  ths.siblings(".Validform_checktip").attr("class", "Validform_checktip Validform_wrong").text("已经存在该账号，请重新输入");
	      	  ths.focus();
	      	  isExistUser = true;
	        }
	    },
	});

	return !isExistUser;
}
</script>
</html>