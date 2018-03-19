<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>维修师傅查看界面</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
    
        <h2>维修师傅信息</h2>
        <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="145" align="right"><Span class="red">*</Span> <span class="grey">申请编号：</span></td>
            <td> <c:out value="${dw.dwId }"></c:out></td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> <span class="grey">解放号：</span></td>
            <td>${dw.huaId }</td>
          </tr>
          <tr>
            <td align="right"><span class="grey">手机号：</span></td>
            <td>${dw.mobile }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> <span class="grey">真实姓名：</span></td>
            <td>${dw.realName }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> <span class="grey">身份证号：</span></td>
            <td>${dw.idNumber}</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> <span class="grey">申请日期：</span></td>
            <td>${dw.registTime }</td>
          </tr>
 		 <tr>
            <td align="right"><Span class="red">*</Span> <span class="grey">服务类型：</span></td>
            <td><c:if test="${fn:length(dw.dredgeTypeNames)>0 }">${dw.dredgeTypeNames } </c:if> </td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> <span class="grey">服务范围：</span></td>
            <td><c:if test="${fn:length(dw.blockNames)>0 }">${dw.blockNames } </c:if></td>
          </tr>
          <tr>
            <td align="right"><span class="grey">身份证照片：</span></td>
            <td><ul class="menu-img"><li><a href="${dw.idPic1 }" rel="clearbox[test2]"><img src="${dw.idPic1 }" border="0" /></a></li></ul>
            <ul class="menu-img"><li><a href="${dw.idPic2 }" rel="clearbox[test2]"><img src="${dw.idPic2 }" border="0" /></a></li></ul></td>
          </tr>
          <tr>
            <td align="right" class="bold">审核说明：</td>
            <td>${dw.auditDesc }</td>
          </tr>
          <tr>
            <td align="right" class="bold">服务星级：</td>
            <td>${dw.starLevel }</td>
          </tr>
        </table>
		<div class="padb"><input type="button" class="info-btn" onclick="history.back();" value="返回" /></div>
</div>

</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js?2016-05-20"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		tiptype: 3,
		ignoreHidden: true,
	});
});

</script>
</html>
