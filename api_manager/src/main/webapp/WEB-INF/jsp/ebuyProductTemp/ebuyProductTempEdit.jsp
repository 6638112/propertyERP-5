<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
    <form class="inputform" action="../roomValidate/saveAuditResult.html" >
    <h2>门牌信息</h2>
    <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
      <tr>
        <td align="right"><span class="grey">用户ID：</span></td>
        <td>${rvInfo.userId }</td>
      </tr>
      <tr>
        <td align="right"><span class="grey">用户姓名：</span></td>
        <td>${rvInfo.userRealName }</td>
      </tr>
      <tr>
        <td align="right"><span class="grey">用户手机号：</span></td>
        <td>${rvInfo.userMobile }</td>
      </tr>
      <tr>
        <td align="right"><span class="grey">用户注册门牌：</span></td>
        <td>${rvInfo.rvDesc}</td>
      </tr>
      <tr>
        <td align="right"><span class="grey">用户上传文件：</span></td>
        <td><ul class="menu-img"><li><a href="<%=OmsSysParamManager.getImageServerUrl("/roomValidatePic/")%>/roomValidatePic/${rvInfo.picURL }" rel="clearbox[test2]"><img src="<%=OmsSysParamManager.getImageServerUrl("/roomValidatePic/") %>/roomValidatePic/${rvInfo.picURL }" border="0" /></a></li></ul></td>
      </tr>
    </table>
    <h2 class="mtop40">审核</h2>
	    <div class="bs-example">
	    		<input type="hidden" name="rvId" value="${rvInfo.rvId }"/>
	    		<input type="hidden" name="rId" value="${rvInfo.rId }"/>
	    		<input type="hidden" name="rrId" value="${rvInfo.rrId }"/>
	    		<input type="hidden" name="rrId" value="${rvInfo.userId }"/>
		        <table class="info-list" border="0">
		          <tr>
		            <td width="90" align="right">审核结果：</td>
		            <td colspan="5"><select class="select_normal w131 select-check" name="auditResult">
		                        <option value="pass">通过</option>
		                        <option value="notpass">不通过</option>
		                        </select></td>
		          </tr>
		          <tr class="dsn">
		            <td align="right">原因：</td>
		            <td colspan="5"><textarea class="textareas txt02" name="auditNotPassResultDesc" cols="" rows="5" datatype="*4-100" nullmsg="审核结果为“不通过”时需填写原因！" errormsg="原因范围为4到100个字！"></textarea></td>
		          </tr>
		        </table>
	    </div>
	    <div class="padb height240"><input id="sumAddresses" class="info-btn" type="submit" value="提 交" /></div>
    </form>
</div>
</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		ignoreHidden: true
	});
});
</script>
</html>
