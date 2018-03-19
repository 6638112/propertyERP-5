<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>店主认证</title>
<link rel="stylesheet" href="../css/merchant/shopping.common.css">
</head>

<body class="bggrey">
<form class="inputform" name="inputform" id="inputform"  method="POST" enctype="multipart/form-data" onsubmit="return false;">
<header class="sectionBox fantasia-header order-top-bg">
	<a class="disblock mleft15 left" href="setting.html"><img class="back-arrow" src="../images/merchant/back-arrow.png" /></a>
    <div class="header-title">店主认证</div>
</header>
<section class="sectionBox">
    <section class="divide-box bordertbgrey"></section>
    <div class="list-box bgwhite">
        <div class="displaybox">
            <div class="item-standard-name height36 f16 boxflex01">店主姓名</div>
	        <div class="boxflex01 f16 t-right grey"><input class="input-text wp100 t-right" type="text" placeholder="如：张三" value="" datatype="*" nullmsg="请输入店主姓名！" name="shopkeepername" /></div>
        </div>
    </div>
    <section class="divide-box bordertbgrey"></section>
    <div class="list-box bgwhite">
        <div class="displaybox">
            <div class="item-standard-name height36 f16 boxflex01">手机号码</div>
            <div class="boxflex01 f16 t-right grey"><input class="input-text wp100 t-right" type="text" maxlength="11" placeholder="如：13566668888" value="" datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码格式！"  name="phoneNum"/></div>
        </div>
    </div>
    <section class="sectionBox password-mind-box bordertbgrey">
        <div class="mleft15">身份证件</div>
    </section>
    <ul class="register-list borderbottomgrey">
        <li class="overvisible">
            <div class="uploadPreview01"><input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="photoimage" class="uploadImage01 fimg1 w100 height100" /></div>
	        <div class="uploadPreview01 img-new-add img-hide"><div class="img-delete-btn">删除</div><input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="photoimage" class="uploadImage01 fimg1 w100 height100" /></div>
			<div class="uploadPreview01 img-add-btn"></div>
		<input class="uploadImageVal01" hidden="true" type="text" name="text" datatype="*" nullmsg="请上传身份证件！"></li>
        <li class="img-add-num grey">注：不超过10M，最多可上传3张</li>
    </ul>
    
    <section class="divide-box pb56"></section>
    <ul class="bottom-menu-box displaybox t-center">
        <li class="p00"><input class="btn-submit btn-next noradius bordertbgrey btnSubmit red" type="button" name="button" value="提交审核"></li>
    </ul>
	</section>
</form>
<section class="pop-tips2 upload-erro hide">
	<div class="pop-tips-text">系统异常，请联系管理员！…</div>
</section>
<section class="pop-tips1 upload-save hide">
	<div class="pop-tips-text">提交成功<br>我们将在3个工作日内完成审核！</div>
</section>
<section class="pop-tips3 upload-text hide">
	<div class="pop-tips-text">信息提交中，请稍后…</div>
</section>
<section class="pop-tips3 upload-login hide">
	<div class="pop-tips-text">未登录，即将跳转！</div>
</section>
<script src="../js/merchant/jquery-1.11.2.min.js"></script>
<script src="../js/merchant/fastclick.js"></script>
<script src="../js/merchant/imgUpload.js"></script>
<script src="../js/merchant/Validform_v5.3.2.js"></script>
<script src="../js/jquery.form.js"></script>
<script>
$(function(){
	new FastClick(document.body);
});
$(function(){
	$(".inputform").Validform({
		url:"../ebuyMerchant/saveShopkeeper.html",
		tiptype:1,
		dragonfly:true,
		btnSubmit:".btnSubmit",
		beforeSubmit:function(){
			$('.upload-text').removeClass('hide');
		},
		callback:function(data){
			$("#inputform").ajaxSubmit(function(data) {	
				if (data.status == '0000') {
					$('.upload-text').addClass('hide');
					$('.upload-save').removeClass('hide');
					setTimeout(function(){
						location.href="<%=basePath%>/ebuyMerchant/setting.html";
					},2000);
				}else if(data.status == '0001'){
					$('.upload-text').addClass('hide');
					$('.upload-login').removeClass('hide');
					setTimeout(function(){
						location.href="<%=basePath%>/ebuyMerchant/login.html";
					},1000);
				}else{
					$('.upload-text').addClass('hide');
					$('.upload-erro').removeClass('hide');
					$("input").val("");
				}
			});
		}
	});
});
</script>


</body>
</html>