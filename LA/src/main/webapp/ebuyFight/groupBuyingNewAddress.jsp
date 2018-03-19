<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>收货地址</title>
<link rel="stylesheet" href="css/groupbuying.css">
</head>

<body class="bggrey">
<form class="inputform">
<section class="sectionBox bggrey pb48">
    <section class="divide-box bordertbgrey"></section>
    <ul class="register-list">
        <li>
            <div class="displaybox">
                <div class="boxflex01 f16 grey"><input id="bankCardPersonInput" class="input-text wp100 f16" type="text" placeholder="收货人" value="" maxlength="100" datatype="*" nullmsg="请填写收货人！" /></div>
            </div>
        </li>
    </ul>
    <section class="divide-box bordertbgrey"></section>
    <ul class="register-list">
        <li>
            <div class="displaybox">
                <div class="boxflex01 f16 grey"><input name="" class="input-text wp100 f16" type="text" placeholder="手机号码" value="" maxlength="11" datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码格式！" /></div>
            </div>
        </li>
    </ul>
    <section class="divide-box bordertbgrey"></section>
    <div class="p010 bgwhite displaybox">
        <div class="item-standard-name f16 boxflex01 ptb20">
			<span>招东小区岗亭</span>
        	<div class="f14 grey mtop10">招商东路50号</div>
        </div>
    </div>
    <section class="divide-box bordertopgrey"></section>
    <ul class="bottom-menu-box displaybox t-center">
    	<li class="p00"><input class="btn-submit bgred noradius noborder btnSubmit height48 lineheight48" type="submit" name="submit" value="保存" /></li>
    </ul>
</section>
</form>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="${resourcePathHttps}/commonjs/Validform_v5.3.2.js"></script>

<script>
$(function(){
	//表单验证
	$(".inputform").Validform({
		tiptype:1,
		btnSubmit:".btnSubmit",
		ajaxPost:true,
		beforeSubmit:function(curform){
		},
		callback:function(data){
			window.location.href="groupBuyingAddressList.html";
		}
	});
	
	new FastClick(document.body);
})
</script>

</body>
</html>