<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业公告详情</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
</head>

<body>
<div class="info">
    <h2>物业公告详情</h2>
    <div class="bs-example bgebeb">
        <table class="info-list" border="0">
          <tr>
          	<td><div align="right">公告标题：</div></td>
          	<td colspan="5" class="bold">${msgDetail.title}</td>
          </tr>
          <tr>
            <td width="100" style="min-width:95px;"><div class="list-name">类别：</div></td>
            <td width="160">物业公告</td>
            <td width="100" style="min-width:95px;"><div class="list-name">推送小区：</div></td>
            <td width="160">深圳</td>
            <td>全部小区</td>
            <td></td>
          </tr>
        </table>
    </div>
    <h2 class="mtop40">公告内容</h2>
    <div class="divborder" style="_width:940px;">
    	${msgDetail.content }
    </div>
    <div class="mtop20">创建时间：<span class="grey">${msgDetail.sys0AddTime }</span><br />创建人：<span class="grey">${msgDetail.createOmsUser.realName }</span></div>
</div>
</body>
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.common.js"></script>
</html>
