<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限-角色管理-人员</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>
<div class="info">
    <h2>角色管理-运营人员管理</h2>
    <div class="distr">
        <div class="bs-example">
            <ul class="ranking">
            	<li>增加人员： <input type="text" class="input_text" value="请输入帐号或姓名"></li>
                <li><input class="input-btn" type="button" value="确定"></li>
            </ul>
            <table class="ranking-info mtop20" border="0" cellspacing="0" cellpadding="0">
              <tr class="nobg">
                <td class="bold">选择</td>
                <td class="bold">帐号</td>
                <td class="bold">姓名</td>
                <td class="bold">帐号描述</td>
              </tr>
              <tr>
                <td><input name="" type="checkbox" value=""></td>
                <td>zhangqian</td>
                <td>张茜茜</td>
                <td class="grey">管家产品</td>
              </tr>
              <tr>
                <td><input name="" type="checkbox" value=""></td>
                <td>zhouhaibo</td>
                <td>周bo波</td>
                <td class="grey">街坊产品</td>
              </tr>
              <tr>
                <td><input name="" type="checkbox" value=""></td>
                <td>limengmeng</td>
                <td>李萌萌</td>
                <td class="grey">超市产品</td>
              </tr>
            </table>

      	</div>
    </div>
    <div class="padb"><div class="info-btn mtop20 left"><a href="#">删除所选</a></div></div>
</div>
</body>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.common.js"></script>
</html>