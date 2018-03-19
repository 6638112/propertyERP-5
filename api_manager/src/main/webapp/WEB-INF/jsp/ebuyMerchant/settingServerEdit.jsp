<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

<title>客户服务</title>
<link rel="stylesheet" href="../css/merchant/shopping.common.css">
</head>

<body class="bggrey">
<form id="inputform" class="inputform" action="saveServiceInfo.html" onsubmit="return false;">
<header class="sectionBox fantasia-header order-top-bg">
	<a class="disblock mleft15 left" href="setting.html"><img class="back-arrow" src="../images/back-arrow.png" /></a>
    <div class="header-title">客户服务</div>
</header>
<section class="sectionBox">
    <section class="divide-box bordertbgrey"></section>
    <div class="list-box bgwhite">
        <div class="displaybox">
            <div class="item-standard-name height36 f16 boxflex01">客服电话</div>
            <div class="boxflex01 f16 t-right grey"><input name="tel" class="input-text wp100 t-right" type="text" maxlength="13" placeholder="请输入手机或座机" value="${merchantSupply.tel}" datatype="phoneNumber|m" nullmsg="请输入客服电话！" errormsg="请输入手机号或座机号"  /></div>
        </div>
    </div>
    <section class="divide-box bordertbgrey"></section>
    <ul class="register-list borderbottomgrey">
        <li class="borderbottomgrey">
            <div class="displaybox">
                <div class="item-standard-name height36 f16 boxflex01">营业时间</div>
                <div class="boxflex01 f16 t-right grey">
                	<input name="startTime" class="input-text wp30 t-right mright20" type="text" placeholder="09:00" value="${merchantSupply.startTime}" maxlength="5" datatype="hourAndMin" nullmsg="请输入营业开始时间！" errormsg="时间格式为  09:00-21:00" />-
                	<input name="endTime" class="input-text wp30 t-right" type="text" placeholder="21:00" value="${merchantSupply.endTime}" maxlength="5" datatype="hourAndMin" nullmsg="请输入营业结束时间！" errormsg="时间格式为  09:00-21:00" />
                </div>
            </div>
        </li>
        <!-- <li>
            <div class="displaybox">
                <div class="item-standard-name height36 f16 boxflex01">营业周期</div>
                <div class="boxflex01 f16 t-right grey"><input class="input-text wp100 t-right" type="text" placeholder="如：周一至周六" value="" datatype="*" nullmsg="请输入营业周期！" /></div>
            </div>
        </li> -->
    </ul>
    
    <section class="divide-box pb56"></section>
    <ul class="bottom-menu-box displaybox t-center">
        <li class="p00"><input class="btn-submit btn-next noradius bordertbgrey btnSubmit red" type="button" name="button" value="提交"></li>
    </ul>

</section>
</form>
<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/fastclick.js"></script>
<script src="../js/merchant/Validform_v5.3.2.js?20170111"></script>
<script src="../js/jquery.form.js"></script>
<script>
$(function(){
	//表单验证
	$(".inputform").Validform({
		tiptype:1,
		btnSubmit:".btnSubmit",
		beforeSubmit:function(){
			$.Showmsg("请稍后…");
		},
		callback:function(data){
			$("#inputform").ajaxSubmit({  
                success: function(data){
					$.Showmsg(data.message);
					window.location.href = "setting.html";
                },  
                error: function(){  
                	$.Showmsg('网络不给力，请稍后重试'); 
                }  
            }); 
		}
	});

	new FastClick(document.body);
})
</script>


</body>
</html>