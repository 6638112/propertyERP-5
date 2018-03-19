<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">

<title>会员注册</title>
<link rel="stylesheet" href="../css/common.css">
</head>

<body class="bggrey">
<header class="fantasia-header borderbottomgrey">
	<a class="disblock p010 left" href="#"><img class="back-arrow" src="../images/back-arrow.png" /></a>
    <div class="header-title">会员注册</div>
</header>
<section class="divide-box borderbottom"></section>
<section class="sectionBox borderbottomgrey">
	<ul class="register-list">
    	<li class="borderbottomgrey"><input class="input-text w150 left" type="text" name="phoneNum" value="请输入手机号码" /><input class="input-btn right grey btn-on" type="button" name="button" value="获取验证码" /></li>
        <li><input class="input-text w150" type="text" name="phoneNum" value="请输入验证码" /></li>
    </ul>
</section>
<section class="sectionBox bggrey">
    <div class="m010 mtop20"><input class="btn-submit bgred" type="submit" name="submit" value="确 定" /></div>
</section>
</body>
</html>