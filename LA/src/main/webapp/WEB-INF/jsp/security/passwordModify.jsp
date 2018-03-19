<%@page import="com.cnfantasia.server.ms.pub.session.UserContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>重置密码</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body style="height:auto;">

    <div class="info" style="min-height:700px">
        <form class="inputform" method="post">
        <h2>重置密码</h2>
        <div class="distr">
            <div class="bs-example">
                <div class="add-user">
                    <table border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>您的帐号：</td>
                        <td> <% out.print(UserContext.getCurrUser().getUserAccount()); %></td>
                      </tr>
                      <tr>
                        <td><Span class="red">*</Span> 旧密码：</td>
                        <td><input id="oldPassword" type="password" class="input_text pp" value="" name="oldPassword" datatype="s6-20" nullmsg="请输入旧密码！"></td>
                      </tr>
                      <tr>
                        <td><Span class="red">*</Span> 新密码：</td>
                        <td><input type="password" class="input_text pp" value="" name="password" datatype="s6-20" nullmsg="请输入新密码！" errormsg="密码至少由6位字符组成！"></td>
                      </tr>
                      <tr>
                        <td><Span class="red">*</Span> 确认新密码：</td>
                        <td><input id="newPassword" type="password" class="input_text pp" value="" name="repassword" datatype="s6-20" recheck="password" nullmsg="请再次输入密码！" errormsg="您两次输入的账号密码不一致！"></td>
                      </tr>
                      <tr>
                        <td></td>
                        <td><input id="submitBtn" class="info-btn" style="margin:0 0 20px;" type="submit" value="确 定" /></td>
                      </tr>
                    </table>
                </div>
            </div>
        </div>
        </form>
    </div>

</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
(function($){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		callback:function(data){
			var saveNewPasswordURL = "../omsUser/saveNewPassword.jsp";
			var oldPassword = $("#oldPassword").val();
			var newPassword = $("#newPassword").val();
			
			$.ajax({//使用ajax请求
				type: "POST",
				url: saveNewPasswordURL,
				async:true,
				data: {oldPassword:oldPassword,password:newPassword,},
				success: function(data, textStatus){
			        alert("操作提示："+data);
				},
			}); 
			
			return false;
		},
	});
    
})(jQuery);
</script>
</html>
