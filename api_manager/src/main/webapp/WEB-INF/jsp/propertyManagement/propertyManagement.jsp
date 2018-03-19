
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业新增管理处</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/jRating.jquery.css" />
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
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
</head>

<body>
<div class="info">
    <form class="inputform" method="post" action="../propertyCompany/saveManagement.html">
    	<input type="hidden" name="managementId" value="${mgt.id }" />
        <h2>管理处基本信息</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="20%"><span class="red">*</span>管理处名称：</td>
            <td><input type="text" name="managementName" class="input_text input_noborder pp w80" value="${mgt.name }" datatype="*3-50" nullmsg="请输入管理处名称！" /></td>
          </tr>
          <tr>
            <td><span class="red">*</span>管理处电话 ：</td>
            <td>
                <input type="text" name="managementTel" class="input_text input_noborder pp w80" value="${mgt.tel }" datatype="t" nullmsg="请输入管理处电话！" />
            </td>
          </tr>
          <tr>
            <td><span class="red">*</span>管理处账号：</td>
            <td>
                <input type="text" name="userAccount" class="input_text input_noborder pp w80" value="${mgt.userAccount }" datatype="*5-15" nullmsg="请输入管理处账号！" />
            </td>
          </tr>
          <tr>
            <td><span class="red">*</span>密码：</td>
            <td>
                <input type="text" name="password" class="input_text input_noborder pp w80" value="${mgt.password }" datatype="*6-15" nullmsg="请输入密码！" />
            </td>
          </tr>
          <tr>
            <td><span class="red">*</span>确认密码：</td>
            <td>
                <input type="text" name="password2" class="input_text input_noborder pp w80" value="${mgt.password }" datatype="*6-15" nullmsg="请输入确认密码！" />
            </td>
          </tr>
        </table>
        <input id="sumCoperationApply" class="info-btn" type="submit" value="提 交" />
    </form>
</div>

</body>
</html>
