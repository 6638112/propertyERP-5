
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>业主托收信息编辑</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
	<form class="inputform" action="<%=basePath%>/bankCollection/ppBankCollectionInfoUpdate.html" method="post">
      <input type="hidden" class="input_text w220" name="id" value="${entity.id }"/>
      <h2>业主托收信息编辑</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right"> 小区：</td>
            <td>${entity.gbName }</td>
          </tr>
          <tr>
            <td width="120" align="right"> 楼栋：</td>
            <td>${entity.buildingName }</td>
          </tr>
          <tr>
            <td align="right"> 单元：</td>
            <td>${ entity.unitName}</td>
          </tr>
          <tr>
            <td align="right"> 房号：</td>
            <td>${entity.rrNum }</td>
          </tr>
          
          <tr>
            <td align="right"> 手机号：</td>
            <td>${entity.ppPhone }</td>
          </tr>
          <tr>
            <td align="right">房间编码：</td>
            <td><input type="text" class="input_text w220 pp" name="roomNo" value="${entity.roomNo }"/></td>
          </tr>
            <tr>
            <td align="right">托收银行卡号：</td>
            <td><input type="text" class="input_text w220 pp" name="bankAccount" value="${entity.bankAccount }"/></td>
          </tr>
          <tr>
            <td align="right">开卡人姓名：</td>
            <td><input type="text" class="input_text w220 pp" name="bankOwnerName" value="${entity.bankOwnerName }"/></td>
          </tr>
            <tr>
            <td align="right">开卡银行：</td>
            <td><input type="text" class="input_text w220 pp" name="bankName" value="${entity.bankName }"/></td>
          </tr>
        </table>
        <div class="padb mtop10"><input id="sumShopList" class="info-btn" type="submit" value="保存"/></div>
    </form>
</div>
</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
</html>
