<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报修管理-增加报修类型</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
	<form class="inputform" action="../propertyRepair/addNewRepairerTypeSave.html" method="post">
        <h2>修改报修类型</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="120" align="right"><Span class="red">*</Span> 报修类型：</td>
            <td><input type="text" class="input_text pp" name="prtName" value="${prt.name }" datatype="*" nullmsg="请填写报修类型！" ></td>
          </tr>
          <tr>
            <td width="120" align="right"><Span class="red">*</Span> 管理处：</td>
            <td>
            	<select name="pmId" >
            		<c:forEach items="${pmList}" var="pm">
            			<c:choose>
            				<c:when test="${prt.tPropertyManagementFId == pm.id }">
			            		<option value="${pm.id }" selected="selected" >${pm.name }</option>
            				</c:when>
            				<c:otherwise>
			            		<option value="${pm.id }"> ${pm.name }</option>
            				</c:otherwise>
            			</c:choose>
            		</c:forEach>
            	</select>
            </td>
          </tr>
        </table>
        <div class="padb mtop10"><input class="info-btn manageRepairType" type="submit" value="保 存" /></div>
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
		tiptype:3
	});
});
</script>

</html>
