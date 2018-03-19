<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报修管理-报修工单管理-关闭工单</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
	<form class="inputform" action="../propertyRepair/closeRepairSave.html">
		<input type="hidden" name="repairId" value="${pr.id}" />
        <h2>报修详情</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right">报修门牌号：</td>
            <td>${pr.address }</td>
          </tr>
          <tr>
            <td width="140" align="right">用户联系方式：</td>
            <td>${pr.tel }</td>
          </tr>
          <tr>
            <td align="right">创建时间：</td>
            <td>${pr.sys0AddTime }</td>
          </tr>
          <tr>
            <td align="right">报修类型：</td>
            <td>${pr.repairTypeName }</td>
          </tr>
          <tr>
            <td align="right"><span class="red">业主期望师傅上门时间：</span></td>
            <td>${fn:substring(pr.expectDate,0,10)} ${fn:substring(pr.expectTimeBegin,0,5)}</td>
          </tr>
          <tr>
            <td align="right">问题描述：</td>
            <td>${pr.repairContent }</td>
          </tr>
        </table>
        
        <h2 class="mtop20">关闭工单</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="140" align="right">修改工单状态：</td>
            <td><select class="select_normal">
                    <option>已结束</option>
                    </select></td>
          </tr>
          <tr>
            <td align="right">处理说明：</td>
            <td><input name="finishDesc" type="text" class="input_text w500"><br /><span class="grey">处理说明会显示在解放区APP上，提交报修的业主可见。</span></td>
          </tr>
        </table>
        <div class="padb mtop10"><input class="info-btn checkRepair" type="submit" value="提 交" /></div>
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
