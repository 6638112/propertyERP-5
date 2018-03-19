<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业公司-审核后的查看界面</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
    
        <h2>账号查看</h2>
        <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="145" align="right"><Span class="red">*</Span> <span class="grey">物业公司名称：</span></td>
            <td> <c:out value="${pc.name }"></c:out></td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> <span class="grey">手机号码：</span></td>
            <td>${pc.mobilePhone }</td>
          </tr>
          <tr>
            <td align="right"><span class="grey">联系人：</span></td>
            <td>${pc.linkman }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> <span class="grey">联系邮箱：</span></td>
            <td>${pc.email }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red"></Span> <span class="grey">后台账号：</span></td>
            <td>${omsUser.userAccount }</td>
          </tr>
          <tr>
            <td align="right"><span class="grey">上传的营业执照：</span></td>
            <td><ul class="menu-img"><li><a href="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) %>${pc.photoBusinessLicense }" rel="clearbox[test2]"><img src="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) %>${pc.photoBusinessLicense }" border="0" /></a></li></ul></td>
          </tr>
          <tr>
            <td align="right"><span class="grey">上传的小区管理资质证明：</span></td>
            <td><ul class="menu-img"><li><a href="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) %>${pc.photoCredentials }" rel="clearbox[test2]"><img src="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) %>${pc.photoCredentials }" border="0" /></a></li></ul></td>
          </tr>
          <tr>
            <td align="right" class="bold">所管辖小区：</td>
            <td></td>
          </tr>
          <c:forEach items="${gbrList}" var="gbr">
	          <tr>
	            <td align="right"><span class="grey">小区信息：</span></td>
	            <td>${gbr.gbrName }（${gbr.apName }&nbsp;&nbsp;${gbr.acName }&nbsp;&nbsp;${gbr.abName }&nbsp;&nbsp;${gbr.gbrAddressdesc }）</td>
	          </tr>
          </c:forEach>
        </table>
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
		tiptype: 3,
		ignoreHidden: true,
		beforeSubmit:function(curform){
			//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
			//这里明确return false的话表单将不会提交;
			return true;	
		}
	});
});
</script>
</html>
